package com.example.evaluacion4charlottegabriel.Dao;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public final class FirebaseTarotDatabase {
    private static final String DATABASE_URL = "https://tarotplush-default-rtdb.firebaseio.com/";

    private FirebaseTarotDatabase() {
    }

    public static DatabaseReference root() {
        return FirebaseDatabase.getInstance(DATABASE_URL).getReference();
    }
}
