package com.example.formulariologin1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLI extends SQLiteOpenHelper {
    public AdminSQLI(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
<<<<<<< HEAD
        BaseDeDatos.execSQL("create table formularios(cedula1 int primary key,nombre1 text, apellido1 text, correo1 text, clave1 text, telefono1 text, batallon1 text,fecha1 text, sexo1 booleano)");

=======
        BaseDeDatos.execSQL("create table formularios(cedula1 int primary key, nombre1 text, apellido1 text, correo1 text, telefono1 text, fecha1,batallon1 text)");
        BaseDeDatos.execSQL("create table soldado(cedulaS int primary key, nombre1 text, apellido1 text, telefono1 text, grado text,batallon1 text, compañia text, ubicacion text)");
>>>>>>> 49c2383f9cc60eb67a8a030042a89713edadc07a
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
