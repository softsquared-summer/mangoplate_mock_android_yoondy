package com.softsquared.mangoplate.src.main.tab_search_restaurant.search.search_suggest.models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.mangoplate.R;

import java.util.ArrayList;

public class SearchSuggestRvAdapter extends RecyclerView.Adapter<SearchSuggestRvAdapter.SearchSuggestViewHolder> {
    private ArrayList<String> suggestionList = new ArrayList<>();

    @NonNull
    @Override
    public SearchSuggestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_suggest, parent, false);
        return new SearchSuggestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchSuggestViewHolder holder, int position) {
        holder.bind(suggestionList.get(position));
    }

    @Override
    public int getItemCount() { return suggestionList.size(); }

    public void add(String word) {
        suggestionList.add(word);
    }

    class SearchSuggestViewHolder extends RecyclerView.ViewHolder {
        TextView tvSearchWord;

        SearchSuggestViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSearchWord = itemView.findViewById(R.id.search_suggest_tv_search_word);
        }

        void bind(String searchWord) {
            tvSearchWord.setText(searchWord);
        }
    }
}
