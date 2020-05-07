package com.softsquared.mangoplate.src.main.tab_search_restaurant;

import android.util.Log;

import com.softsquared.mangoplate.src.main.tab_search_restaurant.interfaces.SearchRestaurantFragmentView;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.interfaces.SearchRestaurantRetrofitInterface;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.models.BannerAdsResponse;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.models.RestaurantListResponse;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.models.WishResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.mangoplate.src.ApplicationClass.TAG;
import static com.softsquared.mangoplate.src.ApplicationClass.getRetrofit;

class SearchRestaurantService {
    private final SearchRestaurantFragmentView searchRestaurantFragmentView;

    SearchRestaurantService(SearchRestaurantFragmentView searchRestaurantFragmentView) {
        this.searchRestaurantFragmentView = searchRestaurantFragmentView;
    }

    // API 2-2
    void getBannerAd() {
        SearchRestaurantRetrofitInterface searchRestaurantRetrofitInterface = getRetrofit().create(SearchRestaurantRetrofitInterface.class);
        searchRestaurantRetrofitInterface.getBannerAd("main").enqueue(new Callback<BannerAdsResponse>() {
            @Override
            public void onResponse(@NotNull Call<BannerAdsResponse> call, @NotNull Response<BannerAdsResponse> response) {
                BannerAdsResponse bannerAdsResponse = response.body();
                if(bannerAdsResponse == null) {
                    Log.d(TAG, "SearchRestaurantService::getBannerAd() failure. bannerAdsResponse is null");
                    searchRestaurantFragmentView.onFailureGetBannerAd();
                    return;
                }
                else if(!bannerAdsResponse.isSuccess()) {
                    Log.d(TAG, "SearchRestaurantService::getBannerAd() failure. bannerAdsResponse code: " + bannerAdsResponse.getCode());
                    Log.d(TAG, "SearchRestaurantService::getBannerAd() failure. bannerAdsResponse message: " + bannerAdsResponse.getMessage());
                    searchRestaurantFragmentView.onFailureGetBannerAd();
                    return;
                }

                searchRestaurantFragmentView.onSuccessGetBannerAd(bannerAdsResponse.getResult());
            }

            @Override
            public void onFailure(@NotNull Call<BannerAdsResponse> call, @NotNull Throwable t) {
                Log.d(TAG, "SearchRestaurantService::getBannerAd() Failure: " + t);
                searchRestaurantFragmentView.onFailureGetBannerAd();
            }
        });
    }

    // API 4-1
    void getRestaurantList(float lat, float lng,
                           String area, String order, String category,
                           String kind, String price, String parking,
                           String radius, String page, String size) {
        Log.d(TAG, "SearchRestaurantService::getRestaurantList() lat: " + lat + ", lng: " + lng);
        Log.d(TAG, "SearchRestaurantService::getRestaurantList() area: " + area + ", order: " + order + ", category: " + category);
        Log.d(TAG, "SearchRestaurantService::getRestaurantList() kind: " + kind + ", price: " + price + ", parking: " + parking);
        Log.d(TAG, "SearchRestaurantService::getRestaurantList() radius: " + radius + ", page: " + page + ", size: " + size);

        SearchRestaurantRetrofitInterface searchRestaurantRetrofitInterface = getRetrofit().create(SearchRestaurantRetrofitInterface.class);
        searchRestaurantRetrofitInterface.getRestaurantList(
                lat, lng, "main", // Mandatory
                area, order, category,
                kind, price, parking,
                radius, page, size
        ).enqueue(new Callback<RestaurantListResponse>() {
            @Override
            public void onResponse(@NotNull Call<RestaurantListResponse> call, @NotNull Response<RestaurantListResponse> response) {
                RestaurantListResponse restaurantListResponse = response.body();
                if(restaurantListResponse == null) {
                    Log.d(TAG, "SearchRestaurantService::getRestaurantList() Failure. restaurantListResponse is null");
                    searchRestaurantFragmentView.onFailureGetRestaurantList();
                    return;
                }
                else if(!restaurantListResponse.isSuccess()) {
                    Log.d(TAG, "SearchRestaurantService::getRestaurantList() Failure. restaurantListResponse code: " + restaurantListResponse.getCode());
                    Log.d(TAG, "SearchRestaurantService::getRestaurantList() Failure. restaurantListResponse message: " + restaurantListResponse.getMessage());
                    searchRestaurantFragmentView.onFailureGetRestaurantList();
                    return;
                }

                searchRestaurantFragmentView.onSuccessGetRestaurantList(restaurantListResponse.getResult());
            }

            @Override
            public void onFailure(@NotNull Call<RestaurantListResponse> call, @NotNull Throwable t) {
                Log.d(TAG, "SearchRestaurantService::getRestaurantList() Failure: " + t);
                searchRestaurantFragmentView.onFailureGetRestaurantList();
            }
        });
    }

    // API 11-1
    void postWish(int restaurantId) {
        SearchRestaurantRetrofitInterface searchRestaurantRetrofitInterface = getRetrofit().create(SearchRestaurantRetrofitInterface.class);
        searchRestaurantRetrofitInterface.postWish(restaurantId).enqueue(new Callback<WishResponse>() {
            @Override
            public void onResponse(@NotNull Call<WishResponse> call, @NotNull Response<WishResponse> response) {
                WishResponse wishResponse = response.body();
                if(wishResponse == null) {
                    Log.d(TAG, "SearchRestaurantService::postWish() Failure. wishResponse is null");
                    searchRestaurantFragmentView.onFailurePostWish();
                    return;
                }
                else if(!wishResponse.isSuccess()) {
                    Log.d(TAG, "SearchRestaurantService::postWish() Failure. wishResponse code: " + wishResponse.getCode());
                    Log.d(TAG, "SearchRestaurantService::postWish() Failure. wishResponse message " + wishResponse.getMessage());
                    searchRestaurantFragmentView.onFailurePostWish();
                    return;
                }

                searchRestaurantFragmentView.onSuccessPostWish(wishResponse.getResult());
            }

            @Override
            public void onFailure(@NotNull Call<WishResponse> call, @NotNull Throwable t) {
                Log.d(TAG, "SearchRestaurantService::postWish() Failure: " + t);
                searchRestaurantFragmentView.onFailurePostWish();
            }
        });
    }
}
