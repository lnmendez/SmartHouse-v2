package com.example.luis.smarthouse.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.luis.smarthouse.MainActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by luis on 09-12-17.
 */

public class CrudDispositivo {
    private ConexionHelper helper;
    private SQLiteDatabase db;
    private ContentValues values;

    private String httpType = "http://appmovil.todojava.net/index.php/tipos";
    private String httpDevice = "http://appmovil.todojava.net/index.php/dispositivos";

    public CrudDispositivo(Context context) {
        helper = new ConexionHelper(context);
        values = new ContentValues();
    }

    public void insertar(Dispositivos d) {
        db = helper.getWritableDatabase();
        values.clear();
        values.put(ConexionHelper.COLUMN_DEVICE_NAME, d.getNombre());
        values.put(ConexionHelper.COLUMN_DEVICE_DESCRIPTION, d.getDescripcion());
        values.put(ConexionHelper.COLUMN_DEVICE_STATE, d.getEstado());
        values.put(ConexionHelper.COLUMN_DEVICE_TYPE, d.getTipo());
        db.insert(ConexionHelper.TABLA_DEVICE, null, values);
        db.close();
    }

    public void editar(Dispositivos d) {
        db = helper.getWritableDatabase();
        values.clear();
        values.put(ConexionHelper.COLUMN_DEVICE_NAME, d.getNombre());
        values.put(ConexionHelper.COLUMN_DEVICE_DESCRIPTION, d.getDescripcion());
        values.put(ConexionHelper.COLUMN_DEVICE_STATE, d.getEstado());
        values.put(ConexionHelper.COLUMN_DEVICE_TYPE, d.getTipo());
        db.update(ConexionHelper.TABLA_DEVICE, values, "id=?", new String[]{String.valueOf(d.getId())});
        db.close();
    }

    public void eliminar(int id) {
        db = helper.getWritableDatabase();
        db.delete(ConexionHelper.TABLA_DEVICE, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Dispositivos> deviceList(int type) {

        List<Dispositivos> list = new ArrayList<>();
        db = helper.getReadableDatabase();

        Cursor c = db.rawQuery("select * from " + ConexionHelper.TABLA_DEVICE, null);

        while (c.moveToNext()) {
            Dispositivos d = new Dispositivos();
            d.setId(c.getInt(0));
            d.setNombre(c.getString(1));
            d.setDescripcion(c.getString(2));
            d.setEstado(c.getInt(3));
            d.setTipo(c.getInt(4));
            if (c.getInt(4) == type) {
                list.add(d);
            }

        }

        db.close();
        return list;
    }

    public List<Dispositivos> deviceListAll() {

        List<Dispositivos> list = new ArrayList<>();
        db = helper.getReadableDatabase();

        Cursor c = db.rawQuery("select * from " + ConexionHelper.TABLA_DEVICE, null);

        while (c.moveToNext()) {
            Dispositivos d = new Dispositivos();
            d.setId(c.getInt(0));
            d.setNombre(c.getString(1));
            d.setDescripcion(c.getString(2));
            d.setEstado(c.getInt(3));
            d.setTipo(c.getInt(4));
            list.add(d);
        }

        db.close();
        return list;
    }

}
