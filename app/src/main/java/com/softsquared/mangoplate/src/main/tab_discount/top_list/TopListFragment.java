package com.softsquared.mangoplate.src.main.tab_discount.top_list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.main.MainActivity;
import com.softsquared.mangoplate.src.main.tab_discount.top_list.models.TopListInfo;

public class TopListFragment extends Fragment {

    public TopListFragment() {
        // Required empty public constructor
    }

    public static TopListFragment newInstance() {
        return new TopListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_list, container, false);

        view.setOnTouchListener((v, event) -> {
            Context context = v.getContext();
            if (context instanceof MainActivity) {
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.vp2MainScreen.setUserInputEnabled(false);
            }
            v.performClick();
            return false;
        });

        setRv(view);

        return view;
    }

    private void setRv(View view) {
        TopListRvAdapter adapter = new TopListRvAdapter();
        RecyclerView rv = view.findViewById(R.id.top_list_rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rv.setAdapter(adapter);

        // TODO: test. erase this later.
        addToAdapter(adapter);
        addToAdapter(adapter);
        addToAdapter(adapter);
    }

    private void addToAdapter(TopListRvAdapter adapter) {
        TopListInfo info0 = new TopListInfo(
                "https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/ms8g3kkep7_5r02s.jpg",
                "오래된 한식당 맛집 베스트 50곳",
                "오래된 데에는 다 이유가 있지!",
                "4 일 전", "818,045", true);
        adapter.add(info0);
        TopListInfo info1 = new TopListInfo(
                "https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/p-riylfqolynj73p.png",
                "각종 모임을 위한 한식당 베스트 40곳",
                "실패할 확률은 없다! 손님 모시기 좋은 한식당",
                "9 시간 전", "179,143", false);
        adapter.add(info1);
        TopListInfo info2 = new TopListInfo(
                "https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/230x_xqgehxwwlfk.jpg",
                "각종 모임을 위한 중식당 베스트 30곳",
                "제대로 된 중식이야말로 대접하기 좋은 음식이지!",
                "1 일 전", "62,085", false);
        adapter.add(info2);
        TopListInfo info3 = new TopListInfo(
                "https://mp-seoul-image-production-s3.mangoplate.com/keyword_search/meta/pictures/sakb75za_eto4efs.jpg",
                "서울숲 맛집 베스트 35곳",
                "맛집 탐방 후 서울숲 산책하면 딱!",
                "2 일 전", "140,041", false);
        adapter.add(info3);
    }
}
