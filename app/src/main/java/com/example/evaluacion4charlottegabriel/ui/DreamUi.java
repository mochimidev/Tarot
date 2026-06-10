package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.TextView;

public final class DreamUi {
    private DreamUi() {
    }

    public static int dp(Context context, float value) {
        return (int) (value * context.getResources().getDisplayMetrics().density + 0.5f);
    }

    public static TextView text(Context context, String value, float sp, int color, int style) {
        TextView view = new TextView(context);
        view.setText(value);
        view.setTextSize(sp);
        view.setTextColor(color);
        view.setTypeface(Typeface.create("sans-serif", style));
        view.setIncludeFontPadding(true);
        view.setLineSpacing(dp(context, 3), 1f);
        return view;
    }

    public static TextView label(Context context, String value, int color) {
        TextView view = text(context, value, 12, color, Typeface.BOLD);
        view.setAllCaps(true);
        view.setLetterSpacing(.08f);
        return view;
    }

    public static GradientDrawable rounded(int color, float radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(radius);
        return drawable;
    }

    public static GradientDrawable stroked(int fill, int stroke, float radius, float strokeWidth) {
        GradientDrawable drawable = rounded(fill, radius);
        drawable.setStroke((int) strokeWidth, stroke);
        return drawable;
    }

    public static int blend(int from, int to, float ratio) {
        float inverse = 1f - ratio;
        return Color.argb(
                Math.round(Color.alpha(from) * inverse + Color.alpha(to) * ratio),
                Math.round(Color.red(from) * inverse + Color.red(to) * ratio),
                Math.round(Color.green(from) * inverse + Color.green(to) * ratio),
                Math.round(Color.blue(from) * inverse + Color.blue(to) * ratio));
    }

    public static void softLayer(View view) {
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }
}
