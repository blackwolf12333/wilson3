package nl.uscki.appcki.wilson.viewmodel.agenda.page;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import nl.uscki.appcki.wilson.api.models.agenda.AgendaItem;

public class AgendaPageDataSourceFactory extends DataSource.Factory<Integer, AgendaItem> {
    MutableLiveData<AgendaPageDataSource> source = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource<Integer, AgendaItem> create() {
        AgendaPageDataSource s = new AgendaPageDataSource();
        source.postValue(s);
        return s;
    }
}
