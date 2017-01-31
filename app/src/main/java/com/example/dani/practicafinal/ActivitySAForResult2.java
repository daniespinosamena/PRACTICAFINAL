package com.example.dani.practicafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivitySAForResult2 extends AppCompatActivity {

    EditText texto;
    Button ok;
    Button back;
    Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safor_result2);
        texto = (EditText)findViewById(R.id.etSendSAFR1);
        ok = (Button)findViewById(R.id.btnSAFR2OK);
        back = (Button)findViewById(R.id.btnBackAFRS2);
        cancelar = (Button)findViewById(R.id.btnSAFR2cancel);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultado = "";
                if (texto.getText().length() != 0) {
                    resultado = texto.getText().toString();
                    Intent i = getIntent();
                    i.putExtra("RESULTADO", resultado);
                    setResult(RESULT_OK, i);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"INTRODUCE TEXTO",Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Si se pulsa el botón, establecemos el resultado como cancelado.
                // Al igual que con "RESULT_OK", esta variable es de la activity.
                setResult(RESULT_CANCELED);
                // Finalizamos la Activity para volver a la anterior
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i = new Intent(ActivitySAForResult2.this, ActivitySAForResult1.class);
                startActivity(i);
                finish();
            }
        });
    }
}
