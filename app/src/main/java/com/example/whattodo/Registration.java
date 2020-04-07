package com.example.whattodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Registration extends AppCompatActivity {
private EditText Nev;
private EditText Email;
private EditText Jelszo;
private UserDatabaseHelper mAdat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Nev = findViewById(R.id.editText9);
        Email = findViewById(R.id.editText10);
        Jelszo = findViewById(R.id.editText11);
        mAdat = new UserDatabaseHelper(this);
    }
    public void Regisztracio(View view) {
        if (Nev.length() != 0 && Email.length() != 0 && Jelszo.length() != 0) {
            mAdat.addData(Nev.getText().toString(),Email.getText().toString(),Jelszo.getText().toString());

            Nev.setText("");
            Email.setText("");
            Jelszo.setText("");



            LoginScreen();

        } else {
            LoginScreen();
        }
    }

    public void LoginScreen()
    {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
    public void Vissza(View view)
    {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
}