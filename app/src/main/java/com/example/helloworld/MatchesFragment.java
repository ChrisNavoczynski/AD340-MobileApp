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
