package com.softsquared.mangoplate.src.main.restaurant_detail.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.mangoplate.R;

import java.util.ArrayList;

public class RestaurantDetailMenuAdapter extends RecyclerView.Adapter<RestaurantDetailMenuAdapter.RestaurantDetailMenuViewHolder> {
    private ArrayList<RestaurantDetailMenuInfo> menuInfoArrayList = new ArrayList<>();

    @NonNull
    @Override
    public RestaurantDetailMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurant_detail_menu, parent, false);
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

    public void add(RestaurantDetailMenuInfo info) {
        menuInfoArrayList.add(info);
    }

    class RestaurantDetailMenuViewHolder extends RecyclerView.ViewHolder {
        TextView tvMenuName;
        TextView tvMenuPrice;

        RestaurantDetailMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMenuName = itemView.findViewById(R.id.restaurant_detail_tv_menu_name);
            tvMenuPrice = itemView.findViewById(R.id.restaurant_detail_tv_menu_price);
        }

        void bind(RestaurantDetailMenuInfo restaurantDetailMenuInfo) {
            tvMenuName.setText(restaurantDetailMenuInfo.getName());
            tvMenuPrice.setText(restaurantDetailMenuInfo.getPrice());
        }
    }
}
