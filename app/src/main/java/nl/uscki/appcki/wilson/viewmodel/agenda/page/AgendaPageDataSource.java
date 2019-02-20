package nl.uscki.appcki.wilson.viewmodel.agenda.page;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import java.io.IOException;

import nl.uscki.appcki.wilson.api.Services;
import nl.uscki.appcki.wilson.models.agenda.Agenda;
import nl.uscki.appcki.wilson.models.agenda.AgendaItem;
import retrofit2.Response;

public class AgendaPageDataSource extends PageKeyedDataSource<Integer, AgendaItem> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, AgendaItem> callback) {
        Response<Agenda> response = null;
        try {
            response = Services.getInstance()
                    .agendaService
                    .agenda(0, params.requestedLoadSize)
                    .execute();

            if (response.isSuccessful()) {
                Agenda newsPage = response.body();

                callback.onResult(
                        newsPage.getContent(),
                        newsPage.getNumber(),
                        newsPage.getNumberOfElements(),
                        newsPage.getNumber()-1,
                        newsPage.getNumber()+1
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, AgendaItem> callback) {
        Log.e("AgendaPageDataSource", "loadAfter" + params.key);
        Response<Agenda> response = null;
        try {
            response = Services.getInstance()
                    .agendaService
                    .agenda(params.key, params.requestedLoadSize)
                    .execute();

            if (response.isSuccessful()) {
                Agenda newsPage = response.body();

                callback.onResult(
                        newsPage.getContent(),
                        newsPage.getNumber()+1
                );
            } else {
                Log.e("AgendaPageDataSource", response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }
}
