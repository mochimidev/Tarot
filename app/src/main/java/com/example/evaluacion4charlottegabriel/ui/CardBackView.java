package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;

public class CardBackView extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CardBackView(Context context) {
        super(context);
        DreamUi.softLayer(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float pad = DreamUi.dp(getContext(), 8);
        RectF card = new RectF(pad, pad, getWidth() - pad, getHeight() - pad);
        paint.setShadowLayer(DreamUi.dp(getContext(), 16), 0, DreamUi.dp(getContext(), 8), DreamColors.CARD_SHADOW);
        paint.setShader(new LinearGradient(card.left, card.top, card.right, card.bottom,
                new int[]{0xffb89be8, 0xfff2b7d8, 0xffd7efff}, null, Shader.TileMode.CLAMP));
        canvas.drawRoundRect(card, DreamUi.dp(getContext(), 26), DreamUi.dp(getContext(), 26), paint);
        paint.clearShadowLayer();
        paint.setShader(null);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DreamUi.dp(getContext(), 4));
        paint.setColor(DreamColors.GOLD_LINE);
        canvas.drawRoundRect(card, DreamUi.dp(getContext(), 26), DreamUi.dp(getContext(), 26), paint);
        paint.setStrokeWidth(DreamUi.dp(getContext(), 1.5f));
        canvas.drawRoundRect(new RectF(card.left + 18, card.top + 18, card.right - 18, card.bottom - 18),
                DreamUi.dp(getContext(), 18), DreamUi.dp(getContext(), 18), paint);
        paint.setStyle(Paint.Style.FILL);
        drawStar(canvas, card.centerX(), card.centerY(), Math.min(card.width(), card.height()) * .13f);
        drawCorner(canvas, card.left + 34, card.top + 34);
        drawCorner(canvas, card.right - 34, card.bottom - 34);
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
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DreamUi.dp(getContext(), 2));
        paint.setColor(DreamColors.GOLD_LINE);
        canvas.drawPath(path, paint);
        paint.setStyle(Paint.Style.FILL);
    }

    private void drawCorner(Canvas canvas, float cx, float cy) {
        paint.setColor(0x88ffffff);
        canvas.drawCircle(cx, cy, DreamUi.dp(getContext(), 7), paint);
        paint.setColor(DreamColors.ROSE);
        canvas.drawCircle(cx, cy, DreamUi.dp(getContext(), 3), paint);
    }
}
