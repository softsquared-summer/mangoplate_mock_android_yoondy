package com.softsquared.mangoplate.src.main.tab_search_restaurant.models;

import com.google.gson.annotations.SerializedName;

public class BannerAdInfo {
    @SerializedName("eventId") private int eventId;

    @SerializedName("imageUrl") private String imageUrl;

    String getImageUrl() { return imageUrl; }

    int getEventId() { return eventId; }
}
