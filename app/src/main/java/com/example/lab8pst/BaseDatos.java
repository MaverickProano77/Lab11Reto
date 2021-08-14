package com.example.lab8pst;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BaseDatos extends AppCompatActivity {
    private EditText txtCodigo, txtDescripcion, txtPrecio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_datos);
        txtCodigo=(EditText)findViewById(R.id.edtcodigo);
        txtDescripcion=(EditText)findViewById(R.id.edtdescripcion);
        txtPrecio=(EditText)findViewById(R.id.edtprecio);
    }
    public void ingresar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = txtCodigo.getText().toString();
        String descri = txtDescripcion.getText().toString();
        String pre = txtPrecio.getText().toString();
        bd.execSQL("insert into articulos (codigo,descripcion,precio) values ('"+cod+"','"+descri+"','"+pre+"')");
        bd.close();
        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        Toast.makeText(this, "Se cargaron los datos del artículo",
                Toast.LENGTH_SHORT).show();
    }

    public void consultaporcodigo(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();
        String cod = txtCodigo.getText().toString();
        Cursor fila = bd.rawQuery(
                "select descripcion,precio from articulos where codigo=" + cod, null);
        if (fila.moveToFirst()) {
            txtDescripcion.setText(fila.getString(0));
            txtPrecio.setText(fila.getString(1));
            Toast.makeText(this, "Consulta exitosa",
                    Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "No existe un artículo con dicho código",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void consultapordescripcion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();
        String descri = txtDescripcion.getText().toString();
        Cursor fila = bd.rawQuery(
                "select codigo,precio from articulos where descripcion like '" + descri +"'", null);
        if (fila.moveToFirst()) {
            txtCodigo.setText(fila.getString(0));
            txtPrecio.setText(fila.getString(1));
            Toast.makeText(this, "Consulta exitosa",
                    Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "No existe un artículo con dicha descripción",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void eliminar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod= txtCodigo.getText().toString();
        bd.execSQL("delete from articulos where codigo = "+cod);
        bd.close();
        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        Toast.makeText(this, "Se borró el artículo con dicho código",
                Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = txtCodigo.getText().toString();
        String descri = txtDescripcion.getText().toString();
        String pre = txtPrecio.getText().toString();
        bd.execSQL("update articulos set codigo="+cod+",descripcion='"+descri+"',precio="+pre+" where codigo="+cod);
        bd.close();
        Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                .show();
    }
}