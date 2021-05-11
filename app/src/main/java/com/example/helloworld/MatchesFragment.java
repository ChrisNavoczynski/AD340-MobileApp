package com.example.helloworld;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MatchesFragment extends Fragment {

    RecyclerView recyclerView;
    List <MatchData> mMatchList;
    MatchData matchData;

    @Override
    public void onCreate(Bundle savedInstanceSate) {
        super.onCreate(savedInstanceSate);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager myGridLayout = new GridLayoutManager(getActivity(), 2,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(myGridLayout);

        mMatchList = new ArrayList<>();
        matchData = new MatchData(getString(R.string.akika), R.drawable.akika);
        mMatchList.add(matchData);
        matchData = new MatchData(getString(R.string.cuddles), R.drawable.cuddles);
        mMatchList.add(matchData);
        matchData = new MatchData(getString(R.string.milan), R.drawable.milan);
        mMatchList.add(matchData);
        matchData = new MatchData(getString(R.string.tifa), R.drawable.tifa);
        mMatchList.add(matchData);
        matchData = new MatchData(getString(R.string.lulu), R.drawable.lulu);
        mMatchList.add(matchData);
        matchData = new MatchData(getString(R.string.snowflake), R.drawable.snowflake);
        mMatchList.add(matchData);
        matchData = new MatchData(getString(R.string.sunshine), R.drawable.sunshine);
        mMatchList.add(matchData);
        matchData = new MatchData(getString(R.string.isis), R.drawable.isis);
        mMatchList.add(matchData);

        MatchViewAdapter myAdapter = new MatchViewAdapter(getContext(), mMatchList);
        recyclerView.setAdapter(myAdapter);

        return view;
    }
}
