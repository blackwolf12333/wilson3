package nl.uscki.appcki.wilson.viewmodel.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import nl.uscki.appcki.wilson.api.models.news.NewsItem;

public class NewsPageViewModel extends ViewModel {
    private LiveData<PagedList<NewsItem>> newsPageLiveData;
    private DataSource<Integer, NewsItem> newsPageDataSource;

    public LiveData<PagedList<NewsItem>> getNewsPageLiveData() {
        if (newsPageLiveData == null) {
            NewsPageDataSourceFactory factory = new NewsPageDataSourceFactory();
            newsPageDataSource = factory.create();
            newsPageLiveData = new LivePagedListBuilder<>(factory, 5).build();
        }

        return newsPageLiveData;
    }

    public void invalidate() {
        newsPageDataSource.invalidate();
    }
}
