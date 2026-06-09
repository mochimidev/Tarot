package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

public class KawaiiSymbolView extends View {
    public static final int DROP = 0;
    public static final int FLAME = 1;
    public static final int STAR = 2;
    public static final int SPROUT = 3;
    public static final int UNICORN = 4;

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int type;

    public KawaiiSymbolView(Context context, int type) {
        super(context);
        this.type = type;
        DreamUi.softLayer(this);
    }

    public void setType(int type) {
        this.type = type;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float w = getWidth();
        float h = getHeight();
        float s = Math.min(w, h);
        float cx = w * .5f;
        float cy = h * .52f;

        paint.setStyle(Paint.Style.FILL);
        paint.setShadowLayer(DreamUi.dp(getContext(), 10), 0, DreamUi.dp(getContext(), 4), DreamColors.CARD_SHADOW);
        paint.setColor(0x66ffffff);
        canvas.drawOval(new RectF(cx - s * .34f, h * .74f, cx + s * .34f, h * .88f), paint);
        paint.clearShadowLayer();

        if (type == DROP) {
            drawDrop(canvas, cx, cy, s);
        } else if (type == FLAME) {
            drawFlame(canvas, cx, cy, s);
        } else if (type == STAR) {
            drawStarCharacter(canvas, cx, cy, s);
        } else if (type == SPROUT) {
            drawSprout(canvas, cx, cy, s);
        } else {
            drawUnicorn(canvas, cx, cy, s);
        }
        drawFace(canvas, cx, cy + s * .05f, s);
    }

    private void drawDrop(Canvas canvas, float cx, float cy, float s) {
        paint.setColor(DreamColors.DROP);
        Path path = new Path();
        path.moveTo(cx, cy - s * .38f);
        path.cubicTo(cx - s * .36f, cy - s * .05f, cx - s * .32f, cy + s * .33f, cx, cy + s * .34f);
        path.cubicTo(cx + s * .32f, cy + s * .33f, cx + s * .36f, cy - s * .05f, cx, cy - s * .38f);
        canvas.drawPath(path, paint);
    }

    private void drawFlame(Canvas canvas, float cx, float cy, float s) {
        paint.setColor(DreamColors.FLAME);
        Path path = new Path();
        path.moveTo(cx, cy - s * .42f);
        path.cubicTo(cx + s * .32f, cy - s * .18f, cx + s * .34f, cy + s * .30f, cx, cy + s * .36f);
        path.cubicTo(cx - s * .34f, cy + s * .28f, cx - s * .22f, cy - s * .06f, cx - s * .08f, cy - s * .18f);
        path.cubicTo(cx - s * .03f, cy, cx + s * .10f, cy - s * .10f, cx, cy - s * .42f);
        canvas.drawPath(path, paint);
        paint.setColor(DreamColors.GOLD_SOFT);
        canvas.drawCircle(cx, cy + s * .02f, s * .16f, paint);
    }

    private void drawStarCharacter(Canvas canvas, float cx, float cy, float s) {
        paint.setColor(DreamColors.STAR);
        Path path = new Path();
        for (int i = 0; i < 10; i++) {
            double angle = -Math.PI / 2 + i * Math.PI / 5;
            float r = i % 2 == 0 ? s * .42f : s * .20f;
            float x = cx + (float) Math.cos(angle) * r;
            float y = cy + (float) Math.sin(angle) * r;
            if (i == 0) path.moveTo(x, y); else path.lineTo(x, y);
        }
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawSprout(Canvas canvas, float cx, float cy, float s) {
        paint.setColor(0xffffe8a5);
        canvas.drawOval(new RectF(cx - s * .30f, cy - s * .10f, cx + s * .30f, cy + s * .40f), paint);
        paint.setColor(DreamColors.SPROUT);
        canvas.drawOval(new RectF(cx - s * .28f, cy - s * .48f, cx, cy - s * .15f), paint);
        canvas.drawOval(new RectF(cx, cy - s * .48f, cx + s * .28f, cy - s * .15f), paint);
        paint.setStrokeWidth(DreamUi.dp(getContext(), 4));
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(cx, cy - s * .12f, cx, cy + s * .04f, paint);
        paint.setStyle(Paint.Style.FILL);
    }

    private void drawUnicorn(Canvas canvas, float cx, float cy, float s) {
        paint.setColor(0xffffffff);
        canvas.drawOval(new RectF(cx - s * .34f, cy - s * .24f, cx + s * .34f, cy + s * .34f), paint);
        paint.setColor(DreamColors.GOLD);
        Path horn = new Path();
        horn.moveTo(cx, cy - s * .52f);
        horn.lineTo(cx - s * .10f, cy - s * .18f);
        horn.lineTo(cx + s * .10f, cy - s * .18f);
        horn.close();
        canvas.drawPath(horn, paint);
        paint.setColor(DreamColors.ROSE);
        canvas.drawCircle(cx - s * .16f, cy - s * .23f, s * .12f, paint);
        paint.setColor(DreamColors.LILAC);
        canvas.drawCircle(cx + s * .07f, cy - s * .28f, s * .13f, paint);
    }

    private void drawFace(Canvas canvas, float cx, float cy, float s) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(DreamColors.INK);
        canvas.drawCircle(cx - s * .12f, cy - s * .02f, s * .045f, paint);
        canvas.drawCircle(cx + s * .12f, cy - s * .02f, s * .045f, paint);
        paint.setColor(0xffffffff);
        canvas.drawCircle(cx - s * .105f, cy - s * .04f, s * .016f, paint);
        canvas.drawCircle(cx + s * .135f, cy - s * .04f, s * .016f, paint);
        paint.setColor(DreamColors.ROSE);
        canvas.drawCircle(cx - s * .22f, cy + s * .07f, s * .045f, paint);
        canvas.drawCircle(cx + s * .22f, cy + s * .07f, s * .045f, paint);
        paint.setColor(DreamColors.INK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DreamUi.dp(getContext(), 2));
        canvas.drawArc(new RectF(cx - s * .07f, cy + s * .02f, cx + s * .07f, cy + s * .13f), 20, 140, false, paint);
        paint.setStyle(Paint.Style.FILL);
    }
}
