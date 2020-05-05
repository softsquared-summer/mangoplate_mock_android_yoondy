package com.softsquared.mangoplate.src.main.restaurant_detail.models;

import com.google.gson.annotations.SerializedName;

public class RestaurantDetailResponse {
    @SerializedName("result") private RestaurantDetailInfo result;

    @SerializedName("code") private int code;

    @SerializedName("message") private String message;

    @SerializedName("isSuccess") private boolean isSuccess;

    public RestaurantDetailInfo getResult() { return result; }

    public int getCode() { return code; }

    public String getMessage() { return message; }

    public boolean isSuccess() { return isSuccess; }
}
