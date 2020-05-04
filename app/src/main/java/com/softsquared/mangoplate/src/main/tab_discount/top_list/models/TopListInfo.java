package com.softsquared.mangoplate.src.main.tab_discount.top_list.models;

import com.google.gson.annotations.SerializedName;

public class TopListInfo {
    // TODO: After completed API 14, These vars and funs must be changed. These are temp names.
    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("title")
    private String title;

    @SerializedName("subtitle")
    private String subtitle;

    @SerializedName("date")
    private String date;

    @SerializedName("viewCount")
    private String viewCount;

    @SerializedName("isBookmark")
    private boolean isBookmark;

    String getImageUrl() { return imageUrl; }

    String getTitle() { return title; }

    String getSubtitle() { return subtitle; }

    String getViewCount() { return viewCount; }

    String getDate() { return date; }

    boolean isBookmark() { return isBookmark; }

    // TODO: test. It must be removed later.
    public TopListInfo(String imageUrl, String title, String subtitle, String date, String viewCount, boolean isBookmark) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.subtitle = subtitle;
        this.date = date;
        this.viewCount = viewCount;
        this.isBookmark = isBookmark;
    }
}
