package com.push.sweateliteathletics.softrasol.MyActivities;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
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
import com.push.sweateliteathletics.softrasol.Adapters.MeditationAdapter;
import com.push.sweateliteathletics.softrasol.Models.MeditationModel;
import com.push.sweateliteathletics.softrasol.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeditationCategoryActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<MeditationModel> list = new ArrayList<>();
    private Dialog dialog;

    private ImageButton mBtnFavourite;
    CollectionReference collectionReference;
    DocumentReference documentReference;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation_category);



        collectionReference = FirebaseFirestore.getInstance().collection("users")
                .document(FirebaseAuth.getInstance().getUid()).collection("favorites");

        documentReference = collectionReference.document("meditation");
        mBtnFavourite = findViewById(R.id.btn_favorite);

        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));//

        mRecyclerView = findViewById(R.id.recyclerview_meditation);

        getMeditationDataFromFirebaseFirestore();
        checkIfAddedToFavoriteOrNot();
        btnFavouriteClick();

    }


    private void checkIfAddedToFavoriteOrNot() {

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        mBtnFavourite.setBackgroundResource(R.drawable.ic_favorite_red);
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
                                                            Toast.makeText(getApplicationContext(), "Removed from favorites", Toast.LENGTH_SHORT).show();
                                                        }else {
                                                            Toast.makeText(getApplicationContext(),
                                                                    task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                            }else {

                                                Map map = new HashMap();
                                                map.put("collection_name", "meditation");
                                                map.put("document_name", "meditation");
                                                map.put("name", "Meditation");

                                                documentReference.set(map).addOnCompleteListener(new OnCompleteListener() {
                                                    @Override
                                                    public void onComplete(@NonNull Task task) {
                                                        if (task.isSuccessful()){
                                                            mBtnFavourite.setBackgroundResource(R.drawable.ic_favorite_red);
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

    private void getMeditationDataFromFirebaseFirestore() {
        showProgressBar();

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        db.collection("meditation").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){

                    for (QueryDocumentSnapshot snapshot : task.getResult()){
                            MeditationModel model = snapshot.toObject(MeditationModel.class);
                            list.add(model);

                        }

                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        MeditationAdapter adapter = new MeditationAdapter(MeditationCategoryActivity.this,
                                list);

                        mRecyclerView.setAdapter(adapter);

                        dialog.cancel();

                        if (list.isEmpty()){
                            Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        dialog.cancel();
                    Toast.makeText(MeditationCategoryActivity.this,
                            task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
            }
        });

    }
    public void showProgressBar(){
        dialog = new Dialog(MeditationCategoryActivity.this);
        dialog.setContentView(R.layout.progress_dialog);
        ProgressBar progressBar = dialog.findViewById(R.id.progress_bar);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressBar.setProgress(100);
        dialog.setCancelable(false);
        dialog.show();
    }

    public void BackClick(View view) {
        onBackPressed();
    }
}
