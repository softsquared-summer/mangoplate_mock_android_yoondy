package com.softsquared.mangoplate.src.main.discount.top_list;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.main.MainActivity;

public class TopListFragment extends Fragment {
    public TopListFragment() {
        // Required empty public constructor
    }

    public static TopListFragment newInstance() {
        return new TopListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_list, container, false);

        view.setOnTouchListener((v, event) -> {
            Context context = v.getContext();
            if (context instanceof MainActivity) {
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.vp2MainScreen.setUserInputEnabled(false);
            }
            v.performClick();
            return false;
        });

        return view;
    }
}
