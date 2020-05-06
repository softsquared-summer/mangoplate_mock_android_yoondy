package com.softsquared.mangoplate.src.main.models;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("result") private UserInfo result;

    @SerializedName("code") private int code;

    @SerializedName("message") private String message;

    @SerializedName("isSuccess") private boolean isSuccess;

    public UserInfo getResult() { return result; }

    public int getCode() { return code; }

    public String getMessage() { return message; }

    public boolean isSuccess() { return isSuccess; }
}
