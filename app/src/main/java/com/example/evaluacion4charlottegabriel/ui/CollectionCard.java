package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.evaluacion4charlottegabriel.R;

public class CollectionCard extends GlassPanel {
    public CollectionCard(Context context, int firebaseId, String label, boolean unlocked, String rarity) {
        super(context);
        setGravity(Gravity.CENTER);
        setPadding(DreamUi.dp(context, 7), DreamUi.dp(context, 7), DreamUi.dp(context, 7), DreamUi.dp(context, 9));
        FrameLayout art = new FrameLayout(context);
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
            image.setImageResource(R.drawable.card_back_official);
        }
        image.setAlpha(unlocked ? 1f : .78f);
        art.addView(image, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        if (!unlocked) {
            ImageView lock = new ImageView(context);
            lock.setImageResource(R.drawable.icon_bloqueado);
            lock.setAlpha(.92f);
            lock.setScaleType(ImageView.ScaleType.FIT_CENTER);
            art.addView(lock, new FrameLayout.LayoutParams(
                    DreamUi.dp(context, 52),
                    DreamUi.dp(context, 52),
                    Gravity.CENTER));
        }
        addView(art, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DreamUi.dp(context, 136)));

        TextView title = DreamUi.text(context, unlocked ? label : "Carta dormida", 10.2f, DreamColors.INK, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        addView(title, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView rare = DreamUi.text(context, rarity, 9.4f, unlocked ? DreamColors.GOLD : DreamColors.MUTED, Typeface.BOLD);
        rare.setGravity(Gravity.CENTER);
        addView(rare, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
