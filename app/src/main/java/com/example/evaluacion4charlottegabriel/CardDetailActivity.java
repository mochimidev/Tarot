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
import com.example.evaluacion4charlottegabriel.ui.DreamBottomNav;
import com.example.evaluacion4charlottegabriel.ui.DreamButton;
import com.example.evaluacion4charlottegabriel.ui.DreamColors;
import com.example.evaluacion4charlottegabriel.ui.DreamDividerView;
import com.example.evaluacion4charlottegabriel.ui.DreamTopBar;
import com.example.evaluacion4charlottegabriel.ui.DreamUi;
import com.example.evaluacion4charlottegabriel.ui.GlassPanel;
import com.example.evaluacion4charlottegabriel.ui.KawaiiSymbolView;
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
        build(TarotNavigator.fallbackCard(numero));
        FirebaseTarotDatabase.root().child(String.valueOf(numero)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Carta carta = snapshot.getValue(Carta.class);
                build(carta != null ? carta : TarotNavigator.fallbackCard(numero));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CardDetailActivity.this, "Mostrando significado local", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void build(Carta carta) {
        TarotScaffold scaffold = new TarotScaffold(this);
        scaffold.setBottomNav(DreamBottomNav.COLLECTION);
        root = scaffold.content();
        String title = safeTitle(carta);
        root.addView(new DreamTopBar(this, title, true, KawaiiSymbolView.STAR));

        TarotCardWidget card = new TarotCardWidget(this);
        card.bind(numero, title, CardMeta.familyCaption(numero), 0);
        add(root, card, 4, 14);

        TextView meta = DreamUi.text(this, CardMeta.familyTag(numero), 14, CardMeta.familyColor(numero), Typeface.BOLD);
        meta.setGravity(Gravity.CENTER);
        add(root, meta, 0, 8);

        addStoryPanel(title, carta);
        addActions();
        setContentView(scaffold);
    }

    private void addStoryPanel(String title, Carta carta) {
        GlassPanel panel = new GlassPanel(this);
        TextView storyTitle = DreamUi.text(this, "Significado", 22, DreamColors.INK, Typeface.BOLD);
        storyTitle.setGravity(Gravity.CENTER);
        panel.addView(storyTitle);
        add(panel, new DreamDividerView(this), 0, 8);

        TextView story = DreamUi.text(this, CardMeta.story(title, numero), 15, DreamColors.DEEP, Typeface.NORMAL);
        story.setGravity(Gravity.CENTER);
        add(panel, story, 8, 14);

        TextView meaningLabel = DreamUi.label(this, "Mensaje", DreamColors.GOLD);
        meaningLabel.setGravity(Gravity.CENTER);
        panel.addView(meaningLabel);
        TextView meaning = DreamUi.text(this, textOrFallback(carta.getDescripcion(), CardMeta.familyMeaning(numero)), 14, DreamColors.DEEP, Typeface.NORMAL);
        meaning.setGravity(Gravity.CENTER);
        add(panel, meaning, 6, 12);

        TextView reversedLabel = DreamUi.label(this, "Invertida", DreamColors.ROSE);
        reversedLabel.setGravity(Gravity.CENTER);
        panel.addView(reversedLabel);
        TextView reversed = DreamUi.text(this, textOrFallback(carta.getDescripcionInvertida(), "Observa con calma lo que todavia esta tomando forma."), 14, DreamColors.DEEP, Typeface.NORMAL);
        reversed.setGravity(Gravity.CENTER);
        add(panel, reversed, 6, 0);

        add(root, panel, 0, 14);
    }

    private void addActions() {
        GlassPanel actions = new GlassPanel(this);
        actions.setGravity(Gravity.CENTER_HORIZONTAL);
        actions.setPadding(DreamUi.dp(this, 16), DreamUi.dp(this, 16),
                DreamUi.dp(this, 16), DreamUi.dp(this, 18));
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

        DreamButton collection = new DreamButton(this, "En coleccion");
        collection.setOnClickListener(v -> {
            prefs.edit().putBoolean("card_" + numero, true).apply();
            Toast.makeText(this, "Guardada en tu album", Toast.LENGTH_SHORT).show();
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.topMargin = DreamUi.dp(this, 12);
        row.addView(collection, params);

        DreamButton listen = new DreamButton(this, "Escuchar");
        listen.setOnClickListener(v -> Toast.makeText(this, "Susurro magico preparado", Toast.LENGTH_SHORT).show());
        LinearLayout.LayoutParams listenParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        listenParams.topMargin = DreamUi.dp(this, 12);
        row.addView(listen, listenParams);

        actions.addView(row);
        add(root, actions, 0, 0);
    }

    private String safeTitle(Carta carta) {
        String title = carta == null ? null : carta.getTitulo();
        return title == null || title.trim().isEmpty() ? CardMeta.cardTitle(numero) : title;
    }

    private String textOrFallback(String text, String fallback) {
        return text == null || text.trim().isEmpty() ? fallback : text;
    }

    private void add(LinearLayout parent, android.view.View child, int top, int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DreamUi.dp(this, top), 0, DreamUi.dp(this, bottom));
        parent.addView(child, params);
    }
}
