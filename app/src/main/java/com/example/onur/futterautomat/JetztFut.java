package com.example.onur.futterautomat;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by Onur on 17.05.2017.
 */
public class JetztFut extends  AppCompatActivity {
    Handler handler = new Handler();
    WebView myWebView;
    SQLiteDatabase myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jetztfut);
        myWebView = (WebView) findViewById(R.id.webview);
        Button button = (Button) findViewById(R.id.button2);
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
            openKlappe();

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
        Toast.makeText(getApplicationContext(), "Klappe wurde geöffnet", Toast.LENGTH_SHORT).show();

        insert("Klappe");
    }
    public void insert(String s) {
        myDB =openOrCreateDatabase("futhisdb",MODE_PRIVATE,null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS "
                + "futhis"
                + " (Field1 VARCHAR);");
        myDB.execSQL("INSERT INTO futhis (Field1) VALUES ('" + s + "')");
    }

}

