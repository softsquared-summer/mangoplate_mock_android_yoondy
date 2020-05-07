package com.softsquared.mangoplate.src.main.tab_discount.top_list;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.ApplicationClass;
import com.softsquared.mangoplate.src.main.MainActivity;
import com.softsquared.mangoplate.src.main.tab_discount.top_list.models.TopListInfo;

import java.util.ArrayList;

public class TopListRvAdapter extends RecyclerView.Adapter<TopListRvAdapter.TopListViewHolder> {
    private ArrayList<TopListInfo> topListArrayList = new ArrayList<>();

    @NonNull
    @Override
    public TopListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_top_list, parent, false);
        return new TopListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopListViewHolder holder, int position) {
        holder.bind(topListArrayList.get(position), position);
    }

    @Override
    public int getItemCount() { return topListArrayList.size(); }

    public void add(TopListInfo topListInfo) {
        topListArrayList.add(topListInfo);
    }

    class TopListViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        ImageView ivTopViewMark;
        ImageView ivBookmark;
        TextView tvViewCount;
        TextView tvDate;
        TextView tvTitle;
        TextView tvSubtitle;

        TopListViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.top_list_iv_photo);
            ivTopViewMark = itemView.findViewById(R.id.top_list_iv_top_view_mark);
            ivBookmark = itemView.findViewById(R.id.top_list_iv_bookmark);
            tvViewCount = itemView.findViewById(R.id.top_list_tv_view_count);
            tvDate = itemView.findViewById(R.id.top_list_tv_date);
            tvTitle = itemView.findViewById(R.id.top_list_tv_title);
            tvSubtitle = itemView.findViewById(R.id.top_list_tv_subtitle);

            itemView.setOnTouchListener((v, event) -> {
                Context context = v.getContext();
                if (context instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) context;
                    mainActivity.vp2MainScreen.setUserInputEnabled(false);
                    Log.d(ApplicationClass.TAG, "vp2MainScreen.UserInputEnabled: " + (mainActivity.vp2MainScreen.isUserInputEnabled() ? "true" : "false"));
                }
                v.performClick();
                return false;
            });
        }

        void bind(TopListInfo topList, int position) {
            Glide.with(itemView.getContext())
                    .load(topList.getImageUrl())
                    .into(ivPhoto);

            ivTopViewMark.setVisibility(position == 0 ? View.VISIBLE : View.INVISIBLE);

            ivBookmark.setImageResource(topList.isBookmark() ?
                    R.drawable.ic_bookmark_filled_orange
                    : R.drawable.ic_bookmark_unfilled_long_gray);

            tvViewCount.setText(topList.getViewCount());
            tvDate.setText(topList.getDate());
            tvTitle.setText(topList.getTitle());
            tvSubtitle.setText(topList.getSubtitle());
        }
    }
}
