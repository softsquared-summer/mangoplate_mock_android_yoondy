package com.softsquared.mangoplate.src.main.search_restaurant.select_area.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.mangoplate.R;

import java.util.ArrayList;

class MiddleAreaListRvAdapter extends RecyclerView.Adapter<MiddleAreaListRvAdapter.MiddleAreaViewHolder> {
    private ArrayList<String> areaArrayList = new ArrayList<>();

    @NonNull
    @Override
    public MiddleAreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_middle_area, parent, false);
        return new MiddleAreaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiddleAreaViewHolder holder, int position) {
        holder.bind(areaArrayList.get(position));
    }

    @Override
    public int getItemCount() { return areaArrayList.size(); }

    void addAllAreaList(ArrayList<String> areaList) {
        areaArrayList.addAll(areaList);
    }

    class MiddleAreaViewHolder extends RecyclerView.ViewHolder {
        Button btnArea;

        MiddleAreaViewHolder(@NonNull View itemView) {
            super(itemView);
            btnArea = itemView.findViewById(R.id.middle_area_btn);
        }

        void bind(String areaName) {
            btnArea.setText(areaName);
            btnArea.setSelected(false);
        }
    }
}
