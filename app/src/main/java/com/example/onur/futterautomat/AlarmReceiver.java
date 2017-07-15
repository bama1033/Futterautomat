package com.example.onur.futterautomat;

import android.content.BroadcastReceiver;
import android.app.Notification;
import android.app.PendingIntent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import android.content.Context;
import android.content.Intent;
/**
 * Created by martinbauer on 29.06.17.
 */

public class AlarmReceiver extends BroadcastReceiver {
    long[] pattern = {
            50,   //Off before vibration
            100, 100,  //on-off
    };

    @Override
    public void onReceive (Context context, Intent intent){
        try {

                Intent resultIntent = new Intent(context.getApplicationContext(), Klappeoffen.class);
            resultIntent.putExtra("alarm","test");
            resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(resultIntent);

//                PendingIntent PendingIntenta =
//                        PendingIntent.getActivity(context, (int) System.currentTimeMillis(), resultIntent, 0);
//
//
//                Notification notification =
//                        new NotificationCompat.Builder(context)
//                                .setContentTitle("www")
//                                .setContentText("uuu")
//                                .setContentIntent(PendingIntenta)
//                                .setVibrate(pattern)
//                                .setAutoCancel(true)
//                                .build();
//
//                NotificationManagerCompat notificationManager =
//                        NotificationManagerCompat.from(context);
//
//                // notificationId allows you to update the notification later on.
//                notificationManager.notify(1, notification);


        } catch (Exception e) {
            Toast.makeText(context, "There was an error somewhere, but we still received an alarm", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
