package com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.models;

import com.google.gson.annotations.SerializedName;

public class AreaInfo {
    @SerializedName("id") private int id;

    @SerializedName("name") private String name;

    public int getId() { return id; }

    public String getName() { return name; }
}
