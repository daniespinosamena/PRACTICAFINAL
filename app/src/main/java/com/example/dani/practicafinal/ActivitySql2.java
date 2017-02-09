package com.example.dani.practicafinal;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ActivitySql2 extends AppCompatActivity {

    String nombre;
    SQLiteDatabase db;
    TextView tvBD;
    UsuariosSQLiteHelper usdbh;
    Button backSQL2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql2);
        //Obtenemos los botones
        backSQL2 = (Button)findViewById(R.id.btnBackSQL2);
        Button btnConsultar = (Button) findViewById(R.id.btnConsultar);
        Button btnInsertar = (Button)findViewById(R.id.btnInsert);
        Button btnUpdate = (Button)findViewById(R.id.btnUpdate);
        Button btnDelete = (Button)findViewById(R.id.btnDelete);
        final EditText etID = (EditText) findViewById(R.id.etIDProducte);
        final EditText etTipo = (EditText) findViewById(R.id.etTipus);
        final EditText etMarca = (EditText) findViewById(R.id.etMarca);
        final EditText etNom = (EditText) findViewById(R.id.etNom);
        final EditText etPreu = (EditText) findViewById(R.id.etPreu);

        //ABRIMOS LA BBDD E INSERTAMOS 5 PRODUCTOS.
        //SI LA BBDD TIENE EL MISMO NOMBRE LA BORRAMOS.
        tvBD = (TextView) findViewById(R.id.tvBD);
        //Abrimos la base de datos 'NOMBRE' en modo escritura
        //recuperar informacion pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        //construir el nombre de la BBDD
        nombre = bundle.getString("Nombre");
        usdbh = new UsuariosSQLiteHelper(this, nombre, null, 1);
        db = usdbh.getWritableDatabase();
        Cursor busqueda = db.rawQuery(" SELECT * FROM Producte", null);
        //Si hemos abierto correctamente la base de datos
        if (db!= null) {
            db.execSQL("DROP TABLE IF EXISTS Producte");
            db.execSQL("CREATE TABLE Producte (ID INTEGER PRIMARY KEY,tipo TEXT, marca TEXT, nom TEXT, preu DOUBLE)");
            //Insertamos los datos en la tabla Usuarios
            db.execSQL("INSERT INTO Producte VALUES (10001,'movil','Apple','Iphone 5s',529.95)");
            db.execSQL("INSERT INTO Producte VALUES (10002,'movil','Apple','Iphone 6',600.00)");
            db.execSQL("INSERT INTO Producte VALUES (10003,'movil','Apple','Iphone 6s',639.95)");
            db.execSQL("INSERT INTO Producte VALUES (10004,'movil','Apple','Iphone 6S Plus',705.55)");
            db.execSQL("INSERT INTO Producte VALUES (10005,'movil','Apple','Iphone 7 Plus',909.99)");
            //Cerramos la base de datos
            db.close();
        }

        //BOTON CONSULTAR
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                db=usdbh.getReadableDatabase();
                //SI LA TABLA ESTA VACIA SE MUESTRA EL RESTO
                if(!etID.getText().toString().equals("")){
                    //BUSCAMOS POR ID
                    String[] columnas = new String[]{"ID","tipo","marca","nom","preu"};
                    String[] args = new String[]{etID.getText().toString()};
                    String res = "--ID---TIPO--MARCA-NOMBRE--PRECIO\n";
                    Cursor c;
                    c=db.query("Producte",columnas,"ID=?",args,null,null,null);
                    String id,tipus,marca,nom,preu;
                    if (c.moveToFirst()){
                        Toast.makeText(getApplicationContext(),"MOSTRANDO PRODUCTO",Toast.LENGTH_SHORT).show();
                        do {
                            id = c.getString(0);
                            tipus = c.getString(1);
                            marca = c.getString(2);
                            nom = c.getString(3);
                            preu = c.getString(4);
                            res += id + "-" + tipus + "-" + marca + "-" + nom + "-" + preu + "\n";
                        } while (c.moveToNext());
                        tvBD.setText(res);
                    }else{
                        Toast.makeText(getApplicationContext(),"PRODUCTO NO ENCONTRADO",Toast.LENGTH_SHORT).show();
                    }
                }else if(!etTipo.getText().toString().equals("")){
                    //BUSCAMOS POR TIPO
                    String[] columnas = new String[]{"ID","tipo","marca","nom","preu"};
                    String[] args = new String[]{etTipo.getText().toString()};
                    String res = "--ID---TIPO--MARCA--NOMBRE--PRECIO\n";
                    Cursor c;
                    c=db.query("Producte",columnas,"tipo=?",args,null,null,null);
                    String id,tipus,marca,nom,preu;
                    if (c.moveToFirst()){
                        Toast.makeText(getApplicationContext(),"MOSTRANDO PRODUCTO",Toast.LENGTH_SHORT).show();
                        do {
                            id = c.getString(0);
                            tipus = c.getString(1);
                            marca = c.getString(2);
                            nom = c.getString(3);
                            preu = c.getString(4);
                            res += id + "-" + tipus + "-" + marca + "-" + nom + "-" + preu + "\n";
                        } while (c.moveToNext());
                        tvBD.setText(res);
                    }else{
                        Toast.makeText(getApplicationContext(),"PRODUCTO NO ENCONTRADO",Toast.LENGTH_SHORT).show();
                    }
                }else if(!etMarca.getText().toString().equals("")){
                    //BUSCAMOS POR MARCA
                    String[] columnas = new String[]{"ID","tipo","marca","nom","preu"};
                    String[] args = new String[]{etMarca.getText().toString()};
                    String res = "--ID---TIPO--MARCA--NOMBRE--PRECIO\n";
                    Cursor c;
                    c=db.query("Producte",columnas,"marca=?",args,null,null,null);
                    String id,tipus,marca,nom,preu;
                    if (c.moveToFirst()){
                        Toast.makeText(getApplicationContext(),"MOSTRANDO PRODUCTO",Toast.LENGTH_SHORT).show();
                        do {
                            id = c.getString(0);
                            tipus = c.getString(1);
                            marca = c.getString(2);
                            nom = c.getString(3);
                            preu = c.getString(4);
                            res += id + "-" + tipus + "-" + marca + "-" + nom + "-" + preu + "\n";
                        } while (c.moveToNext());
                        tvBD.setText(res);
                    }else{
                        Toast.makeText(getApplicationContext(),"PRODUCTO NO ENCONTRADO",Toast.LENGTH_SHORT).show();
                    }
                }else if(etID.getText().toString().equals("")) {
                    String[] columnas = new String[]{"ID", "tipo", "marca", "nom", "preu"};
                    String res = "--ID---TIPO--MARCA--NOMBRE--PRECIO\n";
                    Cursor c;
                    c = db.query("Producte", columnas, null, null, null, null, null);
                    String id, tipus, marca, nom, preu;
                    if (c.moveToFirst()) {
                        do {
                            id = c.getString(0);
                            tipus = c.getString(1);
                            marca = c.getString(2);
                            nom = c.getString(3);
                            preu = c.getString(4);
                            res += id + "-" + tipus + "-" + marca + "-" + nom + "-" + preu + "\n";
                        } while (c.moveToNext());
                    }
                    tvBD.setText(res);
                    Toast.makeText(getApplicationContext(), "BBDD ABIERTA", Toast.LENGTH_SHORT).show();
                    db.close();
                }
            };
        });

        //BOTON INSERT
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!etID.getText().toString().equals("")&&
                   !etTipo.getText().toString().equals("")&&
                   !etMarca.getText().toString().equals("")&&
                   !etNom.getText().toString().equals("")&&
                   !etPreu.getText().toString().equals("") ){
                        db=usdbh.getWritableDatabase();
                        String[] columnas = new String[]{"ID","tipo","marca","nom","preu"};
                        String[] args = new String[]{etID.getText().toString()};
                        String res = "--ID---TIPO--MARCA--NOMBRE--PRECIO\n";
                        Cursor c;
                        c=db.query("Producte",columnas,"ID=?",args,null,null,null);
                        String id,tipus,marca,nom,preu;
                        if (c.moveToFirst()){
                            Toast.makeText(getApplicationContext(),"PRODUCTO EXISTENTE",Toast.LENGTH_SHORT).show();
                        }else{
                            db.execSQL("INSERT into Producte VALUES("+etID.getText().toString()+
                                                                ",'"+etTipo.getText().toString()+
                                                                "','"+etMarca.getText().toString()+
                                                                "','"+etNom.getText().toString()+
                                                                "',"+etPreu.getText().toString()+")");
                            etID.setText("");
                            etTipo.setText("");
                            etMarca.setText("");
                            etNom.setText("");
                            etPreu.setText("");
                            Toast.makeText(getApplicationContext(),"PRODUCTO INSERTADO",Toast.LENGTH_SHORT).show();
                        }
                        db.close();
                }else{Toast.makeText(getApplicationContext(),"FALTAN DATOS",Toast.LENGTH_SHORT).show();}
            }
        });

        //BOTON DELETE
        btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!etID.getText().toString().equals("")){
                    db=usdbh.getWritableDatabase();
                    String[] columnas = new String[]{"ID","tipo","marca","nom","preu"};
                    String[] args = new String[]{etID.getText().toString()};
                    String res = "--ID---TIPO--MARCA--NOMBRE--PRECIO\n";
                    Cursor c;
                    c=db.query("Producte",columnas,"ID=?",args,null,null,null);
                    String id,tipus,marca,nom,preu;
                    if (!c.moveToFirst()){
                        Toast.makeText(getApplicationContext(),"PRODUCTO INEXISTENTE",Toast.LENGTH_SHORT).show();
                    }else{
                        db.execSQL("DELETE FROM Producte WHERE ID="+etID.getText().toString());
                        Toast.makeText(getApplicationContext(),"PRODUCTO BORRADO",Toast.LENGTH_SHORT).show();
                        etID.setText("");
                        etTipo.setText("");
                        etMarca.setText("");
                        etNom.setText("");
                        etPreu.setText("");
                    }
                    db.close();
                }else{Toast.makeText(getApplicationContext(),"INTRODUCE ID",Toast.LENGTH_SHORT).show();}
            }
        });


        //BOTON UPDATE
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!etID.getText().toString().equals("")&&
                        (!etTipo.getText().toString().equals("")||
                        !etMarca.getText().toString().equals("")||
                        !etNom.getText().toString().equals("")||
                        !etPreu.getText().toString().equals("") )){
                    db=usdbh.getWritableDatabase();
                    String[] columnas = new String[]{"ID","tipo","marca","nom","preu"};
                    String[] args = new String[]{etID.getText().toString()};
                    String res = "--ID---TIPO--MARCA--NOMBRE--PRECIO\n";
                    Cursor c;
                    c=db.query("Producte",columnas,"ID=?",args,null,null,null);
                    String id,tipus,marca,nom,preu;
                    if (c.moveToFirst()){
                        tipus=c.getString(1);
                        marca=c.getString(2);
                        nom=c.getString(3);
                        preu=c.getString(4);
                        if(!etTipo.getText().toString().equals("")){
                            db.execSQL("UPDATE Producte SET tipo='"+etTipo.getText().toString()+"' WHERE ID = "+etID.getText().toString());
                        }
                        if(!etMarca.getText().toString().equals("")){
                            db.execSQL("UPDATE Producte SET marca='"+etMarca.getText().toString()+"' WHERE ID = "+etID.getText().toString());
                        }
                        if(!etNom.getText().toString().equals("")){
                            db.execSQL("UPDATE Producte SET nom='"+etNom.getText().toString()+"' WHERE ID = "+etID.getText().toString());
                        }
                        if(!etPreu.getText().toString().equals("")){
                            db.execSQL("UPDATE Producte SET preu="+etPreu.getText().toString()+" WHERE ID = "+etID.getText().toString());
                        }
                        etID.setText("");
                        etTipo.setText("");
                        etMarca.setText("");
                        etNom.setText("");
                        etPreu.setText("");
                        Toast.makeText(getApplicationContext(),"PRODUCTO ACTUALIZADO",Toast.LENGTH_SHORT).show();
                    }else{Toast.makeText(getApplicationContext(),"PRODUCTO NO ECONTRADO",Toast.LENGTH_SHORT).show();}
                    db.close();
                }else{Toast.makeText(getApplicationContext(),"FALTAN DATOS",Toast.LENGTH_SHORT).show();}
            }
        });

        backSQL2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(ActivitySql2.this,ActivitySql1.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

