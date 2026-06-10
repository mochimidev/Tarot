package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.LinearLayout;

public class GlassPanel extends LinearLayout {
    public GlassPanel(Context context) {
        super(context);
        setOrientation(VERTICAL);
        int pad = DreamUi.dp(context, 18);
        setPadding(pad, pad, pad, pad);
        DreamUi.softLayer(this);
        setElevation(DreamUi.dp(context, 8));
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{DreamColors.GLASS, 0x88ffffff});
        bg.setCornerRadius(DreamUi.dp(context, 28));
        bg.setStroke(DreamUi.dp(context, 2), DreamColors.GLASS_STROKE);
        setBackground(bg);
    }
}
