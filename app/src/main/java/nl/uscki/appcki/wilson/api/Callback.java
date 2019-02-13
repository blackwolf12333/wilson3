package nl.uscki.appcki.wilson.api;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by peter on 1/1/17.
 */

public abstract class Callback<T> implements retrofit2.Callback<T> {
    protected String requestUrl = "";
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        requestUrl = call.request().url().toString();
        if(response.isSuccessful()) {
            onSucces(response);
        } else {
            handleError(response);
            onError(response);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        // TODO handle better
    }

    private void handleError(Response<T> response) {
    }

    public abstract void onSucces(Response<T> response);

    /**
     * Optional class for additional error handling on top of the default error handling
     * @param response  Original response
     */
    public void onError(Response<T> response) { }
}
