package com.push.sweateliteathletics.softrasol;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class QuoreSettings extends PreferenceActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name);
        addPreferencesFromResource(R.xml.preferences);
    }
}
