package com.example.whattodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "userek_table";
    private static final String COL1 = "Nev";
    private static final String COL2 = "Email";
    private static final String COL3 = "Jelszo";
    public UserDatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+ TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+COL1 + " TEXT,"+
                COL2 + " TEXT, "+COL3+" TEXT"+")";
        db.execSQL(createTable);
    }
    public String addData(String item,String item2,String item3) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("Nev",item);
        cv.put("Email", item2);
        cv.put("Jelszo",item3);
        long res = db.insert(TABLE_NAME,null,cv);
        if (res==-1){ return"FAIL";}
        else {return "OK";}


    }
    public Cursor AdatBetolt()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor kurzor = db.rawQuery(query,null);
        return  kurzor;
    }
}
