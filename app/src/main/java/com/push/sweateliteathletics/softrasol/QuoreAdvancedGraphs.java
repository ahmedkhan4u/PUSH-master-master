package com.push.sweateliteathletics.softrasol;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Locale;

public class QuoreAdvancedGraphs extends AppCompatActivity {
    private Button btnBack;
    private Button btnMonth;
    private Button btnWeek;
    private Button btnYear;
    private BarChart chart;
    private BarData data;
    private ArrayList<IBarDataSet> dataSetsMonthCalories;
    private ArrayList<IBarDataSet> dataSetsMonthDistance;
    private ArrayList<IBarDataSet> dataSetsMonthDuration;
    private ArrayList<IBarDataSet> dataSetsWeekCalories;
    private ArrayList<IBarDataSet> dataSetsWeekDistance;
    private ArrayList<IBarDataSet> dataSetsWeekDuration;
    private ArrayList<IBarDataSet> dataSetsYearCalories;
    private ArrayList<IBarDataSet> dataSetsYearDistance;
    private ArrayList<IBarDataSet> dataSetsYearDuration;
    private int dataType = 1;
    private ImageButton imCalories;
    private ImageButton imDistance;
    private ImageButton imDuration;
    private int interval = 1;
    private ArrayList<String> labelsMonth;
    private ArrayList<String> labelsWeek;
    private ArrayList<String> labelsYear;
    private SharedPreferences qasa2;
    private TextView title;
    private String units = "Metric";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_graphs);
        String PREFS_NAME = "qA1sa2";


        RelativeLayout bnnr = (RelativeLayout) findViewById(R.id.bnr);
        Banner(bnnr, QuoreAdvancedGraphs.this);

        this.qasa2 = getSharedPreferences("qA1sa2", 0);
        this.units = this.qasa2.getString("units", "Metric");
        this.imCalories = (ImageButton) findViewById(R.id.imbAdvancedCalories);
        this.imDuration = (ImageButton) findViewById(R.id.imbAdvancedTime);
        this.imDistance = (ImageButton) findViewById(R.id.imbAdvancedDistance);
        this.btnWeek = (Button) findViewById(R.id.btnWeek);
        this.btnMonth = (Button) findViewById(R.id.btnMonth);
        this.btnYear = (Button) findViewById(R.id.btnYear);
        this.btnBack = (Button) findViewById(R.id.btnBack);
        this.chart = (BarChart) findViewById(R.id.chart);
        this.title = (TextView) findViewById(R.id.tvAdvGraphTitle);
        XAxis xAxis = this.chart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setSpaceBetweenLabels(0);
        xAxis.setDrawGridLines(false);
        readData();
        drawGraph();
        this.btnBack.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreAdvancedGraphs.this.finish();
            }
        });
        this.btnWeek.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreAdvancedGraphs.this.interval = 1;
//                QuoreAdvancedGraphs.this.btnWeek.setBackgroundResource(R.drawable.green_button_p);
//                QuoreAdvancedGraphs.this.btnMonth.setBackgroundResource(R.drawable.yellow_button_p);
//                QuoreAdvancedGraphs.this.btnYear.setBackgroundResource(R.drawable.yellow_button_p);
                QuoreAdvancedGraphs.this.drawGraph();
            }
        });
        this.btnMonth.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreAdvancedGraphs.this.interval = 2;
//                QuoreAdvancedGraphs.this.btnWeek.setBackgroundResource(R.drawable.yellow_button_p);
//                QuoreAdvancedGraphs.this.btnMonth.setBackgroundResource(R.drawable.green_button_p);
//                QuoreAdvancedGraphs.this.btnYear.setBackgroundResource(R.drawable.yellow_button_p);
                QuoreAdvancedGraphs.this.drawGraph();
            }
        });
        this.btnYear.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreAdvancedGraphs.this.interval = 3;
//                QuoreAdvancedGraphs.this.btnWeek.setBackgroundResource(R.drawable.yellow_button_p);
//                QuoreAdvancedGraphs.this.btnMonth.setBackgroundResource(R.drawable.yellow_button_p);
//                QuoreAdvancedGraphs.this.btnYear.setBackgroundResource(R.drawable.green_button_p);
                QuoreAdvancedGraphs.this.drawGraph();
            }
        });
        this.imCalories.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreAdvancedGraphs.this.dataType = 1;
//                QuoreAdvancedGraphs.this.imCalories.setBackgroundResource(R.drawable.green_button);
//                QuoreAdvancedGraphs.this.imDistance.setBackgroundResource(R.drawable.yellow_button);
//                QuoreAdvancedGraphs.this.imDuration.setBackgroundResource(R.drawable.yellow_button);
                QuoreAdvancedGraphs.this.drawGraph();
            }
        });
        this.imDuration.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreAdvancedGraphs.this.dataType = 3;
//                QuoreAdvancedGraphs.this.imCalories.setBackgroundResource(R.drawable.yellow_button);
//                QuoreAdvancedGraphs.this.imDistance.setBackgroundResource(R.drawable.yellow_button);
//                QuoreAdvancedGraphs.this.imDuration.setBackgroundResource(R.drawable.green_button);
                QuoreAdvancedGraphs.this.drawGraph();
            }
        });
        this.imDistance.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreAdvancedGraphs.this.dataType = 2;
//                QuoreAdvancedGraphs.this.imCalories.setBackgroundResource(R.drawable.yellow_button);
//                QuoreAdvancedGraphs.this.imDistance.setBackgroundResource(R.drawable.green_button);
//                QuoreAdvancedGraphs.this.imDuration.setBackgroundResource(R.drawable.yellow_button);
                QuoreAdvancedGraphs.this.drawGraph();
            }
        });
    }

    public void onResume() {
        super.onResume();
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
        if (this.interval == 1) {
            drawGraphForWeek();
        } else if (this.interval == 2) {
            drawGraphForMonth();
        } else if (this.interval == 3) {
            drawGraphForYear();
        }
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

    private void drawGraphForYear() {
        if (this.dataType == 1) {
            this.data = new BarData(this.labelsYear, this.dataSetsYearCalories);
        } else if (this.dataType == 2) {
            this.data = new BarData(this.labelsYear, this.dataSetsYearDistance);
        } else if (this.dataType == 3) {
            this.data = new BarData(this.labelsYear, this.dataSetsYearDuration);
        }
    }

    private void drawGraphForMonth() {
        if (this.dataType == 1) {
            this.data = new BarData(this.labelsMonth, this.dataSetsMonthCalories);
        } else if (this.dataType == 2) {
            this.data = new BarData(this.labelsMonth, this.dataSetsMonthDistance);
        } else if (this.dataType == 3) {
            this.data = new BarData(this.labelsMonth, this.dataSetsMonthDuration);
        }
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

    private void readData() {
        readDataYear();
        readDataMonth();
        readDataWeek();
    }

    private void readDataYear() {
        this.labelsYear = new ArrayList();
        this.labelsYear.add(getResources().getString(R.string.jan));
        this.labelsYear.add(getResources().getString(R.string.feb));
        this.labelsYear.add(getResources().getString(R.string.mar));
        this.labelsYear.add(getResources().getString(R.string.apr));
        this.labelsYear.add(getResources().getString(R.string.may));
        this.labelsYear.add(getResources().getString(R.string.jun));
        this.labelsYear.add(getResources().getString(R.string.jul));
        this.labelsYear.add(getResources().getString(R.string.aug));
        this.labelsYear.add(getResources().getString(R.string.sep));
        this.labelsYear.add(getResources().getString(R.string.oct));
        this.labelsYear.add(getResources().getString(R.string.nov));
        this.labelsYear.add(getResources().getString(R.string.dec));
        double janDuration = Double.longBitsToDouble(this.qasa2.getLong("januarVreme", Double.doubleToLongBits(0.0d)));
        double febDuration = Double.longBitsToDouble(this.qasa2.getLong("februarVreme", Double.doubleToLongBits(0.0d)));
        double marDuration = Double.longBitsToDouble(this.qasa2.getLong("martVreme", Double.doubleToLongBits(0.0d)));
        double aprDuration = Double.longBitsToDouble(this.qasa2.getLong("aprilVreme", Double.doubleToLongBits(0.0d)));
        double mayDuration = Double.longBitsToDouble(this.qasa2.getLong("majVreme", Double.doubleToLongBits(0.0d)));
        double junDuration = Double.longBitsToDouble(this.qasa2.getLong("junVreme", Double.doubleToLongBits(0.0d)));
        double julDuration = Double.longBitsToDouble(this.qasa2.getLong("julVreme", Double.doubleToLongBits(0.0d)));
        double augDuration = Double.longBitsToDouble(this.qasa2.getLong("avgustVreme", Double.doubleToLongBits(0.0d)));
        double sepDuration = Double.longBitsToDouble(this.qasa2.getLong("septembarVreme", Double.doubleToLongBits(0.0d)));
        double octDuration = Double.longBitsToDouble(this.qasa2.getLong("oktobarVreme", Double.doubleToLongBits(0.0d)));
        double novDuration = Double.longBitsToDouble(this.qasa2.getLong("novembarVreme", Double.doubleToLongBits(0.0d)));
        double decDuration = Double.longBitsToDouble(this.qasa2.getLong("decembarVreme", Double.doubleToLongBits(0.0d)));
        ArrayList<BarEntry> yearDuration = new ArrayList();
        yearDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(janDuration)})).floatValue(), 0));
        yearDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(febDuration)})).floatValue(), 1));
        yearDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(marDuration)})).floatValue(), 2));
        yearDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(aprDuration)})).floatValue(), 3));
        yearDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(mayDuration)})).floatValue(), 4));
        yearDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(junDuration)})).floatValue(), 5));
        yearDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(julDuration)})).floatValue(), 6));
        yearDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(augDuration)})).floatValue(), 7));
        yearDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(sepDuration)})).floatValue(), 8));
        yearDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(octDuration)})).floatValue(), 9));
        yearDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(novDuration)})).floatValue(), 10));
        yearDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(decDuration)})).floatValue(), 11));
        BarDataSet barDataSet = new BarDataSet(yearDuration, getResources().getString(R.string.Time) + ": " + getResources().getString(R.string.min));
        barDataSet.setValueTextSize(14.0f);
        barDataSet.setColor(Color.rgb(255, 204, 0));
        barDataSet.setBarSpacePercent(35.0f);
        this.dataSetsYearDuration = new ArrayList();
        this.dataSetsYearDuration.add(barDataSet);
        double janCalories = Double.longBitsToDouble(this.qasa2.getLong("januarKalorije", Double.doubleToLongBits(0.0d)));
        double febCalories = Double.longBitsToDouble(this.qasa2.getLong("februarKalorije", Double.doubleToLongBits(0.0d)));
        double marCalories = Double.longBitsToDouble(this.qasa2.getLong("martKalorije", Double.doubleToLongBits(0.0d)));
        double aprCalories = Double.longBitsToDouble(this.qasa2.getLong("aprilKalorije", Double.doubleToLongBits(0.0d)));
        double mayCalories = Double.longBitsToDouble(this.qasa2.getLong("majKalorije", Double.doubleToLongBits(0.0d)));
        double junCalories = Double.longBitsToDouble(this.qasa2.getLong("junKalorije", Double.doubleToLongBits(0.0d)));
        double julCalories = Double.longBitsToDouble(this.qasa2.getLong("julKalorije", Double.doubleToLongBits(0.0d)));
        double augCalories = Double.longBitsToDouble(this.qasa2.getLong("avgustKalorije", Double.doubleToLongBits(0.0d)));
        double sepCalories = Double.longBitsToDouble(this.qasa2.getLong("septembarKalorije", Double.doubleToLongBits(0.0d)));
        double octCalories = Double.longBitsToDouble(this.qasa2.getLong("oktobarKalorije", Double.doubleToLongBits(0.0d)));
        double novCalories = Double.longBitsToDouble(this.qasa2.getLong("novembarKalorije", Double.doubleToLongBits(0.0d)));
        double decCalories = Double.longBitsToDouble(this.qasa2.getLong("decembarKalorije", Double.doubleToLongBits(0.0d)));
        ArrayList<BarEntry> yearCalories = new ArrayList();
        yearCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(janCalories)})).floatValue(), 0));
        yearCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(febCalories)})).floatValue(), 1));
        yearCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(marCalories)})).floatValue(), 2));
        yearCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(aprCalories)})).floatValue(), 3));
        yearCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(mayCalories)})).floatValue(), 4));
        yearCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(junCalories)})).floatValue(), 5));
        yearCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(julCalories)})).floatValue(), 6));
        yearCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(augCalories)})).floatValue(), 7));
        yearCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(sepCalories)})).floatValue(), 8));
        yearCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(octCalories)})).floatValue(), 9));
        yearCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(novCalories)})).floatValue(), 10));
        yearCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(decCalories)})).floatValue(), 11));
        barDataSet = new BarDataSet(yearCalories, getResources().getString(R.string.Calories) + ": " + getResources().getString(R.string.kcal));
        barDataSet.setValueTextSize(14.0f);
        barDataSet.setColor(Color.rgb(255, 204, 0));
        barDataSet.setBarSpacePercent(35.0f);
        this.dataSetsYearCalories = new ArrayList();
        this.dataSetsYearCalories.add(barDataSet);
        double janDistance = Double.longBitsToDouble(this.qasa2.getLong("januarRastojanje", Double.doubleToLongBits(0.0d)));
        double febDistance = Double.longBitsToDouble(this.qasa2.getLong("februarRastojanje", Double.doubleToLongBits(0.0d)));
        double marDistance = Double.longBitsToDouble(this.qasa2.getLong("martRastojanje", Double.doubleToLongBits(0.0d)));
        double aprDistance = Double.longBitsToDouble(this.qasa2.getLong("aprilRastojanje", Double.doubleToLongBits(0.0d)));
        double mayDistance = Double.longBitsToDouble(this.qasa2.getLong("majRastojanje", Double.doubleToLongBits(0.0d)));
        double junDistance = Double.longBitsToDouble(this.qasa2.getLong("junRastojanje", Double.doubleToLongBits(0.0d)));
        double julDistance = Double.longBitsToDouble(this.qasa2.getLong("julRastojanje", Double.doubleToLongBits(0.0d)));
        double augDistance = Double.longBitsToDouble(this.qasa2.getLong("avgustRastojanje", Double.doubleToLongBits(0.0d)));
        double sepDistance = Double.longBitsToDouble(this.qasa2.getLong("septembarRastojanje", Double.doubleToLongBits(0.0d)));
        double octDistance = Double.longBitsToDouble(this.qasa2.getLong("oktobarRastojanje", Double.doubleToLongBits(0.0d)));
        double novDistance = Double.longBitsToDouble(this.qasa2.getLong("novembarRastojanje", Double.doubleToLongBits(0.0d)));
        double decDistance = Double.longBitsToDouble(this.qasa2.getLong("decembarRastojanje", Double.doubleToLongBits(0.0d)));
        if (this.units.equalsIgnoreCase("Imperial")) {
            janDistance *= 0.621371d;
            febDistance *= 0.621371d;
            marDistance *= 0.621371d;
            aprDistance *= 0.621371d;
            mayDistance *= 0.621371d;
            junDistance *= 0.621371d;
            julDistance *= 0.621371d;
            augDistance *= 0.621371d;
            sepDistance *= 0.621371d;
            octDistance *= 0.621371d;
            novDistance *= 0.621371d;
            decDistance *= 0.621371d;
        }
        ArrayList<BarEntry> yearDistance = new ArrayList();
        yearDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(janDistance)})).floatValue(), 0));
        yearDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(febDistance)})).floatValue(), 1));
        yearDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(marDistance)})).floatValue(), 2));
        yearDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(aprDistance)})).floatValue(), 3));
        yearDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(mayDistance)})).floatValue(), 4));
        yearDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(junDistance)})).floatValue(), 5));
        yearDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(julDistance)})).floatValue(), 6));
        yearDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(augDistance)})).floatValue(), 7));
        yearDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(sepDistance)})).floatValue(), 8));
        yearDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(octDistance)})).floatValue(), 9));
        yearDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(novDistance)})).floatValue(), 10));
        yearDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(decDistance)})).floatValue(), 11));
        if (this.units.equalsIgnoreCase("Imperial")) {
            barDataSet = new BarDataSet(yearDistance, getResources().getString(R.string.Distance) + ": " + getResources().getString(R.string.mi));
        } else {
            barDataSet = new BarDataSet(yearDistance, getResources().getString(R.string.Distance) + ": " + getResources().getString(R.string.km));
        }
        barDataSet.setValueTextSize(14.0f);
        barDataSet.setColor(Color.rgb(255, 204, 0));
        barDataSet.setBarSpacePercent(35.0f);
        this.dataSetsYearDistance = new ArrayList();
        this.dataSetsYearDistance.add(barDataSet);
    }

    private void readDataMonth() {
        this.labelsMonth = new ArrayList();
        this.labelsMonth.add("1");
        this.labelsMonth.add("2");
        this.labelsMonth.add("3");
        this.labelsMonth.add("4");
        this.labelsMonth.add("5");
        this.labelsMonth.add("6");
        this.labelsMonth.add("7");
        this.labelsMonth.add("8");
        this.labelsMonth.add("9");
        this.labelsMonth.add("10");
        this.labelsMonth.add("11");
        this.labelsMonth.add("12");
        this.labelsMonth.add("13");
        this.labelsMonth.add("14");
        this.labelsMonth.add("15");
        this.labelsMonth.add("16");
        this.labelsMonth.add("17");
        this.labelsMonth.add("18");
        this.labelsMonth.add("19");
        this.labelsMonth.add("20");
        this.labelsMonth.add("21");
        this.labelsMonth.add("22");
        this.labelsMonth.add("23");
        this.labelsMonth.add("24");
        this.labelsMonth.add("25");
        this.labelsMonth.add("26");
        this.labelsMonth.add("27");
        this.labelsMonth.add("28");
        this.labelsMonth.add("29");
        this.labelsMonth.add("30");
        this.labelsMonth.add("31");
        double duration1 = Double.longBitsToDouble(this.qasa2.getLong("vremeprvi", Double.doubleToLongBits(0.0d)));
        double duration2 = Double.longBitsToDouble(this.qasa2.getLong("vremedrugi", Double.doubleToLongBits(0.0d)));
        double duration3 = Double.longBitsToDouble(this.qasa2.getLong("vremetreci", Double.doubleToLongBits(0.0d)));
        double duration4 = Double.longBitsToDouble(this.qasa2.getLong("vremecetvrti", Double.doubleToLongBits(0.0d)));
        double duration5 = Double.longBitsToDouble(this.qasa2.getLong("vremepeti", Double.doubleToLongBits(0.0d)));
        double duration6 = Double.longBitsToDouble(this.qasa2.getLong("vremesesti", Double.doubleToLongBits(0.0d)));
        double duration7 = Double.longBitsToDouble(this.qasa2.getLong("vremesedmi", Double.doubleToLongBits(0.0d)));
        double duration8 = Double.longBitsToDouble(this.qasa2.getLong("vremeosmi", Double.doubleToLongBits(0.0d)));
        double duration9 = Double.longBitsToDouble(this.qasa2.getLong("vremedeveti", Double.doubleToLongBits(0.0d)));
        double duration10 = Double.longBitsToDouble(this.qasa2.getLong("vremedeseti", Double.doubleToLongBits(0.0d)));
        double duration11 = Double.longBitsToDouble(this.qasa2.getLong("vremejedanaesti", Double.doubleToLongBits(0.0d)));
        double duration12 = Double.longBitsToDouble(this.qasa2.getLong("vremedvanaesti", Double.doubleToLongBits(0.0d)));
        double duration13 = Double.longBitsToDouble(this.qasa2.getLong("vremetrinaesti", Double.doubleToLongBits(0.0d)));
        double duration14 = Double.longBitsToDouble(this.qasa2.getLong("vremecetrnaesti", Double.doubleToLongBits(0.0d)));
        double duration15 = Double.longBitsToDouble(this.qasa2.getLong("vremepetnaesti", Double.doubleToLongBits(0.0d)));
        double duration16 = Double.longBitsToDouble(this.qasa2.getLong("vremesesnaesti", Double.doubleToLongBits(0.0d)));
        double duration17 = Double.longBitsToDouble(this.qasa2.getLong("vremesedamnaesti", Double.doubleToLongBits(0.0d)));
        double duration18 = Double.longBitsToDouble(this.qasa2.getLong("vremeosamnaesti", Double.doubleToLongBits(0.0d)));
        double duration19 = Double.longBitsToDouble(this.qasa2.getLong("vremedevetnaesti", Double.doubleToLongBits(0.0d)));
        double duration20 = Double.longBitsToDouble(this.qasa2.getLong("vremedvadeseti", Double.doubleToLongBits(0.0d)));
        double duration21 = Double.longBitsToDouble(this.qasa2.getLong("vremedvadesetprvi", Double.doubleToLongBits(0.0d)));
        double duration22 = Double.longBitsToDouble(this.qasa2.getLong("vremedvadesetdrugi", Double.doubleToLongBits(0.0d)));
        double duration23 = Double.longBitsToDouble(this.qasa2.getLong("vremedvadesettreci", Double.doubleToLongBits(0.0d)));
        double duration24 = Double.longBitsToDouble(this.qasa2.getLong("vremedvadesetcetvrti", Double.doubleToLongBits(0.0d)));
        double duration25 = Double.longBitsToDouble(this.qasa2.getLong("vremedvadesetpeti", Double.doubleToLongBits(0.0d)));
        double duration26 = Double.longBitsToDouble(this.qasa2.getLong("vremedvadesetsesti", Double.doubleToLongBits(0.0d)));
        double duration27 = Double.longBitsToDouble(this.qasa2.getLong("vremedvadesetsedmi", Double.doubleToLongBits(0.0d)));
        double duration28 = Double.longBitsToDouble(this.qasa2.getLong("vremedvadesetosmi", Double.doubleToLongBits(0.0d)));
        double duration29 = Double.longBitsToDouble(this.qasa2.getLong("vremedvadesetdeveti", Double.doubleToLongBits(0.0d)));
        double duration30 = Double.longBitsToDouble(this.qasa2.getLong("vremetrideseti", Double.doubleToLongBits(0.0d)));
        double duration31 = Double.longBitsToDouble(this.qasa2.getLong("vremetridesetprvi", Double.doubleToLongBits(0.0d)));
        ArrayList<BarEntry> monthDuration = new ArrayList();
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration1)})).floatValue(), 0));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration2)})).floatValue(), 1));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration3)})).floatValue(), 2));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration4)})).floatValue(), 3));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration5)})).floatValue(), 4));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration6)})).floatValue(), 5));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration7)})).floatValue(), 6));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration8)})).floatValue(), 7));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration9)})).floatValue(), 8));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration10)})).floatValue(), 9));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration11)})).floatValue(), 10));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration12)})).floatValue(), 11));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration13)})).floatValue(), 12));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration14)})).floatValue(), 13));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration15)})).floatValue(), 14));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration16)})).floatValue(), 15));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration17)})).floatValue(), 16));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration18)})).floatValue(), 17));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration19)})).floatValue(), 18));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration20)})).floatValue(), 19));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration21)})).floatValue(), 20));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration22)})).floatValue(), 21));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration23)})).floatValue(), 22));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration24)})).floatValue(), 23));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration25)})).floatValue(), 24));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration26)})).floatValue(), 25));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration27)})).floatValue(), 26));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration28)})).floatValue(), 27));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration29)})).floatValue(), 28));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration30)})).floatValue(), 29));
        monthDuration.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(duration31)})).floatValue(), 30));
        BarDataSet barDataSet = new BarDataSet(monthDuration, getResources().getString(R.string.Time) + ": " + getResources().getString(R.string.min));
        barDataSet.setValueTextSize(8.0f);
        barDataSet.setColor(Color.rgb(255, 204, 0));
        barDataSet.setBarSpacePercent(35.0f);
        this.dataSetsMonthDuration = new ArrayList();
        this.dataSetsMonthDuration.add(barDataSet);
        double distance1 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjeprvi", Double.doubleToLongBits(0.0d)));
        double distance2 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjedrugi", Double.doubleToLongBits(0.0d)));
        double distance3 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjetreci", Double.doubleToLongBits(0.0d)));
        double distance4 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjecetvrti", Double.doubleToLongBits(0.0d)));
        double distance5 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjepeti", Double.doubleToLongBits(0.0d)));
        double distance6 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjesesti", Double.doubleToLongBits(0.0d)));
        double distance7 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjesedmi", Double.doubleToLongBits(0.0d)));
        double distance8 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjeosmi", Double.doubleToLongBits(0.0d)));
        double distance9 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjedeveti", Double.doubleToLongBits(0.0d)));
        double distance10 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjedeseti", Double.doubleToLongBits(0.0d)));
        double distance11 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjejedanaesti", Double.doubleToLongBits(0.0d)));
        double distance12 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjedvanaesti", Double.doubleToLongBits(0.0d)));
        double distance13 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjetrinaesti", Double.doubleToLongBits(0.0d)));
        double distance14 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjecetrnaesti", Double.doubleToLongBits(0.0d)));
        double distance15 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjepetnaesti", Double.doubleToLongBits(0.0d)));
        double distance16 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjesesnaesti", Double.doubleToLongBits(0.0d)));
        double distance17 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjesedamnaesti", Double.doubleToLongBits(0.0d)));
        double distance18 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjeosamnaesti", Double.doubleToLongBits(0.0d)));
        double distance19 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjedevetnaesti", Double.doubleToLongBits(0.0d)));
        double distance20 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjedvadeseti", Double.doubleToLongBits(0.0d)));
        double distance21 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjedvadesetprvi", Double.doubleToLongBits(0.0d)));
        double distance22 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjedvadesetdrugi", Double.doubleToLongBits(0.0d)));
        double distance23 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjedvadesettreci", Double.doubleToLongBits(0.0d)));
        double distance24 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjedvadesetcetvrti", Double.doubleToLongBits(0.0d)));
        double distance25 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjedvadesetpeti", Double.doubleToLongBits(0.0d)));
        double distance26 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjedvadesetsesti", Double.doubleToLongBits(0.0d)));
        double distance27 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjedvadesetsedmi", Double.doubleToLongBits(0.0d)));
        double distance28 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjedvadesetosmi", Double.doubleToLongBits(0.0d)));
        double distance29 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjedvadesetdeveti", Double.doubleToLongBits(0.0d)));
        double distance30 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjetrideseti", Double.doubleToLongBits(0.0d)));
        double distance31 = Double.longBitsToDouble(this.qasa2.getLong("rastojanjetridesetprvi", Double.doubleToLongBits(0.0d)));
        if (this.units.equalsIgnoreCase("Imperial")) {
            distance1 *= 0.621371d;
            distance2 *= 0.621371d;
            distance3 *= 0.621371d;
            distance4 *= 0.621371d;
            distance5 *= 0.621371d;
            distance6 *= 0.621371d;
            distance7 *= 0.621371d;
            distance8 *= 0.621371d;
            distance9 *= 0.621371d;
            distance10 *= 0.621371d;
            distance11 *= 0.621371d;
            distance12 *= 0.621371d;
            distance13 *= 0.621371d;
            distance14 *= 0.621371d;
            distance15 *= 0.621371d;
            distance16 *= 0.621371d;
            distance17 *= 0.621371d;
            distance18 *= 0.621371d;
            distance19 *= 0.621371d;
            distance20 *= 0.621371d;
            distance21 *= 0.621371d;
            distance22 *= 0.621371d;
            distance23 *= 0.621371d;
            distance24 *= 0.621371d;
            distance25 *= 0.621371d;
            distance26 *= 0.621371d;
            distance27 *= 0.621371d;
            distance28 *= 0.621371d;
            distance29 *= 0.621371d;
            distance30 *= 0.621371d;
            distance31 *= 0.621371d;
        }
        ArrayList<BarEntry> monthDistance = new ArrayList();
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance1)})).floatValue(), 0));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance2)})).floatValue(), 1));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance3)})).floatValue(), 2));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance4)})).floatValue(), 3));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance5)})).floatValue(), 4));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance6)})).floatValue(), 5));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance7)})).floatValue(), 6));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance8)})).floatValue(), 7));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance9)})).floatValue(), 8));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance10)})).floatValue(), 9));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance11)})).floatValue(), 10));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance12)})).floatValue(), 11));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance13)})).floatValue(), 12));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance14)})).floatValue(), 13));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance15)})).floatValue(), 14));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance16)})).floatValue(), 15));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance17)})).floatValue(), 16));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance18)})).floatValue(), 17));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance19)})).floatValue(), 18));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance20)})).floatValue(), 19));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance21)})).floatValue(), 20));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance22)})).floatValue(), 21));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance23)})).floatValue(), 22));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance24)})).floatValue(), 23));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance25)})).floatValue(), 24));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance26)})).floatValue(), 25));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance27)})).floatValue(), 26));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance28)})).floatValue(), 27));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance29)})).floatValue(), 28));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance30)})).floatValue(), 29));
        monthDistance.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(distance31)})).floatValue(), 30));
        if (this.units.equalsIgnoreCase("Imperial")) {
            barDataSet = new BarDataSet(monthDistance, getResources().getString(R.string.Distance) + ": " + getResources().getString(R.string.mi));
        } else {
            barDataSet = new BarDataSet(monthDistance, getResources().getString(R.string.Distance) + ": " + getResources().getString(R.string.km));
        }
        barDataSet.setColor(Color.rgb(255, 204, 0));
        barDataSet.setValueTextSize(8.0f);
        barDataSet.setBarSpacePercent(35.0f);
        this.dataSetsMonthDistance = new ArrayList();
        this.dataSetsMonthDistance.add(barDataSet);
        double calories1 = Double.longBitsToDouble(this.qasa2.getLong("kalorijeprvi", Double.doubleToLongBits(0.0d)));
        double calories2 = Double.longBitsToDouble(this.qasa2.getLong("kalorijedrugi", Double.doubleToLongBits(0.0d)));
        double calories3 = Double.longBitsToDouble(this.qasa2.getLong("kalorijetreci", Double.doubleToLongBits(0.0d)));
        double calories4 = Double.longBitsToDouble(this.qasa2.getLong("kalorijecetvrti", Double.doubleToLongBits(0.0d)));
        double calories5 = Double.longBitsToDouble(this.qasa2.getLong("kalorijepeti", Double.doubleToLongBits(0.0d)));
        double calories6 = Double.longBitsToDouble(this.qasa2.getLong("kalorijesesti", Double.doubleToLongBits(0.0d)));
        double calories7 = Double.longBitsToDouble(this.qasa2.getLong("kalorijesedmi", Double.doubleToLongBits(0.0d)));
        double calories8 = Double.longBitsToDouble(this.qasa2.getLong("kalorijeosmi", Double.doubleToLongBits(0.0d)));
        double calories9 = Double.longBitsToDouble(this.qasa2.getLong("kalorijedeveti", Double.doubleToLongBits(0.0d)));
        double calories10 = Double.longBitsToDouble(this.qasa2.getLong("kalorijedeseti", Double.doubleToLongBits(0.0d)));
        double calories11 = Double.longBitsToDouble(this.qasa2.getLong("kalorijejedanaesti", Double.doubleToLongBits(0.0d)));
        double calories12 = Double.longBitsToDouble(this.qasa2.getLong("kalorijedvanaesti", Double.doubleToLongBits(0.0d)));
        double calories13 = Double.longBitsToDouble(this.qasa2.getLong("kalorijetrinaesti", Double.doubleToLongBits(0.0d)));
        double calories14 = Double.longBitsToDouble(this.qasa2.getLong("kalorijecetrnaesti", Double.doubleToLongBits(0.0d)));
        double calories15 = Double.longBitsToDouble(this.qasa2.getLong("kalorijepetnaesti", Double.doubleToLongBits(0.0d)));
        double calories16 = Double.longBitsToDouble(this.qasa2.getLong("kalorijesesnaesti", Double.doubleToLongBits(0.0d)));
        double calories17 = Double.longBitsToDouble(this.qasa2.getLong("kalorijesedamnaesti", Double.doubleToLongBits(0.0d)));
        double calories18 = Double.longBitsToDouble(this.qasa2.getLong("kalorijeosamnaesti", Double.doubleToLongBits(0.0d)));
        double calories19 = Double.longBitsToDouble(this.qasa2.getLong("kalorijedevetnaesti", Double.doubleToLongBits(0.0d)));
        double calories20 = Double.longBitsToDouble(this.qasa2.getLong("kalorijedvadeseti", Double.doubleToLongBits(0.0d)));
        double calories21 = Double.longBitsToDouble(this.qasa2.getLong("kalorijedvadesetprvi", Double.doubleToLongBits(0.0d)));
        double calories22 = Double.longBitsToDouble(this.qasa2.getLong("kalorijedvadesetdrugi", Double.doubleToLongBits(0.0d)));
        double calories23 = Double.longBitsToDouble(this.qasa2.getLong("kalorijedvadesettreci", Double.doubleToLongBits(0.0d)));
        double calories24 = Double.longBitsToDouble(this.qasa2.getLong("kalorijedvadesetcetvrti", Double.doubleToLongBits(0.0d)));
        double calories25 = Double.longBitsToDouble(this.qasa2.getLong("kalorijedvadesetpeti", Double.doubleToLongBits(0.0d)));
        double calories26 = Double.longBitsToDouble(this.qasa2.getLong("kalorijedvadesetsesti", Double.doubleToLongBits(0.0d)));
        double calories27 = Double.longBitsToDouble(this.qasa2.getLong("kalorijedvadesetsedmi", Double.doubleToLongBits(0.0d)));
        double calories28 = Double.longBitsToDouble(this.qasa2.getLong("kalorijedvadesetosmi", Double.doubleToLongBits(0.0d)));
        double calories29 = Double.longBitsToDouble(this.qasa2.getLong("kalorijedvadesetdeveti", Double.doubleToLongBits(0.0d)));
        double calories30 = Double.longBitsToDouble(this.qasa2.getLong("kalorijetrideseti", Double.doubleToLongBits(0.0d)));
        double calories31 = Double.longBitsToDouble(this.qasa2.getLong("kalorijetridesetprvi", Double.doubleToLongBits(0.0d)));
        ArrayList<BarEntry> monthCalories = new ArrayList();
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories1)})).floatValue(), 0));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories2)})).floatValue(), 1));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories3)})).floatValue(), 2));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories4)})).floatValue(), 3));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories5)})).floatValue(), 4));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories6)})).floatValue(), 5));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories7)})).floatValue(), 6));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories8)})).floatValue(), 7));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories9)})).floatValue(), 8));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories10)})).floatValue(), 9));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories11)})).floatValue(), 10));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories12)})).floatValue(), 11));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories13)})).floatValue(), 12));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories14)})).floatValue(), 13));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories15)})).floatValue(), 14));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories16)})).floatValue(), 15));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories17)})).floatValue(), 16));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories18)})).floatValue(), 17));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories19)})).floatValue(), 18));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories20)})).floatValue(), 19));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories21)})).floatValue(), 20));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories22)})).floatValue(), 21));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories23)})).floatValue(), 22));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories24)})).floatValue(), 23));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories25)})).floatValue(), 24));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories26)})).floatValue(), 25));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories27)})).floatValue(), 26));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories28)})).floatValue(), 27));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories29)})).floatValue(), 28));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories30)})).floatValue(), 29));
        monthCalories.add(new BarEntry(Float.valueOf(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories31)})).floatValue(), 30));
        barDataSet = new BarDataSet(monthCalories, getResources().getString(R.string.Calories) + ": " + getResources().getString(R.string.kcal));
        barDataSet.setColor(Color.rgb(255, 204, 0));
        barDataSet.setValueTextSize(8.0f);
        barDataSet.setBarSpacePercent(35.0f);
        this.dataSetsMonthCalories = new ArrayList();
        this.dataSetsMonthCalories.add(barDataSet);
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
        barDataSet.setValueTextSize(24.0f);
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
        barDataSet.setValueTextSize(24.0f);
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
        barDataSet.setValueTextSize(24.0f);
        barDataSet.setColor(Color.rgb(255, 204, 0));
        barDataSet.setBarSpacePercent(35.0f);
        this.dataSetsWeekDistance = new ArrayList();
        this.dataSetsWeekDistance.add(barDataSet);
    }

    public void Banner(final RelativeLayout Ad_Layout, final Context context) {

        AdView mAdView = new AdView(context);
        mAdView.setAdSize(com.google.android.gms.ads.AdSize.SMART_BANNER);
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
                    AdSize.BANNER_HEIGHT_50);

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
