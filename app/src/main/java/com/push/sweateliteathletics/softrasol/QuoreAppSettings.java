package com.push.sweateliteathletics.softrasol;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView.BufferType;

public class QuoreAppSettings extends AppCompatActivity {
    private AlertDialog AlertDialogDateFormat;
    private AlertDialog AlertDialogGoals;
    private AlertDialog AlertDialogMapType;
    private AlertDialog AlertDialogUnits;
    private Switch autoPauseSwitch;
    private EditText etGoalCalories;
    private EditText etGoalDistance;
    private EditText etGoalDuration;
    private String goalCalories = BuildConfig.FLAVOR;
    private String goalDistance = BuildConfig.FLAVOR;
    private String goalDuration;
    private Switch goalNotifSwitch;
    private boolean isAutoPauseOn;
    private boolean isGoalNotifOn;
    private boolean isScreenOn;
    private SharedPreferences qasa2;
    private Switch screenOnSwitch;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);
        setTitle(getResources().getString(R.string.Settings));
        RelativeLayout layDate = (RelativeLayout) findViewById(R.id.layDate);
        RelativeLayout layKeepScreenOn = (RelativeLayout) findViewById(R.id.layKeepScreenOn);
        RelativeLayout layMapType = (RelativeLayout) findViewById(R.id.layMapType);
        RelativeLayout layDateFormat = (RelativeLayout) findViewById(R.id.layDateFormat);
        RelativeLayout layGoalDistance = (RelativeLayout) findViewById(R.id.layGoalDistance);
        RelativeLayout layGoalCalories = (RelativeLayout) findViewById(R.id.layGoalCalories);
        RelativeLayout layGoalDuration = (RelativeLayout) findViewById(R.id.layGoalDuration);
        RelativeLayout layGoalNotif = (RelativeLayout) findViewById(R.id.layGoalNotif);
        RelativeLayout layUnits = (RelativeLayout) findViewById(R.id.layUnits);
        RelativeLayout layVoiceNotif = (RelativeLayout) findViewById(R.id.layVoiceNotif);
        RelativeLayout layAutoPause = (RelativeLayout) findViewById(R.id.layAutoPause);

        this.screenOnSwitch = (Switch) findViewById(R.id.screenOnSwitch);
        this.goalNotifSwitch = (Switch) findViewById(R.id.goalNotifSwitch);
        this.autoPauseSwitch = (Switch) findViewById(R.id.autoPauseSwitch);
        String PREFS_NAME = "qA1sa2";
        this.qasa2 = getSharedPreferences("qA1sa2", 0);
        this.isScreenOn = this.qasa2.getBoolean("isScreenOn", false);
        this.isGoalNotifOn = this.qasa2.getBoolean("isGoalNotifOn", true);
        this.isAutoPauseOn = this.qasa2.getBoolean("isAutoPauseOn", false);
        String versionCode = BuildConfig.FLAVOR;
        try {
            versionCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (Exception e) {
            versionCode = "1.1.1";
        }

        this.screenOnSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                QuoreAppSettings.this.qasa2.edit().putBoolean("isScreenOn", isChecked).apply();
                QuoreAppSettings.this.isScreenOn = isChecked;
            }
        });
        this.goalNotifSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                QuoreAppSettings.this.qasa2.edit().putBoolean("isGoalNotifOn", isChecked).apply();
                QuoreAppSettings.this.isGoalNotifOn = isChecked;
            }
        });
        this.screenOnSwitch.setChecked(this.isScreenOn);
        this.goalNotifSwitch.setChecked(this.isGoalNotifOn);
        layKeepScreenOn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreAppSettings.this.screenOnSwitch.setChecked(!QuoreAppSettings.this.isScreenOn);
            }
        });
        layGoalNotif.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreAppSettings.this.goalNotifSwitch.setChecked(!QuoreAppSettings.this.isGoalNotifOn);
            }
        });
        this.autoPauseSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                QuoreAppSettings.this.qasa2.edit().putBoolean("isAutoPauseOn", isChecked).apply();
                QuoreAppSettings.this.isAutoPauseOn = isChecked;
            }
        });
        this.autoPauseSwitch.setChecked(this.isAutoPauseOn);
        layAutoPause.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreAppSettings.this.autoPauseSwitch.setChecked(!QuoreAppSettings.this.isAutoPauseOn);
            }
        });
        layVoiceNotif.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreAppSettings.this.startActivity(new Intent(QuoreAppSettings.this, SpeakOption.class));
            }
        });
        layDate.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreAppSettings.this.startActivity(new Intent(QuoreAppSettings.this, QuoreChangeData.class));
            }
        });

        layDateFormat.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int dateFormat = Integer.parseInt(QuoreAppSettings.this.qasa2.getString("dateFormat", "1"));
                Builder builder = new Builder(QuoreAppSettings.this);
                builder.setTitle(QuoreAppSettings.this.getResources().getString(R.string.Date_format));
                builder.setSingleChoiceItems(QuoreAppSettings.this.getResources().getStringArray(R.array.dateFormat), dateFormat - 1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case R.styleable.View_android_theme /*0*/:
                                QuoreAppSettings.this.qasa2.edit().putString("dateFormat", "1").apply();
                                break;
                            case R.styleable.View_android_focusable /*1*/:
                                QuoreAppSettings.this.qasa2.edit().putString("dateFormat", "2").apply();
                                break;
                            case R.styleable.View_paddingEnd /*2*/:
                                QuoreAppSettings.this.qasa2.edit().putString("dateFormat", "3").apply();
                                break;
                            case R.styleable.View_paddingStart /*3*/:
                                QuoreAppSettings.this.qasa2.edit().putString("dateFormat", "4").apply();
                                break;
                        }
                        QuoreAppSettings.this.AlertDialogDateFormat.dismiss();
                    }
                });
                QuoreAppSettings.this.AlertDialogDateFormat = builder.create();
                QuoreAppSettings.this.AlertDialogDateFormat.show();
            }
        });
        layMapType.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int mapType = Integer.parseInt(QuoreAppSettings.this.qasa2.getString("mapType", "1"));
                Builder builder = new Builder(QuoreAppSettings.this);
                builder.setTitle(QuoreAppSettings.this.getResources().getString(R.string.map_type_title));
                builder.setSingleChoiceItems(QuoreAppSettings.this.getResources().getStringArray(R.array.mapType), mapType - 1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case R.styleable.View_android_theme /*0*/:
                                QuoreAppSettings.this.qasa2.edit().putString("mapType", "1").apply();
                                break;
                            case R.styleable.View_android_focusable /*1*/:
                                QuoreAppSettings.this.qasa2.edit().putString("mapType", "2").apply();
                                break;
                            case R.styleable.View_paddingEnd /*2*/:
                                QuoreAppSettings.this.qasa2.edit().putString("mapType", "3").apply();
                                break;
                        }
                        QuoreAppSettings.this.AlertDialogMapType.dismiss();
                    }
                });
                QuoreAppSettings.this.AlertDialogMapType = builder.create();
                QuoreAppSettings.this.AlertDialogMapType.show();
            }
        });
        layUnits.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int unitsInt = 0;
                if (QuoreAppSettings.this.qasa2.getString("units", "Metric").equalsIgnoreCase("Imperial")) {
                    unitsInt = 1;
                }
                Builder builder = new Builder(QuoreAppSettings.this);
                builder.setTitle(QuoreAppSettings.this.getResources().getString(R.string.Units));
                builder.setSingleChoiceItems(QuoreAppSettings.this.getResources().getStringArray(R.array.units), unitsInt, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0 /*0*/:
                                QuoreAppSettings.this.qasa2.edit().putString("units", "Metric").apply();
                                break;
                            case 1 /*1*/:
                                QuoreAppSettings.this.qasa2.edit().putString("units", "Imperial").apply();
                                break;
                        }
                        QuoreAppSettings.this.AlertDialogUnits.dismiss();
                    }
                });
                QuoreAppSettings.this.AlertDialogUnits = builder.create();
                QuoreAppSettings.this.AlertDialogUnits.show();
            }
        });
        layGoalDistance.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreAppSettings.this.goalDistance = QuoreAppSettings.this.qasa2.getString("goalDistance", "0");
                QuoreAppSettings.this.etGoalDistance = new EditText(QuoreAppSettings.this);
                QuoreAppSettings.this.etGoalDistance.setText(QuoreAppSettings.this.goalDistance, BufferType.EDITABLE);
                QuoreAppSettings.this.etGoalDistance.setInputType(2);
                QuoreAppSettings.this.etGoalDistance.setSelection(QuoreAppSettings.this.etGoalDistance.getText().length());
                Builder builder = new Builder(QuoreAppSettings.this);
                builder.setTitle(QuoreAppSettings.this.getResources().getString(R.string.Daily_goal_distance));
                builder.setView(QuoreAppSettings.this.etGoalDistance);
                builder.setNegativeButton(17039360, null);
                builder.setPositiveButton(QuoreAppSettings.this.getResources().getString(R.string.OK), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        QuoreAppSettings.this.changeDistanceGoal();
                    }
                });
                QuoreAppSettings.this.AlertDialogGoals = builder.create();
                try {
                    QuoreAppSettings.this.AlertDialogGoals.getWindow().setSoftInputMode(4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                QuoreAppSettings.this.AlertDialogGoals.show();
            }
        });
        layGoalCalories.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreAppSettings.this.goalCalories = QuoreAppSettings.this.qasa2.getString("goalCalories", "0");
                QuoreAppSettings.this.etGoalCalories = new EditText(QuoreAppSettings.this);
                QuoreAppSettings.this.etGoalCalories.setText(QuoreAppSettings.this.goalCalories, BufferType.EDITABLE);
                QuoreAppSettings.this.etGoalCalories.setInputType(2);
                QuoreAppSettings.this.etGoalCalories.setSelection(QuoreAppSettings.this.etGoalCalories.getText().length());
                Builder builder = new Builder(QuoreAppSettings.this);
                builder.setTitle(QuoreAppSettings.this.getResources().getString(R.string.Daily_goal_calories));
                builder.setView(QuoreAppSettings.this.etGoalCalories);
                builder.setNegativeButton(17039360, null);
                builder.setPositiveButton(QuoreAppSettings.this.getResources().getString(R.string.OK), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        QuoreAppSettings.this.changeCaloriesGoal();
                    }
                });
                QuoreAppSettings.this.AlertDialogGoals = builder.create();
                try {
                    QuoreAppSettings.this.AlertDialogGoals.getWindow().setSoftInputMode(4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                QuoreAppSettings.this.AlertDialogGoals.show();
            }
        });
        layGoalDuration.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreAppSettings.this.goalDuration = QuoreAppSettings.this.qasa2.getString("goalDuration", "0");
                QuoreAppSettings.this.etGoalDuration = new EditText(QuoreAppSettings.this);
                QuoreAppSettings.this.etGoalDuration.setText(QuoreAppSettings.this.goalDuration, BufferType.EDITABLE);
                QuoreAppSettings.this.etGoalDuration.setInputType(2);
                QuoreAppSettings.this.etGoalDuration.setSelection(QuoreAppSettings.this.etGoalDuration.getText().length());
                Builder builder = new Builder(QuoreAppSettings.this);
                builder.setTitle(QuoreAppSettings.this.getResources().getString(R.string.Daily_goal_time_min));
                builder.setView(QuoreAppSettings.this.etGoalDuration);
                builder.setNegativeButton(17039360, null);
                builder.setPositiveButton(QuoreAppSettings.this.getResources().getString(R.string.OK), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        QuoreAppSettings.this.changeDurationGoal();
                    }
                });
                QuoreAppSettings.this.AlertDialogGoals = builder.create();
                try {
                    QuoreAppSettings.this.AlertDialogGoals.getWindow().setSoftInputMode(4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                QuoreAppSettings.this.AlertDialogGoals.show();
            }
        });
    }

    private void changeDistanceGoal() {
        this.qasa2.edit().putString("goalDistance", String.valueOf(this.etGoalDistance.getText())).apply();
    }

    private void changeCaloriesGoal() {
        this.qasa2.edit().putString("goalCalories", String.valueOf(this.etGoalCalories.getText())).apply();
    }

    private void changeDurationGoal() {
        this.qasa2.edit().putString("goalDuration", String.valueOf(this.etGoalDuration.getText())).apply();
    }
}
