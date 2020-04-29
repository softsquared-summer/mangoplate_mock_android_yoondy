package com.softsquared.mangoplate.src.main.discount;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softsquared.mangoplate.R;

public class DiscountFragment extends Fragment {
    public DiscountFragment() {
        // Required empty public constructor
    }

    public static DiscountFragment newInstance() {
        DiscountFragment fragment = new DiscountFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discount, container, false);
    }
}
