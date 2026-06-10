package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class KawaiiSymbolView extends View {
    public static final int DROP = 0;
    public static final int FLAME = 1;
    public static final int STAR = 2;
    public static final int SPROUT = 3;
    public static final int HEART = 4;

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

        paint.setStyle(Paint.Style.FILL);
        paint.setShadowLayer(DreamUi.dp(getContext(), 10), 0, DreamUi.dp(getContext(), 4), DreamColors.CARD_SHADOW);
        paint.setColor(0x55ffffff);
        canvas.drawOval(new RectF(cx - s * .34f, h * .74f, cx + s * .34f, h * .88f), paint);
        paint.clearShadowLayer();
        paint.setAlpha(255);
        DreamAssets.drawFitCenter(canvas, getContext(), DreamAssets.symbolIcon(type),
                new RectF(0, 0, w, h), paint);
    }
}
