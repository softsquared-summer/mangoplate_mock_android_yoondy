package com.softsquared.mangoplate.src.main.tab_search_restaurant.select_area;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.mangoplate.R;

import java.util.ArrayList;

public class SelectDistrictVp2Adapter extends RecyclerView.Adapter<SelectDistrictVp2Adapter.SelectDistrictViewHolder>{
    private ArrayList<Pair<String, ArrayList<String>>> areaCategory = new ArrayList<>();

    @NonNull
    @Override
    public SelectDistrictViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sel_area, parent, false);
        return new SelectDistrictViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectDistrictViewHolder holder, int position) {
        holder.bind(areaCategory.get(position));
    }

    @Override
    public int getItemCount() {
        return areaCategory.size();
    }

    public void add(Pair<String, ArrayList<String>> areaPair) {
        areaCategory.add(areaPair);
    }

    class SelectDistrictViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rvAreaList;
        AreaListRvAdapter rvAreaListAdapter = new AreaListRvAdapter();

        SelectDistrictViewHolder(@NonNull View itemView) {
            super(itemView);
            rvAreaList = itemView.findViewById(R.id.sel_area_rv_area_list);
            rvAreaList.setLayoutManager(new GridLayoutManager(itemView.getContext(), 2));
            rvAreaList.setAdapter(rvAreaListAdapter);
        }

        void bind(Pair<String, ArrayList<String>> areaList) {
            for(String area : areaList.second)
                rvAreaListAdapter.add(area);
        }
    }
}
