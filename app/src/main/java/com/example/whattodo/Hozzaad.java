package com.example.whattodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Time;
import java.util.Date;

public class Hozzaad extends AppCompatActivity {
    DatabaseHelper mAdat;
private Button btnAdd;
private EditText editText1;
private String nev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hozzaad);
        mAdat = new DatabaseHelper(this);
        btnAdd = findViewById(R.id.btnOK);
        editText1 = findViewById(R.id.editText);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText1.getText().toString();
                if (editText1.length() != 0) {
                   // addData();
                    editText1.setText("");
                } else {

                }
                nev = editText1.getText().toString();

            }
        });
    }

    private void openAc1() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }

   /* public void addData(String nev,) {

    }*/
}
