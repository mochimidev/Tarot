package com.example.evaluacion4charlottegabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evaluacion4charlottegabriel.Dao.Carta;
import com.example.evaluacion4charlottegabriel.ui.AnimatedRevealCard;
import com.example.evaluacion4charlottegabriel.ui.DreamButton;
import com.example.evaluacion4charlottegabriel.ui.DreamColors;
import com.example.evaluacion4charlottegabriel.ui.DreamUi;
import com.example.evaluacion4charlottegabriel.ui.GlassPanel;
import com.example.evaluacion4charlottegabriel.ui.CardMeta;
import com.example.evaluacion4charlottegabriel.ui.TarotScaffold;

public class CartaDelDia extends AppCompatActivity {
    private int numero;
    private Carta carta;
    private String titulo;
    private String descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            numero = bundle.getInt("numero");
            int rotacion = bundle.getInt("rotacion");
            carta = (Carta) bundle.getSerializable("carta");
            build(rotacion);
        }
    }

    private void build(int rotacion) {
        TarotScaffold scaffold = new TarotScaffold(this);
        LinearLayout root = scaffold.content();

        TextView header = DreamUi.text(this, "Carta del Dia", 29, DreamColors.INK, Typeface.BOLD);
        header.setGravity(Gravity.CENTER);
        root.addView(header);

        TextView hint = DreamUi.text(this, "Toca la carta flotante y deja que abra una pagina nueva de tu cuento.", 15, DreamColors.DEEP, Typeface.NORMAL);
        hint.setGravity(Gravity.CENTER);
        add(root, hint, 4, 18);

        AnimatedRevealCard reveal = new AnimatedRevealCard(this);
        reveal.setCardImage(numero, rotacion);
        FrameLayout frame = new FrameLayout(this);
        GradientDrawable aura = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{0x55ffffff, 0x22fff0fa});
        aura.setCornerRadius(DreamUi.dp(this, 34));
        frame.setBackground(aura);
        frame.setPadding(DreamUi.dp(this, 28), DreamUi.dp(this, 12), DreamUi.dp(this, 28), DreamUi.dp(this, 12));
        frame.addView(reveal, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DreamUi.dp(this, 500)));
        add(root, frame, 2, 18);

        GlassPanel info = new GlassPanel(this);
        info.setGravity(Gravity.CENTER_HORIZONTAL);
        if (carta != null) {
            titulo = rotacion == 0 ? carta.getTitulo() : carta.getTitulo() + " (Invertida)";
            descripcion = rotacion == 0 ? carta.getDescripcion() : carta.getDescripcionInvertida();
        } else {
            titulo = "Carta no disponible";
            descripcion = "No se pudo cargar la informacion de esta carta.";
        }

        TextView name = DreamUi.text(this, titulo, 25, DreamColors.INK, Typeface.BOLD);
        name.setGravity(Gravity.CENTER);
        info.addView(name);

        TextView family = DreamUi.text(this, CardMeta.familyName(numero) + " · " + CardMeta.rarity(numero), 13, CardMeta.familyColor(numero), Typeface.BOLD);
        family.setGravity(Gravity.CENTER);
        add(info, family, 3, 10);

        TextView meaningLabel = DreamUi.label(this, "Mensaje de hoy", DreamColors.GOLD);
        meaningLabel.setGravity(Gravity.CENTER);
        add(info, meaningLabel, 12, 4);
        TextView meaning = DreamUi.text(this, descripcion, 15, DreamColors.DEEP, Typeface.NORMAL);
        meaning.setGravity(Gravity.CENTER);
        add(info, meaning, 8, 14);
        DreamButton save = new DreamButton(this, "Guardar en mi album");
        save.setOnClickListener(v -> saveCard());
        add(info, save, 18, 0);
        add(root, info, 0, 12);

        setContentView(scaffold);
        reveal.postDelayed(reveal::reveal, 500);
    }

    private void saveCard() {
        SharedPreferences prefs = getSharedPreferences("collection", MODE_PRIVATE);
        prefs.edit().putBoolean("card_" + numero, true).apply();
        Toast.makeText(this, "Carta guardada en tu album", Toast.LENGTH_SHORT).show();
    }
    private void add(LinearLayout parent, android.view.View child, int top, int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DreamUi.dp(this, top), 0, DreamUi.dp(this, bottom));
        parent.addView(child, params);
    }
}
