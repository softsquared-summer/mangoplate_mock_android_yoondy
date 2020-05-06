package com.softsquared.mangoplate.src.main.event.interfaces;

import com.softsquared.mangoplate.src.main.event.models.EventInfo;

import java.util.ArrayList;

public interface EventActivityView {
    void onSuccessGetEventInMyInfo(ArrayList<EventInfo> eventInfoList);
    
    void onFailureGetEventInMyInfo();
}
