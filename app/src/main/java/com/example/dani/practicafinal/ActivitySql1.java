package com.example.dani.practicafinal;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivitySql1 extends AppCompatActivity {

    EditText etBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql1);
        Button backSQL1 = (Button)findViewById(R.id.btnBackSQL1);
        Button btnCrear = (Button)findViewById(R.id.btnCrearBD);
        etBase = (EditText)findViewById(R.id.etBaseDeDatos);

        btnCrear.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(!etBase.getText().toString().equals("")) {
                    Intent intent = new Intent(ActivitySql1.this, ActivitySql2.class);
                    //informacion a pasar
                    Bundle b = new Bundle();
                    b.putString("Nombre", etBase.getText().toString());
                    //meter la informacion en el intent mediante el bundle
                    intent.putExtras(b);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"INTRODUCE UN NOMBRE",Toast.LENGTH_SHORT).show();
                }
            }
        });
        backSQL1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivitySql1.this,ActivityPrincipal.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
