package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatTextView;

public class DreamButton extends AppCompatTextView {
    public DreamButton(Context context, String label) {
        super(context);
        setText(label);
        setTextColor(DreamColors.INK);
        setTextSize(14.5f);
        setTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD));
        setGravity(Gravity.CENTER);
        setMinHeight(DreamUi.dp(context, 46));
        setPadding(DreamUi.dp(context, 18), DreamUi.dp(context, 11), DreamUi.dp(context, 18), DreamUi.dp(context, 11));
        setClickable(true);
        setFocusable(true);
        DreamUi.softLayer(this);
        setElevation(DreamUi.dp(context, 1));
        setShadowLayer(DreamUi.dp(context, 2), 0, DreamUi.dp(context, 1),
                DreamColors.alpha(DreamColors.SOFT_WHITE, 190));
        setBackground(makeBg());
    }

    private GradientDrawable makeBg() {
        GradientDrawable drawable = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{
                        DreamColors.alpha(DreamColors.SOFT_WHITE, 246),
                        DreamColors.alpha(DreamColors.CREAM, 236),
                        DreamColors.alpha(DreamColors.SOFT_PINK, 90)});
        drawable.setCornerRadius(DreamUi.dp(getContext(), 24));
        drawable.setStroke(DreamUi.dp(getContext(), 1),
                DreamColors.alpha(DreamColors.GOLD, 190));
        return drawable;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            animate().scaleX(.97f).scaleY(.97f).setDuration(90).start();
        } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
            animate().scaleX(1f).scaleY(1f).setDuration(120).start();
        }
        return super.onTouchEvent(event);
    }
}
