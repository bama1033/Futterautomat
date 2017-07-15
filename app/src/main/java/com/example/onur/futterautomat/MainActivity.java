package com.example.onur.futterautomat;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myDB =openOrCreateDatabase("futhisdb",MODE_PRIVATE,null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS "
                + "futhis"
                + " (Field1 VARCHAR);");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button ButtonAutFut = (Button) findViewById(R.id.button);
        ButtonAutFut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AutFut.class);
                startActivity(intent);
            }
        });
        final Button ButtonRfid = (Button) findViewById(R.id.button10);
        ButtonRfid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Rfid.class);
                startActivity(intent);
            }
        });
        final Button ButtonJetztFut = (Button) findViewById(R.id.button11);
        ButtonJetztFut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JetztFut.class);
                startActivity(intent);
            }
        });
        final Button ButtonFutHistorie = (Button) findViewById(R.id.button12);
        ButtonFutHistorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FutHistorie.class);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Willst du die App verlassen?")
                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("Nein", null).show();
    }

}
