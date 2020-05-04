package com.softsquared.mangoplate.src.main.my_info.event.event_detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.softsquared.mangoplate.R;

public class EventDetailActivity extends AppCompatActivity {

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
        ivKakaotalkBtn.setOnClickListener(v -> {
            // TODO: Kakaotalk share
        });
    }
}

