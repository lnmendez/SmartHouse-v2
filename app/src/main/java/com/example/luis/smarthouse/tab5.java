package com.example.luis.smarthouse;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.luis.smarthouse.modelo.CrudDispositivo;
import com.example.luis.smarthouse.modelo.Dispositivos;
import com.example.luis.smarthouse.modelo.MiAdaptador2;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class tab5 extends Fragment {

    private CrudDispositivo crudDevice;
    private ListView listView;
    private FloatingActionButton btn;

    private String httpDevice = "http://appmovil.todojava.net/index.php/dispositivos";

    public tab5() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_tab5, container, false);

        crudDevice = new CrudDispositivo(view.getContext());

        listView = (ListView) view.findViewById(R.id.tab5_list);
        btn = (FloatingActionButton) view.findViewById(R.id.tab5_btnadd);

        cargarDeviceList(view);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(view.getContext(), add_tab5.class);
                startActivity(i);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dispositivos d = (Dispositivos) listView.getAdapter().getItem(position);
                toast(d.getDescripcion());

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Dispositivos d = (Dispositivos) listView.getAdapter().getItem(position);
                Intent i = new Intent(view.getContext(), dispositivos.class);
                i.putExtra("idDevice", d.getId()+"");
                i.putExtra("nameDevice", d.getNombre()+"");
                i.putExtra("descDevice", d.getDescripcion()+"");
                i.putExtra("stateDevice", d.getEstado()+"");
                i.putExtra("typeDevice", d.getTipo()+"");
                startActivity(i);
                return true;
            }
        });
        return view;
    }

    private void toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void cargarDeviceList(View view) {

        AsyncHttpClient client = new AsyncHttpClient();

        client.post(httpDevice, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                if (i == 200) {
                    String device = new String(bytes);
                    Log.i("INFO", device);
                    httpDeviceList(device);


                }
            }
            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
            }
        });
    }

    public List<Dispositivos> httpDeviceList(String device) {

        List<Dispositivos> list = new ArrayList<>();

        try {
            JSONArray json = new JSONArray(device);
            for (int n = 0; n < json.length(); n++) {
                Dispositivos d = new Dispositivos();
                d.setId(json.getJSONObject(n).getInt("id"));
                d.setNombre(json.getJSONObject(n).getString("nombre"));
                d.setDescripcion(json.getJSONObject(n).getString("descripcion"));
                d.setEstado(json.getJSONObject(n).getInt("estado"));
                d.setTipo(json.getJSONObject(n).getInt("id_tipo"));
                if (json.getJSONObject(n).getInt("id_tipo") == 4) {
                    list.add(d);
                }

            }
            Log.i("INFO", list.toString());

            listView.setAdapter(new MiAdaptador2(getContext(), list));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
