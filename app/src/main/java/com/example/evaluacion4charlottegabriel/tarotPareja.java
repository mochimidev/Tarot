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
import com.example.evaluacion4charlottegabriel.ui.DreamColors;
import com.example.evaluacion4charlottegabriel.ui.DreamUi;
import com.example.evaluacion4charlottegabriel.ui.GlassPanel;
import com.example.evaluacion4charlottegabriel.ui.TarotCardWidget;
import com.example.evaluacion4charlottegabriel.ui.TarotScaffold;

public class tarotPareja extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        build(bundle.getInt("numero"), bundle.getInt("numerotupersona"),
                (Carta) bundle.getSerializable("tu"),
                (Carta) bundle.getSerializable("pareja"));
    }

    private void build(int numeroTu, int numeroPareja, Carta tu, Carta pareja) {
        TarotScaffold scaffold = new TarotScaffold(this);
        LinearLayout root = scaffold.content();

        TextView header = DreamUi.text(this, "Tarot de Parejas", 29, DreamColors.INK, Typeface.BOLD);
        header.setGravity(Gravity.CENTER);
        root.addView(header);
        TextView sub = DreamUi.text(this, "Tu energía y otra energía se encuentran en una conexión suave.", 15, DreamColors.DEEP, Typeface.NORMAL);
        sub.setGravity(Gravity.CENTER);
        add(root, sub, 4, 18);

        FrameLayout connection = new FrameLayout(this);
        connection.addView(new ConnectionView(this), new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        GridLayout grid = new GridLayout(this);
        grid.setColumnCount(2);
        connection.addView(grid);

        TarotCardWidget first = new TarotCardWidget(this);
        first.bind(numeroTu, tu == null ? "Tu carta" : tu.getTitulo(),
                tu == null ? "" : tu.getDescripcionAmorosa(), 0);
        TarotCardWidget second = new TarotCardWidget(this);
        second.bind(numeroPareja, pareja == null ? "Carta pareja" : pareja.getTitulo(),
                pareja == null ? "" : pareja.getDescripcionAmorosa(), 0);
        addGrid(grid, first);
        addGrid(grid, second);
        add(root, connection, 0, 18);

        GlassPanel summary = new GlassPanel(this);
        TextView title = DreamUi.text(this, "Conexión revelada", 23, DreamColors.INK, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        summary.addView(title);
        TextView body = DreamUi.text(this, "Ambas energías se complementan con ternura y comprensión. La conexión puede crecer aún más con comunicación y paciencia.", 15, DreamColors.DEEP, Typeface.NORMAL);
        body.setGravity(Gravity.CENTER);
        add(summary, body, 10, 0);
        add(root, summary, 0, 0);
        setContentView(scaffold);
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
            paint.setColor(DreamColors.GOLD);
            paint.setStrokeWidth(DreamUi.dp(getContext(), 3));
            paint.setShadowLayer(DreamUi.dp(getContext(), 12), 0, 0, DreamColors.GOLD);
            canvas.drawLine(w * .42f, h * .22f, w * .58f, h * .22f, paint);
            paint.setStyle(Paint.Style.FILL);
            drawHeart(canvas, w * .5f, h * .26f, DreamUi.dp(getContext(), 17));
            paint.setColor(DreamColors.GOLD);
            paint.setShadowLayer(DreamUi.dp(getContext(), 12), 0, 0, DreamColors.GOLD);
            for (int i = 0; i < 9; i++) {
                float x = w * (.43f + i * .018f);
                float y = h * (.18f + (i % 2) * .07f);
                canvas.drawCircle(x, y, DreamUi.dp(getContext(), i % 3 + 2), paint);
            }
            paint.setShadowLayer(0, 0, 0, 0);
        }

        private void drawHeart(Canvas canvas, float cx, float cy, float size) {
            Path path = new Path();
            path.moveTo(cx, cy + size * .65f);
            path.cubicTo(cx - size * 1.15f, cy, cx - size * .65f, cy - size * .85f, cx, cy - size * .32f);
            path.cubicTo(cx + size * .65f, cy - size * .85f, cx + size * 1.15f, cy, cx, cy + size * .65f);
            paint.setColor(DreamColors.ROSE);
            paint.setShadowLayer(DreamUi.dp(getContext(), 10), 0, 0, DreamColors.ROSE);
            canvas.drawPath(path, paint);
            paint.setColor(DreamColors.GOLD_SOFT);
            canvas.drawCircle(cx, cy, size * .20f, paint);
        }
    }
}
