package com.example.lab8pst;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MainActivity extends AppCompatActivity {

    EditText etnombres, etapellidos, etphone, etmail;
    Intent emailIntent = new Intent(Intent.ACTION_SEND);
    Session session;
    String correo;
    String contrasena;
    String informacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etnombres = (EditText)findViewById(R.id.editTextNombres);
        etapellidos = (EditText)findViewById(R.id.editTextApellidos);
        etphone = (EditText)findViewById(R.id.editTextPhone);
        etmail = (EditText)findViewById(R.id.editTextMail);
        correo = "maverickpro1802@gmail.com";
        contrasena = "dragonMav";
    }

    public void enviar (View v) {
        Intent enviar = new Intent(this, SegundoActivity.class);
        enviar.putExtra("nombres", etnombres.getText().toString());
        enviar.putExtra("apellidos", etapellidos.getText().toString());
        enviar.putExtra("celular", etphone.getText().toString());
        enviar.putExtra("correo", etmail.getText().toString());
        startActivity(enviar);

        //Correo con App Externa
        String[] to = {"jmproano@espol.edu.ec"};
        String[] cc = {"steanleo@fiec.espol.edu.ec"};
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_CC, cc);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Formulario de Registro PST");


        emailIntent.putExtra(Intent.EXTRA_TEXT, "Datos de Contacto\n" +
                "Nombres:" + etnombres.getText().toString() + "\n" +
                "Apellidos:" + etapellidos.getText().toString() + "\n" +
                "Teléfono:" + etphone.getText().toString() + "\n" +
                "Correo Electrónico:" + etmail.getText().toString() + "\n");

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviando Email"));
            Log.i("termina envio de email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "No existe cliente Email instalado.", Toast.LENGTH_SHORT).show();
        }
    }
    //Correo sin App Externa (Interno)
    public void enviarInterno (View v){
        Intent enviarInterno = new Intent (this, SegundoActivity.class);
        enviarInterno.putExtra("nombres",etnombres.getText().toString());
        enviarInterno.putExtra("apellidos",etapellidos.getText().toString());
        enviarInterno.putExtra("celular",etphone.getText().toString());
        enviarInterno.putExtra("correo",etmail.getText().toString());
        startActivity(enviarInterno);

        informacion = "Datos de Contacto" + "<br>" +
                      "Nombres: " + etnombres.getText().toString()+ "<br>" +
                      "Apellidos: " + etapellidos.getText().toString() + "<br>" +
                      "Teléfono: " + etphone.getText().toString() + "<br>" +
                      "Correo Electrónico: " + etmail.getText().toString()+ "<br>";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.googlemail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        try {
            session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(correo, contrasena);
                }
            });
            if (session!=null) {
                javax.mail.Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(correo));
                message.setSubject("Formulario de Registro PST"); //Asunto
                message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse("jmproano@espol.edu.ec,steanleo@fiec.espol.edu.ec")); //Destino (Mi correo y el cc al profesort)
                message.setContent(informacion, "text/html; charset=utf-8");
                Transport.send(message);
            }
    } catch (Exception e){
            e.printStackTrace();
        }
    }
}