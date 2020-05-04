package com.softsquared.mangoplate.src.main.event.models;

import com.google.gson.annotations.SerializedName;

public class EventInfo {
    @SerializedName("eventId")
    private String eventId;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("title")
    private String title;

    @SerializedName("status")
    private String status;

    @SerializedName("date")
    private String date;

    String getEventId() { return eventId; }

    String getImageUrl() { return imageUrl; }

    String getTitle() { return title; }

    String getStatus() { return status; }

    String getDate() { return date; }

    // TODO: test. It must be removed later.
    public EventInfo(String eventId, String imageUrl, String title, String status, String date) {
        this.eventId = eventId;
        this.imageUrl = imageUrl;
        this.title = title;
        this.status = status;
        this.date = date;
    }
}
