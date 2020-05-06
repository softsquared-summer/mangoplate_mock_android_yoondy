package com.softsquared.mangoplate.src.main.interfaces;

import com.softsquared.mangoplate.src.main.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MainRetrofitInterface {
    @GET("/user")
    Call<UserResponse> getUserInfo();
}
