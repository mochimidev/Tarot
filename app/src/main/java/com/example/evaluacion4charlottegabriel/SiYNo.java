package com.example.evaluacion4charlottegabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.evaluacion4charlottegabriel.Dao.Carta;
import com.example.evaluacion4charlottegabriel.ui.DreamColors;
import com.example.evaluacion4charlottegabriel.ui.DreamUi;
import com.example.evaluacion4charlottegabriel.ui.GlassPanel;
import com.example.evaluacion4charlottegabriel.ui.TarotCardWidget;
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
        if (bundle == null) {
            return;
        }
        int numero = bundle.getInt("numero");
        int rotacion = bundle.getInt("rotacion");
        Carta carta = (Carta) bundle.getSerializable("carta");
        build(numero, rotacion, carta);
    }

    private void build(int numero, int rotacion, Carta carta) {
        TarotScaffold scaffold = new TarotScaffold(this);
        LinearLayout root = scaffold.content();
        TextView header = DreamUi.text(this, "Consulta Si o No", 29, DreamColors.INK, Typeface.BOLD);
        header.setGravity(Gravity.CENTER);
        root.addView(header);

        String title = carta == null ? "Carta no disponible" : rotacion == 0 ? carta.getTitulo() : carta.getTitulo() + " (Invertida)";
        String desc = carta == null ? "" : rotacion == 0 ? carta.getDescripcion() : carta.getDescripcionInvertida();
        TarotCardWidget card = new TarotCardWidget(this);
        card.bind(numero, title, desc, rotacion);
        add(root, card, 18, 18);

        GlassPanel result = new GlassPanel(this);
        TextView label = DreamUi.text(this, obtenerResultado(numero), 28, DreamColors.INK, Typeface.BOLD);
        label.setGravity(Gravity.CENTER);
        result.addView(label);
        TextView sub = DreamUi.text(this, "Respira, mira tu carta y deja que la respuesta se sienta tranquila.", 15, DreamColors.DEEP, Typeface.NORMAL);
        sub.setGravity(Gravity.CENTER);
        add(result, sub, 8, 0);
        add(root, result, 0, 0);
        setContentView(scaffold);
    }

    private String obtenerResultado(int numeroCarta) {
        if (siSet.contains(numeroCarta)) {
            return "SI";
        } else if (noSet.contains(numeroCarta)) {
            return "NO";
        }
        return "TAL VEZ";
    }
    private void add(LinearLayout parent, android.view.View child, int top, int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DreamUi.dp(this, top), 0, DreamUi.dp(this, bottom));
        parent.addView(child, params);
    }
}
