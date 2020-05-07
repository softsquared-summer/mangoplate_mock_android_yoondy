package com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.interfaces;

import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.models.AreaNearMeInfo;

import java.util.ArrayList;

public interface SelectDistrictFragmentView {
    void onSuccessGetAreaNearMe(ArrayList<AreaNearMeInfo> areaNearMeInfo);

    void onFailureGetAreaNearMe();
}
