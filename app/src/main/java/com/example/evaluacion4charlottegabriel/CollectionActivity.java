package com.example.evaluacion4charlottegabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.evaluacion4charlottegabriel.ui.CardMeta;
import com.example.evaluacion4charlottegabriel.ui.CollectionCard;
import com.example.evaluacion4charlottegabriel.ui.DreamBottomNav;
import com.example.evaluacion4charlottegabriel.ui.DreamColors;
import com.example.evaluacion4charlottegabriel.ui.DreamTopBar;
import com.example.evaluacion4charlottegabriel.ui.DreamUi;
import com.example.evaluacion4charlottegabriel.ui.KawaiiSymbolView;
import com.example.evaluacion4charlottegabriel.ui.TarotScaffold;

public class CollectionActivity extends AppCompatActivity {
    private static final String EXTRA_FAMILY = "family";

    private final Family[] families = new Family[]{
            new Family("Gotitas", "Emociones, ternura e intuici\u00f3n", "gotitas", 36, 49,
                    R.drawable.icon_si_o_no, DreamColors.CLOUD),
            new Family("Chispas", "Valent\u00eda, juego y comienzos", "chispas", 22, 35,
                    R.drawable.icon_carta_dia, 0xffffd9bd),
            new Family("Estrellas", "Deseos, gu\u00eda y confianza", "estrellas", 50, 63,
                    R.drawable.home_star_icon, 0xfffff1ba),
            new Family("Brotes", "Crecimiento, cuidado y abundancia", "brotes", 64, 77,
                    R.drawable.icon_coleccion, DreamColors.SPROUT_SOFT),
            new Family("Unicornios", "El cuento principal del destino", "unicornios", 0, 21,
                    R.drawable.home_unicorn_hero, 0xffffeaf8)
    };

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("collection", MODE_PRIVATE);
        String familyKey = getIntent().getStringExtra(EXTRA_FAMILY);
        Family family = familyForKey(familyKey);
        if (family == null) {
            buildFamilyIndex();
        } else {
            buildFamilyAlbum(family);
        }
    }

    private void buildFamilyIndex() {
        TarotScaffold scaffold = new TarotScaffold(this);
        scaffold.setBottomNav(DreamBottomNav.COLLECTION);
        LinearLayout root = scaffold.content();
        root.addView(new DreamTopBar(this, "Colecci\u00f3n", true, KawaiiSymbolView.STAR));

        addTotalProgress(root);
        for (Family family : families) {
            addFamilyRow(root, family);
        }
        setContentView(scaffold);
    }

    private void addTotalProgress(LinearLayout root) {
        int unlocked = countUnlocked(0, 77);
        LinearLayout chip = new LinearLayout(this);
        chip.setGravity(Gravity.CENTER);
        chip.setPadding(DreamUi.dp(this, 12), DreamUi.dp(this, 8), DreamUi.dp(this, 12), DreamUi.dp(this, 8));
        chip.setBackground(DreamUi.stroked(0xccfffaf3, 0x88ddb066,
                DreamUi.dp(this, 22), DreamUi.dp(this, 1)));

        TextView text = DreamUi.text(this, unlocked + " / 78 cartas desbloqueadas", 12,
                DreamColors.DEEP, Typeface.BOLD);
        text.setGravity(Gravity.CENTER);
        chip.addView(text);
        add(root, chip, 4, 12);
    }

    private void addFamilyRow(LinearLayout root, Family family) {
        FrameLayout card = new FrameLayout(this);
        DreamUi.softLayer(card);
        card.setClickable(true);
        card.setOnClickListener(v -> openFamily(family));
        card.setElevation(DreamUi.dp(this, 3));

        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{DreamUi.blend(family.tint, 0xffffffff, .24f), 0xfffffbf7});
        bg.setCornerRadius(DreamUi.dp(this, 22));
        bg.setStroke(DreamUi.dp(this, 1), 0xaed9a856);
        card.setBackground(bg);

        LinearLayout row = new LinearLayout(this);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(DreamUi.dp(this, 14), DreamUi.dp(this, 12),
                DreamUi.dp(this, 12), DreamUi.dp(this, 12));

        FrameLayout iconShell = new FrameLayout(this);
        GradientDrawable iconBg = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{0xeaffffff, DreamUi.blend(family.tint, 0xffffffff, .12f)});
        iconBg.setCornerRadius(DreamUi.dp(this, 20));
        iconBg.setStroke(DreamUi.dp(this, 1), 0x65ddb066);
        iconShell.setBackground(iconBg);

        ImageView icon = new ImageView(this);
        icon.setImageResource(family.iconRes);
        icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        iconShell.addView(icon, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                Gravity.CENTER));
        row.addView(iconShell, new LinearLayout.LayoutParams(DreamUi.dp(this, 64), DreamUi.dp(this, 64)));

        LinearLayout copy = new LinearLayout(this);
        copy.setOrientation(LinearLayout.VERTICAL);
        copy.setGravity(Gravity.CENTER_VERTICAL);
        copy.setPadding(DreamUi.dp(this, 14), 0, DreamUi.dp(this, 8), 0);

        TextView name = DreamUi.text(this, family.name, 20, DreamColors.INK, Typeface.BOLD);
        name.setIncludeFontPadding(false);
        copy.addView(name);

        int opened = countUnlocked(family.start, family.end);
        TextView progress = DreamUi.text(this,
                opened + " / " + family.size() + " desbloqueadas",
                12, DreamColors.DEEP, Typeface.BOLD);
        progress.setIncludeFontPadding(false);
        copy.addView(progress);

        TextView subtitle = DreamUi.text(this, family.subtitle, 11, DreamColors.MUTED, Typeface.NORMAL);
        subtitle.setIncludeFontPadding(false);
        copy.addView(subtitle);

        row.addView(copy, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

        TextView arrow = DreamUi.text(this, "\u203a", 27, DreamColors.LILAC_DARK, Typeface.BOLD);
        arrow.setGravity(Gravity.CENTER);
        arrow.setIncludeFontPadding(false);
        row.addView(arrow, new LinearLayout.LayoutParams(DreamUi.dp(this, 28), DreamUi.dp(this, 52)));

        card.addView(row, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        add(root, card, 0, 10);
    }

    private void buildFamilyAlbum(Family family) {
        TarotScaffold scaffold = new TarotScaffold(this);
        scaffold.setBottomNav(DreamBottomNav.COLLECTION);
        LinearLayout root = scaffold.content();
        root.addView(new DreamTopBar(this, family.name, true, symbolForFamily(family)));

        TextView summary = DreamUi.text(this,
                countUnlocked(family.start, family.end) + " / " + family.size()
                        + " desbloqueadas",
                14, DreamColors.DEEP, Typeface.BOLD);
        summary.setGravity(Gravity.CENTER);
        add(root, summary, 4, 12);

        GridLayout grid = new GridLayout(this);
        grid.setColumnCount(3);
        for (int i = family.start; i <= family.end; i++) {
            boolean open = prefs.getBoolean("card_" + i, false);
            CollectionCard card = new CollectionCard(this, i, CardMeta.cardTitle(i), open, CardMeta.rarity(i));
            final int cardId = i;
            card.setOnClickListener(v -> openDetail(cardId));
            card.setClickable(true);
            addCard(grid, card);
        }
        add(root, grid, 0, 0);
        setContentView(scaffold);
    }

    private int symbolForFamily(Family family) {
        if ("gotitas".equals(family.key)) return KawaiiSymbolView.DROP;
        if ("chispas".equals(family.key)) return KawaiiSymbolView.FLAME;
        if ("brotes".equals(family.key)) return KawaiiSymbolView.SPROUT;
        if ("unicornios".equals(family.key)) return KawaiiSymbolView.HEART;
        return KawaiiSymbolView.STAR;
    }

    private void openFamily(Family family) {
        Intent intent = new Intent(this, CollectionActivity.class);
        intent.putExtra(EXTRA_FAMILY, family.key);
        startActivity(intent);
    }

    private Family familyForKey(String key) {
        if (key == null) return null;
        for (Family family : families) {
            if (family.key.equals(key)) return family;
        }
        return null;
    }

    private int countUnlocked(int start, int end) {
        int count = 0;
        for (int i = start; i <= end; i++) {
            if (prefs.getBoolean("card_" + i, false)) count++;
        }
        return count;
    }

    private void openDetail(int cardId) {
        Intent intent = new Intent(this, CardDetailActivity.class);
        intent.putExtra("numero", cardId);
        startActivity(intent);
    }

    private void addCard(GridLayout grid, CollectionCard card) {
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.setMargins(DreamUi.dp(this, 4), DreamUi.dp(this, 4), DreamUi.dp(this, 4), DreamUi.dp(this, 8));
        grid.addView(card, params);
    }

    private void add(LinearLayout parent, View child, int top, int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DreamUi.dp(this, top), 0, DreamUi.dp(this, bottom));
        parent.addView(child, params);
    }

    private static class Family {
        final String name;
        final String subtitle;
        final String key;
        final int start;
        final int end;
        final int iconRes;
        final int tint;

        Family(String name, String subtitle, String key, int start, int end, int iconRes, int tint) {
            this.name = name;
            this.subtitle = subtitle;
            this.key = key;
            this.start = start;
            this.end = end;
            this.iconRes = iconRes;
            this.tint = tint;
        }

        int size() {
            return end - start + 1;
        }
    }
}
