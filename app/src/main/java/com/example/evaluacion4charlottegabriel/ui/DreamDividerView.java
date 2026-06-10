package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class DreamDividerView extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public DreamDividerView(Context context) {
        super(context);
        setMinimumHeight(DreamUi.dp(context, 22));
        DreamUi.softLayer(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = DreamUi.dp(getContext(), 22);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float w = getWidth();
        float cy = getHeight() * .5f;
        float center = w * .5f;
        float gap = DreamUi.dp(getContext(), 24);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(DreamUi.dp(getContext(), 1.3f));
        paint.setColor(0x99e7ad47);
        canvas.drawLine(w * .18f, cy, center - gap, cy, paint);
        canvas.drawLine(center + gap, cy, w * .82f, cy, paint);

        paint.setStyle(Paint.Style.FILL);
        drawSparkle(canvas, center - DreamUi.dp(getContext(), 42), cy, DreamUi.dp(getContext(), 5), 0xcff6c75d);
        drawHeart(canvas, center, cy, DreamUi.dp(getContext(), 6));
        drawSparkle(canvas, center + DreamUi.dp(getContext(), 42), cy, DreamUi.dp(getContext(), 5), 0xcff6c75d);
    }

    private void drawHeart(Canvas canvas, float cx, float cy, float size) {
        Path path = new Path();
        path.moveTo(cx, cy + size * .72f);
        path.cubicTo(cx - size * 1.25f, cy - size * .08f, cx - size * .60f, cy - size, cx, cy - size * .36f);
        path.cubicTo(cx + size * .60f, cy - size, cx + size * 1.25f, cy - size * .08f, cx, cy + size * .72f);
        paint.setColor(0xdfff9cc4);
        canvas.drawPath(path, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DreamUi.dp(getContext(), 1));
        paint.setColor(0xbce3a24e);
        canvas.drawPath(path, paint);
        paint.setStyle(Paint.Style.FILL);
    }

    private void drawSparkle(Canvas canvas, float cx, float cy, float r, int color) {
        Path path = new Path();
        path.moveTo(cx, cy - r);
        path.lineTo(cx + r * .18f, cy - r * .18f);
        path.lineTo(cx + r, cy);
        path.lineTo(cx + r * .18f, cy + r * .18f);
        path.lineTo(cx, cy + r);
        path.lineTo(cx - r * .18f, cy + r * .18f);
        path.lineTo(cx - r, cy);
        path.lineTo(cx - r * .18f, cy - r * .18f);
        path.close();
        paint.setColor(color);
        canvas.drawPath(path, paint);
    }
}
