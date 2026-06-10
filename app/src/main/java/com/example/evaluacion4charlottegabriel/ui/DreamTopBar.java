package com.example.evaluacion4charlottegabriel.ui;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DreamTopBar extends LinearLayout {
    public DreamTopBar(Activity activity, String title, boolean showBack, int rightSymbol) {
        super(activity);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        setPadding(0, 0, 0, DreamUi.dp(activity, 8));

        TextView back = DreamUi.text(activity, showBack ? "\u2039" : "\u2630", 30, DreamColors.LILAC_DARK, Typeface.BOLD);
        back.setGravity(Gravity.CENTER);
        back.setMinWidth(DreamUi.dp(activity, 42));
        back.setClickable(showBack);
        if (showBack) {
            back.setOnClickListener(v -> activity.finish());
        }
        addView(back, new LinearLayout.LayoutParams(DreamUi.dp(activity, 46), DreamUi.dp(activity, 44)));

        TextView heading = DreamUi.text(activity, title, 18, DreamColors.INK, Typeface.BOLD);
        heading.setGravity(Gravity.CENTER);
        addView(heading, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

        KawaiiSymbolView symbol = new KawaiiSymbolView(activity, rightSymbol);
        addView(symbol, new LinearLayout.LayoutParams(DreamUi.dp(activity, 38), DreamUi.dp(activity, 38)));
    }
}
