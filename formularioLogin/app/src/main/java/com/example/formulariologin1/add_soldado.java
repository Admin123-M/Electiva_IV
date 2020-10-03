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
            //notificacionDialog();
            NotificationManager notificationManager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
            String NOTIFICATION_CHANEL_ID = "tutorialspoint_01";
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                @SuppressLint("WrongConstant")NotificationChannel notificationChannel =
                        new NotificationChannel(NOTIFICATION_CHANEL_ID, "My Notifications",  NotificationManager.IMPORTANCE_HIGH);
                notificationChannel.setDescription("Descripcion de canal simple");
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,NOTIFICATION_CHANEL_ID);
            notificationBuilder.setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(android.R.drawable.stat_notify_chat)
                    .setTicker("El usuario "+nombre1+" "+apelllido1 +" se registro con exito")
                    .setContentTitle("Registro de usuario")
                    .setContentText("El usuario "+nombre1+" "+apelllido1 +" se registro con exito")
                    .setContentInfo("Nuevo Registro");
            notificationManager.notify(1,notificationBuilder.build());




        } else {
            Toast.makeText(this, "Debe ingresar los datos", Toast.LENGTH_SHORT).show();
        }


    }

    //@RequiresApi(api= Build.VERSION_CODES.0)



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



}
