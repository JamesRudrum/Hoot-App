package com.example.hoot;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ViewHolderActivity extends RecyclerView.ViewHolder {

    View view;
    private List<String> theirInterests;
    private List<String> myInterests;
    private InterestsAlgorithm algorithm;

    public ViewHolderActivity(View itemView) {
        super(itemView);
        view = itemView;
    }

    public void setDetails(final Context ctx, String name, String aboutme, String image, final String userid, final String wiseoryoung){
        TextView nameView = view.findViewById(R.id.feedName);
        TextView aboutMeViewTitle = view.findViewById(R.id.feedAboutMeTitle);
        TextView aboutMeView = view.findViewById(R.id.feedAboutMe);
        ImageView imageView = view.findViewById(R.id.feedProfileImage);
        final Button BTNViewProfile = view.findViewById(R.id.BTNViewProfile);

        final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users/");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myInterests = new ArrayList<>();
                theirInterests = new ArrayList<>();
                getCurrentUserInterests(dataSnapshot);
                getOppositeUserInterests(dataSnapshot, wiseoryoung, userid, theirInterests);
                displayInterestMatchDetails();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        BTNViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation= AnimationUtils.loadAnimation(ctx,R.anim.fadein);
                BTNViewProfile.startAnimation(animation);
                Intent intent = new Intent(ctx, RequestContactPageActivity.class);
                intent.putExtra("userid", userid);
                intent.putExtra("wiseoryoung", wiseoryoung);
                ctx.startActivity(intent);
            }
        });

        nameView.setText(name);
        aboutMeViewTitle.setText("About me:");
        aboutMeView.setText(aboutme);
        GlideApp.with(ctx).load(image).into(imageView);
    }

    private void displayInterestMatchDetails() {
        TextView percentageMatchView = view.findViewById(R.id.feedPercentageMatch);
        TextView matchView = view.findViewById(R.id.feedMatch);
        algorithm = new InterestsAlgorithm();
        String matches = "Number of interest matches: " + String.valueOf(algorithm.calculateMatch(myInterests, theirInterests));
        String percentage = "Percentage match: " + String.valueOf(algorithm.calculatePercentageMatch(myInterests, theirInterests)) + "%";
        matchView.setText(matches);
        percentageMatchView.setText(percentage);
    }

    private void getCurrentUserInterests(DataSnapshot dataSnapshot) {
        if (currentUserIsYoung(dataSnapshot)) {
            getCurrentUsersInterests(dataSnapshot, "Young", myInterests);
        } else {
            getCurrentUsersInterests(dataSnapshot, "Wise", myInterests);

        }
    }

    private boolean currentUserIsYoung(DataSnapshot dataSnapshot) {
        return dataSnapshot.child("Young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).exists();
    }

    private void getOppositeUserInterests(DataSnapshot dataSnapshot, String accountType, String userid, List<String> theirInterests) {
        if (dataSnapshot.child(accountType).child(userid).child("Interests").child("CardGames").exists())
            theirInterests.add("Card Games");
        if (dataSnapshot.child(accountType).child(userid).child("Interests").child("BoardGames").exists())
            theirInterests.add("Board Games");
        if (dataSnapshot.child(accountType).child(userid).child("Interests").child("Puzzles").exists())
            theirInterests.add("Puzzles");
        if (dataSnapshot.child(accountType).child(userid).child("Interests").child("Knitting").exists())
            theirInterests.add("Knitting");
        if (dataSnapshot.child(accountType).child(userid).child("Interests").child("Music").exists())
            theirInterests.add("Music");
        if (dataSnapshot.child(accountType).child(userid).child("Interests").child("Films").exists())
            theirInterests.add("Film");
        if (dataSnapshot.child(accountType).child(userid).child("Interests").child("CurrentAffairs").exists())
            theirInterests.add("Current Affairs");
        if (dataSnapshot.child(accountType).child(userid).child("Interests").child("Photography").exists())
            theirInterests.add("Photography");
        if (dataSnapshot.child(accountType).child(userid).child("Interests").child("Books").exists())
            theirInterests.add("Books");
        if (dataSnapshot.child(accountType).child(userid).child("Interests").child("Sport").exists())
            theirInterests.add("Sport");
    }

    private void getCurrentUsersInterests(DataSnapshot dataSnapshot, String accountType, List<String> myInterests) {
        if (dataSnapshot.child(accountType).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("CardGames").exists())
            myInterests.add("Card Games");
        if (dataSnapshot.child(accountType).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("BoardGames").exists())
            myInterests.add("Board Games");
        if (dataSnapshot.child(accountType).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("Puzzles").exists())
            myInterests.add("Puzzles");
        if (dataSnapshot.child(accountType).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("Knitting").exists())
            myInterests.add("Knitting");
        if (dataSnapshot.child(accountType).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("Music").exists())
            myInterests.add("Music");
        if (dataSnapshot.child(accountType).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("Films").exists())
            myInterests.add("Film");
        if (dataSnapshot.child(accountType).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("CurrentAffairs").exists())
            myInterests.add("Current Affairs");
        if (dataSnapshot.child(accountType).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("Photography").exists())
            myInterests.add("Photography");
        if (dataSnapshot.child(accountType).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("Books").exists())
            myInterests.add("Books");
        if (dataSnapshot.child(accountType).child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Interests").child("Sport").exists())
            myInterests.add("Sport");
    }
}