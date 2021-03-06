package nl.uscki.appcki.wilson.api;

import nl.uscki.appcki.wilson.api.models.ActionResponse;
import nl.uscki.appcki.wilson.api.models.agenda.Agenda;
import nl.uscki.appcki.wilson.api.models.agenda.AgendaItem;
import nl.uscki.appcki.wilson.api.models.agenda.AgendaParticipantLists;
import nl.uscki.appcki.wilson.api.models.comments.Comment;
import nl.uscki.appcki.wilson.api.models.comments.CommentPage;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by peter on 7/12/16.
 */
public interface AgendaService {
    @GET("agenda/{event}")
    Call<AgendaItem> get(@Path("event") Integer id);

    @GET("agenda/")
    Call<Agenda> agenda(@Query("page") Integer page, @Query("size") Integer size);

    @GET("agenda/")
    Call<Agenda> agenda(@Query("page") Integer page, @Query("size") Integer size, @Query("sort") Object sort);

    @GET("agenda/archive/")
    Call<Agenda> archive(@Query("page") Integer page, @Query("size") Integer size);

    @GET("agenda/archive/")
    Call<Agenda> archive(@Query("page") Integer page, @Query("size") Integer size, @Query("sort") Object sort);

    @GET("agenda/categories/")
    Call<Object> categories();

    @FormUrlEncoded
    @POST("agenda/{event}/subscribe")
    Call<ActionResponse<AgendaParticipantLists>> subscribe(@Path("event") Integer id, @Field("note") String note);

    @FormUrlEncoded
    @POST("agenda/{event}/subscribe")
    Call<ActionResponse<AgendaParticipantLists>> subscribe(@Path("event") Integer id, @Field("note") String note, @Field("answer") String answer);

    @FormUrlEncoded
    @POST("agenda/{id}/unsubscribe")
    Call<ActionResponse<AgendaParticipantLists>> unsubscribe(@Path("id") Integer id);

    @GET("agenda/{id}/comments/")
    Call<CommentPage> getComments(@Path("id") Integer agendaId, @Query("page") Integer page, @Query("size") Integer size);

    @POST("agenda/{id}/comments/add")
    Call<ActionResponse<Comment>> replyToComment(@Path("id") Integer agendaId, @Query("parentId") Integer parentId, @Query("comment") String comment);

    @DELETE("agenda/{id}/comments/{comment}")
    Call<Boolean> deleteComment(@Path("id") Integer agendaId, @Path("comment") Integer commentId);
}
