/*
 * Copyright (c) 2020. Syed Usama Ahmad
 * Infusible Coder Pvt Ltd.
 */

package com.push.sweateliteathletics.softrasol.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AutostartReciver extends BroadcastReceiver {
    Alarm60min alarm = new Alarm60min();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            alarm.setAlarm(context);
        }
    }
}