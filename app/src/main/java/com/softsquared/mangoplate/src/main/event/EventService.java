package com.softsquared.mangoplate.src.main.event;

import android.util.Log;

import com.softsquared.mangoplate.src.main.event.interfaces.EventActivityView;
import com.softsquared.mangoplate.src.main.event.interfaces.EventRetrofitInterface;
import com.softsquared.mangoplate.src.main.event.models.EventResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.mangoplate.src.ApplicationClass.TAG;
import static com.softsquared.mangoplate.src.ApplicationClass.getRetrofit;

class EventService {
    private final EventActivityView eventActivityView;

    EventService(EventActivityView eventActivityView) {
        this.eventActivityView = eventActivityView;
    }

    // API 2-2
    void getEventInMyInfo() {
        EventRetrofitInterface eventRetrofitInterface = getRetrofit().create(EventRetrofitInterface.class);
        eventRetrofitInterface.getEventInMyInfo("detail").enqueue(new Callback<EventResponse>() {
            @Override
            public void onResponse(@NotNull Call<EventResponse> call, @NotNull Response<EventResponse> response) {
                EventResponse eventResponse = response.body();
                if(eventResponse == null) {
                    Log.d(TAG, "EventService::getEventInMyInfo() failure. eventResponse is null");
                    eventActivityView.onFailureGetEventInMyInfo();
                    return;
                }
                else if(!eventResponse.isSuccess()) {
                    Log.d(TAG, "EventService::getEventInMyInfo() failure. eventResponse code: " + eventResponse.getCode());
                    Log.d(TAG, "EventService::getEventInMyInfo() failure. eventResponse message: " + eventResponse.getMessage());
                    eventActivityView.onFailureGetEventInMyInfo();
                    return;
                }

                eventActivityView.onSuccessGetEventInMyInfo(eventResponse.getResult());
            }

            @Override
            public void onFailure(@NotNull Call<EventResponse> call, @NotNull Throwable t) {
                Log.d(TAG, "EventService::getEventInMyInfo() Failure: " + t);
                eventActivityView.onFailureGetEventInMyInfo();
            }
        });
    }
}
