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

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ViewHolderActivity extends RecyclerView.ViewHolder {

    View view;
    public List<String> theirInterests;
    public List<String> myInterests;
    public Float percentageMatch;
    public Float numberMatchedInterests;

    public Float calculateMatch(List myInterests, List theirInterests) {

        for (Object myInterest : myInterests) {
            for (Object theirInterest : theirInterests) {
                if (myInterest == theirInterest) {
                    numberMatchedInterests += 1;
                }
            }
        }

        percentageMatch = (numberMatchedInterests / myInterests.size()) * 100;
        return percentageMatch;
    }

    public void getInterests(String wiseoryoung, String userid) {
        DatabaseReference theirRef = FirebaseDatabase.getInstance().getReference("users/" + wiseoryoung + "/" + userid + "/interests");

        theirRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("CardGames").exists())
                    theirInterests.add("Card Games");
                System.out.println("in their interests");
                if (dataSnapshot.child("BoardGames").exists())
                    theirInterests.add("Board Games");
                if (dataSnapshot.child("Puzzles").exists())
                    theirInterests.add("Puzzles");
                if (dataSnapshot.child("Knitting").exists())
                    theirInterests.add("Knitting");
                if (dataSnapshot.child("Music").exists())
                    theirInterests.add("Music");
                if (dataSnapshot.child("Films").exists())
                    theirInterests.add("Film");
                if (dataSnapshot.child("CurrentAffairs").exists())
                    theirInterests.add("Current Affairs");
                if (dataSnapshot.child("Photography").exists())
                    theirInterests.add("Photography");
                if (dataSnapshot.child("Books").exists())
                    theirInterests.add("Books");
                if (dataSnapshot.child("Sport").exists())
                    theirInterests.add("Sport");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users/");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).exists()) {

                    if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").child("CardGames").exists())
                        myInterests.add("Card Games");
                    System.out.println("in my interests");
                    if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").child("BoardGames").exists())
                        myInterests.add("Board Games");
                    if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").child("Puzzles").exists())
                        myInterests.add("Puzzles");
                    if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").child("Knitting").exists())
                        myInterests.add("Knitting");
                    if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").child("Music").exists())
                        myInterests.add("Music");
                    if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").child("Films").exists())
                        myInterests.add("Film");
                    if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").child("CurrentAffairs").exists())
                        myInterests.add("Current Affairs");
                    if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").child("Photography").exists())
                        myInterests.add("Photography");
                    if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").child("Books").exists())
                        myInterests.add("Books");
                    if (dataSnapshot.child("young").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").child("Sport").exists())
                        myInterests.add("Sport");
                } else {

                    if (dataSnapshot.child("wise").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").child("CardGames").exists())
                        myInterests.add("Card Games");
                    if (dataSnapshot.child("wise").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").child("BoardGames").exists())
                        myInterests.add("Board Games");
                    if (dataSnapshot.child("wise").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").child("Puzzles").exists())
                        myInterests.add("Puzzles");
                    if (dataSnapshot.child("wise").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").child("Knitting").exists())
                        myInterests.add("Knitting");
                    if (dataSnapshot.child("wise").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").child("Music").exists())
                        myInterests.add("Music");
                    if (dataSnapshot.child("wise").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").child("Films").exists())
                        myInterests.add("Film");
                    if (dataSnapshot.child("wise").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").child("CurrentAffairs").exists())
                        myInterests.add("Current Affairs");
                    if (dataSnapshot.child("wise").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").child("Photography").exists())
                        myInterests.add("Photography");
                    if (dataSnapshot.child("wise").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").child("Books").exists())
                        myInterests.add("Books");
                    if (dataSnapshot.child("wise").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("interests").child("Sport").exists())
                        myInterests.add("Sport");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    };




    public ViewHolderActivity(View itemView) {
        super(itemView);
        view = itemView;
    }

    public void setDetails(final Context ctx, String name, String aboutme, String image, final String userid, final String wiseoryoung){
        TextView nameView = view.findViewById(R.id.feedName);
        TextView aboutMeView = view.findViewById(R.id.feedAboutMe);
        ImageView imageView = view.findViewById(R.id.feedProfileImage);
        Button BTNViewProfile = view.findViewById(R.id.BTNViewProfile);
        TextView percentageMatchView = view.findViewById(R.id.feedPercentageMatch);

        getInterests(wiseoryoung, userid);

        System.out.println(myInterests);
        System.out.println(theirInterests);

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
//        percentageMatchView.setText(String.valueOf(calculateMatch(myInterests, theirInterests)));
    }

}