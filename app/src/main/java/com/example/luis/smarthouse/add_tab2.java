package com.example.luis.smarthouse;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luis.smarthouse.modelo.CrudDispositivo;
import com.example.luis.smarthouse.modelo.Dispositivos;

public class add_tab2 extends AppCompatActivity {

    private CrudDispositivo crudDispositivo;
    private EditText nombre;
    private EditText descripcion;
    private Toolbar toolbar;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tab2);

        crudDispositivo = new CrudDispositivo(this);

        nombre = (EditText) findViewById(R.id.addt2_name);
        descripcion = (EditText) findViewById(R.id.addt2_descrip);

        toolbar = (Toolbar) findViewById(R.id.addt2_bar);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Nuevo Dispositivo");
    }

    public void addLight(View view) {
        if (nombre.getText().toString().isEmpty() || descripcion.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
        int i = 0;
        for (Dispositivos d : crudDispositivo.deviceList(2)) {
            if (nombre.getText().toString().equals(d.getNombre()) && d.getTipo() == 2) {
                Toast.makeText(this, "Nombre ya registrado", Toast.LENGTH_SHORT).show();
                i++;
            }
        }
        if (i == 0) {
            String name = nombre.getText().toString();
            String describ = descripcion.getText().toString();

            Dispositivos d = new Dispositivos(0, name, describ, 0, 2);

            crudDispositivo.insertar(d);
            nombre.setText("");
            descripcion.setText("");
            Toast.makeText(this, "Dispositivo registrado", Toast.LENGTH_SHORT).show();
        }
    }

}
