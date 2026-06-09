package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TarotCardWidget extends GlassPanel {
    private final ImageView image;
    private final TextView title;
    private final TextView body;

    public TarotCardWidget(Context context) {
        super(context);
        setGravity(Gravity.CENTER_HORIZONTAL);
        setPadding(DreamUi.dp(context, 14), DreamUi.dp(context, 14), DreamUi.dp(context, 14), DreamUi.dp(context, 18));

        FrameLayout halo = new FrameLayout(context);
        GradientDrawable haloBg = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{0x77ffffff, 0x33fff0fa});
        haloBg.setCornerRadius(DreamUi.dp(context, 26));
        haloBg.setStroke(DreamUi.dp(context, 1), 0x99ffe7b8);
        halo.setBackground(haloBg);
        image = new ImageView(context);
        image.setAdjustViewBounds(true);
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        int imgPad = DreamUi.dp(context, 8);
        image.setPadding(imgPad, imgPad, imgPad, imgPad);
        halo.addView(image, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        addView(halo, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DreamUi.dp(context, 350)));

        title = DreamUi.text(context, "", 23, DreamColors.INK, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.topMargin = DreamUi.dp(context, 14);
        addView(title, titleParams);

        body = DreamUi.text(context, "", 14, DreamColors.DEEP, Typeface.NORMAL);
        body.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams bodyParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        bodyParams.topMargin = DreamUi.dp(context, 8);
        addView(body, bodyParams);
    }

    public void bind(int firebaseId, String titleText, String bodyText, int rotation) {
        TarotAssetLoader.loadCard(image, firebaseId, rotation);
        title.setText(titleText);
        body.setText(bodyText);
    }
}
