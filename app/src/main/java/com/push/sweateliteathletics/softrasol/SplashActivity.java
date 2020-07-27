package com.push.sweateliteathletics.softrasol;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.push.sweateliteathletics.softrasol.OnBoardingScreens.OnBoardingActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        CountDownTimer countDownTimer = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
//                SharedPreferences sharedPreferences = getSharedPreferences("fitmind", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("time","1593633779665");
//                editor.apply();

            }

            @Override
            public void onFinish() {

                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    startActivity(new Intent(getApplicationContext(), QuoreMain.class));
                    finish();
                }else {
                    startActivity(new Intent(getApplicationContext(), OnBoardingActivity.class));
                }

//                SharedPreferences sharedPreferences = getSharedPreferences("tutorial", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//
//                if (!sharedPreferences.contains("first_time")){
//                    Intent intent = new Intent(SplashActivity.this, OnBoardingActivity.class);
//                    startActivity(intent);
//                    finish();
//                }else {
//                    startActivity(new Intent(getApplicationContext(), SignIn.class));
//                    finish();
//                }






//                SharedPreferences sharedPreferences = getSharedPreferences("fitbody", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("time","180000");
//                editor.apply();
            }
        };

        countDownTimer.start();

    }
}
