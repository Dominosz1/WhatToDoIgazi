package com.example.whattodo;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
private ArrayList<String> eventek;
private ArrayList<Integer> eventekID;
private ArrayAdapter adapter;

private DatabaseHelper db;
private Button button;
private CalendarView cv;
private ListView lv;
private String kivEvent;
private int eventID;
public static int alarmID = 0;
public static boolean belepve = false;
//private String datum = Calendar.getInstance().getTime().getYear()+"-"+Calendar.getInstance().getTime().getMonth()+"-"+Calendar.getInstance().getTime().getDay();
private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
private Date cal = Calendar.getInstance().getTime();
private String datum = dateFormat.format(cal);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        db = new DatabaseHelper(this);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btnAdd);
        lv = findViewById(R.id.lvAdatok);
cv = findViewById(R.id.calendarView);
        eventek = new ArrayList<>();
        eventekID = new ArrayList<>();
        viewData();
lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
 kivEvent = lv.getItemAtPosition(position).toString();

for(int i =0;i<eventek.size();i++){
    if(eventek.get(i).equals(kivEvent)){
        eventID=eventekID.get(i);
    }
}

OpenActEvInfo();
    }
});
cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        String honap;//naptár adat váltás
        if((month+1)<10)   honap =("0"+(month+1));
        else honap = String.valueOf(month+1);
        String nap;
        if(dayOfMonth<10)   nap =("0"+dayOfMonth);
        else nap = String.valueOf(dayOfMonth);
        datum = year+"-"+honap+"-"+nap;
        eventek.clear();
        lv.setAdapter(null);
        viewData();
    }
});

        //Belepes();
    }

    private void viewData() {// naptár adat betöltés
        Cursor kurzor = db.AdatBetolt(datum);
        if(kurzor.getCount()==0){

           // Toast.makeText(this,"nincs adat",Toast.LENGTH_SHORT).show();
        }
        else {
            while (kurzor.moveToNext()) {
                eventekID.add(kurzor.getInt(0));
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
        //intent.putExtra("AlarmID",alarmID);

        alarmID = alarmID + 1;
        startActivity(intent);

    }
private void OpenActEvInfo(){
        Intent intent = new Intent(this,EventInfo.class);
        intent.putExtra("ID",eventID);
        startActivity(intent);
}
private void Belepes()
{
    if(!belepve)
    {

        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }

    }
    public void Kilepes(View view){
        belepve = false;
        Belepes();
    }



}

