package com.softsquared.mangoplate.src.login;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginResult;
import com.softsquared.mangoplate.src.ApplicationClass;

public class FacebookLoginCallback implements FacebookCallback<LoginResult> {
    private Activity activity;

    FacebookLoginCallback(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        String accessToken = AccessToken.getCurrentAccessToken().getToken();
        Log.d(ApplicationClass.TAG, "accessToken: " + accessToken);
        if(activity instanceof LoginActivity) {
            final LoginService loginService = new LoginService((LoginActivity) activity);
            loginService.login("facebook", accessToken);
        }

        GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                (object, response) -> Log.d(ApplicationClass.TAG, "onCompleted(). JSONObject: " + object.toString()));

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();
    }

    @Override
    public void onCancel() {
    }

    @Override
    public void onError(FacebookException error) {
        Log.e(ApplicationClass.TAG, "FacebookLoginCallback::onError(): " + error.getMessage());
    }
}
