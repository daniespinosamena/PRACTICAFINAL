package com.example.dani.practicafinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class ActivityCalculadora extends AppCompatActivity {


    private TextView pantallaResult;
    private double dig1 = -1, dig2 = -1;
    private double resultado = 0;
    private String oper = "";
    private String op;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        pantallaResult = (TextView) findViewById(R.id.Pantalla);
        Button btnSalir = (Button) findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ActivityCalculadora.this, ActivityPrincipal.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void clickBotonNum(View v) {
        Button botonPulsado = (Button) findViewById(v.getId());
        pantallaResult.setText(pantallaResult.getText() + botonPulsado.getText().toString());
    }

    public void clickOperador(View v) {

        if (pantallaResult.getText() != "") {
            if (dig1 == -1) {
                dig1 = Long.parseLong(pantallaResult.getText().toString());
                pantallaResult.setText("");
            }
        }else {
            if (dig1 == -1) {
                dig1 = 0;
            }
        }

        Button botonPulsado = (Button) findViewById(v.getId());
        op = botonPulsado.getText().toString();

    }

    public void clickIgual(View v) {

        if (pantallaResult.getText() != "") {
            if (dig1 != -1) {
                dig2 = Long.parseLong(pantallaResult.getText().toString());
                pantallaResult.setText(String.valueOf(dig1+dig2));
            }
        }
        switch (op) {
            case "+" : {
                resultado = dig1 + dig2;
                break;
            }
            case "-" : {
                resultado = dig1 - dig2;
                break;
            }
            case "/" : {
                if (dig1 > 0 && dig2 == 0) {
                    resultado = 0;
                } else {
                    resultado = (double) dig1 / dig2;
                }
                break;
            }
            case "X" : {
                resultado = dig1 * dig2;
                break;
            }
        }

        dig1 = -1;
        dig2 = -1;
        pantallaResult.setText(String.valueOf(resultado));
    }

    public void clickClean(View v) {
        dig1 = -1;
        dig2 = -1;
        pantallaResult.setText("");
    }

    public void guardar(View v) {
        SharedPreferences prefs = getSharedPreferences("PreferenciasCalculadora", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("0",String.valueOf(resultado));
        editor.commit();
    }

    public void recuperar(View v){
        SharedPreferences prefs =
                getSharedPreferences("PreferenciasCalculadora", Context.MODE_PRIVATE);

        String resultatAnterior = prefs.getString("0", "0");
        pantallaResult.setText(String.valueOf(resultatAnterior));
    };
}
