package com.example.dani.practicafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ActivityFitxersRaw extends AppCompatActivity {

    Button btnLeerRAW;
    TextView tvContRAW;
    Button backRAW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitxers_raw);
        btnLeerRAW = (Button)findViewById(R.id.btnLeerRAW);
        tvContRAW = (TextView) findViewById(R.id.tvContRAW);
        backRAW=(Button)findViewById(R.id.btnBackRAW);
        btnLeerRAW.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                try {
                    InputStream fraw =
                            getResources().openRawResource(R.raw.rawfile);

                    BufferedReader brin =
                            new BufferedReader(new InputStreamReader(fraw));
                    String linea = brin.readLine();
                    String x = "";
                    //bucle para recorrer
                    while (linea != null) {
                        x = x + linea;
                        linea = brin.readLine();
                    }
                    tvContRAW.setText(x);
                    fraw.close();
                } catch (Exception ex) {
                    Log.e("Ficheros", "Error al leer fichero desde recurso raw");
                }
            }
        });
        backRAW.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivityFitxersRaw.this,ActivityFitxer.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
