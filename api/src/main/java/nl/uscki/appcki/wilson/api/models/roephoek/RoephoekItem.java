package nl.uscki.appcki.wilson.api.models.roephoek;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.List;

import nl.uscki.appcki.wilson.api.models.IWilsonBaseItem;

public class RoephoekItem implements IWilsonBaseItem {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("message")
    @Expose
    private List<Object> message;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

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
     * The nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     *
     * @param nickname
     * The nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     *
     * @return
     * The message
     */
    public List<Object> getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(List<Object> message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The timestamp
     */
    public DateTime getTimestamp() {
        return new DateTime(timestamp);
    }

    /**
     *
     * @param timestamp
     * The timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}