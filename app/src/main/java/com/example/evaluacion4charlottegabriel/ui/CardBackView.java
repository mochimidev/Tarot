package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import com.example.evaluacion4charlottegabriel.R;

public class CardBackView extends View {
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

    public CardBackView(Context context) {
        super(context);
        DreamUi.softLayer(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float pad = DreamUi.dp(getContext(), 4);
        DreamAssets.drawFitCenter(canvas, getContext(), R.drawable.tkd_card_back_official,
                new RectF(pad, pad, getWidth() - pad, getHeight() - pad), paint);
    }
}
