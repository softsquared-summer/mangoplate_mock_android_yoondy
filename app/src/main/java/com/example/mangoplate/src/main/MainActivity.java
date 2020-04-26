package com.example.mangoplate.src.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mangoplate.R;
import com.example.mangoplate.src.BaseActivity;
import com.example.mangoplate.src.main.discount.DiscountFragment;
import com.example.mangoplate.src.main.my_info.MyInfoFragment;
import com.example.mangoplate.src.main.search_restaurant.SearchRestaurantFragment;
import com.example.mangoplate.src.main.timeline.TimelineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_frame_layout, SearchRestaurantFragment.newInstance()).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.main_bot_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
        bottomNavigationView.setItemIconTintList(null);
    }

    private class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.item_search_restaurant: {
                    transaction.replace(R.id.main_frame_layout, SearchRestaurantFragment.newInstance())
                            .commitAllowingStateLoss();

                    break;
                }
                case R.id.item_discount: {
                    transaction.replace(R.id.main_frame_layout, DiscountFragment.newInstance())
                            .commitAllowingStateLoss();

                    break;
                }
                case R.id.item_addition: {
                    break;
                }
                case R.id.item_timeline: {
                    transaction.replace(R.id.main_frame_layout, TimelineFragment.newInstance())
                            .commitAllowingStateLoss();

                    break;
                }
                case R.id.item_my_info: {
                    transaction.replace(R.id.main_frame_layout, MyInfoFragment.newInstance())
                            .commitAllowingStateLoss();

                    break;
                }
            }
            return true;
        }
    }
}
