package com.softsquared.mangoplate.src.main.restaurant_detail.models;

import com.google.gson.annotations.SerializedName;

public class RestaurantDetailMenuInfo {
    @SerializedName("name") private String name;

    @SerializedName("price") private String price;

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
