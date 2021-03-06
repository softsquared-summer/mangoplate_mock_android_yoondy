package com.softsquared.mangoplate.src.login.interfaces;

import com.softsquared.mangoplate.src.login.models.KakaoTempLoginBody;
import com.softsquared.mangoplate.src.login.models.LoginBody;
import com.softsquared.mangoplate.src.login.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginRetrofitInterface {
    @POST("/jwt")
    @Headers("Content-Type: application/json")
    Call<LoginResponse> login(
            @Query("type") String type,
            @Body LoginBody loginBody
    );

    @POST("/jwt")
    @Headers("Content-Type: application/json")
    Call<LoginResponse> loginTempKakao(
            @Query("type") String type,
            @Body KakaoTempLoginBody kakaoTempLoginBody
            );
}
