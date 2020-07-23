package com.push.sweateliteathletics.softrasol.Helper;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UploadGoalData {

    public static boolean uploadGoalData(String documentName, String goal, String date){

        final boolean[] status = {false};

        CollectionReference collectionReference = FirebaseFirestore.getInstance()
                .collection("users").document(FirebaseAuth.getInstance().getUid())
                .collection("goals");

        DocumentReference documentReference = collectionReference.document(documentName);

        Map map = new HashMap();
        map.put("goal", goal);
        map.put("date", date);

        documentReference.set(map).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()){
                    status[0] = true;
                }else {
                    status[0] = false;
                }
            }
        });

        return status[0];
    }

}
