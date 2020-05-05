package com.softsquared.mangoplate.src.main.restaurant_detail;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.ApplicationClass;
import com.softsquared.mangoplate.src.main.restaurant_detail.interfaces.IRestaurantDetailActivityView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class RestaurantDetailService {
    private final String NAVER_MAP_BASE_URI = "https://naveropenapi.apigw.ntruss.com";
    private final String NAVER_MAP_STATIC_MAP_GET_URI = "/map-static/v2/raster";
    private final String NAVER_API_HEADER_NAME_CLIENT_ID = "X-NCP-APIGW-API-KEY-ID";
    private final String NAVER_API_HEADER_NAME_CLIENT_SECRET = "X-NCP-APIGW-API-KEY";
    private final Context context;
    private final IRestaurantDetailActivityView restaurantDetailActivityView;
    private OkHttpClient okHttpClient = new OkHttpClient();

    RestaurantDetailService(IRestaurantDetailActivityView restaurantDetailActivityView, Context context) {
        this.restaurantDetailActivityView = restaurantDetailActivityView;
        this.context = context;
    }

    void requestMapImage(int width, int height, int level, double longitude, double latitude) {
        String customMarkerImgUrl = "https://i.imgur.com/M9nq81b.png";
        String markersQuery = "type:e|icon:" + customMarkerImgUrl + "|pos:" + longitude + "%20" + latitude;
        String query = "?w=" + width + "&h=" + height + "&scale=2&level=" + level
                + "&center=" + longitude + "," + latitude + "&markers=" + markersQuery;
        Log.d(ApplicationClass.TAG, "request: " + NAVER_MAP_BASE_URI + NAVER_MAP_STATIC_MAP_GET_URI + query);
        Log.d(ApplicationClass.TAG, "NAVER_API_HEADER_NAME_CLIENT_ID: " + context.getResources().getString(R.string.naver_client_id));
        Log.d(ApplicationClass.TAG, "NAVER_API_HEADER_NAME_CLIENT_SECRET: " + context.getResources().getString(R.string.naver_client_secret));
        okHttpClient.newCall(new Request.Builder()
                .addHeader(NAVER_API_HEADER_NAME_CLIENT_ID,
                        context.getResources().getString(R.string.naver_client_id))
                .addHeader(NAVER_API_HEADER_NAME_CLIENT_SECRET,
                        context.getResources().getString(R.string.naver_client_secret))
                .url(NAVER_MAP_BASE_URI + NAVER_MAP_STATIC_MAP_GET_URI + query)
                .build())
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        restaurantDetailActivityView.validateFailure(e);
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) {
                        if(response.body() == null) {
                            restaurantDetailActivityView.validateFailure(null);
                            return;
                        }

                        restaurantDetailActivityView.validateSuccess(
                                BitmapFactory.decodeStream(Objects.requireNonNull(Objects.requireNonNull(response).body()).byteStream()));
                    }
                });
    }
}
