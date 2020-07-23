package com.push.sweateliteathletics.softrasol.MyActivities;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.push.sweateliteathletics.softrasol.R;

public class WebViewAcitivity extends AppCompatActivity {
    WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_acitivity);

        web = findViewById(R.id.webview);

        web.loadUrl("https://www.websitepolicies.com/policies/view/1Q4UXqPR");
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setAppCacheEnabled(false);
        web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

    }
}
