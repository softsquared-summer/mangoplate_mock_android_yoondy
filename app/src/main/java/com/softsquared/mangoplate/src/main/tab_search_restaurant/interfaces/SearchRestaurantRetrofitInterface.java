package com.softsquared.mangoplate.src.main.tab_search_restaurant.interfaces;

import com.softsquared.mangoplate.src.main.tab_search_restaurant.models.BannerAdsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchRestaurantRetrofitInterface {
    @GET("/events")
    Call<BannerAdsResponse> getBannerAd(@Query("type") String type);
}
