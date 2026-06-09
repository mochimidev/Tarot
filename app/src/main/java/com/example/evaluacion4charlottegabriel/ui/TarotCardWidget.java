package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
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

        FrameLayout halo = new FrameLayout(context);
        halo.setBackgroundColor(0x00ffffff);
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
                DreamUi.dp(context, 320)));

        title = DreamUi.text(context, "", 24, DreamColors.INK, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        titleParams.topMargin = DreamUi.dp(context, 14);
        addView(title, titleParams);

        body = DreamUi.text(context, "", 15, DreamColors.INK, Typeface.NORMAL);
        body.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams bodyParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        bodyParams.topMargin = DreamUi.dp(context, 8);
        addView(body, bodyParams);
    }

    public void bind(int imageRes, String titleText, String bodyText, int rotation) {
        image.setImageResource(imageRes);
        image.setRotation(rotation);
        title.setText(titleText);
        body.setText(bodyText);
    }
}
