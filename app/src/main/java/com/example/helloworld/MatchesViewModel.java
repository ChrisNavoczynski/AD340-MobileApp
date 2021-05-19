package com.example.helloworld;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.function.Consumer;

public class MatchesViewModel {

    private MatchesModel model;

    public MatchesViewModel() {
       model = new MatchesModel();
    }

    public void addMatches(Matches matches) {
        model.addMatches(matches);
    }

    public void getMatches(Consumer<ArrayList<Matches>> resultCallback) {
        model.getMatches(
                (QuerySnapshot querySnapShot) -> {
                    if (querySnapShot != null) {
                        ArrayList<Matches> myMatches = new ArrayList<>();
                        for (DocumentSnapshot matchSnapshot : querySnapShot.getDocuments()) {
                            Matches matches = matchSnapshot.toObject(Matches.class);
                            assert matches != null;
                            matches.uid = matchSnapshot.getId();
                            myMatches.add(matches);
                        }
                        resultCallback.accept(myMatches);
                    }
                },
                (databaseError -> System.out.println("An error occurred reading matches" + databaseError))
        );
    }

    public void updateMatches(Matches matches) {
        model.updateMatchesById(matches);
    }

    public void clear() {
        model.clear();
    }
}



