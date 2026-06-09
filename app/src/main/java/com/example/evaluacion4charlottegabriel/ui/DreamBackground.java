package com.example.evaluacion4charlottegabriel.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class DreamBackground extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint cloudPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float phase;

    public DreamBackground(Context context) {
        super(context);
        init();
    }

    public DreamBackground(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        cloudPaint.setColor(DreamColors.WHITE_SOFT);
        cloudPaint.setShadowLayer(DreamUi.dp(getContext(), 18), 0, DreamUi.dp(getContext(), 8), DreamColors.SHADOW);
        DreamUi.softLayer(this);
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(9000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(animation -> {
            phase = (float) animation.getAnimatedValue();
            invalidate();
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getWidth();
        int h = getHeight();
        paint.setShader(new LinearGradient(0, 0, w, h,
                new int[]{0xfff7dcff, 0xffd7eaff, 0xffffe2ef, 0xffe5f7d5},
                new float[]{0f, .42f, .75f, 1f}, Shader.TileMode.CLAMP));
        canvas.drawRect(0, 0, w, h, paint);
        paint.setShader(null);

        drawGlow(canvas, w * .22f, h * .14f, DreamColors.GOLD, w * .36f);
        drawGlow(canvas, w * .82f, h * .34f, DreamColors.CLOUD, w * .32f);
        drawCloud(canvas, w * .1f + phase * w * .05f, h * .13f, w * .48f);
        drawCloud(canvas, w * .52f - phase * w * .04f, h * .25f, w * .36f);
        drawCloud(canvas, w * .18f, h * .78f, w * .52f);
        drawStars(canvas, w, h);
    }

    private void drawGlow(Canvas canvas, float cx, float cy, int color, float radius) {
        paint.setShader(new RadialGradient(cx, cy, radius, color & 0x44ffffff, 0x00ffffff, Shader.TileMode.CLAMP));
        canvas.drawCircle(cx, cy, radius, paint);
        paint.setShader(null);
    }

    private void drawCloud(Canvas canvas, float x, float y, float width) {
        float height = width * .26f;
        RectF base = new RectF(x, y + height * .18f, x + width, y + height);
        canvas.drawRoundRect(base, height * .5f, height * .5f, cloudPaint);
        canvas.drawCircle(x + width * .25f, y + height * .25f, height * .42f, cloudPaint);
        canvas.drawCircle(x + width * .48f, y + height * .15f, height * .54f, cloudPaint);
        canvas.drawCircle(x + width * .72f, y + height * .28f, height * .36f, cloudPaint);
    }

    private void drawStars(Canvas canvas, int w, int h) {
        paint.setStyle(Paint.Style.FILL);
        int count = 34;
        for (int i = 0; i < count; i++) {
            float x = ((i * 73) % 100) / 100f * w;
            float y = ((i * 47) % 100) / 100f * h;
            float pulse = .55f + .45f * (float) Math.sin((phase * Math.PI * 2) + i);
            paint.setColor(adjustAlpha(i % 3 == 0 ? DreamColors.GOLD : 0xffffffff, .35f + pulse * .55f));
            drawSpark(canvas, x, y, DreamUi.dp(getContext(), 2 + (i % 4)));
        }
        paint.setStyle(Paint.Style.FILL);
    }

    private void drawSpark(Canvas canvas, float cx, float cy, float r) {
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

    private int adjustAlpha(int color, float factor) {
        return (color & 0x00ffffff) | (((int) (255 * factor)) << 24);
    }
}
