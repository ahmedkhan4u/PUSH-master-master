package com.push.sweateliteathletics.softrasol.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class Mt60minService extends Service {
    Alarm60min alarm = new Alarm60min();


    public void onCreate() {
        super.onCreate();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        alarm.setAlarm(this);
        Log.d("counterisrunning", "work here 1");
        return START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        alarm.setAlarm(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
