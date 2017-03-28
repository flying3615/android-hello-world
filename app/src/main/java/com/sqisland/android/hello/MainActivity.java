package com.sqisland.android.hello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sqisland.android.hello.ui.FragementShow;
import com.sqisland.android.hello.ui.FragmentTest;
import com.sqisland.android.hello.ui.GestureActivity;

public class MainActivity extends FragmentActivity implements FragmentTest.OnFragmentInteractionListener {

    TextView description;

    Button button1;
    Button button2;



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
            case R.id.settings:
                Toast.makeText(this,"click settings menu item", Toast.LENGTH_SHORT).show();
                break;
            case R.id.profile:
                Toast.makeText(this,"click profile menu item", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Toast.makeText(this,"click logout menu item", Toast.LENGTH_SHORT).show();
                break;
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }


    //Called by FragmentTest when user click the button
    @Override
    public void onFragmentInteraction(String name, String passwd) {
        FragementShow fragementShow = (FragementShow) getSupportFragmentManager().findFragmentById(R.id.fragment_fragement_show);
        fragementShow.setNameAndPasswd(name, passwd);
    }
}