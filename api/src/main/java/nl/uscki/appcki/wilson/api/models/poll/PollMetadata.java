package nl.uscki.appcki.wilson.api.models.poll;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import nl.uscki.appcki.wilson.api.models.IWilsonBaseItem;
import nl.uscki.appcki.wilson.api.models.organisation.PersonSimpleName;

/**
 * Created by peter on 3/7/17.
 */

public class PollMetadata implements IWilsonBaseItem{
    /*{
"active": true,
"creation": "2017-03-07T13:31:42Z",
"id": 0,
"maker": {},
"title": "Bestaat vrije wil?"
}*/
    @Expose
    @SerializedName("active")
    Boolean active;
    @Expose
    @SerializedName("creation")
    String creation;
    @Expose
    @SerializedName("id")
    Integer id;
    @Expose
    @SerializedName("maker")
    PersonSimpleName maker;
    @Expose
    @SerializedName("title")
    String title;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public DateTime getCreation() {
        return new DateTime(creation);
    }

    public void setCreation(String creation) {
        this.creation = creation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PersonSimpleName getMaker() {
        return maker;
    }

    public void setMaker(PersonSimpleName maker) {
        this.maker = maker;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
