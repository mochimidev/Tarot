package com.example.evaluacion4charlottegabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Typeface;
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

        GlassPanel panel = new GlassPanel(this);
        panel.addView(row("Sonido magico", "Campanitas suaves al revelar cartas", prefs, "sound", true));
        panel.addView(new DreamDividerView(this), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DreamUi.dp(this, 20)));
        panel.addView(row("Particulas brillantes", "Estrellas, corazones y brillos animados", prefs, "particles", true));
        panel.addView(new DreamDividerView(this), new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DreamUi.dp(this, 20)));
        panel.addView(row("Modo calma", "Lecturas con movimiento mas suave", prefs, "calm", false));
        add(root, panel, 12, 0);
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
        toggle.setText(enabled ? "Activo" : "Calma");
        int fill = enabled ? 0xffc99cff : 0xffc9b8df;
        int stroke = enabled ? 0xdffff0b8 : 0xbfffffff;
        toggle.setBackground(DreamUi.stroked(fill, stroke, DreamUi.dp(this, 22), DreamUi.dp(this, 1.5f)));
    }

    private void add(LinearLayout parent, android.view.View child, int top, int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DreamUi.dp(this, top), 0, DreamUi.dp(this, bottom));
        parent.addView(child, params);
    }
}
