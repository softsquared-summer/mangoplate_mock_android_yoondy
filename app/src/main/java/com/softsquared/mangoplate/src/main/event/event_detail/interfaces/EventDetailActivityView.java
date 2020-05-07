package com.softsquared.mangoplate.src.main.event.event_detail.interfaces;

import com.softsquared.mangoplate.src.main.event.event_detail.models.EventDetailInfo;

public interface EventDetailActivityView {
    void onSuccessGetEventDetail(EventDetailInfo eventDetailInfo);

    void onFailureGetEventDetail();
}
