package com.softsquared.mangoplate.src.main.tab_timeline.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReviewListResponse {
    @SerializedName("result") private ArrayList<ReviewInfo> result;

    @SerializedName("code") private int code;

    @SerializedName("message") private String message;

    @SerializedName("isSuccess") private boolean isSuccess;

    public ArrayList<ReviewInfo> getResult() { return result; }

    public int getCode() { return code; }

    public String getMessage() { return message; }

    public boolean isSuccess() { return isSuccess; }
}
