package com.example.luis.smarthouse;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luis.smarthouse.modelo.CrudDesarrolladores;
import com.example.luis.smarthouse.modelo.MiAdaptador;

public class info extends AppCompatActivity {

    private ListView listView;
    private CrudDesarrolladores crudDesarrolladores;

    private Toolbar toolbar;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        crudDesarrolladores = new CrudDesarrolladores();

        listView = (ListView) findViewById(R.id.info_list);

        toolbar = (Toolbar) findViewById(R.id.info_bar);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Desarrolladores");

        cargarDesarrolladoresList();
    }


    private void toast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void cargarDesarrolladoresList() {
        listView.setAdapter(new MiAdaptador(this, crudDesarrolladores.desarrolladoresList));
    }

}
