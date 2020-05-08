package com.softsquared.mangoplate.src.main.tab_timeline;

import android.util.Log;

import com.softsquared.mangoplate.src.main.tab_timeline.interfaces.TimelineFragmentView;
import com.softsquared.mangoplate.src.main.tab_timeline.interfaces.TimelineRetrofitInterface;
import com.softsquared.mangoplate.src.main.tab_timeline.models.ReviewListResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.mangoplate.src.ApplicationClass.TAG;
import static com.softsquared.mangoplate.src.ApplicationClass.getRetrofit;

class TimelineService {
    private final TimelineFragmentView timelineFragmentView;

    TimelineService(TimelineFragmentView timelineFragmentView) {
        this.timelineFragmentView = timelineFragmentView;
    }

    void getReviewList() {
        final TimelineRetrofitInterface timelineRetrofitInterface = getRetrofit().create(TimelineRetrofitInterface.class);
        ArrayList<String> review = new ArrayList<>();
        review.add("5");
        review.add("3");
        review.add("1");
        timelineRetrofitInterface.getReviewList("all", review, null).enqueue(new Callback<ReviewListResponse>() {
            @Override
            public void onResponse(@NotNull Call<ReviewListResponse> call, @NotNull Response<ReviewListResponse> response) {
                ReviewListResponse reviewListResponse = response.body();
                if(reviewListResponse == null) {
                    Log.d(TAG, "TimelineService::getReviewList() fail. reviewListResponse is null");
                    timelineFragmentView.onFailureGetReviewList();
                    return;
                }
                else if(!reviewListResponse.isSuccess()) {
                    Log.d(TAG, "TimelineService::getReviewList() fail. reviewListResponse code: " + reviewListResponse.getCode());
                    Log.d(TAG, "TimelineService::getReviewList() fail. reviewListResponse message: " + reviewListResponse.getMessage());
                    timelineFragmentView.onFailureGetReviewList();
                    return;
                }

                timelineFragmentView.onSuccessGetReviewList(reviewListResponse.getResult());
            }

            @Override
            public void onFailure(@NotNull Call<ReviewListResponse> call, @NotNull Throwable t) {
                Log.d(TAG, "TimelineService::getReviewList() fail : " + t);
                timelineFragmentView.onFailureGetReviewList();
            }
        });
    }
}
