package com.example.formulariologin1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.formulariologin.R;

public class Listar_soldados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_listar_soldados);


    }

    public void regresar(View view) {
        Intent regresar = new Intent(this, menu_principal.class);
        startActivity(regresar);
    }

    public void soldados(View view) {
        TextView tv_usuarios =(TextView)findViewById(R.id.tvSoldados);
        tv_usuarios.setText("");
        AdminSQLI admin = new AdminSQLI(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String cedula1 = "Activo";
        //String bat = batallon.getAdapter().toString();

        Cursor fila = BaseDeDatos.rawQuery
                ("select cedulaS, nombre1, apellido1, telefono1, grado, batallon1, compañia,ubicacion from soldado ", null);

        //me confirma oo revisa si la consulta tiene valores
        while (fila.moveToNext()) {

            String ci = fila.getString(0);
            String nombre = fila.getString(1);
            String apellido = fila.getString(2);
            //String correo = fila.getString(3);
            //clave.setText(fila.getString(3));
            String telefono = fila.getString(3);
            String grado = fila.getString(4);
            String batallon = fila.getString(5);
            String compañia = fila.getString(6);
            String ubicacion = fila.getString(7);
            //fila.getString(5);
            //sexoA.setSelected(true);
            //sexoB.setSelected(true);

            String detalle = "CI: " + ci + "\n" +
                    "Nombre : " + nombre + "\n" +
                    "Apellido: " + apellido + "\n" +
                    "Numero: " + telefono + "\n" +
                    "Grado: " + grado + "\n" +
                    "Batallon: " + batallon + "\n" +
                    "Compañia: " + compañia + "\n" +
                    "Ubicacion: " + ubicacion + "\n\n";

            tv_usuarios.append(detalle);


            BaseDeDatos.close();
            Toast.makeText(this, "Busqueda exitosa", Toast.LENGTH_SHORT).show();


        }
    }

    public void usuaios(View view) {
        TextView tv_usuarios =(TextView)findViewById(R.id.tvUsuarios);
        tv_usuarios.setText("");
        AdminSQLI admin = new AdminSQLI(this,"administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String cedula1 = "Activo";
        //String bat = batallon.getAdapter().toString();

            Cursor fila = BaseDeDatos.rawQuery
                    ("select cedula1, nombre1, apellido1, correo1, telefono1, fecha1, batallon1, sexo1,estado from formularios ", null);

            //me confirma oo revisa si la consulta tiene valores
            while (fila.moveToNext()) {

                String ci = fila.getString(0);
                String nombre = fila.getString(1);
                String apellido = fila.getString(2);
                String correo = fila.getString(3);
                //clave.setText(fila.getString(3));
                String telefono = fila.getString(4);
                String fecha = fila.getString(5);
                String batallon = fila.getString(6);
                //fila.getString(5);
                //sexoA.setSelected(true);
                //sexoB.setSelected(true);

                String detalle = "CI: " + ci + "\n" +
                        "Nombre : " + nombre + "\n" +
                        "Apellido: " + apellido + "\n" +
                        "Correo: " + correo + "\n" +
                        "Numero: " + telefono + "\n" +
                        "Fecha: " + fecha + "\n" +
                        "Batallon: " + batallon + "\n\n";

                tv_usuarios.append(detalle);


                BaseDeDatos.close();
                Toast.makeText(this, "Busqueda exitosa", Toast.LENGTH_SHORT).show();


        }
    }
}