package com.softsquared.mangoplate.src.login.models;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.auth.authorization.accesstoken.AccessToken;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.usermgmt.response.model.Profile;
import com.kakao.usermgmt.response.model.UserAccount;
import com.kakao.util.OptionalBoolean;
import com.kakao.util.exception.KakaoException;
import com.softsquared.mangoplate.src.ApplicationClass;
import com.softsquared.mangoplate.src.login.LoginActivity;
import com.softsquared.mangoplate.src.login.LoginService;
import com.softsquared.mangoplate.src.main.MainActivity;

public class KakaoLoginCallback implements ISessionCallback {
    private Activity activity;

    public KakaoLoginCallback(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onSessionOpened() {
        UserManagement.getInstance().me(new MeV2ResponseCallback() {
            @Override
            public void onSessionClosed(ErrorResult errorResult) {
                Log.e(ApplicationClass.TAG, "Kakao Login onSessionClosed(): " + errorResult);
            }

            @Override
            public void onFailure(ErrorResult errorResult) {
                super.onFailure(errorResult);
                Log.e(ApplicationClass.TAG, "Kakao Login onFailure(): " + errorResult);
            }

            @Override
            public void onSuccess(MeV2Response result) {
                AccessToken accessToken = Session.getCurrentSession().getTokenInfo();
                Log.d(ApplicationClass.TAG, "Kakao Login access token: " + accessToken.getAccessToken());
                if(activity instanceof LoginActivity) {
                    final LoginService loginService = new LoginService((LoginActivity)activity);
                    loginService.login("kakao", accessToken.getAccessToken());
                }

                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
                activity.finish();

                Log.d(ApplicationClass.TAG, "Kakao Login onSuccess(): " + result);

                UserAccount kakaoAccount = result.getKakaoAccount();
                String email = kakaoAccount.getEmail();
                if (email != null) {
                    Log.i(ApplicationClass.TAG, "Kakao Login email: " + email);
                }
                else if (kakaoAccount.emailNeedsAgreement() == OptionalBoolean.TRUE) {
                    // can get email after request agreement
                    // have to request only when you need
                }
                else {
                    // cant' get email
                }

                Profile profile = kakaoAccount.getProfile();
                if (profile != null) {
                    Log.d(ApplicationClass.TAG, "Kakao Login nickname: " + profile.getNickname());
                    Log.d(ApplicationClass.TAG, "Kakao Login profile image: " + profile.getProfileImageUrl());
                    Log.d(ApplicationClass.TAG, "Kakao Login thumbnail image: " + profile.getThumbnailImageUrl());
                }
                else if (kakaoAccount.profileNeedsAgreement() == OptionalBoolean.TRUE) {
                    // can get profile after request agreement
                }
                else {
                    // can't get profile
                }
            }
        });
    }

    @Override
    public void onSessionOpenFailed(KakaoException exception) {
        Log.e(ApplicationClass.TAG, "KakaoLoginCallback::onSessionOpenFailed(): " + exception.getMessage());
    }
}
