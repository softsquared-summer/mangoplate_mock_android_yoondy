package com.softsquared.mangoplate.src.main.tab_search_restaurant.select_filter;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.BaseActivity;

public class SelectFilterActivity extends BaseActivity {
    private boolean isSelectedKoreanFood = false;
    private boolean isSelectedJapaneseFood = false;
    private boolean isSelectedChineseFood = false;
    private boolean isSelectedWesternFood = false;
    private boolean isSelectedWorldFood = false;
    private boolean isSelectedBuffet = false;
    private boolean isSelectedCafe = false;
    private boolean isSelectedPub = false;

    private boolean isSelectedUnderTenThousandWon = false;
    private boolean isSelectedAboutTenThousandWon = false;
    private boolean isSelectedAboutTwentyThousandWon = false;
    private boolean isSelectedMoreThanThirtyThousandWon = false;

    private Button btnFilterCategoryAll, btnFilterCategoryWish, btnFilterCategoryGone;
    private Button btnFilterParkingAll, btnFilterParkingOnly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_filter);

        setViews();
    }

    private void setViews() {
        FrameLayout flBgPanel = findViewById(R.id.sel_filter_frame_layout_bg_panel);
        flBgPanel.setOnClickListener(v -> finish());

        TextView tvCancel = findViewById(R.id.sel_filter_tv_cancel);
        tvCancel.setOnClickListener(v -> finish());

        TextView tvApply = findViewById(R.id.sel_filter_tv_apply);
        tvApply.setOnClickListener(v -> {
            // TODO: use API, and finish()
        });

        setCategoryViews();
        setTypeOfFood();
        setCostViews();
        setParkViews();
    }

    private void setCategoryViews() {
        btnFilterCategoryAll = findViewById(R.id.sel_filter_btn_all_category);
        btnFilterCategoryWish = findViewById(R.id.sel_filter_btn_wish_category);
        btnFilterCategoryGone = findViewById(R.id.sel_filter_btn_gone_category);

        btnFilterCategoryAll.setTextColor(getResources().getColor(R.color.orange));
        btnFilterCategoryAll.setSelected(true);

        btnFilterCategoryAll.setOnClickListener(v -> {
            btnFilterCategoryAll.setTextColor(getResources().getColor(R.color.orange));
            v.setSelected(true);
            unselectAnotherCategoryBtnsExcept(btnFilterCategoryAll);
        });
        btnFilterCategoryWish.setOnClickListener(v -> {
            btnFilterCategoryWish.setTextColor(getResources().getColor(R.color.orange));
            v.setSelected(true);
            unselectAnotherCategoryBtnsExcept(btnFilterCategoryWish);

        });
        btnFilterCategoryGone.setOnClickListener(v -> {
            btnFilterCategoryGone.setTextColor(getResources().getColor(R.color.orange));
            v.setSelected(true);
            unselectAnotherCategoryBtnsExcept(btnFilterCategoryGone);
        });
    }

    private void setTypeOfFood() {
        ConstraintLayout clKoreanFood = findViewById(R.id.sel_filter_const_layout_korean_food);
        ConstraintLayout clJapaneseFood = findViewById(R.id.sel_filter_const_layout_japanese_food);
        ConstraintLayout clChineseFood = findViewById(R.id.sel_filter_const_layout_chinese_food);
        ConstraintLayout clWesternFood = findViewById(R.id.sel_filter_const_layout_western_food);
        ConstraintLayout clWorldFood = findViewById(R.id.sel_filter_const_layout_world_food);
        ConstraintLayout clBuffet = findViewById(R.id.sel_filter_const_layout_buffet);
        ConstraintLayout clCafe = findViewById(R.id.sel_filter_const_layout_cafe);
        ConstraintLayout clPub = findViewById(R.id.sel_filter_const_layout_pub);

        ImageView ivKoreanFood = findViewById(R.id.sel_filter_iv_korean_food);
        ImageView ivJapaneseFood = findViewById(R.id.sel_filter_iv_japanese_food);
        ImageView ivChineseFood = findViewById(R.id.sel_filter_iv_chinese_food);
        ImageView ivWesternFood = findViewById(R.id.sel_filter_iv_western_food);
        ImageView ivWorldFood = findViewById(R.id.sel_filter_iv_world_food);
        ImageView ivBuffet = findViewById(R.id.sel_filter_iv_buffet);
        ImageView ivCafe = findViewById(R.id.sel_filter_iv_cafe);
        ImageView ivPub = findViewById(R.id.sel_filter_iv_pub);

        TextView tvKoreanFood = findViewById(R.id.sel_filter_tv_korean_food);
        TextView tvJapaneseFood = findViewById(R.id.sel_filter_tv_japanese_food);
        TextView tvChineseFood = findViewById(R.id.sel_filter_tv_chinese_food);
        TextView tvWesternFood = findViewById(R.id.sel_filter_tv_western_food);
        TextView tvWorldFood = findViewById(R.id.sel_filter_tv_world_food);
        TextView tvBuffet = findViewById(R.id.sel_filter_tv_buffet);
        TextView tvCafe = findViewById(R.id.sel_filter_tv_cafe);
        TextView tvPub = findViewById(R.id.sel_filter_tv_pub);

        clKoreanFood.setOnClickListener(v -> {
            if(isSelectedKoreanFood) {
                ivKoreanFood.setImageResource(R.drawable.ic_korean_food_gray);
                tvKoreanFood.setTextColor(getResources().getColor(R.color.middleGray));
            }
            else {
                ivKoreanFood.setImageResource(R.drawable.ic_korean_food_orange);
                tvKoreanFood.setTextColor(getResources().getColor(R.color.orange));
            }
            isSelectedKoreanFood = !isSelectedKoreanFood;
        });
        clJapaneseFood.setOnClickListener(v -> {
            if(isSelectedJapaneseFood) {
                ivJapaneseFood.setImageResource(R.drawable.ic_japanese_food_gray);
                tvJapaneseFood.setTextColor(getResources().getColor(R.color.middleGray));
            }
            else {
                ivJapaneseFood.setImageResource(R.drawable.ic_japanese_food_orange);
                tvJapaneseFood.setTextColor(getResources().getColor(R.color.orange));
            }
            isSelectedJapaneseFood = !isSelectedJapaneseFood;
        });
        clChineseFood.setOnClickListener(v -> {
            if(isSelectedChineseFood) {
                ivChineseFood.setImageResource(R.drawable.ic_chinese_food_gray);
                tvChineseFood.setTextColor(getResources().getColor(R.color.middleGray));
            }
            else {
                ivChineseFood.setImageResource(R.drawable.ic_chinese_food_orange);
                tvChineseFood.setTextColor(getResources().getColor(R.color.orange));
            }
            isSelectedChineseFood = !isSelectedChineseFood;
        });
        clWesternFood.setOnClickListener(v -> {
            if(isSelectedWesternFood) {
                ivWesternFood.setImageResource(R.drawable.ic_western_food_gray);
                tvWesternFood.setTextColor(getResources().getColor(R.color.middleGray));
            }
            else {
                ivWesternFood.setImageResource(R.drawable.ic_western_food_orange);
                tvWesternFood.setTextColor(getResources().getColor(R.color.orange));
            }
            isSelectedWesternFood = !isSelectedWesternFood;
        });
        clWorldFood.setOnClickListener(v -> {
            if(isSelectedWorldFood) {
                ivWorldFood.setImageResource(R.drawable.ic_world_food_gray);
                tvWorldFood.setTextColor(getResources().getColor(R.color.middleGray));
            }
            else {
                ivWorldFood.setImageResource(R.drawable.ic_world_food_orange);
                tvWorldFood.setTextColor(getResources().getColor(R.color.orange));
            }
            isSelectedWorldFood = !isSelectedWorldFood;
        });
        clBuffet.setOnClickListener(v -> {
            if(isSelectedBuffet) {
                ivBuffet.setImageResource(R.drawable.ic_buffet_gray);
                tvBuffet.setTextColor(getResources().getColor(R.color.middleGray));
            }
            else {
                ivBuffet.setImageResource(R.drawable.ic_buffet_orange);
                tvBuffet.setTextColor(getResources().getColor(R.color.orange));
            }
            isSelectedBuffet = !isSelectedBuffet;
        });
        clCafe.setOnClickListener(v -> {
            if(isSelectedCafe) {
                ivCafe.setImageResource(R.drawable.ic_cafe_gray);
                tvCafe.setTextColor(getResources().getColor(R.color.middleGray));
            }
            else {
                ivCafe.setImageResource(R.drawable.ic_cafe_orange);
                tvCafe.setTextColor(getResources().getColor(R.color.orange));
            }
            isSelectedCafe = !isSelectedCafe;
        });
        clPub.setOnClickListener(v -> {
            if(isSelectedPub) {
                ivPub.setImageResource(R.drawable.ic_pub_gray);
                tvPub.setTextColor(getResources().getColor(R.color.middleGray));
            }
            else {
                ivPub.setImageResource(R.drawable.ic_pub_orange);
                tvPub.setTextColor(getResources().getColor(R.color.orange));
            }
            isSelectedPub = !isSelectedPub;
        });
    }

    private void setCostViews() {
        FrameLayout flUnderTenThousandWon = findViewById(R.id.sel_filter_frame_layout_under_ten_thousand_won);
        FrameLayout flAboutTenThousandWon = findViewById(R.id.sel_filter_frame_layout_about_ten_thousand_won);
        FrameLayout flAboutTwentyThousandWon = findViewById(R.id.sel_filter_frame_layout_about_twenty_thousand_won);
        FrameLayout flMoreThanThirtyThousandWon = findViewById(R.id.sel_filter_frame_layout_more_than_thirty_thousand_won);

        ImageView ivUnderTenThousandWon = findViewById(R.id.sel_filter_iv_under_ten_thousand_won);
        ImageView ivAboutTenThousandWon = findViewById(R.id.sel_filter_iv_about_ten_thousand_won);
        ImageView ivAboutTwentyThousandWon = findViewById(R.id.sel_filter_iv_about_twenty_thousand_won);
        ImageView ivMoreThanThirtyThousandWon = findViewById(R.id.sel_filter_iv_more_than_thirty_thousand_won);

        TextView tvUnderTenThousandWon = findViewById(R.id.sel_filter_tv_under_ten_thousand_won);
        TextView tvAboutTenThousandWon = findViewById(R.id.sel_filter_tv_about_ten_thousand_won);
        TextView tvAboutTwentyThousandWon = findViewById(R.id.sel_filter_tv_about_twenty_thousand_won);
        TextView tvMoreThanThirtyThousandWon = findViewById(R.id.sel_filter_tv_more_than_thirty_thousand_won);

        flUnderTenThousandWon.setOnClickListener(v -> {
            if(isSelectedUnderTenThousandWon) {
                ivUnderTenThousandWon.setImageResource(R.drawable.ic_circle_1_gray);
                tvUnderTenThousandWon.setTextColor(getResources().getColor(R.color.middleGray));
            }
            else {
                ivUnderTenThousandWon.setImageResource(R.drawable.ic_circle_1_orange);
                tvUnderTenThousandWon.setTextColor(getResources().getColor(R.color.orange));
            }
            isSelectedUnderTenThousandWon = !isSelectedUnderTenThousandWon;
        });
        flAboutTenThousandWon.setOnClickListener(v -> {
            if(isSelectedAboutTenThousandWon) {
                ivAboutTenThousandWon.setImageResource(R.drawable.ic_circle_2_gray);
                tvAboutTenThousandWon.setTextColor(getResources().getColor(R.color.middleGray));
            }
            else {
                ivAboutTenThousandWon.setImageResource(R.drawable.ic_circle_2_orange);
                tvAboutTenThousandWon.setTextColor(getResources().getColor(R.color.orange));
            }
            isSelectedAboutTenThousandWon = !isSelectedAboutTenThousandWon;
        });
        flAboutTwentyThousandWon.setOnClickListener(v -> {
            if(isSelectedAboutTwentyThousandWon) {
                ivAboutTwentyThousandWon.setImageResource(R.drawable.ic_circle_3_gray);
                tvAboutTwentyThousandWon.setTextColor(getResources().getColor(R.color.middleGray));
            }
            else {
                ivAboutTwentyThousandWon.setImageResource(R.drawable.ic_circle_3_orange);
                tvAboutTwentyThousandWon.setTextColor(getResources().getColor(R.color.orange));
            }
            isSelectedAboutTwentyThousandWon = !isSelectedAboutTwentyThousandWon;
        });
        flMoreThanThirtyThousandWon.setOnClickListener(v -> {
            if(isSelectedMoreThanThirtyThousandWon) {
                ivMoreThanThirtyThousandWon.setImageResource(R.drawable.ic_circle_4_gray);
                tvMoreThanThirtyThousandWon.setTextColor(getResources().getColor(R.color.middleGray));
            }
            else {
                ivMoreThanThirtyThousandWon.setImageResource(R.drawable.ic_circle_4_orange);
                tvMoreThanThirtyThousandWon.setTextColor(getResources().getColor(R.color.orange));
            }
            isSelectedMoreThanThirtyThousandWon = !isSelectedMoreThanThirtyThousandWon;
        });
    }

    private void setParkViews() {
        btnFilterParkingAll = findViewById(R.id.sel_filter_btn_parking_all);
        btnFilterParkingOnly = findViewById(R.id.sel_filter_btn_parking_only);

        btnFilterParkingAll.setTextColor(getResources().getColor(R.color.orange));
        btnFilterParkingAll.setSelected(true);

        btnFilterParkingAll.setOnClickListener(v -> {
            btnFilterParkingAll.setTextColor(getResources().getColor(R.color.orange));
            btnFilterParkingAll.setSelected(true);

            btnFilterParkingOnly.setTextColor(getResources().getColor(R.color.middleGray));
            btnFilterParkingOnly.setSelected(false);
        });
        btnFilterParkingOnly.setOnClickListener(v -> {
            btnFilterParkingOnly.setTextColor(getResources().getColor(R.color.orange));
            btnFilterParkingOnly.setSelected(true);

            btnFilterParkingAll.setTextColor(getResources().getColor(R.color.middleGray));
            btnFilterParkingAll.setSelected(false);
        });
    }

    private void unselectAnotherCategoryBtnsExcept(Button btnFilterCategory) {
        if(btnFilterCategory != btnFilterCategoryAll) {
            btnFilterCategoryAll.setSelected(false);
            btnFilterCategoryAll.setTextColor(getResources().getColor(R.color.middleGray));
        }
        if(btnFilterCategory != btnFilterCategoryWish) {
            btnFilterCategoryWish.setSelected(false);
            btnFilterCategoryWish.setTextColor(getResources().getColor(R.color.middleGray));
        }
        if(btnFilterCategory != btnFilterCategoryGone) {
            btnFilterCategoryGone.setSelected(false);
            btnFilterCategoryGone.setTextColor(getResources().getColor(R.color.middleGray));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
