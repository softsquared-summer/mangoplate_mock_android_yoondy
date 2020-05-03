package com.softsquared.mangoplate.src.main.discount.models;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.softsquared.mangoplate.src.ApplicationClass;
import com.softsquared.mangoplate.src.main.discount.eat_deal.EatDealFragment;
import com.softsquared.mangoplate.src.main.discount.mango_pick_story.MangoPickStoryFragment;
import com.softsquared.mangoplate.src.main.discount.top_list.TopListFragment;

public class DiscountFragmentStateAdapter extends FragmentStateAdapter {
    private int mCount;

    public DiscountFragmentStateAdapter(@NonNull Fragment fragment, int count) {
        super(fragment);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position >= mCount)
            Log.e(ApplicationClass.TAG, "DiscountFragmentStateAdapter.createFragment(position: " + position + ")");

        if(position == 0) return EatDealFragment.newInstance();
        else if(position == 1) return MangoPickStoryFragment.newInstance();
        else return TopListFragment.newInstance();
    }

    @Override
    public int getItemCount() { return mCount; }
}
