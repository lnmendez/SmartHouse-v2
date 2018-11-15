package com.example.luis.smarthouse.modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by luis on 09-12-17.
 */

public class ConexionHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "smarthousedb";
    private static final int VERSION = 1;

    public static final String TABLA_USER = "usuario";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_NAME = "nombre";
    public static final String COLUMN_USER_PASS = "pass";

    public static final String TABLA_DEVICE = "dispositivo";
    public static final String COLUMN_DEVICE_ID = "id";
    public static final String COLUMN_DEVICE_NAME = "nombre";
    public static final String COLUMN_DEVICE_DESCRIPTION = "descripcion";
    public static final String COLUMN_DEVICE_STATE = "estado";
    public static final String COLUMN_DEVICE_TYPE = "id_tipo";

    public ConexionHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script;
        script = "create table " + TABLA_USER + "(" + COLUMN_USER_ID + " integer primary key autoincrement,";
        script += COLUMN_USER_NAME + " text," + COLUMN_USER_PASS + " text)";

        db.execSQL(script);
        db.execSQL("insert into usuario values(null,'luis','1234')");

        String script2;
        script2 = "create table " + TABLA_DEVICE + "(";
        script2 += COLUMN_DEVICE_ID + " integer primary key autoincrement,";
        script2 += COLUMN_DEVICE_NAME + " text,";
        script2 += COLUMN_DEVICE_DESCRIPTION + " text,";
        script2 += COLUMN_DEVICE_STATE + " integer,";
        script2 += COLUMN_DEVICE_TYPE + " integer)";

        db.execSQL(script2);
        db.execSQL("insert into dispositivo values(null,'Pieza 1' ,'Pieza matrimonial', 1, 2)");
        db.execSQL("insert into dispositivo values(null,'Pieza 2' ,'Pieza invitados', 1, 2)");
        db.execSQL("insert into dispositivo values(null,'Baño' ,'Donde se ducha', 0, 2)");
        db.execSQL("insert into dispositivo values(null,'Cocina' ,'Donde se cocina', 0, 3)");
        db.execSQL("insert into dispositivo values(null,'Comedor' ,'Donde se come', 1, 3)");
        db.execSQL("insert into dispositivo values(null,'Pieza 1' ,'Pieza matrimonial', 1, 3)");
        db.execSQL("insert into dispositivo values(null,'Pieza 2' ,'Pieza invitados', 1, 4)");
        db.execSQL("insert into dispositivo values(null,'Baño' ,'Donde se ducha', 0, 4)");
        db.execSQL("insert into dispositivo values(null,'Cocina' ,'Donde se cocina', 0, 4)");
        db.execSQL("insert into dispositivo values(null,'Comedor' ,'Donde se come', 1, 4)");
        db.execSQL("insert into dispositivo values(null,'Pieza 1' ,'Pieza matrimonial', 1, 5)");
        db.execSQL("insert into dispositivo values(null,'Pieza 2' ,'Pieza invitados', 1, 5)");
        db.execSQL("insert into dispositivo values(null,'Baño' ,'Donde se ducha', 0, 5)");
        db.execSQL("insert into dispositivo values(null,'Cocina' ,'Donde se cocina', 0, 6)");
        db.execSQL("insert into dispositivo values(null,'Comedor' ,'Donde se come', 1, 6)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
