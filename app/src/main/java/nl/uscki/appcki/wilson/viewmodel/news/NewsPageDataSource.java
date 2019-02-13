package nl.uscki.appcki.wilson.viewmodel.news;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import java.io.IOException;

import nl.uscki.appcki.wilson.api.NetworkState;
import nl.uscki.appcki.wilson.api.Services;
import nl.uscki.appcki.wilson.models.news.NewsItem;
import nl.uscki.appcki.wilson.models.news.NewsOverview;
import retrofit2.Response;

public class NewsPageDataSource extends PageKeyedDataSource<Integer, NewsItem> {
    public MutableLiveData<NetworkState> networkState = new MutableLiveData<>();

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, NewsItem> callback) {
        networkState.postValue(NetworkState.LOADING);


        Response<NewsOverview> response = null;
        try {
            response = Services.getInstance()
                    .newsService
                    .getNewsCollection(0, params.requestedLoadSize)
                    .execute();

            if (response.isSuccessful()) {
                networkState.postValue(NetworkState.LOADED);
                NewsOverview newsPage = response.body();
                Log.e("NewsPageDataSource", response.body().getContent().size() + "");

                callback.onResult(
                        newsPage.getContent(),
                        newsPage.getNumber(),
                        newsPage.getNumberOfElements(),
                        newsPage.getNumber()-1,
                        newsPage.getNumber()+1
                );
            } else {
                networkState.postValue(NetworkState.LOADED);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, NewsItem> callback) {
        Response<NewsOverview> response = null;
        try {
            response = Services.getInstance()
                    .newsService
                    .getNewsCollection(params.key, params.requestedLoadSize)
                    .execute();

            if (response.isSuccessful()) {
                networkState.postValue(NetworkState.LOADED);
                NewsOverview newsPage = response.body();

                callback.onResult(
                        newsPage.getContent(),
                        newsPage.getNumber()+1
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams params, @NonNull LoadCallback callback) {

    }
}
