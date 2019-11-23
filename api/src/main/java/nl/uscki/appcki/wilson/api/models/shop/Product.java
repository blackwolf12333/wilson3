package nl.uscki.appcki.wilson.api.models.shop;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import nl.uscki.appcki.wilson.api.models.IWilsonBaseItem;

public class Product implements IWilsonBaseItem {
    @Expose
    @SerializedName("id")
    public Integer id;
    @Expose
    @SerializedName("image")
    public Integer image;
    @Expose
    @SerializedName("price")
    public Double price;
    @Expose
    @SerializedName("stock")
    public Integer stock;
    @Expose
    @SerializedName("title")
    public String title;

    @Override
    public Integer getId() {
        return id;
    }
}
