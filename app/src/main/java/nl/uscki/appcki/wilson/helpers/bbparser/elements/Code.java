package nl.uscki.appcki.wilson.helpers.bbparser.elements;

import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.util.Log;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import nl.uscki.appcki.wilson.R;
import nl.uscki.appcki.wilson.helpers.bbparser.Parser;
import nl.uscki.appcki.wilson.ui.view.BBTextView;

/**
 * This class describes a header element, H1 to be exact.
 *
 * @author Ty Mees
 * @version 1.2
 * @since 0.6
 */
public class Code extends GenericElement {

    /**
     * Basic constructor which sets the parsing settings for this element
     */
    public Code(ArrayList<Object> content, String parameter)
    {
        super(content, parameter);
        this.parseContents = false;
        this.replaceEmoji = false;
        this.type = "Code";
    }

    @Override
    public SpannableStringBuilder getSpannedText(BBTextView view) {
        SpannableStringBuilder str = Parser.parse(getContent(), true, view);
        String prefix = "Code:\n";

        // insert "code" header
        str.insert(0, prefix);
        str.setSpan(new StyleSpan(Typeface.BOLD), 0, prefix.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        Log.e("Code", str.toString());

        // set spans for actual code field
        str.setSpan(new TextAppearanceSpan(view.getContext(), R.style.CodeFont), prefix.length(), str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        str.setSpan(new BackgroundColorSpan(ContextCompat.getColor(view.getContext(), R.color.colorCodeBackground)), prefix.length(), str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return str;
    }
}
