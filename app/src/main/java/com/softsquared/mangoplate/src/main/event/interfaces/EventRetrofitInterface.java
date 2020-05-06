package com.softsquared.mangoplate.src.main.event.interfaces;

import com.softsquared.mangoplate.src.main.event.models.EventResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EventRetrofitInterface {
    @GET("/events")
    Call<EventResponse> getEventInMyInfo(@Query("type") String type);
}
