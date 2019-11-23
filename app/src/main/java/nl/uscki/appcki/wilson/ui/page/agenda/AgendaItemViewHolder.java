package nl.uscki.appcki.wilson.ui.page.agenda;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.Navigator;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import nl.uscki.appcki.wilson.R;
import nl.uscki.appcki.wilson.api.MediaAPI;
import nl.uscki.appcki.wilson.api.models.agenda.AgendaItem;

class AgendaItemViewHolder extends RecyclerView.ViewHolder {
    AgendaItemViewHolder(View view) {
        super(view);
    }

    void clear() {

    }

    void bindTo(AgendaItem item) {
        if (item == null)
            return;

        TextView title = itemView.findViewById(R.id.agenda_page_item_title);
        title.setText(item.getTitle());

        TextView time = itemView.findViewById(R.id.agenda_page_item_time);
        time.setText(getTimeString(item));

        TextView location = itemView.findViewById(R.id.agenda_page_item_location);
        location.setText(item.getLocation());

        TextView cost = itemView.findViewById(R.id.agenda_page_item_cost);
        cost.setText(item.getCosts());

        AppCompatImageView posterImage = itemView.findViewById(R.id.agenda_page_item_top_image);


            Glide.with(itemView)
                    .load(MediaAPI.getMediaUrl(item.getPosterid()))
                    .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(16)))
                    .into(posterImage);

        NavController navController = Navigation.findNavController((AppCompatActivity) itemView.getContext(), R.id.nav_host_fragment);
        itemView.setOnClickListener((view) -> {
            AgendaPageFragmentDirections.ActionOpenAgendaDetail openAgendaDetailAction
                    = AgendaPageFragmentDirections.actionOpenAgendaDetail();
            openAgendaDetailAction.setAgendaItemId(item.getId());

            Navigator.Extras extras = null;
            if (item.getPosterid() != null)
                extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(posterImage, "poster_image")
                    .build();

            navController.navigate(openAgendaDetailAction, extras);
        });
    }

    private String getTimeString (AgendaItem item) {
        if (item.getStart().withTimeAtStartOfDay().equals(item.getEnd().withTimeAtStartOfDay())) {
            return item.getStart().withTimeAtStartOfDay().toString("dd MMM, YYYY")
                    + " "
                    + item.getStart().toString("HH:mm")
                    + " - "
                    + item.getEnd().toString("HH:mm");
        } else {
            return item.getStart().toString("dd MMM, YYYY")
                    + " - "
                    + item.getEnd().toString("dd MMM, YYYY");
        }
    }
}
