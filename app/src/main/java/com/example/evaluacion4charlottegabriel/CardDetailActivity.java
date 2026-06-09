package com.example.evaluacion4charlottegabriel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evaluacion4charlottegabriel.Dao.Carta;
import com.example.evaluacion4charlottegabriel.Dao.FirebaseTarotDatabase;
import com.example.evaluacion4charlottegabriel.ui.CardMeta;
import com.example.evaluacion4charlottegabriel.ui.DreamButton;
import com.example.evaluacion4charlottegabriel.ui.DreamColors;
import com.example.evaluacion4charlottegabriel.ui.DreamUi;
import com.example.evaluacion4charlottegabriel.ui.GlassPanel;
import com.example.evaluacion4charlottegabriel.ui.TarotCardWidget;
import com.example.evaluacion4charlottegabriel.ui.TarotScaffold;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class CardDetailActivity extends AppCompatActivity {
    private int numero;
    private LinearLayout root;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        numero = getIntent().getIntExtra("numero", 0);
        prefs = getSharedPreferences("collection", MODE_PRIVATE);
        build(null);
        FirebaseTarotDatabase.root().child(String.valueOf(numero)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                build(snapshot.getValue(Carta.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CardDetailActivity.this, "No se pudo cargar la historia", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void build(Carta carta) {
        TarotScaffold scaffold = new TarotScaffold(this);
        root = scaffold.content();

        String title = carta == null ? "Carta " + numero : carta.getTitulo();
        TextView header = DreamUi.text(this, title, 28, DreamColors.INK, Typeface.BOLD);
        header.setGravity(Gravity.CENTER);
        root.addView(header);

        TextView meta = DreamUi.text(this, CardMeta.familyName(numero) + " · " + CardMeta.rarity(numero), 14, CardMeta.familyColor(numero), Typeface.BOLD);
        meta.setGravity(Gravity.CENTER);
        add(root, meta, 2, 14);

        TarotCardWidget card = new TarotCardWidget(this);
        card.bind(numero, title, CardMeta.familyCaption(numero), 0);
        add(root, card, 0, 16);

        addStoryPanel(title, carta);
        addActions();
        setContentView(scaffold);
    }

    private void addStoryPanel(String title, Carta carta) {
        GlassPanel panel = new GlassPanel(this);
        TextView storyTitle = DreamUi.text(this, "Historia", 22, DreamColors.INK, Typeface.BOLD);
        storyTitle.setGravity(Gravity.CENTER);
        panel.addView(storyTitle);

        TextView story = DreamUi.text(this, CardMeta.story(title, numero), 15, DreamColors.DEEP, Typeface.NORMAL);
        story.setGravity(Gravity.CENTER);
        add(panel, story, 8, 16);

        TextView meaningLabel = DreamUi.label(this, "Significado", DreamColors.GOLD);
        panel.addView(meaningLabel);
        TextView meaning = DreamUi.text(this, carta == null ? "Una energia suave espera ser descubierta en tu album." : carta.getDescripcion(), 14, DreamColors.INK, Typeface.NORMAL);
        add(panel, meaning, 6, 12);

        TextView reversedLabel = DreamUi.label(this, "Invertida", DreamColors.ROSE);
        panel.addView(reversedLabel);
        TextView reversed = DreamUi.text(this, carta == null ? "Mira con calma lo que todavia esta tomando forma." : carta.getDescripcionInvertida(), 14, DreamColors.INK, Typeface.NORMAL);
        add(panel, reversed, 6, 0);

        add(root, panel, 0, 16);
    }

    private void addActions() {
        GlassPanel actions = new GlassPanel(this);
        actions.setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout row = new LinearLayout(this);
        row.setGravity(Gravity.CENTER);
        row.setOrientation(LinearLayout.VERTICAL);

        DreamButton favorite = new DreamButton(this, prefs.getBoolean("fav_" + numero, false) ? "Favorita" : "Marcar favorita");
        favorite.setOnClickListener(v -> {
            boolean next = !prefs.getBoolean("fav_" + numero, false);
            prefs.edit().putBoolean("fav_" + numero, next).apply();
            ((TextView) v).setText(next ? "Favorita" : "Marcar favorita");
        });
        row.addView(favorite, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        DreamButton collection = new DreamButton(this, "En mi coleccion");
        collection.setOnClickListener(v -> {
            prefs.edit().putBoolean("card_" + numero, true).apply();
            Toast.makeText(this, "Guardada en tu album", Toast.LENGTH_SHORT).show();
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = DreamUi.dp(this, 10);
        row.addView(collection, params);

        actions.addView(row);
        add(root, actions, 0, 0);
    }

    private void add(LinearLayout parent, android.view.View child, int top, int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DreamUi.dp(this, top), 0, DreamUi.dp(this, bottom));
        parent.addView(child, params);
    }
}
