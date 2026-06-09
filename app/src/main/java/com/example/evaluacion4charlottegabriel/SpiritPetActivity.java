package com.example.evaluacion4charlottegabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.evaluacion4charlottegabriel.ui.DreamButton;
import com.example.evaluacion4charlottegabriel.ui.DreamColors;
import com.example.evaluacion4charlottegabriel.ui.DreamUi;
import com.example.evaluacion4charlottegabriel.ui.GlassPanel;
import com.example.evaluacion4charlottegabriel.ui.SpiritPetWidget;
import com.example.evaluacion4charlottegabriel.ui.TarotScaffold;

public class SpiritPetActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = getSharedPreferences("collection", MODE_PRIVATE);
        int savedCards = prefs.getAll().size();
        int level = Math.max(1, savedCards / 4 + 1);

        TarotScaffold scaffold = new TarotScaffold(this);
        LinearLayout root = scaffold.content();
        TextView header = DreamUi.text(this, "Mi Mascota", 30, DreamColors.INK, Typeface.BOLD);
        header.setGravity(Gravity.CENTER);
        root.addView(header);

        GlassPanel panel = new GlassPanel(this);
        panel.setGravity(Gravity.CENTER_HORIZONTAL);
        SpiritPetWidget pet = new SpiritPetWidget(this);
        pet.setLevel(level);
        panel.addView(pet, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DreamUi.dp(this, 300)));
        TextView name = DreamUi.text(this, "Lumi, el Loco Unicornio", 24, DreamColors.INK, Typeface.BOLD);
        name.setGravity(Gravity.CENTER);
        panel.addView(name);
        TextView desc = DreamUi.text(this, "Nivel " + level + ". Reacciona a tus lecturas y crece cuando guardas cartas en la coleccion.", 15, DreamColors.DEEP, Typeface.NORMAL);
        desc.setGravity(Gravity.CENTER);
        add(panel, desc, 8, 14);
        DreamButton action = new DreamButton(this, "Darle brillo");
        action.setOnClickListener(v -> {
            v.animate().rotationBy(4f).scaleX(1.04f).scaleY(1.04f).setDuration(160).withEndAction(() ->
                    v.animate().rotation(0f).scaleX(1f).scaleY(1f).setDuration(180).start()).start();
            pet.animate().translationY(-DreamUi.dp(this, 14)).setDuration(180).withEndAction(() ->
                    pet.animate().translationY(0).setDuration(220).start()).start();
        });
        panel.addView(action);
        add(root, panel, 18, 18);

        GlassPanel reactions = new GlassPanel(this);
        TextView title = DreamUi.text(this, "Estado emocional", 20, DreamColors.INK, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        reactions.addView(title);
        TextView body = DreamUi.text(this, savedCards == 0
                ? "Lumi esta curiosa. Guarda tu primera carta diaria para despertar su primera reaccion."
                : "Lumi esta feliz: siente " + savedCards + " brotes de cartas en tu album.",
                15, DreamColors.DEEP, Typeface.NORMAL);
        body.setGravity(Gravity.CENTER);
        add(reactions, body, 8, 0);
        add(root, reactions, 0, 0);
        setContentView(scaffold);
    }

    private void add(LinearLayout parent, android.view.View child, int top, int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DreamUi.dp(this, top), 0, DreamUi.dp(this, bottom));
        parent.addView(child, params);
    }
}
