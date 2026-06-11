package com.example.evaluacion4charlottegabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.evaluacion4charlottegabriel.Dao.Carta;
import com.example.evaluacion4charlottegabriel.ui.CardMeta;
import com.example.evaluacion4charlottegabriel.ui.DreamBottomNav;
import com.example.evaluacion4charlottegabriel.ui.DreamColors;
import com.example.evaluacion4charlottegabriel.ui.DreamDividerView;
import com.example.evaluacion4charlottegabriel.ui.DreamTopBar;
import com.example.evaluacion4charlottegabriel.ui.DreamUi;
import com.example.evaluacion4charlottegabriel.ui.GlassPanel;
import com.example.evaluacion4charlottegabriel.ui.KawaiiSymbolView;
import com.example.evaluacion4charlottegabriel.ui.TarotCardWidget;
import com.example.evaluacion4charlottegabriel.ui.TarotScaffold;

public class tarotPareja extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        int numeroTu = 36;
        int numeroPareja = 50;
        Carta tu = TarotNavigator.fallbackCard(numeroTu);
        Carta pareja = TarotNavigator.fallbackCard(numeroPareja);
        if (bundle != null) {
            numeroTu = bundle.getInt("numero");
            numeroPareja = bundle.getInt("numerotupersona");
            tu = (Carta) bundle.getSerializable("tu");
            pareja = (Carta) bundle.getSerializable("pareja");
            if (tu == null) tu = TarotNavigator.fallbackCard(numeroTu);
            if (pareja == null) pareja = TarotNavigator.fallbackCard(numeroPareja);
        }
        build(numeroTu, numeroPareja, tu, pareja);
    }

    private void build(int numeroTu, int numeroPareja, Carta tu, Carta pareja) {
        TarotScaffold scaffold = new TarotScaffold(this);
        scaffold.setBottomNav(DreamBottomNav.COUPLES);
        LinearLayout root = scaffold.content();
        root.addView(new DreamTopBar(this, "Tarot de Parejas", true, KawaiiSymbolView.HEART));

        TextView sub = DreamUi.text(this, "Tu energia y otra energia se encuentran en una conexion suave.", 15, DreamColors.DEEP, Typeface.NORMAL);
        sub.setGravity(Gravity.CENTER);
        add(root, sub, 0, 14);

        FrameLayout connection = new FrameLayout(this);
        connection.addView(new ConnectionView(this), new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        GridLayout grid = new GridLayout(this);
        grid.setColumnCount(2);
        connection.addView(grid);

        TarotCardWidget first = new TarotCardWidget(this);
        first.bind(numeroTu, safeTitle(tu, numeroTu), safeLove(tu, numeroTu), 0);
        first.setCompact(true);
        TarotCardWidget second = new TarotCardWidget(this);
        second.bind(numeroPareja, safeTitle(pareja, numeroPareja), safeLove(pareja, numeroPareja), 0);
        second.setCompact(true);
        addGrid(grid, first);
        addGrid(grid, second);
        add(root, connection, 0, 16);

        GlassPanel summary = new GlassPanel(this);
        TextView title = DreamUi.text(this, "Conexion revelada", 23, DreamColors.INK, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        summary.addView(title);
        add(summary, new DreamDividerView(this), 0, 8);
        TextView body = DreamUi.text(this, "Ambas energias se complementan con ternura y comprension. La conexion puede crecer aun mas con comunicacion y paciencia.", 15, DreamColors.DEEP, Typeface.NORMAL);
        body.setGravity(Gravity.CENTER);
        add(summary, body, 8, 0);
        add(root, summary, 0, 0);
        setContentView(scaffold);
    }

    private String safeTitle(Carta carta, int id) {
        String title = carta == null ? null : carta.getTitulo();
        return title == null || title.trim().isEmpty() ? CardMeta.cardTitle(id) : title;
    }

    private String safeLove(Carta carta, int id) {
        String text = carta == null ? null : carta.getDescripcionAmorosa();
        return text == null || text.trim().isEmpty() ? CardMeta.familyMeaning(id) : text;
    }

    private void addGrid(GridLayout grid, View child) {
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        params.setMargins(DreamUi.dp(this, 4), 0, DreamUi.dp(this, 4), 0);
        grid.addView(child, params);
    }

    private void add(LinearLayout parent, View child, int top, int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DreamUi.dp(this, top), 0, DreamUi.dp(this, bottom));
        parent.addView(child, params);
    }

    private static class ConnectionView extends View {
        private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        ConnectionView(android.content.Context context) {
            super(context);
            DreamUi.softLayer(this);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            float w = getWidth();
            float h = getHeight();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(DreamColors.GOLD);
            paint.setShadowLayer(DreamUi.dp(getContext(), 12), 0, 0, DreamColors.GOLD);
            for (int i = 0; i < 13; i++) {
                float x = w * (.43f + i * .012f);
                float y = h * (.22f + (i % 2) * .04f);
                canvas.drawCircle(x, y, DreamUi.dp(getContext(), 2 + i % 3), paint);
            }
            drawHeart(canvas, w * .5f, h * .25f, DreamUi.dp(getContext(), 20));
            paint.clearShadowLayer();
        }

        private void drawHeart(Canvas canvas, float cx, float cy, float size) {
            Path path = new Path();
            path.moveTo(cx, cy + size * .65f);
            path.cubicTo(cx - size * 1.15f, cy, cx - size * .65f, cy - size * .85f, cx, cy - size * .32f);
            path.cubicTo(cx + size * .65f, cy - size * .85f, cx + size * 1.15f, cy, cx, cy + size * .65f);
            paint.setColor(DreamColors.ROSE);
            canvas.drawPath(path, paint);
            paint.setColor(DreamColors.GOLD_SOFT);
            canvas.drawCircle(cx, cy, size * .20f, paint);
        }
    }
}
