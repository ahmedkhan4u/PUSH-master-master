package com.push.sweateliteathletics.softrasol;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.RelativeLayout;
import android.widget.Switch;

import java.util.Arrays;

public class SpeakOption extends AppCompatActivity {
    private AlertDialog AlertDialogIntervalType;
    private Switch caloriesSwitch;
    private Switch distanceSwitch;
    private Switch durationSwitch;
    private boolean isVoiceForCaloriesOn;
    private boolean isVoiceForDistanceOn;
    private boolean isVoiceForDurationOn;
    private boolean isVoiceForPaceOn;
    private boolean isVoiceForSpeedOn;
    private boolean isVoiceNotifOn;
    private RelativeLayout layCaloriesGoal;
    private RelativeLayout layDistanceGoal;
    private RelativeLayout layDurationGoal;
    private RelativeLayout layInterval;
    private RelativeLayout layPaceGoal;
    private RelativeLayout laySpeedGoal;
    private RelativeLayout layUpdateType;
    private Switch paceSwitch;
    private SharedPreferences qasa2;
    private Switch speedSwitch;
    private FontTextView tvCaloriesText;
    private FontTextView tvCaloriesTitle;
    private FontTextView tvDistanceText;
    private FontTextView tvDistanceTitle;
    private FontTextView tvDurationText;
    private FontTextView tvDurationTitle;
    private FontTextView tvIntervalText;
    private FontTextView tvIntervalTitle;
    private FontTextView tvIntervalTypeText;
    private FontTextView tvIntervalTypeTitle;
    private FontTextView tvPaceText;
    private FontTextView tvPaceTitle;
    private FontTextView tvSpeedText;
    private FontTextView tvSpeedTitle;
    private Switch voiceNotifSwitch;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak_option);
        setTitle(getResources().getString(R.string.Speak_option));
        RelativeLayout layVoiceNotif = (RelativeLayout) findViewById(R.id.layVoiceNotif);
        this.layDistanceGoal = (RelativeLayout) findViewById(R.id.layGoalDistance);
        this.laySpeedGoal = (RelativeLayout) findViewById(R.id.layGoalSpeed);
        this.layPaceGoal = (RelativeLayout) findViewById(R.id.layGoalPace);
        this.layDurationGoal = (RelativeLayout) findViewById(R.id.layGoalDuration);
        this.layCaloriesGoal = (RelativeLayout) findViewById(R.id.layGoalCalories);
        this.layInterval = (RelativeLayout) findViewById(R.id.layInterval);
        this.layUpdateType = (RelativeLayout) findViewById(R.id.layUpdateType);
        this.voiceNotifSwitch = (Switch) findViewById(R.id.voiceNotifSwitch);
        this.distanceSwitch = (Switch) findViewById(R.id.switchDistance);
        this.speedSwitch = (Switch) findViewById(R.id.switchSpeed);
        this.paceSwitch = (Switch) findViewById(R.id.switchPace);
        this.durationSwitch = (Switch) findViewById(R.id.switchDuration);
        this.caloriesSwitch = (Switch) findViewById(R.id.switchCalories);
        this.tvIntervalTypeTitle = (FontTextView) findViewById(R.id.textView21);
        this.tvIntervalTypeText = (FontTextView) findViewById(R.id.textView22);
        this.tvIntervalTitle = (FontTextView) findViewById(R.id.textView57);
        this.tvIntervalText = (FontTextView) findViewById(R.id.textView58);
        this.tvDistanceTitle = (FontTextView) findViewById(R.id.textView1);
        this.tvDistanceText = (FontTextView) findViewById(R.id.textView2);
        this.tvSpeedTitle = (FontTextView) findViewById(R.id.textView3);
        this.tvSpeedText = (FontTextView) findViewById(R.id.textView4);
        this.tvPaceTitle = (FontTextView) findViewById(R.id.textView5);
        this.tvPaceText = (FontTextView) findViewById(R.id.textView6);
        this.tvDurationTitle = (FontTextView) findViewById(R.id.textView7);
        this.tvDurationText = (FontTextView) findViewById(R.id.textView8);
        this.tvCaloriesTitle = (FontTextView) findViewById(R.id.textView9);
        this.tvCaloriesText = (FontTextView) findViewById(R.id.textView10);
        String PREFS_NAME = "qA1sa2";
        this.qasa2 = getSharedPreferences("qA1sa2", 0);
        this.isVoiceForDistanceOn = this.qasa2.getBoolean("isVoiceForDistanceOn", true);
        this.isVoiceForSpeedOn = this.qasa2.getBoolean("isVoiceForSpeedOn", true);
        this.isVoiceForPaceOn = this.qasa2.getBoolean("isVoiceForPaceOn", true);
        this.isVoiceForDurationOn = this.qasa2.getBoolean("isVoiceForDurationOn", true);
        this.isVoiceForCaloriesOn = this.qasa2.getBoolean("isVoiceForCaloriesOn", true);
        this.isVoiceNotifOn = this.qasa2.getBoolean("isVoiceNotifOn", false);
        changeIntervalTypeText(this.qasa2.getInt("intervalType", 1));
        this.distanceSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SpeakOption.this.qasa2.edit().putBoolean("isVoiceForDistanceOn", isChecked).apply();
                SpeakOption.this.isVoiceForDistanceOn = isChecked;
            }
        });
        this.speedSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SpeakOption.this.qasa2.edit().putBoolean("isVoiceForSpeedOn", isChecked).apply();
                SpeakOption.this.isVoiceForSpeedOn = isChecked;
            }
        });
        this.distanceSwitch.setChecked(this.isVoiceForDistanceOn);
        this.speedSwitch.setChecked(this.isVoiceForSpeedOn);
        this.layDistanceGoal.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                SpeakOption.this.distanceSwitch.setChecked(!SpeakOption.this.isVoiceForDistanceOn);
            }
        });
        this.laySpeedGoal.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                SpeakOption.this.speedSwitch.setChecked(!SpeakOption.this.isVoiceForSpeedOn);
            }
        });
        this.paceSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SpeakOption.this.qasa2.edit().putBoolean("isVoiceForPaceOn", isChecked).apply();
                SpeakOption.this.isVoiceForPaceOn = isChecked;
            }
        });
        this.durationSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SpeakOption.this.qasa2.edit().putBoolean("isVoiceForDurationOn", isChecked).apply();
                SpeakOption.this.isVoiceForDurationOn = isChecked;
            }
        });
        this.paceSwitch.setChecked(this.isVoiceForPaceOn);
        this.durationSwitch.setChecked(this.isVoiceForDurationOn);
        this.layPaceGoal.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                SpeakOption.this.paceSwitch.setChecked(!SpeakOption.this.isVoiceForPaceOn);
            }
        });
        this.layDurationGoal.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                SpeakOption.this.durationSwitch.setChecked(!SpeakOption.this.isVoiceForDurationOn);
            }
        });
        this.caloriesSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SpeakOption.this.qasa2.edit().putBoolean("isVoiceForCaloriesOn", isChecked).apply();
                SpeakOption.this.isVoiceForCaloriesOn = isChecked;
            }
        });
        this.caloriesSwitch.setChecked(this.isVoiceForCaloriesOn);
        this.layCaloriesGoal.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                SpeakOption.this.caloriesSwitch.setChecked(!SpeakOption.this.isVoiceForCaloriesOn);
            }
        });
        this.voiceNotifSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SpeakOption.this.qasa2.edit().putBoolean("isVoiceNotifOn", isChecked).apply();
                SpeakOption.this.isVoiceNotifOn = isChecked;
                SpeakOption.this.adjustIntervalAndOption(SpeakOption.this.isVoiceNotifOn);
            }
        });
        this.voiceNotifSwitch.setChecked(this.isVoiceNotifOn);
        layVoiceNotif.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                SpeakOption.this.voiceNotifSwitch.setChecked(!SpeakOption.this.isVoiceNotifOn);
            }
        });
        this.layUpdateType.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                int intervalType = SpeakOption.this.qasa2.getInt("intervalType", 1);
                Builder builder = new Builder(SpeakOption.this);
                builder.setTitle(SpeakOption.this.getResources().getString(R.string.intervalType));
                builder.setSingleChoiceItems(SpeakOption.this.getResources().getStringArray(R.array.intervalType), intervalType - 1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case R.styleable.View_android_theme /*0*/:
                                SpeakOption.this.qasa2.edit().putInt("intervalType", 1).apply();
                                SpeakOption.this.changeIntervalTypeText(1);
                                break;
                            case R.styleable.View_android_focusable /*1*/:
                                SpeakOption.this.qasa2.edit().putInt("intervalType", 2).apply();
                                SpeakOption.this.changeIntervalTypeText(2);
                                break;
                        }
                        SpeakOption.this.AlertDialogIntervalType.dismiss();
                    }
                });
                SpeakOption.this.AlertDialogIntervalType = builder.create();
                SpeakOption.this.AlertDialogIntervalType.show();
            }
        });
        this.layInterval.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                final int intervalType = SpeakOption.this.qasa2.getInt("intervalType", 1);
                final String[] timeValues = new String[]{"0.5", "1", "2", "5", "10", "15", "30", "45", "60"};
                final String[] distanceValues = new String[]{"0.5", "1", "2", "5", "10", "15", "20", "25", "30"};
                int indexTime = Arrays.asList(timeValues).indexOf(SpeakOption.this.qasa2.getString("intervalTime", "2"));
                int indexDistance = Arrays.asList(distanceValues).indexOf(SpeakOption.this.qasa2.getString("intervalDistance", "2"));
                Builder d = new Builder(SpeakOption.this);
                View dialogView = SpeakOption.this.getLayoutInflater().inflate(R.layout.interval_dialog, null);
                d.setView(dialogView);
                final NumberPicker np = (NumberPicker) dialogView.findViewById(R.id.npInterval);
                np.setMinValue(0);
                np.setMaxValue(8);
                FontTextView tvUnit = (FontTextView) dialogView.findViewById(R.id.tvDialogUnit);
                if (intervalType == 1) {
                    np.setDisplayedValues(timeValues);
                    np.setValue(indexTime);
                    tvUnit.setText(SpeakOption.this.getResources().getString(R.string.min));
                } else {
                    np.setDisplayedValues(distanceValues);
                    np.setValue(indexDistance);
                    if (SpeakOption.this.qasa2.getString("units", "Metric").equalsIgnoreCase("Metric")) {
                        tvUnit.setText(SpeakOption.this.getResources().getString(R.string.km));
                    } else {
                        tvUnit.setText(SpeakOption.this.getResources().getString(R.string.mi));
                    }
                }
                np.setWrapSelectorWheel(true);
                np.setOnValueChangedListener(new OnValueChangeListener() {
                    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                    }
                });
                d.setNegativeButton(17039360, null);
                d.setPositiveButton(17039370, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (intervalType == 1) {
                            SpeakOption.this.qasa2.edit().putString("intervalTime", timeValues[np.getValue()]).apply();
                        } else {
                            SpeakOption.this.qasa2.edit().putString("intervalDistance", distanceValues[np.getValue()]).apply();
                        }
                    }
                });
                d.create().show();
            }
        });
        adjustIntervalAndOption(this.isVoiceNotifOn);
    }

    private void changeIntervalTypeText(int type) {
        if (type == 1) {
            this.tvIntervalTypeText.setText(getResources().getString(R.string.timeBased));
        } else {
            this.tvIntervalTypeText.setText(getResources().getString(R.string.distanceBased));
        }
    }

    private void adjustIntervalAndOption(boolean isVoiceNotifOn) {
        if (isVoiceNotifOn) {
            this.layUpdateType.setClickable(true);
            this.layInterval.setClickable(true);
            this.layDistanceGoal.setClickable(true);
            this.laySpeedGoal.setClickable(true);
            this.layPaceGoal.setClickable(true);
            this.layDurationGoal.setClickable(true);
            this.layCaloriesGoal.setClickable(true);
            this.distanceSwitch.setClickable(true);
            this.speedSwitch.setClickable(true);
            this.paceSwitch.setClickable(true);
            this.durationSwitch.setClickable(true);
            this.caloriesSwitch.setClickable(true);
            this.tvIntervalTypeTitle.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor));
            this.tvIntervalTypeText.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor));
            this.tvIntervalTitle.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor));
            this.tvIntervalText.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor));
            this.tvDistanceTitle.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor));
            this.tvDistanceText.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor));
            this.tvSpeedTitle.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor));
            this.tvSpeedText.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor));
            this.tvPaceTitle.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor));
            this.tvPaceText.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor));
            this.tvDurationTitle.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor));
            this.tvDurationText.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor));
            this.tvCaloriesTitle.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor));
            this.tvCaloriesText.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor));
            return;
        }
        this.layUpdateType.setClickable(false);
        this.layInterval.setClickable(false);
        this.layDistanceGoal.setClickable(false);
        this.laySpeedGoal.setClickable(false);
        this.layPaceGoal.setClickable(false);
        this.layDurationGoal.setClickable(false);
        this.layCaloriesGoal.setClickable(false);
        this.distanceSwitch.setClickable(false);
        this.speedSwitch.setClickable(false);
        this.paceSwitch.setClickable(false);
        this.durationSwitch.setClickable(false);
        this.caloriesSwitch.setClickable(false);
        this.tvIntervalTypeTitle.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor2));
        this.tvIntervalTypeText.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor2));
        this.tvIntervalTitle.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor2));
        this.tvIntervalText.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor2));
        this.tvDistanceTitle.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor2));
        this.tvDistanceText.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor2));
        this.tvSpeedTitle.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor2));
        this.tvSpeedText.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor2));
        this.tvPaceTitle.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor2));
        this.tvPaceText.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor2));
        this.tvDurationTitle.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor2));
        this.tvDurationText.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor2));
        this.tvCaloriesTitle.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor2));
        this.tvCaloriesText.setTextColor(ContextCompat.getColor(this, R.color.settingsTextColor2));
    }
}
