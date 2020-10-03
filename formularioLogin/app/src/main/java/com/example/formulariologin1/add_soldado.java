package com.example.formulariologin1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.formulariologin.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class add_soldado extends AppCompatActivity {

    private EditText cedula,clave, nombre, apellido, correo, telefono, fecha;
    private Spinner Sbatallon, Sgrado, Scompañia, Subicacion;

    private PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_soldado);



        cedula = (EditText) findViewById(R.id.txtCedula);
        nombre = (EditText) findViewById(R.id.txtNombre);
        apellido = (EditText) findViewById(R.id.txtApellido);
        telefono = (EditText) findViewById(R.id.txtTelefono);

        Sgrado = (Spinner)findViewById(R.id.spinnerg);
        String [] opcionesG ={"Grado","MARO","CBOS","CBOP","SGOS","SGOP","SUBS","SUBP","ALFG","TNFG","TNNV","CPCB","CPFG","CPNV"};
        ArrayAdapter <String> adapterG = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opcionesG);
        Sgrado.setAdapter(adapterG);

        Sbatallon = (Spinner)findViewById(R.id.spinner);
        String [] opciones ={"Batallones","CUINMA","BIMJAR","BIMJAM","BIMUIL","BIMEDU","BASEDU","BIMLOR","BIMESM"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opciones);
        Sbatallon.setAdapter(adapter);


        Scompañia = (Spinner)findViewById(R.id.spinnerc);
        String [] opcionesC ={"COMPAÑIA","ALFA","BRAVO","CHARLIE","DELTA"};
        ArrayAdapter <String> adapterC = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opcionesC);
        Scompañia.setAdapter(adapterC);

        Subicacion = (Spinner)findViewById(R.id.spinneru);
        String [] opcionesU ={"ESTADO","PRESENTE","GUARDIA","COMISION","HOSPITAL","PERMISO","TERRENO"};
        ArrayAdapter <String> adapterU = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opcionesU);
        Subicacion.setAdapter(adapterU);


    }

    public void regresar(View view) {
        Intent regresar = new Intent(this, menu_principal.class);
        startActivity(regresar);
    }

    public void regFormu(View view) {

        AdminSQLI admin = new AdminSQLI(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String cedula1 = cedula.getText().toString();
        String nombre1 = nombre.getText().toString();
        String apelllido1 = apellido.getText().toString();
        String telefono1 = telefono.getText().toString();
        String grado1 = Sgrado.getSelectedItem().toString();
        String batallon1 = Sbatallon.getSelectedItem().toString();
        String compañia1 = Scompañia.getSelectedItem().toString();
        String ubicacion1 = Subicacion.getSelectedItem().toString();



        if (!cedula1.isEmpty() && !nombre1.isEmpty() && !apelllido1.isEmpty()
                && !telefono1.isEmpty() && !grado1.isEmpty() && !batallon1.isEmpty() && !compañia1.isEmpty()  && !ubicacion1.isEmpty() )
        {
            ContentValues registrar = new ContentValues();
            registrar.put("cedulaS", cedula1);
            registrar.put("nombre1", nombre1);
            registrar.put("apellido1", apelllido1);
            registrar.put("telefono1", telefono1);
            registrar.put("grado", grado1);
            registrar.put("batallon1", batallon1);
            registrar.put("compañia", compañia1);
            registrar.put("ubicacion", ubicacion1);



            BaseDeDatos.insert("soldado", null, registrar);
            BaseDeDatos.close();

            cedula.setText("");
            nombre.setText("");
            apellido.setText("");
            telefono.setText("");


            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Debe ingresar los datos", Toast.LENGTH_SHORT).show();
        }


    }

    //@RequiresApi(api= Build.VERSION_CODES.0)



    public void Buscar(View view) {
        AdminSQLI admin = new AdminSQLI(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String cedula1 = cedula.getText().toString();
        String batallonact = "", gradoact = "", compañiaact ="", ubicacionact ="";
        if (!cedula1.isEmpty()) {
            Cursor fila = BaseDeDatos.rawQuery
                    ("select nombre1, apellido1, telefono1, grado, batallon1, compañia, ubicacion from soldado where cedulaS =" + cedula1, null);

            //me confirma oo revisa si la consulta tiene valores
            if (fila.moveToFirst()) {

                nombre.setText(fila.getString(0));
                apellido.setText(fila.getString(1));
                telefono.setText(fila.getString(2));
                gradoact= fila.getString(3);
                batallonact= fila.getString(4);
                compañiaact=fila.getString(5);
                ubicacionact=fila.getString(6);



                Sgrado = (Spinner)findViewById(R.id.spinnerg);
                String [] opcionesG ={gradoact,"MARO","CBOS","CBOP","SGOS","SGOP","SUBS","SUBP","ALFG","TNFG","TNNV","CPCB","CPFG","CPNV"};
                ArrayAdapter <String> adapterG = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opcionesG);
                Sgrado.setAdapter(adapterG);

                Sbatallon = (Spinner)findViewById(R.id.spinner);
                String [] opciones ={batallonact ,"CUINMA","BIMJAR","BIMJAM","BIMUIL","BIMEDU","BASEDU","BIMLOR","BIMESM"};
                ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opciones);
                Sbatallon.setAdapter(adapter);


                Scompañia = (Spinner)findViewById(R.id.spinnerc);
                String [] opcionesC ={compañiaact,"ALFA","BRAVO","CHARLIE","DELTA"};
                ArrayAdapter <String> adapterC = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opcionesC);
                Scompañia.setAdapter(adapterC);

                Subicacion = (Spinner)findViewById(R.id.spinneru);
                String [] opcionesU ={ubicacionact,"PRESENTE","GUARDIA","COMISION","HOSPITAL","PERMISO","TERRENO"};
                ArrayAdapter <String> adapterU = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opcionesU);
                Subicacion.setAdapter(adapterU);





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
            int cant = BaseDeDatos.delete(" soldado", "cedulaS=" + cedula1, null);
            BaseDeDatos.close();
            cedula.setText("");
            nombre.setText("");
            apellido.setText("");
            telefono.setText("");

            Sgrado = (Spinner)findViewById(R.id.spinnerg);
            String [] opcionesG ={"Grado","MARO","CBOS","CBOP","SGOS","SGOP","SUBS","SUBP","ALFG","TNFG","TNNV","CPCB","CPFG","CPNV"};
            ArrayAdapter <String> adapterG = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opcionesG);
            Sgrado.setAdapter(adapterG);

            Sbatallon = (Spinner)findViewById(R.id.spinner);
            String [] opciones ={"Batallones","CUINMA","BIMJAR","BIMJAM","BIMUIL","BIMEDU","BASEDU","BIMLOR","BIMESM"};
            ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opciones);
            Sbatallon.setAdapter(adapter);


            Scompañia = (Spinner)findViewById(R.id.spinnerc);
            String [] opcionesC ={"COMPAÑIA","ALFA","BRAVO","CHARLIE","DELTA"};
            ArrayAdapter <String> adapterC = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opcionesC);
            Scompañia.setAdapter(adapterC);

            Subicacion = (Spinner)findViewById(R.id.spinneru);
            String [] opcionesU ={"ESTADO","PRESENTE","GUARDIA","COMISION","HOSPITAL","PERMISO","TERRENO"};
            ArrayAdapter <String> adapterU = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opcionesU);
            Subicacion.setAdapter(adapterU);

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
        String telefono1 = telefono.getText().toString();
        String grado1 = Sgrado.getSelectedItem().toString();
        String batallon1 = Sbatallon.getSelectedItem().toString();
        String compañia1 = Scompañia.getSelectedItem().toString();
        String ubicacion1 = Subicacion.getSelectedItem().toString();

        if (!cedula1.isEmpty() && !nombre1.isEmpty() && !apelllido1.isEmpty()
                && !telefono1.isEmpty() && !grado1.isEmpty() && !batallon1.isEmpty() && !compañia1.isEmpty()  && !ubicacion1.isEmpty() )
        {
            ContentValues registrar = new ContentValues();
            registrar.put("cedulaS", cedula1);
            registrar.put("nombre1", nombre1);
            registrar.put("apellido1", apelllido1);
            registrar.put("telefono1", telefono1);
            registrar.put("grado", grado1);
            registrar.put("batallon1", batallon1);
            registrar.put("compañia", compañia1);
            registrar.put("ubicacion", ubicacion1);

            int cant = BaseDedatos.update("soldado", registrar, "cedulaS=" + cedula1, null);
            cedula.setText("");
            nombre.setText("");
            apellido.setText("");
            telefono.setText("");

            Sgrado = (Spinner)findViewById(R.id.spinnerg);
            String [] opcionesG ={"Grado","MARO","CBOS","CBOP","SGOS","SGOP","SUBS","SUBP","ALFG","TNFG","TNNV","CPCB","CPFG","CPNV"};
            ArrayAdapter <String> adapterG = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opcionesG);
            Sgrado.setAdapter(adapterG);

            Sbatallon = (Spinner)findViewById(R.id.spinner);
            String [] opciones ={"Batallones","CUINMA","BIMJAR","BIMJAM","BIMUIL","BIMEDU","BASEDU","BIMLOR","BIMESM"};
            ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opciones);
            Sbatallon.setAdapter(adapter);


            Scompañia = (Spinner)findViewById(R.id.spinnerc);
            String [] opcionesC ={"COMPAÑIA","ALFA","BRAVO","CHARLIE","DELTA"};
            ArrayAdapter <String> adapterC = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opcionesC);
            Scompañia.setAdapter(adapterC);

            Subicacion = (Spinner)findViewById(R.id.spinneru);
            String [] opcionesU ={"ESTADO","PRESENTE","GUARDIA","COMISION","HOSPITAL","PERMISO","TERRENO"};
            ArrayAdapter <String> adapterU = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,opcionesU);
            Subicacion.setAdapter(adapterU);

            if (cant == 1) {
                Toast.makeText(this, "Actualizacion fue exitosa", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(this, "Ingrese datos", Toast.LENGTH_SHORT).show();
        }
    }



}
