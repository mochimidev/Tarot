package com.example.evaluacion4charlottegabriel.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class AnimatedRevealCard extends FrameLayout {
    private final ImageView front;
    private final TextView back;
    private boolean revealed;

    public AnimatedRevealCard(Context context) {
        super(context);
        setCameraDistance(DreamUi.dp(context, 9000));
        DreamUi.softLayer(this);

        front = new ImageView(context);
        front.setAdjustViewBounds(true);
        front.setScaleType(ImageView.ScaleType.FIT_CENTER);
        front.setVisibility(INVISIBLE);
        front.setPadding(DreamUi.dp(context, 10), DreamUi.dp(context, 10), DreamUi.dp(context, 10), DreamUi.dp(context, 10));
        addView(front, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        back = new TextView(context);
        back.setText("Dream\nSprouts\nTarot");
        back.setGravity(Gravity.CENTER);
        back.setTextSize(27);
        back.setTextColor(0xffffffff);
        back.setTypeface(Typeface.create("serif", Typeface.BOLD));
        back.setShadowLayer(DreamUi.dp(context, 10), 0, DreamUi.dp(context, 2), DreamColors.GOLD);
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.TL_BR,
                new int[]{DreamColors.LILAC, DreamColors.ROSE, DreamColors.CLOUD});
        bg.setCornerRadius(DreamUi.dp(context, 34));
        bg.setStroke(DreamUi.dp(context, 4), DreamColors.GOLD);
        back.setBackground(bg);
        addView(back, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        setOnClickListener(v -> reveal());
    }

    public void setCardImage(int resId, int rotation) {
        front.setImageResource(resId);
        front.setRotation(rotation);
    }

    public ImageView frontImage() {
        return front;
    }

    public void reveal() {
        if (revealed) {
            return;
        }
        revealed = true;
        ObjectAnimator first = ObjectAnimator.ofFloat(this, View.ROTATION_Y, 0f, 90f);
        first.setDuration(280);
        ObjectAnimator second = ObjectAnimator.ofFloat(this, View.ROTATION_Y, -90f, 0f);
        second.setDuration(360);
        first.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                back.setVisibility(INVISIBLE);
                front.setVisibility(VISIBLE);
            }
        });
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(first, second);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.start();
        animate().scaleX(1.03f).scaleY(1.03f).setDuration(420).withEndAction(() ->
                animate().scaleX(1f).scaleY(1f).setDuration(260).start()).start();
    }
}
