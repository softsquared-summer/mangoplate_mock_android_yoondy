package com.softsquared.mangoplate.src.main.my_info.event.event_detail.models;

import com.google.gson.annotations.SerializedName;

public class EventDetailResponse {
    @SerializedName("result")
    private EventDetailInfo result;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public EventDetailInfo getResult() { return result; }

    public int getCode() { return code; }

    public String getMessage() { return message; }

    public boolean isSuccess() { return isSuccess; }
}
