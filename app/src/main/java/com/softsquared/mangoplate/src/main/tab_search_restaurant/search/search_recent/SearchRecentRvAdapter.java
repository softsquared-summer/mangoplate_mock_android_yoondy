package com.softsquared.mangoplate.src.main.tab_search_restaurant.search.search_recent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.mangoplate.R;

import java.util.ArrayList;

public class SearchRecentRvAdapter extends RecyclerView.Adapter<SearchRecentRvAdapter.SearchRecentViewHolder> {
    private ArrayList<String> recentList = new ArrayList<>();

    @NonNull
    @Override
    public SearchRecentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_recent, parent, false);
        return new SearchRecentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecentViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() { return recentList.size(); }

    public void add(String word) {
        recentList.add(word);
    }

    class SearchRecentViewHolder extends RecyclerView.ViewHolder {
        TextView tvSearchWord;
        ImageView ivDeleteBtn;

        SearchRecentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSearchWord = itemView.findViewById(R.id.search_recent_tv_search_word);
            ivDeleteBtn = itemView.findViewById(R.id.search_recent_iv_delete);
        }

        void bind(int position) {
            tvSearchWord.setText(recentList.get(position));

            ivDeleteBtn.setOnClickListener(v -> {
                recentList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, recentList.size());
            });
        }
    }
}
