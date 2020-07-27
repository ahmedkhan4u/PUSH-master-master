package com.push.sweateliteathletics.softrasol.MyActivities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.push.sweateliteathletics.softrasol.QuoreMain;
import com.push.sweateliteathletics.softrasol.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class EditNameActivity extends AppCompatActivity {

    private EditText mTxtName;
    private CollectionReference collectionReference;
    private DocumentReference documentReference;
    private FirebaseAuth mAuth;
    private ImageView mImgBackground;
    private String name;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));//


        mAuth = FirebaseAuth.getInstance();
        mTxtName = findViewById(R.id.edt_person_name_edit);
        mImgBackground = findViewById(R.id.bg_image);

        mImgBackground.setVisibility(View.GONE);

        collectionReference = FirebaseFirestore.getInstance().collection("users");
        documentReference = collectionReference.document(mAuth.getCurrentUser().getUid());

        getNameFromFirebaseFirestore();
        getBackgroundImage();

    }

    private void getBackgroundImage() {

        CollectionReference collectionReference = FirebaseFirestore.getInstance()
                .collection("name_bg_image");

        DocumentReference documentReference = collectionReference
                .document("name_bg_image");

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){

                        String image = task.getResult().getString("image_url");
                            Picasso.get().load(image)
                                    .placeholder(R.drawable.push_bg).into(mImgBackground);

                    }
                }
            }
        });

    }

    private void getNameFromFirebaseFirestore() {

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        name = task.getResult().getString("name");
                        mTxtName.setText(name);
                    }
                }
            }
        });

    }

    public void ButtonEditClick(View view) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(EditNameActivity.this);
        builder1.setMessage("Are you sure you want to edit name");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        name = mTxtName.getText().toString().trim();

                        Map map = new HashMap();
                        map.put("name", name);

                        documentReference.update(map).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(EditNameActivity.this,
                                            "Name Edited", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), QuoreMain.class));
                                    finish();
                                }else {
                                    Toast.makeText(EditNameActivity.this,
                                            task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}
