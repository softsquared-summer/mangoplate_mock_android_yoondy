package com.softsquared.mangoplate.src.main.tab_my_info;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.main.event.EventActivity;

import static com.softsquared.mangoplate.src.ApplicationClass.sSharedPreferences;

public class MyInfoFragment extends Fragment {
    final private String DEFAULT_PROFILE_PHOTO_URL = "https://moonvillageassociation.org/wp-content/uploads/2018/06/default-profile-picture1.jpg;";

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
        CircularImageView ivProfilePhoto = view.findViewById(R.id.my_info_iv_profile_photo);

        String profileUrl = sSharedPreferences.getString("profileUrl", "");
        if(profileUrl.isEmpty())
            profileUrl = DEFAULT_PROFILE_PHOTO_URL;

        Glide.with(view.getContext()).load(profileUrl).into(ivProfilePhoto);
        ivProfilePhoto.setCircleColor(0);
//        Glide.with(view.getContext()).asBitmap().load(profileUrl).into(new CustomTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                ivProfilePhoto.setImageBitmap(resource);
//                ivProfilePhoto.setBackground(new ShapeDrawable(new OvalShape()));
//                ivProfilePhoto.setClipToOutline(true);
//            }
//
//            @Override
//            public void onLoadCleared(@Nullable Drawable placeholder) {
//            }
//        });

        TextView tvName = view.findViewById(R.id.my_info_tv_my_name);
        tvName.setText(sSharedPreferences.getString("name", "홍길동"));

        ConstraintLayout clEvent = view.findViewById(R.id.my_info_const_layout_event);
        clEvent.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), EventActivity.class);
            startActivity(intent);
        });
    }
}
