package com.softsquared.mangoplate.src.main.discount.top_list.models;

import com.google.gson.annotations.SerializedName;

class TopListResponse {
    @SerializedName("result")
    private TopListInfo result;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public TopListInfo getResult() { return result; }

    public int getCode() { return code; }

    public String getMessage() { return message; }

    public boolean isSuccess() { return isSuccess; }
}
