package com.example.lab8pst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SegundoActivity extends AppCompatActivity {

    TextView txtNombres, txtApellidos, txtCelular, txtMail, txtCC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);

        txtNombres = (TextView)findViewById(R.id.txtNombres);
        txtApellidos = (TextView)findViewById(R.id.txtApellidos);
        txtCelular = (TextView)findViewById(R.id.txtCelular);
        txtMail = (TextView)findViewById(R.id.txtMail);
        txtCC = (TextView)findViewById(R.id.txtCC);

        String nombres = getIntent().getStringExtra("nombres");
        String apellidos = getIntent().getStringExtra("apellidos");
        String telefono = getIntent().getStringExtra("celular");
        String mail = getIntent().getStringExtra("correo");

        //Asignación de información ingresada por el cliente hacia los respectivos txt
        txtNombres.setText("Nombres: "+nombres);
        txtApellidos.setText("Apellidos: "+apellidos);
        txtCelular.setText("Telefono: "+telefono);
        txtMail.setText("Correo: "+mail);
        txtCC.setText("CC: steanleo@fiec.espol.edu.ec");

    }

    public void regresar (View v){
        Intent regresar = new Intent(this, MainActivity.class);
        startActivity(regresar);
    }

}