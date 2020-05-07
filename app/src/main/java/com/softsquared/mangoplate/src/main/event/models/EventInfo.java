package com.softsquared.mangoplate.src.main.event.models;

import com.google.gson.annotations.SerializedName;

public class EventInfo {
    @SerializedName("eventId")
    private int eventId;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("title")
    private String title;

    @SerializedName("status")
    private String status;

    @SerializedName("date")
    private String date;

    int getEventId() { return eventId; }

    String getImageUrl() { return imageUrl; }

    String getTitle() { return title; }

    String getStatus() { return status; }

    String getDate() { return date; }
}
