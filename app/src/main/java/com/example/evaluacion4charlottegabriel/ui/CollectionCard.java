package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
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
        setPadding(DreamUi.dp(context, 8), DreamUi.dp(context, 8), DreamUi.dp(context, 8), DreamUi.dp(context, 8));
        ImageView image = new ImageView(context);
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        if (unlocked) {
            Drawable drawable = TarotAssetLoader.getCardDrawable(context, firebaseId);
            if (drawable != null) {
                image.setImageDrawable(drawable);
            } else {
                image.setImageResource(R.drawable.ic_launcher_background);
            }
        } else {
            image.setImageResource(android.R.drawable.ic_lock_lock);
        }
        image.setAlpha(unlocked ? 1f : .42f);
        addView(image, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DreamUi.dp(context, 120)));

        TextView title = DreamUi.text(context, unlocked ? label : "Bloqueada", 12, DreamColors.INK, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        addView(title, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView rare = DreamUi.text(context, rarity, 11, unlocked ? DreamColors.GOLD : DreamColors.DEEP, Typeface.BOLD);
        rare.setGravity(Gravity.CENTER);
        addView(rare, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
    }
}
