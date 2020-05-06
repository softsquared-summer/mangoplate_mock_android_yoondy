package com.softsquared.mangoplate.src.main;

import android.util.Log;

import com.softsquared.mangoplate.src.main.interfaces.IMainActivityView;
import com.softsquared.mangoplate.src.main.interfaces.IMainRetrofitInterface;
import com.softsquared.mangoplate.src.main.models.UserResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.mangoplate.src.ApplicationClass.TAG;
import static com.softsquared.mangoplate.src.ApplicationClass.getRetrofit;

class MainService {
    private final IMainActivityView mainActivityView;

    MainService(IMainActivityView mainActivityView) {
        this.mainActivityView = mainActivityView;
    }

    // API 1-5
    void getMyInfo() {
        IMainRetrofitInterface mainRetrofitInterface = getRetrofit().create(IMainRetrofitInterface.class);
        mainRetrofitInterface.getUserInfo().enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NotNull Call<UserResponse> call, @NotNull Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                if (userResponse == null) {
                    Log.d(TAG, "MainService::getMyInfo() onFailure() : userResponse is null");
                    return;
                }

                Log.d(TAG, "getMyInfo() result: " + userResponse.getResult());
                Log.d(TAG, "getMyInfo() code: " + userResponse.getCode());
                Log.d(TAG, "getMyInfo() message: " + userResponse.getMessage());

                mainActivityView.onGetUserSuccess(userResponse.getResult());
            }

            @Override
            public void onFailure(@NotNull Call<UserResponse> call, @NotNull Throwable t) {
                Log.d(TAG, "MainService::getMyInfo() onFailure() : " + t);
            }
        });
    }
}
