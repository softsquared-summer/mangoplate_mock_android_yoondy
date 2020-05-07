package com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.models;

import com.google.gson.annotations.SerializedName;

public class AreaNearMeInfo {
    @SerializedName("districtId") private int districtId;

    @SerializedName("areaId") private int areaId;

    @SerializedName("name") private String name;

    @SerializedName("distance") private String distance;

    public int getDistrictId() { return districtId; }

    public int getAreaId() { return areaId; }

    public String getName() { return name; }

    public String getDistance() { return distance; }
}
