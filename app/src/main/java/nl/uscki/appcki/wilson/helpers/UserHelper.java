package nl.uscki.appcki.wilson.helpers;

import android.content.SharedPreferences;

public class UserHelper {
    private static UserHelper singleton;
    private String TOKEN;
    private SharedPreferences sharedPreferences;

    private UserHelper() {
        this.TOKEN = "";
    }

    public static synchronized UserHelper getInstance( ) {
        if (singleton == null)
            singleton = new UserHelper();
        return singleton;
    }

    public void init(SharedPreferences preferences) {
        this.sharedPreferences = preferences;

        if (this.sharedPreferences.contains("token")) {
            this.TOKEN = this.sharedPreferences.getString("token", "");
        }
    }

    public void destroy() {
        this.sharedPreferences.edit().putString("token", this.TOKEN).commit();
    }

    public String getToken() {
        return TOKEN;
    }

    public void setToken(String TOKEN) {
        this.TOKEN = TOKEN;
        this.sharedPreferences.edit().putString("token", this.TOKEN).apply();
    }
}
