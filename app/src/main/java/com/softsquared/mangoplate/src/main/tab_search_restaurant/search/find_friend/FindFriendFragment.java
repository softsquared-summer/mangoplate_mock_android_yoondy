package com.softsquared.mangoplate.src.main.tab_search_restaurant.search.find_friend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.softsquared.mangoplate.R;

public class FindFriendFragment extends Fragment {
    public FindFriendFragment() {
        // Required empty public constructor
    }

    public static FindFriendFragment newInstance() {
        return new FindFriendFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_find_friend, container, false);
    }
}
