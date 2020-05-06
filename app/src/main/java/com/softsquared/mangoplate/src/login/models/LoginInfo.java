package com.softsquared.mangoplate.src.login.models;

import com.google.gson.annotations.SerializedName;

public class LoginInfo {
    @SerializedName("jwt") private String jwt;

    public String getJwt() { return jwt; }
}
