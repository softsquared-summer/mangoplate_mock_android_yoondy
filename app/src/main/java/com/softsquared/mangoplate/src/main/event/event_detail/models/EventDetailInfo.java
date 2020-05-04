package com.softsquared.mangoplate.src.main.event.event_detail.models;

import com.google.gson.annotations.SerializedName;

class EventDetailInfo {

    @SerializedName("imageUrl")
    private String imageUrl;

    public String getImageUrl() { return imageUrl; }

    // TODO: test. It must be removed later.
    public EventDetailInfo(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
