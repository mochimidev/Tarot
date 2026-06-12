package com.example.evaluacion4charlottegabriel;

import androidx.appcompat.app.AppCompatActivity;

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
    private TarotScaffold scaffold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scaffold = new TarotScaffold(this);
        scaffold.setBottomNav(DreamBottomNav.SETTINGS);
        LinearLayout root = scaffold.content();
        root.addView(new DreamTopBar(this, "Ajustes", true, KawaiiSymbolView.STAR));

        GlassPanel intro = new GlassPanel(this);
        intro.setGravity(Gravity.CENTER_HORIZONTAL);
        KawaiiSymbolView star = new KawaiiSymbolView(this, KawaiiSymbolView.STAR);
        intro.addView(star, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DreamUi.dp(this, 92)));
        TextView introTitle = DreamUi.text(this, "Ajustes m\u00e1gicos", 24, DreamColors.INK, Typeface.BOLD);
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
        panel.addView(new DreamDividerView(this), new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DreamUi.dp(this, 22)));
        panel.addView(row("Sonido m\u00e1gico", "Ambiente suave para acompa\u00f1ar tus lecturas", MagicSettingsManager.KEY_SOUND_ENABLED));
        panel.addView(new DreamDividerView(this), new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DreamUi.dp(this, 20)));
        panel.addView(row("Part\u00edculas brillantes", "Estrellas, corazones y brillos animados", MagicSettingsManager.KEY_PARTICLES_ENABLED));
        panel.addView(new DreamDividerView(this), new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DreamUi.dp(this, 20)));
        panel.addView(row("Modo calma", "Brillo bajo y movimiento m\u00e1s suave", MagicSettingsManager.KEY_CALM_MODE_ENABLED));
        add(root, panel, 0, 0);
        setContentView(scaffold);
    }

    private LinearLayout row(String title, String subtitle, String key) {
        LinearLayout row = new LinearLayout(this);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(0, DreamUi.dp(this, 8), 0, DreamUi.dp(this, 8));
        LinearLayout copy = new LinearLayout(this);
        copy.setOrientation(LinearLayout.VERTICAL);
        TextView main = DreamUi.text(this, title, 16, DreamColors.INK, Typeface.BOLD);
        TextView sub = DreamUi.text(this, subtitle, 12, DreamColors.DEEP, Typeface.NORMAL);
        copy.addView(main);
        copy.addView(sub);

        TextView toggle = DreamUi.text(this, "", 12, DreamColors.INK, Typeface.BOLD);
        toggle.setGravity(Gravity.CENTER);
        toggle.setMinWidth(DreamUi.dp(this, 78));
        toggle.setMinHeight(DreamUi.dp(this, 40));
        updateToggle(toggle, valueForKey(key));
        toggle.setOnClickListener(v -> {
            boolean next = !valueForKey(key);
            saveValue(key, next);
            updateToggle(toggle, next);
            scaffold.applySettingsState();
        });
        row.addView(copy, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        row.addView(toggle, new LinearLayout.LayoutParams(DreamUi.dp(this, 86), DreamUi.dp(this, 42)));
        return row;
    }

    private boolean valueForKey(String key) {
        if (MagicSettingsManager.KEY_SOUND_ENABLED.equals(key)) {
            return MagicSettingsManager.isSoundEnabled(this);
        }
        if (MagicSettingsManager.KEY_PARTICLES_ENABLED.equals(key)) {
            return MagicSettingsManager.areParticlesEnabled(this);
        }
        return MagicSettingsManager.isCalmModeEnabled(this);
    }

    private void saveValue(String key, boolean value) {
        if (MagicSettingsManager.KEY_SOUND_ENABLED.equals(key)) {
            MagicSettingsManager.setSoundEnabled(this, value);
        } else if (MagicSettingsManager.KEY_PARTICLES_ENABLED.equals(key)) {
            MagicSettingsManager.setParticlesEnabled(this, value);
        } else {
            MagicSettingsManager.setCalmModeEnabled(this, value);
        }
    }

    private void updateToggle(TextView toggle, boolean enabled) {
        toggle.setText(enabled ? "Activo" : "Pausa");
        toggle.setTextColor(enabled ? DreamColors.INK : DreamColors.MUTED);
        toggle.setShadowLayer(DreamUi.dp(this, 2), 0, DreamUi.dp(this, 1),
                DreamColors.alpha(DreamColors.SOFT_WHITE, 150));
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,
                enabled
                        ? new int[]{DreamColors.CREAM, DreamColors.SOFT_PINK, DreamColors.LAVENDER}
                        : new int[]{DreamColors.SOFT_WHITE, DreamUi.blend(DreamColors.LAVENDER, DreamColors.SOFT_WHITE, .72f)});
        bg.setCornerRadius(DreamUi.dp(this, 24));
        bg.setStroke(DreamUi.dp(this, 1.2f),
                enabled ? DreamColors.GOLD : DreamColors.alpha(DreamColors.LAVENDER, 150));
        toggle.setBackground(bg);
        toggle.setElevation(DreamUi.dp(this, enabled ? 2 : 1));
        toggle.setAlpha(enabled ? 1f : .9f);
    }

    private void add(LinearLayout parent, android.view.View child, int top, int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DreamUi.dp(this, top), 0, DreamUi.dp(this, bottom));
        parent.addView(child, params);
    }
}
