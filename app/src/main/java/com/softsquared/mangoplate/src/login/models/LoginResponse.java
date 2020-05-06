package com.softsquared.mangoplate.src.login.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("result") private LoginInfo result;

    @SerializedName("code") private int code;

    @SerializedName("message") private String message;

    @SerializedName("isSuccess") private boolean isSuccess;

    public LoginInfo getResult() { return result; }

    public boolean isSuccess() { return isSuccess; }

    public int getCode() { return code; }

    public String getMessage() { return message; }
}
