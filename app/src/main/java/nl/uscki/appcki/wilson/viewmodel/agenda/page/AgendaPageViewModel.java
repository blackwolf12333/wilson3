package nl.uscki.appcki.wilson.viewmodel.agenda.page;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import nl.uscki.appcki.wilson.api.models.agenda.AgendaItem;

public class AgendaPageViewModel extends ViewModel {
    private LiveData<PagedList<AgendaItem>> agendaPageLiveData;
    private DataSource<Integer, AgendaItem> agendaPageDataSource;

    public LiveData<PagedList<AgendaItem>> getAgendaPageLiveData() {
        if (agendaPageLiveData == null) {
            AgendaPageDataSourceFactory factory = new AgendaPageDataSourceFactory();
            agendaPageDataSource = factory.create();
            agendaPageLiveData = new LivePagedListBuilder<>(factory, 10).build();
        }

        return agendaPageLiveData;
    }

    public void invalidate() {
        agendaPageDataSource.invalidate();
    }
}
