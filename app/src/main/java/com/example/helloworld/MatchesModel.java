package com.example.helloworld;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchesModel {

    private FirebaseFirestore db;
    private List<ListenerRegistration> listeners;

    public MatchesModel() {
        db = FirebaseFirestore.getInstance();
        listeners = new ArrayList<>();
    }

    public void addMatches(Matches matches) {
        CollectionReference matchesRef = db.collection("matches");
        matchesRef.add(matches);
    }

    public void getMatches(Consumer<QuerySnapshot> dataChangedCallback,
                           Consumer<FirebaseFirestoreException> dataErrorCallback) {
        ListenerRegistration listener = db.collection("matches")
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    if (e != null) {
                        dataErrorCallback.accept(e);
                    }
                    dataChangedCallback.accept(queryDocumentSnapshots);
                });
        listeners.add(listener);
    }

    public void updateMatchesById(Matches match) {
        DocumentReference matchRef = db.collection("matches").document(match.uid);
        Map<String, Object> data = new HashMap<>();
        data.put("name", match.name);
        data.put("imageUrl", match.imageUrl);
        data.put("liked", match.liked);
        data.put("lat", match.lat);
        data.put("longitude", match.longitude);
        matchRef.update(data);
    }

    public void clear() {
        listeners.forEach(ListenerRegistration::remove);
    }

}
