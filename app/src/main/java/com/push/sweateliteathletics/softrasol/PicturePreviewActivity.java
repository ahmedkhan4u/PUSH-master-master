package com.push.sweateliteathletics.softrasol;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.otaliastudios.cameraview.BitmapCallback;
import com.otaliastudios.cameraview.PictureResult;
import com.otaliastudios.cameraview.size.AspectRatio;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class PicturePreviewActivity extends AppCompatActivity {

    private static PictureResult picture;
    private Button btnSaveImage;

    public static void setPictureResult(@Nullable PictureResult pictureResult) {
        picture = pictureResult;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_preview);

        final PictureResult result = picture;
        if (result == null) {
            finish();
            return;
        }

        final ImageView imageView = findViewById(R.id.image);
        btnSaveImage = findViewById(R.id.save_img);
        btnSaveImage.setVisibility(View.INVISIBLE);

        //final long delay = getIntent().getLongExtra("delay", 0);
        AspectRatio ratio = AspectRatio.of(result.getSize());
        //captureLatency.setTitleAndMessage("Approx. latency", delay + " milliseconds");
        //captureResolution.setTitleAndMessage("Resolution", result.getSize() + " (" + ratio + ")");
        //exifRotation.setTitleAndMessage("EXIF rotation", result.getRotation() + "");
        try {
            result.toBitmap(3000, 3000, new BitmapCallback() {
                @Override
                public void onBitmapReady(Bitmap bitmap) {
                    imageView.setImageBitmap(bitmap);
                    SaveImage(bitmap);
                }
            });
        } catch (UnsupportedOperationException e) {
            imageView.setImageDrawable(new ColorDrawable(Color.GREEN));
            Toast.makeText(this, "Can't preview this format: " + picture.getFormat(),
                    Toast.LENGTH_LONG).show();
        }



        btnSaveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    result.toBitmap(1000, 1000, new BitmapCallback() {
                        @Override
                        public void onBitmapReady(Bitmap bitmap) {
                            SaveImage(bitmap);
                        }
                    });
                } catch (UnsupportedOperationException e) {

                    Toast.makeText(PicturePreviewActivity.this, "Image not saved" + picture.getFormat(),
                            Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void SaveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(root + "/Push Images");
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ())
            file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            Toast.makeText(PicturePreviewActivity.this, "PUSH picture saved", Toast.LENGTH_LONG).show();
            scanner(file.getAbsolutePath());
            out.flush();
            out.close();
            picture = null;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void scanner(String path) {

        MediaScannerConnection.scanFile(PicturePreviewActivity.this,
                new String[] { path }, null,
                new MediaScannerConnection.OnScanCompletedListener() {

                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("TAG", "Finished scanning " + path);
                    }
                });
    }

}
