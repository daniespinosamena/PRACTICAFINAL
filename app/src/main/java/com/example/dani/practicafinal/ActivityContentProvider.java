package com.example.dani.practicafinal;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityContentProvider extends AppCompatActivity {
    //Columnas que se recuperan del content provider
     String[] projection = new String[]{
             android.provider.CallLog.Calls.NUMBER,
             android.provider.CallLog.Calls.TYPE,
             android.provider.CallLog.Calls.DURATION,
             android.provider.CallLog.Calls.DATE,
             android.provider.CallLog.Calls.CACHED_NAME
     };

    TextView mostrar; //TextView sobre el que escribiremos la lista de llamadas

     String telefono; //Contenido de la columna telefono
     String tipoLlamada;//Contenido de la columna tipo de llamada
     String fecha; //Contenido de la columna fecha
     String cached_name;//Contenido de la columna cached name
     String duracion; //Contenido de la columna duracion

     int colTelefono; //Índice de la columna telefono
     int colTipoLlamada;//Índice de la columna tipo de llamada
     int colFecha; //Índice de la columna fecha
     int colCachedName; //Índice de la columna duracion
     int colDuracion; //Índice de la columna duracion
     int claseDeLlamada;
     Button backPro;
     Button mostrarLlamadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CALL_LOG},1);
        backPro = (Button) findViewById(R.id.btnBackProv);
        mostrarLlamadas = (Button) findViewById(R.id.btnMostrarLlamadas);
        mostrar = (TextView) findViewById(R.id.tvMostrarLlamadas);

        mostrarLlamadas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Uri a la que accedemos
                Uri uriLlamadas = Uri.parse("content://call_log/calls");
                ContentResolver cr = getContentResolver();
                mostrar.setText("Lista de llamadas:\n \n");
                mostrar.setText("FECHA---HORA---TIPO----TELEFONO----DURACION");
                Cursor c = cr.query(uriLlamadas, //sobre el que hacemos la consulta
                        projection, //Columnas a devolver
                        null, //Cláusula WHERE
                        null, //Parámetros de la consulta
                        null); //Orden en el que se devolverán los datos de la consulta

                //Se recorre el cursor para obtener los valores
                if (c.moveToFirst()) {
                    colTipoLlamada = c.getColumnIndex(CallLog.Calls.TYPE);
                    colTelefono = c.getColumnIndex(CallLog.Calls.NUMBER);
                    colFecha = c.getColumnIndex(CallLog.Calls.DATE);
                    colCachedName = c.getColumnIndex(CallLog.Calls.CACHED_NAME);
                    colDuracion = c.getColumnIndex(CallLog.Calls.DURATION);

                    do {
                        claseDeLlamada = c.getInt(colTipoLlamada);
                        telefono = c.getString(colTelefono);
                        fecha = c.getString(colFecha);
                        cached_name = c.getString(colCachedName);
                        duracion = c.getString(colDuracion);
                        if (claseDeLlamada == CallLog.Calls.INCOMING_TYPE) {
                            tipoLlamada = "ENTRADA";
                        } else if (claseDeLlamada == CallLog.Calls.OUTGOING_TYPE) {
                            tipoLlamada = "SALIDA";
                        } else if (claseDeLlamada == CallLog.Calls.MISSED_TYPE) {
                            tipoLlamada = "PERDIDA";
                        }
                        mostrar.append("\n" + DateFormat.format("dd/MM/yy k:mm", Long.parseLong(fecha)) + ": " +
                                tipoLlamada + " - " + telefono + " Duración: " + duracion);
                    } while (c.moveToNext());
                }
            }
        });

        backPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityContentProvider.this, ActivityPrincipal.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
