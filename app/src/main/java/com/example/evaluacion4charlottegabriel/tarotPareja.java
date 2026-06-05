package com.example.evaluacion4charlottegabriel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.evaluacion4charlottegabriel.Dao.Carta;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Actividad que realiza una lectura de tarot para parejas.
 * Obtiene dos cartas aleatorias que representan:
 * - Primera carta: Energía de la primera persona
 * - Segunda carta: Energía de la segunda persona / Pareja
 * 
 * Proporciona interpretación amorosa y análisis de compatibilidad.
 */
public class tarotPareja extends AppCompatActivity {
    private static final String TAG = "tarotPareja";
    
    private TextView tvTituloTu;
    private TextView tvTituloTuPareja;
    private TextView tvDescripcionTu;
    private TextView tvDescripcionTuPareja;
    private ImageView ivCartaTuPareja;
    private ImageView ivCartaTu;
    
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarot_pareja);
        
        // Vincular elementos de la vista
        tvDescripcionTu = findViewById(R.id.tarotParejaTudescripcion);
        tvDescripcionTuPareja = findViewById(R.id.tarotTuparejadesripccion);
        tvTituloTu = findViewById(R.id.tarotParejaTutitulocarta);
        tvTituloTuPareja = findViewById(R.id.tarotTuParejatitulocarta);
        ivCartaTuPareja = findViewById(R.id.tarotTuparejaImagen);
        ivCartaTu = findViewById(R.id.tarotParejaTuImagen);

        // Obtener extras del intent
        bundle = getIntent().getExtras();
        
        if (bundle != null) {
            int numero = bundle.getInt("numero");
            int numeroTuPersona = bundle.getInt("numerotupersona");
            
            // Obtener datos de Firebase
            obtenerCartasYMostrar(numero, numeroTuPersona);
        }
    }

    /**
     * Obtiene las cartas del tarot de Firebase y las muestra
     */
    private void obtenerCartasYMostrar(int numero, int numeroTuPersona) {
        DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
        
        // Obtener primera carta
        referencia.child(String.valueOf(numero))
            .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Carta cartaTu = snapshot.getValue(Carta.class);
                    if (cartaTu != null) {
                        tvTituloTu.setText(cartaTu.getTitulo());
                        tvDescripcionTu.setText(cartaTu.getDescripcionAmorosa());
                        mostrarImagenCarta(numero, ivCartaTu);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    tvTituloTu.setText("Error al cargar");
                }
            });
        
        // Obtener segunda carta
        referencia.child(String.valueOf(numeroTuPersona))
            .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Carta cartaPareja = snapshot.getValue(Carta.class);
                    if (cartaPareja != null) {
                        tvTituloTuPareja.setText(cartaPareja.getTitulo());
                        tvDescripcionTuPareja.setText(cartaPareja.getDescripcionAmorosa());
                        mostrarImagenCarta(numeroTuPersona, ivCartaTuPareja);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    tvTituloTuPareja.setText("Error al cargar");
                }
            });
    }

    /**
     * Carga y muestra la imagen de la carta
     */
    private void mostrarImagenCarta(int numeroCarta, ImageView imageView) {
        String nombreCarta = String.format("carta%d", numeroCarta);
        int valorImagenCarta = getResources().getIdentifier(
            nombreCarta,
            "drawable",
            getPackageName()
        );
        
        if (valorImagenCarta != 0) {
            imageView.setImageResource(valorImagenCarta);
        } else {
            imageView.setImageResource(R.drawable.ic_launcher_background);
        }
    }
}