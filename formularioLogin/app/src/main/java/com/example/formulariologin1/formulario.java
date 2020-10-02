package com.example.formulariologin1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.database.Cursor;

import com.example.formulariologin.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class formulario extends AppCompatActivity {
    private EditText cedula,clave, nombre, apellido, correo, telefono, fecha;
    private Button candelario;
    private Spinner batallon;
    private int dd, mm, aa;
    private RadioButton sexoA,sexoB;
    private RadioGroup radG;
    private boolean  sexo4;
    private boolean sexo5;
    DatePicker picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        cedula = (EditText) findViewById(R.id.txtCedula);

        nombre = (EditText) findViewById(R.id.txtNombre);
        apellido = (EditText) findViewById(R.id.txtApellido);
        correo = (EditText) findViewById(R.id.txtCorreo);

        //clave=(EditText)findViewById(R.id.txtCla);
        telefono = (EditText) findViewById(R.id.txtTelefono);
        fecha = (EditText) findViewById(R.id.txtCa);

        batallon = (Spinner)findViewById(R.id.spinner);
        String [] opciones ={"Batallones","CUINMA","BIMJAR","BIMJAM","BIMUIL","BIMEDU","BASEDU","BIMLOR","BIMESM"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opciones);
        batallon.setAdapter(adapter);
        sexoA = (RadioButton)findViewById(R.id.radioButton1);
        sexoB = (RadioButton)findViewById(R.id.radioButton2);
        radG = (RadioGroup)findViewById(R.id.radioG);


    }

    public void regresar(View view) {
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }

    public void regFormu(View view) {

        AdminSQLI admin = new AdminSQLI(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String cedula1 = cedula.getText().toString();
        String nombre1 = nombre.getText().toString();
        String apelllido1 = apellido.getText().toString();
        String correo1 = correo.getText().toString();
        //String clave1 = clave.getText().toString();
        String telefono1 = telefono.getText().toString();
        String fecha1 = fecha.getText().toString();
        String batallon1 = String.valueOf(batallon.getSelectedItem());
        String sexo1 = sexoA.getText().toString();
        String sexo2 = sexoB.getText().toString();

        if (!cedula1.isEmpty() && !nombre1.isEmpty() && !apelllido1.isEmpty()
                && !correo1.isEmpty() && !telefono1.isEmpty() && !fecha1.isEmpty() && !batallon1.isEmpty())
                {
            ContentValues registrar = new ContentValues();
            registrar.put("cedula1", cedula1);
            registrar.put("nombre1", nombre1);
            registrar.put("apellido1", apelllido1);
            registrar.put("correo1", correo1);
            registrar.put("clave1",cedula1);
            registrar.put("telefono1", telefono1);
            registrar.put("fecha1",fecha1);
            registrar.put("batallon1",batallon1);
            if(sexoA.isChecked()) {
                sexo4 = true;
                registrar.put("sexo1", sexo4);

            }else{
                sexo4=false;
                registrar.put("sexo1", sexo4);

                }
                    if(sexoB.isChecked()){
                        sexo5=true;
                        registrar.put("sexo1", sexo5);
            }else{
                        sexo5=false;
                        registrar.put("sexo1", sexo5);
                    }


            BaseDeDatos.insert("formularios", null, registrar);
            BaseDeDatos.close();

            cedula.setText("");
            nombre.setText("");
            apellido.setText("");
            correo.setText("");
            //clave.setText("");
            telefono.setText("");
            fecha.setText("");
           // batallon.setEmptyView(batallon);
            radG.clearCheck();
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Debe ingresar los datos", Toast.LENGTH_SHORT).show();
        }


    }

    public void Buscar(View view) {
        AdminSQLI admin = new AdminSQLI(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String cedula1 = cedula.getText().toString();
        //String bat = batallon.getAdapter().toString();
        if (!cedula1.isEmpty()) {
            Cursor fila = BaseDeDatos.rawQuery
                    ("select nombre1, apellido1, correo1, telefono1, fecha1, batallon1, sexo1 from formularios where cedula1 =" + cedula1, null);

            //me confirma oo revisa si la consulta tiene valores
            if (fila.moveToFirst()) {
                nombre.setText(fila.getString(0));
                apellido.setText(fila.getString(1));
                correo.setText(fila.getString(2));
               //clave.setText(fila.getString(3));
                telefono.setText(fila.getString(3));
                fecha.setText(fila.getString(4));
                batallon.setSelection(5);
                //fila.getString(5);
                //sexoA.setSelected(true);
                //sexoB.setSelected(true);


                BaseDeDatos.close();
                Toast.makeText(this, "Busqueda exitosa", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }

        } else {
            Toast.makeText(this, "Debe ingresar datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void Eliminar(View view) {
        AdminSQLI admin = new AdminSQLI(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String cedula1 = cedula.getText().toString();
        if (!cedula1.isEmpty()) {
            int cant = BaseDeDatos.delete(" formularios", "cedula1=" + cedula1, null);
            BaseDeDatos.close();
            cedula.setText("");
            nombre.setText("");
            apellido.setText("");
            correo.setText("");
           // usuario.setText("");
            clave.setText("");
            telefono.setText("");
            fecha.setText("");
            Toast.makeText(this, "Su registro fue elimnado exitosamente", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Ingrese dato", Toast.LENGTH_SHORT).show();
        }
    }

    public void Actualizar(View view) {
        AdminSQLI admin = new AdminSQLI(this, "administracion", null, 1);
        SQLiteDatabase BaseDedatos = admin.getWritableDatabase();
        String cedula1 = cedula.getText().toString();
        String nombre1 = nombre.getText().toString();
        String apelllido1 = apellido.getText().toString();
        String correo1 = correo.getText().toString();
        //String clave1 = clave.getText().toString();
        String telefono1 = telefono.getText().toString();
        String fecha1 = fecha.getText().toString();
        String batallon1 = batallon.getAdapter().toString();
        if (!cedula1.isEmpty() && !nombre1.isEmpty() && !apelllido1.isEmpty()
                && !correo1.isEmpty() && !telefono1.isEmpty() ) {
            ContentValues registrar = new ContentValues();
            registrar.put("cedula1", cedula1);
            registrar.put("nombre1", nombre1);
            registrar.put("apellido1", apelllido1);
            registrar.put("correo1", correo1);
            //registrar.put("usuario1",usuario1);
            //registrar.put("clave1",cedula1);
            registrar.put("telefono1", telefono1);
            registrar.put("fecha1",fecha1);
         registrar.put("batallon1",batallon1);
            int cant = BaseDedatos.update("formularios", registrar, "cedula1=" + cedula1, null);
            cedula.setText("");
            nombre.setText("");
            apellido.setText("");
            correo.setText("");
            //clave.setText("");
            telefono.setText("");
            fecha.setText("");
            //batallon.setAdapter("");
            if (cant == 1) {
                Toast.makeText(this, "Actualizacion fue exitosa", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(this, "Ingrese datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View v) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
        Date date = null;
        try {
            date = sdf.parse("2020/10/02");
        }catch (ParseException e){
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        dd = c.get(Calendar.YEAR);
        mm = c.get(Calendar.MONTH);
        aa = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int daysOfMonth) {
                fecha.setText(daysOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        }
                , dd, mm, aa);
        datePickerDialog.show();




    }
}