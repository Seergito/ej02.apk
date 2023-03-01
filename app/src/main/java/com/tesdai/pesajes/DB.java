package com.tesdai.pesajes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class DB extends SQLiteOpenHelper {

    Context CONTEXTO;
    final static String nombre ="PESO";
    final static int VERSION=1;

    SQLiteDatabase db=null;

    public DB(@Nullable Context CONTEXTO) {
        super(CONTEXTO, nombre, null, VERSION);
        this.CONTEXTO=CONTEXTO;
    }

    public void abreDB(){
        if(db==null){
            db=this.getReadableDatabase();
        }
    }

    public void cerrarDB(){
        if(db!=null){
            db.close();
            db=null;
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TABLA_PESO (id INTEGER PRIMARY KEY AUTOINCREMENT,`peso` INT NOT NULL ,`fecha` DATE NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertar(Peso p){
        abreDB();
        ContentValues c=new ContentValues();

        int kg=p.getPeso();
        String f=p.getFecha();

        c.put("peso",kg);
        c.put("fecha",f);

        long rowid=db.insert("TABLA_PESO",null,c);

        if(rowid==-1){
            Toast.makeText(CONTEXTO, "Error en la inserción", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(CONTEXTO, "Valor insertado con éxito", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor Mostrar(){
        abreDB();
        Cursor c=db.rawQuery("SELECT id,peso,fecha FROM TABLA_PESO",null);
        return c;
    }

}
