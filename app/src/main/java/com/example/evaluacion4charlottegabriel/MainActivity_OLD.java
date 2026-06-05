package com.example.evaluacion4charlottegabriel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.evaluacion4charlottegabriel.Dao.Carta;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void iniciarActividadSiyno(View view) {
        Bundle bundle = new Bundle();

        // Generar un numero y rotacion y agregarlos al bundle
        int numero = (int) (Math.random() * 78);
        int rotacion = ((int) (Math.random() * 2)) * 180;
        bundle.putInt("numero", numero);
        bundle.putInt("rotacion", rotacion);

        Intent i = new Intent(this, SiYNo.class);

        DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
        referencia.child(String.valueOf(numero)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Leer informacion de la carta y agregarla al bundle
                Carta carta = snapshot.getValue(Carta.class);
                bundle.putSerializable("carta", carta);

                // Agregar los extras al intent e iniciar la actividad
                i.putExtras(bundle);
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    public void inciarActividadCartaDelDia(View view) {
        Bundle bundle = new Bundle();

        // Generar un numero y rotacion y agregarlos al bundle
        LocalDateTime fecha = LocalDateTime.now();
        int mes = fecha.getMonthValue();
        int dia = fecha.getDayOfMonth();
        int año = fecha.getYear();
        int numero = dia + mes + año;
        numero = numero % 78;
        int rotacion = (numero % 2) * 180;
        bundle.putInt("numero", numero);
        bundle.putInt("rotacion", rotacion);

        Intent i = new Intent(this, CartaDelDia.class);

        DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
        referencia.child(String.valueOf(numero)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Leer informacion de la carta y agregarla al bundle
                Carta carta = snapshot.getValue(Carta.class);
                bundle.putSerializable("carta", carta);

                // Agregar los extras al intent e iniciar la actividad
                i.putExtras(bundle);
                startActivity(i);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public void iniciarActividadTarotPareja(View view){
        Intent i = new Intent(this, tarotPareja.class);
        Bundle bundle = new Bundle();

        int numero = (int) (Math.random() * 78);
        int numerotupersona = (int) (Math.random() * 78);

        bundle.putInt("numero" , numero);
        bundle.putInt("numerotupersona" , numerotupersona);


        DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
        referencia.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot cartaActual : snapshot.getChildren()){
                    if (cartaActual.getKey().equals(String.valueOf(numero))){
                        Carta tu = cartaActual.getValue(Carta.class);
                        bundle.putSerializable("tu", tu);
                    }
                    if (cartaActual.getKey().equals(String.valueOf(numerotupersona))){
                        Carta pareja = cartaActual.getValue(Carta.class);
                        bundle.putSerializable("pareja", pareja);
                    }
                }
                i.putExtras(bundle);
                startActivity(i);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}