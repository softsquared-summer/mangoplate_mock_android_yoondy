package com.softsquared.mangoplate.src.login;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.softsquared.mangoplate.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private ImageView slide0, slide1, lastSlide;
    private ArrayList<Integer> bgImgIdList;
    private Handler timerHandler = new Handler();
    private int bgImgIdListIdx = 0;
    private boolean isMovedToRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setView();
    }

    private void setView() {
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
}
