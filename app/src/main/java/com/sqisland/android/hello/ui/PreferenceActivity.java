package com.sqisland.android.hello.ui;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sqisland.android.hello.R;

public class PreferenceActivity extends Activity {


    EditText userName;
    EditText password;
    TextView showInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        userName = (EditText) findViewById(R.id.name_input);
        password = (EditText) findViewById(R.id.password_input);
        showInfo = (TextView) findViewById(R.id.infoshow);


        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInfo(v);
            }
        });

        findViewById(R.id.display).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display(v);
            }
        });

    }


    public void saveInfo(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",userName.getText().toString());
        editor.putString("password",password.getText().toString());
        editor.apply();

        Toast.makeText(this,"Saved!",Toast.LENGTH_SHORT).show();
    }

    public void display(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("username","");
        String pw = sharedPreferences.getString("password","");
        showInfo.setText(name+" "+pw);

    }

}
