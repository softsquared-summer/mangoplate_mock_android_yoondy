package com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.BaseActivity;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.interfaces.SelectDistrictFragmentView;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.models.AreaInfo;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.models.AreaNearMeInfo;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.models.DistrictInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.softsquared.mangoplate.src.ApplicationClass.TAG;

public class SelectDistrictActivity extends BaseActivity implements SelectDistrictFragmentView {
    private final int SELECT_AREA = 2;
    private final String AREA = "Area";

    private ViewPager2 vp2SelectDistrict;
    private SelectDistrictVp2Adapter vp2SelectDistrictAdapter;
    private Pair<String, ArrayList<String> >[] areaCategory = new Pair[50]; // Pair<districtName, areaList>
    private ArrayList<DistrictInfo> districtInfoList;

    public HashSet<String> selectedItems = new HashSet<>();
    public Button btnApply;

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

        btnApply = findViewById(R.id.sel_district_btn_apply);
        btnApply.setOnClickListener(v -> {
            ArrayList<String> arrayList = new ArrayList<>(selectedItems);
            if(arrayList.size() > 0) {
                Intent intent = new Intent();
                intent.putExtra(AREA, arrayList);
                setResult(SELECT_AREA, intent);
                finish();
            }
        });
    }

    private void setVp2DistrictSelectPage() {
        vp2SelectDistrict = findViewById(R.id.sel_district_vp2_district_select_page);
        vp2SelectDistrictAdapter = new SelectDistrictVp2Adapter();
        vp2SelectDistrict.setAdapter(vp2SelectDistrictAdapter);

        Intent intent = getIntent();
        if(intent == null)
            Log.e(TAG, "SelectDistrictActivity::setVp2DistrictSelectPage() intent is null.");
        else {
            double latitude = intent.getDoubleExtra("latitude", 0);
            double longitude = intent.getDoubleExtra("longitude", 0);

            final SelectDistrictService selectDistrictService = new SelectDistrictService(this);
            selectDistrictService.getAreaNearMe((float) latitude, (float) longitude);

            showProgressDialog();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onSuccessGetAreaNearMe(ArrayList<AreaNearMeInfo> areaNearMeInfo) {
        ArrayList<String> areaList = new ArrayList<>();
        for(AreaNearMeInfo area : areaNearMeInfo)
            areaList.add(area.getName());

        areaCategory[0] = new Pair<>("내 주변", areaList);

        final SelectDistrictService selectDistrictService = new SelectDistrictService(this);
        selectDistrictService.getDistrictList();
    }

    @Override
    public void onFailureGetAreaNearMe() {
        hideProgressDialog();
    }

    @Override
    public void onSuccessGetDistrictList(ArrayList<DistrictInfo> districtInfoList) {
        this.districtInfoList = districtInfoList;

        final SelectDistrictService selectDistrictService = new SelectDistrictService(this);

        for(DistrictInfo district : districtInfoList) {
            selectDistrictService.getAreaList(district.getDistinctsId());
        }
    }

    @Override
    public void onFailureGetDistrictList() {
        hideProgressDialog();
    }

    @Override
    public void onSuccessGetAreaList(int districtId, ArrayList<AreaInfo> areaInfoList) {
        ArrayList<String> areaNameList = new ArrayList<>();
        for(AreaInfo areaInfo : areaInfoList)
            areaNameList.add(areaInfo.getName());

        for(int i=0; i<districtInfoList.size(); i++)
            if(districtInfoList.get(i).getDistinctsId() == districtId) {
                areaCategory[i+1] = new Pair<>(districtInfoList.get(i).getName(), areaNameList);
                break;
            }

        boolean isCompeleteAreaCategory = true;
        for(int i=0; i<=districtInfoList.size(); i++)
            if(areaCategory[i] == null) {
                isCompeleteAreaCategory = false;
                break;
            }

        if(isCompeleteAreaCategory) {
            for(int i=0; i<=districtInfoList.size(); i++)
                if(areaCategory[i] != null)
                    vp2SelectDistrictAdapter.add(areaCategory[i]);

            vp2SelectDistrictAdapter.notifyDataSetChanged();

            TabLayout tabLayout = findViewById(R.id.sel_district_tab_layout_large_category);
            new TabLayoutMediator(tabLayout, vp2SelectDistrict, (tab, position) -> {
                tab.select();
                tab.setText(areaCategory[position].first);
            }).attach();

            hideProgressDialog();
        }
    }

    @Override
    public void onFailureGetAreaList() {
        hideProgressDialog();
    }
}

