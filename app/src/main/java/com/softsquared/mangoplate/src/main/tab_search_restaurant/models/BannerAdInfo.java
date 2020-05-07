package com.softsquared.mangoplate.src.main.tab_search_restaurant.models;

import com.google.gson.annotations.SerializedName;

public class BannerAdInfo {
    @SerializedName("eventId") private int eventId;

    @SerializedName("imageUrl") private String imageUrl;

    public String getImageUrl() { return imageUrl; }

    public int getEventId() { return eventId; }
}
