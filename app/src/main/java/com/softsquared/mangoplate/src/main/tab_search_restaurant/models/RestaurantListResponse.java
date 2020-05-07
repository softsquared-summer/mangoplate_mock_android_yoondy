package com.softsquared.mangoplate.src.main.tab_search_restaurant.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RestaurantListResponse {
    @SerializedName("result") private ArrayList<RestaurantInfo> result;

    @SerializedName("code") private int code;

    @SerializedName("message") private String message;

    @SerializedName("isSuccess") private boolean isSuccess;

    public ArrayList<RestaurantInfo> getResult() { return result; }

    public int getCode() { return code; }

    public String getMessage() { return message; }

    public boolean isSuccess() { return isSuccess; }
}
