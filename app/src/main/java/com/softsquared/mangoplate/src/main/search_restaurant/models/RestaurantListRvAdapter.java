package com.softsquared.mangoplate.src.main.search_restaurant.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.ApplicationClass;
import com.softsquared.mangoplate.src.main.MainActivity;

import java.text.NumberFormat;
import java.util.ArrayList;

public class RestaurantListRvAdapter extends RecyclerView.Adapter<RestaurantListRvAdapter.RestaurantViewHolder> {
    private ArrayList<RestaurantInfo> restaurantInfoArrayList = new ArrayList<>();

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurant_list, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        holder.bind(restaurantInfoArrayList.get(position));
    }

    public void addRestaurantInfo(RestaurantInfo restaurantInfo) {
        restaurantInfoArrayList.add(restaurantInfo);
    }

    @Override
    public int getItemCount() { return restaurantInfoArrayList.size(); }

    class RestaurantViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        ImageView ivStar;
        TextView tvName;
        TextView tvAreaDistance;
        TextView tvViewCount;
        TextView tvReviewCount;
        TextView tvScore;

        RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.restaurant_list_iv_photo);
            ivStar = itemView.findViewById(R.id.restaurant_list_iv_star);
            tvName = itemView.findViewById(R.id.restaurant_list_tv_name);
            tvAreaDistance = itemView.findViewById(R.id.restaurant_list_tv_area_distance);
            tvViewCount = itemView.findViewById(R.id.restaurant_list_tv_view_count);
            tvReviewCount = itemView.findViewById(R.id.restaurant_list_tv_review_count);
            tvScore = itemView.findViewById(R.id.restaurant_list_tv_score);
        }

        void bind(RestaurantInfo restaurantInfo) {
            Glide.with(itemView.getContext())
                    .load(restaurantInfo.getImageUrl())
                    .into(ivPhoto);

            ivStar.setImageResource(restaurantInfo.isWantToGo() ?
                    R.drawable.ic_star_orange : R.drawable.ic_star_white);

            tvName.setText(restaurantInfo.getName());

            String areaAndDistance = restaurantInfo.getArea() + " - " + restaurantInfo.getDistance();
            tvAreaDistance.setText(areaAndDistance);

            tvViewCount.setText(NumberFormat.getInstance().format(restaurantInfo.getViewCount()));
            tvReviewCount.setText(NumberFormat.getInstance().format(restaurantInfo.getReviewCount()));

            String score = Float.toString(restaurantInfo.getScore());
            tvScore.setText(score);
        }
    }
}
