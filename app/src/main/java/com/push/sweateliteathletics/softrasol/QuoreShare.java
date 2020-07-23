package com.push.sweateliteathletics.softrasol;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Time;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class QuoreShare extends AppCompatActivity {
    private LinearLayout linLayShare1;
    private LinearLayout linLayShare2;
    private RelativeLayout relativeLayout;

    public static String PACKAGE_NAME;

    protected void onCreate(Bundle savedInstanceState) {
        String timeS;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        relativeLayout = findViewById(R.id.relLay);
        PACKAGE_NAME = getApplicationContext().getPackageName();
        Intent i = getIntent();
        String units = i.getStringExtra("units");
        String distance = i.getStringExtra("distance");
        double duration = i.getDoubleExtra("duration", 0.0d);
        String calories = i.getStringExtra("calories");
        String maxSpeed = i.getStringExtra("maxSpeed");
        String startTime = i.getStringExtra("startTime");
        String stopTime = i.getStringExtra("stopTime");
        String date = i.getStringExtra("date");
        String pace = i.getStringExtra("pace");
        String avgSpeed = i.getStringExtra("avgSpeed");
        byte[] byteArray = getIntent().getByteArrayExtra("Bitmap");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        Button btnShare1 = (Button) findViewById(R.id.btnShare1);
        Button btnShare2 = (Button) findViewById(R.id.btnShare2);
        this.linLayShare1 = (LinearLayout) findViewById(R.id.layoutShare1);
        this.linLayShare2 = (LinearLayout) findViewById(R.id.layoutShare2);
        FontTextView tvDistance1 = (FontTextView) findViewById(R.id.tvDistance);
        FontTextView tvDuration1 = (FontTextView) findViewById(R.id.tvDuration);
        FontTextView tvAvgSpeed1 = (FontTextView) findViewById(R.id.tvAvgSpeed1);
        FontTextView tvDate1 = (FontTextView) findViewById(R.id.tvDate1);
        FontTextView tvDistanceUnit1 = (FontTextView) findViewById(R.id.tvDistanceUnit1);
        FontTextView tvAvgSpeedUnit1 = (FontTextView) findViewById(R.id.tvAvgSpeedUnit1);
        FontTextView tvDistance2 = (FontTextView) findViewById(R.id.tvDistance2);
        FontTextView tvDuration2 = (FontTextView) findViewById(R.id.tvDuration2);
        FontTextView tvAvgSpeed2 = (FontTextView) findViewById(R.id.tvAvgSpeed2);
        FontTextView tvCalories = (FontTextView) findViewById(R.id.tvCalories);
        FontTextView tvMaxSpeed = (FontTextView) findViewById(R.id.tvMaxSpeed);
        FontTextView tvPace = (FontTextView) findViewById(R.id.tvPace);
        FontTextView tvDate2 = (FontTextView) findViewById(R.id.tvDate2);
        FontTextView tvDistanceUnit2 = (FontTextView) findViewById(R.id.tvDistanceUnit2);
        FontTextView tvAvgSpeedUnit2 = (FontTextView) findViewById(R.id.tvAvgSpeedUnit2);
        FontTextView tvMaxSpeedUnit = (FontTextView) findViewById(R.id.tvMaxSpeedUnit);
        FontTextView tvPaceUnit = (FontTextView) findViewById(R.id.tvPaceUnit);
        ((ImageView) findViewById(R.id.mapImg)).setImageBitmap(bitmap);


        long minutes = TimeUnit.MILLISECONDS.toMinutes((long) duration);
        long seconds = (long)((duration / 1000) % 60);


        if (minutes < 10 && seconds < 10) {
            timeS = "0" + minutes + ":0" + seconds;
        } else if (minutes < 10) {
            timeS = "0" + minutes + ":" + seconds;
        } else if (seconds < 10) {
            timeS = minutes + ":0" + seconds;
        } else {
            timeS = minutes + ":" + seconds;
        }

        if (seconds>=duration){
            tvDuration2.setText(timeS);
        }else {
            tvDuration2.setText(timeS);

        }
        tvDuration1.setText(timeS);
        tvDate1.setText(date);
        tvDate2.setText(date);
        tvCalories.setText(calories);
        if (units.equalsIgnoreCase("Metric")) {
            tvDistance1.setText(distance);
            tvAvgSpeed1.setText(avgSpeed);
            tvDistance2.setText(distance);
            tvAvgSpeed2.setText(avgSpeed+ " avg");
            tvMaxSpeed.setText(maxSpeed+" max");
            tvPace.setText(pace + " pace");
            tvPaceUnit.setText(getResources().getString(R.string.min) + "/" + getResources().getString(R.string.km));
        } else {
            tvDistance1.setText(String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.valueOf(distance).doubleValue() * 0.621371d)}));
            tvAvgSpeed1.setText(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(Double.valueOf(avgSpeed).doubleValue() * 0.621371d)}));
            tvDistanceUnit1.setText(getResources().getString(R.string.mi));
            tvAvgSpeedUnit1.setText(getResources().getString(R.string.mph));
            tvDistance2.setText(String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.valueOf(distance).doubleValue() * 0.621371d)}));
            tvAvgSpeed2.setText(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(Double.valueOf(avgSpeed).doubleValue() * 0.621371d)}));
            tvMaxSpeed.setText(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(Double.valueOf(maxSpeed).doubleValue() * 0.621371d)}));
            tvPace.setText(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(Double.valueOf(pace).doubleValue() / 0.621371d)}));
            tvDistanceUnit2.setText(getResources().getString(R.string.mi));
            tvAvgSpeedUnit2.setText(getResources().getString(R.string.mph));
            tvMaxSpeedUnit.setText(getResources().getString(R.string.mph));
            tvPaceUnit.setText(getResources().getString(R.string.min) + "/" + getResources().getString(R.string.mi));
        }
        btnShare1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(QuoreShare.this, "Screenshot Taken", Toast.LENGTH_SHORT).show();
                QuoreShare.this.shareImage(QuoreShare.this.takeScreenShot(QuoreShare.this.linLayShare1));
            }
        });
        btnShare2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreShare.this.shareImage(QuoreShare.this.takeScreenShot(QuoreShare.this.relativeLayout));
            }
        });
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
            Uri uri = FileProvider.getUriForFile(QuoreShare.this, BuildConfig.APPLICATION_ID + ".provider", file);
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
}
