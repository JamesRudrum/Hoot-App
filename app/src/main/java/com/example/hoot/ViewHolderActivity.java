package com.example.hoot;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URL;

public class ViewHolderActivity extends RecyclerView.ViewHolder {

    View view;

    public ViewHolderActivity(View itemView) {
        super(itemView);
        view = itemView;
    }

    public void setDetails(final Context ctx, String name, String aboutme, String image, final String path){
        TextView nameView = view.findViewById(R.id.feedName);
        TextView aboutMeView = view.findViewById(R.id.feedAboutMe);
        ImageView imageView = view.findViewById(R.id.feedProfileImage);
        Button BTNViewProfile = view.findViewById(R.id.BTNViewProfile);

        BTNViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(path);
                ctx.startActivity(new Intent(ctx, RequestContactPageActivity.class));
            }
        });

        nameView.setText(name);
        aboutMeView.setText(aboutme);
        Picasso.get().load(image).into(imageView);
    }
}