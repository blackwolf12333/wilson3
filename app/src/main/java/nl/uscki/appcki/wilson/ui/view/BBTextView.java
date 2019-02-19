package nl.uscki.appcki.wilson.ui.view;

import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

import java.util.List;

import nl.uscki.appcki.wilson.helpers.bbparser.Parser;

/**
 * Created by peter on 2/7/16.
 */
public class BBTextView extends AppCompatTextView {
    public boolean visibilityOfBBUnit = false;

    public BBTextView(Context context) {
        super(context);
    }

    public BBTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BBTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public void setText(CharSequence text, TextView.BufferType type) {
        this.setMovementMethod(LinkMovementMethod.getInstance());
        super.setText(text, type);
    }

    //TODO start using this, cause it's easier you dumb bitch
    public void setText(List<Object> textJson, boolean parseNewLines) {
        this.setText(Parser.parse(textJson, parseNewLines, this));
    }
}
