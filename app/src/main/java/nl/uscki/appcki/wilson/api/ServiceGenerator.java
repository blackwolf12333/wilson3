package nl.uscki.appcki.wilson.api;

import java.io.IOException;

import nl.uscki.appcki.wilson.UserHelper;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by peter on 7/12/16.
 */
public class ServiceGenerator {

    public static final String API_BASE_URL = "https://api.dev.uscki.nl/api-dev/api/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static OkHttpClient client;

    public static void init() {
        LoggingInterceptor logging = new LoggingInterceptor();
        // set your desired log level
        logging.setLevel(LoggingInterceptor.Level.BODY);
        logging.addFilter("www.uscki.nl").addFilter("api/media/");
        //httpClient.addInterceptor(logging);// TODO uncomment voor debug output

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                if(UserHelper.getInstance().getToken() == null) {
                    return chain.proceed(original);
                }

                if (!original.url().host().contains("uscki.nl"))
                    return chain.proceed(original);

                Request.Builder requestBuilder = original.newBuilder()
                        .header("X-AUTH-TOKEN", UserHelper.getInstance().getToken())
                        .method(original.method(), original.body());

                return chain.proceed(requestBuilder.build());
            }
        });

        client = httpClient.build();
    }

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
}