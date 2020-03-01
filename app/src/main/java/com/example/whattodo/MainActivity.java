package com.example.whattodo;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private ArrayList<String> eventek;
private ArrayAdapter adapter;
private TextView tv;
private DatabaseHelper db;
private Button button;
private CalendarView cv;
private ListView lv;
private String datum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(this);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btnAdd);
        lv = findViewById(R.id.lvAdatok);
cv = findViewById(R.id.calendarView);
        eventek = new ArrayList<>();
        viewData();
lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
String text = lv.getItemAtPosition(position).toString();
Toast.makeText(MainActivity.this,""+text,Toast.LENGTH_SHORT).show();
    }
});
cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        datum = year+"-"+month+"-"+dayOfMonth;
    }
});


    }

    private void viewData() {
        Cursor kurzor = db.AdatBetolt();
        if(kurzor.getCount()==0){

            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
        else {
            while (kurzor.moveToNext()) {
                eventek.add(kurzor.getString(1));
            }

        }
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,eventek);
lv.setAdapter(adapter);
    }

    public void OpenHozzaad(View view){
        openAct2();

    }

    private void openAct2() {
        Intent intent = new Intent(this,Hozzaad.class);
        intent.putExtra("Naptar",datum);
        startActivity(intent);

    }

}
