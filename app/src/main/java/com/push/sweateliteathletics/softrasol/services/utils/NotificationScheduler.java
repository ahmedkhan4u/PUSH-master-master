package com.push.sweateliteathletics.softrasol.services.utils;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.TaskStackBuilder;

import com.push.sweateliteathletics.softrasol.Main2Activity;
import com.push.sweateliteathletics.softrasol.MyFragments.PushFragment;
import com.push.sweateliteathletics.softrasol.QuoreMain;
import com.push.sweateliteathletics.softrasol.R;
import com.push.sweateliteathletics.softrasol.services.OreoNotification;

import java.util.Calendar;
import java.util.Random;

import static android.content.Context.ALARM_SERVICE;

public class NotificationScheduler {
    public static final int DAILY_REMINDER_REQUEST_CODE = 1001;
    public static final String TAG = "NotificationScheduler";

    public static void setReminder(Context context, Class<?> cls, long hour, int min) {
        Log.d(TAG, "setReminder:");
        SharedPreferences sharedPreferences = context.getSharedPreferences("fitbody", Context.MODE_PRIVATE);
        final String time = sharedPreferences.getString("time", "");

        Calendar calendar = Calendar.getInstance();

        Calendar setcalendar = Calendar.getInstance();
//        setcalendar.set(Calendar.HOUR_OF_DAY, hour);
        setcalendar.set(Calendar.MINUTE, min);
        setcalendar.set(Calendar.SECOND, 0);

        // cancel already scheduled reminders
        cancelReminder(context, cls);

        if (setcalendar.before(calendar))
            setcalendar.add(Calendar.DATE, 1);

        // Enable a receiver

        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);


        Intent intent1 = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, DAILY_REMINDER_REQUEST_CODE, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        am.setExact(AlarmManager.RTC_WAKEUP, (hour - (1 * 86400000)), pendingIntent);
    }

    public static void cancelReminder(Context context, Class<?> cls) {
        // Disable a receiver

        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

        Intent intent1 = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, DAILY_REMINDER_REQUEST_CODE, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        am.cancel(pendingIntent);
        pendingIntent.cancel();
    }

    public static void showNotification(Context context, Class<?> cls, String title, String content, String type) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("fitbody", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sendOreoNotification(context.getApplicationContext(), "Push goal reminder", "One day left to your goal - " + content);
        } else {
            sendNotification(context.getApplicationContext(), "Push goal reminder", "One day left to your goal - " + content);
        }
        if (type.equals("body")){
            editor.putString("type","");
        }
        if (type.equals("mind")){
            editor.putString("type2","");
        }
        editor.apply();
    }

    public static void sendOreoNotification(Context context, String notificationTitle, String notificationBody) {

        Random random1 = new Random();
        int j = random1.nextInt(5);

        Intent intent = new Intent(context, QuoreMain.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, j, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSound = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.notification);

        OreoNotification oreoNotification = new OreoNotification(context);
        Notification.Builder builder = oreoNotification.getOreoNotification(notificationTitle, notificationBody, pendingIntent,
                defaultSound, R.drawable.ic_date);

        Random random = new Random();
        int num = random.nextInt(5);

        oreoNotification.getManager().notify(num, builder.build());

    }

    public static void sendNotification(Context context, String notificationTitle, String notificationBody) {

        Random random1 = new Random();
        int j = random1.nextInt(5);

        Intent intent = new Intent(context, QuoreMain.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, j, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSound = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.notification);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_date)
                .setContentTitle(notificationTitle)
                .setContentText(notificationBody)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        NotificationManager noti = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Random random = new Random();
        int num = random.nextInt(5);

        noti.notify(num, builder.build());
    }


}
