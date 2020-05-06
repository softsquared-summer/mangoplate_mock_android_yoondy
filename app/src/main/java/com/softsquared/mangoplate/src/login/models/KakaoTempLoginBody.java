package com.softsquared.mangoplate.src.login.models;

import com.google.gson.annotations.SerializedName;

public class KakaoTempLoginBody {
    @SerializedName("id") private String id;

    @SerializedName("name") private String name;

    @SerializedName("profileUrl") private String profileUrl;

    public KakaoTempLoginBody(String id, String name, String profileUrl) {
        this.id = id;
        this.name = name;
        this.profileUrl = profileUrl;
    }
}
