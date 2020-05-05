package com.softsquared.mangoplate.src.main.restaurant_detail.interfaces;

import android.graphics.Bitmap;

import java.io.IOException;

public interface IRestaurantDetailActivityView {
    void validateSuccess(Bitmap bitmap);

    void validateFailure(IOException e);
}
