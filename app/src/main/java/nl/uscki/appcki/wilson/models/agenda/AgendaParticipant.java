
package nl.uscki.appcki.wilson.models.agenda;

import com.google.gson.annotations.Expose;

import org.joda.time.DateTime;

import nl.uscki.appcki.wilson.models.IWilsonBaseItem;
import nl.uscki.appcki.wilson.models.organisation.PersonSimpleName;

public class AgendaParticipant implements IWilsonBaseItem {

    @Expose
    private Integer id;
    @Expose
    private PersonSimpleName person;
    @Expose
    private String note;
    @Expose
    private DateTime subscribed;
    @Expose
    private Boolean backuplist;
    @Expose
    private Boolean attends;
    @Expose
    private String answer;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The person
     */
    public PersonSimpleName getPerson() {
        return person;
    }

    /**
     * 
     * @param person
     *     The person
     */
    public void setPerson(PersonSimpleName person) {
        this.person = person;
    }

    /**
     * 
     * @return
     *     The note
     */
    public String getNote() {
        return note;
    }

    /**
     * 
     * @param note
     *     The note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * 
     * @return
     *     The subscribed
     */
    public DateTime getSubscribed() {
        return new DateTime(subscribed);
    }

    /**
     * 
     * @return
     *     The backuplist
     */
    public Boolean getBackuplist() {
        return backuplist;
    }

    /**
     * 
     * @param backuplist
     *     The backuplist
     */
    public void setBackuplist(Boolean backuplist) {
        this.backuplist = backuplist;
    }

    /**
     * 
     * @return
     *     The attends
     */
    public Boolean getAttends() {
        return attends;
    }

    /**
     * 
     * @param attends
     *     The attends
     */
    public void setAttends(Boolean attends) {
        this.attends = attends;
    }


    public String getAnswer() {
        return answer;
    }
}
