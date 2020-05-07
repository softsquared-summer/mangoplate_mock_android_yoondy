package com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area;

import android.util.Log;

import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.interfaces.SelectDistrictFragmentView;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.interfaces.SelectDistrictRetrofitInterface;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.models.AreaListResponse;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.models.AreaNearMeInfo;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.models.AreaNearMeResponse;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area.models.DistrictListResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.softsquared.mangoplate.src.ApplicationClass.TAG;
import static com.softsquared.mangoplate.src.ApplicationClass.getRetrofit;

class SelectDistrictService {
    private final SelectDistrictFragmentView selectDistrictFragmentView;

    SelectDistrictService(SelectDistrictFragmentView selectDistrictFragmentView) {
        this.selectDistrictFragmentView = selectDistrictFragmentView;
    }

    void getAreaNearMe(float lat, float lng) {
        final SelectDistrictRetrofitInterface selectDistrictRetrofitInterface = getRetrofit().create(SelectDistrictRetrofitInterface.class);
        selectDistrictRetrofitInterface.getAreaNearMe(lat, lng).enqueue(new Callback<AreaNearMeResponse>() {
            @Override
            public void onResponse(@NotNull Call<AreaNearMeResponse> call, @NotNull Response<AreaNearMeResponse> response) {
                AreaNearMeResponse areaNearMeResponse = response.body();
                if(areaNearMeResponse == null) {
                    Log.d(TAG, "SelectDistrictService::getAreaNearMe() fail. areaNearMeResponse is null");
                    selectDistrictFragmentView.onFailureGetAreaNearMe();
                    return;
                }
                else if(!areaNearMeResponse.isSuccess()) {
                    Log.d(TAG, "SelectDistrictService::getAreaNearMe() fail. areaNearMeResponse code: " + areaNearMeResponse.getCode());
                    Log.d(TAG, "SelectDistrictService::getAreaNearMe() fail. areaNearMeResponse message: " + areaNearMeResponse.getMessage());
                    selectDistrictFragmentView.onFailureGetAreaNearMe();
                    return;
                }

                Log.d(TAG, "SelectDistrictService::getAreaNearMe() success. areaNearMeResponse code: " + areaNearMeResponse.getCode());
                Log.d(TAG, "SelectDistrictService::getAreaNearMe() success. areaNearMeResponse message: " + areaNearMeResponse.getMessage());
                ArrayList<AreaNearMeInfo> result = areaNearMeResponse.getResult();
                for(AreaNearMeInfo area : result)
                    Log.d(TAG, "SelectDistrictService::getAreaNearMe() success. areaNearMeResponse result: " + area.getName());

                selectDistrictFragmentView.onSuccessGetAreaNearMe(areaNearMeResponse.getResult());
            }

            @Override
            public void onFailure(@NotNull Call<AreaNearMeResponse> call, @NotNull Throwable t) {
                Log.d(TAG, "SelectDistrictService::getAreaNearMe() fail : " + t);
                selectDistrictFragmentView.onFailureGetAreaNearMe();
            }
        });
    }

    void getDistrictList() {
        final SelectDistrictRetrofitInterface selectDistrictRetrofitInterface = getRetrofit().create(SelectDistrictRetrofitInterface.class);
        selectDistrictRetrofitInterface.getDistrictList().enqueue(new Callback<DistrictListResponse>() {
            @Override
            public void onResponse(@NotNull Call<DistrictListResponse> call, @NotNull Response<DistrictListResponse> response) {
                DistrictListResponse districtListResponse = response.body();
                if(districtListResponse == null) {
                    Log.d(TAG, "SelectDistrictService::getDistrictList() fail. districtResponse is null");
                    selectDistrictFragmentView.onFailureGetDistrictList();
                    return;
                }
                else if(!districtListResponse.isSuccess()) {
                    Log.d(TAG, "SelectDistrictService::getDistrictList() fail. districtResponse code: " + districtListResponse.getCode());
                    Log.d(TAG, "SelectDistrictService::getDistrictList() fail. districtResponse message: " + districtListResponse.getMessage());
                    selectDistrictFragmentView.onFailureGetDistrictList();
                    return;
                }

                selectDistrictFragmentView.onSuccessGetDistrictList(districtListResponse.getResult());
            }

            @Override
            public void onFailure(@NotNull Call<DistrictListResponse> call, @NotNull Throwable t) {
                Log.d(TAG, "SelectDistrictService::getDistrictList() fail : " + t);
                selectDistrictFragmentView.onFailureGetDistrictList();
            }
        });
    }

    void getAreaList(int districtId) {
        final SelectDistrictRetrofitInterface selectDistrictRetrofitInterface = getRetrofit().create(SelectDistrictRetrofitInterface.class);
        selectDistrictRetrofitInterface.getAreaList(districtId).enqueue(new Callback<AreaListResponse>() {
            @Override
            public void onResponse(@NotNull Call<AreaListResponse> call, @NotNull Response<AreaListResponse> response) {
                AreaListResponse areaListResponse = response.body();
                if(areaListResponse == null) {
                    Log.d(TAG, "SelectDistrictService::getAreaList() fail. areaListResponse is null");
                    selectDistrictFragmentView.onFailureGetAreaList();
                    return;
                }
                else if(!areaListResponse.isSuccess()) {
                    Log.d(TAG, "SelectDistrictService::getAreaList() fail. areaListResponse code: " + areaListResponse.getCode());
                    Log.d(TAG, "SelectDistrictService::getAreaList() fail. areaListResponse message: " + areaListResponse.getMessage());
                    selectDistrictFragmentView.onFailureGetAreaList();
                    return;
                }

                selectDistrictFragmentView.onSuccessGetAreaList(districtId, areaListResponse.getResult());
            }

            @Override
            public void onFailure(@NotNull Call<AreaListResponse> call, @NotNull Throwable t) {
                Log.d(TAG, "SelectDistrictService::getAreaList() fail : " + t);
                selectDistrictFragmentView.onFailureGetAreaList();
            }
        });
    }
}
