package com.push.sweateliteathletics.softrasol;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class QuoreSplash extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (QuoreMain.isStarted || QuoreGlavna.isTrainingStarted) {
            finish();
            return;
        }
        setContentView(R.layout.activity_splash);

        new Thread() {
            public void run() {
                String PREFS_NAME;
                SharedPreferences qasa2;
                String history1Time;
                String history2Time;
                String history3Time;
                String history4Time;
                String history5Time;
                String history6Time;
                String history7Time;
                String history8Time;
                String history9Time;
                String history10Time;
                double maxDuration;
                double overallDistance;
                try {
                    this.sleep(0);
                    PREFS_NAME = "qA1sa2";
                    qasa2 = QuoreSplash.this.getSharedPreferences("qA1sa2", 0);
                    if (qasa2.getBoolean("my_first_time", true)) {
                        qasa2.edit().putBoolean("newTime", false).commit();
                        QuoreSplash.this.startActivity(new Intent(QuoreSplash.this, QuoreStart1.class));
                        QuoreSplash.this.finish();
                        return;
                    }
                    if (qasa2.getBoolean("newTime", true)) {
                        qasa2.edit().putBoolean("newTime", false).commit();
                        history1Time = qasa2.getString("history1Time", "0");
                        history2Time = qasa2.getString("history2Time", "0");
                        history3Time = qasa2.getString("history3Time", "0");
                        history4Time = qasa2.getString("history4Time", "0");
                        history5Time = qasa2.getString("history5Time", "0");
                        history6Time = qasa2.getString("history6Time", "0");
                        history7Time = qasa2.getString("history7Time", "0");
                        history8Time = qasa2.getString("history8Time", "0");
                        history9Time = qasa2.getString("history9Time", "0");
                        history10Time = qasa2.getString("history10Time", "0");
                        qasa2.edit().putLong("history1Duration", Double.doubleToRawLongBits(Double.valueOf(history1Time).doubleValue() * 60000.0d)).commit();
                        qasa2.edit().putLong("history2Duration", Double.doubleToRawLongBits(Double.valueOf(history2Time).doubleValue() * 60000.0d)).commit();
                        qasa2.edit().putLong("history3Duration", Double.doubleToRawLongBits(Double.valueOf(history3Time).doubleValue() * 60000.0d)).commit();
                        qasa2.edit().putLong("history4Duration", Double.doubleToRawLongBits(Double.valueOf(history4Time).doubleValue() * 60000.0d)).commit();
                        qasa2.edit().putLong("history5Duration", Double.doubleToRawLongBits(Double.valueOf(history5Time).doubleValue() * 60000.0d)).commit();
                        qasa2.edit().putLong("history6Duration", Double.doubleToRawLongBits(Double.valueOf(history6Time).doubleValue() * 60000.0d)).commit();
                        qasa2.edit().putLong("history7Duration", Double.doubleToRawLongBits(Double.valueOf(history7Time).doubleValue() * 60000.0d)).commit();
                        qasa2.edit().putLong("history8Duration", Double.doubleToRawLongBits(Double.valueOf(history8Time).doubleValue() * 60000.0d)).commit();
                        qasa2.edit().putLong("history9Duration", Double.doubleToRawLongBits(Double.valueOf(history9Time).doubleValue() * 60000.0d)).commit();
                        qasa2.edit().putLong("history10Duration", Double.doubleToRawLongBits(Double.valueOf(history10Time).doubleValue() * 60000.0d)).commit();
                        maxDuration = Double.longBitsToDouble(qasa2.getLong("rekordTrajanje", Double.doubleToLongBits(0.0d)));
                        qasa2.edit().putLong("rekordTrajanje", Double.doubleToRawLongBits(Double.valueOf(maxDuration).doubleValue() * 3600000.0d)).commit();
                        overallDistance = Double.longBitsToDouble(qasa2.getLong("ukupnoTrajanje", Double.doubleToLongBits(0.0d)));
                        qasa2.edit().putLong("ukupnoTrajanje", Double.doubleToRawLongBits(Double.valueOf(overallDistance).doubleValue() * 3600000.0d)).commit();
                    }
                    QuoreSplash.this.startActivity(new Intent(QuoreSplash.this, SignIn.class));
                    QuoreSplash.this.finish();
                 } catch (InterruptedException e) {
                    e.printStackTrace();
                    PREFS_NAME = "qA1sa2";
                    qasa2 = QuoreSplash.this.getSharedPreferences("qA1sa2", 0);
                    if (qasa2.getBoolean("my_first_time", true)) {
                        qasa2.edit().putBoolean("newTime", false).commit();
                        QuoreSplash.this.startActivity(new Intent(QuoreSplash.this, QuoreStart1.class));
                        QuoreSplash.this.finish();

                        return;
                    }
                    if (qasa2.getBoolean("newTime", true)) {
                        qasa2.edit().putBoolean("newTime", false).commit();
                        history1Time = qasa2.getString("history1Time", "0");
                        history2Time = qasa2.getString("history2Time", "0");
                        history3Time = qasa2.getString("history3Time", "0");
                        history4Time = qasa2.getString("history4Time", "0");
                        history5Time = qasa2.getString("history5Time", "0");
                        history6Time = qasa2.getString("history6Time", "0");
                        history7Time = qasa2.getString("history7Time", "0");
                        history8Time = qasa2.getString("history8Time", "0");
                        history9Time = qasa2.getString("history9Time", "0");
                        history10Time = qasa2.getString("history10Time", "0");
                        qasa2.edit().putLong("history1Duration", Double.doubleToRawLongBits(Double.valueOf(history1Time).doubleValue() * 60000.0d)).commit();
                        qasa2.edit().putLong("history2Duration", Double.doubleToRawLongBits(Double.valueOf(history2Time).doubleValue() * 60000.0d)).commit();
                        qasa2.edit().putLong("history3Duration", Double.doubleToRawLongBits(Double.valueOf(history3Time).doubleValue() * 60000.0d)).commit();
                        qasa2.edit().putLong("history4Duration", Double.doubleToRawLongBits(Double.valueOf(history4Time).doubleValue() * 60000.0d)).commit();
                        qasa2.edit().putLong("history5Duration", Double.doubleToRawLongBits(Double.valueOf(history5Time).doubleValue() * 60000.0d)).commit();
                        qasa2.edit().putLong("history6Duration", Double.doubleToRawLongBits(Double.valueOf(history6Time).doubleValue() * 60000.0d)).commit();
                        qasa2.edit().putLong("history7Duration", Double.doubleToRawLongBits(Double.valueOf(history7Time).doubleValue() * 60000.0d)).commit();
                        qasa2.edit().putLong("history8Duration", Double.doubleToRawLongBits(Double.valueOf(history8Time).doubleValue() * 60000.0d)).commit();
                        qasa2.edit().putLong("history9Duration", Double.doubleToRawLongBits(Double.valueOf(history9Time).doubleValue() * 60000.0d)).commit();
                        qasa2.edit().putLong("history10Duration", Double.doubleToRawLongBits(Double.valueOf(history10Time).doubleValue() * 60000.0d)).commit();
                        maxDuration = Double.longBitsToDouble(qasa2.getLong("rekordTrajanje", Double.doubleToLongBits(0.0d)));
                        qasa2.edit().putLong("rekordTrajanje", Double.doubleToRawLongBits(Double.valueOf(maxDuration).doubleValue() * 3600000.0d)).commit();
                        overallDistance = Double.longBitsToDouble(qasa2.getLong("ukupnoTrajanje", Double.doubleToLongBits(0.0d)));
                        qasa2.edit().putLong("ukupnoTrajanje", Double.doubleToRawLongBits(Double.valueOf(overallDistance).doubleValue() * 3600000.0d)).commit();
                    }
                    QuoreSplash.this.startActivity(new Intent(QuoreSplash.this, SignIn.class));
                    QuoreSplash.this.finish();
//
                } catch (Throwable th) {
                    PREFS_NAME = "qA1sa2";
                    qasa2 = QuoreSplash.this.getSharedPreferences("qA1sa2", 0);
                    if (qasa2.getBoolean("my_first_time", true)) {
                        qasa2.edit().putBoolean("newTime", false).commit();
                        QuoreSplash.this.startActivity(new Intent(QuoreSplash.this, QuoreStart1.class));
                        QuoreSplash.this.finish();

                    } else {
                        if (qasa2.getBoolean("newTime", true)) {
                            qasa2.edit().putBoolean("newTime", false).commit();
                            history1Time = qasa2.getString("history1Time", "0");
                            history2Time = qasa2.getString("history2Time", "0");
                            history3Time = qasa2.getString("history3Time", "0");
                            history4Time = qasa2.getString("history4Time", "0");
                            history5Time = qasa2.getString("history5Time", "0");
                            history6Time = qasa2.getString("history6Time", "0");
                            history7Time = qasa2.getString("history7Time", "0");
                            history8Time = qasa2.getString("history8Time", "0");
                            history9Time = qasa2.getString("history9Time", "0");
                            history10Time = qasa2.getString("history10Time", "0");
                            qasa2.edit().putLong("history1Duration", Double.doubleToRawLongBits(Double.valueOf(history1Time).doubleValue() * 60000.0d)).commit();
                            qasa2.edit().putLong("history2Duration", Double.doubleToRawLongBits(Double.valueOf(history2Time).doubleValue() * 60000.0d)).commit();
                            qasa2.edit().putLong("history3Duration", Double.doubleToRawLongBits(Double.valueOf(history3Time).doubleValue() * 60000.0d)).commit();
                            qasa2.edit().putLong("history4Duration", Double.doubleToRawLongBits(Double.valueOf(history4Time).doubleValue() * 60000.0d)).commit();
                            qasa2.edit().putLong("history5Duration", Double.doubleToRawLongBits(Double.valueOf(history5Time).doubleValue() * 60000.0d)).commit();
                            qasa2.edit().putLong("history6Duration", Double.doubleToRawLongBits(Double.valueOf(history6Time).doubleValue() * 60000.0d)).commit();
                            qasa2.edit().putLong("history7Duration", Double.doubleToRawLongBits(Double.valueOf(history7Time).doubleValue() * 60000.0d)).commit();
                            qasa2.edit().putLong("history8Duration", Double.doubleToRawLongBits(Double.valueOf(history8Time).doubleValue() * 60000.0d)).commit();
                            qasa2.edit().putLong("history9Duration", Double.doubleToRawLongBits(Double.valueOf(history9Time).doubleValue() * 60000.0d)).commit();
                            qasa2.edit().putLong("history10Duration", Double.doubleToRawLongBits(Double.valueOf(history10Time).doubleValue() * 60000.0d)).commit();
                            qasa2.edit().putLong("rekordTrajanje", Double.doubleToRawLongBits(Double.valueOf(Double.longBitsToDouble(qasa2.getLong("rekordTrajanje", Double.doubleToLongBits(0.0d)))).doubleValue() * 3600000.0d)).commit();
                            qasa2.edit().putLong("ukupnoTrajanje", Double.doubleToRawLongBits(Double.valueOf(Double.longBitsToDouble(qasa2.getLong("ukupnoTrajanje", Double.doubleToLongBits(0.0d)))).doubleValue() * 3600000.0d)).commit();
                        }
                        QuoreSplash.this.startActivity(new Intent(QuoreSplash.this, SignIn.class));
                        QuoreSplash.this.finish();

                    }
                }
            }
        }.start();
    }





}
