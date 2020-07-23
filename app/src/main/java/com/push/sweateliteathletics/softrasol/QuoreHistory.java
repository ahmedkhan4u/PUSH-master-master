package com.push.sweateliteathletics.softrasol;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.RelativeLayout;

import java.util.Locale;

public class QuoreHistory extends Fragment {
    private String arrayString;
    private Chronometer cTime1;
    private Chronometer cTime10;
    private Chronometer cTime2;
    private Chronometer cTime3;
    private Chronometer cTime4;
    private Chronometer cTime5;
    private Chronometer cTime6;
    private Chronometer cTime7;
    private Chronometer cTime8;
    private Chronometer cTime9;
    private FontTextView calories1;
    private FontTextView calories10;
    private FontTextView calories2;
    private FontTextView calories3;
    private FontTextView calories4;
    private FontTextView calories5;
    private FontTextView calories6;
    private FontTextView calories7;
    private FontTextView calories8;
    private FontTextView calories9;
    private String caloriesReport;
    private Context context;
    private FontTextView date1;
    private FontTextView date10;
    private FontTextView date2;
    private FontTextView date3;
    private FontTextView date4;
    private FontTextView date5;
    private FontTextView date6;
    private FontTextView date7;
    private FontTextView date8;
    private FontTextView date9;
    private String dateReport;
    private FontTextView distance1;
    private FontTextView distance10;
    private FontTextView distance2;
    private FontTextView distance3;
    private FontTextView distance4;
    private FontTextView distance5;
    private FontTextView distance6;
    private FontTextView distance7;
    private FontTextView distance8;
    private FontTextView distance9;
    private String distanceReport;
    private String highSpeedReport;
    private String history10Calories;
    private String history10Distance;
    private String history10Pace;
    private double history10Time;
    private String history10array;
    private String history10date;
    private String history10highSpeed;
    private String history10lowSpeed;
    private String history10maxSpeed;
    private String history10mediumSpeed;
    private String history10startTime;
    private String history10stopTime;
    private String history1Calories;
    private String history1Distance;
    private String history1Pace;
    private double history1Time;
    private String history1array;
    private String history1date;
    private String history1highSpeed;
    private String history1lowSpeed;
    private String history1maxSpeed;
    private String history1mediumSpeed;
    private String history1startTime;
    private String history1stopTime;
    private String history2Calories;
    private String history2Distance;
    private String history2Pace;
    private double history2Time;
    private String history2array;
    private String history2date;
    private String history2highSpeed;
    private String history2lowSpeed;
    private String history2maxSpeed;
    private String history2mediumSpeed;
    private String history2startTime;
    private String history2stopTime;
    private String history3Calories;
    private String history3Distance;
    private String history3Pace;
    private double history3Time;
    private String history3array;
    private String history3date;
    private String history3highSpeed;
    private String history3lowSpeed;
    private String history3maxSpeed;
    private String history3mediumSpeed;
    private String history3startTime;
    private String history3stopTime;
    private String history4Calories;
    private String history4Distance;
    private String history4Pace;
    private double history4Time;
    private String history4array;
    private String history4date;
    private String history4highSpeed;
    private String history4lowSpeed;
    private String history4maxSpeed;
    private String history4mediumSpeed;
    private String history4startTime;
    private String history4stopTime;
    private String history5Calories;
    private String history5Distance;
    private String history5Pace;
    private double history5Time;
    private String history5array;
    private String history5date;
    private String history5highSpeed;
    private String history5lowSpeed;
    private String history5maxSpeed;
    private String history5mediumSpeed;
    private String history5startTime;
    private String history5stopTime;
    private String history6Calories;
    private String history6Distance;
    private String history6Pace;
    private double history6Time;
    private String history6array;
    private String history6date;
    private String history6highSpeed;
    private String history6lowSpeed;
    private String history6maxSpeed;
    private String history6mediumSpeed;
    private String history6startTime;
    private String history6stopTime;
    private String history7Calories;
    private String history7Distance;
    private String history7Pace;
    private double history7Time;
    private String history7array;
    private String history7date;
    private String history7highSpeed;
    private String history7lowSpeed;
    private String history7maxSpeed;
    private String history7mediumSpeed;
    private String history7startTime;
    private String history7stopTime;
    private String history8Calories;
    private String history8Distance;
    private String history8Pace;
    private double history8Time;
    private String history8array;
    private String history8date;
    private String history8highSpeed;
    private String history8lowSpeed;
    private String history8maxSpeed;
    private String history8mediumSpeed;
    private String history8startTime;
    private String history8stopTime;
    private String history9Calories;
    private String history9Distance;
    private String history9Pace;
    private double history9Time;
    private String history9array;
    private String history9date;
    private String history9highSpeed;
    private String history9lowSpeed;
    private String history9maxSpeed;
    private String history9mediumSpeed;
    private String history9startTime;
    private String history9stopTime;
    private String lowSpeedReport;
    private String maxSpeedReport;
    private String mediumSpeedReport;
    private FontTextView pace1;
    private FontTextView pace10;
    private FontTextView pace2;
    private FontTextView pace3;
    private FontTextView pace4;
    private FontTextView pace5;
    private FontTextView pace6;
    private FontTextView pace7;
    private FontTextView pace8;
    private FontTextView pace9;
    private SharedPreferences qasa2;
    private RelativeLayout relLay1;
    private RelativeLayout relLay10;
    private RelativeLayout relLay2;
    private RelativeLayout relLay3;
    private RelativeLayout relLay4;
    private RelativeLayout relLay5;
    private RelativeLayout relLay6;
    private RelativeLayout relLay7;
    private RelativeLayout relLay8;
    private RelativeLayout relLay9;
    private FontTextView startTime1;
    private FontTextView startTime10;
    private FontTextView startTime2;
    private FontTextView startTime3;
    private FontTextView startTime4;
    private FontTextView startTime5;
    private FontTextView startTime6;
    private FontTextView startTime7;
    private FontTextView startTime8;
    private FontTextView startTime9;
    private String startTimeReport;
    private String stopTimeReport;
    private double timeReport;
    private String units;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_history, container, false);
        this.context = getActivity();
        this.distance1 = (FontTextView) rootView.findViewById(R.id.distanceTv1);
        this.distance2 = (FontTextView) rootView.findViewById(R.id.distanceTv2);
        this.distance3 = (FontTextView) rootView.findViewById(R.id.distanceTv3);
        this.distance4 = (FontTextView) rootView.findViewById(R.id.distanceTv4);
        this.distance5 = (FontTextView) rootView.findViewById(R.id.distanceTv5);
        this.distance6 = (FontTextView) rootView.findViewById(R.id.distanceTv6);
        this.distance7 = (FontTextView) rootView.findViewById(R.id.distanceTv7);
        this.distance8 = (FontTextView) rootView.findViewById(R.id.distanceTv8);
        this.distance9 = (FontTextView) rootView.findViewById(R.id.distanceTv9);
        this.distance10 = (FontTextView) rootView.findViewById(R.id.distanceTv10);
        this.calories1 = (FontTextView) rootView.findViewById(R.id.caloriesTv1);
        this.calories2 = (FontTextView) rootView.findViewById(R.id.caloriesTv2);
        this.calories3 = (FontTextView) rootView.findViewById(R.id.caloriesTv3);
        this.calories4 = (FontTextView) rootView.findViewById(R.id.caloriesTv4);
        this.calories5 = (FontTextView) rootView.findViewById(R.id.caloriesTv5);
        this.calories6 = (FontTextView) rootView.findViewById(R.id.caloriesTv6);
        this.calories7 = (FontTextView) rootView.findViewById(R.id.caloriesTv7);
        this.calories8 = (FontTextView) rootView.findViewById(R.id.caloriesTv8);
        this.calories9 = (FontTextView) rootView.findViewById(R.id.caloriesTv9);
        this.calories10 = (FontTextView) rootView.findViewById(R.id.caloriesTv10);
        this.date1 = (FontTextView) rootView.findViewById(R.id.dateTv1);
        this.date2 = (FontTextView) rootView.findViewById(R.id.dateTv2);
        this.date3 = (FontTextView) rootView.findViewById(R.id.dateTv3);
        this.date4 = (FontTextView) rootView.findViewById(R.id.dateTv4);
        this.date5 = (FontTextView) rootView.findViewById(R.id.dateTv5);
        this.date6 = (FontTextView) rootView.findViewById(R.id.dateTv6);
        this.date7 = (FontTextView) rootView.findViewById(R.id.dateTv7);
        this.date8 = (FontTextView) rootView.findViewById(R.id.dateTv8);
        this.date9 = (FontTextView) rootView.findViewById(R.id.dateTv9);
        this.date10 = (FontTextView) rootView.findViewById(R.id.dateTv10);
        this.pace1 = (FontTextView) rootView.findViewById(R.id.paceTv1);
        this.pace2 = (FontTextView) rootView.findViewById(R.id.paceTv2);
        this.pace3 = (FontTextView) rootView.findViewById(R.id.paceTv3);
        this.pace4 = (FontTextView) rootView.findViewById(R.id.paceTv4);
        this.pace5 = (FontTextView) rootView.findViewById(R.id.paceTv5);
        this.pace6 = (FontTextView) rootView.findViewById(R.id.paceTv6);
        this.pace7 = (FontTextView) rootView.findViewById(R.id.paceTv7);
        this.pace8 = (FontTextView) rootView.findViewById(R.id.paceTv8);
        this.pace9 = (FontTextView) rootView.findViewById(R.id.paceTv9);
        this.pace10 = (FontTextView) rootView.findViewById(R.id.paceTv10);
        this.startTime1 = (FontTextView) rootView.findViewById(R.id.startTimeTv1);
        this.startTime2 = (FontTextView) rootView.findViewById(R.id.startTimeTv2);
        this.startTime3 = (FontTextView) rootView.findViewById(R.id.startTimeTv3);
        this.startTime4 = (FontTextView) rootView.findViewById(R.id.startTimeTv4);
        this.startTime5 = (FontTextView) rootView.findViewById(R.id.startTimeTv5);
        this.startTime6 = (FontTextView) rootView.findViewById(R.id.startTimeTv6);
        this.startTime7 = (FontTextView) rootView.findViewById(R.id.startTimeTv7);
        this.startTime8 = (FontTextView) rootView.findViewById(R.id.startTimeTv8);
        this.startTime9 = (FontTextView) rootView.findViewById(R.id.startTimeTv9);
        this.startTime10 = (FontTextView) rootView.findViewById(R.id.startTimeTv10);
        this.relLay1 = (RelativeLayout) rootView.findViewById(R.id.relLayHis1);
        this.relLay2 = (RelativeLayout) rootView.findViewById(R.id.relLayHis2);
        this.relLay3 = (RelativeLayout) rootView.findViewById(R.id.relLayHis3);
        this.relLay4 = (RelativeLayout) rootView.findViewById(R.id.relLayHis4);
        this.relLay5 = (RelativeLayout) rootView.findViewById(R.id.relLayHis5);
        this.relLay6 = (RelativeLayout) rootView.findViewById(R.id.relLayHis6);
        this.relLay7 = (RelativeLayout) rootView.findViewById(R.id.relLayHis7);
        this.relLay8 = (RelativeLayout) rootView.findViewById(R.id.relLayHis8);
        this.relLay9 = (RelativeLayout) rootView.findViewById(R.id.relLayHis9);
        this.relLay10 = (RelativeLayout) rootView.findViewById(R.id.relLayHis10);
        this.cTime1 = (Chronometer) rootView.findViewById(R.id.cTime1);
        this.cTime2 = (Chronometer) rootView.findViewById(R.id.cTime2);
        this.cTime3 = (Chronometer) rootView.findViewById(R.id.cTime3);
        this.cTime4 = (Chronometer) rootView.findViewById(R.id.cTime4);
        this.cTime5 = (Chronometer) rootView.findViewById(R.id.cTime5);
        this.cTime6 = (Chronometer) rootView.findViewById(R.id.cTime6);
        this.cTime7 = (Chronometer) rootView.findViewById(R.id.cTime7);
        this.cTime8 = (Chronometer) rootView.findViewById(R.id.cTime8);
        this.cTime9 = (Chronometer) rootView.findViewById(R.id.cTime9);
        this.cTime10 = (Chronometer) rootView.findViewById(R.id.cTime10);
        this.qasa2 = getActivity().getSharedPreferences("qA1sa2", 0);
        readHistory();
        this.relLay1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreHistory.this.more(1);
            }
        });
        this.relLay1.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                QuoreHistory.this.messageDelete(1);
                return true;
            }
        });
        this.relLay2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreHistory.this.more(2);
            }
        });
        this.relLay2.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                QuoreHistory.this.messageDelete(2);
                return true;
            }
        });
        this.relLay3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreHistory.this.more(3);
            }
        });
        this.relLay3.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                QuoreHistory.this.messageDelete(3);
                return true;
            }
        });
        this.relLay4.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreHistory.this.more(4);
            }
        });
        this.relLay4.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                QuoreHistory.this.messageDelete(4);
                return true;
            }
        });
        this.relLay5.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreHistory.this.more(5);
            }
        });
        this.relLay5.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                QuoreHistory.this.messageDelete(5);
                return true;
            }
        });
        this.relLay6.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreHistory.this.more(6);
            }
        });
        this.relLay6.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                QuoreHistory.this.messageDelete(6);
                return true;
            }
        });
        this.relLay7.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreHistory.this.more(7);
            }
        });
        this.relLay7.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                QuoreHistory.this.messageDelete(7);
                return true;
            }
        });
        this.relLay8.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreHistory.this.more(8);
            }
        });
        this.relLay8.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                QuoreHistory.this.messageDelete(8);
                return true;
            }
        });
        this.relLay9.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreHistory.this.more(9);
            }
        });
        this.relLay9.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                QuoreHistory.this.messageDelete(9);
                return true;
            }
        });
        this.relLay10.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreHistory.this.more(10);
            }
        });
        this.relLay10.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View arg0) {
                QuoreHistory.this.messageDelete(10);
                return true;
            }
        });
        return rootView;
    }

    public void messageDelete(int id) {
        final int idd = id;
        Builder builder = new Builder(this.context);
        builder.setIcon(R.drawable.ic_warning_black_24dp);
        builder.setTitle(R.string.delete);
        builder.setMessage(getString(R.string.deleteText));
        builder.setNegativeButton(17039360, null);
        builder.setPositiveButton(17039370, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                QuoreHistory.this.delete(idd);
            }
        });
        builder.show();
    }

    private void readHistory() {
        this.history1Pace = this.qasa2.getString("history1Pace", "0");
        this.history2Pace = this.qasa2.getString("history2Pace", "0");
        this.history3Pace = this.qasa2.getString("history3Pace", "0");
        this.history4Pace = this.qasa2.getString("history4Pace", "0");
        this.history5Pace = this.qasa2.getString("history5Pace", "0");
        this.history6Pace = this.qasa2.getString("history6Pace", "0");
        this.history7Pace = this.qasa2.getString("history7Pace", "0");
        this.history8Pace = this.qasa2.getString("history8Pace", "0");
        this.history9Pace = this.qasa2.getString("history9Pace", "0");
        this.history10Pace = this.qasa2.getString("history10Pace", "0");
        this.history1Distance = this.qasa2.getString("history1Distance", "0");
        this.history2Distance = this.qasa2.getString("history2Distance", "0");
        this.history3Distance = this.qasa2.getString("history3Distance", "0");
        this.history4Distance = this.qasa2.getString("history4Distance", "0");
        this.history5Distance = this.qasa2.getString("history5Distance", "0");
        this.history6Distance = this.qasa2.getString("history6Distance", "0");
        this.history7Distance = this.qasa2.getString("history7Distance", "0");
        this.history8Distance = this.qasa2.getString("history8Distance", "0");
        this.history9Distance = this.qasa2.getString("history9Distance", "0");
        this.history10Distance = this.qasa2.getString("history10Distance", "0");
        this.history1Calories = this.qasa2.getString("history1Calories", "0");
        this.history2Calories = this.qasa2.getString("history2Calories", "0");
        this.history3Calories = this.qasa2.getString("history3Calories", "0");
        this.history4Calories = this.qasa2.getString("history4Calories", "0");
        this.history5Calories = this.qasa2.getString("history5Calories", "0");
        this.history6Calories = this.qasa2.getString("history6Calories", "0");
        this.history7Calories = this.qasa2.getString("history7Calories", "0");
        this.history8Calories = this.qasa2.getString("history8Calories", "0");
        this.history9Calories = this.qasa2.getString("history9Calories", "0");
        this.history10Calories = this.qasa2.getString("history10Calories", "0");
        this.history1date = this.qasa2.getString("history1Date", "0");
        this.history2date = this.qasa2.getString("history2Date", "0");
        this.history3date = this.qasa2.getString("history3Date", "0");
        this.history4date = this.qasa2.getString("history4Date", "0");
        this.history5date = this.qasa2.getString("history5Date", "0");
        this.history6date = this.qasa2.getString("history6Date", "0");
        this.history7date = this.qasa2.getString("history7Date", "0");
        this.history8date = this.qasa2.getString("history8Date", "0");
        this.history9date = this.qasa2.getString("history9Date", "0");
        this.history10date = this.qasa2.getString("history10Date", "0");
        this.history1startTime = this.qasa2.getString("history1startTime", "0");
        this.history2startTime = this.qasa2.getString("history2startTime", "0");
        this.history3startTime = this.qasa2.getString("history3startTime", "0");
        this.history4startTime = this.qasa2.getString("history4startTime", "0");
        this.history5startTime = this.qasa2.getString("history5startTime", "0");
        this.history6startTime = this.qasa2.getString("history6startTime", "0");
        this.history7startTime = this.qasa2.getString("history7startTime", "0");
        this.history8startTime = this.qasa2.getString("history8startTime", "0");
        this.history9startTime = this.qasa2.getString("history9startTime", "0");
        this.history10startTime = this.qasa2.getString("history10startTime", "0");
        this.history1stopTime = this.qasa2.getString("history1stopTime", "0");
        this.history2stopTime = this.qasa2.getString("history2stopTime", "0");
        this.history3stopTime = this.qasa2.getString("history3stopTime", "0");
        this.history4stopTime = this.qasa2.getString("history4stopTime", "0");
        this.history5stopTime = this.qasa2.getString("history5stopTime", "0");
        this.history6stopTime = this.qasa2.getString("history6stopTime", "0");
        this.history7stopTime = this.qasa2.getString("history7stopTime", "0");
        this.history8stopTime = this.qasa2.getString("history8stopTime", "0");
        this.history9stopTime = this.qasa2.getString("history9stopTime", "0");
        this.history10stopTime = this.qasa2.getString("history10stopTime", "0");
        this.history1lowSpeed = this.qasa2.getString("history1lowSpeed", "0");
        this.history2lowSpeed = this.qasa2.getString("history2lowSpeed", "0");
        this.history3lowSpeed = this.qasa2.getString("history3lowSpeed", "0");
        this.history4lowSpeed = this.qasa2.getString("history4lowSpeed", "0");
        this.history5lowSpeed = this.qasa2.getString("history5lowSpeed", "0");
        this.history6lowSpeed = this.qasa2.getString("history6lowSpeed", "0");
        this.history7lowSpeed = this.qasa2.getString("history7lowSpeed", "0");
        this.history8lowSpeed = this.qasa2.getString("history8lowSpeed", "0");
        this.history9lowSpeed = this.qasa2.getString("history9lowSpeed", "0");
        this.history10lowSpeed = this.qasa2.getString("history10lowSpeed", "0");
        this.history1mediumSpeed = this.qasa2.getString("history1mediumSpeed", "0");
        this.history2mediumSpeed = this.qasa2.getString("history2mediumSpeed", "0");
        this.history3mediumSpeed = this.qasa2.getString("history3mediumSpeed", "0");
        this.history4mediumSpeed = this.qasa2.getString("history4mediumSpeed", "0");
        this.history5mediumSpeed = this.qasa2.getString("history5mediumSpeed", "0");
        this.history6mediumSpeed = this.qasa2.getString("history6mediumSpeed", "0");
        this.history7mediumSpeed = this.qasa2.getString("history7mediumSpeed", "0");
        this.history8mediumSpeed = this.qasa2.getString("history8mediumSpeed", "0");
        this.history9mediumSpeed = this.qasa2.getString("history9mediumSpeed", "0");
        this.history10mediumSpeed = this.qasa2.getString("history10mediumSpeed", "0");
        this.history1highSpeed = this.qasa2.getString("history1highSpeed", "0");
        this.history2highSpeed = this.qasa2.getString("history2highSpeed", "0");
        this.history3highSpeed = this.qasa2.getString("history3highSpeed", "0");
        this.history4highSpeed = this.qasa2.getString("history4highSpeed", "0");
        this.history5highSpeed = this.qasa2.getString("history5highSpeed", "0");
        this.history6highSpeed = this.qasa2.getString("history6highSpeed", "0");
        this.history7highSpeed = this.qasa2.getString("history7highSpeed", "0");
        this.history8highSpeed = this.qasa2.getString("history8highSpeed", "0");
        this.history9highSpeed = this.qasa2.getString("history9highSpeed", "0");
        this.history10highSpeed = this.qasa2.getString("history10highSpeed", "0");
        this.history1maxSpeed = this.qasa2.getString("history1maxSpeed", "0");
        this.history2maxSpeed = this.qasa2.getString("history2maxSpeed", "0");
        this.history3maxSpeed = this.qasa2.getString("history3maxSpeed", "0");
        this.history4maxSpeed = this.qasa2.getString("history4maxSpeed", "0");
        this.history5maxSpeed = this.qasa2.getString("history5maxSpeed", "0");
        this.history6maxSpeed = this.qasa2.getString("history6maxSpeed", "0");
        this.history7maxSpeed = this.qasa2.getString("history7maxSpeed", "0");
        this.history8maxSpeed = this.qasa2.getString("history8maxSpeed", "0");
        this.history9maxSpeed = this.qasa2.getString("history9maxSpeed", "0");
        this.history10maxSpeed = this.qasa2.getString("history10maxSpeed", "0");
        this.history1array = this.qasa2.getString("history1array", BuildConfig.FLAVOR);
        this.history2array = this.qasa2.getString("history2array", BuildConfig.FLAVOR);
        this.history3array = this.qasa2.getString("history3array", BuildConfig.FLAVOR);
        this.history4array = this.qasa2.getString("history4array", BuildConfig.FLAVOR);
        this.history5array = this.qasa2.getString("history5array", BuildConfig.FLAVOR);
        this.history6array = this.qasa2.getString("history6array", BuildConfig.FLAVOR);
        this.history7array = this.qasa2.getString("history7array", BuildConfig.FLAVOR);
        this.history8array = this.qasa2.getString("history8array", BuildConfig.FLAVOR);
        this.history9array = this.qasa2.getString("history9array", BuildConfig.FLAVOR);
        this.history10array = this.qasa2.getString("history10array", BuildConfig.FLAVOR);
        this.history1Time = Double.longBitsToDouble(this.qasa2.getLong("history1Duration", Double.doubleToLongBits(0.0d)));
        this.history2Time = Double.longBitsToDouble(this.qasa2.getLong("history2Duration", Double.doubleToLongBits(0.0d)));
        this.history3Time = Double.longBitsToDouble(this.qasa2.getLong("history3Duration", Double.doubleToLongBits(0.0d)));
        this.history4Time = Double.longBitsToDouble(this.qasa2.getLong("history4Duration", Double.doubleToLongBits(0.0d)));
        this.history5Time = Double.longBitsToDouble(this.qasa2.getLong("history5Duration", Double.doubleToLongBits(0.0d)));
        this.history6Time = Double.longBitsToDouble(this.qasa2.getLong("history6Duration", Double.doubleToLongBits(0.0d)));
        this.history7Time = Double.longBitsToDouble(this.qasa2.getLong("history7Duration", Double.doubleToLongBits(0.0d)));
        this.history8Time = Double.longBitsToDouble(this.qasa2.getLong("history8Duration", Double.doubleToLongBits(0.0d)));
        this.history9Time = Double.longBitsToDouble(this.qasa2.getLong("history9Duration", Double.doubleToLongBits(0.0d)));
        this.history10Time = Double.longBitsToDouble(this.qasa2.getLong("history10Duration", Double.doubleToLongBits(0.0d)));
        if (this.history1Time < 3600000.0d) {
            this.cTime1.setFormat("00:%s");
        }
        if (this.history2Time < 3600000.0d) {
            this.cTime2.setFormat("00:%s");
        }
        if (this.history3Time < 3600000.0d) {
            this.cTime3.setFormat("00:%s");
        }
        if (this.history4Time < 3600000.0d) {
            this.cTime4.setFormat("00:%s");
        }
        if (this.history5Time < 3600000.0d) {
            this.cTime5.setFormat("00:%s");
        }
        if (this.history6Time < 3600000.0d) {
            this.cTime6.setFormat("00:%s");
        }
        if (this.history7Time < 3600000.0d) {
            this.cTime7.setFormat("00:%s");
        }
        if (this.history8Time < 3600000.0d) {
            this.cTime8.setFormat("00:%s");
        }
        if (this.history9Time < 3600000.0d) {
            this.cTime9.setFormat("00:%s");
        }
        if (this.history10Time < 3600000.0d) {
            this.cTime10.setFormat("00:%s");
        }
    }

    private void showHistory() {
        if (this.history1Time == 0.0d || this.history1date.equalsIgnoreCase("0") || this.history1date.equalsIgnoreCase(BuildConfig.FLAVOR)) {
            this.pace1.setText(BuildConfig.FLAVOR);
            this.distance1.setText(getResources().getString(R.string.noData));
            this.cTime1.setVisibility(4);
            this.date1.setText(BuildConfig.FLAVOR);
            this.calories1.setText(BuildConfig.FLAVOR);
            this.startTime1.setText(BuildConfig.FLAVOR);
        } else {
            if (this.units.equalsIgnoreCase("Metric")) {
                this.distance1.setText(BuildConfig.FLAVOR + this.history1Distance + " " + getResources().getString(R.string.km));
                this.pace1.setText(BuildConfig.FLAVOR + this.history1Pace + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.km));
            } else {
                try {
                    this.distance1.setText(BuildConfig.FLAVOR + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.parseDouble(this.history1Distance) * 0.621371d)}) + " " + getResources().getString(R.string.mi));
                    this.pace1.setText(BuildConfig.FLAVOR + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.parseDouble(this.history1Pace) * 1.609344d)}) + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.mi));
                } catch (Exception e) {
                    this.distance1.setText("0");
                    this.pace1.setText("0");
                }
            }
            this.cTime1.setBase(SystemClock.elapsedRealtime() - ((long) this.history1Time));
            this.date1.setText(BuildConfig.FLAVOR + this.history1date);
            this.calories1.setText(BuildConfig.FLAVOR + this.history1Calories + " " + getResources().getString(R.string.kcal));
            this.startTime1.setText(BuildConfig.FLAVOR + this.history1startTime);
        }
        if (this.history2Time == 0.0d || this.history2date.equalsIgnoreCase("0") || this.history2date.equalsIgnoreCase(BuildConfig.FLAVOR)) {
            this.pace2.setText(BuildConfig.FLAVOR);
            this.distance2.setText(getResources().getString(R.string.noData));
            this.cTime2.setVisibility(4);
            this.date2.setText(BuildConfig.FLAVOR);
            this.calories2.setText(BuildConfig.FLAVOR);
            this.startTime2.setText(BuildConfig.FLAVOR);
        } else {
            if (this.units.equalsIgnoreCase("Metric")) {
                this.distance2.setText(BuildConfig.FLAVOR + this.history2Distance + " " + getResources().getString(R.string.km));
                this.pace2.setText(BuildConfig.FLAVOR + this.history2Pace + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.km));
            } else {
                try {
                    this.distance2.setText(BuildConfig.FLAVOR + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.parseDouble(this.history2Distance) * 0.621371d)}) + " " + getResources().getString(R.string.mi));
                    this.pace2.setText(BuildConfig.FLAVOR + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.parseDouble(this.history2Pace) * 1.609344d)}) + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.mi));
                } catch (Exception e2) {
                    this.distance2.setText("0");
                    this.pace2.setText("0");
                }
            }
            this.cTime2.setBase(SystemClock.elapsedRealtime() - ((long) this.history2Time));
            this.date2.setText(BuildConfig.FLAVOR + this.history2date);
            this.calories2.setText(BuildConfig.FLAVOR + this.history2Calories + " " + getResources().getString(R.string.kcal));
            this.startTime2.setText(BuildConfig.FLAVOR + this.history2startTime);
        }
        if (this.history3Time == 0.0d || this.history3date.equalsIgnoreCase("0") || this.history3date.equalsIgnoreCase(BuildConfig.FLAVOR)) {
            this.pace3.setText(BuildConfig.FLAVOR);
            this.startTime3.setText(BuildConfig.FLAVOR);
            this.distance3.setText(getResources().getString(R.string.noData));
            this.cTime3.setVisibility(4);
            this.date3.setText(BuildConfig.FLAVOR);
            this.calories3.setText(BuildConfig.FLAVOR);
        } else {
            if (this.units.equalsIgnoreCase("Metric")) {
                this.distance3.setText(BuildConfig.FLAVOR + this.history3Distance + " " + getResources().getString(R.string.km));
                this.pace3.setText(BuildConfig.FLAVOR + this.history3Pace + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.km));
            } else {
                try {
                    this.distance3.setText(BuildConfig.FLAVOR + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.parseDouble(this.history3Distance) * 0.621371d)}) + " " + getResources().getString(R.string.mi));
                    this.pace3.setText(BuildConfig.FLAVOR + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.parseDouble(this.history3Pace) * 1.609344d)}) + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.mi));
                } catch (Exception e3) {
                    this.distance3.setText("0");
                    this.pace3.setText("0");
                }
            }
            this.cTime3.setBase(SystemClock.elapsedRealtime() - ((long) this.history3Time));
            this.date3.setText(BuildConfig.FLAVOR + this.history3date);
            this.calories3.setText(BuildConfig.FLAVOR + this.history3Calories + " " + getResources().getString(R.string.kcal));
            this.startTime3.setText(BuildConfig.FLAVOR + this.history3startTime);
        }
        if (this.history4Time == 0.0d || this.history4date.equalsIgnoreCase("0") || this.history4date.equalsIgnoreCase(BuildConfig.FLAVOR)) {
            this.pace4.setText(BuildConfig.FLAVOR);
            this.startTime4.setText(BuildConfig.FLAVOR);
            this.distance4.setText(getResources().getString(R.string.noData));
            this.cTime4.setVisibility(4);
            this.date4.setText(BuildConfig.FLAVOR);
            this.calories4.setText(BuildConfig.FLAVOR);
        } else {
            if (this.units.equalsIgnoreCase("Metric")) {
                this.distance4.setText(BuildConfig.FLAVOR + this.history4Distance + " " + getResources().getString(R.string.km));
                this.pace4.setText(BuildConfig.FLAVOR + this.history4Pace + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.km));
            } else {
                try {
                    this.distance4.setText(BuildConfig.FLAVOR + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.parseDouble(this.history4Distance) * 0.621371d)}) + " " + getResources().getString(R.string.mi));
                    this.pace4.setText(BuildConfig.FLAVOR + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.parseDouble(this.history4Pace) * 1.609344d)}) + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.mi));
                } catch (Exception e4) {
                    this.distance4.setText("0");
                    this.pace4.setText("0");
                }
            }
            this.cTime4.setBase(SystemClock.elapsedRealtime() - ((long) this.history4Time));
            this.date4.setText(BuildConfig.FLAVOR + this.history4date);
            this.calories4.setText(BuildConfig.FLAVOR + this.history4Calories + " " + getResources().getString(R.string.kcal));
            this.startTime4.setText(BuildConfig.FLAVOR + this.history4startTime);
        }
        if (this.history5Time == 0.0d || this.history5date.equalsIgnoreCase("0") || this.history5date.equalsIgnoreCase(BuildConfig.FLAVOR)) {
            this.pace5.setText(BuildConfig.FLAVOR);
            this.startTime5.setText(BuildConfig.FLAVOR);
            this.distance5.setText(getResources().getString(R.string.noData));
            this.cTime5.setVisibility(4);
            this.date5.setText(BuildConfig.FLAVOR);
            this.calories5.setText(BuildConfig.FLAVOR);
        } else {
            if (this.units.equalsIgnoreCase("Metric")) {
                this.distance5.setText(BuildConfig.FLAVOR + this.history5Distance + " " + getResources().getString(R.string.km));
                this.pace5.setText(BuildConfig.FLAVOR + this.history5Pace + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.km));
            } else {
                try {
                    this.distance5.setText(BuildConfig.FLAVOR + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.parseDouble(this.history5Distance) * 0.621371d)}) + " " + getResources().getString(R.string.mi));
                    this.pace5.setText(BuildConfig.FLAVOR + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.parseDouble(this.history5Pace) * 1.609344d)}) + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.mi));
                } catch (Exception e5) {
                    this.distance5.setText("0");
                    this.pace5.setText("0");
                }
            }
            this.cTime5.setBase(SystemClock.elapsedRealtime() - ((long) this.history5Time));
            this.date5.setText(BuildConfig.FLAVOR + this.history5date);
            this.calories5.setText(BuildConfig.FLAVOR + this.history5Calories + " " + getResources().getString(R.string.kcal));
            this.startTime5.setText(BuildConfig.FLAVOR + this.history5startTime);
        }
        if (this.history6Time == 0.0d || this.history6date.equalsIgnoreCase("0") || this.history6date.equalsIgnoreCase(BuildConfig.FLAVOR)) {
            this.pace6.setText(BuildConfig.FLAVOR);
            this.startTime6.setText(BuildConfig.FLAVOR);
            this.distance6.setText(getResources().getString(R.string.noData));
            this.cTime6.setVisibility(4);
            this.date6.setText(BuildConfig.FLAVOR);
            this.calories6.setText(BuildConfig.FLAVOR);
        } else {
            if (this.units.equalsIgnoreCase("Metric")) {
                this.distance6.setText(BuildConfig.FLAVOR + this.history6Distance + " " + getResources().getString(R.string.km));
                this.pace6.setText(BuildConfig.FLAVOR + this.history6Pace + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.km));
            } else {
                try {
                    this.distance6.setText(BuildConfig.FLAVOR + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.parseDouble(this.history6Distance) * 0.621371d)}) + " " + getResources().getString(R.string.mi));
                    this.pace6.setText(BuildConfig.FLAVOR + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.parseDouble(this.history6Pace) * 1.609344d)}) + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.mi));
                } catch (Exception e6) {
                    this.distance6.setText("0");
                    this.pace6.setText("0");
                }
            }
            this.cTime6.setBase(SystemClock.elapsedRealtime() - ((long) this.history6Time));
            this.date6.setText(BuildConfig.FLAVOR + this.history6date);
            this.calories6.setText(BuildConfig.FLAVOR + this.history6Calories + " " + getResources().getString(R.string.kcal));
            this.startTime6.setText(BuildConfig.FLAVOR + this.history6startTime);
        }
        if (this.history7Time == 0.0d || this.history7date.equalsIgnoreCase("0") || this.history7date.equalsIgnoreCase(BuildConfig.FLAVOR)) {
            this.pace7.setText(BuildConfig.FLAVOR);
            this.startTime7.setText(BuildConfig.FLAVOR);
            this.distance7.setText(getResources().getString(R.string.noData));
            this.cTime7.setVisibility(4);
            this.date7.setText(BuildConfig.FLAVOR);
            this.calories7.setText(BuildConfig.FLAVOR);
        } else {
            if (this.units.equalsIgnoreCase("Metric")) {
                this.distance7.setText(BuildConfig.FLAVOR + this.history7Distance + " " + getResources().getString(R.string.km));
                this.pace7.setText(BuildConfig.FLAVOR + this.history7Pace + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.km));
            } else {
                try {
                    this.distance7.setText(BuildConfig.FLAVOR + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.parseDouble(this.history7Distance) * 0.621371d)}) + " " + getResources().getString(R.string.mi));
                    this.pace7.setText(BuildConfig.FLAVOR + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.parseDouble(this.history7Pace) * 1.609344d)}) + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.mi));
                } catch (Exception e7) {
                    this.distance7.setText("0");
                    this.pace7.setText("0");
                }
            }
            this.cTime7.setBase(SystemClock.elapsedRealtime() - ((long) this.history7Time));
            this.date7.setText(BuildConfig.FLAVOR + this.history7date);
            this.calories7.setText(BuildConfig.FLAVOR + this.history7Calories + " " + getResources().getString(R.string.kcal));
            this.startTime7.setText(BuildConfig.FLAVOR + this.history7startTime);
        }
        if (this.history8Time == 0.0d || this.history8date.equalsIgnoreCase("0") || this.history8date.equalsIgnoreCase(BuildConfig.FLAVOR)) {
            this.pace8.setText(BuildConfig.FLAVOR);
            this.startTime8.setText(BuildConfig.FLAVOR);
            this.distance8.setText(getResources().getString(R.string.noData));
            this.cTime8.setVisibility(4);
            this.date8.setText(BuildConfig.FLAVOR);
            this.calories8.setText(BuildConfig.FLAVOR);
        } else {
            if (this.units.equalsIgnoreCase("Metric")) {
                this.distance8.setText(BuildConfig.FLAVOR + this.history8Distance + " " + getResources().getString(R.string.km));
                this.pace8.setText(BuildConfig.FLAVOR + this.history8Pace + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.km));
            } else {
                try {
                    this.distance8.setText(BuildConfig.FLAVOR + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.parseDouble(this.history8Distance) * 0.621371d)}) + " " + getResources().getString(R.string.mi));
                    this.pace8.setText(BuildConfig.FLAVOR + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.parseDouble(this.history8Pace) * 1.609344d)}) + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.mi));
                } catch (Exception e8) {
                    this.distance8.setText("0");
                    this.pace8.setText("0");
                }
            }
            this.cTime8.setBase(SystemClock.elapsedRealtime() - ((long) this.history8Time));
            this.date8.setText(BuildConfig.FLAVOR + this.history8date);
            this.calories8.setText(BuildConfig.FLAVOR + this.history8Calories + " " + getResources().getString(R.string.kcal));
            this.startTime8.setText(BuildConfig.FLAVOR + this.history8startTime);
        }
        if (this.history9Time == 0.0d || this.history9date.equalsIgnoreCase("0") || this.history9date.equalsIgnoreCase(BuildConfig.FLAVOR)) {
            this.pace9.setText(BuildConfig.FLAVOR);
            this.startTime9.setText(BuildConfig.FLAVOR);
            this.distance9.setText(getResources().getString(R.string.noData));
            this.cTime9.setVisibility(4);
            this.date9.setText(BuildConfig.FLAVOR);
            this.calories9.setText(BuildConfig.FLAVOR);
        } else {
            if (this.units.equalsIgnoreCase("Metric")) {
                this.distance9.setText(BuildConfig.FLAVOR + this.history9Distance + " " + getResources().getString(R.string.km));
                this.pace9.setText(BuildConfig.FLAVOR + this.history9Pace + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.km));
            } else {
                try {
                    this.distance9.setText(BuildConfig.FLAVOR + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.parseDouble(this.history9Distance) * 0.621371d)}) + " " + getResources().getString(R.string.mi));
                    this.pace9.setText(BuildConfig.FLAVOR + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.parseDouble(this.history9Pace) * 1.609344d)}) + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.mi));
                } catch (Exception e9) {
                    this.distance9.setText("0");
                    this.pace9.setText("0");
                }
            }
            this.cTime9.setBase(SystemClock.elapsedRealtime() - ((long) this.history9Time));
            this.date9.setText(BuildConfig.FLAVOR + this.history9date);
            this.calories9.setText(BuildConfig.FLAVOR + this.history9Calories + " " + getResources().getString(R.string.kcal));
            this.startTime9.setText(BuildConfig.FLAVOR + this.history9startTime);
        }
        if (this.history10Time == 0.0d || this.history10date.equalsIgnoreCase("0") || this.history10date.equalsIgnoreCase(BuildConfig.FLAVOR)) {
            this.pace10.setText(BuildConfig.FLAVOR);
            this.startTime10.setText(BuildConfig.FLAVOR);
            this.distance10.setText(getResources().getString(R.string.noData));
            this.cTime10.setVisibility(4);
            this.date10.setText(BuildConfig.FLAVOR);
            this.calories10.setText(BuildConfig.FLAVOR);
            return;
        }
        if (this.units.equalsIgnoreCase("Metric")) {
            this.distance10.setText(BuildConfig.FLAVOR + this.history10Distance + " " + getResources().getString(R.string.km));
            this.pace10.setText(BuildConfig.FLAVOR + this.history10Pace + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.km));
        } else {
            try {
                this.distance10.setText(BuildConfig.FLAVOR + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.parseDouble(this.history10Distance) * 0.621371d)}) + " " + getResources().getString(R.string.mi));
                this.pace10.setText(BuildConfig.FLAVOR + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(Double.parseDouble(this.history10Pace) * 1.609344d)}) + " " + getResources().getString(R.string.min) + "/" + getResources().getString(R.string.mi));
            } catch (Exception e10) {
                this.distance10.setText("0");
                this.pace10.setText("0");
            }
        }
        this.cTime10.setBase(SystemClock.elapsedRealtime() - ((long) this.history10Time));
        this.date10.setText(BuildConfig.FLAVOR + this.history10date);
        this.calories10.setText(BuildConfig.FLAVOR + this.history10Calories + " " + getResources().getString(R.string.kcal));
        this.startTime10.setText(BuildConfig.FLAVOR + this.history10startTime);
    }

    private void delete(int id) {
        if (id == 1) {
            this.history1Pace = this.history2Pace;
            this.history1Distance = this.history2Distance;
            this.history1Time = this.history2Time;
            this.history1Calories = this.history2Calories;
            this.history1date = this.history2date;
            this.history1startTime = this.history2startTime;
            this.history1stopTime = this.history2stopTime;
            this.history1array = this.history2array;
            this.history1lowSpeed = this.history2lowSpeed;
            this.history1mediumSpeed = this.history2mediumSpeed;
            this.history1highSpeed = this.history2highSpeed;
            this.history1maxSpeed = this.history2maxSpeed;
            this.history2Pace = this.history3Pace;
            this.history2Distance = this.history3Distance;
            this.history2Time = this.history3Time;
            this.history2Calories = this.history3Calories;
            this.history2date = this.history3date;
            this.history2startTime = this.history3startTime;
            this.history2stopTime = this.history3stopTime;
            this.history2array = this.history3array;
            this.history2lowSpeed = this.history3lowSpeed;
            this.history2mediumSpeed = this.history3mediumSpeed;
            this.history2highSpeed = this.history3highSpeed;
            this.history2maxSpeed = this.history3maxSpeed;
            this.history3Pace = this.history4Pace;
            this.history3Distance = this.history4Distance;
            this.history3Time = this.history4Time;
            this.history3Calories = this.history4Calories;
            this.history3date = this.history4date;
            this.history3startTime = this.history4startTime;
            this.history3stopTime = this.history4stopTime;
            this.history3array = this.history4array;
            this.history3lowSpeed = this.history4lowSpeed;
            this.history3mediumSpeed = this.history4mediumSpeed;
            this.history3highSpeed = this.history4highSpeed;
            this.history3maxSpeed = this.history4maxSpeed;
            this.history4Pace = this.history5Pace;
            this.history4Distance = this.history5Distance;
            this.history4Time = this.history5Time;
            this.history4Calories = this.history5Calories;
            this.history4date = this.history5date;
            this.history4startTime = this.history5startTime;
            this.history4stopTime = this.history5stopTime;
            this.history4array = this.history5array;
            this.history4lowSpeed = this.history5lowSpeed;
            this.history4mediumSpeed = this.history5mediumSpeed;
            this.history4highSpeed = this.history5highSpeed;
            this.history4maxSpeed = this.history5maxSpeed;
            this.history5Pace = this.history6Pace;
            this.history5Distance = this.history6Distance;
            this.history5Time = this.history6Time;
            this.history5Calories = this.history6Calories;
            this.history5date = this.history6date;
            this.history5startTime = this.history6startTime;
            this.history5stopTime = this.history6stopTime;
            this.history5array = this.history6array;
            this.history5lowSpeed = this.history6lowSpeed;
            this.history5mediumSpeed = this.history6mediumSpeed;
            this.history5highSpeed = this.history6highSpeed;
            this.history5maxSpeed = this.history6maxSpeed;
            this.history6Pace = this.history7Pace;
            this.history6Distance = this.history7Distance;
            this.history6Time = this.history7Time;
            this.history6Calories = this.history7Calories;
            this.history6date = this.history7date;
            this.history6startTime = this.history7startTime;
            this.history6stopTime = this.history7stopTime;
            this.history6array = this.history7array;
            this.history6lowSpeed = this.history7lowSpeed;
            this.history6mediumSpeed = this.history7mediumSpeed;
            this.history6highSpeed = this.history7highSpeed;
            this.history6maxSpeed = this.history7maxSpeed;
            this.history7Pace = this.history8Pace;
            this.history7Distance = this.history8Distance;
            this.history7Time = this.history8Time;
            this.history7Calories = this.history8Calories;
            this.history7date = this.history8date;
            this.history7startTime = this.history8startTime;
            this.history7stopTime = this.history8stopTime;
            this.history7array = this.history8array;
            this.history7lowSpeed = this.history8lowSpeed;
            this.history7mediumSpeed = this.history8mediumSpeed;
            this.history7highSpeed = this.history8highSpeed;
            this.history7maxSpeed = this.history8maxSpeed;
            this.history8Pace = this.history9Pace;
            this.history8Distance = this.history9Distance;
            this.history8Time = this.history9Time;
            this.history8Calories = this.history9Calories;
            this.history8date = this.history9date;
            this.history8startTime = this.history9startTime;
            this.history8stopTime = this.history9stopTime;
            this.history8array = this.history9array;
            this.history8lowSpeed = this.history9lowSpeed;
            this.history8mediumSpeed = this.history9mediumSpeed;
            this.history8highSpeed = this.history9highSpeed;
            this.history8maxSpeed = this.history9maxSpeed;
            this.history9Pace = this.history10Pace;
            this.history9Distance = this.history10Distance;
            this.history9Time = this.history10Time;
            this.history9Calories = this.history10Calories;
            this.history9date = this.history10date;
            this.history9startTime = this.history10startTime;
            this.history9stopTime = this.history10stopTime;
            this.history9array = this.history10array;
            this.history9lowSpeed = this.history10lowSpeed;
            this.history9mediumSpeed = this.history10mediumSpeed;
            this.history9highSpeed = this.history10highSpeed;
            this.history9maxSpeed = this.history10maxSpeed;
            this.history10Pace = "0";
            this.history10Distance = "0";
            this.history10Calories = "0";
            this.history10Time = 0.0d;
            this.history10date = "0";
        } else if (id == 2) {
            this.history2Pace = this.history3Pace;
            this.history2Distance = this.history3Distance;
            this.history2Time = this.history3Time;
            this.history2Calories = this.history3Calories;
            this.history2date = this.history3date;
            this.history2startTime = this.history3startTime;
            this.history2stopTime = this.history3stopTime;
            this.history2array = this.history3array;
            this.history2lowSpeed = this.history3lowSpeed;
            this.history2mediumSpeed = this.history3mediumSpeed;
            this.history2highSpeed = this.history3highSpeed;
            this.history2maxSpeed = this.history3maxSpeed;
            this.history3Pace = this.history4Pace;
            this.history3Distance = this.history4Distance;
            this.history3Time = this.history4Time;
            this.history3Calories = this.history4Calories;
            this.history3date = this.history4date;
            this.history3startTime = this.history4startTime;
            this.history3stopTime = this.history4stopTime;
            this.history3array = this.history4array;
            this.history3lowSpeed = this.history4lowSpeed;
            this.history3mediumSpeed = this.history4mediumSpeed;
            this.history3highSpeed = this.history4highSpeed;
            this.history3maxSpeed = this.history4maxSpeed;
            this.history4Pace = this.history5Pace;
            this.history4Distance = this.history5Distance;
            this.history4Time = this.history5Time;
            this.history4Calories = this.history5Calories;
            this.history4date = this.history5date;
            this.history4startTime = this.history5startTime;
            this.history4stopTime = this.history5stopTime;
            this.history4array = this.history5array;
            this.history4lowSpeed = this.history5lowSpeed;
            this.history4mediumSpeed = this.history5mediumSpeed;
            this.history4highSpeed = this.history5highSpeed;
            this.history4maxSpeed = this.history5maxSpeed;
            this.history5Pace = this.history6Pace;
            this.history5Distance = this.history6Distance;
            this.history5Time = this.history6Time;
            this.history5Calories = this.history6Calories;
            this.history5date = this.history6date;
            this.history5startTime = this.history6startTime;
            this.history5stopTime = this.history6stopTime;
            this.history5array = this.history6array;
            this.history5lowSpeed = this.history6lowSpeed;
            this.history5mediumSpeed = this.history6mediumSpeed;
            this.history5highSpeed = this.history6highSpeed;
            this.history5maxSpeed = this.history6maxSpeed;
            this.history6Pace = this.history7Pace;
            this.history6Distance = this.history7Distance;
            this.history6Time = this.history7Time;
            this.history6Calories = this.history7Calories;
            this.history6date = this.history7date;
            this.history6startTime = this.history7startTime;
            this.history6stopTime = this.history7stopTime;
            this.history6array = this.history7array;
            this.history6lowSpeed = this.history7lowSpeed;
            this.history6mediumSpeed = this.history7mediumSpeed;
            this.history6highSpeed = this.history7highSpeed;
            this.history6maxSpeed = this.history7maxSpeed;
            this.history7Pace = this.history8Pace;
            this.history7Distance = this.history8Distance;
            this.history7Time = this.history8Time;
            this.history7Calories = this.history8Calories;
            this.history7date = this.history8date;
            this.history7startTime = this.history8startTime;
            this.history7stopTime = this.history8stopTime;
            this.history7array = this.history8array;
            this.history7lowSpeed = this.history8lowSpeed;
            this.history7mediumSpeed = this.history8mediumSpeed;
            this.history7highSpeed = this.history8highSpeed;
            this.history7maxSpeed = this.history8maxSpeed;
            this.history8Pace = this.history9Pace;
            this.history8Distance = this.history9Distance;
            this.history8Time = this.history9Time;
            this.history8Calories = this.history9Calories;
            this.history8date = this.history9date;
            this.history8startTime = this.history9startTime;
            this.history8stopTime = this.history9stopTime;
            this.history8array = this.history9array;
            this.history8lowSpeed = this.history9lowSpeed;
            this.history8mediumSpeed = this.history9mediumSpeed;
            this.history8highSpeed = this.history9highSpeed;
            this.history8maxSpeed = this.history9maxSpeed;
            this.history9Pace = this.history10Pace;
            this.history9Distance = this.history10Distance;
            this.history9Time = this.history10Time;
            this.history9Calories = this.history10Calories;
            this.history9date = this.history10date;
            this.history9startTime = this.history10startTime;
            this.history9stopTime = this.history10stopTime;
            this.history9array = this.history10array;
            this.history9lowSpeed = this.history10lowSpeed;
            this.history9mediumSpeed = this.history10mediumSpeed;
            this.history9highSpeed = this.history10highSpeed;
            this.history9maxSpeed = this.history10maxSpeed;
            this.history10Pace = "0";
            this.history10Distance = "0";
            this.history10Calories = "0";
            this.history10Time = 0.0d;
            this.history10date = "0";
        } else if (id == 3) {
            this.history3Pace = this.history4Pace;
            this.history3Distance = this.history4Distance;
            this.history3Time = this.history4Time;
            this.history3Calories = this.history4Calories;
            this.history3date = this.history4date;
            this.history3startTime = this.history4startTime;
            this.history3stopTime = this.history4stopTime;
            this.history3array = this.history4array;
            this.history3lowSpeed = this.history4lowSpeed;
            this.history3mediumSpeed = this.history4mediumSpeed;
            this.history3highSpeed = this.history4highSpeed;
            this.history3maxSpeed = this.history4maxSpeed;
            this.history4Pace = this.history5Pace;
            this.history4Distance = this.history5Distance;
            this.history4Time = this.history5Time;
            this.history4Calories = this.history5Calories;
            this.history4date = this.history5date;
            this.history4startTime = this.history5startTime;
            this.history4stopTime = this.history5stopTime;
            this.history4array = this.history5array;
            this.history4lowSpeed = this.history5lowSpeed;
            this.history4mediumSpeed = this.history5mediumSpeed;
            this.history4highSpeed = this.history5highSpeed;
            this.history4maxSpeed = this.history5maxSpeed;
            this.history5Pace = this.history6Pace;
            this.history5Distance = this.history6Distance;
            this.history5Time = this.history6Time;
            this.history5Calories = this.history6Calories;
            this.history5date = this.history6date;
            this.history5startTime = this.history6startTime;
            this.history5stopTime = this.history6stopTime;
            this.history5array = this.history6array;
            this.history5lowSpeed = this.history6lowSpeed;
            this.history5mediumSpeed = this.history6mediumSpeed;
            this.history5highSpeed = this.history6highSpeed;
            this.history5maxSpeed = this.history6maxSpeed;
            this.history6Pace = this.history7Pace;
            this.history6Distance = this.history7Distance;
            this.history6Time = this.history7Time;
            this.history6Calories = this.history7Calories;
            this.history6date = this.history7date;
            this.history6startTime = this.history7startTime;
            this.history6stopTime = this.history7stopTime;
            this.history6array = this.history7array;
            this.history6lowSpeed = this.history7lowSpeed;
            this.history6mediumSpeed = this.history7mediumSpeed;
            this.history6highSpeed = this.history7highSpeed;
            this.history6maxSpeed = this.history7maxSpeed;
            this.history7Pace = this.history8Pace;
            this.history7Distance = this.history8Distance;
            this.history7Time = this.history8Time;
            this.history7Calories = this.history8Calories;
            this.history7date = this.history8date;
            this.history7startTime = this.history8startTime;
            this.history7stopTime = this.history8stopTime;
            this.history7array = this.history8array;
            this.history7lowSpeed = this.history8lowSpeed;
            this.history7mediumSpeed = this.history8mediumSpeed;
            this.history7highSpeed = this.history8highSpeed;
            this.history7maxSpeed = this.history8maxSpeed;
            this.history8Pace = this.history9Pace;
            this.history8Distance = this.history9Distance;
            this.history8Time = this.history9Time;
            this.history8Calories = this.history9Calories;
            this.history8date = this.history9date;
            this.history8startTime = this.history9startTime;
            this.history8stopTime = this.history9stopTime;
            this.history8array = this.history9array;
            this.history8lowSpeed = this.history9lowSpeed;
            this.history8mediumSpeed = this.history9mediumSpeed;
            this.history8highSpeed = this.history9highSpeed;
            this.history8maxSpeed = this.history9maxSpeed;
            this.history9Pace = this.history10Pace;
            this.history9Distance = this.history10Distance;
            this.history9Time = this.history10Time;
            this.history9Calories = this.history10Calories;
            this.history9date = this.history10date;
            this.history9startTime = this.history10startTime;
            this.history9stopTime = this.history10stopTime;
            this.history9array = this.history10array;
            this.history9lowSpeed = this.history10lowSpeed;
            this.history9mediumSpeed = this.history10mediumSpeed;
            this.history9highSpeed = this.history10highSpeed;
            this.history9maxSpeed = this.history10maxSpeed;
            this.history10Pace = "0";
            this.history10Distance = "0";
            this.history10Calories = "0";
            this.history10Time = 0.0d;
            this.history10date = "0";
        } else if (id == 4) {
            this.history4Pace = this.history5Pace;
            this.history4Distance = this.history5Distance;
            this.history4Time = this.history5Time;
            this.history4Calories = this.history5Calories;
            this.history4date = this.history5date;
            this.history4startTime = this.history5startTime;
            this.history4stopTime = this.history5stopTime;
            this.history4array = this.history5array;
            this.history4lowSpeed = this.history5lowSpeed;
            this.history4mediumSpeed = this.history5mediumSpeed;
            this.history4highSpeed = this.history5highSpeed;
            this.history4maxSpeed = this.history5maxSpeed;
            this.history5Pace = this.history6Pace;
            this.history5Distance = this.history6Distance;
            this.history5Time = this.history6Time;
            this.history5Calories = this.history6Calories;
            this.history5date = this.history6date;
            this.history5startTime = this.history6startTime;
            this.history5stopTime = this.history6stopTime;
            this.history5array = this.history6array;
            this.history5lowSpeed = this.history6lowSpeed;
            this.history5mediumSpeed = this.history6mediumSpeed;
            this.history5highSpeed = this.history6highSpeed;
            this.history5maxSpeed = this.history6maxSpeed;
            this.history6Pace = this.history7Pace;
            this.history6Distance = this.history7Distance;
            this.history6Time = this.history7Time;
            this.history6Calories = this.history7Calories;
            this.history6date = this.history7date;
            this.history6startTime = this.history7startTime;
            this.history6stopTime = this.history7stopTime;
            this.history6array = this.history7array;
            this.history6lowSpeed = this.history7lowSpeed;
            this.history6mediumSpeed = this.history7mediumSpeed;
            this.history6highSpeed = this.history7highSpeed;
            this.history6maxSpeed = this.history7maxSpeed;
            this.history7Pace = this.history8Pace;
            this.history7Distance = this.history8Distance;
            this.history7Time = this.history8Time;
            this.history7Calories = this.history8Calories;
            this.history7date = this.history8date;
            this.history7startTime = this.history8startTime;
            this.history7stopTime = this.history8stopTime;
            this.history7array = this.history8array;
            this.history7lowSpeed = this.history8lowSpeed;
            this.history7mediumSpeed = this.history8mediumSpeed;
            this.history7highSpeed = this.history8highSpeed;
            this.history7maxSpeed = this.history8maxSpeed;
            this.history8Pace = this.history9Pace;
            this.history8Distance = this.history9Distance;
            this.history8Time = this.history9Time;
            this.history8Calories = this.history9Calories;
            this.history8date = this.history9date;
            this.history8startTime = this.history9startTime;
            this.history8stopTime = this.history9stopTime;
            this.history8array = this.history9array;
            this.history8lowSpeed = this.history9lowSpeed;
            this.history8mediumSpeed = this.history9mediumSpeed;
            this.history8highSpeed = this.history9highSpeed;
            this.history8maxSpeed = this.history9maxSpeed;
            this.history9Pace = this.history10Pace;
            this.history9Distance = this.history10Distance;
            this.history9Time = this.history10Time;
            this.history9Calories = this.history10Calories;
            this.history9date = this.history10date;
            this.history9startTime = this.history10startTime;
            this.history9stopTime = this.history10stopTime;
            this.history9array = this.history10array;
            this.history9lowSpeed = this.history10lowSpeed;
            this.history9mediumSpeed = this.history10mediumSpeed;
            this.history9highSpeed = this.history10highSpeed;
            this.history9maxSpeed = this.history10maxSpeed;
            this.history10Pace = "0";
            this.history10Distance = "0";
            this.history10Calories = "0";
            this.history10Time = 0.0d;
            this.history10date = "0";
        } else if (id == 5) {
            this.history5Pace = this.history6Pace;
            this.history5Distance = this.history6Distance;
            this.history5Time = this.history6Time;
            this.history5Calories = this.history6Calories;
            this.history5date = this.history6date;
            this.history5startTime = this.history6startTime;
            this.history5stopTime = this.history6stopTime;
            this.history5array = this.history6array;
            this.history5lowSpeed = this.history6lowSpeed;
            this.history5mediumSpeed = this.history6mediumSpeed;
            this.history5highSpeed = this.history6highSpeed;
            this.history5maxSpeed = this.history6maxSpeed;
            this.history6Pace = this.history7Pace;
            this.history6Distance = this.history7Distance;
            this.history6Time = this.history7Time;
            this.history6Calories = this.history7Calories;
            this.history6date = this.history7date;
            this.history6startTime = this.history7startTime;
            this.history6stopTime = this.history7stopTime;
            this.history6array = this.history7array;
            this.history6lowSpeed = this.history7lowSpeed;
            this.history6mediumSpeed = this.history7mediumSpeed;
            this.history6highSpeed = this.history7highSpeed;
            this.history6maxSpeed = this.history7maxSpeed;
            this.history7Pace = this.history8Pace;
            this.history7Distance = this.history8Distance;
            this.history7Time = this.history8Time;
            this.history7Calories = this.history8Calories;
            this.history7date = this.history8date;
            this.history7startTime = this.history8startTime;
            this.history7stopTime = this.history8stopTime;
            this.history7array = this.history8array;
            this.history7lowSpeed = this.history8lowSpeed;
            this.history7mediumSpeed = this.history8mediumSpeed;
            this.history7highSpeed = this.history8highSpeed;
            this.history7maxSpeed = this.history8maxSpeed;
            this.history8Pace = this.history9Pace;
            this.history8Distance = this.history9Distance;
            this.history8Time = this.history9Time;
            this.history8Calories = this.history9Calories;
            this.history8date = this.history9date;
            this.history8startTime = this.history9startTime;
            this.history8stopTime = this.history9stopTime;
            this.history8array = this.history9array;
            this.history8lowSpeed = this.history9lowSpeed;
            this.history8mediumSpeed = this.history9mediumSpeed;
            this.history8highSpeed = this.history9highSpeed;
            this.history8maxSpeed = this.history9maxSpeed;
            this.history9Pace = this.history10Pace;
            this.history9Distance = this.history10Distance;
            this.history9Time = this.history10Time;
            this.history9Calories = this.history10Calories;
            this.history9date = this.history10date;
            this.history9startTime = this.history10startTime;
            this.history9stopTime = this.history10stopTime;
            this.history9array = this.history10array;
            this.history9lowSpeed = this.history10lowSpeed;
            this.history9mediumSpeed = this.history10mediumSpeed;
            this.history9highSpeed = this.history10highSpeed;
            this.history9maxSpeed = this.history10maxSpeed;
            this.history10Pace = "0";
            this.history10Distance = "0";
            this.history10Calories = "0";
            this.history10Time = 0.0d;
            this.history10date = "0";
        } else if (id == 6) {
            this.history6Pace = this.history7Pace;
            this.history6Distance = this.history7Distance;
            this.history6Time = this.history7Time;
            this.history6Calories = this.history7Calories;
            this.history6date = this.history7date;
            this.history6startTime = this.history7startTime;
            this.history6stopTime = this.history7stopTime;
            this.history6array = this.history7array;
            this.history6lowSpeed = this.history7lowSpeed;
            this.history6mediumSpeed = this.history7mediumSpeed;
            this.history6highSpeed = this.history7highSpeed;
            this.history6maxSpeed = this.history7maxSpeed;
            this.history7Pace = this.history8Pace;
            this.history7Distance = this.history8Distance;
            this.history7Time = this.history8Time;
            this.history7Calories = this.history8Calories;
            this.history7date = this.history8date;
            this.history7startTime = this.history8startTime;
            this.history7stopTime = this.history8stopTime;
            this.history7array = this.history8array;
            this.history7lowSpeed = this.history8lowSpeed;
            this.history7mediumSpeed = this.history8mediumSpeed;
            this.history7highSpeed = this.history8highSpeed;
            this.history7maxSpeed = this.history8maxSpeed;
            this.history8Pace = this.history9Pace;
            this.history8Distance = this.history9Distance;
            this.history8Time = this.history9Time;
            this.history8Calories = this.history9Calories;
            this.history8date = this.history9date;
            this.history8startTime = this.history9startTime;
            this.history8stopTime = this.history9stopTime;
            this.history8array = this.history9array;
            this.history8lowSpeed = this.history9lowSpeed;
            this.history8mediumSpeed = this.history9mediumSpeed;
            this.history8highSpeed = this.history9highSpeed;
            this.history8maxSpeed = this.history9maxSpeed;
            this.history9Pace = this.history10Pace;
            this.history9Distance = this.history10Distance;
            this.history9Time = this.history10Time;
            this.history9Calories = this.history10Calories;
            this.history9date = this.history10date;
            this.history9startTime = this.history10startTime;
            this.history9stopTime = this.history10stopTime;
            this.history9array = this.history10array;
            this.history9lowSpeed = this.history10lowSpeed;
            this.history9mediumSpeed = this.history10mediumSpeed;
            this.history9highSpeed = this.history10highSpeed;
            this.history9maxSpeed = this.history10maxSpeed;
            this.history10Pace = "0";
            this.history10Distance = "0";
            this.history10Calories = "0";
            this.history10Time = 0.0d;
            this.history10date = "0";
        } else if (id == 7) {
            this.history7Pace = this.history8Pace;
            this.history7Distance = this.history8Distance;
            this.history7Time = this.history8Time;
            this.history7Calories = this.history8Calories;
            this.history7date = this.history8date;
            this.history7startTime = this.history8startTime;
            this.history7stopTime = this.history8stopTime;
            this.history7array = this.history8array;
            this.history7lowSpeed = this.history8lowSpeed;
            this.history7mediumSpeed = this.history8mediumSpeed;
            this.history7highSpeed = this.history8highSpeed;
            this.history7maxSpeed = this.history8maxSpeed;
            this.history8Pace = this.history9Pace;
            this.history8Distance = this.history9Distance;
            this.history8Time = this.history9Time;
            this.history8Calories = this.history9Calories;
            this.history8date = this.history9date;
            this.history8startTime = this.history9startTime;
            this.history8stopTime = this.history9stopTime;
            this.history8array = this.history9array;
            this.history8lowSpeed = this.history9lowSpeed;
            this.history8mediumSpeed = this.history9mediumSpeed;
            this.history8highSpeed = this.history9highSpeed;
            this.history8maxSpeed = this.history9maxSpeed;
            this.history9Pace = this.history10Pace;
            this.history9Distance = this.history10Distance;
            this.history9Time = this.history10Time;
            this.history9Calories = this.history10Calories;
            this.history9date = this.history10date;
            this.history9startTime = this.history10startTime;
            this.history9stopTime = this.history10stopTime;
            this.history9array = this.history10array;
            this.history9lowSpeed = this.history10lowSpeed;
            this.history9mediumSpeed = this.history10mediumSpeed;
            this.history9highSpeed = this.history10highSpeed;
            this.history9maxSpeed = this.history10maxSpeed;
            this.history10Pace = "0";
            this.history10Distance = "0";
            this.history10Calories = "0";
            this.history10Time = 0.0d;
            this.history10date = "0";
        } else if (id == 8) {
            this.history8Pace = this.history9Pace;
            this.history8Distance = this.history9Distance;
            this.history8Time = this.history9Time;
            this.history8Calories = this.history9Calories;
            this.history8date = this.history9date;
            this.history8startTime = this.history9startTime;
            this.history8stopTime = this.history9stopTime;
            this.history8array = this.history9array;
            this.history8lowSpeed = this.history9lowSpeed;
            this.history8mediumSpeed = this.history9mediumSpeed;
            this.history8highSpeed = this.history9highSpeed;
            this.history8maxSpeed = this.history9maxSpeed;
            this.history9Pace = this.history10Pace;
            this.history9Distance = this.history10Distance;
            this.history9Time = this.history10Time;
            this.history9Calories = this.history10Calories;
            this.history9date = this.history10date;
            this.history9startTime = this.history10startTime;
            this.history9stopTime = this.history10stopTime;
            this.history9array = this.history10array;
            this.history9lowSpeed = this.history10lowSpeed;
            this.history9mediumSpeed = this.history10mediumSpeed;
            this.history9highSpeed = this.history10highSpeed;
            this.history9maxSpeed = this.history10maxSpeed;
            this.history10Pace = "0";
            this.history10Distance = "0";
            this.history10Calories = "0";
            this.history10Time = 0.0d;
            this.history10date = "0";
        } else if (id == 9) {
            this.history9Pace = this.history10Pace;
            this.history9Distance = this.history10Distance;
            this.history9Time = this.history10Time;
            this.history9Calories = this.history10Calories;
            this.history9date = this.history10date;
            this.history9startTime = this.history10startTime;
            this.history9stopTime = this.history10stopTime;
            this.history9array = this.history10array;
            this.history9lowSpeed = this.history10lowSpeed;
            this.history9mediumSpeed = this.history10mediumSpeed;
            this.history9highSpeed = this.history10highSpeed;
            this.history9maxSpeed = this.history10maxSpeed;
            this.history10Pace = "0";
            this.history10Distance = "0";
            this.history10Calories = "0";
            this.history10Time = 0.0d;
            this.history10date = "0";
        } else if (id == 10) {
            this.history10Pace = "0";
            this.history10Distance = "0";
            this.history10Calories = "0";
            this.history10Time = 0.0d;
            this.history10date = "0";
        }
        this.qasa2.edit().putString("history1Pace", this.history1Pace).apply();
        this.qasa2.edit().putString("history2Pace", this.history2Pace).apply();
        this.qasa2.edit().putString("history3Pace", this.history3Pace).apply();
        this.qasa2.edit().putString("history4Pace", this.history4Pace).apply();
        this.qasa2.edit().putString("history5Pace", this.history5Pace).apply();
        this.qasa2.edit().putString("history6Pace", this.history6Pace).apply();
        this.qasa2.edit().putString("history7Pace", this.history7Pace).apply();
        this.qasa2.edit().putString("history8Pace", this.history8Pace).apply();
        this.qasa2.edit().putString("history9Pace", this.history9Pace).apply();
        this.qasa2.edit().putString("history10Pace", this.history10Pace).apply();
        this.qasa2.edit().putString("history1array", this.history1array).apply();
        this.qasa2.edit().putString("history2array", this.history2array).apply();
        this.qasa2.edit().putString("history3array", this.history3array).apply();
        this.qasa2.edit().putString("history4array", this.history4array).apply();
        this.qasa2.edit().putString("history5array", this.history5array).apply();
        this.qasa2.edit().putString("history6array", this.history6array).apply();
        this.qasa2.edit().putString("history7array", this.history7array).apply();
        this.qasa2.edit().putString("history8array", this.history8array).apply();
        this.qasa2.edit().putString("history9array", this.history9array).apply();
        this.qasa2.edit().putString("history10array", this.history10array).apply();
        this.qasa2.edit().putString("history1Distance", this.history1Distance).apply();
        this.qasa2.edit().putString("history2Distance", this.history2Distance).apply();
        this.qasa2.edit().putString("history3Distance", this.history3Distance).apply();
        this.qasa2.edit().putString("history4Distance", this.history4Distance).apply();
        this.qasa2.edit().putString("history5Distance", this.history5Distance).apply();
        this.qasa2.edit().putString("history6Distance", this.history6Distance).apply();
        this.qasa2.edit().putString("history7Distance", this.history7Distance).apply();
        this.qasa2.edit().putString("history8Distance", this.history8Distance).apply();
        this.qasa2.edit().putString("history9Distance", this.history9Distance).apply();
        this.qasa2.edit().putString("history10Distance", this.history10Distance).apply();
        this.qasa2.edit().putLong("history1Duration", Double.doubleToRawLongBits(this.history1Time)).apply();
        this.qasa2.edit().putLong("history2Duration", Double.doubleToRawLongBits(this.history2Time)).apply();
        this.qasa2.edit().putLong("history3Duration", Double.doubleToRawLongBits(this.history3Time)).apply();
        this.qasa2.edit().putLong("history4Duration", Double.doubleToRawLongBits(this.history4Time)).apply();
        this.qasa2.edit().putLong("history5Duration", Double.doubleToRawLongBits(this.history5Time)).apply();
        this.qasa2.edit().putLong("history6Duration", Double.doubleToRawLongBits(this.history6Time)).apply();
        this.qasa2.edit().putLong("history7Duration", Double.doubleToRawLongBits(this.history7Time)).apply();
        this.qasa2.edit().putLong("history8Duration", Double.doubleToRawLongBits(this.history8Time)).apply();
        this.qasa2.edit().putLong("history9Duration", Double.doubleToRawLongBits(this.history9Time)).apply();
        this.qasa2.edit().putLong("history10Duration", Double.doubleToRawLongBits(this.history10Time)).apply();
        this.qasa2.edit().putString("history1Calories", this.history1Calories).apply();
        this.qasa2.edit().putString("history2Calories", this.history2Calories).apply();
        this.qasa2.edit().putString("history3Calories", this.history3Calories).apply();
        this.qasa2.edit().putString("history4Calories", this.history4Calories).apply();
        this.qasa2.edit().putString("history5Calories", this.history5Calories).apply();
        this.qasa2.edit().putString("history6Calories", this.history6Calories).apply();
        this.qasa2.edit().putString("history7Calories", this.history7Calories).apply();
        this.qasa2.edit().putString("history8Calories", this.history8Calories).apply();
        this.qasa2.edit().putString("history9Calories", this.history9Calories).apply();
        this.qasa2.edit().putString("history10Calories", this.history10Calories).apply();
        this.qasa2.edit().putString("history1Date", this.history1date).apply();
        this.qasa2.edit().putString("history2Date", this.history2date).apply();
        this.qasa2.edit().putString("history3Date", this.history3date).apply();
        this.qasa2.edit().putString("history4Date", this.history4date).apply();
        this.qasa2.edit().putString("history5Date", this.history5date).apply();
        this.qasa2.edit().putString("history6Date", this.history6date).apply();
        this.qasa2.edit().putString("history7Date", this.history7date).apply();
        this.qasa2.edit().putString("history8Date", this.history8date).apply();
        this.qasa2.edit().putString("history9Date", this.history9date).apply();
        this.qasa2.edit().putString("history10Date", this.history10date).apply();
        this.qasa2.edit().putString("history1startTime", this.history1startTime).apply();
        this.qasa2.edit().putString("history2startTime", this.history2startTime).apply();
        this.qasa2.edit().putString("history3startTime", this.history3startTime).apply();
        this.qasa2.edit().putString("history4startTime", this.history4startTime).apply();
        this.qasa2.edit().putString("history5startTime", this.history5startTime).apply();
        this.qasa2.edit().putString("history6startTime", this.history6startTime).apply();
        this.qasa2.edit().putString("history7startTime", this.history7startTime).apply();
        this.qasa2.edit().putString("history8startTime", this.history8startTime).apply();
        this.qasa2.edit().putString("history9startTime", this.history9startTime).apply();
        this.qasa2.edit().putString("history10startTime", this.history10startTime).apply();
        this.qasa2.edit().putString("history1stopTime", this.history1stopTime).apply();
        this.qasa2.edit().putString("history2stopTime", this.history2stopTime).apply();
        this.qasa2.edit().putString("history3stopTime", this.history3stopTime).apply();
        this.qasa2.edit().putString("history4stopTime", this.history4stopTime).apply();
        this.qasa2.edit().putString("history5stopTime", this.history5stopTime).apply();
        this.qasa2.edit().putString("history6stopTime", this.history6stopTime).apply();
        this.qasa2.edit().putString("history7stopTime", this.history7stopTime).apply();
        this.qasa2.edit().putString("history8stopTime", this.history8stopTime).apply();
        this.qasa2.edit().putString("history9stopTime", this.history9stopTime).apply();
        this.qasa2.edit().putString("history10stopTime", this.history10stopTime).apply();
        this.qasa2.edit().putString("history1lowSpeed", this.history1lowSpeed).apply();
        this.qasa2.edit().putString("history2lowSpeed", this.history2lowSpeed).apply();
        this.qasa2.edit().putString("history3lowSpeed", this.history3lowSpeed).apply();
        this.qasa2.edit().putString("history4lowSpeed", this.history4lowSpeed).apply();
        this.qasa2.edit().putString("history5lowSpeed", this.history5lowSpeed).apply();
        this.qasa2.edit().putString("history6lowSpeed", this.history6lowSpeed).apply();
        this.qasa2.edit().putString("history7lowSpeed", this.history7lowSpeed).apply();
        this.qasa2.edit().putString("history8lowSpeed", this.history8lowSpeed).apply();
        this.qasa2.edit().putString("history9lowSpeed", this.history9lowSpeed).apply();
        this.qasa2.edit().putString("history10lowSpeed", this.history10lowSpeed).apply();
        this.qasa2.edit().putString("history1mediumSpeed", this.history1mediumSpeed).apply();
        this.qasa2.edit().putString("history2mediumSpeed", this.history2mediumSpeed).apply();
        this.qasa2.edit().putString("history3mediumSpeed", this.history3mediumSpeed).apply();
        this.qasa2.edit().putString("history4mediumSpeed", this.history4mediumSpeed).apply();
        this.qasa2.edit().putString("history5mediumSpeed", this.history5mediumSpeed).apply();
        this.qasa2.edit().putString("history6mediumSpeed", this.history6mediumSpeed).apply();
        this.qasa2.edit().putString("history7mediumSpeed", this.history7mediumSpeed).apply();
        this.qasa2.edit().putString("history8mediumSpeed", this.history8mediumSpeed).apply();
        this.qasa2.edit().putString("history9mediumSpeed", this.history9mediumSpeed).apply();
        this.qasa2.edit().putString("history10mediumSpeed", this.history10mediumSpeed).apply();
        this.qasa2.edit().putString("history1highSpeed", this.history1highSpeed).apply();
        this.qasa2.edit().putString("history2highSpeed", this.history2highSpeed).apply();
        this.qasa2.edit().putString("history3highSpeed", this.history3highSpeed).apply();
        this.qasa2.edit().putString("history4highSpeed", this.history4highSpeed).apply();
        this.qasa2.edit().putString("history5highSpeed", this.history5highSpeed).apply();
        this.qasa2.edit().putString("history6highSpeed", this.history6highSpeed).apply();
        this.qasa2.edit().putString("history7highSpeed", this.history7highSpeed).apply();
        this.qasa2.edit().putString("history8highSpeed", this.history8highSpeed).apply();
        this.qasa2.edit().putString("history9highSpeed", this.history9highSpeed).apply();
        this.qasa2.edit().putString("history10highSpeed", this.history10highSpeed).apply();
        this.qasa2.edit().putString("history1maxSpeed", this.history1maxSpeed).apply();
        this.qasa2.edit().putString("history2maxSpeed", this.history2maxSpeed).apply();
        this.qasa2.edit().putString("history3maxSpeed", this.history3maxSpeed).apply();
        this.qasa2.edit().putString("history4maxSpeed", this.history4maxSpeed).apply();
        this.qasa2.edit().putString("history5maxSpeed", this.history5maxSpeed).apply();
        this.qasa2.edit().putString("history6maxSpeed", this.history6maxSpeed).apply();
        this.qasa2.edit().putString("history7maxSpeed", this.history7maxSpeed).apply();
        this.qasa2.edit().putString("history8maxSpeed", this.history8maxSpeed).apply();
        this.qasa2.edit().putString("history9maxSpeed", this.history9maxSpeed).apply();
        this.qasa2.edit().putString("history10maxSpeed", this.history10maxSpeed).apply();
        readHistory();
        showHistory();
    }

    private void more(int id) {
        boolean isDeleted = false;
        if (id == 1) {
            if (this.history1Time == 0.0d || this.history1date.equalsIgnoreCase("0") || this.history1stopTime.equalsIgnoreCase("0")) {
                isDeleted = true;
            } else {
                this.timeReport = this.history1Time;
                this.distanceReport = this.history1Distance;
                this.caloriesReport = this.history1Calories;
                this.arrayString = "history1array";
                this.maxSpeedReport = this.history1maxSpeed;
                this.startTimeReport = this.history1startTime;
                this.dateReport = this.history1date;
                this.stopTimeReport = this.history1stopTime;
                this.lowSpeedReport = this.history1lowSpeed;
                this.mediumSpeedReport = this.history1mediumSpeed;
                this.highSpeedReport = this.history1highSpeed;
            }
        } else if (id == 2) {
            if (this.history2Time == 0.0d || this.history2date.equalsIgnoreCase("0") || this.history2stopTime.equalsIgnoreCase("0")) {
                isDeleted = true;
            } else {
                this.timeReport = this.history2Time;
                this.distanceReport = this.history2Distance;
                this.caloriesReport = this.history2Calories;
                this.arrayString = "history2array";
                this.maxSpeedReport = this.history2maxSpeed;
                this.startTimeReport = this.history2startTime;
                this.dateReport = this.history2date;
                this.stopTimeReport = this.history2stopTime;
                this.lowSpeedReport = this.history2lowSpeed;
                this.mediumSpeedReport = this.history2mediumSpeed;
                this.highSpeedReport = this.history2highSpeed;
            }
        } else if (id == 3) {
            if (this.history3Time == 0.0d || this.history3date.equalsIgnoreCase("0") || this.history3stopTime.equalsIgnoreCase("0")) {
                isDeleted = true;
            } else {
                this.timeReport = this.history3Time;
                this.distanceReport = this.history3Distance;
                this.caloriesReport = this.history3Calories;
                this.arrayString = "history3array";
                this.maxSpeedReport = this.history3maxSpeed;
                this.startTimeReport = this.history3startTime;
                this.dateReport = this.history3date;
                this.stopTimeReport = this.history3stopTime;
                this.lowSpeedReport = this.history3lowSpeed;
                this.mediumSpeedReport = this.history3mediumSpeed;
                this.highSpeedReport = this.history3highSpeed;
            }
        } else if (id == 4) {
            if (this.history4Time == 0.0d || this.history4date.equalsIgnoreCase("0") || this.history4stopTime.equalsIgnoreCase("0")) {
                isDeleted = true;
            } else {
                this.timeReport = this.history4Time;
                this.distanceReport = this.history4Distance;
                this.caloriesReport = this.history4Calories;
                this.arrayString = "history4array";
                this.maxSpeedReport = this.history4maxSpeed;
                this.startTimeReport = this.history4startTime;
                this.dateReport = this.history4date;
                this.stopTimeReport = this.history4stopTime;
                this.lowSpeedReport = this.history4lowSpeed;
                this.mediumSpeedReport = this.history4mediumSpeed;
                this.highSpeedReport = this.history4highSpeed;
            }
        } else if (id == 5) {
            if (this.history5Time == 0.0d || this.history5date.equalsIgnoreCase("0") || this.history5stopTime.equalsIgnoreCase("0")) {
                isDeleted = true;
            } else {
                this.timeReport = this.history5Time;
                this.distanceReport = this.history5Distance;
                this.caloriesReport = this.history5Calories;
                this.arrayString = "history5array";
                this.maxSpeedReport = this.history5maxSpeed;
                this.startTimeReport = this.history5startTime;
                this.dateReport = this.history5date;
                this.stopTimeReport = this.history5stopTime;
                this.lowSpeedReport = this.history5lowSpeed;
                this.mediumSpeedReport = this.history5mediumSpeed;
                this.highSpeedReport = this.history5highSpeed;
            }
        } else if (id == 6) {
            if (this.history6Time == 0.0d || this.history6date.equalsIgnoreCase("0") || this.history6stopTime.equalsIgnoreCase("0")) {
                isDeleted = true;
            } else {
                this.timeReport = this.history6Time;
                this.distanceReport = this.history6Distance;
                this.caloriesReport = this.history6Calories;
                this.arrayString = "history6array";
                this.maxSpeedReport = this.history6maxSpeed;
                this.startTimeReport = this.history6startTime;
                this.dateReport = this.history6date;
                this.stopTimeReport = this.history6stopTime;
                this.lowSpeedReport = this.history6lowSpeed;
                this.mediumSpeedReport = this.history6mediumSpeed;
                this.highSpeedReport = this.history6highSpeed;
            }
        } else if (id == 7) {
            if (this.history7Time == 0.0d || this.history7date.equalsIgnoreCase("0") || this.history7stopTime.equalsIgnoreCase("0")) {
                isDeleted = true;
            } else {
                this.timeReport = this.history7Time;
                this.distanceReport = this.history7Distance;
                this.caloriesReport = this.history7Calories;
                this.arrayString = "history7array";
                this.maxSpeedReport = this.history7maxSpeed;
                this.startTimeReport = this.history7startTime;
                this.dateReport = this.history7date;
                this.stopTimeReport = this.history7stopTime;
                this.lowSpeedReport = this.history7lowSpeed;
                this.mediumSpeedReport = this.history7mediumSpeed;
                this.highSpeedReport = this.history7highSpeed;
            }
        } else if (id == 8) {
            if (this.history8Time == 0.0d || this.history8date.equalsIgnoreCase("0") || this.history8stopTime.equalsIgnoreCase("0")) {
                isDeleted = true;
            } else {
                this.timeReport = this.history8Time;
                this.distanceReport = this.history8Distance;
                this.caloriesReport = this.history8Calories;
                this.arrayString = "history8array";
                this.maxSpeedReport = this.history8maxSpeed;
                this.startTimeReport = this.history8startTime;
                this.dateReport = this.history8date;
                this.stopTimeReport = this.history8stopTime;
                this.lowSpeedReport = this.history8lowSpeed;
                this.mediumSpeedReport = this.history8mediumSpeed;
                this.highSpeedReport = this.history8highSpeed;
            }
        } else if (id == 9) {
            if (this.history9Time == 0.0d || this.history9date.equalsIgnoreCase("0") || this.history9stopTime.equalsIgnoreCase("0")) {
                isDeleted = true;
            } else {
                this.timeReport = this.history9Time;
                this.distanceReport = this.history9Distance;
                this.caloriesReport = this.history9Calories;
                this.arrayString = "history9array";
                this.maxSpeedReport = this.history9maxSpeed;
                this.startTimeReport = this.history9startTime;
                this.dateReport = this.history9date;
                this.stopTimeReport = this.history9stopTime;
                this.lowSpeedReport = this.history9lowSpeed;
                this.mediumSpeedReport = this.history9mediumSpeed;
                this.highSpeedReport = this.history9highSpeed;
            }
        } else if (id == 10) {
            if (this.history10Time == 0.0d || this.history10date.equalsIgnoreCase("0") || this.history10stopTime.equalsIgnoreCase("0")) {
                isDeleted = true;
            } else {
                this.timeReport = this.history10Time;
                this.distanceReport = this.history10Distance;
                this.caloriesReport = this.history10Calories;
                this.arrayString = "history10array";
                this.maxSpeedReport = this.history10maxSpeed;
                this.startTimeReport = this.history10startTime;
                this.dateReport = this.history10date;
                this.stopTimeReport = this.history10stopTime;
                this.lowSpeedReport = this.history10lowSpeed;
                this.mediumSpeedReport = this.history10mediumSpeed;
                this.highSpeedReport = this.history10highSpeed;
            }
        }
        if (!isDeleted) {
            Intent a = new Intent(this.context, QuoreReport.class);
            a.putExtra("duration", this.timeReport);
            a.putExtra("distance", this.distanceReport);
            a.putExtra("calories", this.caloriesReport);
            a.putExtra("arrayString", this.arrayString);
            a.putExtra("maxSpeed", this.maxSpeedReport);
            a.putExtra("startTime", BuildConfig.FLAVOR + this.startTimeReport);
            a.putExtra("stopTime", BuildConfig.FLAVOR + this.stopTimeReport);
            a.putExtra("date", BuildConfig.FLAVOR + this.dateReport);
            a.putExtra("lowSpeed", BuildConfig.FLAVOR + this.lowSpeedReport);
            a.putExtra("mediumSpeed", BuildConfig.FLAVOR + this.mediumSpeedReport);
            a.putExtra("highSpeed", BuildConfig.FLAVOR + this.highSpeedReport);
            a.putExtra("units", BuildConfig.FLAVOR + this.units);
            startActivity(a);
        }
    }

    public void onResume() {
        super.onResume();
        this.units = this.qasa2.getString("units", "Metric");
        showHistory();
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onDetach() {
        super.onDetach();
    }
}
