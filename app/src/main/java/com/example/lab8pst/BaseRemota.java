package com.example.lab8pst;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

public class BaseRemota extends AppCompatActivity {
    private TextView queryTxt;
    private EditText queryEdt;
    private EditText tabla;
    private String servidor = "192.168.100.75";
    private String ruta = "/android";
    private int puerto = 8080;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_remota);
        queryTxt=(TextView)findViewById(R.id.txtQuery);
        queryEdt=(EditText)findViewById(R.id.edtQuery);
        tabla=(EditText)findViewById(R.id.edtTabla);
    }
    public void Conectar(View v){
        String resultado = null;
        try {
            String url = "http://"+servidor+":"+puerto+ruta;
            AsyncQuery query = new AsyncQuery();
            resultado = query.execute(url).get();
            if (resultado != null){
                queryTxt.setText(resultado);
            }else{
                queryTxt.setText("Error en la consulta");
            }
        }catch (ExecutionException ex){
            ex.printStackTrace();
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }

    public void consultar(View view){
        String resultado = null;
        String datos = null;
        /* Paso 4
        try {
            String datos = "http://"+servidor+":"+puerto+ruta+'/'+queryEdt.getText().toString();
            AsyncQuery async = new AsyncQuery();
            resultado = async.execute(datos).get();
            queryTxt.setText(resultado);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //RETO
        try {
            if(queryEdt.getText().toString().equals("")){
                datos = "http://"+servidor+":"+puerto+ruta+"/Select * From "+tabla.getText().toString();
            }
            else{
                datos = "http://"+servidor+":"+puerto+ruta+"/Select * From "+tabla.getText().toString()
                        + " Where "+queryEdt.getText().toString();
            }
            AsyncQuery async = new AsyncQuery();
            resultado = async.execute(datos).get();
            queryTxt.setText(resultado);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}