package com.softsquared.mangoplate.src.main.tab_search_restaurant.select_radius;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.BaseActivity;

import static com.softsquared.mangoplate.src.ApplicationClass.sSharedPreferences;

public class SelectRadiusActivity extends BaseActivity {
    private final String RADIUS = "RADIUS";
    private final int SELECT_RADIUS = 4;
    private String radius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_radius);

        radius = sSharedPreferences.getString(RADIUS, "");
        if(radius.isEmpty()) {
            radius = "3";
            sSharedPreferences.edit().putString(RADIUS, radius).apply();
        }

        setViews();
    }

    private void setViews() {
        FrameLayout flBgPanel = findViewById(R.id.sel_radius_frame_layout_bg_panel);
        flBgPanel.setOnClickListener(v -> finish());

        TextView tvRadius = findViewById(R.id.sel_radius_tv_radius);

        SeekBar seekBarSelectDistance = findViewById(R.id.sel_radius_seekBar);
        switch (radius) {
            case "0.5": {
                tvRadius.setText("500m");
                seekBarSelectDistance.setProgress(100);
                break;
            }
            case "1": {
                tvRadius.setText("1km");
                seekBarSelectDistance.setProgress(150);
                break;
            }
            case "3": {
                tvRadius.setText("3km");
                seekBarSelectDistance.setProgress(200);
                break;
            }
        }

        seekBarSelectDistance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress < 25) {
                    radius = "0.5";
                    tvRadius.setText("100m");
                }
                else if(progress < 75) {
                    radius = "0.5";
                    tvRadius.setText("300m");
                }
                else if(progress < 125) {
                    radius = "0.5";
                    tvRadius.setText("500m");
                }
                else if(progress < 175) {
                    radius = "1";
                    tvRadius.setText("1km");
                }
                else {
                    radius = "3";
                    tvRadius.setText("3km");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Intent intent = new Intent();
                intent.putExtra(RADIUS, radius);
                setResult(SELECT_RADIUS, intent);
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sSharedPreferences.edit().putString(RADIUS, radius).apply();
    }
}
