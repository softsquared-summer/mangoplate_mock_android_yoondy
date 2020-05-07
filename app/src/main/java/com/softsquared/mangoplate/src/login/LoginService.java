package com.softsquared.mangoplate.src.login;

import android.util.Log;

import com.softsquared.mangoplate.src.login.interfaces.LoginActivityView;
import com.softsquared.mangoplate.src.login.interfaces.LoginRetrofitInterface;
import com.softsquared.mangoplate.src.login.models.KakaoTempLoginBody;
import com.softsquared.mangoplate.src.login.models.LoginBody;
import com.softsquared.mangoplate.src.login.models.LoginResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.mangoplate.src.ApplicationClass.TAG;
import static com.softsquared.mangoplate.src.ApplicationClass.getRetrofit;

class LoginService {
    private final LoginActivityView loginActivityView;

    LoginService(LoginActivityView loginActivityView) {
        this.loginActivityView = loginActivityView;
    }

    void login(String loginType, String accessToken) {
        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.login(loginType, new LoginBody(accessToken))
                .enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NotNull Call<LoginResponse> call, @NotNull Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if(loginResponse == null) {
                    Log.d(TAG, "LoginService::login() fail. loginResponse is null");
                    loginActivityView.onFailureLogin();
                    return;
                }
                else if(!loginResponse.isSuccess()) {
                    Log.d(TAG, "LoginService::login() fail. loginResponse code: " + loginResponse.getCode());
                    Log.d(TAG, "LoginService::login() fail. loginResponse message: "+ loginResponse.getMessage());
                    loginActivityView.onFailureLogin();
                    return;
                }

                loginActivityView.onSuccessLogin(loginResponse.getResult());
            }

            @Override
            public void onFailure(@NotNull Call<LoginResponse> call, @NotNull Throwable t) {
                Log.d(TAG, "LoginService::login() fail : " + t);
                loginActivityView.onFailureLogin();
            }
        });
    }

    // TODO: This is temporary way of API 1-2.
    void kakaoTempLogin(String id, String name, String profileUrl) {
        final LoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(LoginRetrofitInterface.class);
        loginRetrofitInterface.loginTempKakao("kakao", new KakaoTempLoginBody(id, name, profileUrl))
                .enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NotNull Call<LoginResponse> call, @NotNull Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if(loginResponse == null) {
                    Log.d(TAG, "LoginService::login() fail. loginResponse is null");
                    loginActivityView.onFailureLogin();
                    return;
                }
                else if(!loginResponse.isSuccess()) {
                    Log.d(TAG, "LoginService::login() fail. loginResponse code: " + loginResponse.getCode());
                    Log.d(TAG, "LoginService::login() fail. loginResponse message: "+ loginResponse.getMessage());
                    loginActivityView.onFailureLogin();
                    return;
                }

                loginActivityView.onSuccessLogin(loginResponse.getResult());
            }

            @Override
            public void onFailure(@NotNull Call<LoginResponse> call, @NotNull Throwable t) {
                Log.d(TAG, "LoginService::login() fail : " + t);
                loginActivityView.onFailureLogin();
            }
        });
    }
}
