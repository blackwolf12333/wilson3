package nl.uscki.appcki.wilson.api;

import nl.uscki.appcki.wilson.api.models.ActionResponse;
import nl.uscki.appcki.wilson.models.organisation.PersonSimple;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by peter on 12/30/16.
 */

public interface UserService {
    @FormUrlEncoded
    @POST("login")
    Call<Void> login(@Field("username") String username, @Field("password") String password);

    @GET("user/current")
    Call<PersonSimple> currentUser();

    @FormUrlEncoded
    @POST("notifications/android/register")
    Call<ActionResponse<Boolean>> registerDeviceId(@Field("token") String token);
}
