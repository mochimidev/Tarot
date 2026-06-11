package com.example.evaluacion4charlottegabriel.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.evaluacion4charlottegabriel.R;

public class DreamBackground extends View {
    private static final int[] CLOUDS = {
            R.drawable.cloud_01,
            R.drawable.cloud_02,
            R.drawable.cloud_03,
            R.drawable.cloud_04,
            R.drawable.cloud_05,
            R.drawable.cloud_06
    };
    private static final int[] SPARKLES = {
            R.drawable.sparkle_01,
            R.drawable.sparkle_02,
            R.drawable.sparkle_03,
            R.drawable.sparkle_04,
            R.drawable.sparkle_05,
            R.drawable.sparkle_06,
            R.drawable.sparkle_07,
            R.drawable.sparkle_08,
            R.drawable.sparkle_09,
            R.drawable.sparkle_10
    };
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    private final Paint backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int gradientWidth;
    private int gradientHeight;
    private float phase;
    private boolean quietHome;

    public DreamBackground(Context context) {
        super(context);
        init();
    }

    public DreamBackground(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
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

    public void setQuietHome(boolean quietHome) {
        this.quietHome = quietHome;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getWidth();
        int h = getHeight();
        if (w != gradientWidth || h != gradientHeight) {
            gradientWidth = w;
            gradientHeight = h;
            backgroundPaint.setShader(new LinearGradient(
                    0, 0, 0, h,
                    new int[]{
                            Color.rgb(252, 243, 255),
                            Color.rgb(245, 247, 255),
                            Color.rgb(238, 252, 255)
                    },
                    new float[]{0f, .54f, 1f},
                    Shader.TileMode.CLAMP));
        }
        canvas.drawRect(0, 0, w, h, backgroundPaint);
        paint.setAlpha(quietHome ? 92 : 255);
        DreamAssets.drawFill(canvas, getContext(), R.drawable.bg_watercolor, new RectF(0, 0, w, h), paint);
        if (!quietHome) {
            drawCloud(canvas, CLOUDS[0], w * (.02f + phase * .018f), h * .17f, w * .44f, 58);
            drawCloud(canvas, CLOUDS[1], w * (.62f - phase * .018f), h * .36f, w * .34f, 48);
            drawCloud(canvas, CLOUDS[2], w * .04f, h * .82f, w * .50f, 52);
        }
        drawSparkles(canvas, w, h);
    }

    private void drawCloud(Canvas canvas, int resId, float x, float y, float width, int alpha) {
        float height = width * .50f;
        paint.setAlpha(alpha);
        DreamAssets.drawFitCenter(canvas, getContext(), resId, new RectF(x, y, x + width, y + height), paint);
        paint.setAlpha(255);
    }

    private void drawSparkles(Canvas canvas, int w, int h) {
        int count = quietHome ? 12 : 18;
        for (int i = 0; i < count; i++) {
            float x = ((i * 73) % 100) / 100f * w;
            float y = ((i * 47) % 100) / 100f * h;
            float size = DreamUi.dp(getContext(), quietHome ? 7 + (i % 3) * 2 : 10 + (i % 4) * 3);
            float pulse = .68f + .32f * (float) Math.sin((phase * Math.PI * 2) + i);
            paint.setAlpha((int) ((quietHome ? 28 : 46) + pulse * (quietHome ? 34 : 58)));
            DreamAssets.drawFitCenter(canvas, getContext(), SPARKLES[i % SPARKLES.length],
                    new RectF(x - size, y - size, x + size, y + size), paint);
        }
        paint.setAlpha(255);
    }
}
