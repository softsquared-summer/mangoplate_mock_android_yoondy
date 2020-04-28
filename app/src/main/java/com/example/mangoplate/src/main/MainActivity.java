package com.example.mangoplate.src.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Point;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.mangoplate.R;
import com.example.mangoplate.src.ApplicationClass;
import com.example.mangoplate.src.BaseActivity;
import com.example.mangoplate.src.main.discount.DiscountFragment;
import com.example.mangoplate.src.main.models.MainFragmentStateAdapter;
import com.example.mangoplate.src.main.my_info.MyInfoFragment;
import com.example.mangoplate.src.main.search_restaurant.SearchRestaurantFragment;
import com.example.mangoplate.src.main.timeline.TimelineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.sephiroth.android.library.viewrevealanimator.ViewRevealAnimator;

public class MainActivity extends BaseActivity {
    private MainFragmentStateAdapter vp2MainScreenAdapter;
    private ViewRevealAnimator viewRevealAnimator;
    private BottomNavigationView botNav;
    private ViewPager2 vp2MainScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setVp2();
        initView();
    }

    private void setVp2() {
        vp2MainScreenAdapter = new MainFragmentStateAdapter(this, 4);
        vp2MainScreen = findViewById(R.id.main_vp2_main_screen);
        vp2MainScreen.setAdapter(vp2MainScreenAdapter);
        vp2MainScreen.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        vp2MainScreen.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if(position == 0) botNav.setSelectedItemId(R.id.item_search_restaurant);
                else if(position == 1) botNav.setSelectedItemId(R.id.item_discount);
                else if(position == 2) botNav.setSelectedItemId(R.id.item_timeline);
                else if(position == 3) botNav.setSelectedItemId(R.id.item_my_info);
            }
        });
    }

    private void initView() {
        botNav = findViewById(R.id.main_bot_nav);
        botNav.setOnNavigationItemSelectedListener(new ItemSelectedListener());
        botNav.setItemIconTintList(null);

        viewRevealAnimator = findViewById(R.id.main_anim_center_btn_animator);

        ImageButton btnCloseAddition = findViewById(R.id.main_btn_close_addition);
        btnCloseAddition.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewRevealAnimator.setDisplayedChild(
                        viewRevealAnimator.getDisplayedChild() + 1,
                        true,
                        new Point(ApplicationClass.screenWidth/2,
                                ApplicationClass.screenHeight - botNav.getHeight())
                );
            }
        });
    }

    private class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.item_search_restaurant: {
                    vp2MainScreen.setCurrentItem(0);
                    break;
                }
                case R.id.item_discount: {
                    vp2MainScreen.setCurrentItem(1);
                    break;
                }
                case R.id.item_addition: {
                    viewRevealAnimator.setDisplayedChild(
                            viewRevealAnimator.getDisplayedChild() + 1,
                            true,
                            new Point(ApplicationClass.screenWidth/2,
                                    ApplicationClass.screenHeight - botNav.getHeight())
                    );
                    break;
                }
                case R.id.item_timeline: {
                    vp2MainScreen.setCurrentItem(2);
                    break;
                }
                case R.id.item_my_info: {
                    vp2MainScreen.setCurrentItem(3);
                    break;
                }
            }
            return true;
        }
    }
}
