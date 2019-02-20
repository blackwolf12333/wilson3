package nl.uscki.appcki.wilson.ui.page.news;

import android.text.SpannableStringBuilder;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.internal.LinkedTreeMap;

import nl.uscki.appcki.wilson.R;
import nl.uscki.appcki.wilson.api.MediaAPI;
import nl.uscki.appcki.wilson.helpers.bbparser.Parser;
import nl.uscki.appcki.wilson.helpers.bbparser.elements.GenericElement;
import nl.uscki.appcki.wilson.models.news.NewsItem;
import nl.uscki.appcki.wilson.ui.view.BBTextView;

public class NewsItemViewHolder extends RecyclerView.ViewHolder {
    public NewsItemViewHolder (View view) {
        super(view);
    }

    public void clear() {

    }

    public void bindTo(NewsItem item) {
        if (item == null)
            return;

        AppCompatTextView title = itemView.findViewById(R.id.news_item_title);
        title.setText(item.getTitle());

        AppCompatTextView poster = itemView.findViewById(R.id.news_item_poster);
        poster.setText(item.getPerson().getPostalname());

        AppCompatTextView timestamp = itemView.findViewById(R.id.news_item_timestamp);
        timestamp.setText(item.getTimestamp().toString("dd MMM YYYY"));

        BBTextView content = itemView.findViewById(R.id.news_item_content);
        content.setText(item.getShorttext(), true);

        setTopImageIfAvailable(item);
    }

    private void setTopImageIfAvailable (NewsItem item) {
        AppCompatImageView imageView = itemView.findViewById(R.id.news_item_top_image);

        for(Object toParse : item.getShorttext()) {
            if (!toParse.getClass().getSimpleName().equals("String")) {
                GenericElement element = GenericElement.fromLinkedTreeUnit((LinkedTreeMap) toParse);

                if (element.getType() == null || !element.getType().equals("Img") && !element.getType().equals("Media"))
                    continue;

                // this should only contain a url so the view parameter should not matter
                SpannableStringBuilder str = Parser.parse(element.getContent(), false, null);
                String url = str.toString();

                if (element.getType().equals("Media")) {
                    String content = element.getContent().get(0).toString();
                    url = MediaAPI.getMediaUrl(Integer.parseInt(content));
                }

                Glide.with(imageView)
                        .load(url)
                        .apply(new RequestOptions().transform(new CenterCrop(), new RoundedCorners(16)))
                        .into(imageView);

                imageView.setVisibility(View.VISIBLE);

                return;
            }
        }

        imageView.setVisibility(View.GONE);
    }
}
