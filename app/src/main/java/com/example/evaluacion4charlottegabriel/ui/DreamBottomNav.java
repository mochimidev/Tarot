package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.evaluacion4charlottegabriel.R;
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
        setPadding(DreamUi.dp(context, 9), DreamUi.dp(context, 10), DreamUi.dp(context, 9), DreamUi.dp(context, 9));
        addItem(context, "Inicio", R.drawable.icon_inicio, active == HOME, v -> {
            if (active != HOME) TarotNavigator.openHome(context);
        });
        addItem(context, "Coleccion", R.drawable.icon_coleccion, active == COLLECTION, v -> {
            if (active != COLLECTION) TarotNavigator.openCollection(context);
        });
        addItem(context, "Lecturas", R.drawable.icon_carta_dia, active == READINGS, v -> {
            if (active != READINGS) TarotNavigator.openDailyCard(context);
        });
        addItem(context, "Parejas", R.drawable.icon_tarot_parejas, active == COUPLES, v -> {
            if (active != COUPLES) TarotNavigator.openCouples(context);
        });
        addItem(context, "Ajustes", R.drawable.icon_ajustes, active == SETTINGS, v -> {
            if (active != SETTINGS) TarotNavigator.openSettings(context);
        });
    }

    private void addItem(Context context, String label, int iconRes, boolean active, OnClickListener listener) {
        LinearLayout item = new LinearLayout(context);
        item.setOrientation(VERTICAL);
        item.setGravity(Gravity.CENTER);
        item.setPadding(0, DreamUi.dp(context, 2), 0, 0);
        item.setClickable(listener != null);
        if (listener != null) item.setOnClickListener(listener);

        ImageView icon = new ImageView(context);
        icon.setImageResource(iconRes);
        icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        icon.setAlpha(active ? 1f : .88f);
        int iconSize = DreamUi.dp(context, active ? 48 : 43);
        item.addView(icon, new LinearLayout.LayoutParams(iconSize, iconSize));

        TextView text = DreamUi.text(context, label, 9, active ? DreamColors.LILAC_DARK : DreamColors.MUTED, active ? Typeface.BOLD : Typeface.NORMAL);
        text.setGravity(Gravity.CENTER);
        text.setShadowLayer(DreamUi.dp(context, 3), 0, DreamUi.dp(context, 1), 0x66ffffff);
        item.addView(text);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        addView(item, params);
    }
}
