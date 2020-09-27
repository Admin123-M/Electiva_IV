package com.example.formulariologin1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.formulariologin.R;
import android.view.View;

public class cambiarClave extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_clave);
    }
    public void regresar(View view){
        Intent reg = new Intent(this, MainActivity.class);
        startActivity(reg);
    }
}