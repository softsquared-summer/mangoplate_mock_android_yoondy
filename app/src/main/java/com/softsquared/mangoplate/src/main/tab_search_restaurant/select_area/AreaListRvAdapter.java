package com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.mangoplate.R;

import java.util.ArrayList;
import java.util.HashSet;

class AreaListRvAdapter extends RecyclerView.Adapter<AreaListRvAdapter.AreaViewHolder> {
    private ArrayList<String> areaArrayList = new ArrayList<>();

    @NonNull
    @Override
    public AreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_area, parent, false);
        return new AreaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaViewHolder holder, int position) {
        holder.bind(areaArrayList.get(position));
    }

    @Override
    public int getItemCount() { return areaArrayList.size(); }

    public void add(String area) { areaArrayList.add(area); }

    class AreaViewHolder extends RecyclerView.ViewHolder {
        Button btnArea;

        AreaViewHolder(@NonNull View itemView) {
            super(itemView);
            btnArea = itemView.findViewById(R.id.area_btn);
        }

        void bind(String areaName) {
            btnArea.setText(areaName);
            btnArea.setSelected(false);
            btnArea.setOnClickListener(v -> {
                if(v.getContext() instanceof SelectDistrictActivity) {
                    HashSet<String> hashSet = ((SelectDistrictActivity) v.getContext()).selectedItems;
                    if(v.isSelected()) {
                        hashSet.remove(areaName);
                        if(hashSet.isEmpty())
                            ((SelectDistrictActivity) v.getContext()).btnApply.setSelected(false);
                    }
                    else {
                        hashSet.add(areaName);
                        ((SelectDistrictActivity) v.getContext()).btnApply.setSelected(true);
                    }
                }
                btnArea.setTextColor(itemView.getResources().getColor(v.isSelected() ?
                        R.color.middleGray : R.color.orange));
                v.setSelected(!v.isSelected());
            });

        }
    }
}
