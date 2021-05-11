package com.example.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MatchViewAdapter extends RecyclerView.Adapter< MatchViewHolder > {

    private List< MatchData > matchList;
    private Context mContext;

    MatchViewAdapter(Context mContext, List<MatchData> matchList) {
        this.mContext = mContext;
        this.matchList = matchList;
    }

    @Override
    public MatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_card, parent, false);
        return new MatchViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(MatchViewHolder holder, int position) {
        holder.mImage.setImageResource(matchList.get(position).getMatchImage());
        holder.mName.setText(matchList.get(position).getMatchName());
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }
}


