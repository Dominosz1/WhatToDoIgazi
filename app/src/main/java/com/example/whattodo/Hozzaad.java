package com.example.whattodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
public class Hozzaad extends AppCompatActivity {
    DatabaseHelper mAdat;
private Button btnAdd;
private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private TextView tv;

private String nev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdat = new DatabaseHelper(this);
        setContentView(R.layout.activity_hozzaad);
        mAdat = new DatabaseHelper(this);
        btnAdd = findViewById(R.id.btnOK);
        editText1 = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);
       editText2.setText(getIntent().getExtras().getString("Naptar"));
editText3.setText(currentTime());
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText1.length() != 0) {
                   mAdat.addData(editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString(),editText4.getText().toString(),editText5.getText().toString());

                    editText2.setText("");
                    editText3.setText("");
                    editText4.setText("");
                    editText5.setText("");


                    openAc1();

                } else {
openAc1();
                }



            }
        });

    }

    private void openAc1() {
        Intent intent = new Intent(this,MainActivity.class);


        startActivity(intent);

    }
private String currentTime(){
     DateFormat dateFormat = new SimpleDateFormat("hh:mm");
     Date cal = Calendar.getInstance().getTime();
    String ct = dateFormat.format(cal);

    return ct;
}

}
