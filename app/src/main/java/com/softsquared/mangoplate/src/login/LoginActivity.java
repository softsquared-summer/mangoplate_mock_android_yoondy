package com.softsquared.mangoplate.src.login;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginManager;
import com.kakao.auth.AuthType;
import com.kakao.auth.Session;
import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.BaseActivity;
import com.softsquared.mangoplate.src.login.interfaces.LoginActivityView;
import com.softsquared.mangoplate.src.login.models.LoginInfo;
import com.softsquared.mangoplate.src.main.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;

import static com.softsquared.mangoplate.src.ApplicationClass.TAG;
import static com.softsquared.mangoplate.src.ApplicationClass.X_ACCESS_TOKEN;
import static com.softsquared.mangoplate.src.ApplicationClass.sSharedPreferences;

public class LoginActivity extends BaseActivity implements LoginActivityView {
    private ImageView slide0, slide1, lastSlide;
    private ArrayList<Integer> bgImgIdList;
    private Handler timerHandler = new Handler();
    private int bgImgIdListIdx = 0;
    private boolean isMovedToRight;

    private CallbackManager facebookCallbackManager;
    private KakaoLoginCallback kakaoLoginCallback = new KakaoLoginCallback(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setView();
        setFacebookLogin();
        setKakaoLogin();
    }

    private void setView() {
        Intent intent = new Intent(this, MainActivity.class);

        // we didn't decide to implement login skip.
        /*
        TextView tvSkip = findViewById(R.id.login_tv_skip);
        tvSkip.setOnClickListener(v -> startActivity(intent));
         */

        bgImgIdList = new ArrayList<>();
        slide0 = findViewById(R.id.login_iv_bg_slide0);
        slide1 = findViewById(R.id.login_iv_bg_slide1);
        slide0.setColorFilter(getResources().getColor(R.color.gray), PorterDuff.Mode.MULTIPLY);
        slide1.setColorFilter(getResources().getColor(R.color.gray), PorterDuff.Mode.MULTIPLY);

        bgImgIdList.add(R.drawable.login_bg_1);
        bgImgIdList.add(R.drawable.login_bg_2);
        bgImgIdList.add(R.drawable.login_bg_3);

        slide0.setImageResource(bgImgIdList.get(bgImgIdListIdx));
        bgImgIdListIdx = (bgImgIdListIdx + 1) % bgImgIdList.size();
        slide0.startAnimation(moveRightAnim());
        isMovedToRight = true;
        lastSlide = slide0;

        timerHandler.postDelayed(timer, 6500);
    }

    private Runnable timer = new Runnable() {
        public void run() {
            AnimationSet anims = isMovedToRight ? moveLeftAnim() : moveRightAnim();
            isMovedToRight = !isMovedToRight;

            if (lastSlide == slide0) {
                slide1.setImageResource(bgImgIdList.get(bgImgIdListIdx));
                slide1.startAnimation(anims);
                lastSlide = slide1;
            }
            else {
                slide0.setImageResource(bgImgIdList.get(bgImgIdListIdx));
                slide0.startAnimation(anims);
                lastSlide = slide0;
            }
            bgImgIdListIdx = (bgImgIdListIdx + 1) % bgImgIdList.size();
            timerHandler.postDelayed(timer, 6500);
        }
    };

    private AnimationSet moveRightAnim() {
        TranslateAnimation moveRight = new TranslateAnimation(0, 50, 0, 0);
        moveRight.setDuration(7000);

        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(500);

        AlphaAnimation showing = new AlphaAnimation(1.0f, 1.0f);
        showing.setStartOffset(500);
        showing.setDuration(6000);

        AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setStartOffset(6500);
        fadeOut.setDuration(500);

        AlphaAnimation hiding = new AlphaAnimation(1.0f, 1.0f);
        showing.setStartOffset(7000);
        showing.setDuration(6000);

        AnimationSet anims = new AnimationSet(true);
        anims.addAnimation(moveRight);
        anims.addAnimation(fadeIn);
        anims.addAnimation(showing);
        anims.addAnimation(fadeOut);
        anims.addAnimation(hiding);

        return anims;
    }

    private AnimationSet moveLeftAnim() {
        TranslateAnimation moveLeft = new TranslateAnimation(50, 0, 0, 0);
        moveLeft.setDuration(7000);

        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(500);

        AlphaAnimation showing = new AlphaAnimation(1.0f, 1.0f);
        showing.setStartOffset(500);
        showing.setDuration(6000);

        AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setStartOffset(6500);
        fadeOut.setDuration(500);

        AlphaAnimation hiding = new AlphaAnimation(1.0f, 1.0f);
        showing.setStartOffset(7000);
        showing.setDuration(6000);

        AnimationSet anims = new AnimationSet(true);
        anims.addAnimation(moveLeft);
        anims.addAnimation(fadeIn);
        anims.addAnimation(showing);
        anims.addAnimation(fadeOut);
        anims.addAnimation(hiding);

        return anims;
    }

    private void setFacebookLogin() {
        facebookCallbackManager = CallbackManager.Factory.create();
        FacebookCallback<com.facebook.login.LoginResult> facebookCallback = new FacebookLoginCallback(this);
        ImageView btnFacebook = findViewById(R.id.login_iv_facebook_btn);
        btnFacebook.setOnClickListener(v -> {
            LoginManager loginManager = LoginManager.getInstance();
            loginManager.logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "email"));
            loginManager.registerCallback(facebookCallbackManager, facebookCallback);
        });
    }

    private void setKakaoLogin() {
        Session kakaoSession = Session.getCurrentSession();
        kakaoSession.addCallback(kakaoLoginCallback);
        ImageView btnKakao = findViewById(R.id.login_iv_kakaotalk_btn);
        btnKakao.setOnClickListener(v -> kakaoSession.open(AuthType.KAKAO_LOGIN_ALL, LoginActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        facebookCallbackManager.onActivityResult(requestCode, resultCode, data);

        // send to SDK the result of simple login by kakaoTalk|kakaoStory
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data))
            return;

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Session.getCurrentSession().removeCallback(kakaoLoginCallback);
    }

    @Override
    public void onSuccessLogin(LoginInfo loginInfo) {
        if(loginInfo == null) {
            Log.d(TAG, "LoginActivity::onSuccessLogin() loginInfo: null");
            return;
        }
        else Log.d(TAG, "LoginActivity::onSuccessLogin() loginInfo: " + loginInfo.toString());

        sSharedPreferences.edit().putString(X_ACCESS_TOKEN, loginInfo.getJwt()).apply();

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailureLogin() {
        Log.d(TAG, "LoginActivity::onFailureLogin()");
    }
}
