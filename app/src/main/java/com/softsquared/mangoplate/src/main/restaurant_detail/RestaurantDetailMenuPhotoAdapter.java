package com.softsquared.mangoplate.src.main.restaurant_detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.main.restaurant_detail.models.RestaurantDetailMenuInfo;

import java.util.ArrayList;

public class RestaurantDetailMenuPhotoAdapter extends RecyclerView.Adapter<RestaurantDetailMenuPhotoAdapter.RestaurantDetailMenuViewHolder> {
    private ArrayList<RestaurantDetailMenuInfo> menuInfoArrayList = new ArrayList<>();

    @NonNull
    @Override
    public RestaurantDetailMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo_small, parent, false);
        return new RestaurantDetailMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantDetailMenuViewHolder holder, int position) {
        holder.bind(menuInfoArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return menuInfoArrayList.size();
    }

    class RestaurantDetailMenuViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhotoSmall;

        RestaurantDetailMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhotoSmall = itemView.findViewById(R.id.photo_iv_photo_small);
        }

        void bind(RestaurantDetailMenuInfo restaurantDetailMenuInfo) {
            // TODO: There is no photo of menu in API 4-3.
            /*
            Glide.with(itemView.getContext())
                    .load(restaurantDetailMenuInfo.getImage())
                .into(ivPhotoSmall);
             */
        }
    }
}
