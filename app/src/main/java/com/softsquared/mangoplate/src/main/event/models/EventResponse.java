package com.softsquared.mangoplate.src.main.event.models;

import com.google.gson.annotations.SerializedName;

public class EventResponse {
    @SerializedName("result")
    private EventInfo result;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public EventInfo getResult() { return result; }

    public int getCode() { return code; }

    public String getMessage() { return message; }

    public boolean isSuccess() { return isSuccess; }
}
