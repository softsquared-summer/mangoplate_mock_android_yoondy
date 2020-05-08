package com.softsquared.mangoplate.src.main.restaurant_detail;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.main.restaurant_detail.interfaces.RestaurantDetailActivityView;
import com.softsquared.mangoplate.src.main.restaurant_detail.interfaces.RestaurantDetailRetrofitInterface;
import com.softsquared.mangoplate.src.main.restaurant_detail.models.RestaurantDetailResponse;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.softsquared.mangoplate.src.ApplicationClass.TAG;
import static com.softsquared.mangoplate.src.ApplicationClass.getRetrofit;

class RestaurantDetailService {
    private final String NAVER_MAP_BASE_URI = "https://naveropenapi.apigw.ntruss.com";
    private final String NAVER_MAP_STATIC_MAP_GET_URI = "/map-static/v2/raster";
    private final String NAVER_API_HEADER_NAME_CLIENT_ID = "X-NCP-APIGW-API-KEY-ID";
    private final String NAVER_API_HEADER_NAME_CLIENT_SECRET = "X-NCP-APIGW-API-KEY";
    private final Context context;
    private final RestaurantDetailActivityView restaurantDetailActivityView;
    private OkHttpClient okHttpClient = new OkHttpClient();

    RestaurantDetailService(RestaurantDetailActivityView restaurantDetailActivityView, Context context) {
        this.restaurantDetailActivityView = restaurantDetailActivityView;
        this.context = context;
    }

    void requestMapImage(int width, int height, int level, double longitude, double latitude) {
        String customMarkerImgUrl = "https://i.imgur.com/M9nq81b.png";
        String markersQuery = "type:e|icon:" + customMarkerImgUrl + "|pos:" + longitude + "%20" + latitude;
        String query = "?w=" + width + "&h=" + height + "&scale=2&level=" + level
                + "&center=" + longitude + "," + latitude + "&markers=" + markersQuery;
        Log.d(TAG, "request: " + NAVER_MAP_BASE_URI + NAVER_MAP_STATIC_MAP_GET_URI + query);
        Log.d(TAG, "NAVER_API_HEADER_NAME_CLIENT_ID: " + context.getResources().getString(R.string.naver_client_id));
        Log.d(TAG, "NAVER_API_HEADER_NAME_CLIENT_SECRET: " + context.getResources().getString(R.string.naver_client_secret));
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
                        restaurantDetailActivityView.onFailureGetNaverMap(e);
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) {
                        if(response.body() == null) {
                            restaurantDetailActivityView.onFailureGetNaverMap(null);
                            return;
                        }

                        restaurantDetailActivityView.onSuccessGetNaverMap(
                                BitmapFactory.decodeStream(Objects.requireNonNull(Objects.requireNonNull(response).body()).byteStream()));
                    }
                });
    }

    void getRestaurantDetail(int restaurantId) {
        final RestaurantDetailRetrofitInterface restaurantDetailRetrofitInterface = getRetrofit().create(RestaurantDetailRetrofitInterface.class);
        restaurantDetailRetrofitInterface.getRestaurantDetail(restaurantId).enqueue(new retrofit2.Callback<RestaurantDetailResponse>() {
            @Override
            public void onResponse(retrofit2.Call<RestaurantDetailResponse> call, retrofit2.Response<RestaurantDetailResponse> response) {
                RestaurantDetailResponse restaurantDetailResponse = response.body();
                if(restaurantDetailResponse == null) {
                    Log.d(TAG, "RestaurantDetailService::getRestaurantDetail() fail. restaurantDetailResponse is null");
                    restaurantDetailActivityView.onFailureGetRestaurantDetail();
                    return;
                }
                else if(!restaurantDetailResponse.isSuccess()) {
                    Log.d(TAG, "RestaurantDetailService::getRestaurantDetail() fail. restaurantDetailResponse code: " + restaurantDetailResponse.getCode());
                    Log.d(TAG, "RestaurantDetailService::getRestaurantDetail() fail. restaurantDetailResponse message: " + restaurantDetailResponse.getMessage());
                    restaurantDetailActivityView.onFailureGetRestaurantDetail();
                    return;}

                restaurantDetailActivityView.onSuccessGetRestaurantDetail(restaurantDetailResponse.getResult());
            }

            @Override
            public void onFailure(retrofit2.Call<RestaurantDetailResponse> call, Throwable t) {
                Log.d(TAG, "RestaurantDetailService::getRestaurantDetail() fail : " + t);
                restaurantDetailActivityView.onFailureGetRestaurantDetail();
            }
        });
    }
}
