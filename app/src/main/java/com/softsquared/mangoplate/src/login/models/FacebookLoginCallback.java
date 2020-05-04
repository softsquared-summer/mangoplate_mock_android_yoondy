package com.softsquared.mangoplate.src.login.models;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.login.LoginResult;
import com.softsquared.mangoplate.src.ApplicationClass;
import com.softsquared.mangoplate.src.main.MainActivity;

public class FacebookLoginCallback implements FacebookCallback<LoginResult> {
    private Activity activity;

    public FacebookLoginCallback(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        String accessToken = AccessToken.getCurrentAccessToken().getToken();
        Log.d(ApplicationClass.TAG, "accessToken: " + accessToken);

        GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                (object, response) -> Log.d(ApplicationClass.TAG, "onCompleted(). JSONObject: " + object.toString()));

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender,birthday");
        graphRequest.setParameters(parameters);
        graphRequest.executeAsync();

        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    public void onCancel() {
    }

    @Override
    public void onError(FacebookException error) {
        Log.e(ApplicationClass.TAG, "FacebookLoginCallback::onError(): " + error.getMessage());
    }
}
