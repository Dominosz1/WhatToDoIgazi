package com.example.whattodo;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btnAdd);

    }
    public void OpenHozzaad(View view){
        openAct2();

    }

    private void openAct2() {
        Intent intent = new Intent(this,Hozzaad.class);
        startActivity(intent);

    }
}
