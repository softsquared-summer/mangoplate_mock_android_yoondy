package com.softsquared.mangoplate.src.main.tab_my_info;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.main.event.EventActivity;

public class MyInfoFragment extends Fragment {
    public MyInfoFragment() {
        // Required empty public constructor
    }

    public static MyInfoFragment newInstance() {
        return new MyInfoFragment();
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

        ConstraintLayout clEvent = view.findViewById(R.id.my_info_const_layout_event);
        clEvent.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), EventActivity.class);
            startActivity(intent);
        });
    }
}
