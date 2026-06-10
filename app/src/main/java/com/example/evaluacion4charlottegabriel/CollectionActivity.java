package com.example.evaluacion4charlottegabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.evaluacion4charlottegabriel.ui.CardMeta;
import com.example.evaluacion4charlottegabriel.ui.CollectionCard;
import com.example.evaluacion4charlottegabriel.ui.DreamBottomNav;
import com.example.evaluacion4charlottegabriel.ui.DreamColors;
import com.example.evaluacion4charlottegabriel.ui.DreamUi;
import com.example.evaluacion4charlottegabriel.ui.GlassPanel;
import com.example.evaluacion4charlottegabriel.ui.KawaiiSymbolView;
import com.example.evaluacion4charlottegabriel.ui.TarotScaffold;

public class CollectionActivity extends AppCompatActivity {
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("collection", MODE_PRIVATE);
        int unlocked = 0;
        for (int i = 0; i < 78; i++) {
            if (prefs.getBoolean("card_" + i, false)) {
                unlocked++;
            }
        }

        TarotScaffold scaffold = new TarotScaffold(this);
        scaffold.setBottomNav(DreamBottomNav.COLLECTION);
        LinearLayout root = scaffold.content();
        TextView header = DreamUi.text(this, "Colección", 30, DreamColors.INK, Typeface.BOLD);
        header.setGravity(Gravity.CENTER);
        root.addView(header);

        GlassPanel progress = new GlassPanel(this);
        progress.setGravity(Gravity.CENTER_HORIZONTAL);
        TextView pct = DreamUi.text(this, unlocked + "/78 cartas", 25, DreamColors.INK, Typeface.BOLD);
        pct.setGravity(Gravity.CENTER);
        progress.addView(pct);
        TextView sub = DreamUi.text(this, Math.round(unlocked * 100f / 78f) + "% del álbum iluminado", 14, DreamColors.DEEP, Typeface.NORMAL);
        sub.setGravity(Gravity.CENTER);
        add(progress, sub, 2, 8);
        addProgressBar(progress, unlocked);
        add(root, progress, 12, 16);

        addFamily(root, "Arcanos Mayores", "El cuento principal del destino", 0, 21, KawaiiSymbolView.STAR);
        addFamily(root, "Chispas", "Valentía, juego y comienzos", 22, 35, KawaiiSymbolView.FLAME);
        addFamily(root, "Gotitas", "Emociones, ternura e intuición", 36, 49, KawaiiSymbolView.DROP);
        addFamily(root, "Estrellas", "Deseos, guía y confianza", 50, 63, KawaiiSymbolView.STAR);
        addFamily(root, "Brotes", "Crecimiento, cuidado y abundancia", 64, 77, KawaiiSymbolView.SPROUT);
        setContentView(scaffold);
    }

    private void addFamily(LinearLayout root, String title, String subtitle, int start, int end, int symbol) {
        GlassPanel family = new GlassPanel(this);
        family.setPadding(DreamUi.dp(this, 14), DreamUi.dp(this, 14), DreamUi.dp(this, 14), DreamUi.dp(this, 16));

        LinearLayout head = new LinearLayout(this);
        head.setGravity(Gravity.CENTER_VERTICAL);
        KawaiiSymbolView icon = new KawaiiSymbolView(this, symbol);
        head.addView(icon, new LinearLayout.LayoutParams(DreamUi.dp(this, 64), DreamUi.dp(this, 64)));

        LinearLayout copy = new LinearLayout(this);
        copy.setOrientation(LinearLayout.VERTICAL);
        TextView name = DreamUi.text(this, title, 21, DreamColors.INK, Typeface.BOLD);
        copy.addView(name);
        int openedCount = countUnlocked(start, end);
        TextView progress = DreamUi.text(this, subtitle + " · " + openedCount + "/" + (end - start + 1), 13, DreamColors.DEEP, Typeface.NORMAL);
        copy.addView(progress);
        head.addView(copy, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        family.addView(head);

        GridLayout grid = new GridLayout(this);
        grid.setColumnCount(3);
        for (int i = start; i <= end; i++) {
            boolean open = prefs.getBoolean("card_" + i, false);
            CollectionCard card = new CollectionCard(this, i, cardLabel(i), open, CardMeta.rarity(i));
            final int cardId = i;
            card.setOnClickListener(v -> openDetail(cardId, open));
            card.setClickable(true);
            addCard(grid, card);
        }
        add(family, grid, 12, 0);
        add(root, family, 0, 16);
    }

    private void addProgressBar(LinearLayout parent, int unlocked) {
        android.widget.ProgressBar bar = new android.widget.ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        bar.setMax(78);
        bar.setProgress(unlocked);
        android.graphics.drawable.GradientDrawable track = DreamUi.stroked(0x77ffffff, DreamColors.GLASS_STROKE,
                DreamUi.dp(this, 8), DreamUi.dp(this, 1));
        android.graphics.drawable.GradientDrawable fill = new android.graphics.drawable.GradientDrawable(
                android.graphics.drawable.GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{DreamColors.LILAC, DreamColors.ROSE, DreamColors.GOLD_SOFT});
        fill.setCornerRadius(DreamUi.dp(this, 8));
        android.graphics.drawable.ClipDrawable clip = new android.graphics.drawable.ClipDrawable(fill,
                Gravity.LEFT, android.graphics.drawable.ClipDrawable.HORIZONTAL);
        android.graphics.drawable.LayerDrawable layers = new android.graphics.drawable.LayerDrawable(
                new android.graphics.drawable.Drawable[]{track, clip});
        layers.setId(0, android.R.id.background);
        layers.setId(1, android.R.id.progress);
        bar.setProgressDrawable(layers);
        parent.addView(bar, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DreamUi.dp(this, 12)));
    }

    private int countUnlocked(int start, int end) {
        int count = 0;
        for (int i = start; i <= end; i++) {
            if (prefs.getBoolean("card_" + i, false)) count++;
        }
        return count;
    }

    private String cardLabel(int index) {
        String[] ranks = {"As", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "Ocho", "Nueve", "Diez", "Aprendiz", "Explorador", "Reina", "Guardian"};
        if (index < 22) return "Arcano " + index;
        return ranks[(index - 22) % 14];
    }

    private void openDetail(int cardId, boolean unlocked) {
        if (!unlocked) {
            prefs.edit().putBoolean("card_" + cardId, true).apply();
        }
        Intent intent = new Intent(this, CardDetailActivity.class);
        intent.putExtra("numero", cardId);
        startActivity(intent);
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
