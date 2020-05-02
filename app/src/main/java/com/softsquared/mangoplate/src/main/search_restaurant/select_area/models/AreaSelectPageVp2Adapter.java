package com.softsquared.mangoplate.src.main.search_restaurant.select_area.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.mangoplate.R;

import java.util.ArrayList;

public class AreaSelectPageVp2Adapter extends RecyclerView.Adapter<AreaSelectPageVp2Adapter.SelectPageViewHolder>{
    private ArrayList<ArrayList<String> > areaListOfList = new ArrayList<>();

    @NonNull
    @Override
    public SelectPageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_area_select_page, parent, false);
        return new SelectPageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectPageViewHolder holder, int position) {
        holder.bind(areaListOfList.get(position));
    }

    @Override
    public int getItemCount() {
        return areaListOfList.size();
    }

    public void add(ArrayList<String> areaList) {
        areaListOfList.add(areaList);
    }

    class SelectPageViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rvMiddleAreaList;
        MiddleAreaListRvAdapter rvMiddleAreaListAdapter = new MiddleAreaListRvAdapter();

        SelectPageViewHolder(@NonNull View itemView) {
            super(itemView);
            rvMiddleAreaList = itemView.findViewById(R.id.area_sel_page_rv_middle_area_list);
            rvMiddleAreaList.setLayoutManager(new GridLayoutManager(itemView.getContext(), 2));
            rvMiddleAreaList.setAdapter(rvMiddleAreaListAdapter);
        }

        void bind(ArrayList<String> areaList) {
            rvMiddleAreaListAdapter.addAllAreaList(areaList);
        }
    }
}
