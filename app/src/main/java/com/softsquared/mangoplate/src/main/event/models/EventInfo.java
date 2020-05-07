package com.softsquared.mangoplate.src.main.event.models;

import com.google.gson.annotations.SerializedName;

public class EventInfo {
    @SerializedName("eventId") private int eventId;

    @SerializedName("imageUrl") private String imageUrl;

    @SerializedName("title") private String title;

    @SerializedName("status") private String status;

    @SerializedName("date") private String date;

    public int getEventId() { return eventId; }

    public String getImageUrl() { return imageUrl; }

    public String getTitle() { return title; }

    public String getStatus() { return status; }

    public String getDate() { return date; }
}
