package com.push.sweateliteathletics.softrasol.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.push.sweateliteathletics.softrasol.Models.PointsModel;
import com.push.sweateliteathletics.softrasol.R;

import java.util.List;

public class MindsetAdapter extends RecyclerView.Adapter<MindsetAdapter.ViewHolder> {

    private Context context;
    private List<PointsModel> list;

    public MindsetAdapter(Context context, List<PointsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.points_item_list_light, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PointsModel model = list.get(position);
        holder.mTxtTitle.setText(model.getTitle());
        holder.mTxtBody.setText(model.getSub_title());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTxtTitle, mTxtBody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtTitle = itemView.findViewById(R.id.points_title);
            mTxtBody = itemView.findViewById(R.id.points_body);
        }
    }
}