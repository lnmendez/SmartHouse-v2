package com.example.luis.smarthouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import com.example.luis.smarthouse.modelo.CrudUsuario;
import com.example.luis.smarthouse.modelo.Usuario;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class login extends AppCompatActivity {

    private CrudUsuario crudUsuario;
    private EditText user;
    private EditText pass;
    private String httpUser = "http://appmovil.todojava.net/index.php/usuarios1";
    private String httpType = "http://appmovil.todojava.net/index.php/tipos";
    private String httpDevice = "http://appmovil.todojava.net/index.php/dispositivos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        crudUsuario = new CrudUsuario(this);

        user = (EditText) findViewById(R.id.login_name);
        pass = (EditText) findViewById(R.id.login_pass);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void launchMainActivity(View view) {
        if (user.getText().toString().isEmpty() || pass.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            AsyncHttpClient client = new AsyncHttpClient();

            client.post(httpUser, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                    if (i == 200) {
                        String usuarios = new String(bytes);
                        procesaSesion(usuarios);
                    }
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                }
            });
        }
    }

    public void procesaSesion(String usuarios) {
        try {
            JSONArray json = new JSONArray(usuarios);
            String nombre;
            String contraseña;
            int n = 0;
            for (int i = 0; i < json.length(); i++) {
                nombre = json.getJSONObject(i).getString("nombre");
                contraseña = json.getJSONObject(i).getString("pass");
                if (user.getText().toString().equals(nombre) && pass.getText().toString().equals(contraseña)) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    n = 0;
                    break;
                } else {
                    n++;
                }
            }
            if (n > 0) {
                Toast.makeText(this, "Datos Invalidos", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void launchNewUser(View view) {
        Intent i = new Intent(this, add_user.class);
        startActivity(i);
    }
}
