package com.push.sweateliteathletics.softrasol.MyActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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

public class NameSettingActivity extends AppCompatActivity {

    private EditText mEdtName;

    private ProgressDialog progressDialog;
    private ImageView mImgBackground;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_setting);
        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));//

        mImgBackground = findViewById(R.id.bg_image);
        progressDialog = new ProgressDialog(this);
        mEdtName = findViewById(R.id.edt_person_name);


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


    public void ButtonNextClick(View view) {

        validateField();

    }

    private void validateField() {

        String name = mEdtName.getText().toString().trim();

        if (name.isEmpty()){
            mEdtName.setError("Required");
            mEdtName.requestFocus();
            return;
        }

        saveNameToFirebaseFirestore(name);
    }

    private void saveNameToFirebaseFirestore(String name) {

        progressDialog.setTitle("Wait....");
        progressDialog.show();
        progressDialog.setCancelable(false);
        CollectionReference collectionReference =
                FirebaseFirestore.getInstance().collection("users");

        DocumentReference documentReference = collectionReference
                .document(FirebaseAuth.getInstance().getUid());

        Map map = new HashMap();
        map.put("name", name);
        map.put("uid", FirebaseAuth.getInstance().getUid());

        documentReference.set(map).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()){
                    progressDialog.cancel();
                    startActivity(new Intent(getApplicationContext(), QuoreMain.class));
                    finish();
                }else {
                    progressDialog.cancel();
                    Toast.makeText(NameSettingActivity.this,
                            task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
