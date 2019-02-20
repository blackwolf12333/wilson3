package nl.uscki.appcki.wilson.ui.detail.agenda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;

import nl.uscki.appcki.wilson.R;
import nl.uscki.appcki.wilson.api.MediaAPI;
import nl.uscki.appcki.wilson.viewmodel.agenda.item.AgendaItemViewModel;

public class AgendaDetailFragment extends Fragment {
    AgendaItemViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.viewModel = ViewModelProviders.of(this).get(AgendaItemViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // VIEW INIT
        View view = inflater.inflate(R.layout.agenda_detail_fragment, container, false);

        final TextView title = view.findViewById(R.id.agenda_item_title);
        final ImageView poster = view.findViewById(R.id.agenda_item_top_image);

        // DATA INIT
        Integer agendaItemId = AgendaDetailFragmentArgs.fromBundle(getArguments()).getAgendaItemId();
        this.viewModel.getAgendaItemLiveData(agendaItemId).observe(this, agendaItem -> {
            title.setText(agendaItem.getTitle());

            Glide.with(this)
                    .load(MediaAPI.getMediaUrl(agendaItem.getPosterid(), MediaAPI.MediaSize.NORMAL))
                    .into(poster);
        });

        return view;
    }
}
