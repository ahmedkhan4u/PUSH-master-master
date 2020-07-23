package com.push.sweateliteathletics.softrasol.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.push.sweateliteathletics.softrasol.QuoreMain;
import com.push.sweateliteathletics.softrasol.services.utils.LocalData;
import com.push.sweateliteathletics.softrasol.services.utils.NotificationScheduler;

public class AlarmReceiver extends BroadcastReceiver {

    String TAG = "AlarmReceiver";
    String content1 = "";
    String content2 = "";
    String type = "";
    String type2 = "";

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("fitbody", Context.MODE_PRIVATE);
        content1 = sharedPreferences.getString("content1","");
        content2 = sharedPreferences.getString("content2","");
        type = sharedPreferences.getString("type","");
        type2 = sharedPreferences.getString("type2","");
        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED");
                LocalData localData = new LocalData(context);
                NotificationScheduler.setReminder(context, AlarmReceiver.class, localData.get_hour(), localData.get_min());
                return;
            }
        }

        if (!type.equals("") && type.equals("fitbody")){
            NotificationScheduler.showNotification(context, QuoreMain.class, "", content1,"body");
        }

        if (!type2.equals("") && type2.equals("fitmind")){
            NotificationScheduler.showNotification(context, QuoreMain.class, "", content2, "mind");
        }
    }
}
