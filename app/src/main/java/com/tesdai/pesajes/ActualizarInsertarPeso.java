package com.tesdai.pesajes;
// Segunda Activity para Actualizar, Insertar un peso
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;


public class ActualizarInsertarPeso extends AppCompatActivity {

	Button bAceptar ;
	TextView tv;
	EditText etkg;

	DB db;
	long id=0;  // Guardara el rowid de la fila

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actualizar_insertar_peso);
		tv			= findViewById(R.id.tvTitulo);
		etkg		= findViewById(R.id.etPeso);
		bAceptar	= findViewById(R.id.bAceptar);
		db=new DB(ActualizarInsertarPeso.this);
		Intent i=getIntent();
		String fecha=i.getStringExtra("fecha");
		Toast.makeText(this, fecha, Toast.LENGTH_SHORT).show();
		bAceptar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				int kg=Integer.valueOf(etkg.getText().toString());
				Peso p =new Peso(kg,fecha);
				db.insertar(p);
				setResult(RESULT_OK);
				finish();
			}
		});
		

	}

	


}
