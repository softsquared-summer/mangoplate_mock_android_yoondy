package com.softsquared.mangoplate.src.main.event;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.ApplicationClass;
import com.softsquared.mangoplate.src.main.event.event_detail.EventDetailActivity;
import com.softsquared.mangoplate.src.main.event.models.EventInfo;

import java.util.ArrayList;

public class EventRvAdapter extends RecyclerView.Adapter<EventRvAdapter.EventViewHolder> {
    private ArrayList<EventInfo> eventInfoArrayList = new ArrayList<>();
    
    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.bind(eventInfoArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return eventInfoArrayList.size();
    }
    
    public void add(EventInfo eventInfo) {
        eventInfoArrayList.add(eventInfo);
    }

    class EventViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout clPanel;
        ImageView ivImg;
        ImageView ivSeparator;
        TextView tvTitle;
        TextView tvEnded;
        TextView tvDate;

        EventViewHolder(@NonNull View itemView) {
            super(itemView);
            clPanel = itemView.findViewById(R.id.event_const_layout_panel);
            ivImg = itemView.findViewById(R.id.event_iv_img);
            ivSeparator = itemView.findViewById(R.id.event_iv_separator);
            tvTitle = itemView.findViewById(R.id.event_tv_title);
            tvEnded = itemView.findViewById(R.id.event_tv_ended);
            tvDate = itemView.findViewById(R.id.event_tv_date);
        }

        void bind(EventInfo eventInfo) {

            Glide.with(itemView.getContext())
                    .load(eventInfo.getImageUrl())
                    .into(ivImg);

            String title = eventInfo.getTitle();
            if(title == null || title.isEmpty() || title.equals("null")) title = " ";
            tvTitle.setText(title);

            tvDate.setText(eventInfo.getDate());

            String status = eventInfo.getStatus();
            if(status == null || status.isEmpty() || status.equals("null"))
                tvEnded.setVisibility(View.GONE);
            else {
                tvEnded.setVisibility(View.VISIBLE);
                tvEnded.setText(status);

                if(status.equals("종료")) {
                    ivImg.setColorFilter(itemView.getResources().getColor(R.color.gray), PorterDuff.Mode.MULTIPLY);
                    clPanel.setBackground(itemView.getResources().getDrawable(R.drawable.bg_semitransparent_dimming));
                    ivSeparator.setColorFilter(itemView.getResources().getColor(android.R.color.white), PorterDuff.Mode.MULTIPLY);
                    tvEnded.setTextColor(itemView.getResources().getColor(R.color.darkOrange));
                    tvDate.setTextColor(itemView.getResources().getColor(R.color.gray));
                }
            }

            clPanel.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), EventDetailActivity.class);
                intent.putExtra("eventId", eventInfo.getEventId());
                itemView.getContext().startActivity(intent);
            });

            Log.d(ApplicationClass.TAG, "imgUrl: " + eventInfo.getImageUrl() + ", title: " + title + ", date: " + eventInfo.getDate() + ", status: " + eventInfo.getStatus());
        }
    }
}
