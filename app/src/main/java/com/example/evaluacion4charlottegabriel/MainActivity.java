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
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.evaluacion4charlottegabriel.ui.DreamBottomNav;
import com.example.evaluacion4charlottegabriel.ui.DreamColors;
import com.example.evaluacion4charlottegabriel.ui.DreamUi;
import com.example.evaluacion4charlottegabriel.ui.GlassPanel;
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
        addHomeWash(scaffold);
        LinearLayout root = scaffold.content();

        addHeader(root);

        TextView greeting = DreamUi.text(this, "\u00a1Hola, Charlotte! \u2728", 22, DreamColors.INK, Typeface.BOLD);
        greeting.setGravity(Gravity.CENTER);
        add(root, greeting, 8, 2);

        TextView subtitle = DreamUi.text(this, "\u00bfQu\u00e9 mensaje m\u00e1gico\nte espera hoy?", 14, DreamColors.DEEP, Typeface.BOLD);
        subtitle.setGravity(Gravity.CENTER);
        add(root, subtitle, 0, 2);

        ImageView hero = new ImageView(this);
        hero.setImageResource(R.drawable.home_unicorn_hero);
        hero.setAdjustViewBounds(true);
        hero.setScaleType(ImageView.ScaleType.FIT_CENTER);
        addHero(root, hero);

        addDailyFeature(root);

        GridLayout menu = new GridLayout(this);
        menu.setColumnCount(2);
        add(root, menu, 0, 0);

        addMenu(menu, "Si o No", "Obt\u00e9n una respuesta\nclara y sencilla.", R.drawable.icon_si_o_no, DreamColors.CLOUD, v -> TarotNavigator.openYesNo(this));
        addMenu(menu, "Tarot de Parejas", "Explora la conexi\u00f3n\nen su v\u00ednculo.", R.drawable.icon_tarot_parejas, DreamColors.ROSE_SOFT, v -> TarotNavigator.openCouples(this));
        addMenu(menu, "Colecci\u00f3n", "Re\u00fane y descubre\ntodas las cartas.", R.drawable.icon_coleccion, DreamColors.SPROUT_SOFT, v -> TarotNavigator.openCollection(this));
        addMenu(menu, "Mi Mascota", "Cuida a tu gu\u00eda\nm\u00e1gica.", R.drawable.home_unicorn_hero, 0xffffeff7, v -> TarotNavigator.openSettings(this));

        setContentView(scaffold);
    }

    private void addHomeWash(TarotScaffold scaffold) {
        View wash = new View(this);
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{0xdffff7f2, 0xcffffbf2, 0xdffff1f6});
        wash.setBackground(bg);
        scaffold.addView(wash, 1, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private void addHeader(LinearLayout root) {
        LinearLayout header = new LinearLayout(this);
        header.setOrientation(LinearLayout.HORIZONTAL);
        header.setGravity(Gravity.CENTER_VERTICAL);
        header.setPadding(0, 0, 0, DreamUi.dp(this, 3));

        TextView menu = DreamUi.text(this, "\u2630", 24, DreamColors.LILAC_DARK, Typeface.NORMAL);
        menu.setGravity(Gravity.CENTER_VERTICAL);
        header.addView(menu, new LinearLayout.LayoutParams(DreamUi.dp(this, 42), DreamUi.dp(this, 42)));

        View spacer = new View(this);
        header.addView(spacer, new LinearLayout.LayoutParams(0, 1, 1f));

        TextView bell = DreamUi.text(this, "\ud83d\udd14", 19, DreamColors.LILAC_DARK, Typeface.NORMAL);
        bell.setGravity(Gravity.CENTER);
        header.addView(bell, new LinearLayout.LayoutParams(DreamUi.dp(this, 42), DreamUi.dp(this, 42)));
        add(root, header, 0, 0);
    }

    private void addDailyFeature(LinearLayout root) {
        GlassPanel panel = new GlassPanel(this);
        panel.setOrientation(LinearLayout.HORIZONTAL);
        panel.setGravity(Gravity.CENTER_VERTICAL);
        panel.setClickable(true);
        panel.setOnClickListener(v -> TarotNavigator.openDailyCard(this));
        panel.setPadding(DreamUi.dp(this, 9), DreamUi.dp(this, 7), DreamUi.dp(this, 9), DreamUi.dp(this, 7));
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{0xfffff7e9, 0xfffffbf5, 0xfffff2e0});
        bg.setCornerRadius(DreamUi.dp(this, 16));
        bg.setStroke(DreamUi.dp(this, 1), 0xc8e5b35a);
        panel.setBackground(bg);

        ImageView star = cardImage(R.drawable.home_star_icon);
        panel.addView(star, new LinearLayout.LayoutParams(DreamUi.dp(this, 60), DreamUi.dp(this, 60)));

        LinearLayout copy = new LinearLayout(this);
        copy.setOrientation(LinearLayout.VERTICAL);
        copy.setGravity(Gravity.CENTER);
        TextView title = DreamUi.text(this, "Carta del D\u00eda", 17, DreamColors.INK, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        copy.addView(title);
        TextView body = DreamUi.text(this, "Descubre el mensaje que el universo tiene para ti.", 12, DreamColors.DEEP, Typeface.NORMAL);
        body.setGravity(Gravity.CENTER);
        copy.addView(body);
        panel.addView(copy, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

        ImageView flame = cardImage(R.drawable.icon_carta_dia);
        panel.addView(flame, new LinearLayout.LayoutParams(DreamUi.dp(this, 62), DreamUi.dp(this, 62)));
        add(root, panel, 0, 8);
    }

    private void addMenu(GridLayout grid, String title, String subtitle, int iconRes, int tint, View.OnClickListener click) {
        GlassPanel panel = new GlassPanel(this);
        panel.setOrientation(LinearLayout.HORIZONTAL);
        panel.setGravity(Gravity.CENTER_VERTICAL);
        panel.setClickable(true);
        panel.setOnClickListener(click);
        panel.setMinimumHeight(DreamUi.dp(this, 84));
        panel.setPadding(DreamUi.dp(this, 11), DreamUi.dp(this, 8), DreamUi.dp(this, 8), DreamUi.dp(this, 8));
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{DreamUi.blend(tint, 0xffffffff, .24f), 0xfffffbf5});
        bg.setCornerRadius(DreamUi.dp(this, 13));
        bg.setStroke(DreamUi.dp(this, 1), 0x9fd9ad63);
        panel.setBackground(bg);

        LinearLayout copy = new LinearLayout(this);
        copy.setOrientation(LinearLayout.VERTICAL);
        copy.setGravity(Gravity.CENTER_VERTICAL);

        TextView titleView = DreamUi.text(this, title, 14, DreamColors.INK, Typeface.BOLD);
        titleView.setGravity(Gravity.START);
        copy.addView(titleView);
        TextView sub = DreamUi.text(this, subtitle, 9, DreamColors.DEEP, Typeface.NORMAL);
        sub.setGravity(Gravity.START);
        copy.addView(sub);
        panel.addView(copy, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

        ImageView icon = cardImage(iconRes);
        panel.addView(icon, new LinearLayout.LayoutParams(DreamUi.dp(this, 55), DreamUi.dp(this, 55)));

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = DreamUi.dp(this, 88);
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.setMargins(DreamUi.dp(this, 4), DreamUi.dp(this, 4), DreamUi.dp(this, 4), DreamUi.dp(this, 4));
        grid.addView(panel, params);
    }

    private ImageView cardImage(int iconRes) {
        ImageView image = new ImageView(this);
        image.setImageResource(iconRes);
        image.setAdjustViewBounds(true);
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return image;
    }

    private void addHero(LinearLayout parent, ImageView child) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DreamUi.dp(this, 184));
        params.setMargins(0, DreamUi.dp(this, 3), 0, DreamUi.dp(this, 3));
        parent.addView(child, params);
    }

    private void add(LinearLayout parent, View child, int top, int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DreamUi.dp(this, top), 0, DreamUi.dp(this, bottom));
        parent.addView(child, params);
    }
}
