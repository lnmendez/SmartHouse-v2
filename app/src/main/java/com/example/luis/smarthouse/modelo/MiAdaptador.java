package com.example.luis.smarthouse.modelo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.luis.smarthouse.R;

import java.util.List;

import static com.example.luis.smarthouse.R.drawable.luis;

/**
 * Created by luis on 09-12-17.
 */

public class MiAdaptador extends ArrayAdapter<Desarrolladores> {
    public MiAdaptador(Context context, List<Desarrolladores> desarrolladoresList) {
        super(context, 0, desarrolladoresList);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater;
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
        }

        ImageView img = (ImageView) convertView.findViewById(R.id.item2_img);
        TextView txtnombre = (TextView) convertView.findViewById(R.id.item2_nombre);
        TextView txtcargo = (TextView) convertView.findViewById(R.id.item2_cargo);
        TextView txtcorreo = (TextView) convertView.findViewById(R.id.item2_correo);

        Desarrolladores des = getItem(position);


        txtnombre.setText(String.valueOf(des.getNombre()));
        txtcargo.setText(String.valueOf(des.getCargo()));
        txtcorreo.setText(String.valueOf(des.getCorreo()));

        if (des.getNombre().toString().equalsIgnoreCase("Luis Méndez")) {
            img.setImageResource(R.drawable.luis);
        } else if (des.getNombre().toString().equalsIgnoreCase("Jose Yañez")){
            img.setImageResource(R.drawable.jose);
        } else {
            img.setImageResource(R.drawable.cati);
        }

        return convertView;
    }
}
