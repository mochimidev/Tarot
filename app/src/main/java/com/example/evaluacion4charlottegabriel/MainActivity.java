package com.example.evaluacion4charlottegabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
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
        applyHomeSystemBars();
        scaffold.setQuietHomeBackground(true);
        scaffold.setBottomNav(DreamBottomNav.HOME, true);
        LinearLayout root = scaffold.content();
        root.setPadding(DreamUi.dp(this, 6), DreamUi.dp(this, 34),
                DreamUi.dp(this, 6), DreamUi.dp(this, 12));

        addHeader(root);

        TextView greeting = DreamUi.text(this, "\u00a1Hola, Charlotte! \u2728", 18, DreamColors.INK, Typeface.BOLD);
        greeting.setGravity(Gravity.CENTER);
        greeting.setIncludeFontPadding(false);
        add(root, greeting, 7, 5);

        TextView subtitle = DreamUi.text(this, "\u00bfQu\u00e9 mensaje m\u00e1gico\nte espera hoy?", 12, DreamColors.DEEP, Typeface.BOLD);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setIncludeFontPadding(false);
        subtitle.setLineSpacing(DreamUi.dp(this, 2), 1f);
        add(root, subtitle, 0, 6);

        ImageView hero = new ImageView(this);
        hero.setImageResource(R.drawable.home_unicorn_hero);
        hero.setAdjustViewBounds(true);
        hero.setScaleType(ImageView.ScaleType.FIT_CENTER);
        addHero(root, hero);

        addDailyFeature(root);

        GridLayout menu = new GridLayout(this);
        menu.setColumnCount(2);
        add(root, menu, 0, 0);

        addMenu(menu, "Si o No", "Obt\u00e9n una respuesta\nclara y sencilla", R.drawable.icon_si_o_no, DreamColors.CLOUD, v -> TarotNavigator.openYesNo(this));
        addMenu(menu, "Tarot de Parejas", "Explora la conexi\u00f3n\nen su v\u00ednculo", R.drawable.icon_tarot_parejas, DreamColors.ROSE_SOFT, v -> TarotNavigator.openCouples(this));
        addMenu(menu, "Colecci\u00f3n", "Re\u00fane y descubre\ntodas las cartas", R.drawable.icon_coleccion, DreamColors.SPROUT_SOFT, v -> TarotNavigator.openCollection(this));
        addMenu(menu, "Mi Mascota", "Cuida a tu gu\u00eda\nm\u00e1gica", R.drawable.home_unicorn_hero, 0xffffeff7, v -> TarotNavigator.openPet(this));

        setContentView(scaffold);
    }

    private void applyHomeSystemBars() {
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setNavigationBarColor(Color.BLACK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void addHeader(LinearLayout root) {
        LinearLayout header = new LinearLayout(this);
        header.setOrientation(LinearLayout.HORIZONTAL);
        header.setGravity(Gravity.CENTER_VERTICAL);
        header.setPadding(0, 0, 0, 0);

        TextView menu = DreamUi.text(this, "\u2630", 18, 0xd65f3b98, Typeface.NORMAL);
        menu.setGravity(Gravity.CENTER);
        menu.setIncludeFontPadding(false);
        header.addView(menu, new LinearLayout.LayoutParams(DreamUi.dp(this, 30), DreamUi.dp(this, 30)));

        View spacer = new View(this);
        header.addView(spacer, new LinearLayout.LayoutParams(0, 1, 1f));

        TextView bell = DreamUi.text(this, "\ud83d\udd14", 14, 0xd65f3b98, Typeface.NORMAL);
        bell.setGravity(Gravity.CENTER);
        bell.setIncludeFontPadding(false);
        header.addView(bell, new LinearLayout.LayoutParams(DreamUi.dp(this, 30), DreamUi.dp(this, 30)));
        add(root, header, 0, 0);
    }

    private void addDailyFeature(LinearLayout root) {
        FrameLayout panel = new FrameLayout(this);
        DreamUi.softLayer(panel);
        panel.setElevation(DreamUi.dp(this, 2));
        panel.setClickable(true);
        panel.setOnClickListener(v -> TarotNavigator.openDailyCard(this));
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,
                new int[]{0xfffff7e8, 0xfffffbf8, 0xfffff2e2});
        bg.setCornerRadius(DreamUi.dp(this, 14));
        bg.setStroke(DreamUi.dp(this, 1), 0xaee1a94f);
        panel.setBackground(bg);

        ImageView frame = new ImageView(this);
        frame.setImageResource(R.drawable.panel_frame_01);
        frame.setScaleType(ImageView.ScaleType.FIT_XY);
        frame.setAlpha(.38f);
        panel.addView(frame, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(DreamUi.dp(this, 10), DreamUi.dp(this, 7), DreamUi.dp(this, 10), DreamUi.dp(this, 7));

        ImageView star = cardImage(R.drawable.home_star_icon);
        row.addView(star, new LinearLayout.LayoutParams(DreamUi.dp(this, 55), DreamUi.dp(this, 55)));

        LinearLayout copy = new LinearLayout(this);
        copy.setOrientation(LinearLayout.VERTICAL);
        copy.setGravity(Gravity.CENTER);
        TextView title = DreamUi.text(this, "Carta del D\u00eda", 15, DreamColors.INK, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        title.setIncludeFontPadding(false);
        copy.addView(title);
        TextView body = DreamUi.text(this, "Descubre el mensaje\nque el universo tiene para ti.", 9.5f, DreamColors.DEEP, Typeface.BOLD);
        body.setGravity(Gravity.CENTER);
        body.setIncludeFontPadding(false);
        body.setLineSpacing(DreamUi.dp(this, 1), 1f);
        copy.addView(body);
        LinearLayout.LayoutParams copyParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        copyParams.setMargins(DreamUi.dp(this, 4), 0, DreamUi.dp(this, 4), 0);
        row.addView(copy, copyParams);

        ImageView flame = cardImage(R.drawable.icon_carta_dia);
        row.addView(flame, new LinearLayout.LayoutParams(DreamUi.dp(this, 58), DreamUi.dp(this, 58)));
        panel.addView(row, new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DreamUi.dp(this, 72));
        params.setMargins(0, DreamUi.dp(this, 1), 0, DreamUi.dp(this, 8));
        root.addView(panel, params);
    }

    private void addMenu(GridLayout grid, String title, String subtitle, int iconRes, int tint, View.OnClickListener click) {
        GlassPanel panel = new GlassPanel(this);
        panel.setOrientation(LinearLayout.HORIZONTAL);
        panel.setGravity(Gravity.CENTER_VERTICAL);
        panel.setClickable(true);
        panel.setOnClickListener(click);
        panel.setMinimumHeight(DreamUi.dp(this, 86));
        panel.setPadding(DreamUi.dp(this, 10), DreamUi.dp(this, 8), DreamUi.dp(this, 8), DreamUi.dp(this, 8));
        panel.setElevation(DreamUi.dp(this, 2));
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{DreamUi.blend(tint, 0xffffffff, .34f), 0xfffffbf7});
        bg.setCornerRadius(DreamUi.dp(this, 12));
        bg.setStroke(DreamUi.dp(this, 1), 0x8fd4a65a);
        panel.setBackground(bg);

        LinearLayout copy = new LinearLayout(this);
        copy.setOrientation(LinearLayout.VERTICAL);
        copy.setGravity(Gravity.CENTER_VERTICAL);

        TextView titleView = DreamUi.text(this, title, 12.5f, DreamColors.INK, Typeface.BOLD);
        titleView.setGravity(Gravity.START);
        titleView.setIncludeFontPadding(false);
        copy.addView(titleView);
        TextView sub = DreamUi.text(this, subtitle, 7.8f, DreamColors.DEEP, Typeface.BOLD);
        sub.setGravity(Gravity.START);
        sub.setIncludeFontPadding(false);
        sub.setLineSpacing(DreamUi.dp(this, 1), 1f);
        copy.addView(sub);
        panel.addView(copy, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

        ImageView icon = cardImage(iconRes);
        LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(DreamUi.dp(this, 50), DreamUi.dp(this, 50));
        iconParams.leftMargin = DreamUi.dp(this, 3);
        panel.addView(icon, iconParams);

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = DreamUi.dp(this, 90);
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.setMargins(DreamUi.dp(this, 3), DreamUi.dp(this, 3), DreamUi.dp(this, 3), DreamUi.dp(this, 3));
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
                ViewGroup.LayoutParams.MATCH_PARENT, DreamUi.dp(this, 188));
        params.setMargins(0, DreamUi.dp(this, 42), 0, DreamUi.dp(this, 2));
        parent.addView(child, params);
    }

    private void add(LinearLayout parent, View child, int top, int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DreamUi.dp(this, top), 0, DreamUi.dp(this, bottom));
        parent.addView(child, params);
    }
}
