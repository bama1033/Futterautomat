package com.example.onur.futterautomat;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;



/**
 * Created by Onur on 17.05.2017.
 */

public class Rfid extends  AppCompatActivity{
    private ToggleButton tog;
    private String urlTextnew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.futrfid);
         tog = (ToggleButton) findViewById(R.id.button3);;
          urlTextnew ="http://192.168.240.1/data/get";


    }
    public void onToggleClicked(View v){
        if(tog.isChecked()) {
            //Toast.makeText(this, "RFID-Fütterung ist aktiv", Toast.LENGTH_SHORT).show();
            new JSONtask().execute(urlTextnew);
        }
    }
    public void callURL(){
        new JSONtask().execute(urlTextnew);
    }
    public class JSONtask extends AsyncTask<Object, Object, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(Object... params) {
            try {
                HttpURLConnection connection;
                final URL url = new URL((String) params[0]);
                ArrayList<String> list = new ArrayList<>();
                try {
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();
                    InputStream stream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder buffer = new StringBuilder();
                    String line;
                    while (null != (line = reader.readLine())) {
                        buffer.append(line);
                    }
                    String finalJson = buffer.toString();
                    JSONObject parentObject = new JSONObject(finalJson);
                    JSONObject finalobject = parentObject.getJSONObject("value");

                    String nr = finalobject.getString("Kartennummer");

                    list.add(nr);
                    return list;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
            }
            return null;
        }
        protected  void onPostExecute(ArrayList<String> list){
            Toast.makeText(getApplication(), "RFID war: "+list.get(0)+"!", Toast.LENGTH_SHORT).show();
        }
    }
    /*
    public class JSONtask extends AsyncTask<Object,Object,ArrayList<String>>{
        @Override
        protected ArrayList<String> doInBackground(Object... objects) {
            try{
                HttpURLConnection connection;
                final URL url = new URL ("http://192.168.240.1/data/get");
                ArrayList<String> list = new ArrayList<>();
                try{
                    connection=(HttpURLConnection)url.openConnection();
                    Toast.makeText(getApplication(), "1", Toast.LENGTH_SHORT).show();
                    connection.connect();
                    Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
                    InputStream stream = connection.getInputStream();
                    Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();
                    BufferedReader reader =new BufferedReader(new InputStreamReader(stream));
                    Toast.makeText(getApplicationContext(), "4", Toast.LENGTH_SHORT).show();
                    StringBuilder buffer =new StringBuilder();
                    Toast.makeText(getApplicationContext(), "5", Toast.LENGTH_SHORT).show();
                    String line;
                    while(null != (line = reader.readLine())){
                        buffer.append(line);
                    }
                    String finalJson = buffer.toString();
                    JSONObject parentObject =new JSONObject(finalJson);
                    JSONObject finalobject =parentObject.getJSONObject("value");
                    Toast.makeText(getApplicationContext(), "6", Toast.LENGTH_SHORT).show();

                    String Kartennummer =finalobject.getString("Kartennummer");
                    Toast.makeText(getApplicationContext(), "7", Toast.LENGTH_SHORT).show();
                    int nrtest =Integer.parseInt(Kartennummer);
                    if (nrtest==99){
                        Toast.makeText(getApplicationContext(), "RFID ist: "+nrtest+"!", Toast.LENGTH_SHORT).show();
                        SystemClock.sleep(3000);
                        callURL();}
                    else
                        Toast.makeText(getApplicationContext(), "RFID ist: "+nrtest+"!", Toast.LENGTH_SHORT).show();

                    return list;
                }
                catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
            catch (Exception e){
                Log.e("Error", e.getMessage(),e);
            }
            return null;
        }
        protected void onPostExecute (){

        }

    }
*/
}


