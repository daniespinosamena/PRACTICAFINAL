package com.example.dani.practicafinal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ActivityFitxersInt extends AppCompatActivity {

    EditText archivo;
    EditText contenido;
    Button leerInt;
    Button btnGuardar;
    Button backFitxInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitxers_int);
        btnGuardar = (Button)findViewById(R.id.btnGuardarInt);
        archivo = (EditText)findViewById(R.id.etNomFitxInt);
        contenido = (EditText)findViewById(R.id.etContFitxInt);
        leerInt = (Button)findViewById(R.id.btnLeerInt);
        backFitxInt = (Button)findViewById(R.id.btnBackFitxInt);
        btnGuardar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try
                {
                    OutputStreamWriter fout=
                            new OutputStreamWriter(
                                    openFileOutput(archivo.getText().toString(), Context.MODE_PRIVATE));

                    fout.write(contenido.getText().toString());
                    fout.close();
                    File data = getFilesDir();
                    Log.i(archivo.getText().toString(), data.getAbsolutePath());
                    Toast.makeText(getApplicationContext(),"ARCHIVO CREADO EN "+data,Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al escribir fichero a memoria interna");
                }
            }
        });

        leerInt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try
                {
                    BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput(archivo.getText().toString())));
                    String texto = fin.readLine();
                    fin.close();
                    contenido.setText(texto);
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al leer fichero desde memoria interna");
                }
            }
        });
        backFitxInt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivityFitxersInt.this,ActivityFitxer.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
