package com.push.sweateliteathletics.softrasol.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.push.sweateliteathletics.softrasol.Models.MeditationModel;
import com.push.sweateliteathletics.softrasol.MyActivities.MeditationActivity;
import com.push.sweateliteathletics.softrasol.R;

import java.util.List;

public class MeditationAdapter extends RecyclerView.Adapter<MeditationAdapter.ViewHolder> {

    private Context context;
    private List<MeditationModel> list;
    public static String title, description, audio, image;

    public MeditationAdapter(Context context, List<MeditationModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.fit_body_items_list, parent, false);
        return new MeditationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final MeditationModel model = list.get(position);
        holder.mTxtTitle.setText(model.getCategory());
        holder.mTxtBody.setText(model.getTitle());


        if (model.getCategory().equalsIgnoreCase("wise")){
            holder.imageView.setImageResource(R.drawable.meditation_wise);
        }

        if (model.getCategory().equalsIgnoreCase("Happiness")){
            holder.imageView.setImageResource(R.drawable.meditation1);
        }

        if (model.getCategory().equalsIgnoreCase("health")){
            holder.imageView.setImageResource(R.drawable.meditation_happiness);
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title = model.getTitle();
                description = model.getDescription();
                audio = model.getAudio_url();
                image = model.getImage_url();

                Intent intent = new Intent(context, MeditationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("title", title);
//                intent.putExtra("description", description);
//                intent.putExtra("audio",audio);
//                intent.putExtra("image", image);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTxtTitle, mTxtBody;
        private ImageView imageView;
        public RelativeLayout mBackground;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTxtTitle = itemView.findViewById(R.id.txt_title_body_list);
            mTxtBody = itemView.findViewById(R.id.txt_subtitle_body_list);
            mBackground = itemView.findViewById(R.id.meditatoin_bg);
            imageView = itemView.findViewById(R.id.img_programs);
        }
    }
}
