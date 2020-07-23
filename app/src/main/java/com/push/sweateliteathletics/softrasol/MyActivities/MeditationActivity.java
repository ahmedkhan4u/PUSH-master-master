package com.push.sweateliteathletics.softrasol.MyActivities;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.push.sweateliteathletics.softrasol.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class MeditationActivity extends AppCompatActivity {

    private String title, description, audio, image;
    private ImageView mBtnPlay, bgImage, mBtnPause;
    private TextView mTxtTite, mTxtDescription;
    private boolean status = false;



    private MediaPlayer mediaPlayer;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));//

        mediaPlayer = new MediaPlayer();

        widgetsInitialization();
        getIntentData();
        btnPlayClick();
        btnPauseClick();

    }

    private void btnPauseClick() {
        mBtnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                mBtnPause.setVisibility(View.GONE);
                mBtnPlay.setVisibility(View.VISIBLE);
            }
        });
    }

    private void btnPlayClick() {
        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                mBtnPlay.setVisibility(View.GONE);
                mBtnPause.setVisibility(View.VISIBLE);
            }
        });
    }

    private void widgetsInitialization() {

        mTxtTite = findViewById(R.id.txt_med_title);
        mTxtDescription = findViewById(R.id.txt_med_description);

        bgImage = findViewById(R.id.bg);

        mBtnPlay = findViewById(R.id.btn_play);
        mBtnPause = findViewById(R.id.btn_pause);


    }

    private void getIntentData() {

        title = getIntent().getStringExtra("title");
        description = getIntent().getStringExtra("description");
        audio = getIntent().getStringExtra("audio");
        image = getIntent().getStringExtra("image");

        mTxtTite.setText(title);
        mTxtDescription.setText(description);
        Picasso.get().load(image).placeholder(R.drawable.push_bg).into(bgImage);

        if (audio != null){
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(MeditationActivity.this, Uri.parse(audio));
                mediaPlayer.prepare();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    public void BackClick(View view) {
        onBackPressed();
        //mediaPlayer.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //mediaPlayer.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //mediaPlayer.stop();
    }
}

