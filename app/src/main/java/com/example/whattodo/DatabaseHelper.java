package com.example.whattodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import  android.database.*;



public class DatabaseHelper extends SQLiteOpenHelper {

private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "adatok_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "Nev";
    private static final String COL3 = "Datum";
    private static final String COL4 = "Ido";
    private static final String COL5 = "Hely";
    private static final String COL6 = "Egyeb";
    private static final String COL7 = "alarmAZ";



    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null,1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
String createTable = "CREATE TABLE "+ TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
        COL2 + " TEXT, "+COL3+" DATE, "+COL4+" TIME, "+COL5+" TEXT, "+COL6+" TEXT, "+COL7+" TEXT)";
db.execSQL(createTable);
    }

    public String addData(String item,String item2,String item3,String item4,String item5,String item6) {
    SQLiteDatabase db = this.getWritableDatabase();

      ContentValues cv = new ContentValues();
      cv.put("Nev",item);
      cv.put("Datum", item2);
      cv.put("Ido", item3);
      cv.put("Hely",item4);
      cv.put("Egyeb",item5);
      cv.put("alarmAZ",item6);
     long res = db.insert(TABLE_NAME,null,cv);
     if (res==-1){ return"FAIL";}
     else {return "OK";}


    }
  public Cursor AdatBetolt(String datum)
  {
      SQLiteDatabase db = this.getReadableDatabase();
      String query = "SELECT * FROM "+TABLE_NAME+" WHERE "+COL3+" = '"+datum+"'";
      Cursor kurzor = db.rawQuery(query,null);
      return  kurzor;
  }
  public Cursor EventInfo(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
    String query = "SELECT * FROM "+TABLE_NAME+" WHERE ID = "+id;
    Cursor kurzor = db.rawQuery(query,null);
      return  kurzor;
    }
    public void Delete(int id){
        SQLiteDatabase db = this.getWritableDatabase();
       db.delete(TABLE_NAME,"ID = ?",new String[]{String.valueOf(id)});

    }
}


