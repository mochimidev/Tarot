package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.graphics.Typeface;
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

    public static void softLayer(View view) {
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }
}
