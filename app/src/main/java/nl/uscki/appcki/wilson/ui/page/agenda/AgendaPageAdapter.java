package nl.uscki.appcki.wilson.ui.page.agenda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import nl.uscki.appcki.wilson.R;
import nl.uscki.appcki.wilson.models.agenda.AgendaItem;

public class AgendaPageAdapter extends PagedListAdapter<AgendaItem, AgendaItemViewHolder> {

    protected AgendaPageAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public AgendaItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.agenda_page_item_fragment, parent, false);
        return new AgendaItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgendaItemViewHolder holder,
                                 int position) {
        AgendaItem newsItem = getItem(position);

        if (newsItem != null)
            holder.bindTo(newsItem);
        else
            holder.clear();
    }

    private static DiffUtil.ItemCallback<AgendaItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<AgendaItem>() {
                // The ID property identifies when items are the same.
                @Override
                public boolean areItemsTheSame(AgendaItem oldItem, AgendaItem newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                // Use Object.equals() to know when an item's content changes.
                // Implement equals(), or write custom data comparison logic here.
                @Override
                public boolean areContentsTheSame(AgendaItem oldItem, AgendaItem newItem) {
                    return oldItem.getTitle().equals(newItem.getTitle());
                }
            };
}
