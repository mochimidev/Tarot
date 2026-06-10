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

import com.example.evaluacion4charlottegabriel.R;

public class CollectionCard extends GlassPanel {
    public CollectionCard(Context context, int firebaseId, String label, boolean unlocked, String rarity) {
        super(context);
        setGravity(Gravity.CENTER);
        setPadding(DreamUi.dp(context, 8), DreamUi.dp(context, 8), DreamUi.dp(context, 8), DreamUi.dp(context, 10));
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
        image.setAlpha(unlocked ? 1f : .78f);
        addView(image, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DreamUi.dp(context, 154)));

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
        private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);

        LockedCardDrawable(Context context) {
            this.context = context;
        }

        @Override
        public void draw(Canvas canvas) {
            RectF b = new RectF(getBounds());
            float pad = DreamUi.dp(context, 5);
            RectF card = new RectF(b.left + pad, b.top + pad, b.right - pad, b.bottom - pad);
            DreamAssets.drawFitCenter(canvas, context, R.drawable.card_back_official, card, paint);
            float iconSize = Math.min(card.width(), card.height()) * .42f;
            DreamAssets.drawFitCenter(canvas, context, R.drawable.icon_bloqueado,
                    new RectF(card.centerX() - iconSize / 2f, card.centerY() - iconSize / 2f,
                            card.centerX() + iconSize / 2f, card.centerY() + iconSize / 2f), paint);
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
