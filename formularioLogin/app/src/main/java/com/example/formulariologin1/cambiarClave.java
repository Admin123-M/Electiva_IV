package com.example.formulariologin1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.formulariologin.R;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class cambiarClave extends AppCompatActivity {
EditText claveA,claveN1,claveN2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_clave);
        claveA=(EditText)findViewById(R.id.txtClaveA);
        claveN1=(EditText)findViewById(R.id.txtClaveN1);
        claveN2=(EditText)findViewById(R.id.txtClaveN2);
    }
    public void regresar(View view){
        Intent reg = new Intent(this, MainActivity.class);
        startActivity(reg);
    }


    public void Actulizar(View view){
        AdminSQLI admin =  new AdminSQLI(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String clave1 = claveA.getText().toString();
        String claveNueva2 = claveN1.getText().toString();
        String claveNueva3= claveN2.getText().toString();
        if(!clave1.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery("select clave1 from formularios where clave1 =" + clave1,null);
            if (fila.moveToFirst()) {
                if(claveNueva2.equals(claveNueva3)) {
                    ContentValues registrar = new ContentValues();
                    registrar.put("clave1",claveNueva2);
                    int cant = BaseDeDatos.update("formularios",registrar, "clave1=" + clave1,null);
                    claveA.setText("");
                    claveN1.setText("");
                    claveN2.setText("");
                    BaseDeDatos.close();
                    Toast.makeText(this,"Contraseña actualizad", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"Las clave propocionado no son identicas",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,"Debe ingresar una clave", Toast.LENGTH_SHORT);
                BaseDeDatos.close();
            }
                    }else{
            Toast.makeText(this,"Debe ingresar datos",Toast.LENGTH_SHORT).show();
        }

    }

    }

