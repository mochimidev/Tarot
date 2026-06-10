package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import com.example.evaluacion4charlottegabriel.R;

public class DreamDividerView extends View {
    private static int nextVariant;
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    private final int dividerRes;

    public DreamDividerView(Context context) {
        super(context);
        int variant = nextVariant++ % 4;
        dividerRes = variant == 0 ? R.drawable.tkd_divider_01
                : variant == 1 ? R.drawable.tkd_divider_02
                : variant == 2 ? R.drawable.tkd_divider_03
                : R.drawable.tkd_divider_04;
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
        DreamAssets.drawFitCenter(canvas, getContext(), dividerRes,
                new RectF(0, 0, getWidth(), getHeight()), paint);
    }
}
