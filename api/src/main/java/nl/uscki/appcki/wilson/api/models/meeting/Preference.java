package nl.uscki.appcki.wilson.api.models.meeting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import nl.uscki.appcki.wilson.api.models.IWilsonBaseItem;
import nl.uscki.appcki.wilson.api.models.organisation.PersonSimple;

public class Preference  implements IWilsonBaseItem {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("canattend")
    @Expose
    private Boolean canattend;
    @SerializedName("person")
    @Expose
    private PersonSimple person;

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
     * The notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     *
     * @param notes
     * The notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     *
     * @return
     * The canattend
     */
    public Boolean getCanattend() {
        return canattend;
    }

    /**
     *
     * @param canattend
     * The canattend
     */
    public void setCanattend(Boolean canattend) {
        this.canattend = canattend;
    }

    /**
     *
     * @return
     * The person
     */
    public PersonSimple getPerson() {
        return person;
    }

    /**
     *
     * @param person
     * The person
     */
    public void setPerson(PersonSimple person) {
        this.person = person;
    }

}