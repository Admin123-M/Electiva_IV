package com.example.formulariologin1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.formulariologin.R;

public class ImportSoldados extends AppCompatActivity {


    private static final int CODIGO_SOLICITUD_PERMISO = 1;
    private Activity activity;
    Button add_soldados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_soldados);

        activity = this;
        add_soldados = (Button)findViewById(R.id.add_soldados);
    }

    public void mostrarcontactos(View v)
    {

        if (chequearStatusPermiso()){
            consultarCPLlamadas();
        } else{
            solicitarPermiso();
        }
    }
    public void regresar(View view) {
        Intent regresar = new Intent(this, menu_principal.class);
        startActivity(regresar);
    }

    public void import_sol(View v){



        Uri direccionUriLlamadas = ContactsContract.Data.CONTENT_URI;
        String[] campos = {
                ContactsContract.Data._ID,
                ContactsContract.Data.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.TYPE
        };
        String selectionClause = ContactsContract.Data.MIMETYPE + "='" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
                + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";
        ContentResolver contentResolver = getContentResolver();
        Cursor registros = contentResolver.query(direccionUriLlamadas,
                campos,
                selectionClause ,
                null,
                ContactsContract.Data.DISPLAY_NAME + " DESC");
        while (registros.moveToNext()){
            //Se obtiene los datos a partir del indice de la columna.
            String id   = registros.getString(registros.getColumnIndex(campos[0]));
            String nombre   = registros.getString(registros.getColumnIndex(campos[1]));
            String numero   = registros.getString(registros.getColumnIndex(campos[3]));
            String nalt = registros.getString(registros.getColumnIndex(campos[2]));
            String [] nom_array = nalt.split(",");
            int grado = 0;
            int batallon = 0;
            int compañia = 0;
            if(nom_array.length==6) {


                String nombre1 = nom_array[1];
                String apellido = nom_array[0];
                String ci = nom_array[2];
                String cadena1 = nom_array[3];
                String cadena2 = nom_array[4];
                String cadena3 = nom_array[5];
                if (cadena1.matches("[0-9]*")) {
                    grado = Integer.parseInt(cadena1);
                } else {
                    grado = 17;
                }


                if (cadena2.matches("[0-9]*")) {
                    batallon = Integer.parseInt(cadena2);
                } else {
                    batallon = 17;
                }


                if (cadena3.matches("[0-9]*")) {
                    compañia = Integer.parseInt(cadena3);
                } else {
                    compañia = 17;
                }
                String compañia_d = "";
                switch (compañia) {
                    case 1:
                        compañia_d = "ALFA";
                        break;
                    case 2:
                        compañia_d = "BRAVO";
                        break;
                    case 3:
                        compañia_d = "CHARLIE";
                        break;
                    case 4:
                        compañia_d = "DELTA";
                        break;
                    case 17:
                        compañia_d = cadena3;
                        break;

                    default:
                        compañia_d = "ALFA";

                }
                String grado_d = "";
                switch (grado) {
                    case 1:
                        grado_d = "MARO";
                        break;
                    case 2:
                        grado_d = "CBOS";
                        break;
                    case 3:
                        grado_d = "CBOP";
                        break;
                    case 4:
                        grado_d = "SGOS";
                        break;
                    case 5:
                        grado_d = "SGOP";
                        break;
                    case 6:
                        grado_d = "SUBS";
                        break;
                    case 7:
                        grado_d = "SUBP";
                        break;
                    case 8:
                        grado_d = "ALFG";
                        break;
                    case 9:
                        grado_d = "TNFG";
                        break;
                    case 10:
                        grado_d = "TNNV";
                        break;
                    case 11:
                        grado_d = "CPCB";
                        break;
                    case 12:
                        grado_d = "CPFG";
                        break;
                    case 13:
                        grado_d = "CPNV";
                        break;
                    case 17:
                        grado_d = cadena1;
                        break;

                    default:
                        grado_d = "MARO";

                }
                String batallon_d = "";
                switch (batallon) {
                    case 1:
                        batallon_d = "BIMLOR";
                        break;
                    case 2:
                        batallon_d = "BIMESM";
                        break;
                    case 3:
                        batallon_d = "BIMJAR";
                        break;
                    case 4:
                        batallon_d = "BIMJAM";
                        break;
                    case 5:
                        batallon_d = "BIMEDU";
                        break;
                    case 6:
                        batallon_d = "BIMUIL";
                        break;
                    case 7:
                        batallon_d = "CUINMA";
                        break;
                    case 17:
                        batallon_d = cadena2;
                        break;
                    default:
                        batallon_d = "BIMLOR";

                }
                String tipoLlamada = "";


                //--------------------------


                AdminSQLI admin = new AdminSQLI(this, "administracion", null, 1);
                SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

                if (!ci.isEmpty() && !nombre1.isEmpty() && !apellido.isEmpty()
                        && !numero.isEmpty() && !grado_d.isEmpty() && !batallon_d.isEmpty() && !compañia_d.isEmpty()) {

                    ContentValues registrar = new ContentValues();
                    registrar.put("cedulaS", ci);
                    registrar.put("nombre1", nombre1);
                    registrar.put("apellido1", apellido);
                    registrar.put("telefono1", numero);
                    registrar.put("grado", grado_d);
                    registrar.put("batallon1", batallon_d);
                    registrar.put("compañia", compañia_d);
                    registrar.put("ubicacion", "ND");

                    BaseDeDatos.insert("soldado", null, registrar);
                    BaseDeDatos.close();

                    Toast.makeText(this, "Registro exitoso de " + nombre1 + " " + apellido, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "No se guardo", Toast.LENGTH_SHORT).show();
                }
            }






        }

    }

    public void solicitarPermiso()
    {

        boolean solicitarPermisoRCL = ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CONTACTS);
        boolean solicitarPermisoWCL = ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_CONTACTS);

        if (solicitarPermisoRCL && solicitarPermisoWCL) {
            Toast.makeText(ImportSoldados.this, "Los permisos fueron otorgados", Toast.LENGTH_SHORT).show();
        }
        else{
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}, CODIGO_SOLICITUD_PERMISO);
        }

    }

    public boolean chequearStatusPermiso()
    {
        boolean permisoRealCallLog = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
        boolean permisoWriteCallLog = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) == PackageManager.PERMISSION_GRANTED;

        if (permisoRealCallLog && permisoWriteCallLog){
            Toast.makeText(ImportSoldados.this, "Los permisos estan vigentes", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            Toast.makeText(ImportSoldados.this, "no tiene permisos", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case CODIGO_SOLICITUD_PERMISO:
                if (chequearStatusPermiso()){
                    Toast.makeText(ImportSoldados.this, "Ya está activo el permiso", Toast.LENGTH_SHORT).show();
                    consultarCPLlamadas();
                }else{
                    Toast.makeText(ImportSoldados.this, "No se activó el permiso", Toast.LENGTH_SHORT).show();
                }
        }
    }



    public void consultarCPLlamadas()
    {
        TextView tvLlamadas = (TextView) findViewById(R.id.Tv_contactos);
        tvLlamadas.setText("");
        Uri direccionUriLlamadas = ContactsContract.Data.CONTENT_URI;
        String[] campos = {
                ContactsContract.Data._ID,
                ContactsContract.Data.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.TYPE
        };
        String selectionClause = ContactsContract.Data.MIMETYPE + "='" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
                + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";
        ContentResolver contentResolver = getContentResolver();
        Cursor registros = contentResolver.query(direccionUriLlamadas,
                campos,
                selectionClause ,
                null,
                ContactsContract.Data.DISPLAY_NAME + " DESC");
        while (registros.moveToNext()){
            //Se obtiene los datos a partir del indice de la columna.
            String id   = registros.getString(registros.getColumnIndex(campos[0]));
            String nombre   = registros.getString(registros.getColumnIndex(campos[1]));
            String numero   = registros.getString(registros.getColumnIndex(campos[3]));
            String nalt = registros.getString(registros.getColumnIndex(campos[2]));
            String [] nom_array = nalt.split(",");
            int grado = 0;
            int batallon = 0;
            int compañia = 0;


            if(nom_array.length==6) {

                String apellido = nom_array[0];
                String nombre1 = nom_array[1];
                String ci = nom_array[2];
                String cadena1 = nom_array[3];
                String cadena2 = nom_array[4];
                String cadena3 = nom_array[5];

                if (cadena1.matches("[0-9]*")) {
                    grado = Integer.parseInt(cadena1);
                } else {
                    grado = 17;
                }


                if (cadena2.matches("[0-9]*")) {
                    batallon = Integer.parseInt(cadena2);
                } else {
                    batallon = 17;
                }

                if (cadena3.matches("[0-9]*")) {
                    compañia = Integer.parseInt(cadena3);
                } else {
                    compañia = 17;
                }
                String compañia_d = "";
                switch (compañia) {
                    case 1:
                        compañia_d = "ALFA";
                        break;
                    case 2:
                        compañia_d = "BRAVO";
                        break;
                    case 3:
                        compañia_d = "CHARLIE";
                        break;
                    case 4:
                        compañia_d = "DELTA";
                        break;
                    case 17:
                        compañia_d = cadena3;
                        break;

                    default:
                        compañia_d = "ALFA";

                }
                String grado_d = "";
                switch (grado) {
                    case 1:
                        grado_d = "MARO";
                        break;
                    case 2:
                        grado_d = "CBOS";
                        break;
                    case 3:
                        grado_d = "CBOP";
                        break;
                    case 4:
                        grado_d = "SGOS";
                        break;
                    case 5:
                        grado_d = "SGOP";
                        break;
                    case 6:
                        grado_d = "SUBS";
                        break;
                    case 7:
                        grado_d = "SUBP";
                        break;
                    case 8:
                        grado_d = "ALFG";
                        break;
                    case 9:
                        grado_d = "TNFG";
                        break;
                    case 10:
                        grado_d = "TNNV";
                        break;
                    case 11:
                        grado_d = "CPCB";
                        break;
                    case 12:
                        grado_d = "CPFG";
                        break;
                    case 13:
                        grado_d = "CPNV";
                        break;
                    case 17:
                        grado_d = cadena1;
                        break;

                    default:
                        grado_d = "MARO";

                }
                String batallon_d = "";
                switch (batallon) {
                    case 1:
                        batallon_d = "BIMLOR";
                        break;
                    case 2:
                        batallon_d = "BIMESM";
                        break;
                    case 3:
                        batallon_d = "BIMJAR";
                        break;
                    case 4:
                        batallon_d = "BIMJAM";
                        break;
                    case 5:
                        batallon_d = "BIMEDU";
                        break;
                    case 6:
                        batallon_d = "BIMUIL";
                        break;
                    case 7:
                        batallon_d = "CUINMA";
                        break;
                    case 17:
                        batallon_d = cadena2;
                        break;
                    default:
                        batallon_d = "BIMLOR";

                }
                String tipoLlamada = "";
                String detalle = "CI: " + ci + "\n" +
                        "Nombre : " + nombre1 + "\n" +
                        "Apellido: " + apellido + "\n" +
                        "Numero: " + numero + "\n" +
                        "Grado: " + grado_d + "\n" +
                        "Batallon: " + batallon_d + "\n" +
                        "Compañia: " + compañia_d + "\n\n";
                tvLlamadas.append(detalle);
            }
        add_soldados.setVisibility(View.VISIBLE);


        } }


}
