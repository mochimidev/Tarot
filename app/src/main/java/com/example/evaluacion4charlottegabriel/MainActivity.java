package com.example.evaluacion4charlottegabriel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import android.widget.Toast;

import com.example.evaluacion4charlottegabriel.Dao.Carta;
import com.example.evaluacion4charlottegabriel.Dao.FirebaseTarotDatabase;
import com.example.evaluacion4charlottegabriel.ui.DreamButton;
import com.example.evaluacion4charlottegabriel.ui.DreamColors;
import com.example.evaluacion4charlottegabriel.ui.DreamUi;
import com.example.evaluacion4charlottegabriel.ui.GlassPanel;
import com.example.evaluacion4charlottegabriel.ui.KawaiiSymbolView;
import com.example.evaluacion4charlottegabriel.ui.SpiritPetWidget;
import com.example.evaluacion4charlottegabriel.ui.TarotScaffold;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseTarotDatabase.root();
        buildHome();
    }

    private void buildHome() {
        TarotScaffold scaffold = new TarotScaffold(this);
        LinearLayout root = scaffold.content();

        GlassPanel hero = new GlassPanel(this);
        hero.setGravity(Gravity.CENTER_HORIZONTAL);
        hero.setPadding(DreamUi.dp(this, 18), DreamUi.dp(this, 18), DreamUi.dp(this, 18), DreamUi.dp(this, 22));

        ImageView logo = new ImageView(this);
        logo.setImageResource(R.drawable.tarot_kawaii_dreams_logo);
        logo.setAdjustViewBounds(true);
        logo.setScaleType(ImageView.ScaleType.FIT_CENTER);
        hero.addView(logo, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DreamUi.dp(this, 230)));

        TextView greeting = DreamUi.text(this, "Hola, Charlotte", 28, DreamColors.INK, Typeface.BOLD);
        greeting.setGravity(Gravity.CENTER);
        hero.addView(greeting);

        TextView subtitle = DreamUi.text(this, "Tu album magico desperto con cartas, nubes y pequenos deseos brillando para ti.", 15, DreamColors.DEEP, Typeface.NORMAL);
        subtitle.setGravity(Gravity.CENTER);
        add(hero, subtitle, 4, 12);

        SpiritPetWidget pet = new SpiritPetWidget(this);
        pet.setLevel(getSharedPreferences("collection", MODE_PRIVATE).getAll().size() / 4 + 1);
        hero.addView(pet, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DreamUi.dp(this, 150)));

        DreamButton daily = new DreamButton(this, "Revelar mi carta del dia");
        daily.setOnClickListener(v -> inciarActividadCartaDelDia(v));
        add(hero, daily, 10, 0);
        add(root, hero, 0, 0, 18);

        TextView section = DreamUi.label(this, "Lecturas de cuento", DreamColors.LILAC_DARK);
        section.setGravity(Gravity.CENTER);
        add(root, section, 0, 8);

        GridLayout menu = new GridLayout(this);
        menu.setColumnCount(2);
        add(root, menu, 0, 0, 0);

        addMenu(menu, "Carta del Dia", "Un mensaje ilustrado del universo.", KawaiiSymbolView.STAR, DreamColors.GOLD_SOFT, v -> inciarActividadCartaDelDia(v));
        addMenu(menu, "Si o No", "Respuesta clara con magia suave.", KawaiiSymbolView.DROP, DreamColors.CLOUD, v -> iniciarActividadSiyno(v));
        addMenu(menu, "Tarot de Parejas", "Dos energias conectadas por luz.", KawaiiSymbolView.FLAME, DreamColors.ROSE_SOFT, v -> iniciarActividadTarotPareja(v));
        addMenu(menu, "Coleccion", "Album premium por familias.", KawaiiSymbolView.SPROUT, DreamColors.SPROUT_SOFT, v -> startActivity(new Intent(this, CollectionActivity.class)));
        addMenu(menu, "Mi Mascota", "Tu guia unicornio y su nivel.", KawaiiSymbolView.UNICORN, 0xffffeef8, v -> startActivity(new Intent(this, SpiritPetActivity.class)));
        addMenu(menu, "Ajustes", "Sonido, brillo y preferencias.", KawaiiSymbolView.STAR, 0xffedf5ff, v -> startActivity(new Intent(this, SettingsActivity.class)));

        setContentView(scaffold);
    }

    private void addMenu(GridLayout grid, String title, String subtitle, int symbol, int tint, View.OnClickListener click) {
        GlassPanel panel = new GlassPanel(this);
        panel.setClickable(true);
        panel.setOnClickListener(click);
        panel.setMinimumHeight(DreamUi.dp(this, 176));
        panel.setPadding(DreamUi.dp(this, 12), DreamUi.dp(this, 12), DreamUi.dp(this, 12), DreamUi.dp(this, 14));
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[]{DreamUi.blend(tint, 0xffffffff, .25f), 0xfafffbf4});
        bg.setCornerRadius(DreamUi.dp(this, 28));
        bg.setStroke(DreamUi.dp(this, 1), DreamColors.GLASS_STROKE);
        panel.setBackground(bg);

        KawaiiSymbolView icon = new KawaiiSymbolView(this, symbol);
        panel.addView(icon, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, DreamUi.dp(this, 74)));

        TextView titleView = DreamUi.text(this, title, 17, DreamColors.INK, Typeface.BOLD);
        titleView.setGravity(Gravity.CENTER);
        panel.addView(titleView);
        TextView sub = DreamUi.text(this, subtitle, 12, DreamColors.DEEP, Typeface.NORMAL);
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
        params.setMargins(left, top, left, DreamUi.dp(this, bottom));
        parent.addView(child, params);
    }

    private void add(LinearLayout parent, View child, int top, int bottom) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, DreamUi.dp(this, top), 0, DreamUi.dp(this, bottom));
        parent.addView(child, params);
    }

    public void iniciarActividadSiyno(View view) {
        int numero = (int) (Math.random() * 78);
        int rotacion = generarRotacionAleatoria();
        Intent intent = new Intent(this, SiYNo.class);
        databaseReference.child(String.valueOf(numero)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Carta carta = snapshot.getValue(Carta.class);
                if (carta != null) {
                    Bundle bundle = crearBundleSiyno(numero, rotacion, carta);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    mostrarError("No se pudo cargar la carta");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                mostrarError("Error de conexion: " + error.getMessage());
            }
        });
    }

    public void inciarActividadCartaDelDia(View view) {
        int numero = calcularCartaDelDia();
        int rotacion = (numero % 2) * 180;
        Intent intent = new Intent(this, CartaDelDia.class);
        databaseReference.child(String.valueOf(numero)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Carta carta = snapshot.getValue(Carta.class);
                if (carta != null) {
                    Bundle bundle = crearBundleCartaDelDia(numero, rotacion, carta);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    mostrarError("No se pudo cargar la carta del dia");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                mostrarError("Error de conexion: " + error.getMessage());
            }
        });
    }

    public void iniciarActividadTarotPareja(View view) {
        int numeroPrimera = (int) (Math.random() * 78);
        int numeroSegunda = (int) (Math.random() * 78);
        Intent intent = new Intent(this, tarotPareja.class);
        obtenerCartasPareja(intent, numeroPrimera, numeroSegunda);
    }

    private void obtenerCartasPareja(Intent intent, int numeroPrimera, int numeroSegunda) {
        final Carta[] cartas = new Carta[2];
        final int[] cartasObtenidas = {0};
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Carta carta = snapshot.getValue(Carta.class);
                cartas[cartasObtenidas[0]] = carta;
                cartasObtenidas[0]++;
                if (cartasObtenidas[0] == 2 && cartas[0] != null && cartas[1] != null) {
                    Bundle bundle = crearBundleTarotPareja(numeroPrimera, numeroSegunda, cartas[0], cartas[1]);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (cartasObtenidas[0] == 2) {
                    mostrarError("No se pudieron cargar ambas cartas");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                mostrarError("Error al obtener cartas: " + error.getMessage());
            }
        };
        databaseReference.child(String.valueOf(numeroPrimera)).addListenerForSingleValueEvent(listener);
        databaseReference.child(String.valueOf(numeroSegunda)).addListenerForSingleValueEvent(listener);
    }

    private int calcularCartaDelDia() {
        LocalDateTime fecha = LocalDateTime.now();
        return (fecha.getDayOfMonth() + fecha.getMonthValue() + fecha.getYear()) % 78;
    }

    private int generarRotacionAleatoria() {
        return ((int) (Math.random() * 2)) * 180;
    }

    private Bundle crearBundleSiyno(int numero, int rotacion, Carta carta) {
        Bundle bundle = new Bundle();
        bundle.putInt("numero", numero);
        bundle.putInt("rotacion", rotacion);
        bundle.putSerializable("carta", carta);
        return bundle;
    }

    private Bundle crearBundleCartaDelDia(int numero, int rotacion, Carta carta) {
        return crearBundleSiyno(numero, rotacion, carta);
    }

    private Bundle crearBundleTarotPareja(int numero1, int numero2, Carta carta1, Carta carta2) {
        Bundle bundle = new Bundle();
        bundle.putInt("numero", numero1);
        bundle.putInt("numerotupersona", numero2);
        bundle.putSerializable("tu", carta1);
        bundle.putSerializable("pareja", carta2);
        return bundle;
    }

    private void mostrarError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
