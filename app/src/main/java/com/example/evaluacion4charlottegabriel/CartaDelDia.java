package com.example.evaluacion4charlottegabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evaluacion4charlottegabriel.Dao.Carta;
import com.example.evaluacion4charlottegabriel.ui.AnimatedRevealCard;
import com.example.evaluacion4charlottegabriel.ui.CardMeta;
import com.example.evaluacion4charlottegabriel.ui.DreamBottomNav;
import com.example.evaluacion4charlottegabriel.ui.DreamButton;
import com.example.evaluacion4charlottegabriel.ui.DreamColors;
import com.example.evaluacion4charlottegabriel.ui.DreamDividerView;
import com.example.evaluacion4charlottegabriel.ui.DreamTopBar;
import com.example.evaluacion4charlottegabriel.ui.DreamUi;
import com.example.evaluacion4charlottegabriel.ui.GlassPanel;
import com.example.evaluacion4charlottegabriel.ui.KawaiiSymbolView;
import com.example.evaluacion4charlottegabriel.ui.TarotScaffold;

public class CartaDelDia extends AppCompatActivity {
    private int numero;
    private Carta carta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        int rotation = 0;
        if (bundle != null) {
            numero = bundle.getInt("numero");
            rotation = bundle.getInt("rotacion");
            carta = (Carta) bundle.getSerializable("carta");
        } else {
            numero = 0;
            carta = TarotNavigator.fallbackCard(numero);
        }
        if (carta == null) {
            carta = TarotNavigator.fallbackCard(numero);
        }
        build(rotation);
    }

    private void build(int rotation) {
        TarotScaffold scaffold = new TarotScaffold(this);
        scaffold.setBottomNav(DreamBottomNav.READINGS);
        LinearLayout root = scaffold.content();
        root.addView(new DreamTopBar(this, "Carta del Dia", true, KawaiiSymbolView.STAR));

        AnimatedRevealCard reveal = new AnimatedRevealCard(this);
        reveal.setCardImage(numero, rotation);
        FrameLayout frame = new FrameLayout(this);
        GradientDrawable aura = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{
                        DreamColors.alpha(DreamColors.SOFT_WHITE, 120),
                        DreamColors.alpha(DreamColors.SOFT_GOLD, 44)});
        aura.setCornerRadius(DreamUi.dp(this, 34));
        frame.setBackground(aura);
        frame.setPadding(DreamUi.dp(this, 24), DreamUi.dp(this, 8), DreamUi.dp(this, 24), DreamUi.dp(this, 8));
        frame.addView(reveal, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DreamUi.dp(this, 460)));
        add(root, frame, 2, 14);

        TextView hint = DreamUi.text(this, "Toca la carta para\nrevelar tu mensaje", 16, DreamColors.DEEP, Typeface.NORMAL);
        hint.setGravity(Gravity.CENTER);
        add(root, hint, 0, 8);
        add(root, new DreamDividerView(this), 0, 8);

        GlassPanel info = resultPanel(rotation);
        info.setVisibility(View.GONE);
        add(root, info, 0, 12);
        reveal.setOnRevealListener(() -> {
            hint.setVisibility(View.GONE);
            info.setVisibility(View.VISIBLE);
        });
        setContentView(scaffold);
    }

    private GlassPanel resultPanel(int rotation) {
        GlassPanel info = new GlassPanel(this);
        info.setGravity(Gravity.CENTER_HORIZONTAL);
        String title = rotation == 0 ? safeTitle() : safeTitle() + " (Invertida)";
        String description = rotation == 0 ? carta.getDescripcion() : carta.getDescripcionInvertida();
        if (description == null || description.trim().isEmpty()) {
            description = CardMeta.familyMeaning(numero);
        }

        TextView name = DreamUi.text(this, title, 25, DreamColors.INK, Typeface.BOLD);
        name.setGravity(Gravity.CENTER);
        info.addView(name);

        TextView family = DreamUi.text(this, CardMeta.familyTag(numero), 13, CardMeta.familyColor(numero), Typeface.BOLD);
        family.setGravity(Gravity.CENTER);
        add(info, family, 3, 8);
        add(info, new DreamDividerView(this), 0, 8);

        TextView meaningLabel = DreamUi.label(this, "Mensaje de hoy", DreamColors.GOLD);
        meaningLabel.setGravity(Gravity.CENTER);
        add(info, meaningLabel, 6, 4);
        TextView meaning = DreamUi.text(this, description, 15, DreamColors.DEEP, Typeface.NORMAL);
        meaning.setGravity(Gravity.CENTER);
        add(info, meaning, 8, 14);
        DreamButton save = new DreamButton(this, "Guardar en mi album");
        save.setOnClickListener(v -> saveCard());
        add(info, save, 8, 0);
        return info;
    }

    private String safeTitle() {
        String title = carta != null ? carta.getTitulo() : null;
        return title == null || title.trim().isEmpty() ? CardMeta.cardTitle(numero) : title;
    }

    private void saveCard() {
        SharedPreferences prefs = getSharedPreferences("collection", MODE_PRIVATE);
        prefs.edit().putBoolean("card_" + numero, true).apply();
        Toast.makeText(this, "Carta guardada en tu album", Toast.LENGTH_SHORT).show();
    }

    private void add(LinearLayout parent, View child, int top, int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DreamUi.dp(this, top), 0, DreamUi.dp(this, bottom));
        parent.addView(child, params);
    }
}
