package com.example.luis.smarthouse.modelo;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.example.luis.smarthouse.R;

import java.util.List;

/**
 * Created by luis on 09-12-17.
 */

public class MiAdaptador2 extends ArrayAdapter<Dispositivos> {
    public MiAdaptador2(Context context, List<Dispositivos> deviceList) {
        super(context, 0, deviceList);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater;
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.swich_listview, parent, false);
        }

        TextView nombre = (TextView) convertView.findViewById(R.id.item_nombre);
        TextView estado = (TextView) convertView.findViewById(R.id.item_estado);

        Dispositivos device = getItem(position);

        nombre.setText(String.valueOf(device.getNombre()));
        if (device.getEstado() == 1) {
            estado.setText("ACTIVO");
            estado.setTextColor(Color.BLUE);
        } else {
            estado.setText("INACTIVO");
            estado.setTextColor(Color.RED);
        }

        return convertView;
    }
}
