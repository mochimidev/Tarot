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
        setTextSize(16);
        setTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD));
        setGravity(Gravity.CENTER);
        setMinHeight(DreamUi.dp(context, 56));
        setPadding(DreamUi.dp(context, 24), DreamUi.dp(context, 14), DreamUi.dp(context, 24), DreamUi.dp(context, 14));
        setClickable(true);
        setFocusable(true);
        DreamUi.softLayer(this);
        setElevation(DreamUi.dp(context, 3));
        setShadowLayer(DreamUi.dp(context, 4), 0, DreamUi.dp(context, 1), 0x66ffffff);
        setBackground(makeBg());
    }

    private GradientDrawable makeBg() {
        GradientDrawable drawable = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{0xffa982f4, 0xffff96c8, 0xffffd884});
        drawable.setCornerRadius(DreamUi.dp(getContext(), 28));
        drawable.setStroke(DreamUi.dp(getContext(), 1.4f), 0xf4fff8df);
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
