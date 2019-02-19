package nl.uscki.appcki.wilson.helpers.bbparser.spans;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.style.URLSpan;
import android.view.View;

/**
 * Created by peter on 12/20/16.
 */

public class DefensiveURLSpan extends URLSpan {
    public DefensiveURLSpan(String url) {
        super(url);
    }

    public Parcelable.Creator CREATOR = new Parcelable.Creator<DefensiveURLSpan>() {

        @Override
        public DefensiveURLSpan createFromParcel(Parcel source) {
            return new DefensiveURLSpan(source.readString());
        }

        @Override
        public DefensiveURLSpan[] newArray(int size) {
            return new DefensiveURLSpan[size];
        }
    };

    @Override
    public void onClick(View widget) {
        //TODO EventBus.getDefault().post(new LinkClickedEvent(getURL()));
        super.onClick(widget);
    }
}
