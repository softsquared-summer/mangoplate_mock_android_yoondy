package com.softsquared.mangoplate.src.main.tab_search_restaurant.interfaces;

import com.softsquared.mangoplate.src.main.tab_search_restaurant.models.BannerAdInfo;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.models.RestaurantInfo;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.models.WishInfo;

import java.util.ArrayList;

public interface SearchRestaurantFragmentView {
    void onSuccessGetBannerAd(ArrayList<BannerAdInfo> bannerAdInfoList);

    void onFailureGetBannerAd();

    void onSuccessGetRestaurantList(ArrayList<RestaurantInfo> restaurantInfoList);

    void onFailureGetRestaurantList();

    void onSuccessPostWish(WishInfo wishInfo);

    void onFailurePostWish();
}
