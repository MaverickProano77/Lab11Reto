package com.example.lab8pst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }
    public void Iniciar(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void Buscar(View v){
        Intent intent = new Intent(this, WebViewActivity.class);
        startActivity(intent);
    }
    public void IniciarSesion(View v){
        Intent intent = new Intent(this, InicioSesion.class);
        startActivity(intent);
    }
    public void AcercaDe(View v){
        Intent intent = new Intent(this, AcercaDe.class);
        startActivity(intent);
    }
    public void Gmail(View v){
        Intent intent = new Intent(this, RevisarCorreo.class);
        startActivity(intent);
    }
    public void DB(View v){
        Intent intent = new Intent(this, BaseDatos.class);
        startActivity(intent);
    }
    public void DBRemota(View v){
        Intent intent = new Intent(this, BaseRemota.class);
        startActivity(intent);
    }
}