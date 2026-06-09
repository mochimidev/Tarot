package com.example.evaluacion4charlottegabriel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evaluacion4charlottegabriel.Dao.Carta;
import com.example.evaluacion4charlottegabriel.ui.DreamButton;
import com.example.evaluacion4charlottegabriel.ui.DreamColors;
import com.example.evaluacion4charlottegabriel.ui.DreamUi;
import com.example.evaluacion4charlottegabriel.ui.GlassPanel;
import com.example.evaluacion4charlottegabriel.ui.SpiritPetWidget;
import com.example.evaluacion4charlottegabriel.ui.TarotScaffold;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        buildHome();
    }

    private void buildHome() {
        TarotScaffold scaffold = new TarotScaffold(this);
        LinearLayout root = scaffold.content();

        GlassPanel hero = new GlassPanel(this);
        hero.setGravity(Gravity.CENTER_HORIZONTAL);
        SpiritPetWidget pet = new SpiritPetWidget(this);
        pet.setLevel(getSharedPreferences("collection", MODE_PRIVATE).getAll().size() / 4 + 1);
        hero.addView(pet, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DreamUi.dp(this, 210)));

        TextView title = DreamUi.text(this, "Dream Sprouts Tarot", 31, DreamColors.INK, android.graphics.Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        hero.addView(title);
        TextView greeting = DreamUi.text(this, "Hola, exploradora de suenos. El Loco unicornio ya preparo una lectura brillante para ti.", 15, DreamColors.DEEP, android.graphics.Typeface.NORMAL);
        greeting.setGravity(Gravity.CENTER);
        hero.addView(greeting);
        add(root, hero, 0, 0, 18);

        GridLayout menu = new GridLayout(this);
        menu.setColumnCount(2);
        add(root, menu, 0, 0, 0);

        addMenu(menu, "Carta del Dia", "Descubre tu guia de hoy", "Dorada", v -> inciarActividadCartaDelDia(v));
        addMenu(menu, "Si o No", "Una respuesta suave y clara", "Celeste", v -> iniciarActividadSiyno(v));
        addMenu(menu, "Tarot de Parejas", "Dos cartas, una conexion", "Rosa", v -> iniciarActividadTarotPareja(v));
        addMenu(menu, "Coleccion", "Album, rarezas y progreso", "Verde", v -> startActivity(new Intent(this, CollectionActivity.class)));
        addMenu(menu, "Mi Mascota", "Cuida tu unicornio guia", "Lila", v -> startActivity(new Intent(this, SpiritPetActivity.class)));
        addMenu(menu, "Ajustes", "Sonido y magia visual", "Nube", v -> startActivity(new Intent(this, SettingsActivity.class)));

        setContentView(scaffold);
    }

    private void addMenu(GridLayout grid, String title, String subtitle, String rarity, View.OnClickListener click) {
        GlassPanel panel = new GlassPanel(this);
        panel.setClickable(true);
        panel.setOnClickListener(click);
        panel.setMinimumHeight(DreamUi.dp(this, 156));
        TextView rarityView = DreamUi.text(this, rarity, 12, DreamColors.GOLD, android.graphics.Typeface.BOLD);
        rarityView.setGravity(Gravity.CENTER);
        panel.addView(rarityView);
        TextView titleView = DreamUi.text(this, title, 19, DreamColors.INK, android.graphics.Typeface.BOLD);
        titleView.setGravity(Gravity.CENTER);
        panel.addView(titleView);
        TextView sub = DreamUi.text(this, subtitle, 13, DreamColors.DEEP, android.graphics.Typeface.NORMAL);
        sub.setGravity(Gravity.CENTER);
        panel.addView(sub);
        DreamButton button = new DreamButton(this, "Abrir");
        button.setTextSize(13);
        button.setOnClickListener(click);
        add(panel, button, 0, 12, 0);

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
