package com.push.sweateliteathletics.softrasol;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.RingtoneManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.UtteranceProgressListener;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.core.app.NotificationCompat.Builder;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class QuoreGlavna extends Fragment implements OnInitListener, OnMapReadyCallback {
    static boolean isAutoPaused = false;
    static boolean isRunningPaused = false;
    static boolean isTrainingStarted = false;
    static boolean report = true;
    private BroadcastReceiver GPSDataReceiver;
    private LinearLayout LinLayButtons;
    private float accuracy;

    private Dialog alertDialogGPS;
    private AudioManager am;
    private boolean autoPause = false;
    private Button btnPauseResume;
    private Button btnStart;
    private Button btnStop;
    private double caloriesGPS = 0.0d;
    private Context context;
    private String dateFormat;
    private int delayedSeconds = 0;
    private double distanceKmGPS = 0.0d;
    private double distanceMiGPS = 0.0d;
    private boolean engLang = false;
    private double goalCalories;
    private double goalDistance;
    private boolean goalNotification;
    private double goalTime;
    private ImageView gpsSignalIv;
    private Handler h;
    private int highSpeedGPS = 0;
    private String history1array;
    private boolean iSGPSDialogShown = false;
    private ImageButton imbLock;
    private Intent intentReport;
    private int intervalType = 1;
    private boolean isLocked = false;
    private boolean isNoGPSMessageShown = false;
    private boolean isPaused = false;
    private boolean isSpeakOutTime = false;
    private boolean isVoiceCaloriesOn = false;
    private boolean isVoiceDistanceOn = false;
    private boolean isVoiceOn = false;
    private boolean isVoicePaceOn = false;
    private boolean isVoiceSpeedOn = false;
    private boolean isVoiceSupported = false;
    private boolean isVoiceTimeOn = false;
    private boolean keepScreenOn = false;
    private LatLng laln;
    private double lat;
    private ArrayList<LatLng> latLonArray;
    private ArrayList<LatLng> latLonArrayRep;
    private int latLon_i = 0;
    private double lng;
    private int locLangResult;
    private int lowSpeedGPS = 0;
    private Builder mBuilderGoal;
    private Chronometer mChronometer;
    private HashMap<String, String> mHashMap;

    private GoogleMap mMap;
    private OnAudioFocusChangeListener mOnAudioFocusChangeListener;
    private MapView mapView;
    private double maxSpeedGPS = 0.0d;
    private int mediumSpeedGPS = 0;
    private long newLocationTime = 0;
    private String no_GPS_text;
    private String no_GPS_title;
    private NotificationManager notificationManager;
    private String ok_text;
    private String paceForVoice;
    private LatLng previousLaLn;
    private SharedPreferences qasa2;
    private RelativeLayout relLayGPSStatus;
    private boolean resetSpeak = false;
    private boolean shouldSpeak = false;
    private int signalGPS = 0;
    private double speakDistanceInterval = 2.0d;
    private double speakTimeInterval = 120.0d;
    private String speedForVoice;
    private double speedGPS;
    private int startHour;
    private int startMinute;
    private int stopHour;
    private int stopMinute;
    private long stopTime2 = 0;
    private double timeMiliSecGPS;
    private double timeMinutesGPS;
    private long timeWhenRunningAutoPaused = 0;
    private long timeWhenRunningPaused = 0;
    private double totalCaloriesToday;
    private double totalDistanceToday;
    private double totalTimeToday;
    private TextToSpeech tts;
    private FontTextView tvCalories;
    private FontTextView tvDistance;
    private FontTextView tvDistanceTitle;
    private FontTextView tvSpeed;
    private FontTextView tvSpeedTitle;
    private String units;
    private double weight;

    private class MyGPSReceiver extends BroadcastReceiver {
        private MyGPSReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            QuoreGlavna.this.caloriesGPS = intent.getDoubleExtra("caloriesGPS", 0.0d);
            QuoreGlavna.this.distanceKmGPS = intent.getDoubleExtra("distanceKmGPS", 0.0d);
            QuoreGlavna.this.speedGPS = intent.getDoubleExtra("speedGPS", 0.0d);
            QuoreGlavna.this.lng = intent.getDoubleExtra("lng", 0.0d);
            QuoreGlavna.this.lat = intent.getDoubleExtra("lat", 0.0d);
            QuoreGlavna.this.accuracy = intent.getFloatExtra("accuracyGPS", 0.0f);
            QuoreGlavna.this.newLocationTime = System.currentTimeMillis();
            QuoreGlavna.this.setGPSIndicator(QuoreGlavna.this.accuracy);
            if (QuoreGlavna.this.iSGPSDialogShown && QuoreGlavna.this.alertDialogGPS != null) {
                try {
                    QuoreGlavna.this.alertDialogGPS.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (!QuoreGlavna.isRunningPaused) {
                if (!QuoreGlavna.isAutoPaused) {
                    if (QuoreGlavna.this.isPaused) {
                        QuoreGlavna.this.isPaused = false;
                        QuoreGlavna.this.mChronometer.setBase(SystemClock.elapsedRealtime() + QuoreGlavna.this.timeWhenRunningAutoPaused);
                        QuoreGlavna.this.mChronometer.start();
                    }
                    QuoreGlavna.this.laln = new LatLng(QuoreGlavna.this.lat, QuoreGlavna.this.lng);
                    QuoreGlavna.this.latLon_i = QuoreGlavna.this.latLon_i + 1;
                    if (QuoreGlavna.this.latLon_i % 2 == 0) {
                        QuoreGlavna.this.latLonArray.add(QuoreGlavna.this.laln);
                    }
                    if (QuoreGlavna.this.mMap != null) {
                        QuoreGlavna.this.mMap.moveCamera(CameraUpdateFactory.newLatLng(QuoreGlavna.this.laln));
                        QuoreGlavna.this.mMap.animateCamera(CameraUpdateFactory.zoomTo(16.0f));
                    }
                    if (QuoreGlavna.this.maxSpeedGPS < QuoreGlavna.this.speedGPS) {
                        QuoreGlavna.this.maxSpeedGPS = QuoreGlavna.this.speedGPS;
                    }
                    QuoreGlavna.this.timeMiliSecGPS = (double) (SystemClock.elapsedRealtime() - QuoreGlavna.this.mChronometer.getBase());
                    QuoreGlavna.this.timeMinutesGPS = QuoreGlavna.this.timeMiliSecGPS / 60000.0d;
                    QuoreGlavna.this.refreshOtherGoals(QuoreGlavna.this.caloriesGPS, QuoreGlavna.this.distanceKmGPS, QuoreGlavna.this.timeMinutesGPS);
                    if (QuoreGlavna.this.units.equalsIgnoreCase("Imperial")) {
                        if (QuoreGlavna.this.speedGPS * 0.62137d < 7.0d) {
                            QuoreGlavna.this.lowSpeedGPS = QuoreGlavna.this.lowSpeedGPS + 1;
                        } else if (QuoreGlavna.this.speedGPS * 0.62137d >= 7.0d && QuoreGlavna.this.speedGPS * 0.62137d <= 16.0d) {
                            QuoreGlavna.this.mediumSpeedGPS = QuoreGlavna.this.mediumSpeedGPS + 1;
                        } else if (QuoreGlavna.this.speedGPS * 0.62137d > 16.0d) {
                            QuoreGlavna.this.highSpeedGPS = QuoreGlavna.this.highSpeedGPS + 1;
                        }
                    } else if (QuoreGlavna.this.speedGPS < 10.0d) {
                        QuoreGlavna.this.lowSpeedGPS = QuoreGlavna.this.lowSpeedGPS + 1;
                    } else if (QuoreGlavna.this.speedGPS >= 10.0d && QuoreGlavna.this.speedGPS <= 25.0d) {
                        QuoreGlavna.this.mediumSpeedGPS = QuoreGlavna.this.mediumSpeedGPS + 1;
                    } else if (QuoreGlavna.this.speedGPS > 25.0d) {
                        QuoreGlavna.this.highSpeedGPS = QuoreGlavna.this.highSpeedGPS + 1;
                    }
                } else if (!QuoreGlavna.this.isPaused) {
                    QuoreGlavna.this.isPaused = true;
                    QuoreGlavna.this.timeWhenRunningAutoPaused = QuoreGlavna.this.mChronometer.getBase() - SystemClock.elapsedRealtime();
                    QuoreGlavna.this.mChronometer.setBase(SystemClock.elapsedRealtime() + QuoreGlavna.this.timeWhenRunningAutoPaused);
                    QuoreGlavna.this.mChronometer.stop();
                }
            }
            if (QuoreGlavna.isTrainingStarted) {
                QuoreGlavna.this.sendLocationToUI();
            }
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onViewCreated(View v, Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        this.mapView = (MapView) v.findViewById(R.id.map);
        this.mapView.onCreate(savedInstanceState);
        this.mapView.onResume();
        this.mapView.getMapAsync(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_glavna, container, false);
        this.context = getActivity();
        this.mChronometer = (Chronometer) rootView.findViewById(R.id.chronometer1);
        this.btnStart = (Button) rootView.findViewById(R.id.btnStart);
        this.btnStop = (Button) rootView.findViewById(R.id.btnStop);
        this.btnPauseResume = (Button) rootView.findViewById(R.id.btnPauseResume);
        this.imbLock = (ImageButton) rootView.findViewById(R.id.btnLock);
        this.tvDistance = (FontTextView) rootView.findViewById(R.id.tvDistance);
        this.tvSpeed = (FontTextView) rootView.findViewById(R.id.tvSpeed);
        this.tvCalories = (FontTextView) rootView.findViewById(R.id.tvCalories);
        this.tvDistanceTitle = (FontTextView) rootView.findViewById(R.id.tvDistanceTitle);
        this.tvSpeedTitle = (FontTextView) rootView.findViewById(R.id.tvSpeedTitle);
        this.LinLayButtons = (LinearLayout) rootView.findViewById(R.id.LinLayButtons);
        this.gpsSignalIv = (ImageView) rootView.findViewById(R.id.ivGPS);
        this.relLayGPSStatus = (RelativeLayout) rootView.findViewById(R.id.relLayGPSStatus);
        String PREFS_NAME = "qA1sa2";
        this.qasa2 = getActivity().getSharedPreferences("qA1sa2", 0);
        this.weight = Double.longBitsToDouble(this.qasa2.getLong("tezina", Double.doubleToLongBits(75.0d)));
        if (this.weight == 0.0d) {
            this.weight = 75.0d;
        }
        readSettings();
        new Thread() {
            public void run() {
                QuoreGlavna.this.resetGraphData();
            }
        }.start();
        this.notificationManager = (NotificationManager) this.context.getSystemService("notification");
        readGoals();
        readTodayTotal();
        this.am = (AudioManager) this.context.getSystemService("audio");
//        this.mInterstitialAd = new InterstitialAd(this.context);
//        this.mInterstitialAd.setAdUnitId("ca-app-pub-8525515749450362/8024452933");
//        this.mInterstitialAd.setAdListener(new AdListener() {
//            public void onAdClosed() {
//                QuoreGlavna.this.context.startActivity(QuoreGlavna.this.intentReport);
//            }
//        });
        this.no_GPS_title = getResources().getString(R.string.no_GPS_title);
        this.no_GPS_text = getResources().getString(R.string.no_GPS_text);
        this.ok_text = getResources().getString(R.string.OK);
        this.mOnAudioFocusChangeListener = new OnAudioFocusChangeListener() {
            public void onAudioFocusChange(int focusChange) {
                switch (focusChange) {
                }
            }
        };
        this.btnStart.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                QuoreGlavna.this.readTodayTotal();
                LocationManager lm = (LocationManager) QuoreGlavna.this.context.getSystemService("location");
                if (!QuoreGlavna.isTrainingStarted) {
                    if (!lm.isProviderEnabled("gps")) {
                        QuoreGlavna.this.turnOnGPS();
                    } else if (System.currentTimeMillis() - QuoreGlavna.this.stopTime2 > 2000 && QuoreGlavna.report) {
                        if (VERSION.SDK_INT < 23) {
                            if (QuoreGlavna.this.mMap != null) {
                                QuoreGlavna.this.mMap.setMyLocationEnabled(true);
                            }
                            QuoreGlavna.this.start();
                        } else if (QuoreGlavna.this.getActivity().checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0) {
                            if (QuoreGlavna.this.mMap != null) {
                                QuoreGlavna.this.mMap.setMyLocationEnabled(true);
                            }
                            QuoreGlavna.this.start();
                        } else if (ActivityCompat.shouldShowRequestPermissionRationale(QuoreGlavna.this.getActivity(), "android.permission.ACCESS_FINE_LOCATION")) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(QuoreGlavna.this.context);
                            builder.setTitle(QuoreGlavna.this.getResources().getString(R.string.permission_GPS_rationale_title));
                            builder.setMessage(QuoreGlavna.this.getResources().getString(R.string.permission_GPS_rationale));
                            builder.setPositiveButton(QuoreGlavna.this.getResources().getString(R.string.OK), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ActivityCompat.requestPermissions(QuoreGlavna.this.getActivity(), new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 0);
                                }
                            });
                            Dialog alertDialog = builder.create();
                            alertDialog.setCanceledOnTouchOutside(false);
                            alertDialog.show();
                        } else {
                            ActivityCompat.requestPermissions(QuoreGlavna.this.getActivity(), new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 0);
                        }
                    }
                }
            }
        });
        this.btnStop.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (QuoreGlavna.this.isLocked) {
                    Toast.makeText(QuoreGlavna.this.context, QuoreGlavna.this.getResources().getString(R.string.unlock_first), 0).show();
                    return;
                }
                QuoreGlavna.this.stopTime2 = System.currentTimeMillis();
                QuoreGlavna.isTrainingStarted = false;
                QuoreGlavna.this.stop();
                QuoreGlavna.this.btnStart.setVisibility(0);
                QuoreGlavna.this.LinLayButtons.setVisibility(4);
                QuoreGlavna.this.btnStop.setVisibility(4);
                QuoreGlavna.this.btnPauseResume.setVisibility(4);
                QuoreGlavna.this.imbLock.setVisibility(4);
            }
        });
        this.btnPauseResume.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (QuoreGlavna.this.isLocked) {
                    Toast.makeText(QuoreGlavna.this.context, QuoreGlavna.this.getResources().getString(R.string.unlock_first), 0).show();
                } else if (QuoreGlavna.isRunningPaused) {
                    QuoreGlavna.isRunningPaused = false;
                    QuoreGlavna.this.mChronometer.setBase(SystemClock.elapsedRealtime() + QuoreGlavna.this.timeWhenRunningPaused);
                    QuoreGlavna.this.mChronometer.start();

                    //  QuoreGlavna.this.btnPauseResume.setBackgroundColor(Color.parseColor("#fc9509"));
                } else {
                    QuoreGlavna.isRunningPaused = true;
                    if (QuoreGlavna.isAutoPaused) {
                        QuoreGlavna.this.timeWhenRunningPaused = QuoreGlavna.this.timeWhenRunningAutoPaused;
                    } else {
                        QuoreGlavna.this.timeWhenRunningPaused = QuoreGlavna.this.mChronometer.getBase() - SystemClock.elapsedRealtime();
                    }
                    QuoreGlavna.this.mChronometer.stop();

                    //  QuoreGlavna.this.btnPauseResume.setBackgroundColor(Color.parseColor("#94d100"));
                }
            }
        });
        this.imbLock.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (QuoreGlavna.this.isLocked) {
                    QuoreGlavna.this.isLocked = false;
                    QuoreGlavna.this.imbLock.setBackgroundResource(R.drawable.ic_unlock_black_24dp);

                    if (QuoreGlavna.isRunningPaused) {
                        // QuoreGlavna.this.btnPauseResume.setBackgroundColor(Color.parseColor("#94d100"));
                        return;
                    } else {
                        // QuoreGlavna.this.btnPauseResume.setBackgroundColor(Color.parseColor("#fc9509"));
                        return;
                    }
                }
                QuoreGlavna.this.isLocked = true;
                QuoreGlavna.this.imbLock.setBackgroundResource(R.drawable.unlock);

                // QuoreGlavna.this.btnPauseResume.setBackgroundColor(Color.parseColor("#c6c6c6"));
            }
        });
        return rootView;
    }

    public void onMapReady(GoogleMap googleMapRep) {
        this.mMap = googleMapRep;
        setMapType();
    }

    private void setMapType() {
        String mapType = this.qasa2.getString("mapType", "1");
        if (mapType.equalsIgnoreCase("2")) {
            this.mMap.setMapType(2);
        } else if (mapType.equalsIgnoreCase("3")) {
            this.mMap.setMapType(3);
        } else {
            this.mMap.setMapType(1);
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != 0) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        } else if (grantResults.length == 1 && grantResults[0] == 0) {
            if (this.mMap != null) {
                this.mMap.setMyLocationEnabled(true);
            }
            start();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
            builder.setTitle(getResources().getString(R.string.no_GPS_permission));
            builder.setMessage(getResources().getString(R.string.can_not_work_without_GPS));
            builder.setPositiveButton(getResources().getString(R.string.OK), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            Dialog alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        }
    }

    public void onDestroy() {
        if (this.tts != null) {
            this.tts.stop();
            this.tts.shutdown();
        }
        super.onDestroy();
    }

    public void onResume() {
        if (!isTrainingStarted) {
            if (this.mMap != null) {
                setMapType();
            }
            readGoals();
            readTodayTotal();
            readSettings();
        }
        super.onResume();
    }

    private void readSettings() {
        this.keepScreenOn = this.qasa2.getBoolean("isScreenOn", false);
        this.units = this.qasa2.getString("units", "Metric");
        this.dateFormat = this.qasa2.getString("dateFormat", "1");
        this.autoPause = this.qasa2.getBoolean("isAutoPauseOn", false);
        this.isVoiceOn = this.qasa2.getBoolean("isVoiceNotifOn", false);
        this.intervalType = this.qasa2.getInt("intervalType", 1);
        this.speakTimeInterval = Double.parseDouble(this.qasa2.getString("intervalTime", "2")) * 60.0d;
        this.speakDistanceInterval = Double.parseDouble(this.qasa2.getString("intervalDistance", "2"));
        this.isVoiceDistanceOn = this.qasa2.getBoolean("isVoiceForDistanceOn", false);
        this.isVoiceTimeOn = this.qasa2.getBoolean("isVoiceForDurationOn", false);
        this.isVoiceCaloriesOn = this.qasa2.getBoolean("isVoiceForCaloriesOn", false);
        this.isVoiceSpeedOn = this.qasa2.getBoolean("isVoiceForSpeedOn", false);
        this.isVoicePaceOn = this.qasa2.getBoolean("isVoiceForPaceOn", false);
        if (this.units.equalsIgnoreCase("Imperial")) {
            this.tvDistanceTitle.setText(getResources().getString(R.string.DISTANCE_mi));
            this.tvSpeedTitle.setText(getResources().getString(R.string.SPEED_mi));
            return;
        }
        this.tvDistanceTitle.setText(getResources().getString(R.string.DISTANCE_km));
        this.tvSpeedTitle.setText(getResources().getString(R.string.SPEED_km));
    }

    private void readTodayTotal() {
        String dayOfTheWeek = getDayOfTheWeek();
        this.totalCaloriesToday = Double.longBitsToDouble(this.qasa2.getLong(dayOfTheWeek + "Kalorije", Double.doubleToLongBits(0.0d)));
        this.totalDistanceToday = Double.longBitsToDouble(this.qasa2.getLong(dayOfTheWeek + "Rastojanje", Double.doubleToLongBits(0.0d)));
        this.totalTimeToday = Double.longBitsToDouble(this.qasa2.getLong(dayOfTheWeek + "Vreme", Double.doubleToLongBits(0.0d)));
    }

    private void turnOnGPS() {
        if (!((LocationManager) this.context.getSystemService("location")).isProviderEnabled("gps")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
            builder.setIcon(R.drawable.ic_warning_black_24dp);
            builder.setTitle(getResources().getString(R.string.GPS_is_not_turned_on));
            builder.setMessage(getResources().getString(R.string.turn_on_GPS));
            builder.setPositiveButton(getResources().getString(R.string.OK), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    QuoreGlavna.this.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
                }
            });
            Dialog alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        }
    }

    public void start() {
        report = false;
        isTrainingStarted = true;
        if (this.keepScreenOn) {
            getActivity().getWindow().addFlags(128);
        }
        this.latLonArray = new ArrayList();
        this.GPSDataReceiver = new MyGPSReceiver();
        this.context.registerReceiver(this.GPSDataReceiver, new IntentFilter("com.zeopoxa.fitness.running.GPSData"));
        Intent service = new Intent(this.context, GPSService.class);
        service.putExtra("weight", this.weight);
        service.putExtra("jedinicaMere", this.units);
        service.putExtra("autoPause", this.autoPause);
        getActivity().startService(service);
        this.mHashMap = new HashMap();
        this.mHashMap.put("streamType", String.valueOf(3));
        this.mHashMap.put("utteranceId", "TTS NOTIF");
        this.tts = new TextToSpeech(this.context, this);
        this.mChronometer.setBase(SystemClock.elapsedRealtime());
        this.mChronometer.start();
        Calendar cal2 = Calendar.getInstance(Locale.GERMANY);
        this.startHour = cal2.get(11);
        this.startMinute = cal2.get(12);
        readGoals();
        readSettings();
        this.relLayGPSStatus.setVisibility(0);
        this.h = new Handler();
        this.h.postDelayed(new Runnable() {
            public void run() {
                if (QuoreGlavna.isTrainingStarted) {
                    QuoreGlavna.this.speakOut();
                    QuoreGlavna.this.delayedSeconds = QuoreGlavna.this.delayedSeconds + 1;
                    if (QuoreGlavna.this.delayedSeconds > 30 && (QuoreGlavna.this.newLocationTime == 0 || System.currentTimeMillis() - QuoreGlavna.this.newLocationTime > 4000)) {
                        QuoreGlavna.this.setGPSIndicator(0.0f);
                        if (!QuoreGlavna.this.isNoGPSMessageShown) {
                            QuoreGlavna.this.isNoGPSMessageShown = true;
                            QuoreGlavna.this.iSGPSDialogShown = true;
                            try {
                                QuoreGlavna.this.alertUserNoGPS();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    QuoreGlavna.this.h.postDelayed(this, 1000);
                }
            }
        }, 1000);
        this.btnStop.setVisibility(0);
        this.btnPauseResume.setVisibility(0);
        this.imbLock.setVisibility(0);
        this.LinLayButtons.setVisibility(0);
        this.btnStart.setVisibility(4);

    }

    private void alertUserNoGPS() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setIcon(R.drawable.ic_warning_black_24dp);
        builder.setTitle(this.no_GPS_title);
        builder.setMessage(this.no_GPS_text);
        builder.setPositiveButton(this.ok_text, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        this.alertDialogGPS = builder.create();
        this.alertDialogGPS.setCanceledOnTouchOutside(false);
        this.alertDialogGPS.show();
        try {
            RingtoneManager.getRingtone(this.context, RingtoneManager.getDefaultUri(2)).play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        String startTime;
        String stopTime;
        if (isRunningPaused) {
            isRunningPaused = false;
            this.mChronometer.setBase(SystemClock.elapsedRealtime() + this.timeWhenRunningPaused);
            this.mChronometer.start();

            //this.btnPauseResume.setBackgroundColor(Color.parseColor("#fc9509"));
        }
        if (this.isPaused) {
            this.isPaused = false;
            this.mChronometer.setBase(SystemClock.elapsedRealtime() + this.timeWhenRunningAutoPaused);
            this.mChronometer.start();
        }
        if (isAutoPaused) {
            isAutoPaused = false;
        }
        setGPSIndicator(0.0f);
        this.relLayGPSStatus.setVisibility(4);
        try {
            this.h.removeCallbacksAndMessages(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            this.context.unregisterReceiver(this.GPSDataReceiver);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        getActivity().startService(new Intent(this.context, GPSService.class));
        this.latLonArrayRep = this.latLonArray;
        this.latLonArray = null;
        this.mChronometer.stop();
        if (this.tts != null) {
            this.tts.stop();
            this.tts.shutdown();
            this.tts = null;
        }
        if (this.keepScreenOn) {
            getActivity().getWindow().clearFlags(128);
        }
        double timeMilisec = (double) (SystemClock.elapsedRealtime() - this.mChronometer.getBase());
        final double timeMiliSec = timeMilisec;
        final double timeMinutes = timeMilisec / 60000.0d;
        final double distanceKm = this.distanceKmGPS;
        final double maxSpeed = this.maxSpeedGPS;
        final double calories = this.caloriesGPS;
        final int lowSpeed = this.lowSpeedGPS;
        final int mediumSpeed = this.mediumSpeedGPS;
        final int highSpeed = this.highSpeedGPS;
        double pace = 0.0d;
        if (distanceKm > 0.0d) {
            pace = timeMinutes / distanceKm;
        }
        final double paceF = pace;
        Calendar cal3 = Calendar.getInstance(Locale.GERMANY);
        this.stopHour = cal3.get(11);
        this.stopMinute = cal3.get(12);
        if (this.startMinute < 10) {
            startTime = this.startHour + ":0" + this.startMinute + " ";
        } else {
            startTime = this.startHour + ":" + this.startMinute + " ";
        }
        if (this.stopMinute < 10) {
            stopTime = this.stopHour + ":0" + this.stopMinute + " ";
        } else {
            stopTime = this.stopHour + ":" + this.stopMinute + " ";
        }
        final String startTimeF = startTime;
        final String stopTimeF = stopTime;
        int stopDay = cal3.get(5);
        int stopMonth = cal3.get(2);
        int stopYear = cal3.get(1);
        stopMonth++;
        if (stopMonth == 13) {
            stopMonth = 1;
        }
        String stopDate = BuildConfig.FLAVOR;
        try {
            if (this.dateFormat.equalsIgnoreCase("1")) {
                stopDate = BuildConfig.FLAVOR + DateFormat.getDateFormat(this.context).format(Calendar.getInstance().getTime());
            } else if (this.dateFormat.equalsIgnoreCase("2")) {
                stopDate = BuildConfig.FLAVOR + stopDay + "/" + stopMonth + "/" + stopYear;
            } else if (this.dateFormat.equalsIgnoreCase("3")) {
                stopDate = BuildConfig.FLAVOR + stopMonth + "/" + stopDay + "/" + stopYear;
            } else {
                stopDate = BuildConfig.FLAVOR + stopYear + "/" + stopMonth + "/" + stopDay;
            }
        } catch (Exception e22) {
            e22.printStackTrace();
        }
        final String stopDateF = stopDate;
        this.history1array = this.qasa2.getString("history1array", BuildConfig.FLAVOR);
        this.qasa2.edit().putString("history1array", new Gson().toJson(this.latLonArrayRep)).commit();
        new Thread() {
            public void run() {
                QuoreGlavna.this.addToHistory(distanceKm, timeMiliSec, calories, lowSpeed, mediumSpeed, highSpeed, startTimeF, stopTimeF, maxSpeed, stopDateF, paceF, QuoreGlavna.this.latLonArrayRep);
                QuoreGlavna.this.addToCharts(distanceKm, timeMinutes, calories);
                QuoreGlavna.this.addToRecords(distanceKm, timeMiliSec, calories, maxSpeed, paceF);
                QuoreGlavna.this.addToTotal(distanceKm, timeMiliSec, calories);
                try {
                    QuoreGlavna.this.qasa2.edit().putLong("latitude", Double.doubleToRawLongBits(QuoreGlavna.this.lat)).commit();
                    QuoreGlavna.this.qasa2.edit().putLong("longitude", Double.doubleToRawLongBits(QuoreGlavna.this.lng)).commit();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                QuoreGlavna.this.resetVariables();
            }
        }.start();
        this.mChronometer.setBase(SystemClock.elapsedRealtime());
        this.tvDistance.setText("0");
        this.tvSpeed.setText("0");
        this.tvCalories.setText("0");
        this.intentReport = new Intent(this.context, QuoreReport.class);
        this.intentReport.putExtra("duration", timeMilisec);
        this.intentReport.putExtra("distance", String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(distanceKm)}));
        this.intentReport.putExtra("calories", String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories)}));
        this.intentReport.putExtra("maxSpeed", String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(maxSpeed)}));
        this.intentReport.putExtra("startTime", BuildConfig.FLAVOR + startTime);
        this.intentReport.putExtra("stopTime", BuildConfig.FLAVOR + stopTime);
        this.intentReport.putExtra("date", BuildConfig.FLAVOR + stopDate);
        this.intentReport.putExtra("lowSpeed", BuildConfig.FLAVOR + lowSpeed);
        this.intentReport.putExtra("mediumSpeed", BuildConfig.FLAVOR + mediumSpeed);
        this.intentReport.putExtra("highSpeed", BuildConfig.FLAVOR + highSpeed);
        this.intentReport.putExtra("arrayString", "history1array");
        this.intentReport.putExtra("units", BuildConfig.FLAVOR + this.units);


        startActivity(this.intentReport);
//        fb_interstal(getActivity());
    }

    private void resetVariables() {
        this.distanceKmGPS = 0.0d;
        this.caloriesGPS = 0.0d;
        this.latLon_i = 0;
        this.highSpeedGPS = 0;
        this.mediumSpeedGPS = 0;
        this.lowSpeedGPS = 0;
        this.maxSpeedGPS = 0.0d;
        this.speedGPS = 0.0d;
        this.timeMinutesGPS = 0.0d;
        this.timeMiliSecGPS = 0.0d;
        this.newLocationTime = 0;
        this.delayedSeconds = 0;
        this.isNoGPSMessageShown = false;
        this.iSGPSDialogShown = false;
    }

    private void addToTotal(double distanceKm, double timeMiliSec, double calories) {
        double totalDuration = Double.longBitsToDouble(this.qasa2.getLong("ukupnoTrajanje", Double.doubleToLongBits(0.0d)));
        double totalDistance = Double.longBitsToDouble(this.qasa2.getLong("ukupnoRastojanje", Double.doubleToLongBits(0.0d))) + distanceKm;
        double totalCalories = Double.longBitsToDouble(this.qasa2.getLong("ukupnoKalorije", Double.doubleToLongBits(0.0d))) + calories;
        this.qasa2.edit().putLong("ukupnoTrajanje", Double.doubleToRawLongBits(totalDuration + timeMiliSec)).commit();
        this.qasa2.edit().putLong("ukupnoRastojanje", Double.doubleToRawLongBits(totalDistance)).commit();
        this.qasa2.edit().putLong("ukupnoKalorije", Double.doubleToRawLongBits(totalCalories)).commit();
    }

    private void addToRecords(double distanceKm, double timeMiliSec, double calories, double speed, double pace) {
        double maxDuration = Double.longBitsToDouble(this.qasa2.getLong("rekordTrajanje", Double.doubleToLongBits(0.0d)));
        double maxDistance = Double.longBitsToDouble(this.qasa2.getLong("rekordRastojanje", Double.doubleToLongBits(0.0d)));
        double maxCalories = Double.longBitsToDouble(this.qasa2.getLong("rekordKalorije", Double.doubleToLongBits(0.0d)));
        double maxSpeed = Double.longBitsToDouble(this.qasa2.getLong("rekordBrzina", Double.doubleToLongBits(0.0d)));
        double maxPace = Double.longBitsToDouble(this.qasa2.getLong("rekordRitamRas", Double.doubleToLongBits(0.0d)));
        if (timeMiliSec > maxDuration) {
            this.qasa2.edit().putLong("rekordTrajanje", Double.doubleToRawLongBits(timeMiliSec)).commit();
        }
        if (distanceKm > maxDistance) {
            this.qasa2.edit().putLong("rekordRastojanje", Double.doubleToRawLongBits(distanceKm)).commit();
        }
        if (calories > maxCalories) {
            this.qasa2.edit().putLong("rekordKalorije", Double.doubleToRawLongBits(calories)).commit();
        }
        if (speed > maxSpeed) {
            this.qasa2.edit().putLong("rekordBrzina", Double.doubleToRawLongBits(speed)).commit();
        }
        if ((pace > 0.0d && maxPace == 0.0d) || (pace < maxPace && pace != 0.0d)) {
            this.qasa2.edit().putLong("rekordRitamRas", Double.doubleToRawLongBits(pace)).commit();
        }
    }

    private void addToCharts(double distanceKm, double timeMinutes, double calories) {
        String dayInMonth = getDayInMonth(Calendar.getInstance(Locale.GERMANY).get(5));
        String monthInYear = getMonthInYear();
        String dayOfTheWeek = getDayOfTheWeek();
        double durationWeek = Double.longBitsToDouble(this.qasa2.getLong(dayOfTheWeek + "Vreme", Double.doubleToLongBits(0.0d)));
        double caloriesWeek = Double.longBitsToDouble(this.qasa2.getLong(dayOfTheWeek + "Kalorije", Double.doubleToLongBits(0.0d)));
        double distanceWeek = Double.longBitsToDouble(this.qasa2.getLong(dayOfTheWeek + "Rastojanje", Double.doubleToLongBits(0.0d)));
        this.qasa2.edit().putLong(dayOfTheWeek + "Vreme", Double.doubleToRawLongBits(timeMinutes + durationWeek)).commit();
        this.qasa2.edit().putLong(dayOfTheWeek + "Kalorije", Double.doubleToRawLongBits(calories + caloriesWeek)).commit();
        this.qasa2.edit().putLong(dayOfTheWeek + "Rastojanje", Double.doubleToRawLongBits(distanceKm + distanceWeek)).commit();
        double distanceDay = Double.longBitsToDouble(this.qasa2.getLong("rastojanje" + dayInMonth, Double.doubleToLongBits(0.0d)));
        double durationDay = Double.longBitsToDouble(this.qasa2.getLong("vreme" + dayInMonth, Double.doubleToLongBits(0.0d)));
        double caloriesDay = Double.longBitsToDouble(this.qasa2.getLong("kalorije" + dayInMonth, Double.doubleToLongBits(0.0d)));
        this.qasa2.edit().putLong("vreme" + dayInMonth, Double.doubleToRawLongBits(durationDay + timeMinutes)).commit();
        this.qasa2.edit().putLong("rastojanje" + dayInMonth, Double.doubleToRawLongBits(distanceDay + distanceKm)).commit();
        this.qasa2.edit().putLong("kalorije" + dayInMonth, Double.doubleToRawLongBits(caloriesDay + calories)).commit();
        double distanceMonth = Double.longBitsToDouble(this.qasa2.getLong(monthInYear + "Rastojanje", Double.doubleToLongBits(0.0d)));
        double durationMonth = Double.longBitsToDouble(this.qasa2.getLong(monthInYear + "Vreme", Double.doubleToLongBits(0.0d)));
        double caloriesMonth = Double.longBitsToDouble(this.qasa2.getLong(monthInYear + "Kalorije", Double.doubleToLongBits(0.0d)));
        this.qasa2.edit().putLong(monthInYear + "Vreme", Double.doubleToRawLongBits(durationMonth + timeMinutes)).commit();
        this.qasa2.edit().putLong(monthInYear + "Rastojanje", Double.doubleToRawLongBits(distanceMonth + distanceKm)).commit();
        this.qasa2.edit().putLong(monthInYear + "Kalorije", Double.doubleToRawLongBits(caloriesMonth + calories)).commit();
    }

    private void addToHistory(double distanceKm, double timeMiliSec, double calories, int lowSpeed, int mediumSpeed, int highSpeed, String startTime, String stopTime, double maxSpeed, String dateH, double pace, ArrayList<LatLng> arrayList) {
        String history2array = this.qasa2.getString("history2array", BuildConfig.FLAVOR);
        String history3array = this.qasa2.getString("history3array", BuildConfig.FLAVOR);
        String history4array = this.qasa2.getString("history4array", BuildConfig.FLAVOR);
        String history5array = this.qasa2.getString("history5array", BuildConfig.FLAVOR);
        String history6array = this.qasa2.getString("history6array", BuildConfig.FLAVOR);
        String history7array = this.qasa2.getString("history7array", BuildConfig.FLAVOR);
        String history8array = this.qasa2.getString("history8array", BuildConfig.FLAVOR);
        String history9array = this.qasa2.getString("history9array", BuildConfig.FLAVOR);
        this.qasa2.edit().putString("history2array", this.history1array).commit();
        this.qasa2.edit().putString("history3array", history2array).commit();
        this.qasa2.edit().putString("history4array", history3array).commit();
        this.qasa2.edit().putString("history5array", history4array).commit();
        this.qasa2.edit().putString("history6array", history5array).commit();
        this.qasa2.edit().putString("history7array", history6array).commit();
        this.qasa2.edit().putString("history8array", history7array).commit();
        this.qasa2.edit().putString("history9array", history8array).commit();
        this.qasa2.edit().putString("history10array", history9array).commit();
        String history1Pace = this.qasa2.getString("history1Pace", "0");
        String history2Pace = this.qasa2.getString("history2Pace", "0");
        String history3Pace = this.qasa2.getString("history3Pace", "0");
        String history4Pace = this.qasa2.getString("history4Pace", "0");
        String history5Pace = this.qasa2.getString("history5Pace", "0");
        String history6Pace = this.qasa2.getString("history6Pace", "0");
        String history7Pace = this.qasa2.getString("history7Pace", "0");
        String history8Pace = this.qasa2.getString("history8Pace", "0");
        String history9Pace = this.qasa2.getString("history9Pace", "0");
        this.qasa2.edit().putString("history1Pace", String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(pace)})).commit();
        this.qasa2.edit().putString("history2Pace", history1Pace).commit();
        this.qasa2.edit().putString("history3Pace", history2Pace).commit();
        this.qasa2.edit().putString("history4Pace", history3Pace).commit();
        this.qasa2.edit().putString("history5Pace", history4Pace).commit();
        this.qasa2.edit().putString("history6Pace", history5Pace).commit();
        this.qasa2.edit().putString("history7Pace", history6Pace).commit();
        this.qasa2.edit().putString("history8Pace", history7Pace).commit();
        this.qasa2.edit().putString("history9Pace", history8Pace).commit();
        this.qasa2.edit().putString("history10Pace", history9Pace).commit();
        String history1Distance = this.qasa2.getString("history1Distance", "0");
        String history2Distance = this.qasa2.getString("history2Distance", "0");
        String history3Distance = this.qasa2.getString("history3Distance", "0");
        String history4Distance = this.qasa2.getString("history4Distance", "0");
        String history5Distance = this.qasa2.getString("history5Distance", "0");
        String history6Distance = this.qasa2.getString("history6Distance", "0");
        String history7Distance = this.qasa2.getString("history7Distance", "0");
        String history8Distance = this.qasa2.getString("history8Distance", "0");
        String history9Distance = this.qasa2.getString("history9Distance", "0");
        this.qasa2.edit().putString("history1Distance", String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(distanceKm)})).commit();
        this.qasa2.edit().putString("history2Distance", history1Distance).commit();
        this.qasa2.edit().putString("history3Distance", history2Distance).commit();
        this.qasa2.edit().putString("history4Distance", history3Distance).commit();
        this.qasa2.edit().putString("history5Distance", history4Distance).commit();
        this.qasa2.edit().putString("history6Distance", history5Distance).commit();
        this.qasa2.edit().putString("history7Distance", history6Distance).commit();
        this.qasa2.edit().putString("history8Distance", history7Distance).commit();
        this.qasa2.edit().putString("history9Distance", history8Distance).commit();
        this.qasa2.edit().putString("history10Distance", history9Distance).commit();
        double history1Time = Double.longBitsToDouble(this.qasa2.getLong("history1Duration", Double.doubleToLongBits(0.0d)));
        double history2Time = Double.longBitsToDouble(this.qasa2.getLong("history2Duration", Double.doubleToLongBits(0.0d)));
        double history3Time = Double.longBitsToDouble(this.qasa2.getLong("history3Duration", Double.doubleToLongBits(0.0d)));
        double history4Time = Double.longBitsToDouble(this.qasa2.getLong("history4Duration", Double.doubleToLongBits(0.0d)));
        double history5Time = Double.longBitsToDouble(this.qasa2.getLong("history5Duration", Double.doubleToLongBits(0.0d)));
        double history6Time = Double.longBitsToDouble(this.qasa2.getLong("history6Duration", Double.doubleToLongBits(0.0d)));
        double history7Time = Double.longBitsToDouble(this.qasa2.getLong("history7Duration", Double.doubleToLongBits(0.0d)));
        double history8Time = Double.longBitsToDouble(this.qasa2.getLong("history8Duration", Double.doubleToLongBits(0.0d)));
        double history9Time = Double.longBitsToDouble(this.qasa2.getLong("history9Duration", Double.doubleToLongBits(0.0d)));
        this.qasa2.edit().putLong("history1Duration", Double.doubleToRawLongBits(timeMiliSec)).commit();
        this.qasa2.edit().putLong("history2Duration", Double.doubleToRawLongBits(history1Time)).commit();
        this.qasa2.edit().putLong("history3Duration", Double.doubleToRawLongBits(history2Time)).commit();
        this.qasa2.edit().putLong("history4Duration", Double.doubleToRawLongBits(history3Time)).commit();
        this.qasa2.edit().putLong("history5Duration", Double.doubleToRawLongBits(history4Time)).commit();
        this.qasa2.edit().putLong("history6Duration", Double.doubleToRawLongBits(history5Time)).commit();
        this.qasa2.edit().putLong("history7Duration", Double.doubleToRawLongBits(history6Time)).commit();
        this.qasa2.edit().putLong("history8Duration", Double.doubleToRawLongBits(history7Time)).commit();
        this.qasa2.edit().putLong("history9Duration", Double.doubleToRawLongBits(history8Time)).commit();
        this.qasa2.edit().putLong("history10Duration", Double.doubleToRawLongBits(history9Time)).commit();
        String history1Calories = this.qasa2.getString("history1Calories", "0");
        String history2Calories = this.qasa2.getString("history2Calories", "0");
        String history3Calories = this.qasa2.getString("history3Calories", "0");
        String history4Calories = this.qasa2.getString("history4Calories", "0");
        String history5Calories = this.qasa2.getString("history5Calories", "0");
        String history6Calories = this.qasa2.getString("history6Calories", "0");
        String history7Calories = this.qasa2.getString("history7Calories", "0");
        String history8Calories = this.qasa2.getString("history8Calories", "0");
        String history9Calories = this.qasa2.getString("history9Calories", "0");
        this.qasa2.edit().putString("history1Calories", String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(calories)})).commit();
        this.qasa2.edit().putString("history2Calories", history1Calories).commit();
        this.qasa2.edit().putString("history3Calories", history2Calories).commit();
        this.qasa2.edit().putString("history4Calories", history3Calories).commit();
        this.qasa2.edit().putString("history5Calories", history4Calories).commit();
        this.qasa2.edit().putString("history6Calories", history5Calories).commit();
        this.qasa2.edit().putString("history7Calories", history6Calories).commit();
        this.qasa2.edit().putString("history8Calories", history7Calories).commit();
        this.qasa2.edit().putString("history9Calories", history8Calories).commit();
        this.qasa2.edit().putString("history10Calories", history9Calories).commit();
        String history1date = this.qasa2.getString("history1Date", "0");
        String history2date = this.qasa2.getString("history2Date", "0");
        String history3date = this.qasa2.getString("history3Date", "0");
        String history4date = this.qasa2.getString("history4Date", "0");
        String history5date = this.qasa2.getString("history5Date", "0");
        String history6date = this.qasa2.getString("history6Date", "0");
        String history7date = this.qasa2.getString("history7Date", "0");
        String history8date = this.qasa2.getString("history8Date", "0");
        String history9date = this.qasa2.getString("history9Date", "0");
        this.qasa2.edit().putString("history1Date", dateH).commit();
        this.qasa2.edit().putString("history2Date", history1date).commit();
        this.qasa2.edit().putString("history3Date", history2date).commit();
        this.qasa2.edit().putString("history4Date", history3date).commit();
        this.qasa2.edit().putString("history5Date", history4date).commit();
        this.qasa2.edit().putString("history6Date", history5date).commit();
        this.qasa2.edit().putString("history7Date", history6date).commit();
        this.qasa2.edit().putString("history8Date", history7date).commit();
        this.qasa2.edit().putString("history9Date", history8date).commit();
        this.qasa2.edit().putString("history10Date", history9date).commit();
        String history1startTime = this.qasa2.getString("history1startTime", "0");
        String history2startTime = this.qasa2.getString("history2startTime", "0");
        String history3startTime = this.qasa2.getString("history3startTime", "0");
        String history4startTime = this.qasa2.getString("history4startTime", "0");
        String history5startTime = this.qasa2.getString("history5startTime", "0");
        String history6startTime = this.qasa2.getString("history6startTime", "0");
        String history7startTime = this.qasa2.getString("history7startTime", "0");
        String history8startTime = this.qasa2.getString("history8startTime", "0");
        String history9startTime = this.qasa2.getString("history9startTime", "0");
        this.qasa2.edit().putString("history1startTime", startTime).commit();
        this.qasa2.edit().putString("history2startTime", history1startTime).commit();
        this.qasa2.edit().putString("history3startTime", history2startTime).commit();
        this.qasa2.edit().putString("history4startTime", history3startTime).commit();
        this.qasa2.edit().putString("history5startTime", history4startTime).commit();
        this.qasa2.edit().putString("history6startTime", history5startTime).commit();
        this.qasa2.edit().putString("history7startTime", history6startTime).commit();
        this.qasa2.edit().putString("history8startTime", history7startTime).commit();
        this.qasa2.edit().putString("history9startTime", history8startTime).commit();
        this.qasa2.edit().putString("history10startTime", history9startTime).commit();
        String history1stopTime = this.qasa2.getString("history1stopTime", "0");
        String history2stopTime = this.qasa2.getString("history2stopTime", "0");
        String history3stopTime = this.qasa2.getString("history3stopTime", "0");
        String history4stopTime = this.qasa2.getString("history4stopTime", "0");
        String history5stopTime = this.qasa2.getString("history5stopTime", "0");
        String history6stopTime = this.qasa2.getString("history6stopTime", "0");
        String history7stopTime = this.qasa2.getString("history7stopTime", "0");
        String history8stopTime = this.qasa2.getString("history8stopTime", "0");
        String history9stopTime = this.qasa2.getString("history9stopTime", "0");
        this.qasa2.edit().putString("history1stopTime", stopTime).commit();
        this.qasa2.edit().putString("history2stopTime", history1stopTime).commit();
        this.qasa2.edit().putString("history3stopTime", history2stopTime).commit();
        this.qasa2.edit().putString("history4stopTime", history3stopTime).commit();
        this.qasa2.edit().putString("history5stopTime", history4stopTime).commit();
        this.qasa2.edit().putString("history6stopTime", history5stopTime).commit();
        this.qasa2.edit().putString("history7stopTime", history6stopTime).commit();
        this.qasa2.edit().putString("history8stopTime", history7stopTime).commit();
        this.qasa2.edit().putString("history9stopTime", history8stopTime).commit();
        this.qasa2.edit().putString("history10stopTime", history9stopTime).commit();
        String history1lowSpeed = this.qasa2.getString("history1lowSpeed", "0");
        String history2lowSpeed = this.qasa2.getString("history2lowSpeed", "0");
        String history3lowSpeed = this.qasa2.getString("history3lowSpeed", "0");
        String history4lowSpeed = this.qasa2.getString("history4lowSpeed", "0");
        String history5lowSpeed = this.qasa2.getString("history5lowSpeed", "0");
        String history6lowSpeed = this.qasa2.getString("history6lowSpeed", "0");
        String history7lowSpeed = this.qasa2.getString("history7lowSpeed", "0");
        String history8lowSpeed = this.qasa2.getString("history8lowSpeed", "0");
        String history9lowSpeed = this.qasa2.getString("history9lowSpeed", "0");
        this.qasa2.edit().putString("history1lowSpeed", BuildConfig.FLAVOR + lowSpeed).commit();
        this.qasa2.edit().putString("history2lowSpeed", history1lowSpeed).commit();
        this.qasa2.edit().putString("history3lowSpeed", history2lowSpeed).commit();
        this.qasa2.edit().putString("history4lowSpeed", history3lowSpeed).commit();
        this.qasa2.edit().putString("history5lowSpeed", history4lowSpeed).commit();
        this.qasa2.edit().putString("history6lowSpeed", history5lowSpeed).commit();
        this.qasa2.edit().putString("history7lowSpeed", history6lowSpeed).commit();
        this.qasa2.edit().putString("history8lowSpeed", history7lowSpeed).commit();
        this.qasa2.edit().putString("history9lowSpeed", history8lowSpeed).commit();
        this.qasa2.edit().putString("history10lowSpeed", history9lowSpeed).commit();
        String history1mediumSpeed = this.qasa2.getString("history1mediumSpeed", "0");
        String history2mediumSpeed = this.qasa2.getString("history2mediumSpeed", "0");
        String history3mediumSpeed = this.qasa2.getString("history3mediumSpeed", "0");
        String history4mediumSpeed = this.qasa2.getString("history4mediumSpeed", "0");
        String history5mediumSpeed = this.qasa2.getString("history5mediumSpeed", "0");
        String history6mediumSpeed = this.qasa2.getString("history6mediumSpeed", "0");
        String history7mediumSpeed = this.qasa2.getString("history7mediumSpeed", "0");
        String history8mediumSpeed = this.qasa2.getString("history8mediumSpeed", "0");
        String history9mediumSpeed = this.qasa2.getString("history9mediumSpeed", "0");
        this.qasa2.edit().putString("history1mediumSpeed", BuildConfig.FLAVOR + mediumSpeed).commit();
        this.qasa2.edit().putString("history2mediumSpeed", history1mediumSpeed).commit();
        this.qasa2.edit().putString("history3mediumSpeed", history2mediumSpeed).commit();
        this.qasa2.edit().putString("history4mediumSpeed", history3mediumSpeed).commit();
        this.qasa2.edit().putString("history5mediumSpeed", history4mediumSpeed).commit();
        this.qasa2.edit().putString("history6mediumSpeed", history5mediumSpeed).commit();
        this.qasa2.edit().putString("history7mediumSpeed", history6mediumSpeed).commit();
        this.qasa2.edit().putString("history8mediumSpeed", history7mediumSpeed).commit();
        this.qasa2.edit().putString("history9mediumSpeed", history8mediumSpeed).commit();
        this.qasa2.edit().putString("history10mediumSpeed", history9mediumSpeed).commit();
        String history1highSpeed = this.qasa2.getString("history1highSpeed", "0");
        String history2highSpeed = this.qasa2.getString("history2highSpeed", "0");
        String history3highSpeed = this.qasa2.getString("history3highSpeed", "0");
        String history4highSpeed = this.qasa2.getString("history4highSpeed", "0");
        String history5highSpeed = this.qasa2.getString("history5highSpeed", "0");
        String history6highSpeed = this.qasa2.getString("history6highSpeed", "0");
        String history7highSpeed = this.qasa2.getString("history7highSpeed", "0");
        String history8highSpeed = this.qasa2.getString("history8highSpeed", "0");
        String history9highSpeed = this.qasa2.getString("history9highSpeed", "0");
        this.qasa2.edit().putString("history1highSpeed", BuildConfig.FLAVOR + highSpeed).commit();
        this.qasa2.edit().putString("history2highSpeed", history1highSpeed).commit();
        this.qasa2.edit().putString("history3highSpeed", history2highSpeed).commit();
        this.qasa2.edit().putString("history4highSpeed", history3highSpeed).commit();
        this.qasa2.edit().putString("history5highSpeed", history4highSpeed).commit();
        this.qasa2.edit().putString("history6highSpeed", history5highSpeed).commit();
        this.qasa2.edit().putString("history7highSpeed", history6highSpeed).commit();
        this.qasa2.edit().putString("history8highSpeed", history7highSpeed).commit();
        this.qasa2.edit().putString("history9highSpeed", history8highSpeed).commit();
        this.qasa2.edit().putString("history10highSpeed", history9highSpeed).commit();
        String history1maxSpeed = this.qasa2.getString("history1maxSpeed", "0");
        String history2maxSpeed = this.qasa2.getString("history2maxSpeed", "0");
        String history3maxSpeed = this.qasa2.getString("history3maxSpeed", "0");
        String history4maxSpeed = this.qasa2.getString("history4maxSpeed", "0");
        String history5maxSpeed = this.qasa2.getString("history5maxSpeed", "0");
        String history6maxSpeed = this.qasa2.getString("history6maxSpeed", "0");
        String history7maxSpeed = this.qasa2.getString("history7maxSpeed", "0");
        String history8maxSpeed = this.qasa2.getString("history8maxSpeed", "0");
        String history9maxSpeed = this.qasa2.getString("history9maxSpeed", "0");
        this.qasa2.edit().putString("history1maxSpeed", String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(maxSpeed)})).commit();
        this.qasa2.edit().putString("history2maxSpeed", history1maxSpeed).commit();
        this.qasa2.edit().putString("history3maxSpeed", history2maxSpeed).commit();
        this.qasa2.edit().putString("history4maxSpeed", history3maxSpeed).commit();
        this.qasa2.edit().putString("history5maxSpeed", history4maxSpeed).commit();
        this.qasa2.edit().putString("history6maxSpeed", history5maxSpeed).commit();
        this.qasa2.edit().putString("history7maxSpeed", history6maxSpeed).commit();
        this.qasa2.edit().putString("history8maxSpeed", history7maxSpeed).commit();
        this.qasa2.edit().putString("history9maxSpeed", history8maxSpeed).commit();
        this.qasa2.edit().putString("history10maxSpeed", history9maxSpeed).commit();
    }

    public String getDayOfTheWeek() {
        int dayNumber = Calendar.getInstance().get(7);
        String dayOfTheWeek = "1";
        if (dayNumber == 2) {
            return "pon";
        }
        if (dayNumber == 3) {
            return "uto";
        }
        if (dayNumber == 4) {
            return "sre";
        }
        if (dayNumber == 5) {
            return "cet";
        }
        if (dayNumber == 6) {
            return "pet";
        }
        if (dayNumber == 7) {
            return "sub";
        }
        if (dayNumber == 1) {
            return "ned";
        }
        return dayOfTheWeek;
    }

    public String getMonthInYear() {
        int monthNumber = Calendar.getInstance(Locale.GERMANY).get(2) + 1;
        if (monthNumber == 13) {
            monthNumber = 1;
        }
        String month = "1";
        if (monthNumber == 1) {
            return "januar";
        }
        if (monthNumber == 2) {
            return "februar";
        }
        if (monthNumber == 3) {
            return "mart";
        }
        if (monthNumber == 4) {
            return "april";
        }
        if (monthNumber == 5) {
            return "maj";
        }
        if (monthNumber == 6) {
            return "jun";
        }
        if (monthNumber == 7) {
            return "jul";
        }
        if (monthNumber == 8) {
            return "avgust";
        }
        if (monthNumber == 9) {
            return "septembar";
        }
        if (monthNumber == 10) {
            return "oktobar";
        }
        if (monthNumber == 11) {
            return "novembar";
        }
        if (monthNumber == 12) {
            return "decembar";
        }
        return month;
    }

    public String getDayInMonth(int dayNumber) {
        String dayInMonth = BuildConfig.FLAVOR;
        if (dayNumber == 1) {
            return "prvi";
        }
        if (dayNumber == 2) {
            return "drugi";
        }
        if (dayNumber == 3) {
            return "treci";
        }
        if (dayNumber == 4) {
            return "cetvrti";
        }
        if (dayNumber == 5) {
            return "peti";
        }
        if (dayNumber == 6) {
            return "sesti";
        }
        if (dayNumber == 7) {
            return "sedmi";
        }
        if (dayNumber == 8) {
            return "osmi";
        }
        if (dayNumber == 9) {
            return "deveti";
        }
        if (dayNumber == 10) {
            return "deseti";
        }
        if (dayNumber == 11) {
            return "jedanaesti";
        }
        if (dayNumber == 12) {
            return "dvanaesti";
        }
        if (dayNumber == 13) {
            return "trinaesti";
        }
        if (dayNumber == 14) {
            return "cetrnaesti";
        }
        if (dayNumber == 15) {
            return "petnaesti";
        }
        if (dayNumber == 16) {
            return "sesnaesti";
        }
        if (dayNumber == 17) {
            return "sedamnaesti";
        }
        if (dayNumber == 18) {
            return "osamnaesti";
        }
        if (dayNumber == 19) {
            return "devetnaesti";
        }
        if (dayNumber == 20) {
            return "dvadeseti";
        }
        if (dayNumber == 21) {
            return "dvadesetprvi";
        }
        if (dayNumber == 22) {
            return "dvadesetdrugi";
        }
        if (dayNumber == 23) {
            return "dvadesettreci";
        }
        if (dayNumber == 24) {
            return "dvadesetcetvrti";
        }
        if (dayNumber == 25) {
            return "dvadesetpeti";
        }
        if (dayNumber == 26) {
            return "dvadesetsesti";
        }
        if (dayNumber == 27) {
            return "dvadesetsedmi";
        }
        if (dayNumber == 28) {
            return "dvadesetosmi";
        }
        if (dayNumber == 29) {
            return "dvadesetdeveti";
        }
        if (dayNumber == 30) {
            return "trideseti";
        }
        if (dayNumber == 31) {
            return "tridesetprvi";
        }
        return dayInMonth;
    }

    public void refreshOtherGoals(double calories, double distanceKm, double timeMinutes) {
        if (this.goalCalories <= this.totalCaloriesToday + calories && this.goalCalories > 0.0d) {
            notifyUser(getResources().getString(R.string.Goal_reached_calories));
            this.qasa2.edit().putString("goalCalories", "0").apply();
            this.goalCalories = 0.0d;
        }
        if (this.units.equalsIgnoreCase("Imperial")) {
            if (this.goalDistance * 1.60934d <= this.totalDistanceToday + distanceKm && this.goalDistance > 0.0d) {
                notifyUser(getResources().getString(R.string.Goal_reached_distance));
                this.qasa2.edit().putString("goalDistance", "0").apply();
                this.goalDistance = 0.0d;
            }
        } else if (this.goalDistance <= this.totalDistanceToday + distanceKm && this.goalDistance > 0.0d) {
            notifyUser(getResources().getString(R.string.Goal_reached_distance));
            this.qasa2.edit().putString("goalDistance", "0").apply();
            this.goalDistance = 0.0d;
        }
        if (this.goalTime <= this.totalTimeToday + timeMinutes && this.goalTime > 0.0d) {
            notifyUser(getResources().getString(R.string.Goal_reached_time));
            this.qasa2.edit().putString("goalDuration", "0").apply();
            this.goalTime = 0.0d;
        }
    }

    public void readGoals() {
        try {
            this.goalTime = Double.parseDouble(this.qasa2.getString("goalDuration", "0"));
        } catch (NumberFormatException e) {
            this.goalTime = 0.0d;
        }
        try {
            this.goalCalories = Double.parseDouble(this.qasa2.getString("goalCalories", "0"));
        } catch (NumberFormatException e2) {
            this.goalCalories = 0.0d;
        }
        try {
            this.goalDistance = Double.parseDouble(this.qasa2.getString("goalDistance", "0"));
        } catch (NumberFormatException e3) {
            this.goalDistance = 0.0d;
        }
        this.goalNotification = this.qasa2.getBoolean("isGoalNotifOn", true);
    }

    public void notifyUser(String message) {
        this.mBuilderGoal = new Builder(this.context).setContentTitle(getResources().getString(R.string.GOAL)).setContentText(message).setSmallIcon(R.drawable.running_notif).setAutoCancel(true);
        if (this.goalNotification) {
            this.mBuilderGoal.setDefaults(1);
        } else {
            this.mBuilderGoal.setDefaults(0);
        }
        Intent resultIntent = new Intent(this.context, QuoreMain.class);
        resultIntent.setFlags(603979776);
        this.mBuilderGoal.setContentIntent(PendingIntent.getActivity(this.context, 0, resultIntent, 134217728));
        this.notificationManager.notify(124, this.mBuilderGoal.build());
    }

    private void resetGraphData() {
        Calendar cal1 = Calendar.getInstance(Locale.GERMANY);
        int day1 = cal1.get(6);
        if (day1 != this.qasa2.getInt("dan", 0)) {
            this.qasa2.edit().putInt("dan", day1).commit();
            this.qasa2.edit().putString("goalCalories", "0").commit();
            this.qasa2.edit().putString("goalDistance", "0").commit();
            this.qasa2.edit().putString("goalDuration", "0").commit();
        }
        int week1 = cal1.get(3);
        if (week1 != this.qasa2.getInt("week", 0)) {
            resetGraphDataWeek();
            this.qasa2.edit().putInt("week", week1).commit();
        }
        int month1 = cal1.get(2);
        if (month1 != this.qasa2.getInt("month", 0)) {
            resetGraphDataMonth();
            this.qasa2.edit().putInt("month", month1).commit();
        }
        int year1 = cal1.get(1);
        if (year1 != this.qasa2.getInt("year", 0)) {
            resetGraphDataYear();
            this.qasa2.edit().putInt("year", year1).commit();
        }
    }

    private void resetGraphDataWeek() {
        this.qasa2.edit().putLong("ponVreme", 0).commit();
        this.qasa2.edit().putLong("ponKalorije", 0).commit();
        this.qasa2.edit().putLong("ponRastojanje", 0).commit();
        this.qasa2.edit().putLong("utoVreme", 0).commit();
        this.qasa2.edit().putLong("utoKalorije", 0).commit();
        this.qasa2.edit().putLong("utoRastojanje", 0).commit();
        this.qasa2.edit().putLong("sreVreme", 0).commit();
        this.qasa2.edit().putLong("sreKalorije", 0).commit();
        this.qasa2.edit().putLong("sreRastojanje", 0).commit();
        this.qasa2.edit().putLong("cetVreme", 0).commit();
        this.qasa2.edit().putLong("cetKalorije", 0).commit();
        this.qasa2.edit().putLong("cetRastojanje", 0).commit();
        this.qasa2.edit().putLong("petVreme", 0).commit();
        this.qasa2.edit().putLong("petKalorije", 0).commit();
        this.qasa2.edit().putLong("petRastojanje", 0).commit();
        this.qasa2.edit().putLong("subVreme", 0).commit();
        this.qasa2.edit().putLong("subKalorije", 0).commit();
        this.qasa2.edit().putLong("subRastojanje", 0).commit();
        this.qasa2.edit().putLong("nedVreme", 0).commit();
        this.qasa2.edit().putLong("nedKalorije", 0).commit();
        this.qasa2.edit().putLong("nedRastojanje", 0).commit();
    }

    private void resetGraphDataMonth() {
        for (int i = 1; i < 32; i++) {
            this.qasa2.edit().putLong("vreme" + getDayInMonth(i), Double.doubleToLongBits(0.0d)).commit();
            this.qasa2.edit().putLong("rastojanje" + getDayInMonth(i), Double.doubleToLongBits(0.0d)).commit();
            this.qasa2.edit().putLong("kalorije" + getDayInMonth(i), Double.doubleToLongBits(0.0d)).commit();
        }
    }

    private void resetGraphDataYear() {
        this.qasa2.edit().putLong("januarVreme", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("februarVreme", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("martVreme", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("aprilVreme", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("majVreme", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("junVreme", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("julVreme", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("avgustVreme", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("septembarVreme", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("oktobarVreme", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("novembarVreme", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("decembarVreme", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("januarKalorije", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("februarKalorije", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("martKalorije", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("aprilKalorije", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("majKalorije", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("junKalorije", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("julKalorije", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("avgustKalorije", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("septembarKalorije", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("oktobarKalorije", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("novembarKalorije", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("decembarKalorije", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("januarRastojanje", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("februarRastojanje", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("martRastojanje", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("aprilRastojanje", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("majRastojanje", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("junRastojanje", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("julRastojanje", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("avgustRastojanje", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("septembarRastojanje", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("oktobarRastojanje", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("novembarRastojanje", Double.doubleToLongBits(0.0d)).commit();
        this.qasa2.edit().putLong("decembarRastojanje", Double.doubleToLongBits(0.0d)).commit();
    }

    public void onInit(int status) {
        if (status == 0 && this.tts != null) {
            String lang = Locale.getDefault().getLanguage();
            if (lang.equalsIgnoreCase("de")) {
                this.locLangResult = this.tts.setLanguage(new Locale("de"));
            } else if (lang.equalsIgnoreCase("fr")) {
                this.locLangResult = this.tts.setLanguage(new Locale("fr"));
            } else if (lang.equalsIgnoreCase("it")) {
                this.locLangResult = this.tts.setLanguage(new Locale("it"));
            } else if (lang.equalsIgnoreCase("pt")) {
                this.locLangResult = this.tts.setLanguage(new Locale("pt"));
            } else if (lang.equalsIgnoreCase("es")) {
                this.locLangResult = this.tts.setLanguage(new Locale("es"));
            } else if (lang.equalsIgnoreCase("ru")) {
                this.locLangResult = this.tts.setLanguage(new Locale("ru"));
            } else {
                this.locLangResult = this.tts.setLanguage(Locale.US);
                this.engLang = true;
            }
            if (this.locLangResult == -1 || this.locLangResult == -2) {
                this.locLangResult = this.tts.setLanguage(Locale.US);
                this.engLang = true;
                if (this.locLangResult == -1 || this.locLangResult == -2) {
                    this.isVoiceSupported = false;
                } else {
                    this.isVoiceSupported = true;
                }
            } else {
                this.isVoiceSupported = true;
            }
        }
        if (status == 0 && this.tts != null && this.isVoiceSupported) {
            this.tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                public void onStart(String s) {
                }

                public void onDone(String callbackId) {
                    QuoreGlavna.this.am.abandonAudioFocus(QuoreGlavna.this.mOnAudioFocusChangeListener);
                }

                public void onError(String callbackId) {
                }
            });
        }
    }

    private void speakOut() {
        if (this.isVoiceSupported && this.isVoiceOn && isTrainingStarted && !isAutoPaused && !isRunningPaused) {
            try {
                long speakTime = (SystemClock.elapsedRealtime() - this.mChronometer.getBase()) / 1000;
                boolean z;
                if (this.intervalType == 1) {
                    z = ((double) speakTime) % this.speakTimeInterval < 3.0d && speakTime > 2;
                    this.shouldSpeak = z;
                    if (((double) speakTime) % this.speakTimeInterval > 20.0d) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.resetSpeak = z;
                } else if (this.units.equalsIgnoreCase("Metric")) {
                    z = this.distanceKmGPS % this.speakDistanceInterval < 0.3d && this.distanceKmGPS > 0.3d;
                    this.shouldSpeak = z;
                    if (this.distanceKmGPS % this.speakDistanceInterval > 0.3d) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.resetSpeak = z;
                } else {
                    this.distanceMiGPS = this.distanceKmGPS * 0.621371d;
                    z = this.distanceMiGPS % this.speakDistanceInterval < 0.3d && this.distanceMiGPS > 0.3d;
                    this.shouldSpeak = z;
                    this.resetSpeak = this.distanceMiGPS % this.speakDistanceInterval > 0.3d;
                }
                if (this.shouldSpeak && this.isSpeakOutTime) {
                    String text = "0";
                    String distance = BuildConfig.FLAVOR;
                    float speakTimeMinutes = (float) (speakTime / 60);
                    if (((int) (((float) speakTime) - (60.0f * speakTimeMinutes))) == 30) {
                        speakTimeMinutes = (float) (((double) speakTimeMinutes) + 0.5d);
                    }
                    if (this.units.equalsIgnoreCase("Imperial")) {
                        if (this.engLang) {
                            distance = String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(this.distanceKmGPS * 0.621371d)}) + " " + getResources().getString(R.string.miles_v);
                            this.speedForVoice = String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(this.speedGPS * 0.621371d)}) + " " + getResources().getString(R.string.miles_per_hour_v);
                            if (this.distanceKmGPS > 0.0d) {
                                this.paceForVoice = String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(((double) ((SystemClock.elapsedRealtime() - this.mChronometer.getBase()) / 60000)) / (this.distanceKmGPS * 0.621371d))}) + " " + getResources().getString(R.string.minutes_per_mile_v);
                            } else {
                                this.paceForVoice = "0 " + getResources().getString(R.string.minutes_per_mile_v);
                            }
                        } else {
                            distance = String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(this.distanceKmGPS * 0.621371d)}) + " " + getResources().getString(R.string.miles_v2);
                            this.speedForVoice = String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(this.speedGPS * 0.621371d)}) + " " + getResources().getString(R.string.miles_per_hour_v2);
                            if (this.distanceKmGPS > 0.0d) {
                                this.paceForVoice = String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(((double) ((SystemClock.elapsedRealtime() - this.mChronometer.getBase()) / 60000)) / (this.distanceKmGPS * 0.621371d))}) + " " + getResources().getString(R.string.minutes_per_mile_v2);
                            } else {
                                this.paceForVoice = "0 " + getResources().getString(R.string.minutes_per_mile_v2);
                            }
                        }
                    } else if (this.engLang) {
                        distance = String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(this.distanceKmGPS)}) + " " + getResources().getString(R.string.kilometer_v);
                        this.speedForVoice = String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(this.speedGPS)}) + " " + getResources().getString(R.string.kilometers_per_hour_v);
                        if (this.distanceKmGPS > 0.0d) {
                            this.paceForVoice = String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(((double) ((SystemClock.elapsedRealtime() - this.mChronometer.getBase()) / 60000)) / this.distanceKmGPS)}) + " " + getResources().getString(R.string.minutes_per_kilometer_v);
                        } else {
                            this.paceForVoice = "0 " + getResources().getString(R.string.minutes_per_kilometer_v);
                        }
                    } else {
                        distance = String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(this.distanceKmGPS)}) + " " + getResources().getString(R.string.kilometer_v2);
                        this.speedForVoice = String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(this.speedGPS)}) + " " + getResources().getString(R.string.kilometers_per_hour_v2);
                        if (this.distanceKmGPS > 0.0d) {
                            this.paceForVoice = String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(((double) ((SystemClock.elapsedRealtime() - this.mChronometer.getBase()) / 60000)) / this.distanceKmGPS)}) + " " + getResources().getString(R.string.minutes_per_kilometer_v);
                        } else {
                            this.paceForVoice = "0 " + getResources().getString(R.string.minutes_per_kilometer_v2);
                        }
                    }
                    if (this.isVoiceDistanceOn) {
                        if (text.equalsIgnoreCase("0")) {
                            if (this.engLang) {
                                text = getResources().getString(R.string.You_run_v) + " " + distance;
                            } else {
                                text = getResources().getString(R.string.You_run_v2) + " " + distance;
                            }
                        } else if (this.engLang) {
                            text = text + ", " + getResources().getString(R.string.and_run_v) + " " + distance;
                        } else {
                            text = text + ", " + getResources().getString(R.string.and_run_v2) + " " + distance;
                        }
                    }
                    if (this.isVoiceTimeOn) {
                        if (text.equalsIgnoreCase("0")) {
                            if (this.engLang) {
                                text = getResources().getString(R.string.You_run_v) + " " + speakTimeMinutes + " " + getResources().getString(R.string.minutes_v);
                            } else {
                                text = getResources().getString(R.string.You_run_v2) + " " + speakTimeMinutes + " " + getResources().getString(R.string.minutes_v2);
                            }
                        } else if (this.engLang) {
                            text = text + ", " + getResources().getString(R.string.it_took_you_v) + " " + speakTimeMinutes + " " + getResources().getString(R.string.minutes_v);
                        } else {
                            text = text + ", " + getResources().getString(R.string.it_took_you_v2) + " " + speakTimeMinutes + " " + getResources().getString(R.string.minutes_v2);
                        }
                    }
                    if (this.isVoiceSpeedOn) {
                        if (text.equalsIgnoreCase("0")) {
                            if (this.engLang) {
                                text = getResources().getString(R.string.Your_speed_is_v) + " " + this.speedForVoice;
                            } else {
                                text = getResources().getString(R.string.Your_speed_is_v2) + " " + this.speedForVoice;
                            }
                        } else if (this.engLang) {
                            text = text + ", " + getResources().getString(R.string.Your_speed_is_v) + " " + this.speedForVoice;
                        } else {
                            text = text + ", " + getResources().getString(R.string.Your_speed_is_v2) + " " + this.speedForVoice;
                        }
                    }
                    if (this.isVoicePaceOn) {
                        if (text.equalsIgnoreCase("0")) {
                            if (this.engLang) {
                                text = getResources().getString(R.string.Your_pace_is_v) + " " + this.paceForVoice;
                            } else {
                                text = getResources().getString(R.string.Your_pace_is_v2) + " " + this.paceForVoice;
                            }
                        } else if (this.engLang) {
                            text = text + ", " + getResources().getString(R.string.Your_pace_is_v) + " " + this.paceForVoice;
                        } else {
                            text = text + ", " + getResources().getString(R.string.Your_pace_is_v2) + " " + this.paceForVoice;
                        }
                    }
                    if (this.isVoiceCaloriesOn) {
                        if (text.equalsIgnoreCase("0")) {
                            if (this.engLang) {
                                text = getResources().getString(R.string.You_burned_v) + " " + String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(this.caloriesGPS)}) + " " + getResources().getString(R.string.calories_v);
                            } else {
                                text = getResources().getString(R.string.You_burned_v2) + " " + String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(this.caloriesGPS)}) + " " + getResources().getString(R.string.calories_v2);
                            }
                        } else if (this.engLang) {
                            text = text + ", " + getResources().getString(R.string.and_burned_v) + " " + String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(this.caloriesGPS)}) + " " + getResources().getString(R.string.calories_v);
                        } else {
                            text = text + ", " + getResources().getString(R.string.and_burned_v2) + " " + String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(this.caloriesGPS)}) + " " + getResources().getString(R.string.calories_v2);
                        }
                    }
                    if (!text.equalsIgnoreCase("0")) {
                        this.am.requestAudioFocus(this.mOnAudioFocusChangeListener, 3, 3);
                        this.tts.speak(text, 0, this.mHashMap);
                    }
                    this.isSpeakOutTime = false;
                } else if (this.resetSpeak) {
                    this.isSpeakOutTime = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void sendLocationToUI() {
        if (this.units.equalsIgnoreCase("Imperial")) {
            this.tvDistance.setText(String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(this.distanceKmGPS * 0.621371d)}));
            this.tvCalories.setText(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(this.caloriesGPS)}));
            this.tvSpeed.setText(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(this.speedGPS * 0.621371d)}));
            return;
        }
        this.tvDistance.setText(String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(this.distanceKmGPS)}));
        this.tvCalories.setText(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(this.caloriesGPS)}));
        this.tvSpeed.setText(String.format(Locale.US, "%.1f", new Object[]{Double.valueOf(this.speedGPS)}));
    }

    private void setGPSIndicator(float accuracyGPS) {
        if (accuracyGPS == 0.0f && this.signalGPS != 0) {
            this.signalGPS = 0;
            this.gpsSignalIv.setImageResource(R.drawable.gps_searching);
        } else if (accuracyGPS > 30.0f && this.signalGPS != 1) {
            this.signalGPS = 1;
            this.gpsSignalIv.setImageResource(R.drawable.gps_bad);
        } else if (accuracyGPS > 15.0f && accuracyGPS <= 30.0f && this.signalGPS != 2) {
            this.signalGPS = 2;
            this.gpsSignalIv.setImageResource(R.drawable.gps_fair);
        } else if (accuracyGPS > 0.0f && accuracyGPS <= 15.0f && this.signalGPS != 3) {
            this.signalGPS = 3;
            this.gpsSignalIv.setImageResource(R.drawable.gps_good);
        }
    }


}
