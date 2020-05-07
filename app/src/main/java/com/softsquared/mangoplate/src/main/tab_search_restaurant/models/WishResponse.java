package com.softsquared.mangoplate.src.main.tab_search_restaurant.models;

import com.google.gson.annotations.SerializedName;

public class WishResponse {
    @SerializedName("result") private WishInfo result;

    @SerializedName("code") private int code;

    @SerializedName("message") private String message;

    @SerializedName("isSuccess") private boolean isSuccess;

    public WishInfo getResult() { return result; }

    public int getCode() { return code; }

    public String getMessage() { return message; }

    public boolean isSuccess() { return isSuccess; }
}
