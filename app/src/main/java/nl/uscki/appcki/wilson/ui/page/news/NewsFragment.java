package nl.uscki.appcki.wilson.ui.page.news;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import nl.uscki.appcki.wilson.R;
import nl.uscki.appcki.wilson.activities.MainActivity;
import nl.uscki.appcki.wilson.viewmodel.news.NewsPageViewModel;

public class NewsFragment extends Fragment {
    private NewsPageViewModel model;
    private NewsPageAdapter adapter;

    private MainActivity context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.model = ViewModelProviders.of(this).get(NewsPageViewModel.class);
        this.adapter = new NewsPageAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_page_fragment, container, false);

        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.news_page_swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(() -> model.invalidate());

        RecyclerView recyclerView = view.findViewById(R.id.news_page_recyclerview);
        recyclerView.setAdapter(this.adapter);

        this.model.getNewsPageLiveData().observe(this.context, data -> {
            this.adapter.submitList(data);
            swipeRefreshLayout.setRefreshing(false);
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            this.context = (MainActivity) context;
        }
    }
}
