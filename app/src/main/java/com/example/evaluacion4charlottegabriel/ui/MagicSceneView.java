package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import com.example.evaluacion4charlottegabriel.R;

public class MagicSceneView extends View {
    public static final int FLOATING_CARD = 0;
    public static final int HEART_LINK = 1;
    public static final int CLOUDS = 2;

    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    private final int mode;

    public MagicSceneView(Context context, int mode) {
        super(context);
        this.mode = mode;
        DreamUi.softLayer(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float w = getWidth();
        float h = getHeight();
        drawAsset(canvas, R.drawable.cloud_05, w * .10f, h * .52f, w * .36f);
        drawAsset(canvas, R.drawable.cloud_06, w * .46f, h * .57f, w * .44f);
        drawSparkles(canvas, w, h);
        if (mode == HEART_LINK) {
            float size = Math.min(w, h) * .34f;
            DreamAssets.drawFitCenter(canvas, getContext(), R.drawable.icon_favoritos,
                    new RectF(w * .5f - size, h * .42f - size, w * .5f + size, h * .42f + size), paint);
        } else if (mode == CLOUDS) {
            drawAsset(canvas, R.drawable.cloud_02, w * .28f, h * .30f, w * .45f);
        } else {
            drawFloatingCard(canvas, w, h);
        }
    }

    private void drawFloatingCard(Canvas canvas, float w, float h) {
        canvas.save();
        canvas.rotate(-8, w * .5f, h * .45f);
        float cw = w * .42f;
        float ch = h * .58f;
        DreamAssets.drawFitCenter(canvas, getContext(), R.drawable.card_back_official,
                new RectF(w * .5f - cw / 2, h * .16f, w * .5f + cw / 2, h * .16f + ch), paint);
        canvas.restore();
    }

    private void drawAsset(Canvas canvas, int resId, float x, float y, float width) {
        DreamAssets.drawFitCenter(canvas, getContext(), resId,
                new RectF(x, y, x + width, y + width * .50f), paint);
    }

    private void drawSparkles(Canvas canvas, float w, float h) {
        int[] sparkles = {
                R.drawable.sparkle_01,
                R.drawable.sparkle_02,
                R.drawable.sparkle_04,
                R.drawable.sparkle_08
        };
        for (int i = 0; i < 16; i++) {
            float x = ((i * 67) % 100) / 100f * w;
            float y = ((i * 41) % 86) / 100f * h + h * .05f;
            float size = DreamUi.dp(getContext(), 12 + i % 4 * 4);
            DreamAssets.drawFitCenter(canvas, getContext(), sparkles[i % sparkles.length],
                    new RectF(x - size, y - size, x + size, y + size), paint);
        }
    }
}
