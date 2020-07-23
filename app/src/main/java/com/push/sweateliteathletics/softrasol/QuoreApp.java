package com.push.sweateliteathletics.softrasol;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;


public class QuoreApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MobileAds.initialize(this, getString(R.string.ads_id));

    }
}
