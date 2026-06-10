package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
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
        RectF shadow = new RectF(bounds);
        shadow.offset(0, DreamUi.dp(context, 3));
        paint.setShader(null);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0x2f67458e);
        paint.setShadowLayer(DreamUi.dp(context, 18), 0, DreamUi.dp(context, 5), 0x3a6d4a98);
        canvas.drawRoundRect(shadow, DreamUi.dp(context, 24), DreamUi.dp(context, 24), paint);
        paint.clearShadowLayer();

        paint.setShader(new LinearGradient(
                bounds.left, bounds.top, bounds.right, bounds.bottom,
                new int[]{0xf9fffdfa, 0xf4fff1f8, 0xf7fff9df},
                new float[]{0f, .55f, 1f},
                Shader.TileMode.CLAMP));
        canvas.drawRoundRect(bounds, DreamUi.dp(context, 24), DreamUi.dp(context, 24), paint);
        paint.setShader(null);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DreamUi.dp(context, 1.4f));
        paint.setColor(0xcce9b45e);
        RectF stroke = new RectF(bounds);
        stroke.inset(DreamUi.dp(context, 1), DreamUi.dp(context, 1));
        canvas.drawRoundRect(stroke, DreamUi.dp(context, 23), DreamUi.dp(context, 23), paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(135);
        DreamAssets.drawFill(canvas, context, frameRes, bounds, paint);
        paint.setAlpha(255);
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
