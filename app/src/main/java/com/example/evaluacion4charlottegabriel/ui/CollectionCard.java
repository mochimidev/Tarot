package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CollectionCard extends GlassPanel {
    public CollectionCard(Context context, int firebaseId, String label, boolean unlocked, String rarity) {
        super(context);
        setGravity(Gravity.CENTER);
        setPadding(DreamUi.dp(context, 7), DreamUi.dp(context, 7), DreamUi.dp(context, 7), DreamUi.dp(context, 9));
        ImageView image = new ImageView(context);
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        if (unlocked) {
            Drawable drawable = TarotAssetLoader.getCardDrawable(context, firebaseId);
            if (drawable != null) {
                image.setImageDrawable(drawable);
            } else {
                image.setImageDrawable(new TarotBackDrawable(context));
            }
        } else {
            image.setImageDrawable(new LockedCardDrawable(context));
        }
        image.setAlpha(unlocked ? 1f : .42f);
        addView(image, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DreamUi.dp(context, 148)));

        TextView title = DreamUi.text(context, unlocked ? label : "Carta dormida", 11, DreamColors.INK, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        addView(title, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView rare = DreamUi.text(context, rarity, 10, unlocked ? DreamColors.GOLD : DreamColors.MUTED, Typeface.BOLD);
        rare.setGravity(Gravity.CENTER);
        addView(rare, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    private static class LockedCardDrawable extends android.graphics.drawable.Drawable {
        private final Context context;
        private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        LockedCardDrawable(Context context) {
            this.context = context;
        }

        @Override
        public void draw(Canvas canvas) {
            RectF b = new RectF(getBounds());
            float pad = DreamUi.dp(context, 5);
            RectF card = new RectF(b.left + pad, b.top + pad, b.right - pad, b.bottom - pad);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(0xb6b8adc4);
            canvas.drawRoundRect(card, DreamUi.dp(context, 12), DreamUi.dp(context, 12), paint);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(DreamUi.dp(context, 2));
            paint.setColor(0x99ffe7b8);
            canvas.drawRoundRect(card, DreamUi.dp(context, 12), DreamUi.dp(context, 12), paint);

            paint.setStyle(Paint.Style.FILL);
            paint.setColor(0xeefff7ee);
            float cx = card.centerX();
            float cy = card.centerY();
            RectF body = new RectF(cx - DreamUi.dp(context, 15), cy, cx + DreamUi.dp(context, 15), cy + DreamUi.dp(context, 24));
            canvas.drawRoundRect(body, DreamUi.dp(context, 6), DreamUi.dp(context, 6), paint);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(DreamUi.dp(context, 4));
            canvas.drawArc(new RectF(cx - DreamUi.dp(context, 13), cy - DreamUi.dp(context, 18),
                    cx + DreamUi.dp(context, 13), cy + DreamUi.dp(context, 10)), 205, 130, false, paint);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(DreamColors.ROSE);
            canvas.drawCircle(cx, cy + DreamUi.dp(context, 11), DreamUi.dp(context, 3), paint);
        }

        @Override
        public void setAlpha(int alpha) {
            paint.setAlpha(alpha);
        }

        @Override
        public void setColorFilter(android.graphics.ColorFilter colorFilter) {
            paint.setColorFilter(colorFilter);
        }

        @Override
        public int getOpacity() {
            return android.graphics.PixelFormat.TRANSLUCENT;
        }
    }
}
