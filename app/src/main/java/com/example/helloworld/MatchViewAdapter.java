package com.example.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MatchViewAdapter extends RecyclerView.Adapter< MatchViewHolder > {

    private List< Matches > matchList;
    private Context mContext;

    MatchViewAdapter(Context mContext, List<Matches> matchList) {
        this.mContext = mContext;
        this.matchList = matchList;
    }

    @Override
    public MatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_card, parent, false);
        return new MatchViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        if (matchList != null) {
            Matches mBind = this.matchList.get(position);
            holder.mName.setText(mBind.name);
            Picasso.get().load(mBind.imageUrl).into(holder.mImage);
            if (mBind.liked) {
                holder.favBtn.setBackgroundResource(R.drawable.ic_color_favorite_24);
            } else {
                holder.favBtn.setBackgroundResource(R.drawable.ic_shadow_favorite_24);
            }
        }
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public void setMatchesList(List<Matches> mSet) {

        this.matchList = mSet;
    }
}


