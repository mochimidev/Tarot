package com.example.evaluacion4charlottegabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.evaluacion4charlottegabriel.Dao.Carta;

/**
 * Actividad que muestra la carta del tarot del día.
 * La carta se selecciona basándose en el cálculo del día actual:
 * (día + mes + año) % 78
 * 
 * Esto garantiza que todos los usuarios vean la misma carta en el mismo día.
 */
public class CartaDelDia extends AppCompatActivity {
    private static final String TAG = "CartaDelDia";
    
    private ImageView ivImagen;
    private TextView tvTitulo;
    private TextView tvDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta_del_dia);

        // Vincular los elementos de la vista
        tvTitulo = findViewById(R.id.CartaDelDiaTitulo);
        tvDescripcion = findViewById(R.id.CartaDelDiaDescripcion);
        ivImagen = findViewById(R.id.CartaDelDiaImagen);

        // Obtener los extras del intent
        Bundle bundle = getIntent().getExtras();
        
        if (bundle != null) {
            int numero = bundle.getInt("numero");
            int rotacion = bundle.getInt("rotacion");
            Carta carta = (Carta) bundle.getSerializable("carta");

            if (carta != null) {
                // Mostrar la información de la carta
                mostrarCarta(carta, rotacion);
                // Mostrar imagen
                mostrarImagenCarta(numero, rotacion);
            } else {
                tvTitulo.setText("Error");
                tvDescripcion.setText("No se pudo cargar la información de la carta.");
            }
        }
    }

    /**
     * Muestra el título y descripción de la carta según su rotación
     */
    private void mostrarCarta(Carta carta, int rotacion) {
        if (rotacion == 0) {
            // Carta derecha
            tvDescripcion.setText(carta.getDescripcion());
            tvTitulo.setText(carta.getTitulo());
        } else {
            // Carta invertida
            tvDescripcion.setText(carta.getDescripcionInvertida());
            tvTitulo.setText(carta.getTitulo() + " (Invertida)");
        }
    }

    /**
     * Carga y muestra la imagen de la carta con rotación si es necesaria
     */
    private void mostrarImagenCarta(int numero, int rotacion) {
        String nombreCarta = String.format("carta%d", numero);
        int valorImagenCarta = getResources().getIdentifier(
            nombreCarta, 
            "drawable", 
            getPackageName()
        );
        
        if (valorImagenCarta != 0) {
            ivImagen.setImageResource(valorImagenCarta);
            ivImagen.setRotation(rotacion);
        } else {
            // Si la imagen no existe, mostrar una imagen por defecto
            ivImagen.setImageResource(R.drawable.ic_launcher_background);
        }
    }
}