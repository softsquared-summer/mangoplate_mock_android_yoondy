package com.softsquared.mangoplate.src.main.restaurant_detail.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RestaurantDetailInfo {
    @SerializedName("images") ArrayList<RestaurantDetailPhotoInfo> images;

    @SerializedName("name") private String name;

    @SerializedName("seenNum") private String seenNum;

    @SerializedName("reviewNum") private String reviewNum;

    @SerializedName("starNum") private String starNum;

    @SerializedName("rating") private String rating;

    @SerializedName("ratingColor") private String ratingColor;

    @SerializedName("userStar") private String userStar;

    @SerializedName("address") private String address;

    @SerializedName("oldAddress") private String oldAddress;

    @SerializedName("phone") private String phone;

    @SerializedName("userName") private String userName;

    @SerializedName("userProfileUrl") private String userProfileUrl;

    @SerializedName("infoUpdate") private String infoUpdate;

    @SerializedName("infoTime") private String infoTime;

    @SerializedName("infoHoliday") private String infoHoliday;

    @SerializedName("infoDescription") private String infoDescription;

    @SerializedName("infoPrice") private String infoPrice;

    @SerializedName("infoKind") private String infoKind;

    @SerializedName("infoParking") private String infoParking;

    @SerializedName("infoSite") private String infoSite;

    @SerializedName("keywords") private ArrayList<String> keywords;

    @SerializedName("menu") private ArrayList<RestaurantDetailMenuInfo> menu;

    @SerializedName("menuUpdate") private String menuUpdate;

    public ArrayList<RestaurantDetailPhotoInfo> getImages() {
        return images;
    }

    public String getName() {
        return name;
    }

    public String getSeenNum() {
        return seenNum;
    }

    public String getReviewNum() {
        return reviewNum;
    }

    public String getStarNum() {
        return starNum;
    }

    public String getRating() {
        return rating;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public String getUserStar() {
        return userStar;
    }

    public String getAddress() {
        return address;
    }

    public String getOldAddress() {
        return oldAddress;
    }

    public String getPhone() {
        return phone;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserProfileUrl() {
        return userProfileUrl;
    }

    public String getInfoUpdate() {
        return infoUpdate;
    }

    public String getInfoTime() {
        return infoTime;
    }

    public String getInfoHoliday() {
        return infoHoliday;
    }

    public String getInfoDescription() {
        return infoDescription;
    }

    public String getInfoPrice() {
        return infoPrice;
    }

    public String getInfoKind() {
        return infoKind;
    }

    public String getInfoParking() {
        return infoParking;
    }

    public String getInfoSite() {
        return infoSite;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public ArrayList<RestaurantDetailMenuInfo> getMenu() {
        return menu;
    }

    public String getMenuUpdate() {
        return menuUpdate;
    }
}
