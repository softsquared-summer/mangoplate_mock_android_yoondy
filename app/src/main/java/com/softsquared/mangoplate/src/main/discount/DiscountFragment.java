package com.softsquared.mangoplate.src.main.discount;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.main.MainActivity;
import com.softsquared.mangoplate.src.main.discount.models.DiscountFragmentStateAdapter;

public class DiscountFragment extends Fragment {
    public DiscountFragment() {
        // Required empty public constructor
    }

    public static DiscountFragment newInstance() {
        return new DiscountFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discount, container, false);

        setVp2(view);

        return view;
    }

    private void setVp2(View view) {
        TabLayout tabLayout = view.findViewById(R.id.discount_tab_layout);
        ViewPager2 vp2 = view.findViewById(R.id.discount_vp2);
        vp2.setAdapter(new DiscountFragmentStateAdapter(this, 3));
        vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                MainActivity mainActivity = (MainActivity) getActivity();
                if(mainActivity != null)
                    mainActivity.vp2MainScreen.setUserInputEnabled(true);
            }
        });

        new TabLayoutMediator(tabLayout, vp2, (tab, position) -> {
            if(position == 0) tab.setText(getString(R.string.eat_deal));
            else if(position == 1) tab.setText(getString(R.string.mango_pick_story));
            else tab.setText(getString(R.string.top_list));

            tab.select();
        }).attach();

    }
}
