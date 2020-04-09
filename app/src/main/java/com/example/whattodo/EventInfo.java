package com.example.whattodo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EventInfo extends AppCompatActivity {
    private DatabaseHelper db;
    private AlarmManager alarmManager;
    private TextView tv;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private EditText et;
    private int alarmAZ;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(this);
        setContentView(R.layout.activity_event_info);
        tv = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);
        tv4 = findViewById(R.id.textView4);
        et = findViewById(R.id.editText6);
viewData();
        button = findViewById(R.id.btnDel);
    }
    private void viewData() {
        Cursor kurzor = db.EventInfo(getIntent().getExtras().getInt("ID"));
        if(kurzor.getCount()==0){

            Toast.makeText(this,"Nincs adat",Toast.LENGTH_SHORT).show();
        }
        else {
            while (kurzor.moveToNext()) {
                tv.setText(kurzor.getString(1));
                tv2.setText(kurzor.getString(2));
                tv3.setText(kurzor.getString(3));
                tv4.setText(kurzor.getString(4));
                et.setText(kurzor.getString(5));
                alarmAZ = kurzor.getInt(6);
            }

        }

    }
    public void Torol(View view){
db.Delete(getIntent().getExtras().getInt("ID"));
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        Intent intent = new Intent(getApplicationContext(),Alert.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),alarmAZ,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
        Intent intenttovabb = new Intent(this,MainActivity.class);


        startActivity(intenttovabb);
    }
}
