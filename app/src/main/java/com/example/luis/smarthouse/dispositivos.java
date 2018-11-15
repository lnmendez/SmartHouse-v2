package com.example.luis.smarthouse;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luis.smarthouse.modelo.CrudDispositivo;
import com.example.luis.smarthouse.modelo.Dispositivos;

public class dispositivos extends AppCompatActivity {

    private CrudDispositivo crudDispositivo;
    private TextView nombre;
    private TextView descripcion;
    private TextView estado;
    private ImageView imageView;
    private Button button;

    private Toolbar toolbar;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispositivos);

        crudDispositivo = new CrudDispositivo(this);

        nombre = (TextView) findViewById(R.id.device_name);
        descripcion = (TextView) findViewById(R.id.device_descrip);
        estado = (TextView) findViewById(R.id.device_estado);
        imageView = (ImageView) findViewById(R.id.device_img);
        button = (Button) findViewById(R.id.device_btn);

        toolbar = (Toolbar) findViewById(R.id.device_bar);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();


        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Estado del Dispositivo");


        int id = Integer.parseInt(getIntent().getStringExtra("idDevice"));
        String name = getIntent().getStringExtra("nameDevice");
        String desc = getIntent().getStringExtra("descDevice");
        int state = Integer.parseInt(getIntent().getStringExtra("stateDevice"));
        int type = Integer.parseInt(getIntent().getStringExtra("typeDevice"));

        nombre.setText("Nombre: " + name);
        descripcion.setText("Descripción: " + desc);
        if (state == 1) {
            estado.setText("Estado: Activo");
            button.setText("DESATIVAR");
        } else {
            estado.setText("Estado: Inactivo");
            button.setText("ACTIVAR");
        }
        switch (type) {
            case 2:
                imageView.setImageResource(R.drawable.lightbulb);
                break;
            case 3:
                imageView.setImageResource(R.drawable.move_img);
                break;
            case 4:
                imageView.setImageResource(R.drawable.door_img);
                break;
            case 5:
                imageView.setImageResource(R.drawable.window_img);
                break;
            case 6:
                imageView.setImageResource(R.drawable.cold_img);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu2, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        int id = Integer.parseInt(getIntent().getStringExtra("idDevice"));
        String name = getIntent().getStringExtra("nameDevice");
        String desc = getIntent().getStringExtra("descDevice");
        //int state = Integer.parseInt(getIntent().getStringExtra("stateDevice"));
        int type = Integer.parseInt(getIntent().getStringExtra("typeDevice"));

        estado = (TextView) findViewById(R.id.device_estado);

        String state = estado.getText().toString();

        int idstate = 0;
        if (state.equals("Estado: Activo")) {
            idstate = 1;
        }

        switch (item.getItemId()) {
            case R.id.menu2_edit:
                i = new Intent(this, editar.class);
                i.putExtra("idDevice2", id + "");
                i.putExtra("nameDevice2", name + "");
                i.putExtra("descDevice2", desc + "");
                i.putExtra("stateDevice2", idstate + "");
                i.putExtra("typeDevice2", type + "");
                startActivity(i);
                return true;
            case R.id.menu2_delete:

                crudDispositivo.eliminar(id);
                i = new Intent(this, MainActivity.class);
                startActivity(i);
                Toast.makeText(this, "Dispositivo Eliminado", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deviceAction(View view) {

        int id = Integer.parseInt(getIntent().getStringExtra("idDevice"));
        String name = getIntent().getStringExtra("nameDevice");
        String desc = getIntent().getStringExtra("descDevice");
        //int state = Integer.parseInt(getIntent().getStringExtra("stateDevice"));
        int type = Integer.parseInt(getIntent().getStringExtra("typeDevice"));

        String state = button.getText().toString();
        int idstate = 1;
        if (state.equals("ACTIVAR")) {
            idstate = 0;
        }


        if (idstate == 1) {
            Dispositivos d = new Dispositivos(id, name, desc, 0, type);
            crudDispositivo.editar(d);
        } else {
            Dispositivos d = new Dispositivos(id, name, desc, 1, type);
            crudDispositivo.editar(d);
        }

        nombre.setText("Nombre: " + name);
        descripcion.setText("Descripción: " + desc);
        estado.setText("Estado: " + desc);
        if (idstate == 0) {
            estado.setText("Estado: Activo");
            button.setText("DESATIVAR");
        } else {
            estado.setText("Estado: Inactivo");
            button.setText("ACTIVAR");
        }
        switch (type) {
            case 2:
                imageView.setImageResource(R.drawable.lightbulb);
                break;
            case 3:
                imageView.setImageResource(R.drawable.move_img);
                break;
            case 4:
                imageView.setImageResource(R.drawable.door_img);
                break;
            case 5:
                imageView.setImageResource(R.drawable.window_img);
                break;
            case 6:
                imageView.setImageResource(R.drawable.cold_img);
                break;
        }


    }

}
