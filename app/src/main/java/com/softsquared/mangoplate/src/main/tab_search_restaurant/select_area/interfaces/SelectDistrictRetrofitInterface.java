package com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.interfaces;

import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.models.AreaListResponse;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.models.AreaNearMeResponse;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.models.DistrictListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SelectDistrictRetrofitInterface {
    @GET("/near-districts")
    Call<AreaNearMeResponse> getAreaNearMe(@Query("lat") float lat,
                                           @Query("lng") float lng);

    @GET("/districts")
    Call<DistrictListResponse> getDistrictList();

    @GET("/districts/{districtId}")
    Call<AreaListResponse> getAreaList(@Path("districtId") int districtId);
}
