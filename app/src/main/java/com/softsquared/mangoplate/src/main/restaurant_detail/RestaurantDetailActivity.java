package com.softsquared.mangoplate.src.main.restaurant_detail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amar.library.ui.StickyScrollView;
import com.amar.library.ui.interfaces.IScrollViewListener;
import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.ApplicationClass;
import com.softsquared.mangoplate.src.BaseActivity;
import com.softsquared.mangoplate.src.gps.GpsService;
import com.softsquared.mangoplate.src.main.restaurant_detail.interfaces.RestaurantDetailActivityView;
import com.softsquared.mangoplate.src.main.restaurant_detail.models.RestaurantDetailMenuInfo;
import com.softsquared.mangoplate.src.main.restaurant_detail.models.RestaurantDetailPhotoInfo;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.models.RestaurantInfo;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.RestaurantListRvAdapter;

import java.io.IOException;

public class RestaurantDetailActivity extends BaseActivity implements RestaurantDetailActivityView {
    final private RestaurantDetailService restaurantDetailService = new RestaurantDetailService(this, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        setTopBarAndStickyScrollView();
        setRestaurantBasicInfo();
        setMenuForRecord();
        setLocationInfo();
        setRestaurantServiceInfo();
        setRestaurantMenu();
        setPopularKeyword();
        setRestaurantReview();
        setRvPopularRestaurantNearby();
        setView();

        // TODO: test code. erase later
        GpsService gpsService = new GpsService(getApplicationContext());
        Log.d(ApplicationClass.TAG, "gps2: " + gpsService.getLongitude() + ", " + gpsService.getLatitude());
    }

    private void setTopBarAndStickyScrollView() {
        ConstraintLayout clTopBar = findViewById(R.id.restaurant_detail_const_layout_top_bar);
        ConstraintLayout clBackBtn = findViewById(R.id.restaurant_detail_const_layout_back_btn);
        ConstraintLayout clPhotoBtn = findViewById(R.id.restaurant_detail_const_layout_photo_btn);
        ConstraintLayout clShareBtn = findViewById(R.id.restaurant_detail_const_layout_share_btn);

        ImageView ivBackBtn = findViewById(R.id.restaurant_detail_iv_back_btn);
        ImageView ivPhotoBtn = findViewById(R.id.restaurant_detail_iv_photo_btn);
        ImageView ivShareBtn = findViewById(R.id.restaurant_detail_iv_share_btn);

        StickyScrollView stickyScrollView = findViewById(R.id.restaurant_detail_sticky_scroll_view);
        stickyScrollView.setScrollViewListener(new IScrollViewListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                if(t > oldt && stickyScrollView.isHeaderSticky()) { // scroll to bottom
                    clTopBar.setBackgroundColor(getResources().getColor(R.color.orange));
                    ivBackBtn.setColorFilter(getResources().getColor(android.R.color.white));
                    ivPhotoBtn.setColorFilter(getResources().getColor(android.R.color.white));
                    ivShareBtn.setColorFilter(getResources().getColor(android.R.color.white));
                }
                else if(t < oldt && !stickyScrollView.isHeaderSticky()) { // scroll to up
                    clTopBar.setBackgroundColor(getResources().getColor(android.R.color.white));
                    ivBackBtn.clearColorFilter();
                    ivBackBtn.clearColorFilter();
                    ivPhotoBtn.clearColorFilter();
                    ivShareBtn.clearColorFilter();
                }
            }

            @Override
            public void onScrollStopped(boolean isStoped) {
            }
        });

        clBackBtn.setOnClickListener(v -> finish());
        clPhotoBtn.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));
        clShareBtn.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));
    }

    private void setRestaurantBasicInfo() {
        setRvRestaurantDetailPhoto();

        ImageView ivRatingInfoBtn = findViewById(R.id.restaurant_detail_iv_rating_info_btn);
        ivRatingInfoBtn.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));
    }

    private void setRvRestaurantDetailPhoto() {
        RestaurantDetailPhotoAdapter restaurantDetailPhotoAdapter = new RestaurantDetailPhotoAdapter();
        RecyclerView rvRestaurantDetailPhoto = findViewById(R.id.restaurant_detail_rv_photos);
        rvRestaurantDetailPhoto.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rvRestaurantDetailPhoto.setAdapter(restaurantDetailPhotoAdapter);

        // TODO: test
        RestaurantDetailPhotoInfo info0 = new RestaurantDetailPhotoInfo(23, "https://i.imgur.com/OSupQAB.jpg");
        restaurantDetailPhotoAdapter.add(info0);
        RestaurantDetailPhotoInfo info1 = new RestaurantDetailPhotoInfo(24, "https://i.imgur.com/Im86J1J.jpg");
        restaurantDetailPhotoAdapter.add(info1);
        RestaurantDetailPhotoInfo info2 = new RestaurantDetailPhotoInfo(25, "https://i.imgur.com/nwe2QFd.jpg");
        restaurantDetailPhotoAdapter.add(info2);
        restaurantDetailPhotoAdapter.add(info0);
        restaurantDetailPhotoAdapter.add(info1);
    }

    private void setMenuForRecord() {
        ConstraintLayout clWish = findViewById(R.id.restaurant_detail_const_layout_wish);
        ConstraintLayout clGone = findViewById(R.id.restaurant_detail_const_layout_gone);
        ConstraintLayout clWriteReview = findViewById(R.id.restaurant_detail_const_layout_write_review);
        ConstraintLayout clMyList = findViewById(R.id.restaurant_detail_const_layout_my_list);

        clWish.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));
        clGone.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));
        clWriteReview.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));
        clMyList.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));
    }

    private void setLocationInfo() {
        ConstraintLayout clMap = findViewById(R.id.restaurant_detail_const_layout_map);
        ConstraintLayout clPathFind = findViewById(R.id.restaurant_detail_const_layout_path_find);
        ConstraintLayout clNavigation = findViewById(R.id.restaurant_detail_const_layout_navigation);
        ConstraintLayout clCallTaxi = findViewById(R.id.restaurant_detail_const_layout_call_taxi);
        ConstraintLayout clCopyAddress = findViewById(R.id.restaurant_detail_const_layout_copy_address);

        clMap.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));
        clPathFind.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));
        clNavigation.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));
        clCallTaxi.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));
        clCopyAddress.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));

        // TODO: test. It must be removed later.
        double longitude = 127.07622041;
        double latitude = 37.5959791;
        restaurantDetailService.requestMapImage(500, 140, 17,
                longitude, latitude);
    }

    private void setRestaurantServiceInfo() {
        ConstraintLayout clSeeMoreInfo = findViewById(R.id.restaurant_detail_const_layout_see_more_info);
        clSeeMoreInfo.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));
    }

    private void setRestaurantMenu() {
        setRvRestaurantDetailMenu();
        setRvRestaurantDetailMenuPhoto();

        ConstraintLayout clSeeMoreMenu = findViewById(R.id.restaurant_detail_const_layout_see_more_menu);
        clSeeMoreMenu.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));
    }

    private void setRvRestaurantDetailMenu() {
        RestaurantDetailMenuAdapter restaurantDetailMenuAdapter = new RestaurantDetailMenuAdapter();
        RecyclerView rvRestaurantDetailMenu = findViewById(R.id.restaurant_detail_rv_menu);
        rvRestaurantDetailMenu.setLayoutManager(new LinearLayoutManager(this));
        rvRestaurantDetailMenu.setAdapter(restaurantDetailMenuAdapter);

        // TODO: test. It must be removed later.
        addToRestaurantDetailMenuAdapter(restaurantDetailMenuAdapter);
    }

    // TODO: There is no photo of menu in API 4-3, so it doesn't work.
    private void setRvRestaurantDetailMenuPhoto() {
        RestaurantDetailMenuPhotoAdapter restaurantDetailMenuPhotoAdapter = new RestaurantDetailMenuPhotoAdapter();
        RecyclerView rvRestaurantDetailMenuPhoto = findViewById(R.id.restaurant_detail_rv_menu_photo);
        rvRestaurantDetailMenuPhoto.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rvRestaurantDetailMenuPhoto.setAdapter(restaurantDetailMenuPhotoAdapter);
    }

    private void setPopularKeyword() {
        // TODO: ERROR! need material theme style for using chip.
    }

    private void setRestaurantReview() {
        ConstraintLayout clReviewDeliciousBtn = findViewById(R.id.restaurant_detail_const_layout_review_delicious_btn);
        ConstraintLayout clReviewGoodBtn = findViewById(R.id.restaurant_detail_const_layout_review_good_btn);
        ConstraintLayout clReviewNotGoodBtn = findViewById(R.id.restaurant_detail_const_layout_review_not_good_btn);
        ConstraintLayout clSeeMoreReview = findViewById(R.id.restaurant_detail_const_layout_see_more_review);

        clReviewDeliciousBtn.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));
        clReviewGoodBtn.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));
        clReviewNotGoodBtn.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));
        clSeeMoreReview.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));

        setRvRestaurantReview();
    }

    // TODO: need the layout of restaurant review
    private void setRvRestaurantReview() {
        /*
        RestaurantReviewAdapter restaurantReviewAdapter = new RestaurantReviewAdapter();
        RecyclerView rvRestaurantReview = findViewById(R.id.restaurant_detail_rv_restaurant_review);
        rvRestaurantReview.setLayoutManager(new LinearLayoutManager(this));
        rvRestaurantReview.setAdapter(restaurantReviewAdapter);
         */
    }

    private void setRvPopularRestaurantNearby() {
        RestaurantListRvAdapter popularRestaurantNearbyAdapter = new RestaurantListRvAdapter();
        RecyclerView rvPopularRestaurantNearby = findViewById(R.id.restaurant_detail_rv_popular_restaurant_nearby);
        rvPopularRestaurantNearby.setLayoutManager(new GridLayoutManager(this, 2));
        rvPopularRestaurantNearby.setAdapter(popularRestaurantNearbyAdapter);

        // TODO: test. It must be removed later.
        addToRestaurantListRvAdapter(popularRestaurantNearbyAdapter);
        addToRestaurantListRvAdapter(popularRestaurantNearbyAdapter);
    }

    private void addToRestaurantDetailMenuAdapter(RestaurantDetailMenuAdapter adapter) {
        RestaurantDetailMenuInfo info0 = new RestaurantDetailMenuInfo("아이들 돈가스", "7,500");
        adapter.add(info0);
        RestaurantDetailMenuInfo info1 = new RestaurantDetailMenuInfo("로스 가스", "8,000");
        adapter.add(info1);
        RestaurantDetailMenuInfo info2 = new RestaurantDetailMenuInfo("히레 가스", "8,500");
        adapter.add(info2);
    }

    // TODO: test. It must be removed later.
    private void addToRestaurantListRvAdapter(RestaurantListRvAdapter adapter) {
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
        adapter.addRestaurantInfo(info0);
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
        adapter.addRestaurantInfo(info1);
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
        adapter.addRestaurantInfo(info2);
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
        adapter.addRestaurantInfo(info3);
    }

    private void setView() {
        ConstraintLayout clCallRestaurant = findViewById(R.id.restaurant_detail_const_layout_call_restaurant);
        clCallRestaurant.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));

        ConstraintLayout clRequestEditRestaurantInfo = findViewById(R.id.restaurant_detail_const_layout_request_edit_restaurant_info);
        clRequestEditRestaurantInfo.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));

        ConstraintLayout clSearchBlogReviewBtn = findViewById(R.id.restaurant_detail_const_layout_search_blog_review_title);
        clSearchBlogReviewBtn.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));
    }

    @Override
    public void onSuccessGetNaverMap(Bitmap bitmap) {
        ImageView ivMap = findViewById(R.id.restaurant_detail_iv_map);
        new Handler(Looper.getMainLooper()).post(() -> ivMap.setImageBitmap(bitmap));
    }

    @Override
    public void onFailureGetNaverMap(IOException e) {
        Log.e(ApplicationClass.TAG, "RestaurantDetailActivity::validateFailure(): " + e);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
