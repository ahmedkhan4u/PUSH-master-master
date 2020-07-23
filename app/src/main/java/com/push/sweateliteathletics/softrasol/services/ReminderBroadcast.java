package com.push.sweateliteathletics.softrasol.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.push.sweateliteathletics.softrasol.R;


public class ReminderBroadcast extends BroadcastReceiver {
    private static String TAG = ReminderBroadcast.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyPush")
                .setSmallIcon(R.drawable.push_icon)
                .setContentTitle("My Notification")
                .setContentText("Hey, this is a test notification")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(200, builder.build());
        Log.d(TAG, "recieved");
    }
}
