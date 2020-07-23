package com.push.sweateliteathletics.softrasol.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.push.sweateliteathletics.softrasol.Models.ProgramsModel;
import com.push.sweateliteathletics.softrasol.R;

import java.util.List;

public class FitBodyProgramsAdapter extends RecyclerView.Adapter<FitBodyProgramsAdapter.ViewHolder> {

    private Context context;
    private List<ProgramsModel> list;

    public FitBodyProgramsAdapter(Context context, List<ProgramsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.fit_body_items_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final ProgramsModel model = list.get(position);
        holder.mTxtTitle.setText(model.getTitle());
        holder.mTxtSubTitle.setText(model.getSub_title());
        holder.mImgPrograms.setImageResource(model.getImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFitMindProgramsData(holder, model);
            }
        });

        holder.mBtnLock.setVisibility(View.VISIBLE);
    }

    private void setFitMindProgramsData(final ViewHolder holder, ProgramsModel model) {

        CollectionReference collectionReference = FirebaseFirestore.getInstance()
                .collection("fitbody");
        DocumentReference documentReference = collectionReference.document("programs");

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    if (task.getResult().exists()){

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                        builder1.setTitle(task.getResult().getString("title"));
                        builder1.setMessage(task.getResult().getString("description"));
                        builder1.setCancelable(true);

                        builder1.setPositiveButton(
                                "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });


                        AlertDialog alert11 = builder1.create();
                        alert11.show();

                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImgPrograms;
        private TextView mTxtTitle;
        private TextView mTxtSubTitle;
        private ImageButton mBtnLock;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTxtSubTitle = itemView.findViewById(R.id.txt_subtitle_body_list);
            mTxtTitle = itemView.findViewById(R.id.txt_title_body_list);
            mBtnLock = itemView.findViewById(R.id.btn_lock);
            mImgPrograms = itemView.findViewById(R.id.img_programs);
        }
    }
}