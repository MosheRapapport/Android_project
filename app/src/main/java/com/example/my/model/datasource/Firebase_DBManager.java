package com.example.my.model.datasource;

import androidx.annotation.NonNull;

import com.example.my.model.entities.Pack;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Firebase_DBManager {

    public interface Action<T> {
        void onSuccess(T obj);

        void onFailure(Exception exception);

        void onProgress(String status, double percent);
    }

    public interface NotifyDataChange<T> {
        void OnDataChanged(T obj);

        void onFailure(Exception exception);
    }

    static DatabaseReference PackRef;
    static List<Pack> packsList;

    static {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        PackRef = database.getReference("packs");
        packsList = new ArrayList<>();
    }

    public static void addPackToFirebase(final Pack pack, final Action<Long> action) {
        final String key = PackRef.push().getKey();
        pack.setaKey(key);
        PackRef.child(key).setValue(pack).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                action.onSuccess(null);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                action.onFailure(e);

            }
        });
    }

}
