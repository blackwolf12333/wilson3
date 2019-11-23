package nl.uscki.appcki.wilson.api.models.comments;

import com.google.gson.annotations.Expose;

import java.util.List;

import nl.uscki.appcki.wilson.api.models.IWilsonBaseItem;
import nl.uscki.appcki.wilson.api.models.organisation.PersonSimple;

public class Comment implements IWilsonBaseItem {
    @Expose
    public int id;
    @Expose
    public boolean announcement;
    @Expose
    public PersonSimple person;
    @Expose
    public List<Comment> reactions;
    @Expose
    public Long timestamp;
    @Expose
    public List<Object> comment; // List<Object> represents BB code

    @Override
    public Integer getId() {
        return id;
    }
}
