package com.push.sweateliteathletics.softrasol.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.push.sweateliteathletics.softrasol.Models.FavoritesModel;
import com.push.sweateliteathletics.softrasol.MyActivities.BreathworkActivity;
import com.push.sweateliteathletics.softrasol.MyActivities.ChallengesActivity;
import com.push.sweateliteathletics.softrasol.MyActivities.ExerciseActivity;
import com.push.sweateliteathletics.softrasol.MyActivities.MeditationCategoryActivity;
import com.push.sweateliteathletics.softrasol.MyActivities.MindsetActivity;
import com.push.sweateliteathletics.softrasol.MyActivities.MobilityActivity;
import com.push.sweateliteathletics.softrasol.MyActivities.MotivationActivity;
import com.push.sweateliteathletics.softrasol.MyActivities.NutritionActivity;
import com.push.sweateliteathletics.softrasol.R;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private Context context;
    private List<FavoritesModel> list;

    public FavoritesAdapter(Context context, List<FavoritesModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.push_fav_list_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final FavoritesModel model = list.get(position);
        holder.mTxtFav.setText(model.getName());

        getIconsFromDrawable(holder,model);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoHomeProfiles(model);
            }
        });


    }

    private void gotoHomeProfiles(FavoritesModel model) {

        if (model.getDocument_name().equalsIgnoreCase("breath_work")){
            context.startActivity(new Intent(context, BreathworkActivity.class));
        }

        if (model.getDocument_name().equalsIgnoreCase("nutrition")){
            context.startActivity(new Intent(context, NutritionActivity.class));
        }

        if (model.getDocument_name().equalsIgnoreCase("challenges")){
            context.startActivity(new Intent(context, ChallengesActivity.class));
        }

        if (model.getDocument_name().equalsIgnoreCase("exercise")){
            context.startActivity(new Intent(context, ExerciseActivity.class));
        }

        if (model.getDocument_name().equalsIgnoreCase("mobility")){
            context.startActivity(new Intent(context, MobilityActivity.class));
        }

        if (model.getDocument_name().equalsIgnoreCase("motivation")){
            context.startActivity(new Intent(context, MotivationActivity.class));
        }

        if (model.getCollection_name().equalsIgnoreCase("mindset")){
            context.startActivity(new Intent(context, MindsetActivity.class));
        }

        if (model.getCollection_name().equalsIgnoreCase("meditation")){
            context.startActivity(new Intent(context, MeditationCategoryActivity.class));
        }

    }

    private void getIconsFromDrawable(ViewHolder holder, FavoritesModel model) {

        if (model.getDocument_name().equalsIgnoreCase("breath_work")){
            holder.mImgFav.setImageResource(R.drawable.ic_breathwork);
        }

        if (model.getDocument_name().equalsIgnoreCase("nutrition")){
            holder.mImgFav.setImageResource(R.drawable.ic_nutrition);
        }

        if (model.getDocument_name().equalsIgnoreCase("challenges")){
            holder.mImgFav.setImageResource(R.drawable.ic_challenges);
        }

        if (model.getDocument_name().equalsIgnoreCase("exercise")){
            holder.mImgFav.setImageResource(R.drawable.ic_workout);
        }

        if (model.getDocument_name().equalsIgnoreCase("mobility")){
            holder.mImgFav.setImageResource(R.drawable.ic_mobility);
        }

        if (model.getDocument_name().equalsIgnoreCase("motivation")){
            holder.mImgFav.setImageResource(R.drawable.ic_motivation);
        }

        if (model.getCollection_name().equalsIgnoreCase("mindset")){
            holder.mImgFav.setImageResource(R.drawable.ic_mindset);
        }

        if (model.getCollection_name().equalsIgnoreCase("meditation")){
            holder.mImgFav.setImageResource(R.drawable.ic_meditation);
        }

        if (model.getDocument_name().equalsIgnoreCase("")){
            holder.mImgFav.setImageResource(R.drawable.ic_no_data);
        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView mImgFav;
        private TextView mTxtFav;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImgFav = itemView.findViewById(R.id.img_favorite);
            mTxtFav = itemView.findViewById(R.id.txt_favorite);
        }
    }
}
