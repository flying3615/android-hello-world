package com.sqisland.android.hello;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sqisland.android.hello.ui.AnimationActivity;
import com.sqisland.android.hello.ui.FragementShow;
import com.sqisland.android.hello.ui.FragmentTest;
import com.sqisland.android.hello.ui.GestureActivity;

public class MainActivity extends FragmentActivity implements FragmentTest.OnFragmentInteractionListener {

    TextView description;

    Button button1;
    Button button2;


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            description.setText(" after 5 seconds, Nice Job Thread !!!");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        description = (TextView) findViewById(R.id.description);



        button1 = (Button) findViewById(R.id.button1);

        button1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        description.setText("Button1 clicked");
                    }
                }
        );

        button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GestureActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //render menu on GUI
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //response to the menu item click
        switch (item.getItemId()) {
            case R.id.send_broadcast:
                //create a broadcast intent
                Intent i = new Intent();
                i.setAction("com.sqisland.android.hello");
                i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                sendBroadcast(i);
                break;
            case R.id.animation:
                Toast.makeText(this,"click animation menu item", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, AnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.thread:
                Toast.makeText(this,"click thread menu item", Toast.LENGTH_SHORT).show();
                startThread();
                break;
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startThread() {
        //create and start a thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }).start();

    }


    //Called by FragmentTest when user click the button
    @Override
    public void onFragmentInteraction(String name, String passwd) {
        FragementShow fragementShow = (FragementShow) getSupportFragmentManager().findFragmentById(R.id.fragment_fragement_show);
        fragementShow.setNameAndPasswd(name, passwd);
    }
}