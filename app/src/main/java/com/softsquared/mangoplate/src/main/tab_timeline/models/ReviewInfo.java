package com.softsquared.mangoplate.src.main.tab_timeline.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReviewInfo {
    @SerializedName("reviewId") private int reviewId;

    @SerializedName("userId") private int userId;

    @SerializedName("name") private String name;

    @SerializedName("profileUrl") private String profileUrl;

    @SerializedName("reviewNum") private String reviewNum;

    @SerializedName("followerNum") private String followerNum;

    @SerializedName("review") private String review;

    @SerializedName("restaurantId") private int restaurantId;

    @SerializedName("restaurantName") private String restaurantName;

    @SerializedName("restaurantArea") private String restaurantArea;

    @SerializedName("content") private String content;

    @SerializedName("createdAt") private String createdAt;

    @SerializedName("userStar") private String userStar;

    @SerializedName("images") private ArrayList<ImageInfo> images;

    public int getReviewId() { return reviewId; }

    public int getUserId() { return userId; }

    public String getName() { return name; }

    public String getProfileUrl() { return profileUrl; }

    public String getReviewNum() { return reviewNum; }

    public String getFollowerNum() { return followerNum; }

    public String getReview() { return review; }

    public int getRestaurantId() { return restaurantId; }

    public String getRestaurantName() { return restaurantName; }

    public String getRestaurantArea() { return restaurantArea; }

    public String getContent() { return content; }

    public String getCreatedAt() { return createdAt; }

    public String getUserStar() { return userStar; }

    public ArrayList<ImageInfo> getImages() { return images; }
}
