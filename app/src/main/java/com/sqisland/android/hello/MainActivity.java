package com.sqisland.android.hello;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.view.GestureDetectorCompat;

public class MainActivity extends Activity {

    TextView description;

    Button button1;
    Button button2;


    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        description = (TextView) findViewById(R.id.description);

        button1 = (Button) findViewById(R.id.button1);

        button1.setOnClickListener(v -> description.setText("Button1 clicked"));

        button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(v -> Toast.makeText(this, "Button clicked", Toast.LENGTH_LONG).show());

        GestureListener gestureListener = new GestureListener();
        mDetector = new GestureDetectorCompat(this,gestureListener);
        mDetector.setOnDoubleTapListener(gestureListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_bye) {
            description.setText(R.string.bye);
        }
        return super.onOptionsItemSelected(item);
    }
}