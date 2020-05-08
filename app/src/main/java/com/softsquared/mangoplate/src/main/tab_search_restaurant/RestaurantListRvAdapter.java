package com.softsquared.mangoplate.src.main.tab_search_restaurant;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.main.restaurant_detail.RestaurantDetailActivity;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.interfaces.SearchRestaurantFragmentView;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.models.RestaurantInfo;

import java.util.ArrayList;

public class RestaurantListRvAdapter extends RecyclerView.Adapter<RestaurantListRvAdapter.RestaurantViewHolder> {
    private ArrayList<RestaurantInfo> restaurantInfoArrayList = new ArrayList<>();
    private SearchRestaurantFragmentView searchRestaurantFragmentView;

    public RestaurantListRvAdapter(SearchRestaurantFragmentView searchRestaurantFragmentView) {
        this.searchRestaurantFragmentView = searchRestaurantFragmentView;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurant_list, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        holder.bind(searchRestaurantFragmentView, restaurantInfoArrayList.get(position));
    }

    public void add(RestaurantInfo restaurantInfo) {
        restaurantInfoArrayList.add(restaurantInfo);
    }

    @Override
    public int getItemCount() { return restaurantInfoArrayList.size(); }

    void clear() { restaurantInfoArrayList.clear(); }

    class RestaurantViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout clWholeScreen;
        ImageView ivPhoto;
        ImageView ivStar;
        TextView tvName;
        TextView tvAreaDistance;
        TextView tvViewCount;
        TextView tvReviewCount;
        TextView tvScore;
        boolean isWish;

        RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            clWholeScreen = itemView.findViewById(R.id.restaurant_list_const_layout_whole_screen);
            ivPhoto = itemView.findViewById(R.id.restaurant_list_iv_photo);
            ivStar = itemView.findViewById(R.id.restaurant_list_iv_star);
            tvName = itemView.findViewById(R.id.restaurant_list_tv_name);
            tvAreaDistance = itemView.findViewById(R.id.restaurant_list_tv_area_distance);
            tvViewCount = itemView.findViewById(R.id.restaurant_list_tv_view_count);
            tvReviewCount = itemView.findViewById(R.id.restaurant_list_tv_review_count);
            tvScore = itemView.findViewById(R.id.restaurant_list_tv_score);
        }

        void bind(SearchRestaurantFragmentView searchRestaurantFragmentView, RestaurantInfo restaurantInfo) {
            clWholeScreen.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), RestaurantDetailActivity.class);
                intent.putExtra("restaurantId", restaurantInfo.getRestaurantId());
                itemView.getContext().startActivity(intent);
            });

            Glide.with(itemView.getContext())
                    .load(restaurantInfo.getImg())
                    .into(ivPhoto);

            isWish = restaurantInfo.getStar().equals("YES");
            ivStar.setImageResource(isWish ?
                    R.drawable.ic_star_filled_orange_and_white_border : R.drawable.ic_star_unfilled_white);

            ivStar.setOnClickListener(v -> {
                final SearchRestaurantService searchRestaurantService = new SearchRestaurantService(searchRestaurantFragmentView);
                searchRestaurantService.postWish(restaurantInfo.getRestaurantId());

                ivStar.setImageResource(isWish ?
                        R.drawable.ic_star_unfilled_white : R.drawable.ic_star_filled_orange_and_white_border);

                isWish = !isWish;
            });

            tvName.setText(restaurantInfo.getTitle());

            String areaAndDistance = restaurantInfo.getArea() + " - " + restaurantInfo.getDistance();
            tvAreaDistance.setText(areaAndDistance);

            tvViewCount.setText(restaurantInfo.getSeenNum());
            tvReviewCount.setText(restaurantInfo.getReviewNum());

            String score = restaurantInfo.getRating();
            if(score.indexOf('.') < 0) score += ".0";
            tvScore.setText(score);

            switch (restaurantInfo.getRatingColor()) {
                case "orange": {
                    tvScore.setVisibility(View.VISIBLE);
                    tvScore.setTextColor(itemView.getResources().getColor(R.color.orange));
                    break;
                }
                case "gray": {
                    tvScore.setVisibility(View.VISIBLE);
                    tvScore.setTextColor(itemView.getResources().getColor(R.color.middleBrightGray));
                    break;
                }
                case "": {
                    tvScore.setVisibility(View.INVISIBLE);
                    break;
                }
            }
        }
    }
}
