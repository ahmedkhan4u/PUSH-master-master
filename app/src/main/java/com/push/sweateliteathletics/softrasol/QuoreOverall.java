package com.push.sweateliteathletics.softrasol;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

public class QuoreOverall extends Fragment {
    private Chronometer cDuration;
    private double overallCalories;
    private double overallDistance;
    private double overallDuration;
    private double overallPace;
    private SharedPreferences qasa2;
    private FontTextView tvOverallCalories;
    private FontTextView tvOverallDistance;
    private FontTextView tvOverallPace;
    private String units;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_overall, container, false);
        this.cDuration = (Chronometer) rootView.findViewById(R.id.chronoOverall);
        this.tvOverallDistance = (FontTextView) rootView.findViewById(R.id.tvOverallDistance);
        this.tvOverallCalories = (FontTextView) rootView.findViewById(R.id.tvOverallCalories);
        this.tvOverallPace = (FontTextView) rootView.findViewById(R.id.tvOverallPace);
        String PREFS_NAME = "qA1sa2";
        this.qasa2 = getActivity().getSharedPreferences("qA1sa2", 0);
        readData();
        return rootView;
    }

    public void onResume() {
        super.onResume();
        this.units = this.qasa2.getString("units", "Metric");
        showData();
    }

    private void readData() {
        this.overallDuration = Double.longBitsToDouble(this.qasa2.getLong("ukupnoTrajanje", Double.doubleToLongBits(0.0d)));
        this.overallDistance = Double.longBitsToDouble(this.qasa2.getLong("ukupnoRastojanje", Double.doubleToLongBits(0.0d)));
        this.overallCalories = Double.longBitsToDouble(this.qasa2.getLong("ukupnoKalorije", Double.doubleToLongBits(0.0d)));
    }

    private void showData() {
        double overallDurationInMinutes = this.overallDuration / 60000.0d;
        if (this.units.equalsIgnoreCase("Metric")) {
            if (this.overallDistance > 0.0d) {
                this.overallPace = overallDurationInMinutes / this.overallDistance;
            } else {
                this.overallPace = 0.0d;
            }
            this.tvOverallDistance.setText(String.format("%.3f", new Object[]{Double.valueOf(this.overallDistance)}) + " " + getResources().getString(R.string.km));
            this.tvOverallPace.setText(String.format("%.1f", new Object[]{Double.valueOf(this.overallPace)}) + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.km));
        } else {
            if (this.overallDistance > 0.0d) {
                this.overallPace = overallDurationInMinutes / (this.overallDistance * 0.621371d);
            } else {
                this.overallPace = 0.0d;
            }
            this.tvOverallDistance.setText(String.format("%.3f", new Object[]{Double.valueOf(this.overallDistance * 0.621371d)}) + " " + getResources().getString(R.string.mi));
            this.tvOverallPace.setText(String.format("%.1f", new Object[]{Double.valueOf(this.overallPace)}) + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.mi));
        }
        if (this.overallDuration < 3600000.0d) {
            this.cDuration.setFormat("00:%s");
        }
        this.cDuration.setBase(SystemClock.elapsedRealtime() - ((long) this.overallDuration));
        this.tvOverallCalories.setText(String.format("%.1f", new Object[]{Double.valueOf(this.overallCalories)}) + " " + getResources().getString(R.string.kcal));
    }
}
