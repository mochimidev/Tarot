package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class TarotScaffold extends FrameLayout {
    private final LinearLayout content;

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
        content.setPadding(pad, DreamUi.dp(context, 28), pad, DreamUi.dp(context, 32));
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
}
