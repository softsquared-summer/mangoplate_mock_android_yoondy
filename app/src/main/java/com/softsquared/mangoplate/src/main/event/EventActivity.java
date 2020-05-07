package com.softsquared.mangoplate.src.main.event;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.ApplicationClass;
import com.softsquared.mangoplate.src.BaseActivity;
import com.softsquared.mangoplate.src.main.event.interfaces.EventActivityView;
import com.softsquared.mangoplate.src.main.event.models.EventInfo;
import com.softsquared.mangoplate.src.main.event.models.EventRvAdapter;

import java.util.ArrayList;

public class EventActivity extends BaseActivity implements EventActivityView {
    private EventRvAdapter eventInMyInfoRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        setView();
        setRv();
    }

    private void setView() {
        ImageView ivBackBtn = findViewById(R.id.event_iv_back_btn);
        ivBackBtn.setOnClickListener(v -> finish());
    }

    private void setRv() {
        eventInMyInfoRvAdapter = new EventRvAdapter();
        RecyclerView rvEventInMyInfo = findViewById(R.id.event_rv);
        rvEventInMyInfo.setHasFixedSize(true);
        rvEventInMyInfo.setLayoutManager(new LinearLayoutManager(this));
        rvEventInMyInfo.setAdapter(eventInMyInfoRvAdapter);

        final EventService eventService = new EventService(this);
        eventService.getEventInMyInfo();
        showProgressDialog();
    }

    @Override
    public void onSuccessGetEventInMyInfo(ArrayList<EventInfo> eventInfoList) {
        for(EventInfo e : eventInfoList)
            eventInMyInfoRvAdapter.add(e);

        eventInMyInfoRvAdapter.notifyDataSetChanged();
        hideProgressDialog();
    }

    @Override
    public void onFailureGetEventInMyInfo() {
        Log.d(ApplicationClass.TAG, "EventActivity::onFailureGetEventInMyInfo()");
        hideProgressDialog();
    }
}
