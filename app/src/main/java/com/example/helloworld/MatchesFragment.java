package com.example.helloworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MatchesFragment extends Fragment {

    RecyclerView recyclerView;
    public ArrayList mList = new ArrayList();
    private MatchesViewModel viewModel = new MatchesViewModel();

    @Override
    public void onCreate(Bundle savedInstanceSate) {
        super.onCreate(savedInstanceSate);
        setHasOptionsMenu(true);

        if(getArguments() != null){
           mList = getArguments().getParcelableArrayList("matches");
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(false);
        GridLayoutManager myGridLayout = new GridLayoutManager(getActivity(), 2,
                GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(myGridLayout);
        MatchViewAdapter myAdapter = new MatchViewAdapter(getContext(), mList);
        recyclerView.setAdapter(myAdapter);

        viewModel = new MatchesViewModel();
        viewModel.getMatches(
                (ArrayList<Matches> matches) -> {
                    myAdapter.setMatchesList(matches);
                    myAdapter.notifyDataSetChanged();
                }
        );

/*        mMatchList = new ArrayList<>();
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
        mMatchList.add(matchData);*/
        return view;
    }

    public MatchesViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(MatchesViewModel vm) {
        viewModel = vm;
    }

    @Override
    public void onPause() {
        viewModel.clear();
        super.onPause();
    }
}
