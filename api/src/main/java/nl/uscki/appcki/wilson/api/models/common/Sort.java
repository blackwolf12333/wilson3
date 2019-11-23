
package nl.uscki.appcki.wilson.api.models.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sort {

    @SerializedName("direction")
    @Expose
    private String direction;
    @SerializedName("property")
    @Expose
    private String property;
    @SerializedName("ignoreCase")
    @Expose
    private boolean ignoreCase;
    @SerializedName("nullHandling")
    @Expose
    private String nullHandling;
    @SerializedName("ascending")
    @Expose
    private boolean ascending;

}
