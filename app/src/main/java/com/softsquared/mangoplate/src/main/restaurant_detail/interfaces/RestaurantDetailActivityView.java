package com.softsquared.mangoplate.src.main.restaurant_detail.interfaces;

import android.graphics.Bitmap;

import java.io.IOException;

public interface RestaurantDetailActivityView {
    void onSuccessGetNaverMap(Bitmap bitmap);

    void onFailureGetNaverMap(IOException e);
}
