package com.example.hoot;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ViewHolderActivity extends RecyclerView.ViewHolder {

    View view;
    public List<String> theirInterests;
    public List<String> myInterests;
    public Float percentageMatch;

    public int calculateMatch(List myInterests, List theirInterests) {
         int numberMatchedInterests = 0;
        for (int i = 0; i < myInterests.size(); i++) {
            for (int x = 0; x < theirInterests.size(); x++) {
                if (x == i) {
                    numberMatchedInterests += 1;
                }
            }
        }
        return numberMatchedInterests;
    }

    public int calculatePercentageMatch(List myInterests, List theirInterests) {
        int numberMatchedInterests = 0;
        System.out.println(myInterests.size());
        System.out.println(theirInterests.size());
        for (int i = 0; i < myInterests.size(); i++) {
            for (int x = 0; x < theirInterests.size(); x++) {
                if (x == i) {
                    numberMatchedInterests += 1;
                }
            }
        }
        float matches = (float) numberMatchedInterests;
        percentageMatch = (matches / myInterests.size()) * 100;
        return Math.round(percentageMatch);
    }


    public ViewHolderActivity(View itemView) {
        super(itemView);
        view = itemView;
    }

    public void setDetails(final Context ctx, String name, String aboutme, String image, final String userid, final String wiseoryoung){
        TextView nameView = view.findViewById(R.id.feedName);
        TextView aboutMeView = view.findViewById(R.id.feedAboutMe);
        ImageView imageView = view.findViewById(R.id.feedProfileImage);
        Button BTNViewProfile = view.findViewById(R.id.BTNViewProfile);

        final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users/");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myInterests = new ArrayList<>();
                if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).exists()) {

                    if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("CardGames").exists())
                        myInterests.add("Card Games");
                    if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("BoardGames").exists())
                        myInterests.add("Board Games");
                    if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("Puzzles").exists())
                        myInterests.add("Puzzles");
                    if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("Knitting").exists())
                        myInterests.add("Knitting");
                    if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("Music").exists())
                        myInterests.add("Music");
                    if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("Films").exists())
                        myInterests.add("Film");
                    if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("CurrentAffairs").exists())
                        myInterests.add("Current Affairs");
                    if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("Photography").exists())
                        myInterests.add("Photography");
                    if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("Books").exists())
                        myInterests.add("Books");
                    if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("Sport").exists())
                        myInterests.add("Sport");
                } else {

                    if (dataSnapshot.child("wise").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("CardGames").exists())
                        myInterests.add("Card Games");
                    if (dataSnapshot.child("wise").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("BoardGames").exists())
                        myInterests.add("Board Games");
                    if (dataSnapshot.child("wise").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("Puzzles").exists())
                        myInterests.add("Puzzles");
                    if (dataSnapshot.child("wise").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("Knitting").exists())
                        myInterests.add("Knitting");
                    if (dataSnapshot.child("wise").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("Music").exists())
                        myInterests.add("Music");
                    if (dataSnapshot.child("wise").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("Films").exists())
                        myInterests.add("Film");
                    if (dataSnapshot.child("wise").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("CurrentAffairs").exists())
                        myInterests.add("Current Affairs");
                    if (dataSnapshot.child("wise").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("Photography").exists())
                        myInterests.add("Photography");
                    if (dataSnapshot.child("wise").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("Books").exists())
                        myInterests.add("Books");
                    if (dataSnapshot.child("wise").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("Sport").exists())
                        myInterests.add("Sport");

                }
                theirInterests = new ArrayList<>();
                System.out.println(dataSnapshot.child(wiseoryoung).child(userid).child("Interests").child("Films"));

                if (dataSnapshot.child(wiseoryoung).child(userid).child("Interests").child("CardGames").exists())
                    theirInterests.add("Card Games");
                if (dataSnapshot.child(wiseoryoung).child(userid).child("Interests").child("BoardGames").exists())
                    theirInterests.add("Board Games");
                if (dataSnapshot.child(wiseoryoung).child(userid).child("Interests").child("Puzzles").exists())
                    theirInterests.add("Puzzles");
                if (dataSnapshot.child(wiseoryoung).child(userid).child("Interests").child("Knitting").exists())
                    theirInterests.add("Knitting");
                if (dataSnapshot.child(wiseoryoung).child(userid).child("Interests").child("Music").exists())
                    theirInterests.add("Music");
                if (dataSnapshot.child(wiseoryoung).child(userid).child("Interests").child("Films").exists())
                    theirInterests.add("Film");
                if (dataSnapshot.child(wiseoryoung).child(userid).child("Interests").child("CurrentAffairs").exists())
                    theirInterests.add("Current Affairs");
                if (dataSnapshot.child(wiseoryoung).child(userid).child("Interests").child("Photography").exists())
                    theirInterests.add("Photography");
                if (dataSnapshot.child(wiseoryoung).child(userid).child("Interests").child("Books").exists())
                    theirInterests.add("Books");
                if (dataSnapshot.child(wiseoryoung).child(userid).child("Interests").child("Sport").exists())
                    theirInterests.add("Sport");

                TextView percentageMatchView = view.findViewById(R.id.feedPercentageMatch);
                TextView matchView = view.findViewById(R.id.feedMatch);
                matchView.setText("Number of interest matches :" + String.valueOf(calculateMatch(myInterests, theirInterests)));
                percentageMatchView.setText("Percentage match :" + String.valueOf(calculatePercentageMatch(myInterests, theirInterests)) + "%");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        BTNViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, RequestContactPageActivity.class);
                intent.putExtra("userid", userid);
                intent.putExtra("wiseoryoung", wiseoryoung);
                ctx.startActivity(intent);
            }
        });

        nameView.setText(name);
        aboutMeView.setText(aboutme);
        Picasso.get().load(image).into(imageView);
    }
}