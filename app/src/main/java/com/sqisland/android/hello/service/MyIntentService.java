package com.sqisland.android.hello.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by liuyufei on 28/03/17.
 */

public class MyIntentService extends IntentService {

    private static final String TAG = "android.hello.service";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //This is what the service does
        Log.i(TAG,"The service has now started");
    }
}
