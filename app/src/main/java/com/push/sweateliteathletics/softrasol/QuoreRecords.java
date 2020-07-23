package com.push.sweateliteathletics.softrasol;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import androidx.fragment.app.Fragment;


public class QuoreRecords extends Fragment {
    private Chronometer cDuration;
    private double maxCalories;
    private double maxDistance;
    private double maxDuration;
    private double maxPace;
    private double maxSpeed;
    private SharedPreferences qasa2;
    private FontTextView tvCalories;
    private FontTextView tvDistance;
    private FontTextView tvPace;
    private FontTextView tvSpeed;
    private String units;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_records, container, false);
        this.cDuration = (Chronometer) rootView.findViewById(R.id.chronoRec);
        this.tvDistance = (FontTextView) rootView.findViewById(R.id.tvRecordDistance);
        this.tvSpeed = (FontTextView) rootView.findViewById(R.id.tvRecordSpeed);
        this.tvPace = (FontTextView) rootView.findViewById(R.id.tvRecordPace);
        this.tvCalories = (FontTextView) rootView.findViewById(R.id.tvRecordCalories);
        String PREFS_NAME = "qA1sa2";
        this.qasa2 = getActivity().getSharedPreferences("qA1sa2", 0);
        readData();
        return rootView;
    }

    private void readData() {
        this.maxDuration = Double.longBitsToDouble(this.qasa2.getLong("rekordTrajanje", Double.doubleToLongBits(0.0d)));
        this.maxDistance = Double.longBitsToDouble(this.qasa2.getLong("rekordRastojanje", Double.doubleToLongBits(0.0d)));
        this.maxSpeed = Double.longBitsToDouble(this.qasa2.getLong("rekordBrzina", Double.doubleToLongBits(0.0d)));
        this.maxPace = Double.longBitsToDouble(this.qasa2.getLong("rekordRitamRas", Double.doubleToLongBits(0.0d)));
        this.maxCalories = Double.longBitsToDouble(this.qasa2.getLong("rekordKalorije", Double.doubleToLongBits(0.0d)));
    }

    private void showData() {
        if (this.maxDuration < 3600000.0d) {
            this.cDuration.setFormat("00:%s");
        }
        this.cDuration.setBase(SystemClock.elapsedRealtime() - ((long) this.maxDuration));
        this.tvCalories.setText(String.format("%.1f", new Object[]{Double.valueOf(this.maxCalories)}) + " " + getResources().getString(R.string.kcal));
        if (this.units.equalsIgnoreCase("Metric")) {
            this.tvDistance.setText(String.format("%.3f", new Object[]{Double.valueOf(this.maxDistance)}) + " " + getResources().getString(R.string.km));
            this.tvSpeed.setText(String.format("%.2f", new Object[]{Double.valueOf(this.maxSpeed)}) + " " + getResources().getString(R.string.kph));
            this.tvPace.setText(String.format("%.0f", new Object[]{Double.valueOf(this.maxPace)}) + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.km));
            return;
        }
        this.tvDistance.setText(String.format("%.3f", new Object[]{Double.valueOf(this.maxDistance * 0.621371d)}) + " " + getResources().getString(R.string.mi));
        this.tvSpeed.setText(String.format("%.2f", new Object[]{Double.valueOf(this.maxSpeed * 0.621371d)}) + " " + getResources().getString(R.string.mph));
        this.tvPace.setText(String.format("%.0f", new Object[]{Double.valueOf(this.maxPace * 1.609344d)}) + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.mi));
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onResume() {
        super.onResume();
        this.units = this.qasa2.getString("units", "Metric");
        showData();
    }
}
