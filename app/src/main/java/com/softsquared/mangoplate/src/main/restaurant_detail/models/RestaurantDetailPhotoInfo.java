package com.softsquared.mangoplate.src.main.restaurant_detail.models;

import com.google.gson.annotations.SerializedName;

public class RestaurantDetailPhotoInfo {
    @SerializedName("imageId") private int imageId;

    @SerializedName("imageUrl") private String imageUrl;

    public int getImageId() {
        return imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    // TODO: test. It must be removed later.
    public RestaurantDetailPhotoInfo(int imageId, String imageUrl) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }
}
