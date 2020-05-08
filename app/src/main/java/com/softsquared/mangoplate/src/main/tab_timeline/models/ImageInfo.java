package com.softsquared.mangoplate.src.main.tab_timeline.models;

import com.google.gson.annotations.SerializedName;

public class ImageInfo {
    @SerializedName("imageId") private String imageId;

    @SerializedName("imageUrl") private String imageUrl;

    public String getImageId() { return imageId; }

    public String getImageUrl() { return imageUrl; }
}
