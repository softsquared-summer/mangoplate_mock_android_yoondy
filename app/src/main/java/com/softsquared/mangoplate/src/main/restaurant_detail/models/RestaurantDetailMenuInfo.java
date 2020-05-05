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

    // TODO: test. It must be removed later.
    public RestaurantDetailMenuInfo(String name, String price) {
        this.name = name;
        this.price = price;
    }
}
