package com.softsquared.mangoplate.src.main.event.event_detail.interfaces;

import com.softsquared.mangoplate.src.main.event.event_detail.models.EventDetailResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EventDetailRetrofitInterface {
    @GET("/events/{eventId}")
    Call<EventDetailResponse> getEventInMyInfo(@Path("eventId") String eventId);
}
