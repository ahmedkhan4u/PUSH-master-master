package com.push.sweateliteathletics.softrasol;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class QuoreStart2 extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start2);



        RelativeLayout bnnr = (RelativeLayout) findViewById(R.id.bnr);
        //Banner(bnnr, QuoreStart2.this);

        ((Button) findViewById(R.id.btnNext2)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreStart2.this.startActivity(new Intent(QuoreStart2.this, QuoreMain.class));
                QuoreStart2.this.finish();


            }
        });
    }

/*
    public void Banner(final RelativeLayout Ad_Layout, final Context context) {

        AdView mAdView = new AdView(context);
        mAdView.setAdSize(AdSize.LARGE_BANNER);
        mAdView.setAdUnitId(getString(R.string.ads_bnr));
        AdRequest adre = new AdRequest.Builder().build();
        mAdView.loadAd(adre);
        Ad_Layout.addView(mAdView);

        mAdView.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                // TODO Auto-generated method stub
                Ad_Layout.setVisibility(View.VISIBLE);
                super.onAdLoaded();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // TODO Auto-generated method stub

                fb_baner(Ad_Layout, context);
            }
        });
    }


    public void fb_baner(final RelativeLayout ad_layout, final Context context) {

        try {
            com.facebook.ads.AdView adView = new com.facebook.ads.AdView(context, getString(R.string.fb_bnr),
                    com.facebook.ads.AdSize.BANNER_HEIGHT_90);

            ad_layout.addView(adView);
            adView.setAdListener(new com.facebook.ads.AdListener() {
                @Override
                public void onError(Ad ad, AdError adError) {
                    Banner(ad_layout, context);
                }

                @Override
                public void onAdLoaded(Ad ad) {
                    ad_layout.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            });
            adView.loadAd();


        } catch (Exception e) {
        }
    }*/

}
