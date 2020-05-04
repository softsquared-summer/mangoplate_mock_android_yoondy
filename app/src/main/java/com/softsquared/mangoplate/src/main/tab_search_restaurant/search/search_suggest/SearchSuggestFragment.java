package com.softsquared.mangoplate.src.main.tab_search_restaurant.search.search_suggest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.search.search_suggest.models.SearchSuggestRvAdapter;

public class SearchSuggestFragment extends Fragment {
    public SearchSuggestFragment() {
        // Required empty public constructor
    }

    public static SearchSuggestFragment newInstance() {
        return new SearchSuggestFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_suggest, container, false);

        setView(view);

        return view;
    }

    private void setView(View view) {
        SearchSuggestRvAdapter adapter = new SearchSuggestRvAdapter();
        RecyclerView rv = view.findViewById(R.id.search_suggest_rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rv.setAdapter(adapter);

        // TODO: This is just dummy data. We decided to replace this function with dummy data.
        addToAdapter(adapter);
    }

    private void addToAdapter(SearchSuggestRvAdapter adapter) {
        adapter.add("오비라거곰세권매장");
        adapter.add("오래된한식당");
        adapter.add("제주");
        adapter.add("파스타");
        adapter.add("현대백화점신촌점할인");
        adapter.add("강원도");
    }
}
