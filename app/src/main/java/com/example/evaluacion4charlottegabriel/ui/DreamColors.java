package com.example.evaluacion4charlottegabriel.ui;

import android.graphics.Color;

public final class DreamColors {
    public static final int CREAM = Color.rgb(255, 246, 234);
    public static final int SOFT_WHITE = Color.rgb(255, 249, 245);
    public static final int GOLD = Color.rgb(232, 184, 92);
    public static final int SOFT_GOLD = Color.rgb(243, 217, 154);
    public static final int PURPLE = Color.rgb(110, 76, 163);
    public static final int LAVENDER = Color.rgb(183, 138, 242);
    public static final int SOFT_PINK = Color.rgb(247, 198, 216);
    public static final int PEACH = Color.rgb(255, 200, 138);
    public static final int SKY = Color.rgb(189, 238, 255);
    public static final int MINT = Color.rgb(221, 243, 199);

    public static final int LILAC = LAVENDER;
    public static final int LILAC_DARK = PURPLE;
    public static final int GOLD_SOFT = SOFT_GOLD;
    public static final int CLOUD = SKY;
    public static final int ROSE = SOFT_PINK;
    public static final int ROSE_SOFT = SOFT_PINK;
    public static final int SPROUT = MINT;
    public static final int SPROUT_SOFT = MINT;
    public static final int FLAME = PEACH;
    public static final int DROP = SKY;
    public static final int STAR = SOFT_GOLD;
    public static final int INK = PURPLE;
    public static final int DEEP = PURPLE;
    public static final int MUTED = LAVENDER;
    public static final int WARM_WHITE = SOFT_WHITE;
    public static final int WHITE_SOFT = SOFT_WHITE;
    public static final int GLASS = SOFT_WHITE;
    public static final int GLASS_STROKE = SOFT_GOLD;
    public static final int SHADOW = PURPLE;
    public static final int CARD_SHADOW = PURPLE;
    public static final int GOLD_LINE = GOLD;

    public static final int TRANSPARENT = Color.TRANSPARENT;

    private DreamColors() {
    }

    public static int alpha(int color, int alpha) {
        return Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
    }
}
