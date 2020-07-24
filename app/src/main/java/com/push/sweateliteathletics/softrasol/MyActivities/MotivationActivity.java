package com.push.sweateliteathletics.softrasol.MyActivities;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.push.sweateliteathletics.softrasol.Adapters.PointsAdapter;
import com.push.sweateliteathletics.softrasol.Models.PointsModel;
import com.push.sweateliteathletics.softrasol.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MotivationActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<PointsModel> list = new ArrayList<>();
    private VideoView videoView;
    private TextView mTxtTitle;
    private Dialog dialog;
    private ImageView mImgThumnail, mLoader, mPlay;



    private ImageView mBtnFavourite;
    CollectionReference collectionReference;
    DocumentReference documentReference;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motivation);

        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));//

        collectionReference = FirebaseFirestore.getInstance().collection("users")
                .document(FirebaseAuth.getInstance().getUid()).collection("favorites");

        documentReference = collectionReference.document("motivation");
        mBtnFavourite = findViewById(R.id.btn_favorite);

        mRecyclerView = findViewById(R.id.recyclerview_points_data);
        videoView = findViewById(R.id.video_view);
        mTxtTitle = findViewById(R.id.points_title);
        mImgThumnail = findViewById(R.id.img_thumbnail);
        mLoader = findViewById(R.id.loader);
        mPlay = findViewById(R.id.btn_play);

        recyclerViewImplementation();
        getVideoData();
        getPointsData();

        checkIfAddedToFavoriteOrNot();
        btnFavouriteClick();
        btnPlayClick();

    }
    private void btnPlayClick() {

        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.start();
                mLoader.setVisibility(View.VISIBLE);
                Glide.with(getApplicationContext())
                        .load(R.drawable.loading_gif)
                        .into(mLoader);
                mPlay.setVisibility(View.GONE);
                mImgThumnail.setVisibility(View.GONE);
                mLoader.setVisibility(View.GONE);
            }
        });

    }

    private void heartAnimation() {
        Animation anim = AnimationUtils.loadAnimation
                (getApplicationContext(), R.anim.heart_animation);
        mBtnFavourite.startAnimation(anim);
    }
    private void checkIfAddedToFavoriteOrNot() {

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        mBtnFavourite.setBackgroundResource(R.drawable.ic_favorite_red);
                        heartAnimation();
                    }
                }
            }
        });

    }


    private void btnFavouriteClick() {

        mBtnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            if (task.getResult().size()>2){
                                Toast.makeText(getApplicationContext(),
                                        getString(R.string.favorite_limit), Toast.LENGTH_LONG).show();

                                    documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                mBtnFavourite.setBackgroundResource(R.drawable.ic_favorite);
                                                heartAnimation();

                                            }
                                        }
                                    });


                                return;
                            }else {
                                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()){
                                            if (task.getResult().exists()){
                                                documentReference.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()){
                                                            mBtnFavourite.setBackgroundResource(R.drawable.ic_favorite);
                                                            heartAnimation();
                                                            Toast.makeText(getApplicationContext(), "Removed from favorites", Toast.LENGTH_SHORT).show();
                                                        }else {
                                                            Toast.makeText(getApplicationContext(),
                                                                    task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                            }else {

                                                Map map = new HashMap();
                                                map.put("collection_name", "videos");
                                                map.put("document_name", "motivation");
                                                map.put("name", "Motivation");

                                                documentReference.set(map).addOnCompleteListener(new OnCompleteListener() {
                                                    @Override
                                                    public void onComplete(@NonNull Task task) {
                                                        if (task.isSuccessful()){
                                                            mBtnFavourite.setBackgroundResource(R.drawable.ic_favorite_red);
                                                            heartAnimation();
                                                            Toast.makeText(getApplicationContext(), "Added to favourites", Toast.LENGTH_SHORT).show();
                                                        }else {
                                                            Toast.makeText(getApplicationContext(),
                                                                    task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    }
                                });
                            }
                        }
                    }
                });

            }
        });

    }

    private void recyclerViewImplementation() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        PointsAdapter adapter = new PointsAdapter(getApplicationContext(), list);
        mRecyclerView.setAdapter(adapter);


    }

    public void BackClick(View view) {
        onBackPressed();
    }

    private void getPointsData() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("videos").document("motivation")
                .collection("points_data").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot snapshot : task.getResult()){
                        PointsModel model = snapshot.toObject(PointsModel.class);
                        list.add(model);
                    }

                    recyclerViewImplementation();
                }
            }
        });

    }


    private void getVideoData() {

        showProgressBar();
        CollectionReference collectionReference = FirebaseFirestore.getInstance()
                .collection("videos");

        DocumentReference documentReference = collectionReference.document("motivation");

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){

                        if (task.getResult().contains("thumbnailUrl")){
                            String thumbnailUrl = task.getResult().getString("thumbnailUrl");
                            Glide.with(getApplicationContext())
                                    .load(thumbnailUrl)
                                    .into(mImgThumnail);
                        }

                        //ghjgjgjgjhgjgjhghjghghj

                        mTxtTitle.setText(task.getResult().getString("title"));

                        Uri uri = Uri.parse(task.getResult().getString("video_url"));
                        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mLoader.setVisibility(View.GONE);
                            }
                        });
                        videoView.setVideoURI(uri);
                        MediaController mediaController = new MediaController(MotivationActivity.this);
                        videoView.setMediaController(mediaController);

                        mLoader.setVisibility(View.GONE);

                        dialog.cancel();

                    }else {
                        dialog.cancel();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            }
        });
    }

    public void showProgressBar(){


        dialog = new Dialog(MotivationActivity.this);
        dialog.setContentView(R.layout.progress_dialog);
        ProgressBar progressBar = dialog.findViewById(R.id.progress_bar);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressBar.setProgress(100);
        dialog.setCancelable(false);
        dialog.show();
    }

}
