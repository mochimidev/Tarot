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
        setMinHeight(DreamUi.dp(context, 54));
        setPadding(DreamUi.dp(context, 22), DreamUi.dp(context, 13), DreamUi.dp(context, 22), DreamUi.dp(context, 13));
        setClickable(true);
        setFocusable(true);
        DreamUi.softLayer(this);
        setShadowLayer(DreamUi.dp(context, 7), 0, DreamUi.dp(context, 1), 0x55ffffff);
        setBackground(makeBg());
    }

    private GradientDrawable makeBg() {
        GradientDrawable drawable = new GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{0xffb98cff, 0xffff98c9, 0xffffcf70});
        drawable.setCornerRadius(DreamUi.dp(getContext(), 24));
        drawable.setStroke(DreamUi.dp(getContext(), 2), 0xdffff8df);
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
