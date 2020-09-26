package com.example.formulariologin1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import com.example.formulariologin.R;

public class MainActivity extends AppCompatActivity {
    private EditText cedula,clave;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        cedula=(EditText)findViewById(R.id.txtD);
        clave=(EditText)findViewById(R.id.txtP);

    }

    public void Ingresar(final View view){
        AdminSQLI admin = new AdminSQLI(this, "administracion",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String cedula1 = cedula.getText().toString();
        String clave1 = clave.getText().toString();
        if(!cedula1.isEmpty() && !clave1.isEmpty()) {
            Cursor  fila =BaseDeDatos.rawQuery("select cedula1 from  formularios where cedula1=" + cedula1,null);
        if(fila.moveToFirst() && clave1.equals(cedula1)){

            AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
            alerta.setMessage("¿Deseas Mantener la Sesión activa?")
                    .setCancelable(false)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            SharedPreferences preferencias = getSharedPreferences("admin", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferencias.edit();
                            editor.putString("cedula1", cedula.getText().toString());
                            editor.putString("clave1", clave.getText().toString());

                            editor.commit();
                            IrPreferecia();


                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            IrPreferecia();

                        }

                    });
            AlertDialog titulo = alerta.create();
            titulo.setTitle("Corfirmación");
            titulo.show();


        }else{
            Toast.makeText(this,"Usuario o clave incorrecto",Toast
            .LENGTH_SHORT).show();
        }
        }else{
            Toast.makeText(this,"Ingrese Clave y Contraseña", Toast.LENGTH_SHORT).show();
        }

        }
        public void Registrar(View view){
        Intent reg = new Intent(this,formulario.class);
        startActivity(reg);

        }
    public void IrPreferecia() {
        /*SharedPreferences preferencias = getSharedPreferences("administracion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        String cedula1 =cedula.getText().toString();
        String clave1 = clave.getText().toString();
        editor.putString("cedula1", cedula1);
        editor.putString("clave1", clave1);
        editor.commit();*/
        Intent reg = new Intent(this,menu_principal.class);
        startActivity(reg);

    }




    }

