package com.push.sweateliteathletics.softrasol;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.otaliastudios.cameraview.VideoResult;
import com.otaliastudios.cameraview.size.AspectRatio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import static java.lang.System.in;

public class VideoPreviewActivity extends AppCompatActivity {

    private VideoView videoView;

    private static VideoResult videoResult;

    private Button btnSaveVideo;


    public static void setVideoResult(@Nullable VideoResult result) {
        videoResult = result;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_preview);

        final VideoResult result = videoResult;
        if (result == null) {
            finish();
            return;
        }

        btnSaveVideo = findViewById(R.id.save_video);

        videoView = findViewById(R.id.video);
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playVideo();
            }
        });

        AspectRatio ratio = AspectRatio.of(result.getSize());
        MediaController controller = new MediaController(this);
        controller.setAnchorView(videoView);
        controller.setMediaPlayer(videoView);
        videoView.setMediaController(controller);
        videoView.setVideoURI(Uri.fromFile(result.getFile()));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                ViewGroup.LayoutParams lp = videoView.getLayoutParams();
                float videoWidth = mp.getVideoWidth();
                float videoHeight = mp.getVideoHeight();
                float viewWidth = videoView.getWidth();
                lp.height = (int) (viewWidth * (videoHeight / videoWidth));
                videoView.setLayoutParams(lp);
                playVideo();
                saveVideoToInternalStorage(videoResult.getFile().toString());

                btnSaveVideo.setVisibility(View.GONE);

                if (result.isSnapshot()) {
                    // Log the real size for debugging reason.
                    Log.e("VideoPreview", "The video full size is " + videoWidth + "x" + videoHeight);
                }
            }
        });

        //Click this button to save video
        btnSaveVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Calling 1st Method but not working
                //SaveVideo(videoResult.getFile().toString());

                //2nd method calling but still not working
                //saveVideoToInternalStorage(videoResult.getFile().toString());
            }
        });


    }

    void playVideo() {
        if (!videoView.isPlaying()) {
            videoView.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!isChangingConfigurations()) {
            setVideoResult(null);
        }
    }




    //For saving Video...

    private void saveVideoToInternalStorage (String filePath) {

        File newfile;

        try {

            File currentFile = new File(filePath);
            String fileName = currentFile.getName();

            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File directory = cw.getDir("videoDir", Context.MODE_PRIVATE);


            newfile = new File(directory.getAbsolutePath(), fileName);

            if(currentFile.exists()){

                InputStream in = new FileInputStream(currentFile);
                OutputStream out = new FileOutputStream(newfile);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                scanner(filePath);
                in.close();
                out.close();
                Log.v("", "Video file saved successfully.");
                Toast.makeText(this, "Push video saved", Toast.LENGTH_SHORT).show();
                videoResult = null;


            }else{
                Log.v("", "Video saving failed. Source file missing.");
            }



        } catch (Exception e) {
            Log.d("Err ", "EXS " + e.getMessage());
            e.printStackTrace();
        }

    }

    private void scanner(String path) {

        MediaScannerConnection.scanFile(VideoPreviewActivity.this,
                new String[] { path }, null,
                new MediaScannerConnection.OnScanCompletedListener() {

                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("TAG", "Finished scanning " + path);
                    }
                });
    }

}
