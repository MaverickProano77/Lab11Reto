package com.example.lab8pst;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
    }
    public void Link(View v){ //Llamar pagina web que ingresa el usuario
        EditText url = (EditText)findViewById(R.id.edtUrl);
        String link = url.getText().toString();
        WebView web = (WebView) findViewById(R.id.web);
        WebSettings opciones = web.getSettings();
        opciones.setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("https://"+link); //asumir que esta, aun no validaciones
    }
    public void Regresar(View v){
        finish();
    } // termina
}