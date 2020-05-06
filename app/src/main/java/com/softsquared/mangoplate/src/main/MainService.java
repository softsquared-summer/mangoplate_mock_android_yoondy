package com.softsquared.mangoplate.src.main;

import android.util.Log;

import com.softsquared.mangoplate.src.main.interfaces.MainActivityView;
import com.softsquared.mangoplate.src.main.interfaces.MainRetrofitInterface;
import com.softsquared.mangoplate.src.main.models.UserResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.mangoplate.src.ApplicationClass.TAG;
import static com.softsquared.mangoplate.src.ApplicationClass.getRetrofit;

class MainService {
    private final MainActivityView mainActivityView;

    MainService(MainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
    }

    // API 1-5
    void getMyInfo() {
        MainRetrofitInterface mainRetrofitInterface = getRetrofit().create(MainRetrofitInterface.class);
        mainRetrofitInterface.getUserInfo().enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NotNull Call<UserResponse> call, @NotNull Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                if(userResponse == null) {
                    Log.d(TAG, "MainService::getMyInfo() failure. userResponse is null");
                    mainActivityView.onFailureGetUser();
                    return;
                }
                else if(!userResponse.isSuccess()) {
                    Log.d(TAG, "MainService::getMyInfo() failure. userResponse code: " + userResponse.getCode());
                    Log.d(TAG, "MainService::getMyInfo() failure. userResponse message: " + userResponse.getMessage());
                    mainActivityView.onFailureGetUser();
                    return;
                }

                mainActivityView.onSuccessGetUser(userResponse.getResult());
            }

            @Override
            public void onFailure(@NotNull Call<UserResponse> call, @NotNull Throwable t) {
                mainActivityView.onFailureGetUser();
            }
        });
    }
}
