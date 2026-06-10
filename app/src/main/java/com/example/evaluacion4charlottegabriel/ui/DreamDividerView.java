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
        paint.setStrokeWidth(DreamUi.dp(getContext(), 1.2f));
        paint.setColor(0x99e4a641);
        canvas.drawLine(w * .12f, cy, w * .43f, cy, paint);
        canvas.drawLine(w * .57f, cy, w * .88f, cy, paint);
        drawHeart(canvas, w * .5f, cy - DreamUi.dp(getContext(), 1), DreamUi.dp(getContext(), 7));
    }

    private void drawHeart(Canvas canvas, float cx, float cy, float size) {
        Path path = new Path();
        path.moveTo(cx, cy + size * .65f);
        path.cubicTo(cx - size * 1.15f, cy, cx - size * .65f, cy - size * .85f, cx, cy - size * .32f);
        path.cubicTo(cx + size * .65f, cy - size * .85f, cx + size * 1.15f, cy, cx, cy + size * .65f);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(DreamColors.ROSE);
        canvas.drawPath(path, paint);
        paint.setStyle(Paint.Style.STROKE);
    }
}
