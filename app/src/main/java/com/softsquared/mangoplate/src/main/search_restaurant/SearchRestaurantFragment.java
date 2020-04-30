package com.softsquared.mangoplate.src.main.search_restaurant;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.main.MainActivity;
import com.softsquared.mangoplate.src.main.search_restaurant.models.BannerAdInfo;
import com.softsquared.mangoplate.src.main.search_restaurant.models.BannerAdsVp2Adapter;

import java.util.Timer;
import java.util.TimerTask;

import static com.softsquared.mangoplate.src.ApplicationClass.TAG;

public class SearchRestaurantFragment extends Fragment {
    private ViewPager2 vp2BannerAds;
    private BannerAdsVp2Adapter vp2Adapter;
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

        return view;
    }

    private void setVp2BannerAds(View view) {
        vp2Adapter = new BannerAdsVp2Adapter();
        vp2BannerAds = view.findViewById(R.id.sch_rest_vp2_banner_ads);
        vp2BannerAds.setAdapter(vp2Adapter);

        // TODO: test. It must be removed later.
        addToVp2Adapter(vp2Adapter);

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
                int adsCount = vp2Adapter.getItemCount();
                int nextIndex = (index + 1) % adsCount;
                Log.d(TAG, "tt index: " + nextIndex);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(() -> vp2BannerAds.setCurrentItem(nextIndex));
            }
        };
        Timer timer = new Timer();
        timer.schedule(setNextBannerAd, 8000, 8000);
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
