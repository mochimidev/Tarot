package com.example.evaluacion4charlottegabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.evaluacion4charlottegabriel.Dao.Carta;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Actividad que interpreta preguntas de Sí/No mediante cartas del tarot.
 * Basado en listas predefinidas de cartas "positivas", "negativas" y "neutras".
 */
public class SiYNo extends AppCompatActivity {
    private static final String TAG = "SiYNo";
    
    // Cartas que representan "SÍ"
    private static final Integer[] CARTAS_SI = {
        0, 1, 3, 6, 7, 8, 10, 14, 17, 19,
        20, 21, 22, 23, 24, 25, 27, 29, 30, 32,
        33, 34, 35, 36, 37, 38, 41, 44, 45, 46,
        47, 48, 49, 50, 60, 61, 64, 66, 69, 71,
        72, 73, 74, 75, 76, 77
    };
    
    // Cartas que representan "NO"
    private static final Integer[] CARTAS_NO = {
        13, 15, 16, 18, 26, 28, 31, 40, 43, 52,
        54, 56, 57, 58, 59, 67, 68
    };
    
    private Set<Integer> siSet;
    private Set<Integer> noSet;
    
    private TextView tvTitulo;
    private TextView tvDescripcion;
    private TextView tvResultado;
    private ImageView ivCarta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_si_yno);

        // Inicializar conjuntos para búsqueda rápida
        siSet = new HashSet<>(Arrays.asList(CARTAS_SI));
        noSet = new HashSet<>(Arrays.asList(CARTAS_NO));

        // Vincular elementos de la vista
        tvTitulo = findViewById(R.id.Siynotitulocarta);
        tvDescripcion = findViewById(R.id.Siynodescripcion);
        tvResultado = findViewById(R.id.Siynoresultado);
        ivCarta = findViewById(R.id.Siynoimagencarta);

        // Obtener extras del intent
        Bundle bundle = getIntent().getExtras();
        
        if (bundle != null) {
            int numero = bundle.getInt("numero");
            int rotacion = bundle.getInt("rotacion");
            Carta carta = (Carta) bundle.getSerializable("carta");

            if (carta != null) {
                // Mostrar información de la carta
                mostrarCarta(carta, rotacion);
                // Mostrar resultado de Sí/No
                analizarSiNoQuizas(numero, rotacion);
            } else {
                tvTitulo.setText("Error");
                tvDescripcion.setText("No se pudo cargar la carta.");
            }
        }
    }

    /**
     * Muestra el título y descripción de la carta
     */
    private void mostrarCarta(Carta carta, int rotacion) {
        if (rotacion == 0) {
            tvDescripcion.setText(carta.getDescripcion());
            tvTitulo.setText(carta.getTitulo());
        } else {
            tvDescripcion.setText(carta.getDescripcionInvertida());
            tvTitulo.setText(carta.getTitulo() + " (Invertida)");
        }
    }

    /**
     * Analiza la carta y determina si la respuesta es Sí, No o Talvez
     * Después carga y muestra la imagen de la carta
     */
    private void analizarSiNoQuizas(int numero, int rotacion) {
        // Determinar resultado basado en la carta
        String resultado = obtenerResultado(numero);
        tvResultado.setText(resultado);

        // Mostrar imagen de la carta
        mostrarImagenCarta(numero, rotacion);
    }

    /**
     * Obtiene el resultado (Sí, No o Talvez) basado en la carta seleccionada
     */
    private String obtenerResultado(int numeroCarta) {
        if (siSet.contains(numeroCarta)) {
            return "✓ Tu respuesta es SÍ (ᐢ▾ᐢ)";
        } else if (noSet.contains(numeroCarta)) {
            return "✗ Tu respuesta es NO (´；ω；`)";
        } else {
            return "❓ Tu respuesta es TALVEZ... (´・ω・`)";
        }
    }

    /**
     * Carga y muestra la imagen de la carta con rotación
     */
    private void mostrarImagenCarta(int numero, int rotacion) {
        String nombreCarta = String.format("carta%d", numero);
        int valorImagenCarta = getResources().getIdentifier(
            nombreCarta,
            "drawable",
            getPackageName()
        );
        
        if (valorImagenCarta != 0) {
            ivCarta.setImageResource(valorImagenCarta);
            ivCarta.setRotation(rotacion);
        } else {
            ivCarta.setImageResource(R.drawable.ic_launcher_background);
        }
    }
}