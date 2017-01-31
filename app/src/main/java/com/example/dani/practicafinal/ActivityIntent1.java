package com.example.dani.practicafinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityIntent1 extends AppCompatActivity {

    Button btnEnviarNum;
    Button backInt1;
    EditText etTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent1);
        btnEnviarNum = (Button)findViewById(R.id.btnEnviarNum);
        etTel = (EditText)findViewById(R.id.etTel);
        backInt1 = (Button)findViewById(R.id.btnBackInt1);
        //crear el intent implementando el onClick
        btnEnviarNum.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivityIntent1.this,ActivityIntent2.class);

                //informacion a pasar
                Bundle b = new Bundle();
                b.putString("Telefono", etTel.getText().toString());

                //meter la informacion en el intent mediante el bundle
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        backInt1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivityIntent1.this,ActivityPrincipal.class);
                startActivity(intent);
                }
        });
    }
}
