package com.example.my.model.datasource;

import androidx.annotation.NonNull;

import com.example.my.model.entities.Pack;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
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
    private static ChildEventListener pakeRefChildEventListener;

    public static void notifitoPackList(final NotifyDataChange<List<Pack>>notifyDataChange){
        if (notifyDataChange != null) {

            if (pakeRefChildEventListener != null) {
                notifyDataChange.onFailure(new Exception("first unNotify student list"));
                return;
            }
            packsList.clear();

            pakeRefChildEventListener= new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Pack pack = dataSnapshot.getValue(Pack.class);
                    String aKey = dataSnapshot.getKey();
                    pack.setaKey(aKey);
                    packsList.add(pack);


                    notifyDataChange.OnDataChanged(packsList);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Pack pack = dataSnapshot.getValue(Pack.class);
                    String aKey = dataSnapshot.getKey();
                    pack.setaKey(aKey);


                    for (int i = 0; i < packsList.size(); i++) {
                        if (packsList.get(i).getaKey().equals(aKey)) {
                            packsList.set(i, pack);
                            break;
                        }
                    }
                    notifyDataChange.OnDataChanged(packsList);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    Pack pack = dataSnapshot.getValue(Pack.class);
                   String aKey = dataSnapshot.getKey();
                    pack.setaKey(aKey);

                    for (int i = 0; i < packsList.size(); i++) {
                        if (packsList.get(i).getaKey() == aKey) {
                            packsList.remove(i);
                            break;
                        }
                    }
                    notifyDataChange.OnDataChanged(packsList);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    notifyDataChange.onFailure(databaseError.toException());
                }
            };
            PackRef.addChildEventListener(pakeRefChildEventListener);
        }

    }
    public static void stopnotifitoPackList() {
        if (pakeRefChildEventListener != null) {
            PackRef.removeEventListener(pakeRefChildEventListener);
            pakeRefChildEventListener = null;
        }

    }
};

