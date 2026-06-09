package com.example.evaluacion4charlottegabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.evaluacion4charlottegabriel.ui.DreamColors;
import com.example.evaluacion4charlottegabriel.ui.DreamUi;
import com.example.evaluacion4charlottegabriel.ui.GlassPanel;
import com.example.evaluacion4charlottegabriel.ui.TarotScaffold;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        TarotScaffold scaffold = new TarotScaffold(this);
        LinearLayout root = scaffold.content();

        TextView header = DreamUi.text(this, "Ajustes", 30, DreamColors.INK, Typeface.BOLD);
        header.setGravity(Gravity.CENTER);
        root.addView(header);

        GlassPanel panel = new GlassPanel(this);
        panel.addView(row("Sonido magico", "Activar campanitas al revelar cartas", prefs, "sound", true));
        panel.addView(row("Particulas brillantes", "Mantener estrellas y brillos animados", prefs, "particles", true));
        panel.addView(row("Modo calma", "Lecturas con movimiento mas suave", prefs, "calm", false));
        add(root, panel, 18, 0);
        setContentView(scaffold);
    }

    private LinearLayout row(String title, String subtitle, SharedPreferences prefs, String key, boolean fallback) {
        LinearLayout row = new LinearLayout(this);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(0, DreamUi.dp(this, 8), 0, DreamUi.dp(this, 8));
        TextView text = DreamUi.text(this, title + "\n" + subtitle, 15, DreamColors.INK, Typeface.BOLD);
        Switch sw = new Switch(this);
        sw.setChecked(prefs.getBoolean(key, fallback));
        sw.setOnCheckedChangeListener((buttonView, isChecked) -> prefs.edit().putBoolean(key, isChecked).apply());
        row.addView(text, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        row.addView(sw);
        return row;
    }

    private void add(LinearLayout parent, android.view.View child, int top, int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DreamUi.dp(this, top), 0, DreamUi.dp(this, bottom));
        parent.addView(child, params);
    }
}
