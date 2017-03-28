package com.sqisland.android.hello.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sqisland.android.hello.R;
import com.sqisland.android.hello.service.MyService;

public class AnimationActivity extends Activity {

    ViewGroup sbLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        sbLayout = (ViewGroup) findViewById(R.id.activity_animation);

        sbLayout.setOnTouchListener(new RelativeLayout.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                moveButton();
                return true;
            }
        });

        //start my service
        Intent myServiceIntent = new Intent(this, MyService.class);
        startService(myServiceIntent);


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void moveButton(){
        View moveButton = findViewById(R.id.moveButton);

        TransitionManager.beginDelayedTransition(sbLayout);

        //change the position of the button
        RelativeLayout.LayoutParams posistionRules = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        posistionRules.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
        posistionRules.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
        moveButton.setLayoutParams(posistionRules);

        //Change the size of the button
        ViewGroup.LayoutParams sizeRule = moveButton.getLayoutParams();
        sizeRule.width = 450;
        sizeRule.height = 300;
        moveButton.setLayoutParams(sizeRule);

    }
}
