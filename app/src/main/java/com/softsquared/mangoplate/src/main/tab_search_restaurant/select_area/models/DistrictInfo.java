package com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.models;

import com.google.gson.annotations.SerializedName;

public class DistrictInfo {
    @SerializedName("distinctsId") int distinctsId;

    @SerializedName("name") String name;

    public int getDistinctsId() { return distinctsId; }

    public String getName() { return name; }
}
