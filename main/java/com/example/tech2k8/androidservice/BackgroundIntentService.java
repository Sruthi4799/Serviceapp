package com.example.tech2k8.androidservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

public class BackgroundIntentService extends IntentService {

    public BackgroundIntentService() {
        super("BackgroundIntentService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent)
    {
        try {
            Log.i("BackgroundIntentService","on Intent handle started");
            Thread.sleep(5000);
            Log.i("BackgroundIntentService","on Intent handle finished ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
