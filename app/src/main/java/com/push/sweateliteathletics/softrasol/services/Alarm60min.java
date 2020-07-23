/*
 * Copyright (c) 2020. Syed Usama Ahmad
 * Infusible Coder Pvt Ltd.
 */

package com.push.sweateliteathletics.softrasol.services;


import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.push.sweateliteathletics.softrasol.Main2Activity;
import com.push.sweateliteathletics.softrasol.MyFragments.PushFragment;
import com.push.sweateliteathletics.softrasol.QuoreMain;
import com.push.sweateliteathletics.softrasol.R;

import java.util.Random;

public class Alarm60min extends BroadcastReceiver {

   public static long milisecremaingin ;
    CountDownTimer countDownTimer;
    //SharedPreferences.Editor editor = sharedPreferences.edit();

    @Override
    public void onReceive(final Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        assert pm != null;
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "myandroidkeylogger:showwakelock");
        wl.acquire(10 * 60 * 1000L /*10 minutes*/);

        Log.d("counterisrunning", "OnStartCommand");
        Log.d("counterisrunning", "Created");

// is me ab se 60 sec bad ak alga woo
        SharedPreferences pref = context.getSharedPreferences("fitbody", Context.MODE_PRIVATE);

        String time = pref.getString("time","-1");


        if (time.equals("0")){

            Log.d("counterisrunning001122", "working time up");
          //  showNotiFication(context,"Timer Up","Dear User Your Timer is up");


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Log.d("counter", "avvv 55");

                PushFragment.sendOreoNotification(context,"Timer Up","Dear User Your Timer is up");
            } else {

                Log.d("counter", "avvv 44");
                PushFragment.sendNotification(context,"Timer Up","Dear User Your Timer is up");
            }

            cancelAlarm(context);
        }


//        countDownTimer = new CountDownTimer(Long.parseLong(time), 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                Log.d("counterisrunning", millisUntilFinished+"");
//
//                milisecremaingin = millisUntilFinished;
//                //ye bacground me clha rah hota ha ke nahi
//                //chal paraaa  a
//                // han acha ab milisec lene ke liye  ik static variable bandoo ya kuch ur
//            }
//
//            @Override
//            public void onFinish() {
////cancelAlarm(context);
//
//                Log.d("counterisrunning", "finished");
//            }
//        };
//        countDownTimer.start();



        wl.release();
    }



    public void setAlarm(Context context) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm60min.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
        assert am != null;
        Log.d("counterisrunning", "work here 2");



        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),500,  pi); //3600000L, 86400000L Millisec * Second * Minute
    }//check now

    public void cancelAlarm(Context context) {
        Intent intent = new Intent(context, Alarm60min.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.cancel(sender);
    }


}