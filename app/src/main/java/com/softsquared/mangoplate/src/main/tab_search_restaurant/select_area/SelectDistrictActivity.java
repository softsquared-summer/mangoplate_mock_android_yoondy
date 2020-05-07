package com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.widget.FrameLayout;

import androidx.viewpager2.widget.ViewPager2;

import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.BaseActivity;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.interfaces.SelectDistrictFragmentView;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.models.AreaNearMeInfo;

import java.util.ArrayList;

public class SelectDistrictActivity extends BaseActivity implements SelectDistrictFragmentView {
    private ViewPager2 vp2SelectDistrict;
    private SelectDistrictVp2Adapter vp2SelectDistrictAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_district);

        setViews();
        setVp2DistrictSelectPage();
    }

    private void setViews() {
        FrameLayout flBgPanel = findViewById(R.id.sel_district_frame_layout_bg_panel);
        flBgPanel.setOnClickListener(v -> finish());
    }

    private void setVp2DistrictSelectPage() {
        vp2SelectDistrict = findViewById(R.id.sel_district_vp2_district_select_page);
        vp2SelectDistrictAdapter = new SelectDistrictVp2Adapter();
        vp2SelectDistrict.setAdapter(vp2SelectDistrictAdapter);

        // TODO: test. It must be removed later.
//        addToVp2Adapter(vp2SelectDistrictAdapter);

        // TODO: test. It must be removed later. replace with the result of API.
//        String[] tabName = {"최근지역", "내 주변", "서울-강남", "서울-강북", "경기도", "인천", "대구", "부산", "제주", "대전", "광주", "강원도"};


        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra("latitude", 0);
        double longitude = intent.getDoubleExtra("longitude", 0);

        final SelectDistrictService selectDistrictService = new SelectDistrictService(this);
        selectDistrictService.getAreaNearMe((float) latitude, (float) longitude);

        showProgressDialog();
    }

    // TODO: test. It must be removed later.
/*
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
*/
    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onSuccessGetAreaNearMe(ArrayList<AreaNearMeInfo> areaNearMeInfo) {
        ArrayList<String> recentAreaList = new ArrayList<>();
        recentAreaList.add("중랑구");
        recentAreaList.add("테스트지역");
        recentAreaList.add("강남구");
        recentAreaList.add("금천구");

        ArrayList<String> areaList = new ArrayList<>();
        for(AreaNearMeInfo area : areaNearMeInfo)
            areaList.add(area.getName());

        vp2SelectDistrictAdapter.add(new Pair<>("최근지역", recentAreaList));
        vp2SelectDistrictAdapter.add(new Pair<>("내 주변", areaList));
        vp2SelectDistrictAdapter.notifyDataSetChanged();

        // 나머지 지역들도 API 써서 불러오면 그 때 하는게 맞을 듯?
        /*
        TabLayout tabLayout = findViewById(R.id.sel_district_tab_layout_large_category);
        new TabLayoutMediator(tabLayout, vp2SelectDistrict, (tab, position) -> {
            tab.select();
            tab.setText(tabName[position]);
        }).attach();
        */


        hideProgressDialog();
    }

    @Override
    public void onFailureGetAreaNearMe() {
        hideProgressDialog();
    }
}
