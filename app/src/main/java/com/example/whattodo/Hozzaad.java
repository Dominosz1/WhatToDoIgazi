package com.example.whattodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
public class Hozzaad extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    DatabaseHelper mAdat;
private Button btnAdd;
private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdat = new DatabaseHelper(this);
        setContentView(R.layout.activity_hozzaad);

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
public void setAlarm(View view){
    DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getSupportFragmentManager(),"time picker");
}

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
String[] date = editText2.getText().toString().split("-");
Calendar c = Calendar.getInstance();
c.set(Calendar.YEAR,Integer.parseInt(date[0]));
c.set(Calendar.MONTH,Integer.parseInt(honap(date[1])));
c.set(Calendar.DAY_OF_MONTH,Integer.parseInt(date[2]));
c.set(Calendar.HOUR_OF_DAY,hourOfDay);
c.set(Calendar.MINUTE,minute);
c.set(Calendar.SECOND,0);
startAlarm(c);
    }
    private void startAlarm(Calendar c)
    {

        Button b = findViewById(R.id.btnAlert);
        if(alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        Intent intent = new Intent(this,Alert.class);
        intent.putExtra("cim",editText1.getText().toString());
        intent.putExtra("szoveg",editText5.getText().toString());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,getIntent().getExtras().getInt("alarmID"),intent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
    b.setEnabled(false);
    }
    private String honap(String d){
        if(d.startsWith("0") == true){
           return d.substring(1);
        }
        else return d;
    }
}
