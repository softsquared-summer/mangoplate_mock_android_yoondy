package com.softsquared.mangoplate.src.main.search_restaurant.select_area;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.main.search_restaurant.select_area.models.AreaSelectPageVp2Adapter;

import java.util.ArrayList;

public class SelectAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_area);

        setViews();
    }

    private void setViews() {
        FrameLayout flBgPanel = findViewById(R.id.sel_area_frame_layout_bg_panel);
        flBgPanel.setOnClickListener(v -> finish());

        ViewPager2 vp2AreaSelectPage = findViewById(R.id.sel_area_vp2_area_select_page);
        AreaSelectPageVp2Adapter vp2AreaSelectPageAdapter = new AreaSelectPageVp2Adapter();
        vp2AreaSelectPage.setAdapter(vp2AreaSelectPageAdapter);

        // TODO: test. It must be removed later.
        addToVp2Adapter(vp2AreaSelectPageAdapter);

        // TODO: test. It must be removed later. replace with the result of API.
        String[] tabName = {"최근지역", "내 주변", "서울-강남", "서울-강북", "경기도", "인천", "대구", "부산", "제주", "대전", "광주", "강원도"};

        TabLayout tabLayout = findViewById(R.id.sel_area_tab_layout_large_category);
        new TabLayoutMediator(tabLayout, vp2AreaSelectPage, (tab, position) -> {
            tab.select();
            tab.setText(tabName[position]);
        }).attach();
    }

    // TODO: test. It must be removed later.
    private void addToVp2Adapter(AreaSelectPageVp2Adapter vp2AreaSelectPageAdapter) {
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        ArrayList<String> list3 = new ArrayList<>();

        vp2AreaSelectPageAdapter.add(list1);

        list1.add("중랑구");
        list1.add("동대문구");
        list1.add("노원구");
        list1.add("구리시");

        list2.add("전체");
        list2.add("가로수길");
        list2.add("강남역");
        list2.add("강동구");
        list2.add("개포/수서/일원");
        list2.add("관악구");
        list2.add("교대/서초");
        list2.add("구로구");

        list3.add("전체");
        list3.add("건대/군자/광진");
        list3.add("광화문");
        list3.add("노원구");
        list3.add("대학로/혜화");
        list3.add("동대문구");
        list3.add("동부이촌동");
        list3.add("마포/공덕");
        list3.add("명동/남산");
        list3.add("삼청/인사");
        list3.add("상암/성신");
        list3.add("서대문구");
        list3.add("성북구");
        list3.add("수유/도봉/강북");
        list3.add("시청/남대문");
        list3.add("신촌/이대");
        list3.add("연남동");

        vp2AreaSelectPageAdapter.add(list1);
        vp2AreaSelectPageAdapter.add(list2);
        vp2AreaSelectPageAdapter.add(list3);

        list1.clear();
        for(int i=0; i<8; i++) vp2AreaSelectPageAdapter.add(list1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
