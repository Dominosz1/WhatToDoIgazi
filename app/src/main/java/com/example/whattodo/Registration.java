package com.example.whattodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

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
        try {

            if (Validacio(Email.getText().toString()) && emailCheck() == false) {
                if (Nev.length() != 0 && Email.length() != 0 && Jelszo.length() != 0) {
                    mAdat.addData(Nev.getText().toString(), Email.getText().toString(), Jelszo.getText().toString());

                    Nev.setText("");
                    Email.setText("");
                    Jelszo.setText("");


                    LoginScreen();

                } else {
                    LoginScreen();
                }
            } else Toast.makeText(this, "Hibás E-mail formátum vagy már regisztráltál", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {Toast.makeText(this, "Nem várt hiba történt. Próbálkozz újra!", Toast.LENGTH_SHORT).show();}
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
    public static boolean Validacio(String email)
    {
        String emailformat = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailformat);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    private boolean emailCheck(){
        for (int i =0;i<Login.Emailek.size();i++){
            if(Login.Emailek.get(i).equals(Email.getText().toString())) {

                return true;

              }

            }
    return false;
    }
    }

