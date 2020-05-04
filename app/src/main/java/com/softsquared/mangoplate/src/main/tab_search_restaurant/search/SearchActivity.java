package com.softsquared.mangoplate.src.main.tab_search_restaurant.search;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;
import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.BaseActivity;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.search.find_friend.FindFriendFragment;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.search.search_recent.SearchRecentFragment;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.search.search_suggest.SearchSuggestFragment;

public class SearchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setViews();
    }

    private void setViews() {
        FrameLayout flBackBtn = findViewById(R.id.search_frame_layout_back_btn);
        flBackBtn.setOnClickListener(v -> finish());

        TabLayout tabLayout = findViewById(R.id.search_tab_layout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                callFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        callFragment(0);
    }

    private void callFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(position == 0) {
            SearchSuggestFragment searchSuggestFragment = SearchSuggestFragment.newInstance();
            transaction.replace(R.id.search_frame_layout_fragment_container, searchSuggestFragment)
                    .commitAllowingStateLoss();
        }
        else if(position == 1) {
            SearchRecentFragment searchRecentFragment = SearchRecentFragment.newInstance();
            transaction.replace(R.id.search_frame_layout_fragment_container, searchRecentFragment)
                    .commitAllowingStateLoss();
        }
        else if(position == 2) {
            FindFriendFragment findFriendFragment = FindFriendFragment.newInstance();
            transaction.replace(R.id.search_frame_layout_fragment_container, findFriendFragment)
                    .commitAllowingStateLoss();
        }
        else {
            Fragment blankFragment = new Fragment();
            transaction.replace(R.id.search_frame_layout_fragment_container, blankFragment)
                    .commitAllowingStateLoss();
        }
    }
}
