package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

public class TarotBackDrawable extends Drawable {
    private final Context context;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public TarotBackDrawable(Context context) {
        this.context = context;
    }

    @Override
    public void draw(Canvas canvas) {
        RectF b = new RectF(getBounds());
        float pad = DreamUi.dp(context, 4);
        RectF card = new RectF(b.left + pad, b.top + pad, b.right - pad, b.bottom - pad);
        paint.setStyle(Paint.Style.FILL);
        paint.setShader(new LinearGradient(card.left, card.top, card.right, card.bottom,
                new int[]{DreamColors.LILAC, DreamColors.ROSE_SOFT, DreamColors.CLOUD}, null, Shader.TileMode.CLAMP));
        canvas.drawRoundRect(card, DreamUi.dp(context, 18), DreamUi.dp(context, 18), paint);
        paint.setShader(null);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DreamUi.dp(context, 3));
        paint.setColor(DreamColors.GOLD_LINE);
        canvas.drawRoundRect(card, DreamUi.dp(context, 18), DreamUi.dp(context, 18), paint);
        paint.setStyle(Paint.Style.FILL);
        drawStar(canvas, card.centerX(), card.centerY(), Math.min(card.width(), card.height()) * .18f);
    }

    private void drawStar(Canvas canvas, float cx, float cy, float r) {
        Path path = new Path();
        for (int i = 0; i < 10; i++) {
            double angle = -Math.PI / 2 + i * Math.PI / 5;
            float radius = i % 2 == 0 ? r : r * .48f;
            float x = cx + (float) Math.cos(angle) * radius;
            float y = cy + (float) Math.sin(angle) * radius;
            if (i == 0) path.moveTo(x, y); else path.lineTo(x, y);
        }
        path.close();
        paint.setColor(DreamColors.GOLD_SOFT);
        canvas.drawPath(path, paint);
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
}
