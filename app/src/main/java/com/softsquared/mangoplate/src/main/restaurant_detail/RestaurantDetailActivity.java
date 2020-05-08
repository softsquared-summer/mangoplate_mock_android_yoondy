package com.softsquared.mangoplate.src.main.restaurant_detail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amar.library.ui.StickyScrollView;
import com.amar.library.ui.interfaces.IScrollViewListener;
import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.BaseActivity;
import com.softsquared.mangoplate.src.gps.GpsService;
import com.softsquared.mangoplate.src.main.restaurant_detail.interfaces.RestaurantDetailActivityView;
import com.softsquared.mangoplate.src.main.restaurant_detail.models.RestaurantDetailInfo;
import com.softsquared.mangoplate.src.main.restaurant_detail.models.RestaurantDetailMenuInfo;
import com.softsquared.mangoplate.src.main.restaurant_detail.models.RestaurantDetailPhotoInfo;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.RestaurantListRvAdapter;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.SearchRestaurantService;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.interfaces.SearchRestaurantFragmentView;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.models.BannerAdInfo;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.models.RestaurantInfo;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.models.WishInfo;

import java.io.IOException;
import java.util.ArrayList;

import static com.softsquared.mangoplate.src.ApplicationClass.TAG;

public class RestaurantDetailActivity extends BaseActivity implements RestaurantDetailActivityView, SearchRestaurantFragmentView {
    final private RestaurantDetailService restaurantDetailService = new RestaurantDetailService(this, this);
    final private SearchRestaurantService searchRestaurantService = new SearchRestaurantService(this);
    private RestaurantDetailPhotoAdapter restaurantDetailPhotoAdapter;
    private RestaurantListRvAdapter popularRestaurantNearbyAdapter;
    private RestaurantDetailMenuAdapter restaurantDetailMenuAdapter;
    private StickyScrollView stickyScrollView;
    private int restaurantId;
    private boolean isWish;
    private ImageView ivWish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        Intent intent = getIntent();
        if(intent != null) {
            restaurantId = intent.getIntExtra("restaurantId", -1);
            if (restaurantId >= 0) {
                restaurantDetailService.getRestaurantDetail(restaurantId);
                showProgressDialog();
                Log.d(TAG, ">>> restaurantId: " + restaurantId);
            }
        }

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
    }

    private void setTopBarAndStickyScrollView() {
        ConstraintLayout clTopBar = findViewById(R.id.restaurant_detail_const_layout_top_bar);
        ConstraintLayout clBackBtn = findViewById(R.id.restaurant_detail_const_layout_back_btn);
        ConstraintLayout clPhotoBtn = findViewById(R.id.restaurant_detail_const_layout_photo_btn);
        ConstraintLayout clShareBtn = findViewById(R.id.restaurant_detail_const_layout_share_btn);

        ImageView ivBackBtn = findViewById(R.id.restaurant_detail_iv_back_btn);
        ImageView ivPhotoBtn = findViewById(R.id.restaurant_detail_iv_photo_btn);
        ImageView ivShareBtn = findViewById(R.id.restaurant_detail_iv_share_btn);

        stickyScrollView = findViewById(R.id.restaurant_detail_sticky_scroll_view);
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
        restaurantDetailPhotoAdapter = new RestaurantDetailPhotoAdapter();
        RecyclerView rvRestaurantDetailPhoto = findViewById(R.id.restaurant_detail_rv_photos);
        rvRestaurantDetailPhoto.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rvRestaurantDetailPhoto.setAdapter(restaurantDetailPhotoAdapter);

        // for stickyHeader's bug, at least 1 item is need
        RestaurantDetailPhotoInfo info0 = new RestaurantDetailPhotoInfo(23, "https://i.imgur.com/OSupQAB.jpg");
        restaurantDetailPhotoAdapter.add(info0);
    }

    private void setMenuForRecord() {
        ConstraintLayout clWish = findViewById(R.id.restaurant_detail_const_layout_wish);
        ConstraintLayout clGone = findViewById(R.id.restaurant_detail_const_layout_gone);
        ConstraintLayout clWriteReview = findViewById(R.id.restaurant_detail_const_layout_write_review);
        ConstraintLayout clMyList = findViewById(R.id.restaurant_detail_const_layout_my_list);

        clWish.setOnClickListener(v -> {
            searchRestaurantService.postWish(restaurantId);
            ivWish.setImageResource(isWish ?
                    R.drawable.ic_star_unfilled_orange : R.drawable.ic_star_filled_orange);

            isWish = !isWish;
        });
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
        restaurantDetailMenuAdapter = new RestaurantDetailMenuAdapter();
        RecyclerView rvRestaurantDetailMenu = findViewById(R.id.restaurant_detail_rv_menu);
        rvRestaurantDetailMenu.setLayoutManager(new LinearLayoutManager(this));
        rvRestaurantDetailMenu.setAdapter(restaurantDetailMenuAdapter);
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
        popularRestaurantNearbyAdapter = new RestaurantListRvAdapter(this);
        RecyclerView rvPopularRestaurantNearby = findViewById(R.id.restaurant_detail_rv_popular_restaurant_nearby);
        rvPopularRestaurantNearby.setLayoutManager(new GridLayoutManager(this, 2));
        rvPopularRestaurantNearby.setAdapter(popularRestaurantNearbyAdapter);

        // TODO: this is temporary.
        final GpsService gpsService = new GpsService(this);
        final SearchRestaurantService searchRestaurantService = new SearchRestaurantService(this);
        searchRestaurantService.getRestaurantList(
                (float) gpsService.getLatitude(), (float) gpsService.getLongitude(),
                null, null, null,
                null, null, null,
                 null, null, null
        );
    }

        private void setView() {
            ConstraintLayout clCallRestaurant = findViewById(R.id.restaurant_detail_const_layout_call_restaurant);
            clCallRestaurant.setOnClickListener(v -> {
                //
            });

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
        Log.e(TAG, "RestaurantDetailActivity::validateFailure(): " + e);
    }

    @Override
    public void onSuccessGetRestaurantDetail(RestaurantDetailInfo restaurantDetailInfo) {
        restaurantDetailPhotoAdapter.clear();
        ArrayList<RestaurantDetailPhotoInfo> photoInfoList = restaurantDetailInfo.getImages();
        for(RestaurantDetailPhotoInfo photoInfo : photoInfoList)
            restaurantDetailPhotoAdapter.add(photoInfo);

        restaurantDetailPhotoAdapter.notifyDataSetChanged();

        TextView tvTitle = findViewById(R.id.restaurant_detail_tv_name_top_bar);
        TextView tvName = findViewById(R.id.restaurant_detail_tv_name);
        tvTitle.setText(restaurantDetailInfo.getName());
        tvName.setText(restaurantDetailInfo.getName());

        TextView tvRating = findViewById(R.id.restaurant_detail_tv_rating);
        tvRating.setText(restaurantDetailInfo.getRating());
        switch (restaurantDetailInfo.getRatingColor()) {
            case "orange": {
                tvRating.setVisibility(View.VISIBLE);
                tvRating.setTextColor(getResources().getColor(R.color.orange));
                break;
            }
            case "gray": {
                tvRating.setVisibility(View.VISIBLE);
                tvRating.setTextColor(getResources().getColor(R.color.middleBrightGray));
                break;
            }
            case "": {
                tvRating.setVisibility(View.INVISIBLE);
                break;
            }
        }

        TextView tvViewCnt = findViewById(R.id.restaurant_detail_tv_view_count);
        tvViewCnt.setText(restaurantDetailInfo.getSeenNum());
        TextView tvReviewCnt = findViewById(R.id.restaurant_detail_tv_review_count);
        tvReviewCnt.setText(restaurantDetailInfo.getReviewNum());
        TextView tvWishCnt = findViewById(R.id.restaurant_detail_tv_wish_count);
        tvWishCnt.setText(restaurantDetailInfo.getStarNum());

        isWish = restaurantDetailInfo.getUserStar().equals("YES");
        ivWish = findViewById(R.id.restaurant_detail_iv_wish);
        ivWish.setImageResource(isWish ?
                R.drawable.ic_star_filled_orange : R.drawable.ic_star_unfilled_orange);

        TextView tvRoadAddress = findViewById(R.id.restaurant_detail_tv_road_address);
        tvRoadAddress.setText(restaurantDetailInfo.getAddress());
        TextView tvParcelAddress = findViewById(R.id.restaurant_detail_tv_parcel_address);
        tvParcelAddress.setText(restaurantDetailInfo.getOldAddress());

        restaurantDetailService.requestMapImage(500, 140, 17,
                restaurantDetailInfo.getLng(), restaurantDetailInfo.getLat());

        ConstraintLayout clCallRestaurant = findViewById(R.id.restaurant_detail_const_layout_call_restaurant);
        clCallRestaurant.setOnClickListener(v ->
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + restaurantDetailInfo.getPhone())))
        );

        TextView tvServiceInfoUpdate = findViewById(R.id.restaurant_detail_tv_service_info_update_date);
        tvServiceInfoUpdate.setText(restaurantDetailInfo.getInfoUpdate());
        TextView tvServiceTime = findViewById(R.id.restaurant_detail_tv_service_time);
        tvServiceTime.setText(restaurantDetailInfo.getInfoTime());
        TextView tvDayOff = findViewById(R.id.restaurant_detail_tv_day_off);
        tvDayOff.setText(restaurantDetailInfo.getInfoHoliday());
        TextView tvPrice = findViewById(R.id.restaurant_detail_tv_price);
        tvPrice.setText(restaurantDetailInfo.getInfoPrice());
        TextView tvPriceInfo = findViewById(R.id.restaurant_detail_tv_price_info);
        tvPriceInfo.setText(restaurantDetailInfo.getInfoDescription());

        ArrayList<RestaurantDetailMenuInfo> menuInfoList = restaurantDetailInfo.getMenu();
        restaurantDetailMenuAdapter.clear();
        for(RestaurantDetailMenuInfo menuInfo : menuInfoList)
            restaurantDetailMenuAdapter.add(menuInfo);

        restaurantDetailMenuAdapter.notifyDataSetChanged();

        TextView tvMenuUpdate = findViewById(R.id.restaurant_detail_tv_menu_update_date);
        tvMenuUpdate.setText(restaurantDetailInfo.getMenuUpdate());

        stickyScrollView.initHeaderView(R.id.restaurant_detail_const_layout_menu_for_record);

        hideProgressDialog();
    }

    @Override
    public void onFailureGetRestaurantDetail() {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onSuccessGetBannerAd(ArrayList<BannerAdInfo> bannerAdInfoList) {

    }

    @Override
    public void onFailureGetBannerAd() {

    }

    @Override
    public void onSuccessGetRestaurantList(ArrayList<RestaurantInfo> restaurantInfoList) {
        for(RestaurantInfo restaurantInfo : restaurantInfoList)
            popularRestaurantNearbyAdapter.add(restaurantInfo);

        popularRestaurantNearbyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailureGetRestaurantList() {

    }

    @Override
    public void onSuccessPostWish(WishInfo wishInfo) {

    }

    @Override
    public void onFailurePostWish() {

    }
}
