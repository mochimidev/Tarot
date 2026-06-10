package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.evaluacion4charlottegabriel.TarotNavigator;

public class DreamBottomNav extends GlassPanel {
    public static final int HOME = 0;
    public static final int COLLECTION = 1;
    public static final int READINGS = 2;
    public static final int COUPLES = 3;
    public static final int SETTINGS = 4;

    public DreamBottomNav(Context context, int active) {
        super(context);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        setPadding(DreamUi.dp(context, 8), DreamUi.dp(context, 7), DreamUi.dp(context, 8), DreamUi.dp(context, 7));
        addItem(context, "Inicio", KawaiiSymbolView.STAR, active == HOME, v -> {
            if (active != HOME) TarotNavigator.openHome(context);
        });
        addItem(context, "Coleccion", KawaiiSymbolView.SPROUT, active == COLLECTION, v -> {
            if (active != COLLECTION) TarotNavigator.openCollection(context);
        });
        addItem(context, "Lecturas", KawaiiSymbolView.HEART, active == READINGS, v -> {
            if (active != READINGS) TarotNavigator.openDailyCard(context);
        });
        addItem(context, "Parejas", KawaiiSymbolView.FLAME, active == COUPLES, v -> {
            if (active != COUPLES) TarotNavigator.openCouples(context);
        });
        addItem(context, "Ajustes", KawaiiSymbolView.STAR, active == SETTINGS, v -> {
            if (active != SETTINGS) TarotNavigator.openSettings(context);
        });
    }

    private void addItem(Context context, String label, int symbol, boolean active, OnClickListener listener) {
        LinearLayout item = new LinearLayout(context);
        item.setOrientation(VERTICAL);
        item.setGravity(Gravity.CENTER);
        item.setPadding(0, DreamUi.dp(context, 2), 0, 0);
        item.setClickable(listener != null);
        if (listener != null) item.setOnClickListener(listener);

        KawaiiSymbolView icon = new KawaiiSymbolView(context, symbol);
        icon.setAlpha(active ? 1f : .70f);
        item.addView(icon, new LinearLayout.LayoutParams(DreamUi.dp(context, 26), DreamUi.dp(context, 26)));

        TextView text = DreamUi.text(context, label, 9, active ? DreamColors.LILAC_DARK : DreamColors.MUTED, active ? Typeface.BOLD : Typeface.NORMAL);
        text.setGravity(Gravity.CENTER);
        item.addView(text);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        addView(item, params);
    }
}
