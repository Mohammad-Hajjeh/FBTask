package com.journaldev.loginwithfbexample;


import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolders> {
    private ArrayList<FBPost> fbPosts;

    public PostAdapter(ArrayList<FBPost> fbPosts) {
        this.fbPosts = fbPosts;
    }

    @NonNull
    @Override
    public MyViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_items,parent,false);
        return new MyViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolders holder, int position) {
        holder.textViewCreated.setText(fbPosts.get(position).getCreated_time());
        holder.textViewMessage.setText(fbPosts.get(position).getMessage());
        holder.textViewId.setText(fbPosts.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return fbPosts.size();
    }
    public void clear(){
        fbPosts.clear();
        notifyDataSetChanged();
    }
    public static class MyViewHolders extends RecyclerView.ViewHolder{
        TextView textViewCreated;
        TextView textViewMessage;
        TextView textViewId;

        public MyViewHolders(@NonNull View itemView) {
            super(itemView);
            textViewCreated=itemView.findViewById(R.id.textViewCreated);
            textViewMessage=itemView.findViewById(R.id.textViewMessage);
            textViewId=itemView.findViewById(R.id.textViewId);
            textViewMessage.setMovementMethod(new ScrollingMovementMethod());


        }
    }
}
