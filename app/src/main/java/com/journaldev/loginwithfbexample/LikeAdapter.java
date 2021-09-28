package com.journaldev.loginwithfbexample;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.MyViewHolders> {
    private ArrayList<FBLike> fbLikes;

    public LikeAdapter(ArrayList<FBLike> fbLikes) {
        this.fbLikes = fbLikes;
    }

    @NonNull
    @Override
    public LikeAdapter.MyViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_likes_items,parent,false);
        return new LikeAdapter.MyViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LikeAdapter.MyViewHolders holder, int position) {
        holder.textViewCreated.setText(fbLikes.get(position).getCreated_time());
        holder.textViewName.setText(fbLikes.get(position).getName());
        holder.textViewId.setText(fbLikes.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return fbLikes.size();
    }
    public void clear(){
        fbLikes.clear();
        notifyDataSetChanged();
    }
    public static class MyViewHolders extends RecyclerView.ViewHolder{
        TextView textViewCreated;
        TextView textViewName;
        TextView textViewId;

        public MyViewHolders(@NonNull View itemView) {
            super(itemView);
            textViewCreated=itemView.findViewById(R.id.textViewCreated);
            textViewName=itemView.findViewById(R.id.textViewName);
            textViewId=itemView.findViewById(R.id.textViewId);
            textViewName.setMovementMethod(new ScrollingMovementMethod());


        }
    }
}
