package com.softsquared.mangoplate.src.main;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.ApplicationClass;
import com.softsquared.mangoplate.src.BaseActivity;
import com.softsquared.mangoplate.src.gps.GpsService;
import com.softsquared.mangoplate.src.main.interfaces.MainActivityView;
import com.softsquared.mangoplate.src.main.models.UserInfo;

import it.sephiroth.android.library.viewrevealanimator.ViewRevealAnimator;

import static com.softsquared.mangoplate.src.ApplicationClass.TAG;
import static com.softsquared.mangoplate.src.ApplicationClass.sSharedPreferences;

public class MainActivity extends BaseActivity implements MainActivityView {
    public ViewPager2 vp2MainScreen;

    final MainService mainService = new MainService(this);
    private ViewRevealAnimator viewRevealAnimator;
    private BottomNavigationView botNav;
    private FloatingActionButton btnOpenAddition, btnCloseAddition;
    private AlphaAnimation halfFadeIn, halfFadeOut, fadeIn;
    private RotateAnimation rotateCw, rotateCcw;
    private TranslateAnimation riseUp;
    private ImageView ivIcGoToEatDeal, ivIcIndicateRestaurant, ivIcWriteReview, ivIcAddRestaurant;
    private TextView tvGoToEatDeal, tvIndicateRestaurant, tvWriteReview, tvAddRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setVp2();
        initView();

        mainService.getMyInfo();

        // TODO: test code. erase later
        GpsService gpsService = new GpsService(getApplicationContext());
        Log.d(TAG, "gps1: " + gpsService.getLongitude() + ", " + gpsService.getLatitude());
    }

    private void setVp2() {
        MainFragmentStateAdapter vp2MainScreenAdapter = new MainFragmentStateAdapter(this, 4);
        vp2MainScreen = findViewById(R.id.main_vp2_main_screen);
        vp2MainScreen.setAdapter(vp2MainScreenAdapter);
        vp2MainScreen.setOnTouchListener((v, event) -> {
            vp2MainScreen.setUserInputEnabled(true);
            v.performClick();
            return false;
        });
        vp2MainScreen.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 0) botNav.setSelectedItemId(R.id.item_search_restaurant);
                else if (position == 1) botNav.setSelectedItemId(R.id.item_discount);
                else if (position == 2) botNav.setSelectedItemId(R.id.item_timeline);
                else if (position == 3) botNav.setSelectedItemId(R.id.item_my_info);
            }
        });
    }

    private void initView() {
        botNav = findViewById(R.id.main_bot_nav);
        botNav.setOnNavigationItemSelectedListener(new ItemSelectedListener());
        botNav.setItemIconTintList(null);

        viewRevealAnimator = findViewById(R.id.main_anim_center_btn_animator);

        btnOpenAddition = findViewById(R.id.main_btn_addition);
        btnCloseAddition = findViewById(R.id.main_btn_close_addition);

        fadeIn = new AlphaAnimation(0.0f, 1.0f);
        halfFadeIn = new AlphaAnimation(0.5f, 1.0f);
        halfFadeOut = new AlphaAnimation(1.0f, 0.5f);
        rotateCw = new RotateAnimation(-45, 0, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateCcw = new RotateAnimation(0, -45, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        riseUp = new TranslateAnimation(0, 0, 200, 0);

        fadeIn.setDuration(600);
        halfFadeIn.setDuration(400);
        halfFadeOut.setDuration(400);
        rotateCw.setDuration(400);
        rotateCcw.setDuration(400);
        riseUp.setDuration(600);

        btnCloseAddition.setOnClickListener(v -> {
            btnOpenAddition.startAnimation(halfFadeIn);

            AnimationSet closeAnimationSet = new AnimationSet(true);
            closeAnimationSet.addAnimation(halfFadeOut);
            closeAnimationSet.addAnimation(rotateCcw);
            btnCloseAddition.startAnimation(closeAnimationSet);

            viewRevealAnimator.setDisplayedChild(
                    viewRevealAnimator.getDisplayedChild() + 1,
                    true,
                    new Point(ApplicationClass.getScreenWidth() / 2,
                            ApplicationClass.getScreenHeight() - botNav.getHeight())
            );
        });

        ivIcGoToEatDeal = findViewById(R.id.main_iv_ic_go_to_eat_deal);
        ivIcIndicateRestaurant = findViewById(R.id.main_iv_ic_indicate_restaurant);
        ivIcWriteReview = findViewById(R.id.main_iv_ic_write_review);
        ivIcAddRestaurant = findViewById(R.id.main_iv_ic_add_restaurant);
        tvGoToEatDeal = findViewById(R.id.main_tv_go_to_eat_deal);
        tvIndicateRestaurant = findViewById(R.id.main_tv_indicate_restaurant);
        tvWriteReview = findViewById(R.id.main_tv_write_review);
        tvAddRestaurant = findViewById(R.id.main_tv_add_restaurant);
    }

    private class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.item_search_restaurant: {
                    vp2MainScreen.setCurrentItem(0);
                    break;
                }
                case R.id.item_discount: {
                    vp2MainScreen.setCurrentItem(1);
                    break;
                }
                case R.id.item_addition: {
                    btnOpenAddition.startAnimation(halfFadeOut);
//test
                    GpsService gpsService = new GpsService(getApplicationContext());
                    Log.d(TAG, "gps2: " + gpsService.getLongitude() + ", " + gpsService.getLatitude());


                    AnimationSet openAnimationSet = new AnimationSet(true);
                    openAnimationSet.addAnimation(halfFadeIn);
                    openAnimationSet.addAnimation(rotateCw);
                    btnCloseAddition.startAnimation(openAnimationSet);

                    viewRevealAnimator.setDisplayedChild(
                            viewRevealAnimator.getDisplayedChild() + 1,
                            true,
                            new Point(ApplicationClass.getScreenWidth() / 2,
                                    ApplicationClass.getScreenHeight() - botNav.getHeight())
                    );

                    AnimationSet appearAnimation = new AnimationSet(true);
                    appearAnimation.addAnimation(fadeIn);
                    appearAnimation.addAnimation(riseUp);

                    ivIcGoToEatDeal.startAnimation(appearAnimation);
                    tvGoToEatDeal.startAnimation(appearAnimation);
                    ivIcIndicateRestaurant.startAnimation(appearAnimation);
                    tvIndicateRestaurant.startAnimation(appearAnimation);
                    ivIcWriteReview.startAnimation(appearAnimation);
                    tvWriteReview.startAnimation(appearAnimation);
                    ivIcAddRestaurant.startAnimation(appearAnimation);
                    tvAddRestaurant.startAnimation(appearAnimation);

                    break;
                }
                case R.id.item_timeline: {
                    vp2MainScreen.setCurrentItem(2);
                    break;
                }
                case R.id.item_my_info: {
                    vp2MainScreen.setCurrentItem(3);
                    break;
                }
            }
            return true;
        }
    }

    @Override
    public void onSuccessGetUser(UserInfo myInfo) {
        Log.d(TAG, "name: " + myInfo.getName());
        Log.d(TAG, "email: " + myInfo.getEmail());
        Log.d(TAG, "phone: " + myInfo.getPhone());
        Log.d(TAG, "profileUrl: " + myInfo.getProfileUrl());

        SharedPreferences.Editor editor= sSharedPreferences.edit();
        editor.putString("name", myInfo.getName());
        editor.putString("email", myInfo.getEmail());
        editor.putString("phone", myInfo.getPhone());
        editor.putString("profileUrl", myInfo.getProfileUrl());
        editor.apply();
    }

    @Override
    public void onFailureGetUser() {
        Log.d(TAG, "MainActivity::onFailureGetUser()");
    }
}