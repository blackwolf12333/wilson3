package nl.uscki.appcki.wilson.api;

import nl.uscki.appcki.wilson.api.models.common.Pageable;
import nl.uscki.appcki.wilson.api.models.media.MediaFileMetaData;
import nl.uscki.appcki.wilson.api.models.smobo.SmoboItem;
import nl.uscki.appcki.wilson.api.models.smobo.SmoboSearchPage;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by peter on 3/4/17.
 */

public interface SmoboService {
    @GET("people/{id}")
    Call<SmoboItem> get(@Path("id") Integer id);

    @GET("people/")
    Call<SmoboSearchPage> getPeopleCollection(@Query("page") Integer page, @Query("size") Integer size, @Query("sort") String sort);

    @GET("people/{id}/photos/")
    Call<Pageable<MediaFileMetaData>> photos(@Path("id") Integer id, @Query("page") Integer page, @Query("size") Integer size);

    @GET("people/search")
    Call<SmoboSearchPage> search(@Query("query") String query, @Query("page") Integer page, @Query("size") Integer size);

    @GET("people/search")
    Call<SmoboSearchPage> search(@Query("query") String query, @Query("page") Integer page, @Query("size") Integer size, @Query("sort") String sort);
}
