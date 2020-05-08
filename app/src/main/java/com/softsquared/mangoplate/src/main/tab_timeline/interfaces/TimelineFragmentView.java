package com.softsquared.mangoplate.src.main.tab_timeline.interfaces;

import com.softsquared.mangoplate.src.main.tab_timeline.models.ReviewInfo;

import java.util.ArrayList;

public interface TimelineFragmentView {
    void onSuccessGetReviewList(ArrayList<ReviewInfo> reviewInfoList);

    void onFailureGetReviewList();
}
