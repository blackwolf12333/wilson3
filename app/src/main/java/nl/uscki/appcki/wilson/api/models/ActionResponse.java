package nl.uscki.appcki.wilson.api.models;

import com.google.gson.annotations.Expose;

public class ActionResponse<T> {
    @Expose()
    public String message;
    @Expose()
    public T payload;
}
