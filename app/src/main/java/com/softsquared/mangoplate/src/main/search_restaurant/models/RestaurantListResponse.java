package com.softsquared.mangoplate.src.main.search_restaurant.models;

import com.google.gson.annotations.SerializedName;

public class RestaurantListResponse {
    @SerializedName("result")
    private RestaurantInfo result;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public RestaurantInfo getResult() { return result; }

    public int getCode() { return code; }

    public String getMessage() { return message; }

    public boolean isSuccess() { return isSuccess; }
}
