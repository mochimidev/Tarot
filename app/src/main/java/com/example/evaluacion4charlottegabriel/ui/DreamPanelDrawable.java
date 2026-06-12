package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

public class DreamPanelDrawable extends Drawable {
    private final Context context;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

    public DreamPanelDrawable(Context context) {
        this.context = context;
    }

    @Override
    public void draw(Canvas canvas) {
        RectF bounds = new RectF(getBounds());
        RectF shadow = new RectF(bounds);
        shadow.inset(DreamUi.dp(context, 5), DreamUi.dp(context, 4));
        shadow.offset(0, DreamUi.dp(context, 5));
        paint.setShader(null);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(DreamColors.alpha(DreamColors.GOLD, 18));
        paint.setShadowLayer(DreamUi.dp(context, 16), 0, DreamUi.dp(context, 5),
                DreamColors.alpha(DreamColors.PURPLE, 30));
        canvas.drawRoundRect(shadow, DreamUi.dp(context, 24), DreamUi.dp(context, 24), paint);
        paint.clearShadowLayer();

        paint.setShader(new LinearGradient(
                bounds.left, bounds.top, bounds.right, bounds.bottom,
                new int[]{
                        DreamColors.alpha(DreamColors.CREAM, 238),
                        DreamColors.alpha(DreamColors.SOFT_WHITE, 246),
                        DreamColors.alpha(DreamColors.SOFT_GOLD, 92),
                        DreamColors.alpha(DreamColors.CREAM, 226)},
                new float[]{0f, .44f, .76f, 1f},
                Shader.TileMode.CLAMP));
        canvas.drawRoundRect(bounds, DreamUi.dp(context, 24), DreamUi.dp(context, 24), paint);
        paint.setShader(null);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DreamUi.dp(context, 1f));
        paint.setColor(DreamColors.alpha(DreamColors.GOLD, 176));
        RectF stroke = new RectF(bounds);
        stroke.inset(DreamUi.dp(context, 1), DreamUi.dp(context, 1));
        canvas.drawRoundRect(stroke, DreamUi.dp(context, 23), DreamUi.dp(context, 23), paint);

        paint.setStrokeWidth(DreamUi.dp(context, .7f));
        paint.setColor(DreamColors.alpha(DreamColors.SOFT_GOLD, 112));
        RectF innerStroke = new RectF(bounds);
        innerStroke.inset(DreamUi.dp(context, 4), DreamUi.dp(context, 4));
        canvas.drawRoundRect(innerStroke, DreamUi.dp(context, 20), DreamUi.dp(context, 20), paint);
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
