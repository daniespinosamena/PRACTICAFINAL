package com.example.dani.practicafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityIntent2 extends AppCompatActivity {

    TextView texto;
    String telefono,cadena;
    Button btnMenu;
    Button backInt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent2);

        //localizamos los controles
        texto = (TextView)findViewById(R.id.tvNumRes);
        btnMenu = (Button)findViewById(R.id.btnMenu);
        backInt2 = (Button)findViewById(R.id.btnBackInt2);
        //recuperar informacion pasada en el intent
        Bundle bundle = this.getIntent().getExtras();

        //construir el mensaje a mostrar
        cadena = texto.getText().toString();
        telefono = bundle.getString("Telefono");
        texto.setText(cadena+ telefono);

        //crear el intent implementando el onClick
        btnMenu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivityIntent2.this,ActivityPrincipal.class);
                startActivity(intent);
            }
        });
        backInt2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivityIntent2.this,ActivityIntent1.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
