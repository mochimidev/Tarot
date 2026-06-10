package com.example.evaluacion4charlottegabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.evaluacion4charlottegabriel.ui.DreamBottomNav;
import com.example.evaluacion4charlottegabriel.ui.DreamColors;
import com.example.evaluacion4charlottegabriel.ui.DreamDividerView;
import com.example.evaluacion4charlottegabriel.ui.DreamTopBar;
import com.example.evaluacion4charlottegabriel.ui.DreamUi;
import com.example.evaluacion4charlottegabriel.ui.GlassPanel;
import com.example.evaluacion4charlottegabriel.ui.KawaiiSymbolView;
import com.example.evaluacion4charlottegabriel.ui.MagicSceneView;
import com.example.evaluacion4charlottegabriel.ui.TarotScaffold;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildHome();
    }

    private void buildHome() {
        TarotScaffold scaffold = new TarotScaffold(this);
        scaffold.setBottomNav(DreamBottomNav.HOME);
        LinearLayout root = scaffold.content();

        root.addView(new DreamTopBar(this, "Tarot Kawaii Dreams", false, KawaiiSymbolView.HEART));

        ImageView logo = new ImageView(this);
        logo.setImageResource(R.drawable.tarot_kawaii_dreams_logo);
        logo.setAdjustViewBounds(true);
        logo.setScaleType(ImageView.ScaleType.FIT_CENTER);
        add(root, logo, 0, 2, 4);

        TextView greeting = DreamUi.text(this, "\u00a1Hola, Charlotte! \u2728", 25, DreamColors.INK, Typeface.BOLD);
        greeting.setGravity(Gravity.CENTER);
        root.addView(greeting);

        TextView subtitle = DreamUi.text(this, "\u00bfQue mensaje magico te espera hoy?", 15, DreamColors.DEEP, Typeface.NORMAL);
        subtitle.setGravity(Gravity.CENTER);
        add(root, subtitle, 2, 4);

        MagicSceneView scene = new MagicSceneView(this, MagicSceneView.FLOATING_CARD);
        root.addView(scene, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DreamUi.dp(this, 210)));
        add(root, new DreamDividerView(this), 0, 4);
        addDailyFeature(root);

        GridLayout menu = new GridLayout(this);
        menu.setColumnCount(2);
        add(root, menu, 0, 0, 0);

        addMenu(menu, "Si o No", "Obten una respuesta clara y sencilla.", KawaiiSymbolView.DROP, DreamColors.CLOUD, v -> TarotNavigator.openYesNo(this));
        addMenu(menu, "Tarot de Parejas", "Explora la conexion en su vinculo.", KawaiiSymbolView.HEART, DreamColors.ROSE_SOFT, v -> TarotNavigator.openCouples(this));
        addMenu(menu, "Coleccion", "Reune y descubre todas las cartas.", KawaiiSymbolView.SPROUT, DreamColors.SPROUT_SOFT, v -> TarotNavigator.openCollection(this));
        addMenu(menu, "Ajustes", "Sonido, brillos y preferencias.", KawaiiSymbolView.STAR, 0xffedf5ff, v -> TarotNavigator.openSettings(this));

        setContentView(scaffold);
    }

    private void addDailyFeature(LinearLayout root) {
        GlassPanel panel = new GlassPanel(this);
        panel.setOrientation(LinearLayout.HORIZONTAL);
        panel.setGravity(Gravity.CENTER_VERTICAL);
        panel.setClickable(true);
        panel.setOnClickListener(v -> TarotNavigator.openDailyCard(this));
        panel.setPadding(DreamUi.dp(this, 12), DreamUi.dp(this, 10), DreamUi.dp(this, 12), DreamUi.dp(this, 10));
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{0xeefff8ef, 0xfafff3fb, 0xeefff7df});
        bg.setCornerRadius(DreamUi.dp(this, 18));
        bg.setStroke(DreamUi.dp(this, 2), 0xaee4a641);
        panel.setBackground(bg);

        KawaiiSymbolView star = new KawaiiSymbolView(this, KawaiiSymbolView.STAR);
        panel.addView(star, new LinearLayout.LayoutParams(DreamUi.dp(this, 64), DreamUi.dp(this, 64)));

        LinearLayout copy = new LinearLayout(this);
        copy.setOrientation(LinearLayout.VERTICAL);
        TextView title = DreamUi.text(this, "Carta del Dia", 18, DreamColors.INK, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        copy.addView(title);
        TextView body = DreamUi.text(this, "Descubre el mensaje que el universo tiene para ti.", 12, DreamColors.DEEP, Typeface.NORMAL);
        body.setGravity(Gravity.CENTER);
        copy.addView(body);
        panel.addView(copy, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

        KawaiiSymbolView flame = new KawaiiSymbolView(this, KawaiiSymbolView.FLAME);
        panel.addView(flame, new LinearLayout.LayoutParams(DreamUi.dp(this, 58), DreamUi.dp(this, 58)));
        add(root, panel, 4, 12);
    }

    private void addMenu(GridLayout grid, String title, String subtitle, int symbol, int tint, View.OnClickListener click) {
        GlassPanel panel = new GlassPanel(this);
        panel.setClickable(true);
        panel.setOnClickListener(click);
        panel.setMinimumHeight(DreamUi.dp(this, 134));
        panel.setPadding(DreamUi.dp(this, 12), DreamUi.dp(this, 12), DreamUi.dp(this, 12), DreamUi.dp(this, 14));
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{DreamUi.blend(tint, 0xffffffff, .24f), 0xfafffbf4});
        bg.setCornerRadius(DreamUi.dp(this, 18));
        bg.setStroke(DreamUi.dp(this, 1), 0x99e4a641);
        panel.setBackground(bg);

        KawaiiSymbolView icon = new KawaiiSymbolView(this, symbol);
        panel.addView(icon, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DreamUi.dp(this, 52)));

        TextView titleView = DreamUi.text(this, title, 16, DreamColors.INK, Typeface.BOLD);
        titleView.setGravity(Gravity.CENTER);
        panel.addView(titleView);
        TextView sub = DreamUi.text(this, subtitle, 11, DreamColors.DEEP, Typeface.NORMAL);
        sub.setGravity(Gravity.CENTER);
        panel.addView(sub);

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.setMargins(DreamUi.dp(this, 5), DreamUi.dp(this, 6), DreamUi.dp(this, 5), DreamUi.dp(this, 6));
        grid.addView(panel, params);
    }

    private void add(LinearLayout parent, View child, int left, int top, int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(DreamUi.dp(this, left), DreamUi.dp(this, top), DreamUi.dp(this, left), DreamUi.dp(this, bottom));
        if (child instanceof ImageView) {
            params.height = DreamUi.dp(this, 74);
        }
        parent.addView(child, params);
    }

    private void add(LinearLayout parent, View child, int top, int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DreamUi.dp(this, top), 0, DreamUi.dp(this, bottom));
        parent.addView(child, params);
    }
}
