package com.softsquared.mangoplate.src.main.my_info;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.softsquared.mangoplate.R;

public class MyInfoFragment extends Fragment {
    public MyInfoFragment() {
        // Required empty public constructor
    }

    public static MyInfoFragment newInstance() {
        MyInfoFragment fragment = new MyInfoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_info, container, false);

        setView(view);

        return view;
    }

    private void setView(View view) {
        ImageView ivProfilePhoto = view.findViewById(R.id.my_info_iv_profile_photo);
        ivProfilePhoto.setBackground(new ShapeDrawable(new OvalShape()));
        ivProfilePhoto.setClipToOutline(true);
    }
}
