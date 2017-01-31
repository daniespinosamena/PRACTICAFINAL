package com.example.dani.practicafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityFitxer extends AppCompatActivity {

    Button btnInterna;
    Button btnExterna;
    Button btnRAW;
    Button backFitx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitxer);
        btnInterna = (Button)findViewById(R.id.btnInterna);
        btnExterna = (Button)findViewById(R.id.btnExterna);
        btnRAW = (Button)findViewById(R.id.btnRaw);
        backFitx = (Button)findViewById(R.id.btnBackFitxers);
        btnInterna.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivityFitxer.this,ActivityFitxersInt.class);
                startActivity(intent);
            }
        });
        btnExterna.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivityFitxer.this,ActivityFitxersSD.class);
                startActivity(intent);
            }
        });
        btnRAW.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivityFitxer.this,ActivityFitxersRaw.class);
                startActivity(intent);
            }
        });
        backFitx.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivityFitxer.this,ActivityPrincipal.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
