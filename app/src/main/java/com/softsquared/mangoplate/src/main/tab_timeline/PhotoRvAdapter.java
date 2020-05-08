package com.softsquared.mangoplate.src.main.tab_timeline;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softsquared.mangoplate.R;

import java.util.ArrayList;

class PhotoRvAdapter extends RecyclerView.Adapter<PhotoRvAdapter.PhotoViewHolder> {
    private ArrayList<String> photoList = new ArrayList<>();

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo_small, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        holder.bind(photoList.get(position));
    }

    @Override
    public int getItemCount() { return photoList.size(); }

    public void add(String imageUrl) {
        photoList.add(imageUrl);
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;

        PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.photo_iv_photo_small);
        }

        public void bind(String imageInfo) {
            Glide.with(itemView.getContext())
                    .load(imageInfo)
                    .into(ivPhoto);
        }
    }
}
