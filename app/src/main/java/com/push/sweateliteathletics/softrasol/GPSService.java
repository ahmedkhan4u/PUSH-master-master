package com.push.sweateliteathletics.softrasol;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat.Builder;
import java.util.Locale;

public class GPSService extends Service implements LocationListener {
    private int GPSi = 0;
    private boolean autoPause = false;
    private double caloriesGPS = 0.0d;
    private double distanceBetweenTwoPointsGPS;
    private double distanceKmGPS = 0.0d;
    private double elevation1;
    private double elevation2;
    private double elevation3;
    private double elevation4;
    private double elevation5;
    private double elevation6;
    private double elevationGPS = 0.0d;
    private Location lastLoc;
    private LocationManager locationManager;
    private Builder mBuilderDistance;
    private int nadmI = 1;
    private Notification notif;
    private NotificationManager notificationManager;
    private double previousCaloriesGPS = 0.0d;
    private double putNagib;
    private double speedGPS;
    private double speedMeterMinGPS = 0.0d;
    private double startElevationGPS;
    private double startTimeMiliSec;
    private double stepenNagiba;
    private double stopElevationGPS;
    private int timeInOnePlace = 0;
    private double timeMiliSecGPS;
    private double timeMinutesGPS;
    private String units;
    private double visinaNagib;
    private double vremeAutoDeoPauze = 0.0d;
    private double vremeAutoPauza = 0.0d;
    private double vremeAutoZaustavljanja = 0.0d;
    private double vremeDeoPauze = 0.0d;
    private double vremePauza = 0.0d;
    private double vremeZaustavljanja = 0.0d;
    private double weight;

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (QuoreGlavna.isTrainingStarted) {
            this.units = intent.getStringExtra("jedinicaMere");
            this.weight = intent.getDoubleExtra("weight", 75.0d);
            this.autoPause = intent.getBooleanExtra("autoPause", false);
            this.startTimeMiliSec = (double) SystemClock.elapsedRealtime();


            this.notificationManager = (NotificationManager) getSystemService("notification");
            Intent resultIntent = new Intent(this, QuoreMain.class);
            resultIntent.setFlags(603979776);
            if (Build.VERSION.SDK_INT >= 26) {

                String str = "com.QuoreApps.fitnessworkout.runningtracker.GPS";
                NotificationChannel notificationChannel = new NotificationChannel(str, "GPS notification", 2);
                notificationChannel.enableLights(false);
                notificationChannel.enableVibration(false);
                this.notificationManager.createNotificationChannel(notificationChannel);

                StringBuilder stringBuilder6 = new StringBuilder();
                stringBuilder6.append(getResources().getString(R.string.Distance));
                stringBuilder6.append(": 0");
                /*  smallIcon = smallIcon.setContentTitle(stringBuilder6.toString());*/
                stringBuilder6 = new StringBuilder();
                stringBuilder6.append(getResources().getString(R.string.Calories));
                stringBuilder6.append(": 0");


                mBuilderDistance = new Builder(this).setSmallIcon(R.drawable.running_notif).setContentText(stringBuilder6.toString()).setAutoCancel(false).setOngoing(true).setChannelId(str).setContentIntent(PendingIntent.getActivity(this, 0, resultIntent, 134217728));

            } else {

                this.mBuilderDistance = new Builder(this).setSmallIcon(R.drawable.running_notif).setContentTitle(getResources().getString(R.string.Distance) + ": 0").setContentText(getResources().getString(R.string.Calories) + ": 0").setAutoCancel(false).setOngoing(true).setContentIntent(PendingIntent.getActivity(this, 0, resultIntent, 134217728));

            }
            this.notif = this.mBuilderDistance.build();

            startForeground(123, this.notif);
            this.notificationManager.notify(123, this.mBuilderDistance.build());
            this.locationManager = (LocationManager) getSystemService("location");
            this.locationManager.requestLocationUpdates("gps", 0, 0.0f, this);
        } else {
            if (this.notificationManager == null) {
                this.notificationManager = (NotificationManager) getSystemService("notification");
            }
            this.notificationManager.cancel(123);
            if (this.locationManager == null) {
                this.locationManager = (LocationManager) getSystemService("location");
            }
            this.locationManager.removeUpdates(this);
            resetVariables();
            stopForeground(true);
            stopSelf();
        }
        return 1;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void onLocationChanged(Location location) {
        if (location != null && QuoreGlavna.isTrainingStarted) {
            checkPause();
            if (this.lastLoc != null) {
                this.distanceBetweenTwoPointsGPS = (double) (this.lastLoc.distanceTo(location) / 1000.0f);
                checkAutoPause();
                if (this.distanceBetweenTwoPointsGPS < 4.0E-4d) {
                    this.distanceBetweenTwoPointsGPS = 0.0d;
                }
                if (!(this.GPSi <= 4 || QuoreGlavna.isAutoPaused || QuoreGlavna.isRunningPaused)) {
                    this.distanceKmGPS += this.distanceBetweenTwoPointsGPS;
                }
            }
            this.speedGPS = ((double) location.getSpeed()) * 3.6d;
            this.lastLoc = location;
            if (this.GPSi < 2) {
                double altitude = location.getAltitude();
                this.elevation6 = altitude;
                this.elevation5 = altitude;
                this.elevation4 = altitude;
                this.elevation3 = altitude;
                this.elevation2 = altitude;
                this.elevation1 = altitude;
            } else {
                this.elevation6 = this.elevation5;
                this.elevation5 = this.elevation4;
                this.elevation4 = this.elevation3;
                this.elevation3 = this.elevation2;
                this.elevation2 = this.elevation1;
                this.elevation1 = location.getAltitude();
            }
            if (this.GPSi > 7) {
                this.elevationGPS = (((((this.elevation1 + this.elevation2) + this.elevation3) + this.elevation4) + this.elevation5) + this.elevation6) / 6.0d;
                this.stopElevationGPS = this.elevationGPS;
                this.timeMiliSecGPS = ((double) SystemClock.elapsedRealtime()) - this.startTimeMiliSec;
                this.timeMinutesGPS = ((this.timeMiliSecGPS - this.vremePauza) - this.vremeAutoPauza) / 60000.0d;
                if (this.timeMinutesGPS > 0.0d) {
                    this.speedMeterMinGPS = (this.distanceKmGPS * 1000.0d) / this.timeMinutesGPS;
                } else {
                    this.speedMeterMinGPS = 0.0d;
                }
                if (Double.isNaN(this.speedMeterMinGPS)) {
                    this.speedMeterMinGPS = 0.0d;
                }
                if (this.GPSi > 9) {
                    this.visinaNagib = this.startElevationGPS - this.stopElevationGPS;
                    this.putNagib = this.distanceKmGPS * 1000.0d;
                    if (this.putNagib > 0.0d) {
                        this.stepenNagiba = Math.asin(this.visinaNagib / this.putNagib) / 100.0d;
                    } else {
                        this.stepenNagiba = 0.0d;
                    }
                    if (Double.isNaN(this.stepenNagiba)) {
                        this.stepenNagiba = 0.0d;
                    }
                    if (!(QuoreGlavna.isAutoPaused || QuoreGlavna.isRunningPaused)) {
                        this.caloriesGPS = ((((((this.speedMeterMinGPS * 0.2d) + 3.5d) + ((this.speedMeterMinGPS * this.stepenNagiba) * 0.9d)) / 3.5d) * this.weight) * this.timeMinutesGPS) / 60.0d;
                    }
                    if (this.caloriesGPS < this.previousCaloriesGPS) {
                        this.caloriesGPS = this.previousCaloriesGPS;
                    }
                    this.previousCaloriesGPS = this.caloriesGPS;
                }
            }
            this.GPSi++;
            if (this.nadmI == 1 && this.elevationGPS != 0.0d) {
                this.startElevationGPS = this.elevationGPS;
                this.stopElevationGPS = this.elevationGPS;
                this.nadmI = 2;
            }
            if (QuoreGlavna.isAutoPaused || QuoreGlavna.isRunningPaused) {
                this.speedGPS = 0.0d;
            }
            try {
                if (this.units.equalsIgnoreCase("Imperial")) {
                    this.mBuilderDistance.setContentTitle(getResources().getString(R.string.Distance) + ": " + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(this.distanceKmGPS * 0.621371d)}) + " mi");
                } else {
                    this.mBuilderDistance.setContentTitle(getResources().getString(R.string.Distance) + ": " + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(this.distanceKmGPS)}) + " km");
                }
                this.mBuilderDistance.setContentText(getResources().getString(R.string.Calories) + ": " + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(this.caloriesGPS)}) + " kcal");
                this.notificationManager.notify(123, this.mBuilderDistance.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Intent i = new Intent("com.zeopoxa.fitness.running.GPSData");
            i.putExtra("caloriesGPS", this.caloriesGPS);
            i.putExtra("distanceKmGPS", this.distanceKmGPS);
            i.putExtra("speedGPS", this.speedGPS);
            i.putExtra("lng", location.getLongitude());
            i.putExtra("lat", location.getLatitude());
            i.putExtra("accuracyGPS", location.getAccuracy());
            sendBroadcast(i);
        }
    }

    private void checkPause() {
        if (QuoreGlavna.isRunningPaused) {
            if (this.vremeZaustavljanja != 0.0d) {
                this.vremeDeoPauze = ((double) System.currentTimeMillis()) - this.vremeZaustavljanja;
            }
            this.vremeZaustavljanja = (double) System.currentTimeMillis();
            this.vremePauza += this.vremeDeoPauze;
            this.vremeDeoPauze = 0.0d;
            return;
        }
        this.vremeZaustavljanja = 0.0d;
        this.vremeDeoPauze = 0.0d;
    }

    private void checkAutoPause() {
        if (QuoreGlavna.isRunningPaused) {
            this.timeInOnePlace = 0;
            QuoreGlavna.isAutoPaused = false;
            return;
        }
        if (this.distanceBetweenTwoPointsGPS < 5.0E-4d) {
            this.timeInOnePlace++;
            if (this.timeInOnePlace > 4 && this.autoPause) {
                QuoreGlavna.isAutoPaused = true;
            }
        } else {
            this.timeInOnePlace = 0;
            QuoreGlavna.isAutoPaused = false;
        }
        if (QuoreGlavna.isAutoPaused) {
            if (this.vremeAutoZaustavljanja != 0.0d) {
                this.vremeAutoDeoPauze = ((double) System.currentTimeMillis()) - this.vremeAutoZaustavljanja;
            }
            this.vremeAutoZaustavljanja = (double) System.currentTimeMillis();
            this.vremeAutoPauza += this.vremeAutoDeoPauze;
            this.vremeAutoDeoPauze = 0.0d;
            return;
        }
        this.vremeAutoZaustavljanja = 0.0d;
        this.vremeAutoDeoPauze = 0.0d;
    }

    private void resetVariables() {
        this.distanceKmGPS = 0.0d;
        this.previousCaloriesGPS = 0.0d;
        this.caloriesGPS = 0.0d;
        this.GPSi = 0;
        this.nadmI = 1;
        this.stopElevationGPS = 0.0d;
        this.startElevationGPS = 0.0d;
        this.elevationGPS = 0.0d;
        this.elevation6 = 0.0d;
        this.elevation5 = 0.0d;
        this.elevation4 = 0.0d;
        this.elevation3 = 0.0d;
        this.elevation2 = 0.0d;
        this.elevation1 = 0.0d;
        this.lastLoc = null;
        this.distanceBetweenTwoPointsGPS = 0.0d;
        this.speedMeterMinGPS = 0.0d;
        this.timeMinutesGPS = 0.0d;
        this.timeMiliSecGPS = 0.0d;
        this.stepenNagiba = 0.0d;
        this.putNagib = 0.0d;
        this.visinaNagib = 0.0d;
        this.vremePauza = 0.0d;
        this.vremeAutoPauza = 0.0d;
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onProviderDisabled(String provider) {
    }
}
