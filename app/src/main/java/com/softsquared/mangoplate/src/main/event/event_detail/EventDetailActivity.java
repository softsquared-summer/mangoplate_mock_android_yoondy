package com.softsquared.mangoplate.src.main.event.event_detail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.BaseActivity;
import com.softsquared.mangoplate.src.main.event.event_detail.interfaces.EventDetailActivityView;
import com.softsquared.mangoplate.src.main.event.event_detail.models.EventDetailInfo;

import static com.softsquared.mangoplate.src.ApplicationClass.TAG;

public class EventDetailActivity extends BaseActivity implements EventDetailActivityView {
    private String eventId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Intent intent = getIntent();
        eventId = intent.getStringExtra("eventId");
        Log.d(TAG, "eventId: " + eventId);

        setView();
    }

    private void setView() {
        ImageView ivBackBtn = findViewById(R.id.event_detail_iv_back_btn);
        ivBackBtn.setOnClickListener(v -> finish());

        ImageView ivKakaotalkBtn = findViewById(R.id.event_detail_iv_kakaotalk_btn);
        ivKakaotalkBtn.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));

        ImageView ivShareBtn = findViewById(R.id.event_detail_iv_share_btn);
        ivShareBtn.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));

        if(!eventId.isEmpty()) {
            final EventDetailService eventDetailService = new EventDetailService(this);
            eventDetailService.getEventDetail(eventId);
            showProgressDialog();
        }
    }

    @Override
    public void onSuccessGetEventDetail(EventDetailInfo eventDetailInfo) {
        ImageView ivEventImg = findViewById(R.id.event_detail_iv_event_img);

        Glide.with(this)
                .load(eventDetailInfo.getImageUrl())
                .into(ivEventImg);

        hideProgressDialog();
    }

    @Override
    public void onFailureGetEventDetail() {
        hideProgressDialog();
    }
}

