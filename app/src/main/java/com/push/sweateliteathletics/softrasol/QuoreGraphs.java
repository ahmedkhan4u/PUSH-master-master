package com.push.sweateliteathletics.softrasol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.Locale;

public class QuoreGraphs extends Fragment {
    private Button btnAdvancedCharts;
    private BarChart chart;
    private Context context;
    private BarData data;
    private ArrayList<IBarDataSet> dataSetsWeekCalories;
    private ArrayList<IBarDataSet> dataSetsWeekDistance;
    private ArrayList<IBarDataSet> dataSetsWeekDuration;
    private int dataType = 1;
    private ImageButton imCalories;
    private ImageButton imDistance;
    private ImageButton imDuration;
    private ArrayList<String> labelsWeek;
    private SharedPreferences qasa2;
    private FontTextView title;
    private String units = "Metric";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_graphs, container, false);
        this.context = getActivity();
        String PREFS_NAME = "qA1sa2";
        this.qasa2 = getActivity().getSharedPreferences("qA1sa2", 0);
        this.imCalories = (ImageButton) rootView.findViewById(R.id.imbAdvancedCalories);
        this.imDuration = (ImageButton) rootView.findViewById(R.id.imbAdvancedDuration);
        this.imDistance = (ImageButton) rootView.findViewById(R.id.imbAdvancedDistance);
        this.btnAdvancedCharts = (Button) rootView.findViewById(R.id.btnAdvanced);
        this.chart = (BarChart) rootView.findViewById(R.id.chart);
        this.title = (FontTextView) rootView.findViewById(R.id.tvAdvGraphTitle);
        XAxis xAxis = this.chart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setSpaceBetweenLabels(0);
        xAxis.setDrawGridLines(false);
        this.btnAdvancedCharts.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreGraphs.this.startActivity(new Intent(QuoreGraphs.this.context, QuoreAdvancedGraphs.class));
            }
        });
        this.imCalories.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreGraphs.this.dataType = 1;
//                QuoreGraphs.this.imCalories.setBackgroundResource(R.drawable.green_button);
//                QuoreGraphs.this.imDistance.setBackgroundResource(R.drawable.yellow_button);
//                QuoreGraphs.this.imDuration.setBackgroundResource(R.drawable.yellow_button);
                QuoreGraphs.this.drawGraph();
            }
        });
        this.imDuration.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreGraphs.this.dataType = 3;
//                QuoreGraphs.this.imCalories.setBackgroundResource(R.drawable.yellow_button);
//                QuoreGraphs.this.imDistance.setBackgroundResource(R.drawable.yellow_button);
//                QuoreGraphs.this.imDuration.setBackgroundResource(R.drawable.green_button);
                QuoreGraphs.this.drawGraph();
            }
        });
        this.imDistance.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreGraphs.this.dataType = 2;
//                QuoreGraphs.this.imCalories.setBackgroundResource(R.drawable.yellow_button);
//                QuoreGraphs.this.imDistance.setBackgroundResource(R.drawable.green_button);
//                QuoreGraphs.this.imDuration.setBackgroundResource(R.drawable.yellow_button);
                QuoreGraphs.this.drawGraph();
            }
        });
        return rootView;
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
        readDataWeek();
        drawGraph();
    }

    private void drawGraph() {
        if (this.dataType == 1) {
            this.title.setText(getResources().getString(R.string.Calories) + ": " + getResources().getString(R.string.kcal));
        } else if (this.dataType == 3) {
            this.title.setText(getResources().getString(R.string.Time) + ": " + getResources().getString(R.string.min));
        } else if (this.dataType == 2) {
            if (this.units.equalsIgnoreCase("Imperial")) {
                this.title.setText(getResources().getString(R.string.Distance) + ": " + getResources().getString(R.string.mi));
            } else {
                this.title.setText(getResources().getString(R.string.Distance) + ": " + getResources().getString(R.string.km));
            }
        }
        drawGraphForWeek();
        this.chart.setData(this.data);
        this.chart.setDrawBarShadow(false);
        this.chart.setDescription(BuildConfig.FLAVOR);
        this.chart.setMaxVisibleValueCount(60);
        this.chart.setPinchZoom(false);
        this.chart.setDoubleTapToZoomEnabled(false);
        this.chart.setDragEnabled(false);
        this.chart.setDrawGridBackground(false);
        this.chart.animateXY(1200, 1200);
        this.chart.getAxisRight().setDrawLabels(false);
        this.chart.invalidate();
    }

    private void drawGraphForWeek() {
        if (this.dataType == 1) {
            this.data = new BarData(this.labelsWeek, this.dataSetsWeekCalories);
        } else if (this.dataType == 2) {
            this.data = new BarData(this.labelsWeek, this.dataSetsWeekDistance);
        } else if (this.dataType == 3) {
            this.data = new BarData(this.labelsWeek, this.dataSetsWeekDuration);
        }
    }

    private void readDataWeek() {
        this.labelsWeek = new ArrayList();
        this.labelsWeek.add(getResources().getString(R.string.mon));
        this.labelsWeek.add(getResources().getString(R.string.tue));
        this.labelsWeek.add(getResources().getString(R.string.wen));
        this.labelsWeek.add(getResources().getString(R.string.thu));
        this.labelsWeek.add(getResources().getString(R.string.fri));
        this.labelsWeek.add(getResources().getString(R.string.sat));
        this.labelsWeek.add(getResources().getString(R.string.sun));
        double monDuration = Double.longBitsToDouble(this.qasa2.getLong("ponVreme", Double.doubleToLongBits(0.0d)));
        double monCalories = Double.longBitsToDouble(this.qasa2.getLong("ponKalorije", Double.doubleToLongBits(0.0d)));
        double monDistance = Double.longBitsToDouble(this.qasa2.getLong("ponRastojanje", Double.doubleToLongBits(0.0d)));
        double tueDuration = Double.longBitsToDouble(this.qasa2.getLong("utoVreme", Double.doubleToLongBits(0.0d)));
        double tueCalories = Double.longBitsToDouble(this.qasa2.getLong("utoKalorije", Double.doubleToLongBits(0.0d)));
        double tueDistance = Double.longBitsToDouble(this.qasa2.getLong("utoRastojanje", Double.doubleToLongBits(0.0d)));
        double wenDuration = Double.longBitsToDouble(this.qasa2.getLong("sreVreme", Double.doubleToLongBits(0.0d)));
        double wenCalories = Double.longBitsToDouble(this.qasa2.getLong("sreKalorije", Double.doubleToLongBits(0.0d)));
        double wenDistance = Double.longBitsToDouble(this.qasa2.getLong("sreRastojanje", Double.doubleToLongBits(0.0d)));
        double thuDuration = Double.longBitsToDouble(this.qasa2.getLong("cetVreme", Double.doubleToLongBits(0.0d)));
        double thuCalories = Double.longBitsToDouble(this.qasa2.getLong("cetKalorije", Double.doubleToLongBits(0.0d)));
        double thuDistance = Double.longBitsToDouble(this.qasa2.getLong("cetRastojanje", Double.doubleToLongBits(0.0d)));
        double friDuration = Double.longBitsToDouble(this.qasa2.getLong("petVreme", Double.doubleToLongBits(0.0d)));
        double friCalories = Double.longBitsToDouble(this.qasa2.getLong("petKalorije", Double.doubleToLongBits(0.0d)));
        double friDistance = Double.longBitsToDouble(this.qasa2.getLong("petRastojanje", Double.doubleToLongBits(0.0d)));
        double satDuration = Double.longBitsToDouble(this.qasa2.getLong("subVreme", Double.doubleToLongBits(0.0d)));
        double satCalories = Double.longBitsToDouble(this.qasa2.getLong("subKalorije", Double.doubleToLongBits(0.0d)));
        double satDistance = Double.longBitsToDouble(this.qasa2.getLong("subRastojanje", Double.doubleToLongBits(0.0d)));
        double sunDuration = Double.longBitsToDouble(this.qasa2.getLong("nedVreme", Double.doubleToLongBits(0.0d)));
        double sunCalories = Double.longBitsToDouble(this.qasa2.getLong("nedKalorije", Double.doubleToLongBits(0.0d)));
        double sunDistance = Double.longBitsToDouble(this.qasa2.getLong("nedRastojanje", Double.doubleToLongBits(0.0d)));
        if (this.units.equalsIgnoreCase("Imperial")) {
            monDistance *= 0.621371d;
            tueDistance *= 0.621371d;
            wenDistance *= 0.621371d;
            thuDistance *= 0.621371d;
            friDistance *= 0.621371d;
            satDistance *= 0.621371d;
            sunDistance *= 0.621371d;
        }
        ArrayList<BarEntry> weekDuration = new ArrayList();
        weekDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(monDuration)})).floatValue(), 0));
        weekDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(tueDuration)})).floatValue(), 1));
        weekDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(wenDuration)})).floatValue(), 2));
        weekDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(thuDuration)})).floatValue(), 3));
        weekDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(friDuration)})).floatValue(), 4));
        weekDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(satDuration)})).floatValue(), 5));
        weekDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(sunDuration)})).floatValue(), 6));
        BarDataSet barDataSet = new BarDataSet(weekDuration, getResources().getString(R.string.Time) + ": " + getResources().getString(R.string.min));
        barDataSet.setValueTextSize(20.0f);
        barDataSet.setColor(Color.rgb(255, 204, 0));
        barDataSet.setBarSpacePercent(35.0f);
        this.dataSetsWeekDuration = new ArrayList();
        this.dataSetsWeekDuration.add(barDataSet);
        ArrayList<BarEntry> weekCalories = new ArrayList();
        weekCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(monCalories)})).floatValue(), 0));
        weekCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(tueCalories)})).floatValue(), 1));
        weekCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(wenCalories)})).floatValue(), 2));
        weekCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(thuCalories)})).floatValue(), 3));
        weekCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(friCalories)})).floatValue(), 4));
        weekCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(satCalories)})).floatValue(), 5));
        weekCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(sunCalories)})).floatValue(), 6));
        barDataSet = new BarDataSet(weekCalories, getResources().getString(R.string.Calories) + ": " + getResources().getString(R.string.kcal));
        barDataSet.setValueTextSize(20.0f);
        barDataSet.setColor(Color.rgb(255, 204, 0));
        barDataSet.setBarSpacePercent(35.0f);
        this.dataSetsWeekCalories = new ArrayList();
        this.dataSetsWeekCalories.add(barDataSet);
        ArrayList<BarEntry> weekDistance = new ArrayList();
        weekDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(monDistance)})).floatValue(), 0));
        weekDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(tueDistance)})).floatValue(), 1));
        weekDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(wenDistance)})).floatValue(), 2));
        weekDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(thuDistance)})).floatValue(), 3));
        weekDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(friDistance)})).floatValue(), 4));
        weekDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(satDistance)})).floatValue(), 5));
        weekDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(sunDistance)})).floatValue(), 6));
        if (this.units.equalsIgnoreCase("Imperial")) {
            barDataSet = new BarDataSet(weekDistance, getResources().getString(R.string.Distance) + ": " + getResources().getString(R.string.mi));
        } else {
            barDataSet = new BarDataSet(weekDistance, getResources().getString(R.string.Distance) + ": " + getResources().getString(R.string.km));
        }
        barDataSet.setValueTextSize(20.0f);
        barDataSet.setColor(Color.rgb(255, 204, 0));
        barDataSet.setBarSpacePercent(35.0f);
        this.dataSetsWeekDistance = new ArrayList();
        this.dataSetsWeekDistance.add(barDataSet);
    }
}
