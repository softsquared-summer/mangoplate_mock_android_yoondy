package com.softsquared.mangoplate.src.main.event.event_detail;

import android.util.Log;

import com.softsquared.mangoplate.src.main.event.event_detail.interfaces.EventDetailActivityView;
import com.softsquared.mangoplate.src.main.event.event_detail.interfaces.EventDetailRetrofitInterface;
import com.softsquared.mangoplate.src.main.event.event_detail.models.EventDetailResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.mangoplate.src.ApplicationClass.TAG;
import static com.softsquared.mangoplate.src.ApplicationClass.getRetrofit;

class EventDetailService {
    private final EventDetailActivityView eventDetailActivityView;

    EventDetailService(EventDetailActivityView eventDetailActivityView) {
        this.eventDetailActivityView = eventDetailActivityView;
    }

    // API 2-3
    void getEventDetail(int eventId) {
        EventDetailRetrofitInterface eventDetailRetrofitInterface = getRetrofit().create(EventDetailRetrofitInterface.class);
        eventDetailRetrofitInterface.getEventInMyInfo(eventId).enqueue(new Callback<EventDetailResponse>() {
            @Override
            public void onResponse(@NotNull Call<EventDetailResponse> call, @NotNull Response<EventDetailResponse> response) {
                EventDetailResponse eventDetailResponse = response.body();
                if(eventDetailResponse == null) {
                    Log.d(TAG, "EventDetailService::getEventDetail() failure. eventDetailResponse is null");
                    eventDetailActivityView.onFailureGetEventDetail();
                    return;
                }
                else if(!eventDetailResponse.isSuccess()) {
                    Log.d(TAG, "EventDetailService::getEventDetail() failure. eventDetailResponse code: " + eventDetailResponse.getCode());
                    Log.d(TAG, "EventDetailService::getEventDetail() failure. eventDetailResponse message: " + eventDetailResponse.getMessage());
                    eventDetailActivityView.onFailureGetEventDetail();
                    return;
                }

                eventDetailActivityView.onSuccessGetEventDetail(eventDetailResponse.getResult());
            }

            @Override
            public void onFailure(@NotNull Call<EventDetailResponse> call, @NotNull Throwable t) {
                Log.d(TAG, "EventDetailService::getEventDetail() failure: " + t);
                eventDetailActivityView.onFailureGetEventDetail();
            }
        });
    }
}
