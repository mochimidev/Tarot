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
        shadow.inset(DreamUi.dp(context, 4), DreamUi.dp(context, 3));
        shadow.offset(0, DreamUi.dp(context, 4));
        paint.setShader(null);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0x18d39a48);
        paint.setShadowLayer(DreamUi.dp(context, 18), 0, DreamUi.dp(context, 6), 0x3bd3a15a);
        canvas.drawRoundRect(shadow, DreamUi.dp(context, 26), DreamUi.dp(context, 26), paint);
        paint.clearShadowLayer();

        paint.setShader(new LinearGradient(
                bounds.left, bounds.top, bounds.right, bounds.bottom,
                new int[]{0xfcfffaf0, 0xf9fffdf8, 0xfbfff1f7, 0xfafff8e6},
                new float[]{0f, .40f, .72f, 1f},
                Shader.TileMode.CLAMP));
        canvas.drawRoundRect(bounds, DreamUi.dp(context, 26), DreamUi.dp(context, 26), paint);
        paint.setShader(null);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DreamUi.dp(context, 1.1f));
        paint.setColor(0xd8e4ad55);
        RectF stroke = new RectF(bounds);
        stroke.inset(DreamUi.dp(context, 1), DreamUi.dp(context, 1));
        canvas.drawRoundRect(stroke, DreamUi.dp(context, 25), DreamUi.dp(context, 25), paint);

        paint.setStrokeWidth(DreamUi.dp(context, .7f));
        paint.setColor(0x7afff6d6);
        RectF innerStroke = new RectF(bounds);
        innerStroke.inset(DreamUi.dp(context, 4), DreamUi.dp(context, 4));
        canvas.drawRoundRect(innerStroke, DreamUi.dp(context, 22), DreamUi.dp(context, 22), paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(42);
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
