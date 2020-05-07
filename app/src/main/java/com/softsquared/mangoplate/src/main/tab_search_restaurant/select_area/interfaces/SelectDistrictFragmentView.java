package com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.interfaces;

import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.models.AreaInfo;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.models.AreaNearMeInfo;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.models.DistrictInfo;

import java.util.ArrayList;

public interface SelectDistrictFragmentView {
    void onSuccessGetAreaNearMe(ArrayList<AreaNearMeInfo> areaNearMeInfo);

    void onFailureGetAreaNearMe();

    void onSuccessGetDistrictList(ArrayList<DistrictInfo> districtInfoList);

    void onFailureGetDistrictList();

    void onSuccessGetAreaList(int districtId, ArrayList<AreaInfo> areaInfoList);

    void onFailureGetAreaList();
}
