package com.softsquared.mangoplate.src.main.tab_timeline.interfaces;

import com.softsquared.mangoplate.src.main.tab_timeline.models.ReviewListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TimelineRetrofitInterface {
    @GET("/reviews")
    Call<ReviewListResponse> getReviewList(
            @Query("type") String type,
            @Query("review") List<String> review,
            @Query("area") List<String> area);
}
