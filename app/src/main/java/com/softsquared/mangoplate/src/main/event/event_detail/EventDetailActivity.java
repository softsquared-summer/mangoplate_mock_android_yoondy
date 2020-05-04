package com.softsquared.mangoplate.src.main.event.event_detail;

import android.os.Bundle;
import android.widget.ImageView;

import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.BaseActivity;

public class EventDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        setView();
    }

    private void setView() {
        ImageView ivBackBtn = findViewById(R.id.event_detail_iv_back_btn);
        ivBackBtn.setOnClickListener(v -> finish());

        ImageView ivKakaotalkBtn = findViewById(R.id.event_detail_iv_kakaotalk_btn);
        ivKakaotalkBtn.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));

        ImageView ivShareBtn = findViewById(R.id.event_detail_iv_share_btn);
        ivShareBtn.setOnClickListener(v -> showCustomToast(getString(R.string.notify_not_prepared)));
    }
}

