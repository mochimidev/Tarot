package com.example.evaluacion4charlottegabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public class MascotaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("pet_settings", MODE_PRIVATE);

        TarotScaffold scaffold = new TarotScaffold(this);
        scaffold.setBottomNav(DreamBottomNav.PET);
        LinearLayout root = scaffold.content();
        root.addView(new DreamTopBar(this, "Mi Mascota", true, KawaiiSymbolView.HEART));

        addPetHero(root);
        addStatusPanel(root, prefs);

        GlassPanel options = new GlassPanel(this);
        TextView title = DreamUi.text(this, "Ajustes de mascota", 22, DreamColors.INK, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        options.addView(title);
        options.addView(new DreamDividerView(this), new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DreamUi.dp(this, 22)));
        options.addView(row("Mimos mágicos", "Brillitos suaves cuando la saludas", prefs, "sparkles", true));
        options.addView(new DreamDividerView(this), new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DreamUi.dp(this, 20)));
        options.addView(row("Recordatorios", "Avisos tiernos para visitarla cada día", prefs, "reminders", true));
        options.addView(new DreamDividerView(this), new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DreamUi.dp(this, 20)));
        options.addView(row("Modo sueño", "Animaciones más tranquilas por la noche", prefs, "sleep", false));
        options.addView(new DreamDividerView(this), new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DreamUi.dp(this, 20)));
        options.addView(row("Compañía en lecturas", "Mostrarla como guía en lecturas mágicas", prefs, "guide", true));
        add(root, options, 10, 0);

        setContentView(scaffold);
    }

    private void addPetHero(LinearLayout root) {
        GlassPanel hero = new GlassPanel(this);
        hero.setGravity(Gravity.CENTER_HORIZONTAL);
        hero.setPadding(DreamUi.dp(this, 16), DreamUi.dp(this, 14), DreamUi.dp(this, 16), DreamUi.dp(this, 16));

        ImageView image = new ImageView(this);
        image.setImageResource(R.drawable.home_unicorn_hero);
        image.setAdjustViewBounds(true);
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        hero.addView(image, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DreamUi.dp(this, 185)));

        TextView name = DreamUi.text(this, "Lunita", 24, DreamColors.INK, Typeface.BOLD);
        name.setGravity(Gravity.CENTER);
        hero.addView(name);
        TextView subtitle = DreamUi.text(this, "Tu guía kawaii está lista para acompañarte.", 14, DreamColors.DEEP, Typeface.NORMAL);
        subtitle.setGravity(Gravity.CENTER);
        hero.addView(subtitle);
        add(root, hero, 6, 10);
    }

    private void addStatusPanel(LinearLayout root, SharedPreferences prefs) {
        GlassPanel panel = new GlassPanel(this);
        panel.setPadding(DreamUi.dp(this, 18), DreamUi.dp(this, 16), DreamUi.dp(this, 18), DreamUi.dp(this, 18));

        TextView title = DreamUi.text(this, "Estado de hoy", 20, DreamColors.INK, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        panel.addView(title);
        TextView body = DreamUi.text(this, "Feliz, curiosa y rodeada de nubecitas pastel.", 14, DreamColors.DEEP, Typeface.NORMAL);
        body.setGravity(Gravity.CENTER);
        add(panel, body, 5, 12);

        LinearLayout chips = new LinearLayout(this);
        chips.setGravity(Gravity.CENTER);
        chips.setOrientation(LinearLayout.HORIZONTAL);
        chips.addView(chip("Cariño 95%"));
        chips.addView(chip("Energía suave"));
        chips.addView(chip(prefs.getBoolean("guide", true) ? "Guía activa" : "Guía en pausa"));
        panel.addView(chips);
        add(root, panel, 0, 10);
    }

    private TextView chip(String text) {
        TextView chip = DreamUi.text(this, text, 11, DreamColors.LILAC_DARK, Typeface.BOLD);
        chip.setGravity(Gravity.CENTER);
        chip.setPadding(DreamUi.dp(this, 8), DreamUi.dp(this, 6), DreamUi.dp(this, 8), DreamUi.dp(this, 6));
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{0xfffff7e7, 0xffffedf7});
        bg.setCornerRadius(DreamUi.dp(this, 18));
        bg.setStroke(DreamUi.dp(this, 1), 0xb9ddb066);
        chip.setBackground(bg);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(DreamUi.dp(this, 3), 0, DreamUi.dp(this, 3), 0);
        chip.setLayoutParams(params);
        return chip;
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
        toggle.setMinWidth(DreamUi.dp(this, 82));
        toggle.setMinHeight(DreamUi.dp(this, 40));
        updateToggle(toggle, prefs.getBoolean(key, fallback));
        toggle.setOnClickListener(v -> {
            boolean next = !prefs.getBoolean(key, fallback);
            prefs.edit().putBoolean(key, next).apply();
            updateToggle(toggle, next);
        });

        row.addView(copy, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        row.addView(toggle, new LinearLayout.LayoutParams(DreamUi.dp(this, 90), DreamUi.dp(this, 42)));
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

    private void add(LinearLayout parent, View child, int top, int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DreamUi.dp(this, top), 0, DreamUi.dp(this, bottom));
        parent.addView(child, params);
    }
}
