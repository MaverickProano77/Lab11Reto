package com.example.lab8pst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BienvenidoActivity extends AppCompatActivity {

    TextView txtBienvenido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenido);

        String usuario = getIntent().getStringExtra("usuario");

        txtBienvenido = (TextView)findViewById(R.id.txtBienvenido);
        txtBienvenido.setText("Bienvenido " + usuario);
    }

    public void MenuPrincipal(View v){
        Intent intent = new Intent(this, InicioActivity.class);
        startActivity(intent);
    }
}