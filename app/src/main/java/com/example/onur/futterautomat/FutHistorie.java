package com.example.onur.futterautomat;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Onur on 17.05.2017.
 */

public class FutHistorie extends  AppCompatActivity {
    SQLiteDatabase myDB;
    List<String> score= new ArrayList<String>();
    String [] values =new String[score.size()];
    ArrayAdapter<String> adapter;
    ArrayList<String> listitems=new ArrayList<String>();
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myDB =openOrCreateDatabase("futhisdb",MODE_PRIVATE,null);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.futhistorie);
        lv=(ListView)findViewById(R.id.listView);
        setup();
        retrieve();
    }


    public void setup() {
        // private static final String SQL_CREATE_ENTRIES = "CREATE TABLE" + mydb.Table_Name + "("
    /* Create a Table in the Database. */
        myDB.execSQL("CREATE TABLE IF NOT EXISTS "
                + "futhis"
                + " (Field1 VARCHAR);");
    }
    public void insert(String s) {

        myDB.execSQL("INSERT INTO futhis (Field1) VALUES ('" + s + "')");
    }

    public void retrieve() {
           /*retrieve data from database */
        Cursor c = myDB.rawQuery("SELECT * FROM " + "futhis" , null);

        int Column1 = c.getColumnIndex("Field1");

        // Check if our result was valid.
        c.moveToFirst();
        int i=0;
        if (c != null) {
            // Loop through all Results
            do {
                String Name = c.getString(Column1);
                //values[i]=score.get(i).getName();
                listitems.add(Name);
                i++;
            }while(c.moveToNext());
        }
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listitems);
        lv.setAdapter(adapter);
    }
}
