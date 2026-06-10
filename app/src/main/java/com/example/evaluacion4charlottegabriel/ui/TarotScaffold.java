package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class TarotScaffold extends FrameLayout {
    private final LinearLayout content;
    private DreamBottomNav bottomNav;

    public TarotScaffold(Context context) {
        super(context);
        addView(new DreamBackground(context), new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        ScrollView scrollView = new ScrollView(context);
        scrollView.setFillViewport(false);
        scrollView.setClipToPadding(false);
        scrollView.setScrollBarSize(0);
        content = new LinearLayout(context);
        content.setOrientation(LinearLayout.VERTICAL);
        int pad = DreamUi.dp(context, 20);
        content.setPadding(pad, DreamUi.dp(context, 28), pad, DreamUi.dp(context, 104));
        scrollView.addView(content, new ScrollView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(scrollView, new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public LinearLayout content() {
        return content;
    }

    public void setBottomNav(int active) {
        if (bottomNav != null) {
            removeView(bottomNav);
        }
        bottomNav = new DreamBottomNav(getContext(), active);
        LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = DreamUi.dp(getContext(), 12);
        params.setMargins(margin, 0, margin, margin);
        params.gravity = android.view.Gravity.BOTTOM;
        addView(bottomNav, params);
    }
}
