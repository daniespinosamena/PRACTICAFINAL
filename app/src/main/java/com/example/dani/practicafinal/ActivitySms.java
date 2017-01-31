package com.example.dani.practicafinal;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivitySms extends AppCompatActivity {

    Button enviar,backSms;
    EditText numsms;
    EditText mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
        enviar = (Button)findViewById(R.id.btnEnviar);
        backSms= (Button)findViewById(R.id.btnBackSMS);
        numsms = (EditText)findViewById(R.id.etNumero);
        mensaje=(EditText)findViewById(R.id.etMensajeEnviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                    if(mensaje.getText().toString().length()<50) {
                        SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage(numsms.getText().toString(), null, mensaje.getText().toString(), null, null);
                    }else{
                        Toast.makeText(getApplicationContext(),"Mensaje muy largo, solo 50 caracteres",Toast.LENGTH_SHORT).show();
                    }
            }
        });
        backSms.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivitySms.this,ActivityPrincipal.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
