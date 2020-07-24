package com.push.sweateliteathletics.softrasol;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import androidx.core.app.NavUtils;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class QuoreReport extends AppCompatActivity implements OnMapReadyCallback {
    private double avgSpeed = 0.0d;
    final SnapshotReadyCallback callback = new SnapshotReadyCallback() {
        public void onSnapshotReady(Bitmap snapshot) {
            Intent a = new Intent(QuoreReport.this, QuoreShare.class);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            snapshot.compress(CompressFormat.PNG, 100, stream);
            a.putExtra("Bitmap", stream.toByteArray());
            a.putExtra("units", QuoreReport.this.units);
            a.putExtra("distance", QuoreReport.this.distance);
            a.putExtra("duration", QuoreReport.this.duration);
            a.putExtra("calories", QuoreReport.this.calories);
            a.putExtra("maxSpeed", QuoreReport.this.maxSpeed);
            a.putExtra("startTime", QuoreReport.this.startTime);
            a.putExtra("stopTime", QuoreReport.this.stopTime);
            a.putExtra("date", QuoreReport.this.date);
            a.putExtra("pace", String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(QuoreReport.this.pace)}));
            a.putExtra("avgSpeed", String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(QuoreReport.this.avgSpeed)}));
            QuoreReport.this.startActivity(a);
        }
    };
    private String calories;
    private String date;
    private String distance;
    private double duration;
    private double highSpeed;
    private ImageButton imbZoom;
    private boolean isZoomed = false;
    private ArrayList<LatLng> latLonArray;
    private LinearLayout linLay1;
    private double lowSpeed;
    private GoogleMap mMapRep;
    private String maxSpeed;
    private double mediumSpeed;
    private double pace = 0.0d;
    private RelativeLayout relMap;
    private String startTime;
    private String stopTime;
    private String units;

    private LinearLayout linearLayout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        setTitle(R.string.app_name);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        linearLayout = findViewById(R.id.linearLayout);
        RelativeLayout bnnr = (RelativeLayout) findViewById(R.id.bnr);
        //fb_baner(bnnr, QuoreReport.this);


        this.linLay1 = (LinearLayout) findViewById(R.id.LayReport1);
        this.relMap = (RelativeLayout) findViewById(R.id.relMap);
        this.imbZoom = (ImageButton) findViewById(R.id.imbZoom);
        String PREFS_NAME = "qA1sa2";
        SharedPreferences qasa2 = getSharedPreferences("qA1sa2", 0);
        Intent i = getIntent();
        this.duration = i.getDoubleExtra("duration", 0.0d);
        this.distance = i.getStringExtra("distance");
        this.calories = i.getStringExtra("calories");
        this.maxSpeed = i.getStringExtra("maxSpeed");
        String lowSpeedStr = i.getStringExtra("lowSpeed");
        String mediumSpeedStr = i.getStringExtra("mediumSpeed");
        String highSpeedStr = i.getStringExtra("highSpeed");
        this.units = i.getStringExtra("units");
        this.startTime = i.getStringExtra("startTime");
        this.stopTime = i.getStringExtra("stopTime");
        this.date = i.getStringExtra("date");
        this.latLonArray = (ArrayList) new Gson().fromJson(qasa2.getString(i.getStringExtra("arrayString"), BuildConfig.FLAVOR), new TypeToken<ArrayList<LatLng>>() {
        }.getType());
        try {
            ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapRep)).getMapAsync(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int lowSpeedInt = Integer.parseInt(lowSpeedStr);
        int mediumSpeedInt = Integer.parseInt(mediumSpeedStr);
        int HighSpeedInt = Integer.parseInt(highSpeedStr);
        if (lowSpeedInt > 0 || mediumSpeedInt > 0 || HighSpeedInt > 0) {
            this.lowSpeed = (((double) lowSpeedInt) * 100.0d) / ((double) ((lowSpeedInt + mediumSpeedInt) + HighSpeedInt));
            this.highSpeed = (((double) HighSpeedInt) * 100.0d) / ((double) ((lowSpeedInt + mediumSpeedInt) + HighSpeedInt));
            try {
                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                this.mediumSpeed = (100.0d - Double.valueOf(decimalFormat.format(this.lowSpeed)).doubleValue()) - Double.valueOf(decimalFormat.format(this.highSpeed)).doubleValue();
            } catch (NumberFormatException e2) {
                this.mediumSpeed = (100.0d - this.lowSpeed) - this.highSpeed;
            }
        } else {
            this.lowSpeed = 0.0d;
            this.mediumSpeed = 0.0d;
            this.highSpeed = 0.0d;
        }
        FontTextView timeTitleTv = (FontTextView) findViewById(R.id.tvDurationTitle);
        Chronometer cDuration = (Chronometer) findViewById(R.id.chronoReport);
        FontTextView distanceTv = (FontTextView) findViewById(R.id.tvDistance);
        FontTextView caloriesTv = (FontTextView) findViewById(R.id.tvCalories);
        FontTextView paceTv = (FontTextView) findViewById(R.id.tvPace);
        FontTextView maxSpeedTv = (FontTextView) findViewById(R.id.tvMaxSpeed);
        FontTextView avgSpeedTv = (FontTextView) findViewById(R.id.tvAvgSpeed);
        FontTextView procLowSpeed = (FontTextView) findViewById(R.id.tvLowSpeedProc);
        FontTextView procMediumSpeed = (FontTextView) findViewById(R.id.tvMediumSpeedProc);
        FontTextView procHighSpeed = (FontTextView) findViewById(R.id.tvHighSpeedProc);
        FontTextView lowSpeedTitle = (FontTextView) findViewById(R.id.tvLowSpeed);
        FontTextView mediumSpeedTitle = (FontTextView) findViewById(R.id.tvMediumSpeed);
        FontTextView highSpeedTitle = (FontTextView) findViewById(R.id.tvHighSpeed);
        double durationMinutes = this.duration / 60000.0d;
        this.pace = 0.0d;
        if (durationMinutes > 0.0d) {
            try {
                if (Double.parseDouble(this.distance) > 0.0d) {
                    this.pace = durationMinutes / Double.parseDouble(this.distance);
                }
            } catch (Exception e3) {
                this.pace = 0.0d;
            }
        }
        this.avgSpeed = 0.0d;
        if (durationMinutes > 0.0d) {
            try {
                if (Double.parseDouble(this.distance) > 0.0d) {
                    this.avgSpeed = (Double.parseDouble(this.distance) * 60.0d) / durationMinutes;
                }
            } catch (Exception e4) {
                this.avgSpeed = 0.0d;
            }
        }
        if (this.avgSpeed > Double.parseDouble(this.maxSpeed)) {
            this.avgSpeed = Double.parseDouble(this.maxSpeed);
        }
        procLowSpeed.setText(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(this.lowSpeed)}) + "%");
        procMediumSpeed.setText(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(this.mediumSpeed)}) + "%");
        procHighSpeed.setText(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(this.highSpeed)}) + "%");
        timeTitleTv.setText(this.startTime + getResources().getString(R.string.h) + " - " + this.stopTime + getResources().getString(R.string.h) + "   " + this.date);
        if (this.duration < 3600000.0d) {
            cDuration.setFormat("00:%s");
        }
        cDuration.setBase(SystemClock.elapsedRealtime() - ((long) this.duration));
        caloriesTv.setText(this.calories + " " + getResources().getString(R.string.kcal));
        if (this.units.equalsIgnoreCase("Metric")) {
            distanceTv.setText(this.distance + " " + getResources().getString(R.string.km));
            paceTv.setText(String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(this.pace)}) + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.km));
            maxSpeedTv.setText(this.maxSpeed + " " + getResources().getString(R.string.kph));
            avgSpeedTv.setText(String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(this.avgSpeed)}) + " " + getResources().getString(R.string.kph));
        } else {
            lowSpeedTitle.setText(getResources().getString(R.string.speed_lower_than_7_mph));
            mediumSpeedTitle.setText(getResources().getString(R.string.speed_between_7_mph_and_16_mph));
            highSpeedTitle.setText(getResources().getString(R.string.speed_higher_than_16_mph));
            distanceTv.setText(String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.valueOf(this.distance).doubleValue() * 0.621371d)}) + " " + getResources().getString(R.string.mi));
            try {
                if (this.maxSpeed.equalsIgnoreCase("0")) {
                    maxSpeedTv.setText("0 " + getResources().getString(R.string.mph));
                } else {
                    maxSpeedTv.setText(String.format(Locale.US, "%.3f", new Object[]{Double.valueOf(Double.valueOf(this.maxSpeed).doubleValue() * 0.621371d)}) + " " + getResources().getString(R.string.mph));
                }
            } catch (Exception e5) {
                maxSpeedTv.setText("0 " + getResources().getString(R.string.mph));
            }
            paceTv.setText(String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(this.pace * 1.609344d)}) + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.mi));
            avgSpeedTv.setText(String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(this.avgSpeed * 0.621371d)}) + " " + getResources().getString(R.string.mph));
        }
        QuoreGlavna.report = true;
        this.imbZoom.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                LayoutParams p = new LayoutParams(-1, -1);
                if (QuoreReport.this.isZoomed) {
                    p.weight = 1.0f;
                    QuoreReport.this.linLay1.setLayoutParams(p);
                    p.weight = 1.0f;
                    QuoreReport.this.relMap.setLayoutParams(p);
                    QuoreReport.this.imbZoom.setBackgroundResource(R.drawable.ic_zoom_in);
                    QuoreReport.this.isZoomed = false;
                    return;
                }
                p.weight = 0.0f;
                QuoreReport.this.linLay1.setLayoutParams(p);
                p.weight = 0.0f;
                QuoreReport.this.relMap.setLayoutParams(p);
                QuoreReport.this.imbZoom.setBackgroundResource(R.drawable.ic_zoom_out);
                QuoreReport.this.isZoomed = true;
            }
        });
    }

    public void onMapReady(GoogleMap googleMapRep) {
        this.mMapRep = googleMapRep;
        if (this.latLonArray != null && this.latLonArray.size() > 0) {
            PolylineOptions line = new PolylineOptions();
            Builder boundsBuilder = new Builder();
            for (int i = 0; i < this.latLonArray.size() - 2; i++) {
                line.add(new LatLng[]{(LatLng) this.latLonArray.get(i), (LatLng) this.latLonArray.get(i + 1)}).width(15.0f).color(-16776961);
                if (i % 20 == 0) {
                    boundsBuilder.include((LatLng) this.latLonArray.get(i));
                }
            }
            this.mMapRep.addPolyline(line).setZIndex(1.0f);
            this.mMapRep.setMaxZoomPreference(18.0f);
            this.mMapRep.addCircle(new CircleOptions().center((LatLng) this.latLonArray.get(0)).strokeColor(Color.argb(255, 255, 255, 255)).radius(12.0d).fillColor(-16711936)).setZIndex(2.0f);
            this.mMapRep.addCircle(new CircleOptions().center((LatLng) this.latLonArray.get(this.latLonArray.size() - 1)).radius(12.0d).strokeColor(Color.argb(255, 255, 255, 255)).fillColor(-65536)).setZIndex(3.0f);
            boundsBuilder.include((LatLng) this.latLonArray.get(this.latLonArray.size() - 1));
            final LatLngBounds latLngBounds = boundsBuilder.build();
            new CountDownTimer(200, 200) {
                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    try {
                        QuoreReport.this.mMapRep.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 100));
                    } catch (Exception e) {
                        QuoreReport.this.mMapRep.moveCamera(CameraUpdateFactory.newLatLng((LatLng) QuoreReport.this.latLonArray.get(QuoreReport.this.latLonArray.size() / 2)));
                        QuoreReport.this.mMapRep.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
                    }
                }
            }.start();
        }
    }

    public void onResume() {
        super.onResume();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.report_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case 16908332:
                NavUtils.navigateUpFromSameTask(this);
                break;
            case R.id.share_report /*2131231080*/:
                if (this.mMapRep != null)
                {
                    //this.mMapRep.snapshot(this.callback);
                    QuoreReport.this.shareImage(QuoreReport.this.takeScreenShot(QuoreReport.this.linLay1));
                    break;
                }
                break;
        }
        return true;
    }

    private void shareImage(Bitmap bitmap) {
        try {
            File file = new File(getExternalCacheDir(), "Zeopoxa.png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, true);

            //Uri uri = FileProvider.getUriForFile(this, "com.push.sweateliteathletics.softrasol.provider", file);

            //File file = new File(path);
            Uri uri = FileProvider.getUriForFile(QuoreReport.this, BuildConfig.APPLICATION_ID + ".provider", file);
            Log.d("U: " , "Here is the URI " + file);

            Intent intent = new Intent("android.intent.action.SEND");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.setType("image/png");
            startActivity(Intent.createChooser(intent, "PUSH image via"));
        } catch (Exception e) {
            Log.d("Exp: " ,"Uri Exp: " + e.getMessage());
            //e.printStackTrace();
        }
    }

    private Bitmap takeScreenShot(View v) {
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache();
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return b;
    }



//    public void Banner(final RelativeLayout Ad_Layout, final Context context) {
//
//        AdView mAdView = new AdView(context);
//        mAdView.setAdSize(com.google.android.gms.ads.AdSize.SMART_BANNER);
//        mAdView.setAdUnitId(getString(R.string.ads_bnr));
//        AdRequest adre = new AdRequest.Builder().build();
//        mAdView.loadAd(adre);
//        Ad_Layout.addView(mAdView);
//
//        mAdView.setAdListener(new AdListener() {
//
//            @Override
//            public void onAdLoaded() {
//                // TODO Auto-generated method stub
//                Ad_Layout.setVisibility(View.VISIBLE);
//                super.onAdLoaded();
//            }
//
//            @Override
//            public void onAdFailedToLoad(int errorCode) {
//                // TODO Auto-generated method stub
//
//                fb_baner(Ad_Layout, context);
//            }
//        });
//    }
//
//
//    public void fb_baner(final RelativeLayout ad_layout, final Context context) {
//
//        try {
//            com.facebook.ads.AdView adView = new com.facebook.ads.AdView(context, getString(R.string.fb_bnr),
//                    AdSize.BANNER_HEIGHT_50);
//
//            ad_layout.addView(adView);
//            adView.setAdListener(new com.facebook.ads.AdListener() {
//                @Override
//                public void onError(Ad ad, AdError adError) {
//                    Banner(ad_layout, context);
//                }
//
//                @Override
//                public void onAdLoaded(Ad ad) {
//                    ad_layout.setVisibility(View.VISIBLE);
//                }
//
//                @Override
//                public void onAdClicked(Ad ad) {
//
//                }
//
//                @Override
//                public void onLoggingImpression(Ad ad) {
//
//                }
//            });
//            adView.loadAd();
//
//
//        } catch (Exception e) {
//        }
//    }
}
