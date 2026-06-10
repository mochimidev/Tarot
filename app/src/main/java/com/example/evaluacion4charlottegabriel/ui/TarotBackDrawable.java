package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import com.example.evaluacion4charlottegabriel.R;

public class TarotBackDrawable extends Drawable {
    private final Context context;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

    public TarotBackDrawable(Context context) {
        this.context = context;
    }

    @Override
    public void draw(Canvas canvas) {
        RectF b = new RectF(getBounds());
        float pad = DreamUi.dp(context, 2);
        DreamAssets.drawFitCenter(canvas, context, R.drawable.card_back_official,
                new RectF(b.left + pad, b.top + pad, b.right - pad, b.bottom - pad), paint);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(android.graphics.ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return android.graphics.PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return DreamAssets.bitmap(context, R.drawable.card_back_official).getWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return DreamAssets.bitmap(context, R.drawable.card_back_official).getHeight();
    }
}
