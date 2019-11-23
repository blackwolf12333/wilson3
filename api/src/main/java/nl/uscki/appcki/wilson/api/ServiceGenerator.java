package nl.uscki.appcki.wilson.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by peter on 7/12/16.
 */
public class ServiceGenerator {

    static final String API_BASE_URL = "https://api.dev.uscki.nl/v2/api/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static OkHttpClient client;
    static IUserHelper userHelper;

    public static void init(IUserHelper userHelper) {
        ServiceGenerator.userHelper = userHelper;

        LoggingInterceptor logging = new LoggingInterceptor();
        // set your desired log level
        logging.setLevel(LoggingInterceptor.Level.BODY);
        logging.addFilter("www.uscki.nl").addFilter("api/media/");
        httpClient.addInterceptor(logging);// TODO uncomment voor debug output

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            if(userHelper.getToken() == null) {
                return chain.proceed(original);
            }

            if (!original.url().host().contains("uscki.nl"))
                return chain.proceed(original);

            Request.Builder requestBuilder = original.newBuilder()
                    .header("Authorization", userHelper.getToken())
                    .method(original.method(), original.body());

            return chain.proceed(requestBuilder.build());
        });

        client = httpClient.build();
    }

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
}
