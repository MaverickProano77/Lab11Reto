package com.example.lab8pst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InicioSesion extends AppCompatActivity {

    EditText usuario, contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        usuario = (EditText)findViewById(R.id.edtUsuario);
        contrasena = (EditText)findViewById(R.id.edtContrasena);
    }
    public void ingresarDatos (View v){
        Intent dtUser = new Intent(this, BienvenidoActivity.class);
        dtUser.putExtra("usuario",usuario.getText().toString());
        dtUser.putExtra("contrasena",contrasena.getText().toString());
        startActivity(dtUser);
        usuario.setText("");
        contrasena.setText("");
    }

    public void RegresarN(View v){
        finish();
    }
}