package com.sqisland.android.hello.ui;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import com.sqisland.android.hello.MainActivity;
import com.sqisland.android.hello.R;

public class NotificationActivity extends Activity {

    Button notf_btn;

    NotificationCompat.Builder notification;

    private static final int uniqueID = 65748;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notification = new NotificationCompat.Builder(this);
        //delete from top list
        notification.setAutoCancel(true);

        notf_btn = (Button) findViewById(R.id.notif_button);
        notf_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Build the notification
                notification.setSmallIcon(R.drawable.ic_launcher);
                notification.setTicker("This is the ticker");
                notification.setWhen(System.currentTimeMillis());
                notification.setContentTitle("Here is the title");
                notification.setContentText("I am the body text of your notification");

                //back to MainActivity
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(v.getContext(),0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent(pendingIntent);

                //Build and issue it
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(uniqueID,notification.build());
            }
        });
    }
}
