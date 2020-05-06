package com.softsquared.mangoplate.src.login.interfaces;

import com.softsquared.mangoplate.src.login.models.LoginResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ILoginRetrofitInterface {
    @POST("/jwt")
    @Headers("Content-Type: application/json")
    Call<LoginResponse> login(
            @Query("type") String type,
            @Body RequestBody params
    );

    @POST("/jwt")
    @Headers("Content-Type: application/json")
    Call<LoginResponse> loginTempKakao(
            @Query("type") String type,
            @Body RequestBody params
    );
}
