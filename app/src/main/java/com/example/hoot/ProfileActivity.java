package com.example.hoot;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;


public class ProfileActivity extends AppCompatActivity {

    private ImageView IVprofilePagePicture;
    private TextView TVprofileWiseOrYoung;
    private TextView TVprofilePageName;
    private TextView TVAboutMeProfile;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private TextView TVinterestsListProfilePage;
    private List<String> interestList;
    private ImageButton BTNProfileProfile;
    private ImageButton BTNFeedProfile;
    private ImageButton BTNLogoutProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();;
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        IVprofilePagePicture = findViewById(R.id.IVprofilePagePicture);
        TVprofileWiseOrYoung = findViewById(R.id.TVprofileWiseOrYoung);
        TVprofilePageName = findViewById(R.id.TVprofilePageName);
        TVAboutMeProfile = findViewById(R.id.TVAboutMeProfile);
        TVinterestsListProfilePage = findViewById(R.id.TVinterestsListProfilePage);

        BTNProfileProfile = findViewById(R.id.BTNProfileProfile);
        BTNProfileProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation= AnimationUtils.loadAnimation(ProfileActivity.this,R.anim.fadein);
                BTNProfileProfile.startAnimation(animation);
                startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
            }
        });

        BTNFeedProfile = findViewById(R.id.BTNFeedProfile);
        BTNFeedProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation= AnimationUtils.loadAnimation(ProfileActivity.this,R.anim.fadein);
                BTNFeedProfile.startAnimation(animation);
                startActivity(new Intent(ProfileActivity.this, FeedActivity.class));

            }
        });

        BTNLogoutProfile = findViewById(R.id.BTNLogoutProfile);
        BTNLogoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation= AnimationUtils.loadAnimation(ProfileActivity.this,R.anim.fadein);
                BTNLogoutProfile.startAnimation(animation);
                mAuth.signOut();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            FirebaseUser user = mAuth.getCurrentUser();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                interestList = new ArrayList<>();
                if (userIsYoung(dataSnapshot)) {
                    displayUserDetails(dataSnapshot, "Young", "Young");
                    getAllUserInterests(dataSnapshot, "Young");
                    addInterestsToView();
                    ProfileImage(dataSnapshot, "Young");

                } else if (userIsWise(dataSnapshot)) {
                    displayUserDetails(dataSnapshot, "Wise", "Wise");
                    getAllUserInterests(dataSnapshot, "Wise");
                    addInterestsToView();
                    ProfileImage(dataSnapshot, "Wise");
                }
            }

            private void ProfileImage(@NonNull DataSnapshot dataSnapshot, String accounttype) {
                    String image = dataSnapshot.child(accounttype).child(user.getUid()).child("image").getValue(String.class);
                    GlideApp.with(ProfileActivity.this).load(image).into(IVprofilePagePicture);
            }

            private boolean userIsWise(@NonNull DataSnapshot dataSnapshot) {
                return dataSnapshot.child("Wise").child(user.getUid()).exists();
            }

            private boolean userIsYoung(@NonNull DataSnapshot dataSnapshot) {
                return dataSnapshot.child("Young").child(user.getUid()).exists();
            }

            private void addInterestsToView() {
                int numberOfInterests = interestList.size();
                for (int i = 0; i < numberOfInterests; i++) {
                    TVinterestsListProfilePage.append((CharSequence) interestList.get(i));
                }
            }

            private void displayUserDetails(@NonNull DataSnapshot dataSnapshot, String accountType, String accountTypeDisplay) {
                String name = dataSnapshot.child(accountType).child(user.getUid()).child("name").getValue(String.class);
                String aboutMe = dataSnapshot.child(accountType).child(user.getUid()).child("aboutme").getValue(String.class);
                TVprofilePageName.setText(name);
                TVprofileWiseOrYoung.setText(accountTypeDisplay);
                TVAboutMeProfile.setText(aboutMe);
            }

            private void getAllUserInterests(@NonNull DataSnapshot dataSnapshot, String accountType) {
                if (dataSnapshot.child(accountType).child(user.getUid()).child("Interests").child("BoardGames").exists())
                    interestList.add("Board Games\n");
                if (dataSnapshot.child(accountType).child(user.getUid()).child("Interests").child("Books").exists())
                    interestList.add("Books\n");
                if (dataSnapshot.child(accountType).child(user.getUid()).child("Interests").child("CardGames").exists())
                    interestList.add("Card Games\n");
                if (dataSnapshot.child(accountType).child(user.getUid()).child("Interests").child("CurrentAffairs").exists())
                    interestList.add("Current Affairs\n");
                if (dataSnapshot.child(accountType).child(user.getUid()).child("Interests").child("Films").exists())
                    interestList.add("Films\n");
                if (dataSnapshot.child(accountType).child(user.getUid()).child("Interests").child("Knitting").exists())
                    interestList.add("Knitting\n");
                if (dataSnapshot.child(accountType).child(user.getUid()).child("Interests").child("Music").exists())
                    interestList.add("Music\n");
                if (dataSnapshot.child(accountType).child(user.getUid()).child("Interests").child("Photography").exists())
                    interestList.add("Photography\n");
                if (dataSnapshot.child(accountType).child(user.getUid()).child("Interests").child("Puzzles").exists())
                    interestList.add("Puzzles\n");
                if (dataSnapshot.child(accountType).child(user.getUid()).child("Interests").child("Sport").exists())
                    interestList.add("Sport\n");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

   }

