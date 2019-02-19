package nl.uscki.appcki.wilson.helpers.bbparser.spans;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;
import android.view.ViewTreeObserver;

import java.io.IOException;

import nl.uscki.appcki.wilson.api.Callback;
import nl.uscki.appcki.wilson.api.MediaAPI;
import nl.uscki.appcki.wilson.api.Services;
import nl.uscki.appcki.wilson.ui.view.BBTextView;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by peter on 12/20/16.
 */

public class NetworkImageSpan extends DynamicDrawableSpan {
    private String source;
    private Integer mediaId;
    private BBTextView view;

    private Drawable drawable;

    private Callback<ResponseBody> imageResponseCallback = new Callback<ResponseBody>() {
        @Override
        public void onSucces(Response<ResponseBody> response) {
            final Drawable responseDrawable = Drawable.createFromStream(response.body().byteStream(), "res");

            if (responseDrawable != null) {
                NetworkImageSpan.this.drawable = responseDrawable;
                NetworkImageSpan.this.drawable.invalidateSelf();

                // When we're still parsing the BBTextView, the initial width and height are 0. Wait
                // until the BBTextView is drawn and measured to start fitting the image
                view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        // We're resizing the view, so remove this listener to avoid endless recursion
                        view.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                        // Store some nice stuff
                        int bitmapWidth = responseDrawable.getBounds().right;
                        int bitmapHeight = responseDrawable.getBounds().bottom;

                        // Determine how to scale the image so it fits in either dimension
                        float scaleFactorX = view.getMeasuredWidth() / (float) bitmapWidth;
                        float scaleFactorY = view.getMeasuredHeight() / (float) bitmapHeight;

                        // Use scale factor that scales image the most, so we no it fits both
                        // horizontally and vertically (note, Float.min() requires higher API).
                        float scaleFactor = scaleFactorX < scaleFactorY ? scaleFactorX : scaleFactorY;

                        if (scaleFactor <= 1) {
                            // Only rescale if the image does not fit. If it's already small, we do not
                            // want to scale up
                            bitmapWidth = Math.round(scaleFactor * bitmapWidth);
                            bitmapHeight = Math.round(scaleFactor * bitmapHeight);

                            NetworkImageSpan.this.drawable.setBounds(0, 0, bitmapWidth, bitmapHeight);

                            view.requestLayout();
                            view.invalidate();
                        }
                    }
                });

                // Initially draw image in original dimensions. Above observer will resize if necessery
                // as soon as the view size is measured
                /*mDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                mDrawable.setLevel(1);*/

                // i don't know yet a better way to refresh TextView
                // mTv.invalidate() doesn't work as expected
                view.requestLayout();
                view.invalidate();
            }
        }
    };

    public NetworkImageSpan(Integer mediaId, String source, BBTextView view) {
        this.source = source;
        this.view = view;
        this.mediaId = mediaId;

        this.drawable = new ColorDrawable(Color.TRANSPARENT).mutate();
        this.drawable.setBounds(
                0,
                0,
                this.drawable.getIntrinsicWidth(),
                this.drawable.getIntrinsicHeight()
        );
    }

    @Override
    public Drawable getDrawable() {
        this.drawable.scheduleSelf(new Runnable() {
            @Override
            public void run() {
                Response response = null;

                try {
                    if(source != null) {
                        response = Services.getInstance().imageService.getImage(source).execute();
                    } else if(mediaId != null) {
                        response = Services.getInstance().mediaService.file(mediaId, MediaAPI.MediaSize.LARGE.toString()).execute();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }, 0);

        if(source != null) {
            Services.getInstance().imageService.getImage(source).enqueue(this.imageResponseCallback);
        } else if(mediaId != null) {
            Services.getInstance().mediaService.file(mediaId, MediaAPI.MediaSize.LARGE.toString()).enqueue(this.imageResponseCallback);
        }

        return this.drawable;
    }
}
