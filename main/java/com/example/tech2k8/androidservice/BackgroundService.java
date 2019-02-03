package com.example.tech2k8.androidservice;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class BackgroundService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        showNotification();
        Log.i("BackgroundService","service started"+startId);
        //stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void showNotification()
    {
        NotificationCompat.Builder builder =new NotificationCompat.Builder(this,"All Category");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Background service");
        builder.setContentText("we are working in background");
        builder.setOngoing(true);

        Intent mainActvity =new Intent(this,MainActivity.class);
        PendingIntent intent = PendingIntent.getActivity(this,1001,mainActvity,0);

        builder.setContentIntent(intent);
        NotificationManager manager =getSystemService(NotificationManager.class);
        manager.notify(1021,builder.build());

    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
