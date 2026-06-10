package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;

public class MagicSceneView extends View {
    public static final int FLOATING_CARD = 0;
    public static final int HEART_LINK = 1;
    public static final int CLOUDS = 2;

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final int mode;

    public MagicSceneView(Context context, int mode) {
        super(context);
        this.mode = mode;
        DreamUi.softLayer(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float w = getWidth();
        float h = getHeight();
        drawCloud(canvas, w * .12f, h * .55f, w * .34f);
        drawCloud(canvas, w * .48f, h * .60f, w * .42f);
        drawSparkles(canvas, w, h);
        if (mode == HEART_LINK) {
            drawHeart(canvas, w * .5f, h * .42f, Math.min(w, h) * .18f);
        } else if (mode == CLOUDS) {
            drawCloud(canvas, w * .28f, h * .30f, w * .45f);
        } else {
            drawFloatingCard(canvas, w, h);
        }
    }

    private void drawFloatingCard(Canvas canvas, float w, float h) {
        canvas.save();
        canvas.rotate(-8, w * .5f, h * .45f);
        float cw = w * .42f;
        float ch = h * .58f;
        RectF card = new RectF(w * .5f - cw / 2, h * .16f, w * .5f + cw / 2, h * .16f + ch);
        paint.setShadowLayer(DreamUi.dp(getContext(), 14), 0, DreamUi.dp(getContext(), 8), DreamColors.CARD_SHADOW);
        paint.setShader(new LinearGradient(card.left, card.top, card.right, card.bottom,
                new int[]{DreamColors.LILAC, DreamColors.ROSE_SOFT, DreamColors.CLOUD}, null, Shader.TileMode.CLAMP));
        canvas.drawRoundRect(card, DreamUi.dp(getContext(), 22), DreamUi.dp(getContext(), 22), paint);
        paint.clearShadowLayer();
        paint.setShader(null);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DreamUi.dp(getContext(), 3));
        paint.setColor(DreamColors.GOLD_LINE);
        canvas.drawRoundRect(card, DreamUi.dp(getContext(), 22), DreamUi.dp(getContext(), 22), paint);
        paint.setStrokeWidth(DreamUi.dp(getContext(), 1));
        canvas.drawRoundRect(new RectF(card.left + 12, card.top + 12, card.right - 12, card.bottom - 12),
                DreamUi.dp(getContext(), 16), DreamUi.dp(getContext(), 16), paint);
        paint.setStyle(Paint.Style.FILL);
        drawStar(canvas, card.centerX(), card.centerY(), cw * .20f);
        canvas.restore();
    }

    private void drawCloud(Canvas canvas, float x, float y, float width) {
        float height = width * .28f;
        paint.setShader(new LinearGradient(x, y, x, y + height,
                0xeeffffff, 0xccf4e4ff, Shader.TileMode.CLAMP));
        paint.setShadowLayer(DreamUi.dp(getContext(), 10), 0, DreamUi.dp(getContext(), 5), DreamColors.SHADOW);
        canvas.drawRoundRect(new RectF(x, y + height * .25f, x + width, y + height),
                height * .45f, height * .45f, paint);
        canvas.drawCircle(x + width * .25f, y + height * .28f, height * .42f, paint);
        canvas.drawCircle(x + width * .52f, y + height * .15f, height * .55f, paint);
        canvas.drawCircle(x + width * .76f, y + height * .34f, height * .34f, paint);
        paint.clearShadowLayer();
        paint.setShader(null);
    }

    private void drawSparkles(Canvas canvas, float w, float h) {
        for (int i = 0; i < 16; i++) {
            float x = ((i * 67) % 100) / 100f * w;
            float y = ((i * 41) % 86) / 100f * h + h * .05f;
            paint.setColor(i % 2 == 0 ? DreamColors.GOLD : DreamColors.ROSE);
            drawStar(canvas, x, y, DreamUi.dp(getContext(), 3 + i % 4));
        }
    }

    private void drawStar(Canvas canvas, float cx, float cy, float r) {
        Path path = new Path();
        path.moveTo(cx, cy - r * 2);
        path.lineTo(cx + r * .45f, cy - r * .35f);
        path.lineTo(cx + r * 2, cy);
        path.lineTo(cx + r * .45f, cy + r * .35f);
        path.lineTo(cx, cy + r * 2);
        path.lineTo(cx - r * .45f, cy + r * .35f);
        path.lineTo(cx - r * 2, cy);
        path.lineTo(cx - r * .45f, cy - r * .35f);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawHeart(Canvas canvas, float cx, float cy, float size) {
        Path path = new Path();
        path.moveTo(cx, cy + size * .65f);
        path.cubicTo(cx - size * 1.15f, cy, cx - size * .65f, cy - size * .85f, cx, cy - size * .32f);
        path.cubicTo(cx + size * .65f, cy - size * .85f, cx + size * 1.15f, cy, cx, cy + size * .65f);
        paint.setColor(DreamColors.ROSE);
        paint.setShadowLayer(DreamUi.dp(getContext(), 12), 0, 0, DreamColors.ROSE);
        canvas.drawPath(path, paint);
        paint.clearShadowLayer();
    }
}
