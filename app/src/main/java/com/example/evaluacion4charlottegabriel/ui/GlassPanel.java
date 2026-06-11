package com.example.evaluacion4charlottegabriel.ui;

import android.content.Context;
import android.widget.LinearLayout;

public class GlassPanel extends LinearLayout {
    public GlassPanel(Context context) {
        super(context);
        setOrientation(VERTICAL);
        int pad = DreamUi.dp(context, 20);
        setPadding(pad, pad, pad, pad);
        DreamUi.softLayer(this);
        setElevation(DreamUi.dp(context, 5));
        setBackground(new DreamPanelDrawable(context));
    }
}
