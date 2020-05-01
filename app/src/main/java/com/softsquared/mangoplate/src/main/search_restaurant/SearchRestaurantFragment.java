package com.softsquared.mangoplate.src.main.search_restaurant;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.ApplicationClass;
import com.softsquared.mangoplate.src.main.MainActivity;
import com.softsquared.mangoplate.src.main.search_restaurant.models.BannerAdInfo;
import com.softsquared.mangoplate.src.main.search_restaurant.models.BannerAdsVp2Adapter;
import com.softsquared.mangoplate.src.main.search_restaurant.models.RestaurantInfo;
import com.softsquared.mangoplate.src.main.search_restaurant.models.RestaurantListRvAdapter;

import java.util.Timer;
import java.util.TimerTask;

public class SearchRestaurantFragment extends Fragment {
    private ViewPager2 vp2BannerAds;
    private BannerAdsVp2Adapter vp2BannerAdsAdapter;
    private TimerTask setNextBannerAd;

    public SearchRestaurantFragment() {
        // Required empty public constructor
    }

    public static SearchRestaurantFragment newInstance() {
        return new SearchRestaurantFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_restaurant, container, false);

        setVp2BannerAds(view);

        RecyclerView rvRestaurantList = view.findViewById(R.id.sch_rest_rv_restaurants_list);
        rvRestaurantList.setHasFixedSize(true);
        rvRestaurantList.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        RestaurantListRvAdapter rvRestaurantListAdapter = new RestaurantListRvAdapter();
        rvRestaurantList.setAdapter(rvRestaurantListAdapter);

        // TODO: test. It must be removed later.
        addToRvAdapter(rvRestaurantListAdapter);

        return view;
    }

    private void setVp2BannerAds(View view) {
        vp2BannerAdsAdapter = new BannerAdsVp2Adapter();
        vp2BannerAds = view.findViewById(R.id.sch_rest_vp2_banner_ads);
        vp2BannerAds.setAdapter(vp2BannerAdsAdapter);

        // TODO: test. It must be removed later.
        addToVp2Adapter(vp2BannerAdsAdapter);

        vp2BannerAds.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                MainActivity mainActivity = (MainActivity) getActivity();
                if(mainActivity != null)
                    mainActivity.vp2MainScreen.setUserInputEnabled(true);
            }
        });

        // Indicator
        TabLayout tabLayout = view.findViewById(R.id.sch_rest_tab_layout_banner_ads_indicator);
        new TabLayoutMediator(tabLayout, vp2BannerAds, (tab, position) -> tab.select()).attach();

        setCirculateBannerAds();
    }

    private void setCirculateBannerAds() {
        setNextBannerAd = new TimerTask() {
            @Override
            public void run() {
                int index = vp2BannerAds.getCurrentItem();
                int adsCount = vp2BannerAdsAdapter.getItemCount();
                int nextIndex = (index + 1) % adsCount;
                new Handler(Looper.getMainLooper())
                        .post(() -> vp2BannerAds.setCurrentItem(nextIndex));
            }
        };
        new Timer().schedule(setNextBannerAd, 8000, 8000);
    }

    // TODO: test. It must be removed later.
    private void addToRvAdapter(RestaurantListRvAdapter rvRestaurantListAdapter) {
        RestaurantInfo info0 = new RestaurantInfo(
                "https://i.imgur.com/OSupQAB.jpg",
                "1. 시키카츠",
                "동대문구",
                "2.95 Km",
                8945,
                14,
                4.7f,
                false
        );
        rvRestaurantListAdapter.addRestaurantInfo(info0);
        RestaurantInfo info1 = new RestaurantInfo(
                "https://i.imgur.com/Im86J1J.jpg",
                "2. 오관스시",
                "동대문구",
                "2.83 Km",
                75394,
                57,
                4.3f,
                false
        );
        rvRestaurantListAdapter.addRestaurantInfo(info1);
        RestaurantInfo info2 = new RestaurantInfo(
                "https://i.imgur.com/nwe2QFd.jpg",
                "3. 회기왕족발보쌈",
                "동대문구",
                "2.54 Km",
                147224,
                103,
                4.3f,
                false
        );
        rvRestaurantListAdapter.addRestaurantInfo(info2);
        RestaurantInfo info3 = new RestaurantInfo(
                "https://i.imgur.com/7Lj7d86.jpg",
                "4. 이문동커피집",
                "동대문구",
                "2.12 Km",
                30293,
                33,
                2.7f,
                true
        );
        rvRestaurantListAdapter.addRestaurantInfo(info3);
    }

    // TODO: test. It must be removed later.
    private void addToVp2Adapter(BannerAdsVp2Adapter vp2Adapter) {
        BannerAdInfo info0 = new BannerAdInfo();
        info0.setImageUrl("https://i.imgur.com/zD38cF0.png");
        vp2Adapter.addBannerAdInfo(info0);
        BannerAdInfo info1 = new BannerAdInfo();
        info1.setImageUrl("https://i.imgur.com/Ve8w2qi.png");
        vp2Adapter.addBannerAdInfo(info1);
        BannerAdInfo info2 = new BannerAdInfo();
        info2.setImageUrl("https://i.imgur.com/OnMuBL5.png");
        vp2Adapter.addBannerAdInfo(info2);
        BannerAdInfo info3 = new BannerAdInfo();
        info3.setImageUrl("https://i.imgur.com/t1reWoz.png");
        vp2Adapter.addBannerAdInfo(info3);
        BannerAdInfo info4 = new BannerAdInfo();
        info4.setImageUrl("https://i.imgur.com/Es0wA2R.png");
        vp2Adapter.addBannerAdInfo(info4);
    }

    @Override
    public void onPause() {
        super.onPause();
        setNextBannerAd.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();
        setNextBannerAd.run();
    }
}