package com.push.sweateliteathletics.softrasol.MyActivities;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.push.sweateliteathletics.softrasol.Adapters.MeditationAdapter;
import com.push.sweateliteathletics.softrasol.Helper.MediaPlayerHelper;
import com.push.sweateliteathletics.softrasol.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class MeditationActivity extends AppCompatActivity {

    private String title, description, audio, image;
    private ImageView mBtnPlay, bgImage, mBtnPause;
    private TextView mTxtTite, mTxtDescription;
    private boolean status = false;

    private Dialog dialog;

    //private MediaPlayer mediaPlayer;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));//

        //mediaPlayer = new MediaPlayer();

        widgetsInitialization();
        getIntentData();
        btnPlayClick();
        btnPauseClick();

        if (MediaPlayerHelper.mediaPlayer.isPlaying()){
            MediaPlayerHelper.mediaPlayer.stop();
            MediaPlayerHelper.mediaPlayer.reset();

            MediaPlayerHelper.mediaPlayer = new MediaPlayer();
        }

        showProgressBar();


        new Thread(new Runnable() {
            @Override
            public void run() {
                if (MeditationAdapter.audio != null){
                    try {
                        MediaPlayerHelper.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        MediaPlayerHelper.mediaPlayer = new MediaPlayer();
                        MediaPlayerHelper.mediaPlayer.setDataSource(MeditationActivity.this, Uri.parse(MeditationAdapter.audio));
                        MediaPlayerHelper.mediaPlayer.prepare();
                        dialog.cancel();
                    } catch (IOException e) {
                        e.printStackTrace();
                        dialog.cancel();
                    }

                }
            }
        }).start();

    }

    private void btnPauseClick() {
        mBtnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MediaPlayerHelper.mediaPlayer.pause();
                    mBtnPause.setVisibility(View.GONE);
                    mBtnPlay.setVisibility(View.VISIBLE);
                }catch (Exception ex){

                }
            }
        });
    }

    private void btnPlayClick() {
        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayerHelper.mediaPlayer.start();
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

//        title = getIntent().getStringExtra("title");
//        description = getIntent().getStringExtra("description");
//        audio = getIntent().getStringExtra("audio");
//        image = getIntent().getStringExtra("image");


        mTxtTite.setText(MeditationAdapter.title);
        mTxtDescription.setText(MeditationAdapter.description);

        Picasso.get().load(MeditationAdapter.image).placeholder(R.drawable.push_bg).into(bgImage);
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

    public void showProgressBar(){

        dialog = new Dialog(MeditationActivity.this);
        dialog.setContentView(R.layout.progress_dialog);
        ProgressBar progressBar = dialog.findViewById(R.id.progress_bar);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressBar.setProgress(100);
        dialog.setCancelable(false);
        dialog.show();
    }
}

