package com.example.onur.futterautomat;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Handler;
import android.webkit.WebView;
import android.app.PendingIntent;
import android.widget.Toast;

/**
 * Created by Onur on 17.05.2017.
 */

public class AutFut extends  AppCompatActivity{
    Editable TextStunden;
    Editable TextMinuten;
    Editable TextSekunden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autfut);
        Button button = (Button) findViewById(R.id.button2);
        assert button != null;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText textstund =(EditText)findViewById(R.id.editText);
                EditText textmin =(EditText)findViewById(R.id.editText2);
                EditText textsek =(EditText)findViewById(R.id.editText3);
                TextStunden=textstund.getText();
                TextMinuten=textmin.getText();
                TextSekunden=textsek.getText();
                int h = Integer.parseInt(TextStunden.toString());
                int m = Integer.parseInt(TextMinuten.toString());
                int s = Integer.parseInt(TextSekunden.toString());
                final Calendar cal = Calendar.getInstance();
                cal.add(Calendar.HOUR,h);
                cal.add(Calendar.MINUTE,m);
                cal.add(Calendar.SECOND,s);
                AlarmManager alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                Intent intentalarm = new Intent(getApplicationContext(),AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),1,intentalarm,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),pendingIntent);
                Toast.makeText(getApplicationContext(), "Alarm wurde gesetzt", Toast.LENGTH_SHORT).show();
            }
        } );
    }
}
