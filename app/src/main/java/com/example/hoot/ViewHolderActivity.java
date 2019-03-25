package com.example.hoot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewHolderActivity extends RecyclerView.ViewHolder {

    View view;

    public ViewHolderActivity(View itemView) {
        super(itemView);

        view = itemView;
    }

    public void setDetails(Context ctx, String name, String aboutme, String image){
        TextView nameView = view.findViewById(R.id.feedName);
        TextView aboutMeView = view.findViewById(R.id.feedAboutMe);
        ImageView imageView = view.findViewById(R.id.feedProfileImage);

        nameView.setText(name);
        aboutMeView.setText(aboutme);
        Picasso.get().load(image).into(imageView);
    }
}