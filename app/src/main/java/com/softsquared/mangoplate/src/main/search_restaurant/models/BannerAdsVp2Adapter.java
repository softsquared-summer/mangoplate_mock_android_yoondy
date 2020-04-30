package com.softsquared.mangoplate.src.main.search_restaurant.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.main.MainActivity;

import java.util.ArrayList;

public class BannerAdsVp2Adapter extends RecyclerView.Adapter<BannerAdsVp2Adapter.AdViewHolder> {
    private ArrayList<BannerAdInfo> bannerAdInfoArrayList = new ArrayList<>();

    @NonNull
    @Override
    public BannerAdsVp2Adapter.AdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_banner_ad, parent, false);
        return new BannerAdsVp2Adapter.AdViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerAdsVp2Adapter.AdViewHolder holder, int position) {
        holder.bind(bannerAdInfoArrayList.get(position).getImageUrl());
    }

    public void addBannerAdInfo(BannerAdInfo bannerAdInfo) {
        bannerAdInfoArrayList.add(bannerAdInfo);
    }

    @Override
    public int getItemCount() {
        return bannerAdInfoArrayList.size();
    }

    class AdViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAd;

        AdViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAd = itemView.findViewById(R.id.banner_ad_vp2_iv_ad);
        }

        void bind(String url) {
            Glide.with(itemView.getContext()).load(url).into(ivAd);

            itemView.setOnTouchListener((v, event) -> {
                Context context = v.getContext();
                if (context instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) context;
                    mainActivity.vp2MainScreen.setUserInputEnabled(false);
                }
                v.performClick();
                return false;
            });

            itemView.setOnClickListener(v -> {
                // TODO: go to detail of event
            });
        }
    }
}
