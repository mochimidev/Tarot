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
        setTextColor(0xffffffff);
        setTextSize(15);
        setTypeface(Typeface.create("serif", Typeface.BOLD));
        setGravity(Gravity.CENTER);
        setMinHeight(DreamUi.dp(context, 50));
        setPadding(DreamUi.dp(context, 20), DreamUi.dp(context, 12), DreamUi.dp(context, 20), DreamUi.dp(context, 12));
        setClickable(true);
        setFocusable(true);
        DreamUi.softLayer(this);
        setShadowLayer(DreamUi.dp(context, 8), 0, DreamUi.dp(context, 2), 0x44ffffff);
        setBackground(makeBg());
    }

    private GradientDrawable makeBg() {
        GradientDrawable drawable = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{DreamColors.LILAC, DreamColors.ROSE, DreamColors.GOLD_SOFT});
        drawable.setCornerRadius(DreamUi.dp(getContext(), 25));
        drawable.setStroke(DreamUi.dp(getContext(), 2), 0x99ffffff);
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
