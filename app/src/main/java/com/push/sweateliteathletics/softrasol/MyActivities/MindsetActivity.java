package com.push.sweateliteathletics.softrasol.MyActivities;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.push.sweateliteathletics.softrasol.Adapters.MindsetAdapter;
import com.push.sweateliteathletics.softrasol.Models.PointsModel;
import com.push.sweateliteathletics.softrasol.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MindsetActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<PointsModel> list = new ArrayList<>();
    private TextView mTxtTitle, mTxtDescription;
    private Dialog dialog;
    private ImageView bgImage;

    private ImageView mBtnFavourite;
    CollectionReference collectionReference;
    DocumentReference documentReference;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mindset);
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));//

        collectionReference = FirebaseFirestore.getInstance().collection("users")
                .document(FirebaseAuth.getInstance().getUid()).collection("favorites");

        documentReference = collectionReference.document("mindset");
        mBtnFavourite = findViewById(R.id.btn_favorite);

        mTxtTitle = findViewById(R.id.txt_mindset_title);
        mTxtDescription = findViewById(R.id.txt_mindset_description);
        mRecyclerView = findViewById(R.id.recyclerview_mindset);
        bgImage = findViewById(R.id.bg);

        getPointsData();
        getData();
        checkIfAddedToFavoriteOrNot();
        btnFavouriteClick();

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
                                                map.put("collection_name", "mindset");
                                                map.put("document_name", "mindset");
                                                map.put("name", "Mindset");

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

    private void recyclerviewImplementation() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        MindsetAdapter adapter = new MindsetAdapter(getApplicationContext(), list);
        mRecyclerView.setAdapter(adapter);
    }

    public void BackClick(View view) {
        onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }


    private void getPointsData() {

        showProgressBar();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("mindset").document("mindset")
                .collection("points_data").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().size()>0){
                        for (QueryDocumentSnapshot snapshot : task.getResult()){
                            PointsModel model = snapshot.toObject(PointsModel.class);
                            list.add(model);
                        }

                        dialog.cancel();
                        recyclerviewImplementation();
                    }
                    else {
                        dialog.cancel();
                    }
                }else {
                    Toast.makeText(MindsetActivity.this,
                            task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            }
        });

    }


    private void getData() {


        CollectionReference collectionReference = FirebaseFirestore.getInstance()
                .collection("mindset");

        DocumentReference documentReference = collectionReference.document("mindset");

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){

                        mTxtTitle.setText(task.getResult().getString("title"));
                        mTxtDescription.setText(task.getResult().getString("description"));
                        String image = task.getResult().getString("image_url");
                        Picasso.get().load(image).into(bgImage);

                    }
                }else {
                    Toast.makeText(MindsetActivity.this,
                            task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public void showProgressBar(){
        dialog = new Dialog(MindsetActivity.this);
        dialog.setContentView(R.layout.progress_dialog);
        ProgressBar progressBar = dialog.findViewById(R.id.progress_bar);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressBar.setProgress(100);
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}
