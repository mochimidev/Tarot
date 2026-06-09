package com.example.evaluacion4charlottegabriel.Dao;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class DaoCarta {

    public DatabaseReference getReferencia() {
        return referencia;
    }

    private final DatabaseReference referencia;

    public DaoCarta() {
        referencia = FirebaseTarotDatabase.root();
    }

    public Carta leerDatosCarta(int id) {
        final Carta[] carta = {new Carta()};
        referencia.child("0").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                carta[0] = snapshot.getValue(Carta.class);
                Log.d("hola gabriel" , carta[0].getTitulo());
                Log.d("hola gabriel" , carta[0].getDescripcion());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return carta[0];

    }
}
