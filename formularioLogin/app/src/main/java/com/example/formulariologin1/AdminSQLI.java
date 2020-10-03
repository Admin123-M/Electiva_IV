package com.example.formulariologin1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLI extends SQLiteOpenHelper {
    public AdminSQLI(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    //Base de datos de formulario de registro de Jefe de compañia
    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {


        BaseDeDatos.execSQL("create table formularios(cedula1 int primary key,nombre1 text, apellido1 text, correo1 text, clave1 text, telefono1 text, fecha1 text)");




    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
