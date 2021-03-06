package com.example.whattodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
private UserDatabaseHelper db;
public static ArrayList<String> Emailek;
private ArrayList<String> Jelszavak;
private EditText Email;
private EditText Jelszo;

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        belep();
        setContentView(R.layout.activity_login);
        Email = findViewById(R.id.editText7);
        Jelszo = findViewById(R.id.editText8);
        Emailek = new ArrayList<>();
        Jelszavak = new ArrayList<>();
        db = new UserDatabaseHelper(this);
        try {
            viewData();
        }catch (Exception e){}
    }
    private void viewData() {//bejelentkezési adatok betöltése
        Cursor kurzor = db.AdatBetolt();
        if (kurzor.getCount() == 0) {


        } else {
            while (kurzor.moveToNext()) {
                Emailek.add(kurzor.getString(2));
                Jelszavak.add(kurzor.getString(3));

            }

        }
    }
    public void openRegisztracio(View view){
        Intent intent = new Intent(this,Registration.class);
        startActivity(intent);
    }
    public void belepesCheck(View view){//beléptetés
    for (int i =0;i<Emailek.size();i++){
        if(Emailek.get(i).equals(Email.getText().toString())){
            if(Jelszavak.get(i).equals(Jelszo.getText().toString()))
            {
                MainActivity.belepve = true;
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
            else Toast.makeText(this,"Hibás bejelentkezés",Toast.LENGTH_SHORT).show();
        }
        else if(!Emailek.contains(Email.getText().toString())) Toast.makeText(this,"Hibás bejelentkezés",Toast.LENGTH_SHORT).show();
    }
    }
    private void belep()
    {
        if(MainActivity.belepve == true){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
}
