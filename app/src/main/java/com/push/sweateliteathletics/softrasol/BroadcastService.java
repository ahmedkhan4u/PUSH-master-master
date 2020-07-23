package com.push.sweateliteathletics.softrasol;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.concurrent.TimeUnit;

public class BroadcastService extends Service {

    CountDownTimer countDownTimer;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();



//        SharedPreferences sharedPreferences = getSharedPreferences("fitmind", Context.MODE_PRIVATE);
//        final SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        CountDownTimer countDownTimer;
//
//        long milliSeceonds = Long.parseLong(sharedPreferences.getString("time","0"));
//        String is_started = sharedPreferences.getString("is_started", "false");
//
//        if (is_started.equalsIgnoreCase("true")){
//            countDownTimer = new CountDownTimer(milliSeceonds, 1000) {
//                @Override
//                public void onTick(long millisUntilFinished) {
//
//                    SharedPreferences sharedPreferences = getSharedPreferences("fitmind", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("time", millisUntilFinished+"");
//                    editor.apply();
//
//                    long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
//                    millisUntilFinished -= TimeUnit.DAYS.toMillis(days);
//                    long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
//                    millisUntilFinished -= TimeUnit.HOURS.toMillis(hours);
//                    long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
//                    millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes);
//                    long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
//
//
//                    Log.d("counter",
//                            "Millis Until Finished"+ millisUntilFinished+
//                                    "\nDays : "+days
//                                    +"\n Hours : "+ hours
//                                    +"\n Minutes : "+ minutes
//                                    +"\n Seconds : "+ seconds
//                    );
//                }
//
//                @Override
//                public void onFinish() {
//                    editor.putString("is_started", "false");
//                }
//            };
//
//            countDownTimer.start();
//        }
//


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("counter", "OnStartCommand");
        Log.d("counter", "Created");


        countDownTimer = new CountDownTimer(70000000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("counter", millisUntilFinished+"");
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();

        return START_STICKY;
    }
//check karoo
    @Override
    public void onDestroy() {
        super.onDestroy();
//        if(countDownTimer != null)
//        countDownTimer.cancel();
        Log.d("counter","Destroyed"
        );

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}