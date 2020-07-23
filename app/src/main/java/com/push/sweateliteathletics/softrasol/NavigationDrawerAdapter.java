package com.push.sweateliteathletics.softrasol;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.Collections;
import java.util.List;

public class NavigationDrawerAdapter extends Adapter<NavigationDrawerAdapter.MyViewHolder> {
    private Context context;
    List<NavDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;

    class MyViewHolder extends ViewHolder {
        ImageView icon;
        FontTextView title;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.title = (FontTextView) itemView.findViewById(R.id.title);
            this.icon = (ImageView) itemView.findViewById(R.id.ivImage);
        }
    }

    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        this.data.remove(position);
        notifyItemRemoved(position);
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(this.inflater.inflate(R.layout.nav_drawer_row, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerItem current = (NavDrawerItem) this.data.get(position);
        holder.title.setText(current.getTitle());
        holder.icon.setImageResource(current.getIcon());
    }

    public int getItemCount() {
        return this.data.size();
    }
}
