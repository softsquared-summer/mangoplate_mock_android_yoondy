package com.softsquared.mangoplate.src.main.tab_search_restaurant;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.gps.GpsService;
import com.softsquared.mangoplate.src.main.MainActivity;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.interfaces.SearchRestaurantFragmentView;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.models.BannerAdInfo;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.models.RestaurantInfo;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.search.SearchActivity;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.SelectDistrictActivity;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_filter.SelectFilterActivity;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_radius.SelectRadiusActivity;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_sort_by.SelectSortByActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.softsquared.mangoplate.src.ApplicationClass.TAG;
import static com.softsquared.mangoplate.src.ApplicationClass.sSharedPreferences;

public class SearchRestaurantFragment extends Fragment implements SearchRestaurantFragmentView {
    private final String AREA = "Area";
    private final String ORDER = "order";
    private final String RADIUS = "radius";
    private final int REQUEST = 12345;
    private final int SEARCH = 1;
    private final int SELECT_AREA = 2;
    private final int SELECT_SORT_BY = 3;
    private final int SELECT_RADIUS = 4;
    private final int SELECT_FILTER = 5;
    private SearchRestaurantService searchRestaurantService;

    private GpsService gpsService;
    private MainActivity mainActivity;
    private boolean isFadeAnimActivity = false;
    private boolean isReadyToVp2BannerAds = false;
    private ViewPager2 vp2BannerAds;
    private RestaurantListRvAdapter rvRestaurantListAdapter;
    private BannerAdsVp2Adapter vp2BannerAdsAdapter;
    private TimerTask setNextBannerAd;

    private String sortBy, radius;
    private TextView tvSortBy, tvRadius, tvArea;

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

        Activity activity = getActivity();
        if(activity instanceof MainActivity)
            mainActivity = (MainActivity) activity;

        searchRestaurantService = new SearchRestaurantService(this);

        sortBy = sSharedPreferences.getString(ORDER, "");
        if(sortBy.isEmpty()) {
            sortBy = "평점순";
            sSharedPreferences.edit().putString(ORDER, sortBy).apply();
        }

//        radius = sSharedPreferences.getString(RADIUS, "");
//        if(radius.isEmpty()) {
            radius = "3";
            sSharedPreferences.edit().putString(RADIUS, radius).apply();
//        }

        setVp2BannerAds(view);
        setRvRestaurantList(view);
        setBtnSearch(view);
        setBtnMap(view);
        setBtnSelectArea(view);
        setBtnSelectSortBy(view);
        setBtnSelectRadius(view);
        setBtnSelectFilter(view);

        gpsService = new GpsService(getContext());
        updateRvRestaurantList(false);

        return view;
    }

    private void setVp2BannerAds(View view) {
        mainActivity.showProgressDialog();

        vp2BannerAdsAdapter = new BannerAdsVp2Adapter();
        vp2BannerAds = view.findViewById(R.id.sch_rest_vp2_banner_ads);
        vp2BannerAds.setAdapter(vp2BannerAdsAdapter);

        final SearchRestaurantService searchRestaurantService = new SearchRestaurantService(this);
        searchRestaurantService.getBannerAd();

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
    }

    private void setRvRestaurantList(View view) {
        RecyclerView rvRestaurantList = view.findViewById(R.id.sch_rest_rv_restaurants_list);
        rvRestaurantList.setHasFixedSize(true);
        rvRestaurantList.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        rvRestaurantListAdapter = new RestaurantListRvAdapter();
        rvRestaurantList.setAdapter(rvRestaurantListAdapter);
    }

    private void setBtnSearch(View view) {
        FrameLayout flSearch = view.findViewById(R.id.sch_rest_frame_layout_search);
        Intent intent = new Intent(getContext(), SearchActivity.class);
        flSearch.setOnClickListener(v -> {
            isFadeAnimActivity = false;
            startActivity(intent);
        });
    }

    private void setBtnMap(View view) {
        FrameLayout flMap = view.findViewById(R.id.sch_rest_frame_layout_map);
        flMap.setOnClickListener(v -> mainActivity.showCustomToast(getString(R.string.notify_not_prepared)));
    }

    private void setBtnSelectArea(View view) {
        tvArea = view.findViewById(R.id.sch_rest_tv_location);
        ConstraintLayout clSelectArea = view.findViewById(R.id.sch_rest_const_layout_location_watch_now);
        Intent intent = new Intent(getContext(), SelectDistrictActivity.class);
        GpsService gpsService = new GpsService(getContext());

        intent.putExtra("latitude", gpsService.getLatitude());
        intent.putExtra("longitude", gpsService.getLongitude());
        clSelectArea.setOnClickListener(v -> {
            isFadeAnimActivity = true;
            startActivityForResult(intent, REQUEST);
        });
    }

    private void setBtnSelectSortBy(View view) {
        tvSortBy = view.findViewById(R.id.sch_rest_tv_sort_by);
        tvSortBy.setText(sortBy.isEmpty() ? "-" : sortBy);

        isFadeAnimActivity = true;
        ConstraintLayout clSelectSortBy = view.findViewById(R.id.sch_rest_const_layout_sort_by);
        Intent intent = new Intent(getContext(), SelectSortByActivity.class);
        clSelectSortBy.setOnClickListener(v -> {
            isFadeAnimActivity = true;
            startActivityForResult(intent, REQUEST);
        });
    }

    private void setBtnSelectRadius(View view) {
        tvRadius = view.findViewById(R.id.sch_rest_tv_radius);
        String radiusText = "- km";
        if(radius.equals("0.5")) radiusText = "500m";
        else if(radius.equals("1")) radiusText = "1km";
        else if(radius.equals("3")) radiusText = "3km";
        tvRadius.setText(radiusText);

        isFadeAnimActivity = true;
        ConstraintLayout clSelectRadius = view.findViewById(R.id.sch_rest_const_layout_radius_btn);
        Intent intent = new Intent(getContext(), SelectRadiusActivity.class);
        clSelectRadius.setOnClickListener(v -> {
            isFadeAnimActivity = true;
            startActivityForResult(intent, REQUEST);
        });
    }

    private void setBtnSelectFilter(View view) {
        isFadeAnimActivity = true;
        ImageView ivSelectFilter = view.findViewById(R.id.sch_rest_iv_filter_btn);
        Intent intent = new Intent(getContext(), SelectFilterActivity.class);
        ivSelectFilter.setOnClickListener(v -> {
            isFadeAnimActivity = true;
            startActivityForResult(intent, REQUEST);
        });
    }

    private void setCirculateBannerAds() {
        setNextBannerAd = new TimerTask() {
            @Override
            public void run() {
                int index = vp2BannerAds.getCurrentItem();
                int adsCount = vp2BannerAdsAdapter.getItemCount();
                int nextIndex = (index + 1) % adsCount;
                new Handler(Looper.getMainLooper())
                        .postDelayed(() -> vp2BannerAds.setCurrentItem(nextIndex), 2000);
            }
        };
        new Timer().schedule(setNextBannerAd, 0, 2000);
    }

    private void updateRvRestaurantList(boolean areaMode) {
        Log.d(TAG, "lat: " + gpsService.getLatitude() + ", lng: " + gpsService.getLongitude());
        String order = sSharedPreferences.getString(ORDER, "");
        String radius = sSharedPreferences.getString(RADIUS, "");
        String area = sSharedPreferences.getString(AREA, "");
        searchRestaurantService.getRestaurantList(
                (float) gpsService.getLatitude(), (float) gpsService.getLongitude(),
                areaMode ? area.isEmpty() ? null : area : null,
                order.isEmpty() ? null : order,
                null,
                null, null, null,
                areaMode ? null : radius.isEmpty() ? null : radius,
                null, null
        );
        mainActivity.showProgressDialog();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST) {
            boolean areaMode = true;
            switch (resultCode) {
                case SELECT_AREA: {
                    if(data != null) {
                        ArrayList<String> areaList = data.getStringArrayListExtra(AREA);
                        if(areaList != null) {
                            if(areaList.size() > 0) {
                                String text = areaList.get(0);
                                if(areaList.size() > 1) text +=  " 외 " + (areaList.size() - 1) + " 곳";
                                tvArea.setText(text);
                                Log.d(TAG, "string joined: " + String.join(", ", areaList));
                                sSharedPreferences.edit().putString(AREA, String.join(", ", areaList)).apply();
                            }
                        }
                    }
                    break;
                }
                case SELECT_SORT_BY: {
                    if(data != null) {
                        String sortBy = data.getStringExtra(ORDER);
                        if(sortBy != null) {
                            sSharedPreferences.edit().putString(ORDER, sortBy).apply();
                            tvSortBy.setText(sortBy.isEmpty() ? "-" : sortBy);
                        }
                    }
                    break;
                }
                case SELECT_RADIUS: {
                    if(data != null) {
                        String radius = data.getStringExtra(RADIUS);
                        if(radius != null) {
                            areaMode = false;
                            sSharedPreferences.edit().putString(RADIUS, radius).apply();
                            String radiusText = "- km";
                            if(radius.equals("0.5")) radiusText = "500m";
                            else if(radius.equals("1")) radiusText = "1km";
                            else if(radius.equals("3")) radiusText = "3km";
                            tvRadius.setText(radiusText);
                        }
                    }
                    break;
                }
                case SELECT_FILTER: {
                    break;
                }
            }

            updateRvRestaurantList(areaMode);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        Activity activity = getActivity();
        if(isFadeAnimActivity && activity != null) {
            isFadeAnimActivity = false;
            activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

        if(setNextBannerAd != null)
            setNextBannerAd.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();

        if(isReadyToVp2BannerAds) {
            if (setNextBannerAd == null)
                setCirculateBannerAds();
            else
                setNextBannerAd.run();
        }
    }

    @Override
    public void onSuccessGetBannerAd(ArrayList<BannerAdInfo> bannerAdInfoList) {
        for(BannerAdInfo b : bannerAdInfoList)
            vp2BannerAdsAdapter.add(b);

        vp2BannerAdsAdapter.notifyDataSetChanged();
        isReadyToVp2BannerAds = true;
        setCirculateBannerAds();

        mainActivity.hideProgressDialog();
    }

    @Override
    public void onFailureGetBannerAd() {
        mainActivity.hideProgressDialog();
    }

    @Override
    public void onSuccessGetRestaurantList(ArrayList<RestaurantInfo> restaurantInfoList) {
        rvRestaurantListAdapter.clear();

        for(RestaurantInfo restaurantInfo : restaurantInfoList)
            rvRestaurantListAdapter.add(restaurantInfo);

        rvRestaurantListAdapter.notifyDataSetChanged();
        mainActivity.hideProgressDialog();
    }

    @Override
    public void onFailureGetRestaurantList() {
        mainActivity.hideProgressDialog();
    }
}
