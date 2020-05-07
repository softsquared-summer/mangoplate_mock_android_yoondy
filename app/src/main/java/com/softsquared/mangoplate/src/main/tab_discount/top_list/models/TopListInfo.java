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

    public String getImageUrl() { return imageUrl; }

    public String getTitle() { return title; }

    public String getSubtitle() { return subtitle; }

    public String getViewCount() { return viewCount; }

    public String getDate() { return date; }

    public boolean isBookmark() { return isBookmark; }

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
