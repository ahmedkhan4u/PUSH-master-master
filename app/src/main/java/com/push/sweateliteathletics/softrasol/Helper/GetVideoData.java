package com.push.sweateliteathletics.softrasol.Helper;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.push.sweateliteathletics.softrasol.Models.PointsModel;

import java.util.List;

public class GetVideoData {

    public static String videoUrl;
    public static String title;
    public static List<PointsModel> list;
    public static boolean video_status = false;
    public static boolean points_status = false;

    public static boolean getVideoData(String document_name){

        CollectionReference collectionReference = FirebaseFirestore.getInstance()
                .collection("videos");

        DocumentReference documentReference = collectionReference.document(document_name);

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){
                        videoUrl = task.getResult().getString("video_url");
                        title = task.getResult().getString("title");
                        video_status = true;
                    }
                }
            }
        });

        return video_status;
    }

    public static boolean getPointsData(String document_name){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("videos").document(document_name)
                .collection("points_data").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot snapshot : task.getResult()){
                        PointsModel model = snapshot.toObject(PointsModel.class);
                        list.add(model);
                    }

                    points_status = true;
                }
            }
        });

        return points_status;
    }

}
