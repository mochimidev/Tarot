package com.example.evaluacion4charlottegabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.evaluacion4charlottegabriel.Dao.Carta;
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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SiYNo extends AppCompatActivity {
    private static final Integer[] CARTAS_SI = {0, 1, 3, 6, 7, 8, 10, 14, 17, 19, 20, 21, 22, 23, 24, 25, 27, 29, 30, 32, 33, 34, 35, 36, 37, 38, 41, 44, 45, 46, 47, 48, 49, 50, 60, 61, 64, 66, 69, 71, 72, 73, 74, 75, 76, 77};
    private static final Integer[] CARTAS_NO = {13, 15, 16, 18, 26, 28, 31, 40, 43, 52, 54, 56, 57, 58, 59, 67, 68};
    private final Set<Integer> siSet = new HashSet<>(Arrays.asList(CARTAS_SI));
    private final Set<Integer> noSet = new HashSet<>(Arrays.asList(CARTAS_NO));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        int numero = 36;
        int rotation = 0;
        Carta carta = TarotNavigator.fallbackCard(numero);
        if (bundle != null) {
            numero = bundle.getInt("numero");
            rotation = bundle.getInt("rotacion");
            carta = (Carta) bundle.getSerializable("carta");
            if (carta == null) carta = TarotNavigator.fallbackCard(numero);
        }
        build(numero, rotation, carta);
    }

    private void build(int numero, int rotation, Carta carta) {
        TarotScaffold scaffold = new TarotScaffold(this);
        scaffold.setBottomNav(DreamBottomNav.READINGS);
        LinearLayout root = scaffold.content();
        root.addView(new DreamTopBar(this, "Si o No", true, KawaiiSymbolView.HEART));

        TextView prompt = DreamUi.text(this, "Piensa en tu pregunta\ncon el corazon", 15, DreamColors.DEEP, Typeface.NORMAL);
        prompt.setGravity(Gravity.CENTER);
        add(root, prompt, 0, 10);

        FrameLayout dropFrame = new FrameLayout(this);
        KawaiiSymbolView drop = new KawaiiSymbolView(this, KawaiiSymbolView.DROP);
        dropFrame.addView(drop, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DreamUi.dp(this, 190)));
        add(root, dropFrame, 0, 8);

        DreamButton consult = new DreamButton(this, "Consultar");
        add(root, consult, 0, 20);

        GlassPanel result = resultPanel(numero, rotation, carta);
        result.setVisibility(View.GONE);
        add(root, result, 0, 0);
        consult.setOnClickListener(v -> {
            result.setVisibility(View.VISIBLE);
            consult.setVisibility(View.GONE);
        });
        setContentView(scaffold);
    }

    private GlassPanel resultPanel(int numero, int rotation, Carta carta) {
        GlassPanel result = new GlassPanel(this);
        result.setGravity(Gravity.CENTER_HORIZONTAL);
        String answer = obtenerResultado(numero);

        TextView label = DreamUi.text(this, answer, 34, answerColor(answer), Typeface.BOLD);
        label.setGravity(Gravity.CENTER);
        result.addView(label);
        add(result, new DreamDividerView(this), 0, 8);

        KawaiiSymbolView reaction = new KawaiiSymbolView(this, CardMeta.familySymbol(numero));
        result.addView(reaction, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                DreamUi.dp(this, 112)));
        TextView sub = DreamUi.text(this, responseCopy(answer), 15, DreamColors.DEEP, Typeface.NORMAL);
        sub.setGravity(Gravity.CENTER);
        add(result, sub, 8, 8);

        String desc = rotation == 0 ? carta.getDescripcion() : carta.getDescripcionInvertida();
        if (desc == null || desc.trim().isEmpty()) {
            desc = CardMeta.familyMeaning(numero);
        }
        TextView explanation = DreamUi.text(this, desc, 13, DreamColors.MUTED, Typeface.NORMAL);
        explanation.setGravity(Gravity.CENTER);
        add(result, explanation, 2, 16);
        DreamButton again = new DreamButton(this, "Hacer otra pregunta");
        again.setOnClickListener(v -> {
            TarotNavigator.openYesNo(this);
            finish();
        });
        add(result, again, 0, 0);
        return result;
    }

    private String obtenerResultado(int numeroCarta) {
        if (siSet.contains(numeroCarta)) return "Si";
        if (noSet.contains(numeroCarta)) return "No";
        return "Tal vez";
    }

    private int answerColor(String answer) {
        if ("Si".equals(answer)) return DreamColors.LILAC_DARK;
        if ("No".equals(answer)) return DreamColors.ROSE;
        return DreamColors.GOLD;
    }

    private String responseCopy(String answer) {
        if ("Si".equals(answer)) return "La energia favorece tu camino.";
        if ("No".equals(answer)) return "La carta sugiere esperar y cuidar tu paz.";
        return "Todavia hay nubes suaves en la respuesta.";
    }

    private void add(LinearLayout parent, View child, int top, int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DreamUi.dp(this, top), 0, DreamUi.dp(this, bottom));
        parent.addView(child, params);
    }
}
