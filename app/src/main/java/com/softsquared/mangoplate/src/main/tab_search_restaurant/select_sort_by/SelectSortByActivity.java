package com.softsquared.mangoplate.src.main.tab_search_restaurant.select_sort_by;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.BaseActivity;

import static com.softsquared.mangoplate.src.ApplicationClass.sSharedPreferences;

public class SelectSortByActivity extends BaseActivity {
    private final String ORDER = "order";
    private final int SELECT_SORT_BY = 3;
    private Button btnSortByScore, btnSortByRecommend, btnSortByReview, btnSortByDistance;
    private String sortBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sort_by);

        sortBy = sSharedPreferences.getString(ORDER, "");
        if(sortBy.isEmpty()) {
            sortBy = "평점순";
            sSharedPreferences.edit().putString(ORDER, sortBy).apply();
        }

        setViews();
    }

    private void setViews() {
        ImageView ivCancel = findViewById(R.id.sel_sort_by_iv_cancel);
        ivCancel.setOnClickListener(v -> finish());

        FrameLayout flBgPanel = findViewById(R.id.sel_sort_by_frame_layout_bg_panel);
        flBgPanel.setOnClickListener(v -> finish());

        btnSortByScore = findViewById(R.id.sel_sort_by_btn_sort_by_score);
        btnSortByRecommend = findViewById(R.id.sel_sort_by_btn_sort_by_recommend);
        btnSortByReview = findViewById(R.id.sel_sort_by_btn_sort_by_review);
        btnSortByDistance = findViewById(R.id.sel_sort_by_btn_sort_by_distance);

        switch (sortBy) {
            case "평점순": {
                btnSortByScore.setTextColor(getResources().getColor(R.color.orange));
                btnSortByScore.setSelected(true);
                unselectAnotherBtnsExcept(btnSortByScore);
                break;
            }
            case "리뷰순": {
                btnSortByReview.setTextColor(getResources().getColor(R.color.orange));
                btnSortByReview.setSelected(true);
                unselectAnotherBtnsExcept(btnSortByReview);
                break;
            }
            case "거리순": {
                btnSortByDistance.setTextColor(getResources().getColor(R.color.orange));
                btnSortByDistance.setSelected(true);
                unselectAnotherBtnsExcept(btnSortByDistance);
                break;
            }
        }

        btnSortByScore.setOnClickListener(v -> {
            btnSortByScore.setTextColor(getResources().getColor(R.color.orange));
            v.setSelected(true);
            unselectAnotherBtnsExcept(btnSortByScore);

            Intent intent = new Intent();
            sortBy = "평점순";
            intent.putExtra(ORDER, sortBy);
            setResult(SELECT_SORT_BY, intent);
            finish();
        });
        btnSortByRecommend.setOnClickListener(v -> {
            // We decided to not consider sorting by recommend (추천순).
            /*
            btnSortByRecommend.setTextColor(getResources().getColor(R.color.orange));
            v.setSelected(true);
            unselectAnotherBtnsExcept(btnSortByRecommend);
             */

            showCustomToast(getString(R.string.notify_not_prepared));
        });
        btnSortByReview.setOnClickListener(v -> {
            btnSortByReview.setTextColor(getResources().getColor(R.color.orange));
            v.setSelected(true);
            unselectAnotherBtnsExcept(btnSortByReview);

            Intent intent = new Intent();
            sortBy = "리뷰순";
            intent.putExtra(ORDER, sortBy);
            setResult(SELECT_SORT_BY, intent);
            finish();
        });
        btnSortByDistance.setOnClickListener(v -> {
            btnSortByDistance.setTextColor(getResources().getColor(R.color.orange));
            v.setSelected(true);
            unselectAnotherBtnsExcept(btnSortByDistance);

            Intent intent = new Intent();
            sortBy = "거리순";
            intent.putExtra(ORDER, sortBy);
            setResult(SELECT_SORT_BY, intent);
            finish();
        });
    }

    private void unselectAnotherBtnsExcept(Button btnSortBy) {
        if(btnSortBy != btnSortByScore) {
            btnSortByScore.setSelected(false);
            btnSortByScore.setTextColor(getResources().getColor(R.color.middleGray));
        }
        if(btnSortBy != btnSortByRecommend) {
            btnSortByRecommend.setSelected(false);
            btnSortByRecommend.setTextColor(getResources().getColor(R.color.middleGray));
        }
        if(btnSortBy != btnSortByReview) {
            btnSortByReview.setSelected(false);
            btnSortByReview.setTextColor(getResources().getColor(R.color.middleGray));
        }
        if(btnSortBy != btnSortByDistance) {
            btnSortByDistance.setSelected(false);
            btnSortByDistance.setTextColor(getResources().getColor(R.color.middleGray));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sSharedPreferences.edit().putString(ORDER, sortBy).apply();
    }
}
