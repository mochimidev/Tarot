package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.LinearLayout;

public class GlassPanel extends LinearLayout {
    public GlassPanel(Context context) {
        super(context);
        setOrientation(VERTICAL);
        int pad = DreamUi.dp(context, 20);
        setPadding(pad, pad, pad, pad);
        DreamUi.softLayer(this);
        setElevation(DreamUi.dp(context, 4));
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{DreamColors.GLASS, 0xf7fffaf4});
        bg.setCornerRadius(DreamUi.dp(context, 30));
        bg.setStroke(DreamUi.dp(context, 1.5f), DreamColors.GLASS_STROKE);
        setBackground(bg);
    }
}
