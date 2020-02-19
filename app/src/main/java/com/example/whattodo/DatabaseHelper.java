package com.example.whattodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import  android.database.*;
import android.util.Log;

import java.sql.*;


public class DatabaseHelper extends SQLiteOpenHelper {

    // fields

private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "adatok_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "Nev";
    private static Date COL3;
    private static Time COL4;
    private static final String COL5 = "Hely";
    private static final String COL6 = "Egyeb";



    // constructors
    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null,1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP  TABLE IF EXISTS "+TABLE_NAME);
onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
String createTable = "CREATE TABLE "+ TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
        COL2 + " TEXT, "+COL3+" DATE, "+COL4+" TIME, "+COL5+" TEXT, "+COL6+" TEXT)";
db.execSQL(createTable);
    }

    public void addData(String item,Date item2,Time item3,String item4,String item5) {
    SQLiteDatabase db = this.getWritableDatabase();

      db.execSQL("INSERT INTO "+TABLE_NAME+" VALUES ('{}','{}','{}','{}','{}','{}')".format(item,item2,item3,item4,item5));



    }
}


