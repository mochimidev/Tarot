package com.example.evaluacion4charlottegabriel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.evaluacion4charlottegabriel.Dao.Carta;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;

/**
 * Pantalla principal de la aplicación Tarot.
 * Proporciona acceso a tres módulos principales:
 * 1. Carta del Día - Predicción diaria
 * 2. Sí y No - Respuestas a preguntas específicas
 * 3. Tarot de Parejas - Lectura de compatibilidad
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Inicializar referencia a Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    /**
     * Inicia la actividad de "Sí y No"
     * Genera un número aleatorio (0-77) para seleccionar una carta
     */
    public void iniciarActividadSiyno(View view) {
        // Generar número aleatorio y rotación
        int numero = (int) (Math.random() * 78);
        int rotacion = generarRotacionAleatoria();

        // Crear intent hacia SiYNo
        Intent intent = new Intent(this, SiYNo.class);

        // Obtener carta de Firebase
        databaseReference.child(String.valueOf(numero))
            .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Carta carta = snapshot.getValue(Carta.class);
                    
                    if (carta != null) {
                        // Crear y pasar bundle con la información
                        Bundle bundle = crearBundleSiyno(numero, rotacion, carta);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        mostrarError("No se pudo cargar la carta");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    mostrarError("Error de conexión: " + error.getMessage());
                }
            });
    }

    /**
     * Inicia la actividad de "Carta del Día"
     * Calcula la carta basada en la fecha actual: (día + mes + año) % 78
     */
    public void inciarActividadCartaDelDia(View view) {
        // Calcular número de carta basado en fecha actual
        int numero = calcularCartaDelDia();
        int rotacion = (numero % 2) * 180; // Alternación de rotación
        
        // Crear intent hacia CartaDelDia
        Intent intent = new Intent(this, CartaDelDia.class);

        // Obtener carta de Firebase
        databaseReference.child(String.valueOf(numero))
            .addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Carta carta = snapshot.getValue(Carta.class);
                    
                    if (carta != null) {
                        // Crear y pasar bundle con la información
                        Bundle bundle = crearBundleCartaDelDia(numero, rotacion, carta);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        mostrarError("No se pudo cargar la carta del día");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    mostrarError("Error de conexión: " + error.getMessage());
                }
            });
    }

    /**
     * Inicia la actividad de "Tarot de Parejas"
     * Genera dos números aleatorios para seleccionar dos cartas
     */
    public void iniciarActividadTarotPareja(View view) {
        // Generar dos números aleatorios
        int numeroPrimera = (int) (Math.random() * 78);
        int numeroSegunda = (int) (Math.random() * 78);
        
        // Crear intent hacia tarotPareja
        Intent intent = new Intent(this, tarotPareja.class);
        
        // Obtener ambas cartas de Firebase
        obtenerCartasPareja(intent, numeroPrimera, numeroSegunda);
    }

    /**
     * Obtiene las dos cartas necesarias para la lectura de parejas
     */
    private void obtenerCartasPareja(Intent intent, int numeroPrimera, int numeroSegunda) {
        final Carta[] cartas = new Carta[2];
        final int[] cartasObtenidas = {0};

        // Listener para la primera carta
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Carta carta = snapshot.getValue(Carta.class);
                cartas[cartasObtenidas[0]] = carta;
                cartasObtenidas[0]++;

                // Cuando tengamos las dos cartas, iniciar la actividad
                if (cartasObtenidas[0] == 2 && cartas[0] != null && cartas[1] != null) {
                    Bundle bundle = crearBundleTarotPareja(numeroPrimera, numeroSegunda, 
                                                           cartas[0], cartas[1]);
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

        // Obtener ambas cartas
        databaseReference.child(String.valueOf(numeroPrimera))
            .addListenerForSingleValueEvent(listener);
        databaseReference.child(String.valueOf(numeroSegunda))
            .addListenerForSingleValueEvent(listener);
    }

    /**
     * Calcula el número de carta del día basado en la fecha actual
     * Fórmula: (día + mes + año) % 78
     */
    private int calcularCartaDelDia() {
        LocalDateTime fecha = LocalDateTime.now();
        int mes = fecha.getMonthValue();
        int dia = fecha.getDayOfMonth();
        int año = fecha.getYear();
        
        int numero = (dia + mes + año) % 78;
        return numero;
    }

    /**
     * Genera un valor de rotación aleatorio (0 o 180 grados)
     */
    private int generarRotacionAleatoria() {
        return ((int) (Math.random() * 2)) * 180;
    }

    /**
     * Crea el bundle para la actividad SiYNo
     */
    private Bundle crearBundleSiyno(int numero, int rotacion, Carta carta) {
        Bundle bundle = new Bundle();
        bundle.putInt("numero", numero);
        bundle.putInt("rotacion", rotacion);
        bundle.putSerializable("carta", carta);
        return bundle;
    }

    /**
     * Crea el bundle para la actividad CartaDelDia
     */
    private Bundle crearBundleCartaDelDia(int numero, int rotacion, Carta carta) {
        Bundle bundle = new Bundle();
        bundle.putInt("numero", numero);
        bundle.putInt("rotacion", rotacion);
        bundle.putSerializable("carta", carta);
        return bundle;
    }

    /**
     * Crea el bundle para la actividad tarotPareja
     */
    private Bundle crearBundleTarotPareja(int numero1, int numero2, 
                                          Carta carta1, Carta carta2) {
        Bundle bundle = new Bundle();
        bundle.putInt("numero", numero1);
        bundle.putInt("numerotupersona", numero2);
        bundle.putSerializable("tu", carta1);
        bundle.putSerializable("pareja", carta2);
        return bundle;
    }

    /**
     * Muestra un mensaje de error al usuario
     */
    private void mostrarError(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
