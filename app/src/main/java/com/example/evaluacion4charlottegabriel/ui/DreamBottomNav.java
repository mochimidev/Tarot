package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
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
    public static final int PET = 3;
    public static final int SETTINGS = 4;
    public static final int COUPLES = 5;

    public DreamBottomNav(Context context, int active) {
        this(context, active, false);
    }

    public DreamBottomNav(Context context, int active, boolean compact) {
        super(context);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        setPadding(DreamUi.dp(context, compact ? 8 : 9), DreamUi.dp(context, compact ? 4 : 5),
                DreamUi.dp(context, compact ? 8 : 9), DreamUi.dp(context, compact ? 4 : 5));
        setElevation(DreamUi.dp(context, compact ? 2 : 3));
        setBackground(makeNavBg(context, compact));
        addItem(context, "Inicio", R.drawable.icon_inicio, active == HOME, v -> {
            if (active != HOME) TarotNavigator.openHome(context);
        }, compact);
        addItem(context, "Coleccion", R.drawable.icon_coleccion, active == COLLECTION, v -> {
            if (active != COLLECTION) TarotNavigator.openCollection(context);
        }, compact);
        addItem(context, "Lecturas", R.drawable.icon_carta_dia, active == READINGS, v -> {
            if (active != READINGS) TarotNavigator.openDailyCard(context);
        }, compact);
        addItem(context, "Mascota", R.drawable.home_unicorn_hero, active == PET, v -> {
            if (active != PET) TarotNavigator.openPet(context);
        }, compact);
        addItem(context, "Ajustes", R.drawable.icon_ajustes, active == SETTINGS, v -> {
            if (active != SETTINGS) TarotNavigator.openSettings(context);
        }, compact);
    }

    private void addItem(Context context, String label, int iconRes, boolean active, OnClickListener listener, boolean compact) {
        LinearLayout item = new LinearLayout(context);
        item.setOrientation(VERTICAL);
        item.setGravity(Gravity.CENTER);
        item.setPadding(0, DreamUi.dp(context, compact ? 0 : 2), 0, 0);
        item.setClickable(listener != null);
        if (listener != null) item.setOnClickListener(listener);

        ImageView icon = new ImageView(context);
        icon.setImageResource(iconRes);
        icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        icon.setAlpha(active ? 1f : .88f);
        int iconSize = DreamUi.dp(context, compact ? (active ? 23 : 21) : (active ? 27 : 24));
        item.addView(icon, new LinearLayout.LayoutParams(iconSize, iconSize));

        TextView text = DreamUi.text(context, label, compact ? 7.5f : 8.2f, active ? DreamColors.LILAC_DARK : DreamColors.MUTED, active ? Typeface.BOLD : Typeface.NORMAL);
        text.setGravity(Gravity.CENTER);
        text.setShadowLayer(DreamUi.dp(context, 3), 0, DreamUi.dp(context, 1), 0x66ffffff);
        item.addView(text);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        addView(item, params);
    }

    private GradientDrawable makeNavBg(Context context, boolean compact) {
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                compact ? new int[]{0xdffff9f6, 0xd9fff0f8} : new int[]{0xeafffbf3, 0xe8fff1f8});
        bg.setCornerRadius(DreamUi.dp(context, compact ? 18 : 24));
        bg.setStroke(DreamUi.dp(context, 1), compact ? 0x80ddb066 : 0x8fddb066);
        return bg;
    }
}
