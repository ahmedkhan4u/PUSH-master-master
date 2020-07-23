package com.push.sweateliteathletics.softrasol.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.push.sweateliteathletics.softrasol.Models.PointsModel;
import com.push.sweateliteathletics.softrasol.MyActivities.FitMindProgramsActivity;
import com.push.sweateliteathletics.softrasol.R;

import java.util.List;

public class FitBodyAdapter extends RecyclerView.Adapter<FitBodyAdapter.ViewHolder> {

    private Context context;
    private List<PointsModel> list;

    public FitBodyAdapter(Context context, List<PointsModel> list) {
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PointsModel model = list.get(position);
        holder.mTxtTitle.setText(model.getTitle()+" "+(position+1));
        holder.mTxtSubTitle.setText(model.getSub_title());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("Coming soon,");
                builder1.setMessage("This program will be released soon.");
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
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTxtTitle;
        private TextView mTxtSubTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTxtSubTitle = itemView.findViewById(R.id.txt_subtitle_body_list);
            mTxtTitle = itemView.findViewById(R.id.txt_title_body_list);
        }
    }
}
