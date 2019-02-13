package nl.uscki.appcki.wilson.viewmodel.news;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import nl.uscki.appcki.wilson.models.news.NewsItem;

public class NewsPageDataSourceFactory extends DataSource.Factory<Integer, NewsItem> {
    MutableLiveData<NewsPageDataSource> source = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource<Integer, NewsItem> create() {
        NewsPageDataSource s = new NewsPageDataSource();
        source.postValue(s);
        return s;
    }
}
