package nl.uscki.appcki.wilson.viewmodel.agenda.item;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import nl.uscki.appcki.wilson.api.Callback;
import nl.uscki.appcki.wilson.api.Services;
import nl.uscki.appcki.wilson.api.models.agenda.AgendaItem;
import retrofit2.Response;

public class AgendaItemViewModel extends ViewModel {
    private MutableLiveData<AgendaItem> agendaItemLiveData;

    public MutableLiveData<AgendaItem> getAgendaItemLiveData(Integer agendaItemId) {
        if (this.agendaItemLiveData == null) {
            this.agendaItemLiveData = new MutableLiveData<>();
            this.initialize(agendaItemId);
        }

        return agendaItemLiveData;
    }

    private void initialize(Integer agendaItemId) {
        Services.getInstance().agendaService.get(agendaItemId).enqueue(new Callback<AgendaItem>() {
            @Override
            public void onSucces(Response<AgendaItem> response) {
                AgendaItemViewModel.this.agendaItemLiveData.postValue(response.body());
            }
        });
    }
}
