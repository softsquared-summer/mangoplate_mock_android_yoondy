package com.softsquared.mangoplate.src.login.models;

import com.google.gson.annotations.SerializedName;

public class LoginBody {
    @SerializedName("at") private String at;

    public LoginBody(String at) { this.at = at; }
}
