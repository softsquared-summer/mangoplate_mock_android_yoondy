package com.softsquared.mangoplate.src.main.restaurant_detail.interfaces;

import com.softsquared.mangoplate.src.main.restaurant_detail.models.RestaurantDetailResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestaurantDetailRetrofitInterface {
    @GET("/restaurants/{restaurantId}")
    Call<RestaurantDetailResponse> getRestaurantDetail(@Path("restaurantId") int restaurantId);
}
