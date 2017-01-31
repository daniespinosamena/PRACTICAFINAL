package com.example.dani.practicafinal;

import android.content.Intent;
import android.os.Process;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityPrincipal extends AppCompatActivity {

    Button btnIntent;
    Button btnShared;
    Button btnSqlite;
    Button btnFicheros;
    Button btnSAForResult;
    Button btnContent;
    Button btnBroadcast;
    Button btnSms;
    Button btnclose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Obtener las referencias de los botones;
        btnIntent = (Button)findViewById(R.id.btnIntent);
        btnShared = (Button)findViewById(R.id.btnShared);
        btnSqlite = (Button)findViewById(R.id.btnSqlite);
        btnFicheros = (Button)findViewById(R.id.btnFicheros);
        btnSAForResult = (Button)findViewById(R.id.btnStarActivity);
        btnContent = (Button)findViewById(R.id.btnContent);
        btnBroadcast=(Button)findViewById(R.id.btnBroadcast);
        btnSms=(Button)findViewById(R.id.btnSms);
        btnclose=(Button)findViewById(R.id.btnClose);

        //crear el intent implementando el onClick
        btnIntent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivityPrincipal.this,ActivityIntent1.class);
                startActivity(intent);
            }
        });
        btnShared.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivityPrincipal.this,ActivityCalculadora.class);
                startActivity(intent);
            }
        });
        btnSqlite.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivityPrincipal.this,ActivitySql1.class);
                startActivity(intent);
            }
        });
        btnFicheros.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivityPrincipal.this,ActivityFitxer.class);
                startActivity(intent);
            }
        });
        btnSAForResult.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivityPrincipal.this,ActivitySAForResult1.class);
                startActivity(intent);
            }
        });
        btnContent.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivityPrincipal.this,ActivityContentProvider.class);
                startActivity(intent);
            }
        });
        btnBroadcast.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivityPrincipal.this,ActivityBroadcastReceiver.class);
                startActivity(intent);
            }
        });
        btnSms.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivityPrincipal.this,ActivitySms.class);
                startActivity(intent);
            }
        });
        btnclose.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finishAffinity();
            }
        });
    }
}
