package com.example.evaluacion4charlottegabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.evaluacion4charlottegabriel.ui.DreamBottomNav;
import com.example.evaluacion4charlottegabriel.ui.DreamColors;
import com.example.evaluacion4charlottegabriel.ui.DreamDividerView;
import com.example.evaluacion4charlottegabriel.ui.DreamTopBar;
import com.example.evaluacion4charlottegabriel.ui.DreamUi;
import com.example.evaluacion4charlottegabriel.ui.GlassPanel;
import com.example.evaluacion4charlottegabriel.ui.KawaiiSymbolView;
import com.example.evaluacion4charlottegabriel.ui.TarotScaffold;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        TarotScaffold scaffold = new TarotScaffold(this);
        scaffold.setBottomNav(DreamBottomNav.SETTINGS);
        LinearLayout root = scaffold.content();
        root.addView(new DreamTopBar(this, "Ajustes", true, KawaiiSymbolView.STAR));

        GlassPanel intro = new GlassPanel(this);
        intro.setGravity(Gravity.CENTER_HORIZONTAL);
        KawaiiSymbolView star = new KawaiiSymbolView(this, KawaiiSymbolView.STAR);
        intro.addView(star, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DreamUi.dp(this, 92)));
        TextView introTitle = DreamUi.text(this, "Ajustes mágicos", 24, DreamColors.INK, Typeface.BOLD);
        introTitle.setGravity(Gravity.CENTER);
        intro.addView(introTitle);
        TextView introCopy = DreamUi.text(this, "Personaliza el brillo, el sonido y la calma de tus lecturas.", 14, DreamColors.DEEP, Typeface.NORMAL);
        introCopy.setGravity(Gravity.CENTER);
        intro.addView(introCopy);
        add(root, intro, 6, 10);

        GlassPanel panel = new GlassPanel(this);
        TextView title = DreamUi.text(this, "Preferencias", 22, DreamColors.INK, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        panel.addView(title);
        panel.addView(new DreamDividerView(this), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DreamUi.dp(this, 22)));
        panel.addView(row("Sonido mágico", "Campanitas suaves al revelar cartas", prefs, "sound", true));
        panel.addView(new DreamDividerView(this), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DreamUi.dp(this, 20)));
        panel.addView(row("Partículas brillantes", "Estrellas, corazones y brillos animados", prefs, "particles", true));
        panel.addView(new DreamDividerView(this), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DreamUi.dp(this, 20)));
        panel.addView(row("Modo calma", "Lecturas con movimiento más suave", prefs, "calm", false));
        add(root, panel, 0, 0);
        setContentView(scaffold);
    }

    private LinearLayout row(String title, String subtitle, SharedPreferences prefs, String key, boolean fallback) {
        LinearLayout row = new LinearLayout(this);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(0, DreamUi.dp(this, 8), 0, DreamUi.dp(this, 8));
        LinearLayout copy = new LinearLayout(this);
        copy.setOrientation(LinearLayout.VERTICAL);
        TextView main = DreamUi.text(this, title, 16, DreamColors.INK, Typeface.BOLD);
        TextView sub = DreamUi.text(this, subtitle, 12, DreamColors.DEEP, Typeface.NORMAL);
        copy.addView(main);
        copy.addView(sub);

        TextView toggle = DreamUi.text(this, "", 12, 0xffffffff, Typeface.BOLD);
        toggle.setGravity(Gravity.CENTER);
        toggle.setMinWidth(DreamUi.dp(this, 78));
        toggle.setMinHeight(DreamUi.dp(this, 40));
        updateToggle(toggle, prefs.getBoolean(key, fallback));
        toggle.setOnClickListener(v -> {
            boolean next = !prefs.getBoolean(key, fallback);
            prefs.edit().putBoolean(key, next).apply();
            updateToggle(toggle, next);
        });
        row.addView(copy, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        row.addView(toggle, new LinearLayout.LayoutParams(DreamUi.dp(this, 86), DreamUi.dp(this, 42)));
        return row;
    }

    private void updateToggle(TextView toggle, boolean enabled) {
        toggle.setText(enabled ? "Activo" : "Pausa");
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,
                enabled
                        ? new int[]{0xffa982f4, 0xffff96c8, 0xffffd884}
                        : new int[]{0xffd9c9ec, 0xffffeef7});
        bg.setCornerRadius(DreamUi.dp(this, 24));
        bg.setStroke(DreamUi.dp(this, 1.2f), enabled ? 0xf2fff8df : 0xdffff8ef);
        toggle.setBackground(bg);
        toggle.setElevation(DreamUi.dp(this, 2));
    }

    private void add(LinearLayout parent, android.view.View child, int top, int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DreamUi.dp(this, top), 0, DreamUi.dp(this, bottom));
        parent.addView(child, params);
    }
}
