package com.softsquared.mangoplate.src.login;

import android.util.Log;

import com.softsquared.mangoplate.src.login.interfaces.ILoginActivityView;
import com.softsquared.mangoplate.src.login.interfaces.ILoginRetrofitInterface;
import com.softsquared.mangoplate.src.login.models.KakaoTempLoginBody;
import com.softsquared.mangoplate.src.login.models.LoginBody;
import com.softsquared.mangoplate.src.login.models.LoginResponse;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.mangoplate.src.ApplicationClass.MEDIA_TYPE_JSON;
import static com.softsquared.mangoplate.src.ApplicationClass.TAG;
import static com.softsquared.mangoplate.src.ApplicationClass.getRetrofit;

public class LoginService {
    private final ILoginActivityView loginActivityView;

    public LoginService(ILoginActivityView loginActivityView) {
        this.loginActivityView = loginActivityView;
    }

    public void login(String loginType, String accessToken) {
        final ILoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(ILoginRetrofitInterface.class);
        loginRetrofitInterface.login(loginType, new LoginBody(accessToken))
                .enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NotNull Call<LoginResponse> call, @NotNull Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                Log.d(TAG, "response: " + response);
                Log.d(TAG, "loginResponse: " + loginResponse);
                assert loginResponse != null;
                Log.d(TAG, "code: " + loginResponse.getCode());
                Log.d(TAG, "result: " + loginResponse.getResult());
                Log.d(TAG, "message: " + loginResponse.getMessage());

                if(loginResponse.isSuccess())
                    loginActivityView.validateSuccess(loginResponse.getResult());
                else
                    loginActivityView.validateFailure();
            }

            @Override
            public void onFailure(@NotNull Call<LoginResponse> call, @NotNull Throwable t) {
                loginActivityView.validateFailure();
            }
        });
    }

    // TODO: This is temporary way of API 1-2.
    public void kakaoTempLogin(String id, String name, String profileUrl) {
        final ILoginRetrofitInterface loginRetrofitInterface = getRetrofit().create(ILoginRetrofitInterface.class);
        loginRetrofitInterface.loginTempKakao("kakao", new KakaoTempLoginBody(id, name, profileUrl))
                .enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NotNull Call<LoginResponse> call, @NotNull Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                Log.d(TAG, "response: " + response);
                Log.d(TAG, "loginResponse: " + loginResponse);
                assert loginResponse != null;
                Log.d(TAG, "code: " + loginResponse.getCode());
                Log.d(TAG, "result: " + loginResponse.getResult());
                Log.d(TAG, "message: " + loginResponse.getMessage());

                if(loginResponse.isSuccess())
                    loginActivityView.validateSuccess(loginResponse.getResult());
                else
                    loginActivityView.validateFailure();
            }

            @Override
            public void onFailure(@NotNull Call<LoginResponse> call, @NotNull Throwable t) {
                loginActivityView.validateFailure();
            }
        });
    }
}
