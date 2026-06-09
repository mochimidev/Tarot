package com.example.evaluacion4charlottegabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.evaluacion4charlottegabriel.ui.CollectionCard;
import com.example.evaluacion4charlottegabriel.ui.DreamColors;
import com.example.evaluacion4charlottegabriel.ui.DreamUi;
import com.example.evaluacion4charlottegabriel.ui.GlassPanel;
import com.example.evaluacion4charlottegabriel.ui.TarotScaffold;

public class CollectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("collection", MODE_PRIVATE);
        int unlocked = 0;
        for (int i = 0; i < 78; i++) {
            if (prefs.getBoolean("card_" + i, false)) {
                unlocked++;
            }
        }

        TarotScaffold scaffold = new TarotScaffold(this);
        LinearLayout root = scaffold.content();
        TextView header = DreamUi.text(this, "Coleccion", 30, DreamColors.INK, Typeface.BOLD);
        header.setGravity(Gravity.CENTER);
        root.addView(header);

        GlassPanel progress = new GlassPanel(this);
        TextView pct = DreamUi.text(this, unlocked + "/78 cartas - " + Math.round(unlocked * 100f / 78f) + "% completado", 20, DreamColors.INK, Typeface.BOLD);
        pct.setGravity(Gravity.CENTER);
        progress.addView(pct);
        TextView sub = DreamUi.text(this, "Album Dream Sprouts con espacios bloqueados y rarezas.", 14, DreamColors.DEEP, Typeface.NORMAL);
        sub.setGravity(Gravity.CENTER);
        progress.addView(sub);
        add(root, progress, 12, 16);

        GridLayout grid = new GridLayout(this);
        grid.setColumnCount(3);
        for (int i = 0; i < 78; i++) {
            boolean open = prefs.getBoolean("card_" + i, false);
            addCard(grid, new CollectionCard(this, i, "Carta " + (i + 1), open, rarity(i)));
        }
        root.addView(grid, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        setContentView(scaffold);
    }

    private String rarity(int index) {
        if (index % 22 == 0) return "Legendaria";
        if (index % 7 == 0) return "Rara";
        return "Comun";
    }

    private void addCard(GridLayout grid, CollectionCard card) {
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.setMargins(DreamUi.dp(this, 4), DreamUi.dp(this, 4), DreamUi.dp(this, 4), DreamUi.dp(this, 4));
        grid.addView(card, params);
    }

    private void add(LinearLayout parent, android.view.View child, int top, int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DreamUi.dp(this, top), 0, DreamUi.dp(this, bottom));
        parent.addView(child, params);
    }
}
