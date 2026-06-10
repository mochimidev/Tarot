package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.evaluacion4charlottegabriel.R;

import java.util.HashMap;
import java.util.Map;

public final class DreamAssets {
    private static final Map<Integer, Bitmap> CACHE = new HashMap<>();

    private DreamAssets() {
    }

    public static Bitmap bitmap(Context context, int resId) {
        Bitmap cached = CACHE.get(resId);
        if (cached == null) {
            cached = BitmapFactory.decodeResource(context.getResources(), resId);
            CACHE.put(resId, cached);
        }
        return cached;
    }

    public static void drawFitCenter(Canvas canvas, Context context, int resId, RectF bounds, Paint paint) {
        Bitmap bitmap = bitmap(context, resId);
        if (bitmap == null || bounds.width() <= 0 || bounds.height() <= 0) return;
        float scale = Math.min(bounds.width() / bitmap.getWidth(), bounds.height() / bitmap.getHeight());
        float width = bitmap.getWidth() * scale;
        float height = bitmap.getHeight() * scale;
        RectF dst = new RectF(
                bounds.centerX() - width / 2f,
                bounds.centerY() - height / 2f,
                bounds.centerX() + width / 2f,
                bounds.centerY() + height / 2f);
        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), dst, paint);
    }

    public static void drawFill(Canvas canvas, Context context, int resId, RectF bounds, Paint paint) {
        Bitmap bitmap = bitmap(context, resId);
        if (bitmap == null || bounds.width() <= 0 || bounds.height() <= 0) return;
        float scale = Math.max(bounds.width() / bitmap.getWidth(), bounds.height() / bitmap.getHeight());
        float width = bitmap.getWidth() * scale;
        float height = bitmap.getHeight() * scale;
        RectF dst = new RectF(
                bounds.centerX() - width / 2f,
                bounds.centerY() - height / 2f,
                bounds.centerX() + width / 2f,
                bounds.centerY() + height / 2f);
        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), dst, paint);
    }

    public static int symbolIcon(int type) {
        if (type == KawaiiSymbolView.DROP) return R.drawable.tkd_icon_si_o_no;
        if (type == KawaiiSymbolView.FLAME) return R.drawable.tkd_icon_carta_dia;
        if (type == KawaiiSymbolView.SPROUT) return R.drawable.tkd_icon_coleccion;
        if (type == KawaiiSymbolView.HEART) return R.drawable.tkd_icon_tarot_parejas;
        return R.drawable.tkd_icon_carta_dia;
    }
}
