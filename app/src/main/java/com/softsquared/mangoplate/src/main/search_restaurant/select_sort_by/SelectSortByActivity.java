package com.softsquared.mangoplate.src.main.search_restaurant.select_sort_by;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.softsquared.mangoplate.R;

public class SelectSortByActivity extends AppCompatActivity {
    private Button btnSortByScore, btnSortByRecommend, btnSortByReview, btnSortByDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sort_by);

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

        btnSortByScore.setTextColor(getResources().getColor(R.color.orange));
        btnSortByScore.setSelected(true);

        btnSortByScore.setOnClickListener(v -> {
            btnSortByScore.setTextColor(getResources().getColor(R.color.orange));
            v.setSelected(true);
            unselectAnotherBtnsExcept(btnSortByScore);
        });
        btnSortByRecommend.setOnClickListener(v -> {
            btnSortByRecommend.setTextColor(getResources().getColor(R.color.orange));
            v.setSelected(true);
            unselectAnotherBtnsExcept(btnSortByRecommend);
        });
        btnSortByReview.setOnClickListener(v -> {
            btnSortByReview.setTextColor(getResources().getColor(R.color.orange));
            v.setSelected(true);
            unselectAnotherBtnsExcept(btnSortByReview);
        });
        btnSortByDistance.setOnClickListener(v -> {
            btnSortByDistance.setTextColor(getResources().getColor(R.color.orange));
            v.setSelected(true);
            unselectAnotherBtnsExcept(btnSortByDistance);
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
}
