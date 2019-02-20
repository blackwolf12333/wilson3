package nl.uscki.appcki.wilson.api;

import android.net.Uri;

/**
 * Created by peter on 2/6/16.
 */
public class MediaAPI {
    public static String API_URL = ServiceGenerator.API_BASE_URL + "media/file/";

    public enum MediaSize {
        SMALL,
        NORMAL,
        LARGE;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

    public static String getMediaUrl(Integer id) {
        if (id == null)
            id = 72137;// TODO temporary default media id
        return API_URL + id + "/" + MediaSize.LARGE.toString();
    }

    public static String getMediaUrl(int id, MediaSize size) {
        return API_URL + id + "/" + size.toString();
    }

    public static Uri getMediaUri(int id) {
        return Uri.parse(getMediaUrl(id));
    }

    public static Uri getMediaUri(int id, MediaSize size) {
        return Uri.parse(getMediaUrl(id, size));
    }
}
