package com.softsquared.mangoplate.src.main.tab_search_restaurant.search.search_recent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softsquared.mangoplate.R;
import com.softsquared.mangoplate.src.main.tab_search_restaurant.search.search_recent.models.SearchRecentRvAdapter;

public class SearchRecentFragment extends Fragment {
    private TextView tvGuidePhrase;

    public SearchRecentFragment() {
        // Required empty public constructor
    }

    public static SearchRecentFragment newInstance() {
        return new SearchRecentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_recent, container, false);

        setView(view);

        return view;
    }

    private void setView(View view) {
        tvGuidePhrase = view.findViewById(R.id.search_recent_tv_guide_phrase);

        SearchRecentRvAdapter adapter = new SearchRecentRvAdapter();
        RecyclerView rv = view.findViewById(R.id.search_recent_rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rv.setAdapter(adapter);
        rv.setItemAnimator(null);

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);

                if(adapter.getItemCount() > 0)
                    tvGuidePhrase.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);

                if(adapter.getItemCount() <= 0)
                    tvGuidePhrase.setVisibility(View.VISIBLE);
            }
        });

        // TODO: test. erase this later.
        addToAdapter(adapter);
    }

    private void addToAdapter(SearchRecentRvAdapter adapter) {
        adapter.add("족발");
        adapter.add("오비라거곰세권매장");
        adapter.add("보쌈");
        adapter.add("무한뷔페");

        tvGuidePhrase.setVisibility(View.INVISIBLE);
    }
}
