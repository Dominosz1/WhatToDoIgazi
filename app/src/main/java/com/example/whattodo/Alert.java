package com.example.whattodo;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class Alert extends BroadcastReceiver {
    private NotificationManagerCompat notificationManager;
    @Override
    public void onReceive(Context context, Intent intent) {
        String cim = intent.getExtras().getString("cim");
        String szoveg = intent.getExtras().getString("szoveg");
        notificationManager = NotificationManagerCompat.from(context);
        Notification notification = new NotificationCompat.Builder(context,App.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_date_range_black_24dp)
                .setContentTitle(cim)
                .setContentText(szoveg)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .build();
        notificationManager.notify(1,notification);
    }
}
