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
/*            Picasso.with(mContext)
                    .load(uploadCurrent.getImageUrl())
                    .placeholder(R.mipmap.imageUrl)
                    .into(holder.imageView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess(){}
                        @Override
                        public void onError(){}
                    });*/
/*            holder.mImage.setImageResource(matchList.get(position).getMatchImage());
            holder.mName.setText(matchList.get(position).getMatchName());*/

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


