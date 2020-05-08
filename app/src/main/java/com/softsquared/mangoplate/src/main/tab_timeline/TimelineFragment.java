package com.softsquared.mangoplate.src.main.tab_timeline;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.main.MainActivity;
import com.softsquared.mangoplate.src.main.tab_timeline.interfaces.TimelineFragmentView;
import com.softsquared.mangoplate.src.main.tab_timeline.models.ReviewInfo;

import java.util.ArrayList;

public class TimelineFragment extends Fragment implements TimelineFragmentView {
    private MainActivity mainActivity;
    private ReviewRvAdapter reviewRvAdapter;

    public TimelineFragment() {
        // Required empty public constructor
    }

    public static TimelineFragment newInstance() {
        return new TimelineFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);

        Activity activity = getActivity();
        if(activity instanceof MainActivity)
            mainActivity = (MainActivity) activity;

        setRvReview(view);

        return view;
    }

    private void setRvReview(View view) {
        reviewRvAdapter = new ReviewRvAdapter();
        RecyclerView rvReview = view.findViewById(R.id.timeline_rv_review);
        rvReview.setLayoutManager(new LinearLayoutManager(getContext()));
        rvReview.setAdapter(reviewRvAdapter);

        final TimelineService timelineService = new TimelineService(this);
        timelineService.getReviewList();
        mainActivity.showProgressDialog();
    }

    @Override
    public void onSuccessGetReviewList(ArrayList<ReviewInfo> reviewInfoList) {
        for(ReviewInfo reviewInfo : reviewInfoList)
            reviewRvAdapter.add(reviewInfo);

        reviewRvAdapter.notifyDataSetChanged();
        mainActivity.hideProgressDialog();
    }

    @Override
    public void onFailureGetReviewList() {
        mainActivity.hideProgressDialog();
    }
}
