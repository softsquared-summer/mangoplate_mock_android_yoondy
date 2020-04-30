package com.softsquared.mangoplate.src.main.search_restaurant.models;

import com.google.gson.annotations.SerializedName;

public class BannerAdInfo {
    @SerializedName("imageUrl")
    private String imageUrl;

    public String getImageUrl() { return imageUrl; }

    // TODO: test. It must be removed later.
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
