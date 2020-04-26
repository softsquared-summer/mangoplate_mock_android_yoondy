package com.example.mangoplate.src.main.search_restaurant;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mangoplate.R;

public class SearchRestaurantFragment extends Fragment {
    public SearchRestaurantFragment() {
        // Required empty public constructor
    }

    public static SearchRestaurantFragment newInstance() {
        SearchRestaurantFragment fragment = new SearchRestaurantFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_restaurant, container, false);
    }
}
