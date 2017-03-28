package com.sqisland.android.hello.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liuyufei on 28/03/17.
 */

public class MyService extends Service {

    private static final String TAG = "android.hello.service";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG,"MyService onStartCommand");

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++){
                    try {
                        Thread.sleep(5000);
                        Log.i(TAG,"service is doing something");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"MyService onDestroy");

    }

    //binder for client and this service
    private final IBinder myBinder = new MyLocalBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"MyService onbind");
        return myBinder;
    }


    public String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }


    public class MyLocalBinder extends Binder {
        public MyService getService(){
            return MyService.this;
        }

    }
}
