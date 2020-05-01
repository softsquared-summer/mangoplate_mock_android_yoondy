package com.softsquared.mangoplate.src.main.search_restaurant.models;

import com.google.gson.annotations.SerializedName;

public class RestaurantInfo {
    // TODO: After completed API 4-1, These vars and funs must be changed. These are temp names.
    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("name")
    private String name;

    @SerializedName("area")
    private String area;

    @SerializedName("distance")
    private String distance;

    @SerializedName("viewCount")
    private int viewCount;

    @SerializedName("reviewCount")
    private int reviewCount;

    @SerializedName("score")
    private float score;

    @SerializedName("isWantToGo")
    private boolean isWantToGo;

    public String getImageUrl() { return imageUrl; }

    public String getName() { return name; }

    public String getArea() { return area; }

    public String getDistance() { return distance; }

    public int getViewCount() { return viewCount; }

    public int getReviewCount() { return reviewCount; }

    public float getScore() { return score; }

    public boolean isWantToGo() { return isWantToGo; }

    // TODO: test. It must be removed later.
    public RestaurantInfo(String imageUrl, String name, String area, String distance, int viewCount, int reviewCount, float score, boolean isWantToGo) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.area = area;
        this.distance = distance;
        this.viewCount = viewCount;
        this.reviewCount = reviewCount;
        this.score = score;
        this.isWantToGo = isWantToGo;
    }
}
