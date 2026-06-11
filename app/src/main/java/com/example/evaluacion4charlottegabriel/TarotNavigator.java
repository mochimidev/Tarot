package com.example.evaluacion4charlottegabriel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.evaluacion4charlottegabriel.Dao.Carta;
import com.example.evaluacion4charlottegabriel.Dao.FirebaseTarotDatabase;
import com.example.evaluacion4charlottegabriel.ui.CardMeta;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;

public final class TarotNavigator {
    private TarotNavigator() {
    }

    public static void openHome(Context context) {
        start(context, new Intent(context, MainActivity.class));
    }

    public static void openCollection(Context context) {
        start(context, new Intent(context, CollectionActivity.class));
    }

    public static void openSettings(Context context) {
        start(context, new Intent(context, SettingsActivity.class));
    }

    public static void openPet(Context context) {
        start(context, new Intent(context, MascotaActivity.class));
    }

    public static void openDailyCard(Context context) {
        int numero = dailyCardId();
        int rotation = (numero % 2) * 180;
        Intent intent = new Intent(context, CartaDelDia.class);
        intent.putExtras(cardBundle(numero, rotation, fallbackCard(numero)));
        start(context, intent);
    }

    public static void openYesNo(Context context) {
        int numero = (int) (Math.random() * 78);
        int rotation = ((int) (Math.random() * 2)) * 180;
        Intent intent = new Intent(context, SiYNo.class);
        intent.putExtras(cardBundle(numero, rotation, fallbackCard(numero)));
        start(context, intent);
    }

    public static void openCouples(Context context) {
        int first = (int) (Math.random() * 78);
        int second = (int) (Math.random() * 78);
        Intent intent = new Intent(context, tarotPareja.class);
        Bundle bundle = new Bundle();
        bundle.putInt("numero", first);
        bundle.putInt("numerotupersona", second);
        bundle.putSerializable("tu", fallbackCard(first));
        bundle.putSerializable("pareja", fallbackCard(second));
        intent.putExtras(bundle);
        start(context, intent);
    }

    public static Carta fallbackCard(int id) {
        String title = CardMeta.cardTitle(id);
        String family = CardMeta.familyName(id);
        String description = CardMeta.familyMeaning(id);
        String inverted = "Cuando aparece invertida, " + title.toLowerCase()
                + " pide bajar el ritmo, ordenar emociones y escuchar la intuicion con suavidad.";
        String love = "En una lectura de pareja, " + title.toLowerCase()
                + " habla de cuidado, presencia y una conexion que crece cuando ambas energias se miran con ternura.";
        return new Carta(title, description, inverted, love);
    }

    private static Bundle cardBundle(int numero, int rotation, Carta carta) {
        Bundle bundle = new Bundle();
        bundle.putInt("numero", numero);
        bundle.putInt("rotacion", rotation);
        bundle.putSerializable("carta", carta);
        return bundle;
    }

    private static int dailyCardId() {
        LocalDateTime date = LocalDateTime.now();
        return (date.getDayOfMonth() + date.getMonthValue() + date.getYear()) % 78;
    }

    private static void fetchCard(Context context, int id, CardConsumer consumer) {
        FirebaseTarotDatabase.root().child(String.valueOf(id)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Carta carta = snapshot.getValue(Carta.class);
                consumer.accept(carta != null ? carta : fallbackCard(id));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Usando lectura local: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                consumer.accept(fallbackCard(id));
            }
        });
    }

    private static void start(Context context, Intent intent) {
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    private interface CardConsumer {
        void accept(Carta carta);
    }
}
