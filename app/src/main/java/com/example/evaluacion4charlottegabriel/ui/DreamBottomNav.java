package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
        setPadding(DreamUi.dp(context, compact ? 10 : 11), DreamUi.dp(context, compact ? 6 : 7),
                DreamUi.dp(context, compact ? 10 : 11), DreamUi.dp(context, compact ? 6 : 7));
        setElevation(DreamUi.dp(context, 4));
        setBackground(makeNavBg(context, compact));
        addItem(context, "Inicio", R.drawable.icon_inicio, active == HOME, v -> {
            if (active != HOME) TarotNavigator.openHome(context);
        }, compact);
        addItem(context, "Colecci\u00f3n", R.drawable.icon_coleccion, active == COLLECTION, v -> {
            if (active != COLLECTION) TarotNavigator.openCollection(context);
        }, compact);
        addItem(context, "Lecturas", R.drawable.icon_carta_dia, active == READINGS || active == COUPLES, v -> {
            if (active != READINGS && active != COUPLES) TarotNavigator.openDailyCard(context);
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
        icon.setAlpha(active ? 1f : .74f);
        int iconSize = DreamUi.dp(context, compact ? (active ? 29 : 26) : (active ? 31 : 28));
        FrameLayout iconShell = new FrameLayout(context);
        iconShell.setPadding(DreamUi.dp(context, active ? 3 : 2), DreamUi.dp(context, active ? 2 : 3),
                DreamUi.dp(context, active ? 3 : 2), DreamUi.dp(context, active ? 2 : 3));
        if (active) {
            iconShell.setBackground(makeActiveIconBg(context));
            iconShell.setElevation(DreamUi.dp(context, 2));
            DreamUi.softLayer(iconShell);
        }
        iconShell.addView(icon, new FrameLayout.LayoutParams(iconSize, iconSize, Gravity.CENTER));
        item.addView(iconShell, new LinearLayout.LayoutParams(
                DreamUi.dp(context, compact ? 39 : 43),
                DreamUi.dp(context, compact ? 34 : 37)));

        TextView text = DreamUi.text(context, label, compact ? 8.8f : 9.4f, active ? DreamColors.INK : DreamColors.MUTED, active ? Typeface.BOLD : Typeface.NORMAL);
        text.setGravity(Gravity.CENTER);
        text.setShadowLayer(DreamUi.dp(context, 3), 0, DreamUi.dp(context, 1),
                DreamColors.alpha(DreamColors.SOFT_WHITE, 140));
        item.addView(text);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        addView(item, params);
    }

    private GradientDrawable makeActiveIconBg(Context context) {
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{
                        DreamColors.alpha(DreamColors.CREAM, 232),
                        DreamColors.alpha(DreamColors.SOFT_GOLD, 194),
                        DreamColors.alpha(DreamColors.LAVENDER, 70)});
        bg.setCornerRadius(DreamUi.dp(context, 22));
        bg.setStroke(DreamUi.dp(context, 1), DreamColors.alpha(DreamColors.GOLD, 190));
        return bg;
    }

    private GradientDrawable makeNavBg(Context context, boolean compact) {
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                compact
                        ? new int[]{
                                DreamColors.alpha(DreamColors.SOFT_WHITE, 244),
                                DreamColors.alpha(DreamColors.CREAM, 234)}
                        : new int[]{
                                DreamColors.alpha(DreamColors.SOFT_WHITE, 246),
                                DreamColors.alpha(DreamColors.CREAM, 238)});
        bg.setCornerRadius(DreamUi.dp(context, 28));
        bg.setStroke(DreamUi.dp(context, 1),
                DreamColors.alpha(DreamColors.GOLD, compact ? 176 : 190));
        return bg;
    }
}
