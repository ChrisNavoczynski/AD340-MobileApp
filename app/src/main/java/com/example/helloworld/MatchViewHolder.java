package com.example.helloworld;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import static android.provider.Settings.System.getString;
import static androidx.core.graphics.drawable.IconCompat.getResources;


class MatchViewHolder extends RecyclerView.ViewHolder {

    ImageView mImage;
    TextView mName;
    Button favBtn;

    MatchViewHolder(View itemView) {
        super(itemView);

        mImage = itemView.findViewById(R.id.ivImage);
        mName = itemView.findViewById(R.id.tvMatch);
        favBtn = itemView.findViewById(R.id.favBtn);

        favBtn.setOnClickListener(view -> {
            String name = "You liked " + mName.getText().toString();
            favBtn.setBackgroundResource(R.drawable.ic_color_favorite_24);
                Toast toast = Toast.makeText(view.getContext(),
                        name, Toast.LENGTH_LONG);
                View tView = toast.getView();
                tView.setBackgroundResource(R.color.p_light);
                toast.show();
        });
    }
}
