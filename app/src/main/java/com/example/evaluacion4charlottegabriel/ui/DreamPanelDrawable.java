package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import com.example.evaluacion4charlottegabriel.R;

public class DreamPanelDrawable extends Drawable {
    private static int nextVariant;
    private final Context context;
    private final int frameRes;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

    public DreamPanelDrawable(Context context) {
        this.context = context;
        int variant = nextVariant++ % 3;
        // TODO: replace placeholder PNGs with final premium watercolor panel frame assets.
        frameRes = variant == 0 ? R.drawable.panel_frame_01
                : variant == 1 ? R.drawable.panel_frame_02
                : R.drawable.panel_frame_03;
    }

    @Override
    public void draw(Canvas canvas) {
        RectF bounds = new RectF(getBounds());
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0xdffff8fb);
        canvas.drawRoundRect(bounds, DreamUi.dp(context, 26), DreamUi.dp(context, 26), paint);
        paint.setColor(0xffffffff);
        DreamAssets.drawFill(canvas, context, frameRes, bounds, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return android.graphics.PixelFormat.TRANSLUCENT;
    }
}
