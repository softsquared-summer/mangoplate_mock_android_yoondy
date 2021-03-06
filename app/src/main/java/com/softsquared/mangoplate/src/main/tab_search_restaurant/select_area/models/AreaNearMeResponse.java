package com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AreaNearMeResponse {
    @SerializedName("result") private ArrayList<AreaNearMeInfo> result;

    @SerializedName("code") private int code;

    @SerializedName("message") private String message;

    @SerializedName("isSuccess") private boolean isSuccess;

    public ArrayList<AreaNearMeInfo> getResult() { return result; }

    public int getCode() { return code; }

    public String getMessage() { return message; }

    public boolean isSuccess() { return isSuccess; }
}
