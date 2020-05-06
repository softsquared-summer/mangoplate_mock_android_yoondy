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

        // TODO: test. erase this later.
//        addToAdapter(eventInMyInfoRvAdapter);

        final EventService eventService = new EventService(this);
        eventService.getEventInMyInfo();
        showProgressDialog();
    }

    private void addToAdapter(EventRvAdapter adapter) {
        EventInfo info0 = new EventInfo("0",
                "https://i.imgur.com/SRtOkqA.png",
                "[맛집엔BC X IBK기업은행] 5만원 캐시백 이벤트! 마감 임박! 지금 지원해보세요!",
                "NEW",
                "57일 남음");
        adapter.add(info0);
        EventInfo info1 = new EventInfo("1",
                "https://i.imgur.com/VcdOcI6.png",
                "[Visa 프리미엄 회원 전용] 365일 EAT딜 할인이 2배!",
                "null",
                "241일 남음");
        adapter.add(info1);
        EventInfo info2 = new EventInfo("2",
                "https://i.imgur.com/mHFGJkv.png",
                "2020 망고플레이트 인기 맛집 어워즈",
                "null",
                "기한없음");
        adapter.add(info2);
        EventInfo info3 = new EventInfo("3",
                "https://i.imgur.com/7133b5E.png",
                "",
                "종료",
                "2020.3.17 ~ 2020.3.24");
        adapter.add(info3);
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
