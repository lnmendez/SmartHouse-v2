package com.example.luis.smarthouse;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luis.smarthouse.modelo.CrudDispositivo;
import com.example.luis.smarthouse.modelo.Dispositivos;

public class editar extends AppCompatActivity {

    private CrudDispositivo crudDispositivo;
    private EditText nombre;
    private EditText descripcion;
    private TextView title;
    private ImageView imageView;
    private Toolbar toolbar;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        crudDispositivo = new CrudDispositivo(this);

        nombre = (EditText) findViewById(R.id.edit_name);
        descripcion = (EditText) findViewById(R.id.edit_descrip);
        imageView = (ImageView) findViewById(R.id.edit_img);
        title = (TextView) findViewById(R.id.edit_title);

        toolbar = (Toolbar) findViewById(R.id.edit_bar);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Editar Dispositivo");

        String name = getIntent().getStringExtra("nameDevice2");
        String desc = getIntent().getStringExtra("descDevice2");
        int type = Integer.parseInt(getIntent().getStringExtra("typeDevice2"));

        nombre.setText(name);
        descripcion.setText(desc);
        switch (type) {
            case 2:
                imageView.setImageResource(R.drawable.lightbulb);
                title.setText("Editar Sensor de Luz");
                break;
            case 3:
                imageView.setImageResource(R.drawable.move_img);
                title.setText("Editar Sensor de Movimiento");
                break;
            case 4:
                imageView.setImageResource(R.drawable.door_img);
                title.setText("Editar Sensor de Puerta");
                break;
            case 5:
                imageView.setImageResource(R.drawable.window_img);
                title.setText("Editar Sensor de Ventana");
                break;
            case 6:
                imageView.setImageResource(R.drawable.cold_img);
                title.setText("Editar Sensor de Temperatura");
                break;
        }
    }

    public void editSave(View view) {
        if (nombre.getText().toString().isEmpty() || descripcion.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
        int n = 0;
        for (Dispositivos d : crudDispositivo.deviceList(2)) {
            if (nombre.getText().toString().equals(d.getNombre()) && d.getTipo() == 2) {
                Toast.makeText(this, "Nombre ya registrado", Toast.LENGTH_SHORT).show();
                n++;
            }
        }
        if (n == 0) {
            int id = Integer.parseInt(getIntent().getStringExtra("idDevice2"));
            String name = nombre.getText().toString();
            String describ = descripcion.getText().toString();
            int state = Integer.parseInt(getIntent().getStringExtra("stateDevice2"));
            int type = Integer.parseInt(getIntent().getStringExtra("typeDevice2"));

            Dispositivos d = new Dispositivos(id, name, describ, state, type);

            crudDispositivo.editar(d);

            Intent i;
            i = new Intent(this, dispositivos.class);
            i.putExtra("idDevice", id + "");
            i.putExtra("nameDevice", name + "");
            i.putExtra("descDevice", describ + "");
            i.putExtra("stateDevice", state + "");
            i.putExtra("typeDevice", type + "");
            startActivity(i);


            Toast.makeText(this, "Dispositivo editado", Toast.LENGTH_SHORT).show();
        }
    }

}
