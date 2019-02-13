package nl.uscki.appcki.wilson.activities.ui.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import nl.uscki.appcki.wilson.R;
import nl.uscki.appcki.wilson.models.news.NewsItem;

public class NewsPageAdapter extends PagedListAdapter<NewsItem, NewsItemViewHolder> {

    protected NewsPageAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public NewsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_fragment, parent, false);
        return new NewsItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsItemViewHolder holder,
                                 int position) {
        NewsItem NewsItem = getItem(position);

        // Note that "NewsItem" can be null if it's a placeholder.
        holder.bindTo(NewsItem);
    }
    
    private static DiffUtil.ItemCallback<NewsItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<NewsItem>() {
                // The ID property identifies when items are the same.
                @Override
                public boolean areItemsTheSame(NewsItem oldItem, NewsItem newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                // Use Object.equals() to know when an item's content changes.
                // Implement equals(), or write custom data comparison logic here.
                @Override
                public boolean areContentsTheSame(NewsItem oldItem, NewsItem newItem) {
                    return oldItem.getTitle().equals(newItem.getTitle());
                }
            };
}
