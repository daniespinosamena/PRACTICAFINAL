package com.example.dani.practicafinal;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ActivityFitxersSD extends AppCompatActivity {

    EditText nomFitxerSD;
    EditText contingutSD;
    Button leerSD;
    Button guardarSD;
    Button backSD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitxers_sd);
        nomFitxerSD = (EditText) findViewById(R.id.etNomFitxerSD);
        contingutSD = (EditText) findViewById(R.id.etContSD);
        leerSD = (Button) findViewById(R.id.btnLeerSD);
        guardarSD = (Button) findViewById(R.id.btnGuardarSD);
        backSD=(Button)findViewById(R.id.btnBackSD);
        boolean sdDisponible = false;
        boolean sdAccesoEscritura = false;
        //Comprobamos el estado de la memoria externa (tarjeta SD)
        String estado = Environment.getExternalStorageState();
        if (estado.equals(Environment.MEDIA_MOUNTED)) {
            sdDisponible = true;
            sdAccesoEscritura = true;
            Toast.makeText(getApplicationContext(), "DISPONIBLE ESCRITURA", Toast.LENGTH_SHORT).show();
        } else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            sdDisponible = true;
            sdAccesoEscritura = false;
            Toast.makeText(getApplicationContext(), "SOLO LECTURA", Toast.LENGTH_SHORT).show();
        } else {
            sdDisponible = false;
            sdAccesoEscritura = false;
            Toast.makeText(getApplicationContext(), "PERMISO DENEGADO", Toast.LENGTH_SHORT).show();
        }

        guardarSD.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //cogemos la ruta, abrimos el fichero y ESCRIBIMOS
                OutputStreamWriter osw;
                String ruta;
                try
                {
                    ruta = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
                    Toast.makeText(getApplicationContext(),"ARCHIVO CREADO EN "+ruta,Toast.LENGTH_SHORT).show();
                    File f = new File(ruta,nomFitxerSD.getText().toString());
                    osw =new OutputStreamWriter(new FileOutputStream(f));
                    osw.write(contingutSD.getText().toString()+"\n");
                    osw.flush();
                    osw.close();
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
                }
            }
        });

        //cogemos la ruta, abrimos el fichero y LEEMOS
        leerSD.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try
                {
                    File ruta_sd = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                    File f = new File(ruta_sd.getAbsolutePath(), nomFitxerSD.getText().toString());
                    BufferedReader fin =new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                    String texto = fin.readLine();
                    fin.close();
                    contingutSD.setText(texto);
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al leer fichero desde tarjeta SD");
                }
            }
        });
        backSD.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivityFitxersSD.this,ActivityFitxer.class);
                startActivity(intent);
                finish();
            }
        });
    }
}