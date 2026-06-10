package com.example.evaluacion4charlottegabriel.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.evaluacion4charlottegabriel.R;

public class DreamBackground extends View {
    // TODO: replace placeholder PNGs with final premium watercolor illustration assets.
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
        DreamAssets.drawFill(canvas, getContext(), R.drawable.bg_watercolor, new RectF(0, 0, w, h), paint);
        drawCloud(canvas, CLOUDS[0], w * (.05f + phase * .04f), h * .11f, w * .52f);
        drawCloud(canvas, CLOUDS[1], w * (.54f - phase * .035f), h * .23f, w * .40f);
        drawCloud(canvas, CLOUDS[2], w * .10f, h * .76f, w * .62f);
        drawCloud(canvas, CLOUDS[3], w * .58f, h * .84f, w * .44f);
        drawSparkles(canvas, w, h);
    }

    private void drawCloud(Canvas canvas, int resId, float x, float y, float width) {
        float height = width * .50f;
        DreamAssets.drawFitCenter(canvas, getContext(), resId, new RectF(x, y, x + width, y + height), paint);
    }

    private void drawSparkles(Canvas canvas, int w, int h) {
        for (int i = 0; i < 38; i++) {
            float x = ((i * 73) % 100) / 100f * w;
            float y = ((i * 47) % 100) / 100f * h;
            float size = DreamUi.dp(getContext(), 12 + (i % 5) * 4);
            float pulse = .68f + .32f * (float) Math.sin((phase * Math.PI * 2) + i);
            paint.setAlpha((int) (120 + pulse * 95));
            DreamAssets.drawFitCenter(canvas, getContext(), SPARKLES[i % SPARKLES.length],
                    new RectF(x - size, y - size, x + size, y + size), paint);
        }
        paint.setAlpha(255);
    }
}
