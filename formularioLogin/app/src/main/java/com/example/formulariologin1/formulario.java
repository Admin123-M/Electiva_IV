package com.example.formulariologin1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
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

    private int dd, mm, aa;
    private RadioButton sexoA,sexoB;
    private RadioGroup radG;
    private PendingIntent pendingIntent;


    DatePicker picker;
    @Override
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        
        cedula = (EditText) findViewById(R.id.txtCedula);

        nombre = (EditText) findViewById(R.id.txtNombre);
        apellido = (EditText) findViewById(R.id.txtApellido);
        correo = (EditText) findViewById(R.id.txtCorreo);

        telefono = (EditText) findViewById(R.id.txtTelefono);
        fecha = (EditText) findViewById(R.id.txtCa);


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

        String telefono1 = telefono.getText().toString();
        String fecha1 = fecha.getText().toString();


        if (!cedula1.isEmpty() && !nombre1.isEmpty() && !apelllido1.isEmpty()
                && !correo1.isEmpty() && !telefono1.isEmpty() && !fecha1.isEmpty())
                {
            ContentValues registrar = new ContentValues();
            registrar.put("cedula1", cedula1);
            registrar.put("nombre1", nombre1);
            registrar.put("apellido1", apelllido1);
            registrar.put("correo1", correo1);
            registrar.put("clave1",cedula1);
            registrar.put("telefono1", telefono1);
            registrar.put("fecha1",fecha1);

            BaseDeDatos.insert("formularios", null, registrar);
            BaseDeDatos.close();

            cedula.setText("");
            nombre.setText("");
            apellido.setText("");
            correo.setText("");

            telefono.setText("");
            fecha.setText("");


            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Debe ingresar los datos", Toast.LENGTH_SHORT).show();
        }


    }

    public void Buscar(View view) {
        AdminSQLI admin = new AdminSQLI(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String cedula1 = cedula.getText().toString();

        if (!cedula1.isEmpty()) {
            Cursor fila = BaseDeDatos.rawQuery
                    ("select nombre1, apellido1, correo1, telefono1, fecha1 from formularios where cedula1 =" + cedula1, null);

            //me confirma oo revisa si la consulta tiene valores
            if (fila.moveToFirst()) {
                nombre.setText(fila.getString(0));
                apellido.setText(fila.getString(1));
                correo.setText(fila.getString(2));

                telefono.setText(fila.getString(3));
                fecha.setText(fila.getString(4));



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

        String telefono1 = telefono.getText().toString();
        String fecha1 = fecha.getText().toString();

        if (!cedula1.isEmpty() && !nombre1.isEmpty() && !apelllido1.isEmpty()
                && !correo1.isEmpty() && !telefono1.isEmpty() ) {
            ContentValues registrar = new ContentValues();
            registrar.put("cedula1", cedula1);
            registrar.put("nombre1", nombre1);
            registrar.put("apellido1", apelllido1);
            registrar.put("correo1", correo1);

            registrar.put("telefono1", telefono1);
            registrar.put("fecha1",fecha1);

            int cant = BaseDedatos.update("formularios", registrar, "cedula1=" + cedula1, null);
            cedula.setText("");
            nombre.setText("");
            apellido.setText("");
            correo.setText("");

            telefono.setText("");
            fecha.setText("");

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
