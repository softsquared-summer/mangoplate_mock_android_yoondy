package com.softsquared.mangoplate.src.main.tab_search_restaurant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.main.MainActivity;
import com.softsquared.mangoplate.src.main.event.event_detail.EventDetailActivity;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.models.BannerAdInfo;

import java.util.ArrayList;

public class BannerAdsVp2Adapter extends RecyclerView.Adapter<BannerAdsVp2Adapter.AdViewHolder> {
    private ArrayList<BannerAdInfo> bannerAdInfoArrayList = new ArrayList<>();

    @NonNull
    @Override
    public AdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_banner_ad, parent, false);
        return new AdViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdViewHolder holder, int position) {
        holder.bind(bannerAdInfoArrayList.get(position));
    }

    public void add(BannerAdInfo bannerAdInfo) {
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

        void bind(BannerAdInfo bannerAdInfo) {
            Glide.with(itemView.getContext())
                    .load(bannerAdInfo.getImageUrl())
                    .into(ivAd);

            itemView.setOnTouchListener((v, event) -> {
                Context context = v.getContext();
                if (context instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) context;
                    mainActivity.vp2MainScreen.setUserInputEnabled(false);
                }
                v.performClick();
                return false;
            });

            ivAd.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), EventDetailActivity.class);
                intent.putExtra("eventId", bannerAdInfo.getEventId());
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
