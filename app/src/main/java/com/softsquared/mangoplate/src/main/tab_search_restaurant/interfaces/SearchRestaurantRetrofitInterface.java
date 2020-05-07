package com.softsquared.mangoplate.src.main.tab_search_restaurant.interfaces;

import com.softsquared.mangoplate.src.main.tab_search_restaurant.models.BannerAdsResponse;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.models.RestaurantListResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchRestaurantRetrofitInterface {
    @GET("/events")
    Call<BannerAdsResponse> getBannerAd(@Query("type") String type);

    @GET("/restaurants")
    Call<RestaurantListResponse> getRestaurantList(
            @Query("lat") float lat, // Mandatory
            @Query("lng") float lng, // Mandatory
            @Query("type") @NotNull String type, // Mandatory
            @Query("area") String area,
            @Query("order") String order,
            @Query("category") String category,
            @Query("kind") String kind,
            @Query("price") String price,
            @Query("parking") String parking,
            @Query("radius") String radius,
            @Query("page") String page,
            @Query("size") String size // Mandatory if there is "page"
    );
}
