package nl.uscki.appcki.wilson.models.meeting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import nl.uscki.appcki.wilson.models.IWilsonBaseItem;
import nl.uscki.appcki.wilson.models.organisation.PersonSimpleName;

public class Participation  implements IWilsonBaseItem {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("person")
    @Expose
    private PersonSimpleName person;
    @SerializedName("preferences")
    @Expose
    private List<Preference> preferences;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The person
     */
    public PersonSimpleName getPerson() {
        return person;
    }

    /**
     *
     * @param person
     * The person
     */
    public void setPerson(PersonSimpleName person) {
        this.person = person;
    }

    public List<Preference> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Preference> preferences) {
        this.preferences = preferences;
    }
}