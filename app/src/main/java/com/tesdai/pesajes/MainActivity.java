package com.tesdai.pesajes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    SQLiteDatabase baseDatos; // Base de datos
    SimpleCursorAdapter adaptador; //Adaptador para el ListView
    DB db;

    final static int PETICION_INSERT=1;
    final static int PETICION_ACTUALIZAR=2;

    // Obtiene la fecha y hora actuales en formato base de datos
    private String getStringFechaHoraActual() {
        GregorianCalendar calendarioHoy= new GregorianCalendar();
        Date fechaHoraActual = calendarioHoy.getTime();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());
        return formateador.format(fechaHoraActual);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.listViewPesos);
        registerForContextMenu(lv);
        db=new DB(MainActivity.this);
        db.abreDB();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  // Menu principal
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override // Click menu principal (Insertar)
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mp_insertarPeso:
                Intent intent=new Intent(this,ActualizarInsertarPeso.class);
                intent.putExtra("fecha",getStringFechaHoraActual());
                startActivityForResult(intent,PETICION_INSERT);
                break;

        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v==lv) {
            getMenuInflater().inflate(R.menu.menu_contextual_listview, menu);
        }
    }

    @Override // Menu contextual ListView (Actualizar y borrar)
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo infoFilaListView= (AdapterContextMenuInfo) item.getMenuInfo();
        long rowid = infoFilaListView.id;

        switch (item.getItemId()) {
            case R.id.mc_actualizarPeso :
                // Completar
                break;
            case R.id.mc_borrarPeso :
                // Completar
                break;
        }

        return super.onContextItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PETICION_INSERT && resultCode==RESULT_OK){
            Toast.makeText(this, "DATOS INSERTADOS EXITOSAMENTE", Toast.LENGTH_SHORT).show();
            Cursor c=db.Mostrar();
            String cols[]={"peso","fecha"};
            int controles[]={R.id.tvPeso,R.id.tvFecha};
            SimpleCursorAdapter sc=new SimpleCursorAdapter(MainActivity.this,R.layout.itemlistview,c,cols,controles,0);
            lv.setAdapter(sc);

        }else{Toast.makeText(this, "DATOS NO INSERTADOS", Toast.LENGTH_SHORT).show();}
        if(requestCode==PETICION_ACTUALIZAR && resultCode==RESULT_OK){
            Toast.makeText(this, "DATOS ACTUALIZADOS EXITOSAMENTE", Toast.LENGTH_SHORT).show();
        }else{Toast.makeText(this, "DATOS NO ACTUALIZADOS", Toast.LENGTH_SHORT).show();}
    }
}
