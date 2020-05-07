package com.softsquared.mangoplate.src.main.tab_search_restaurant;

import android.util.Log;

import com.softsquared.mangoplate.src.main.tab_search_restaurant.interfaces.SearchRestaurantFragmentView;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.interfaces.SearchRestaurantRetrofitInterface;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.models.BannerAdsResponse;

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
}
