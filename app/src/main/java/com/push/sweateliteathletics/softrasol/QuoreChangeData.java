package com.push.sweateliteathletics.softrasol;

import android.content.Context;
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
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.Calendar;
import java.util.Locale;

public class QuoreChangeData extends AppCompatActivity {
    private ArrayAdapter<CharSequence> adapterP;
    private ArrayAdapter<CharSequence> adapterT;
    private ArrayAdapter<CharSequence> adapterV;
    private Button btnSave;
    private Calendar cal1;
    private EditText etHeight;
    private EditText etWeight;
    private EditText etYear;
    private int gender;
    private double height;
    private String heightStr;
    private String mHeight;
    private String mWeight;
    private SharedPreferences qasa2;
    private Spinner spinGender;
    private Spinner spinHeight;
    private Spinner spinWeight;
    private double weight;
    private String weightStr;
    private int year;
    private String yearStr;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_data);
        setTitle(R.string.app_name);


        RelativeLayout bnnr = (RelativeLayout) findViewById(R.id.bnr);
        Banner(bnnr, QuoreChangeData.this);

        this.qasa2 = getSharedPreferences("qA1sa2", 0);
        this.cal1 = Calendar.getInstance(Locale.GERMANY);
        this.spinHeight = (Spinner) findViewById(R.id.spHeight);
        this.spinWeight = (Spinner) findViewById(R.id.spWeight);
        this.spinGender = (Spinner) findViewById(R.id.spGender);
        this.etYear = (EditText) findViewById(R.id.etDate);
        this.etHeight = (EditText) findViewById(R.id.etHeight);
        this.etWeight = (EditText) findViewById(R.id.etWeight);
        this.btnSave = (Button) findViewById(R.id.btnSave);
        this.adapterT = ArrayAdapter.createFromResource(this, R.array.Weight, 17367048);
        this.adapterT.setDropDownViewResource(17367049);
        this.spinWeight.setAdapter(this.adapterT);
        this.adapterV = ArrayAdapter.createFromResource(this, R.array.Height, 17367048);
        this.adapterV.setDropDownViewResource(17367049);
        this.spinHeight.setAdapter(this.adapterV);
        this.adapterP = ArrayAdapter.createFromResource(this, R.array.Gender, 17367048);
        this.adapterP.setDropDownViewResource(17367049);
        this.spinGender.setAdapter(this.adapterP);
        this.etHeight.setText(BuildConfig.FLAVOR + Double.longBitsToDouble(this.qasa2.getLong("visina", Double.doubleToLongBits(175.0d))));
        this.etWeight.setText(BuildConfig.FLAVOR + Double.longBitsToDouble(this.qasa2.getLong("tezina", Double.doubleToLongBits(50.0d))));
        this.etYear.setText(BuildConfig.FLAVOR + (this.cal1.get(1) - this.qasa2.getInt("godine", 25)));
        this.spinGender.setSelection(this.qasa2.getInt("pol", 0));
        this.btnSave.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreChangeData.this.yearStr = QuoreChangeData.this.etYear.getText().toString();
                QuoreChangeData.this.weightStr = QuoreChangeData.this.etWeight.getText().toString();
                QuoreChangeData.this.heightStr = QuoreChangeData.this.etHeight.getText().toString();
                QuoreChangeData.this.mHeight = QuoreChangeData.this.spinHeight.getSelectedItem().toString();
                QuoreChangeData.this.mWeight = QuoreChangeData.this.spinWeight.getSelectedItem().toString();
                QuoreChangeData.this.gender = QuoreChangeData.this.spinGender.getSelectedItemPosition();
                if (QuoreChangeData.this.yearStr.trim().isEmpty()) {
                    Toast.makeText(QuoreChangeData.this.getApplicationContext(), QuoreChangeData.this.getResources().getString(R.string.not_entered_year_of_birth), 0).show();
                } else if (QuoreChangeData.this.yearStr.length() != 4) {
                    Toast.makeText(QuoreChangeData.this.getApplicationContext(), QuoreChangeData.this.getResources().getString(R.string.incorrect_year_of_birth), 0).show();
                } else if (Integer.parseInt(QuoreChangeData.this.yearStr) <= 1800) {
                    Toast.makeText(QuoreChangeData.this.getApplicationContext(), QuoreChangeData.this.getResources().getString(R.string.small_number_for_years), 0).show();
                } else if (QuoreChangeData.this.heightStr.trim().isEmpty()) {
                    Toast.makeText(QuoreChangeData.this.getApplicationContext(), QuoreChangeData.this.getResources().getString(R.string.not_entered_height), 0).show();
                } else if (QuoreChangeData.this.weightStr.trim().isEmpty()) {
                    Toast.makeText(QuoreChangeData.this.getApplicationContext(), QuoreChangeData.this.getResources().getString(R.string.not_entered_weight), 0).show();
                } else {
                    try {
                        QuoreChangeData.this.height = Double.parseDouble(QuoreChangeData.this.heightStr);
                        QuoreChangeData.this.weight = Double.parseDouble(QuoreChangeData.this.weightStr);
                        QuoreChangeData.this.year = Integer.parseInt(QuoreChangeData.this.yearStr);
                        QuoreChangeData.this.year = QuoreChangeData.this.cal1.get(1) - QuoreChangeData.this.year;
                        if (QuoreChangeData.this.mHeight.equalsIgnoreCase("inch")) {
                            QuoreChangeData.this.height = QuoreChangeData.this.height * 2.54d;
                            QuoreChangeData.this.height = (double) Math.round(QuoreChangeData.this.height);
                        }
                        if (QuoreChangeData.this.mWeight.equalsIgnoreCase("lb")) {
                            QuoreChangeData.this.weight = QuoreChangeData.this.weight * 0.453592d;
                            QuoreChangeData.this.weight = (double) Math.round(QuoreChangeData.this.weight);
                        }
                        QuoreChangeData.this.qasa2.edit().putLong("visina", Double.doubleToRawLongBits(QuoreChangeData.this.height)).apply();
                        QuoreChangeData.this.qasa2.edit().putLong("tezina", Double.doubleToRawLongBits(QuoreChangeData.this.weight)).apply();
                        QuoreChangeData.this.qasa2.edit().putInt("godine", QuoreChangeData.this.year).apply();
                        QuoreChangeData.this.qasa2.edit().putInt("pol", QuoreChangeData.this.gender).apply();
                        QuoreChangeData.this.finish();
                    } catch (Exception e) {
                        Toast.makeText(QuoreChangeData.this.getApplicationContext(), QuoreChangeData.this.getResources().getString(R.string.Error_saving_data), 0).show();
                    }
                }
            }
        });
    }

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
    }
}
