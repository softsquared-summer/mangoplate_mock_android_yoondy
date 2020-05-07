package com.softsquared.mangoplate.src.main;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.softsquared.mangoplate.src.ApplicationClass;
import com.softsquared.mangoplate.src.main.tab_discount.DiscountFragment;
import com.softsquared.mangoplate.src.main.tab_discount.top_list.TopListFragment;
import com.softsquared.mangoplate.src.main.tab_my_info.MyInfoFragment;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.SearchRestaurantFragment;
import com.softsquared.mangoplate.src.main.tab_timeline.TimelineFragment;

public class MainFragmentStateAdapter extends FragmentStateAdapter {
    private int mCount;

    MainFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity, int count) {
        super(fragmentActivity);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position >= mCount)
            Log.e(ApplicationClass.TAG, "MainFragmentStateAdapter.createFragment(position: " + position + ")");

        if(position == 0) return SearchRestaurantFragment.newInstance();
//        else if(position == 1) return DiscountFragment.newInstance();
        else if(position == 1) return TopListFragment.newInstance();
        else if(position == 2) return TimelineFragment.newInstance();
        else return MyInfoFragment.newInstance();
    }

    @Override
    public int getItemCount() { return mCount; }
}
