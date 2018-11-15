package com.example.luis.smarthouse;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luis.smarthouse.modelo.CrudUsuario;
import com.example.luis.smarthouse.modelo.Usuario;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import cz.msebera.android.httpclient.Header;

public class add_user extends AppCompatActivity {

    private CrudUsuario crudUsuario;
    private EditText user;
    private EditText pass;
    private EditText pass2;

    private Toolbar toolbar;
    private ActionBar actionBar;

    private String httpUser = "http://appmovil.todojava.net/index.php/usuarios1";
    private String httpInsertUser = "http://appmovil.todojava.net/index.php/insertUsuario1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        crudUsuario = new CrudUsuario(this);

        user = (EditText) findViewById(R.id.adduser_name);
        pass = (EditText) findViewById(R.id.adduser_pass);
        pass2 = (EditText) findViewById(R.id.adduser_pass2);

        toolbar = (Toolbar) findViewById(R.id.adduser_bar);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Nuevo Usuario");


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addUser(View view) {
        if (user.getText().toString().isEmpty() || pass.getText().toString().isEmpty() || pass2.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            AsyncHttpClient client = new AsyncHttpClient();

            client.post(httpUser, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                    if (i == 200) {
                        String usuarios = new String(bytes);
                        procesarNuevoUsuario(usuarios);
                    }
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                }
            });
        }
    }

    public void procesarNuevoUsuario(String usuarios) {
        try {
            JSONArray json = new JSONArray(usuarios);
            String nombre;
            int n = 0;
            for (int i = 0; i < json.length(); i++) {
                nombre = json.getJSONObject(i).getString("nombre");
                if (user.getText().toString().equals(nombre)) {
                    Log.i("INFO", "soy belga");
                    Toast.makeText(this, "Nombre de usuario ya existe", Toast.LENGTH_SHORT).show();
                    n++;
                }
            }
            if (n == 0) {
                if (pass.getText().toString().equals(pass2.getText().toString())) {
                    String name = user.getText().toString();
                    String contraseña = pass.getText().toString();
                    Log.i("INFO", name);
                    Log.i("INFO", contraseña);
                    AsyncHttpClient client = new AsyncHttpClient();
                    RequestParams params = new RequestParams();
                    params.put("nombre", name);
                    params.put("pass", contraseña);

                    client.post(httpInsertUser, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int i, Header[] headers, byte[] bytes) {
                            if (i == 200) {
                                String respuesta = new String(bytes);
                                Log.i("INFO", respuesta);
                            }
                        }
                        @Override
                        public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                        }
                    });
                    Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                    user.setText("");
                    pass.setText("");
                    pass2.setText("");
                } else {
                    Toast.makeText(this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void launchLogin(View view) {
        Intent i = new Intent(this, login.class);
        startActivity(i);
    }
}
