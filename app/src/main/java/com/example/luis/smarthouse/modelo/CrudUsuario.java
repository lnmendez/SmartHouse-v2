package com.example.luis.smarthouse.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis on 09-12-17.
 */

public class CrudUsuario {
    private ConexionHelper helper;
    private SQLiteDatabase db;
    private ContentValues values;

    public CrudUsuario(Context context) {
        helper = new ConexionHelper(context);
        values = new ContentValues();
    }

    public void insertar(Usuario u) {
        db = helper.getWritableDatabase();
        values.clear();
        values.put(ConexionHelper.COLUMN_USER_NAME, u.getNombre());
        values.put(ConexionHelper.COLUMN_USER_PASS, u.getPass());
        db.insert(ConexionHelper.TABLA_USER, null, values);
        db.close();
    }

    public void editar(Usuario u) {
        db = helper.getWritableDatabase();
        values.clear();
        values.put(ConexionHelper.COLUMN_USER_NAME, u.getNombre());
        values.put(ConexionHelper.COLUMN_USER_PASS, u.getPass());
        db.update(ConexionHelper.TABLA_USER, values, "id=?", new String[]{String.valueOf(u.getId())});
        db.close();
    }

    public void eliminar(int id) {
        db = helper.getWritableDatabase();
        db.delete(ConexionHelper.TABLA_USER, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public Usuario buscarUsuario(int id) {
        Usuario u = null;
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + ConexionHelper.TABLA_USER + " where id=?",
                new String[]{String.valueOf(id)});
        if (cursor.moveToNext()) {
            cursor.getString(1);
            cursor.getString(2);
        }
        db.close();
        return u;
    }

    public List<Usuario> userList() {

        List<Usuario> list = new ArrayList<>();
        db = helper.getReadableDatabase();

        Cursor c = db.rawQuery("select * from " + ConexionHelper.TABLA_USER, null);

        while (c.moveToNext()) {
            Usuario u = new Usuario();
            u.setId(c.getInt(0));
            u.setNombre(c.getString(1));
            u.setPass(c.getString(2));
            list.add(u);
        }

        db.close();
        return list;
    }

}
