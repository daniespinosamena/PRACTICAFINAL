package com.example.dani.practicafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivitySAForResult1 extends AppCompatActivity {

    Button btnMarca;
    Button btnTipo;
    Button backAFR1;
    EditText etMarca;
    EditText etTipo;
    private final static int MARCA = 0;
    private final static int TIPO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safor_result1);
        btnMarca = (Button) findViewById(R.id.btnMarca);
        btnTipo = (Button) findViewById(R.id.btnTipo);
        backAFR1 = (Button) findViewById(R.id.btnBackAFR1);
        etMarca = (EditText)findViewById(R.id.etMarca);
        etTipo = (EditText)findViewById(R.id.etTipo);
        btnMarca.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(ActivitySAForResult1.this, ActivitySAForResult2.class);
                startActivityForResult(i, MARCA);
            }
        });

        btnTipo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(ActivitySAForResult1.this, ActivitySAForResult2.class);
                startActivityForResult(i, TIPO);
            }
        });

        backAFR1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(ActivitySAForResult1.this, ActivityPrincipal.class);
                startActivity(i);
                finish();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            // Si es así mostramos mensaje de cancelado por pantalla.
            Toast.makeText(this, "RESULTADO CANCELADO", Toast.LENGTH_SHORT)
                    .show();
        } else {
            // De lo contrario, recogemos el resultado de la segunda actividad.
            String resultado = data.getExtras().getString("RESULTADO");
            // Y tratamos el resultado en función de si se lanzó para rellenar el
            // nombre o el apellido.
            switch (requestCode) {
                case MARCA:
                    etMarca.setText(resultado);
                    break;
                case TIPO:
                    etTipo.setText(resultado);
                    break;
            }
        }
    }
}
