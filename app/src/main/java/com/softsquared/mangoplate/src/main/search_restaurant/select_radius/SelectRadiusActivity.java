package com.softsquared.mangoplate.src.main.search_restaurant.select_radius;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.ApplicationClass;

public class SelectRadiusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_radius);

        setViews();
    }

    private void setViews() {
        FrameLayout flBgPanel = findViewById(R.id.sel_radius_frame_layout_bg_panel);
        flBgPanel.setOnClickListener(v -> finish());

        TextView tvRadius = findViewById(R.id.sel_radius_tv_radius);

        SeekBar seekBarSelectDistance = findViewById(R.id.sel_radius_seekBar);
        seekBarSelectDistance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(ApplicationClass.TAG, "seekBar progress: " + progress);
                if(progress < 25) {
                    tvRadius.setText("100m");
                }
                else if(progress < 75) {
                    tvRadius.setText("300m");
                }
                else if(progress < 125) {
                    tvRadius.setText("500m");
                }
                else if(progress < 175) {
                    tvRadius.setText("1km");
                }
                else {
                    tvRadius.setText("3km");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO: seekBarSelectDistance.setProgress(index);
                // TODO: finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
