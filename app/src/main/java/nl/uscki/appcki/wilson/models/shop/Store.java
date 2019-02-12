package nl.uscki.appcki.wilson.models.shop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import nl.uscki.appcki.wilson.models.IWilsonBaseItem;

public class Store implements IWilsonBaseItem {
    @Expose
    @SerializedName("id")
    public Integer id;
    @Expose
    @SerializedName("image")
    public Integer image;
    @Expose
    @SerializedName("description")
    public String description;
    @Expose
    @SerializedName("title")
    public String title;

    @Override
    public Integer getId() {
        return id;
    }
}
