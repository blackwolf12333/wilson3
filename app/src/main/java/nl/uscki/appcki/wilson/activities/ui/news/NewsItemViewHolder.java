package nl.uscki.appcki.wilson.activities.ui.news;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import nl.uscki.appcki.wilson.R;
import nl.uscki.appcki.wilson.models.news.NewsItem;

public class NewsItemViewHolder extends RecyclerView.ViewHolder {
    public NewsItemViewHolder (View view) {
        super(view);
    }

    public void bindTo(NewsItem item) {
        if (item == null)
            return;

        TextView title = itemView.findViewById(R.id.news_item_title);
        title.setText(item.getTitle());
    }
}
