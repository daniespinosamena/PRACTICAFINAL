package com.example.dani.practicafinal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityBroadcastReceiver extends AppCompatActivity {

    Button backBroadcast;
    TextView mostrarBateria;
    BroadcastReceiver broadcast1=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);
        backBroadcast = (Button)findViewById(R.id.btnBackBroadc);
        mostrarBateria = (TextView)findViewById(R.id.tvMostrarLlamadas);
        backBroadcast.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivityBroadcastReceiver.this,ActivityPrincipal.class);
                startActivity(intent);
                finish();
            }
        });

        IntentFilter batteryIntentFilter1 = new IntentFilter((Intent.ACTION_BATTERY_CHANGED));
        IntentFilter batteryIntentFilter2 = new IntentFilter((Intent.ACTION_BATTERY_OKAY));
        IntentFilter batteryIntentFilter3 = new IntentFilter((Intent.ACTION_POWER_CONNECTED));
        IntentFilter batteryIntentFilter4 = new IntentFilter((Intent.ACTION_POWER_DISCONNECTED));
        IntentFilter batteryIntentFilter5 = new IntentFilter((Intent.ACTION_BATTERY_LOW));

        broadcast1 = new BroadcastReceiver(){
            public void onReceive(Context context, Intent intent){
                String action = intent.getAction();
                String strAction;

                if(action==Intent.ACTION_BATTERY_LOW){
                    strAction = "ACTION_BATTERY_LOW";
                    Toast.makeText(getApplicationContext(), "BATTERY_LOW", Toast.LENGTH_SHORT).show();
                }else if(action==Intent.ACTION_BATTERY_OKAY){
                    strAction = "ACTION_BATTERY_OKAY";
                    Toast.makeText(getApplicationContext(), "BATTERY_OKAY", Toast.LENGTH_SHORT).show();
                }else if(action==Intent.ACTION_POWER_CONNECTED){
                    strAction = "ACTION_POWER_CONNECTED";
                    Toast.makeText(getApplicationContext(), "POWER_CONNECTED", Toast.LENGTH_SHORT).show();
                }else if(action==Intent.ACTION_POWER_DISCONNECTED){
                    strAction = "ACTION_POWER_DISCONNECTED";
                    Toast.makeText(getApplicationContext(), "POWER_DISCONNECTED", Toast.LENGTH_SHORT).show();
                }else{
                    strAction="unknow";
                }
                Log.i("Bateria",strAction);
            }
        };

        registerReceiver(broadcast1,batteryIntentFilter1);
        registerReceiver(broadcast1,batteryIntentFilter2);
        registerReceiver(broadcast1,batteryIntentFilter3);
        registerReceiver(broadcast1,batteryIntentFilter4);
        registerReceiver(broadcast1,batteryIntentFilter5);
    };
}
