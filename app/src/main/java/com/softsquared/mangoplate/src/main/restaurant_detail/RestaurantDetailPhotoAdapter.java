package com.softsquared.mangoplate.src.main.restaurant_detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.main.restaurant_detail.models.RestaurantDetailPhotoInfo;

import java.util.ArrayList;

public class RestaurantDetailPhotoAdapter extends RecyclerView.Adapter<RestaurantDetailPhotoAdapter.RestaurantDetailPhotoViewHolder> {
    private ArrayList<RestaurantDetailPhotoInfo> photoInfoArrayList  = new ArrayList<>();

    @NonNull
    @Override
    public RestaurantDetailPhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo_big, parent, false);
        return new RestaurantDetailPhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantDetailPhotoViewHolder holder, int position) {
        holder.bind(photoInfoArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return Math.min(photoInfoArrayList.size(), 5);
    }

    public void add(RestaurantDetailPhotoInfo info) {
        photoInfoArrayList.add(info);
    }

    class RestaurantDetailPhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;

        RestaurantDetailPhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.photo_iv_photo_big);
        }

        void bind(RestaurantDetailPhotoInfo restaurantDetailPhotoInfo) {
            Glide.with(itemView.getContext())
                    .load(restaurantDetailPhotoInfo.getImageUrl())
                    .into(ivPhoto);
        }
    }
}
