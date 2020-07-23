package com.push.sweateliteathletics.softrasol;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Calendar;
import java.util.Locale;

public class QuoreStart1 extends AppCompatActivity
{
    private ArrayAdapter<CharSequence> adapterP;
    private ArrayAdapter<CharSequence> adapterT;
    private ArrayAdapter<CharSequence> adapterU;
    private ArrayAdapter<CharSequence> adapterV;
    private Button btnNext;
    private EditText etHeight;
    private EditText etWeight;
    private EditText etYear;
    private int gender;
    private double height;
    private String heightStr;
    private String mHeight;
    private String mWeight;
    private Spinner spinGender;
    private Spinner spinHeight;
    private Spinner spinUnit;
    private Spinner spinWeight;
    private String units;
    private int unitsPosition;
    private double weight;
    private String weightStr;
    private int year;
    private String yearStr;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start1);




        RelativeLayout bnnr = (RelativeLayout) findViewById(R.id.bnr);
        //Banner(bnnr, QuoreStart1.this);


        this.spinHeight = (Spinner) findViewById(R.id.spHeight);
        this.spinWeight = (Spinner) findViewById(R.id.spWeight);
        this.spinGender = (Spinner) findViewById(R.id.spGender);
        this.spinUnit = (Spinner) findViewById(R.id.spUnit);
        this.etYear = (EditText) findViewById(R.id.etDate);
        this.etHeight = (EditText) findViewById(R.id.etHeight);
        this.etWeight = (EditText) findViewById(R.id.etWeight);
        this.btnNext = (Button) findViewById(R.id.btnNext1);
        this.adapterT = ArrayAdapter.createFromResource(this, R.array.Weight, 17367048);
        this.adapterT.setDropDownViewResource(17367049);
        this.spinWeight.setAdapter(this.adapterT);
        this.adapterV = ArrayAdapter.createFromResource(this, R.array.Height, 17367048);
        this.adapterV.setDropDownViewResource(17367049);
        this.spinHeight.setAdapter(this.adapterV);
        this.adapterP = ArrayAdapter.createFromResource(this, R.array.Gender, 17367048);
        this.adapterP.setDropDownViewResource(17367049);
        this.spinGender.setAdapter(this.adapterP);
        this.adapterU = ArrayAdapter.createFromResource(this, R.array.units, 17367048);
        this.adapterU.setDropDownViewResource(17367049);
        this.spinUnit.setAdapter(this.adapterU);
        this.btnNext.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreStart1.this.yearStr = QuoreStart1.this.etYear.getText().toString();
                QuoreStart1.this.weightStr = QuoreStart1.this.etWeight.getText().toString();
                QuoreStart1.this.heightStr = QuoreStart1.this.etHeight.getText().toString();
                QuoreStart1.this.mHeight = QuoreStart1.this.spinHeight.getSelectedItem().toString();
                QuoreStart1.this.mWeight = QuoreStart1.this.spinWeight.getSelectedItem().toString();
                QuoreStart1.this.gender = QuoreStart1.this.spinGender.getSelectedItemPosition();
                QuoreStart1.this.unitsPosition = QuoreStart1.this.spinUnit.getSelectedItemPosition();
                if (QuoreStart1.this.yearStr.trim().isEmpty()) {
                    Toast.makeText(QuoreStart1.this.getApplicationContext(), QuoreStart1.this.getResources().getString(R.string.not_entered_year_of_birth), 0).show();
                } else if (QuoreStart1.this.yearStr.length() != 4) {
                    Toast.makeText(QuoreStart1.this.getApplicationContext(), QuoreStart1.this.getResources().getString(R.string.incorrect_year_of_birth), 0).show();
                } else if (Integer.parseInt(QuoreStart1.this.yearStr) <= 1800) {
                    Toast.makeText(QuoreStart1.this.getApplicationContext(), QuoreStart1.this.getResources().getString(R.string.small_number_for_years), 0).show();
                } else if (QuoreStart1.this.heightStr.trim().isEmpty()) {
                    Toast.makeText(QuoreStart1.this.getApplicationContext(), QuoreStart1.this.getResources().getString(R.string.not_entered_height), 0).show();
                } else if (QuoreStart1.this.weightStr.trim().isEmpty()) {
                    Toast.makeText(QuoreStart1.this.getApplicationContext(), QuoreStart1.this.getResources().getString(R.string.not_entered_weight), 0).show();
                } else {
                    try {
                        Calendar cal1 = Calendar.getInstance(Locale.GERMANY);
                        QuoreStart1.this.height = Double.parseDouble(QuoreStart1.this.heightStr);
                        QuoreStart1.this.weight = Double.parseDouble(QuoreStart1.this.weightStr);
                        QuoreStart1.this.year = Integer.parseInt(QuoreStart1.this.yearStr);
                        QuoreStart1.this.year = cal1.get(1) - QuoreStart1.this.year;
                        if (QuoreStart1.this.unitsPosition == 0) {
                            QuoreStart1.this.units = "Metric";
                        } else {
                            QuoreStart1.this.units = "Imperial";
                        }
                        if (QuoreStart1.this.mHeight.equalsIgnoreCase("inch")) {
                            QuoreStart1.this.height = QuoreStart1.this.height * 2.54d;
                            QuoreStart1.this.height = (double) Math.round(QuoreStart1.this.height);
                        }
                        if (QuoreStart1.this.mWeight.equalsIgnoreCase("lb")) {
                            QuoreStart1.this.weight = QuoreStart1.this.weight * 0.453592d;
                            QuoreStart1.this.weight = (double) Math.round(QuoreStart1.this.weight);
                        }
                        String PREFS_NAME = "qA1sa2";
                        SharedPreferences qasa2 = QuoreStart1.this.getSharedPreferences("qA1sa2", 0);
                        qasa2.edit().putLong("visina", Double.doubleToRawLongBits(QuoreStart1.this.height)).apply();
                        qasa2.edit().putLong("tezina", Double.doubleToRawLongBits(QuoreStart1.this.weight)).apply();
                        qasa2.edit().putInt("godine", QuoreStart1.this.year).apply();
                        qasa2.edit().putInt("pol", QuoreStart1.this.gender).apply();
                        qasa2.edit().putString("units", QuoreStart1.this.units).apply();
                        qasa2.edit().putBoolean("my_first_time", false).apply();
                        QuoreStart1.this.startActivity(new Intent(QuoreStart1.this, SignIn.class));
                        QuoreStart1.this.finish();
                    } catch (Exception e)
                    {
                        Toast.makeText(QuoreStart1.this.getApplicationContext(), QuoreStart1.this.getResources().getString(R.string.Error_saving_data), 0).show();
                    }
                }
            }
        });
    }



    public void Banner(final RelativeLayout Ad_Layout, final Context context) {

        AdView mAdView = new AdView(context);
        mAdView.setAdSize(com.google.android.gms.ads.AdSize.LARGE_BANNER);
        mAdView.setAdUnitId(getString(R.string.ads_bnr));
        AdRequest adre = new AdRequest.Builder().build();
        mAdView.loadAd(adre);
        //Ad_Layout.addView(mAdView);

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
    }
}
