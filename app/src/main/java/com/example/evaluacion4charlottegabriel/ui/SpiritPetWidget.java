package com.example.evaluacion4charlottegabriel.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class SpiritPetWidget extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float bob;
    private int level = 1;

    public SpiritPetWidget(Context context) {
        super(context);
        DreamUi.softLayer(this);
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(2600);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(a -> {
            bob = (float) a.getAnimatedValue();
            invalidate();
        });
        animator.start();
    }

    public void setLevel(int level) {
        this.level = Math.max(1, level);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float w = getWidth();
        float h = getHeight();
        float y = (float) Math.sin(bob * Math.PI * 2) * DreamUi.dp(getContext(), 7);
        canvas.save();
        canvas.translate(0, y);

        paint.setColor(0x55ffffff);
        canvas.drawOval(new RectF(w * .2f, h * .78f, w * .8f, h * .92f), paint);

        paint.setColor(0xffffffff);
        paint.setShadowLayer(DreamUi.dp(getContext(), 14), 0, DreamUi.dp(getContext(), 6), DreamColors.SHADOW);
        canvas.drawOval(new RectF(w * .22f, h * .32f, w * .78f, h * .78f), paint);
        canvas.drawOval(new RectF(w * .29f, h * .14f, w * .71f, h * .52f), paint);

        paint.clearShadowLayer();
        paint.setColor(DreamColors.ROSE);
        canvas.drawCircle(w * .38f, h * .38f, DreamUi.dp(getContext(), 8), paint);
        canvas.drawCircle(w * .62f, h * .38f, DreamUi.dp(getContext(), 8), paint);

        paint.setColor(DreamColors.INK);
        canvas.drawCircle(w * .42f, h * .31f, DreamUi.dp(getContext(), 4), paint);
        canvas.drawCircle(w * .58f, h * .31f, DreamUi.dp(getContext(), 4), paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DreamUi.dp(getContext(), 2));
        canvas.drawArc(new RectF(w * .45f, h * .34f, w * .55f, h * .43f), 15, 150, false, paint);
        paint.setStyle(Paint.Style.FILL);

        paint.setColor(DreamColors.GOLD);
        Path horn = new Path();
        horn.moveTo(w * .5f, h * .06f);
        horn.lineTo(w * .43f, h * .22f);
        horn.lineTo(w * .57f, h * .22f);
        horn.close();
        canvas.drawPath(horn, paint);

        paint.setColor(DreamColors.LILAC);
        canvas.drawOval(new RectF(w * .24f, h * .18f, w * .39f, h * .35f), paint);
        canvas.drawOval(new RectF(w * .61f, h * .18f, w * .76f, h * .35f), paint);

        paint.setColor(DreamColors.SPROUT);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(android.graphics.Typeface.create("sans-serif", android.graphics.Typeface.BOLD));
        paint.setTextSize(DreamUi.dp(getContext(), 18));
        canvas.drawText("Nv. " + level, w * .5f, h * .97f, paint);
        canvas.restore();
    }
}
