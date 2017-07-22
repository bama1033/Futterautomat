package com.example.onur.futterautomat;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by martinbauer on 01.07.17.
 */

public class Klappeoffen extends AppCompatActivity {
    Handler handler = new Handler();
    WebView myWebView;
    SQLiteDatabase myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.klappegeoffnet);
        myWebView = (WebView) findViewById(R.id.webview);
        Button button = (Button) findViewById(R.id.button4);
        assert button != null;

        if(savedInstanceState==null)
        {
            Bundle extras = getIntent().getExtras();
            if(extras==null){

            }
            else{
                String method = extras.getString("alarm");
                if (method.equals("test")){
                    openKlappe();
                }
            }

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

    }
    public void openKlappe(){

        myWebView.loadUrl("http://192.168.240.1/data/put/servo/1");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myWebView.loadUrl("http://192.168.240.1/data/put/servo/2");
            }
        }, 3000);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        insert(currentDateTimeString);
    }
    public void insert(String s) {
        myDB =openOrCreateDatabase("futhisdb",MODE_PRIVATE,null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS "
                + "futhis"
                + " (Field1 VARCHAR);");
        myDB.execSQL("INSERT INTO futhis (Field1) VALUES ('" + "Es wurde am "+s +" per Alarm gefüttert"+ "')");
    }

}
