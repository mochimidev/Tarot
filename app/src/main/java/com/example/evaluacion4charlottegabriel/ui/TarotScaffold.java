package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.app.Activity;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class TarotScaffold extends FrameLayout {
    private final LinearLayout content;
    private final ScrollView scrollView;
    private DreamBottomNav bottomNav;

    public TarotScaffold(Context context) {
        super(context);
        if (context instanceof Activity) {
            ((Activity) context).getWindow().setStatusBarColor(Color.rgb(11, 13, 39));
            ((Activity) context).getWindow().setNavigationBarColor(Color.BLACK);
        }
        addView(new DreamBackground(context), new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        scrollView = new ScrollView(context);
        scrollView.setFillViewport(false);
        scrollView.setClipToPadding(false);
        scrollView.setScrollBarSize(0);
        content = new LinearLayout(context);
        content.setOrientation(LinearLayout.VERTICAL);
        int pad = DreamUi.dp(context, 20);
        content.setPadding(pad, DreamUi.dp(context, 28), pad, DreamUi.dp(context, 28));
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
        ViewGroup.LayoutParams scrollParams = scrollView.getLayoutParams();
        if (scrollParams instanceof MarginLayoutParams) {
            ((MarginLayoutParams) scrollParams).bottomMargin = DreamUi.dp(getContext(), 118);
            scrollView.setLayoutParams(scrollParams);
        }
        LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = DreamUi.dp(getContext(), 12);
        params.setMargins(margin, 0, margin, margin);
        params.gravity = android.view.Gravity.BOTTOM;
        addView(bottomNav, params);
    }
}
