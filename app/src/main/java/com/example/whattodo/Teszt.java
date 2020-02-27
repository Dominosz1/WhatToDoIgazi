package com.example.whattodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Teszt extends AppCompatActivity {

    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teszt);
tv = findViewById(R.id.idk);
tv.setText(String.valueOf(getIntent().getExtras().getString("nevT")));
    }
}
