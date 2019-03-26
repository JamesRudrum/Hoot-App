package com.example.hoot;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
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
    private TextView TVaboutMeTitle;
    private TextView TVAboutMeProfile;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;
    private String profileImageName;
    private TextView TVinterestsListProfilePage;
    private List<String> interestList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        profileImageName = user.getUid() + ".jpg";


        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        storageReference = FirebaseStorage.getInstance().getReference("images");
        StorageReference profileImage = storageReference.child(profileImageName);

//        GlideApp.with(this).load(profileImage).into(IVprofilePagePicture);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            FirebaseUser user = mAuth.getCurrentUser();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                interestList = new ArrayList<>();
                if (dataSnapshot.child("young").child(user.getUid()).exists()) {
                    String name = dataSnapshot.child("young").child(user.getUid()).child("name").getValue(String.class);
                    String aboutMe = dataSnapshot.child("young").child(user.getUid()).child("aboutme").getValue(String.class);
                    TVprofilePageName.setText(name);
                    TVprofileWiseOrYoung.setText("Young");
                    TVAboutMeProfile.setText(aboutMe);
                    if (dataSnapshot.child("young").child(user.getUid()).child("Interests").child("CardGames").exists()) interestList.add("Card Games\n");
                    if (dataSnapshot.child("young").child(user.getUid()).child("Interests").child("BoardGames").exists()) interestList.add("Board Games\n");
                    if (dataSnapshot.child("young").child(user.getUid()).child("Interests").child("Puzzles").exists()) interestList.add("Puzzles\n");
                    if (dataSnapshot.child("young").child(user.getUid()).child("Interests").child("Knitting").exists()) interestList.add("Knitting\n");
                    if (dataSnapshot.child("young").child(user.getUid()).child("Interests").child("Music").exists()) interestList.add("Music\n");
                    if (dataSnapshot.child("young").child(user.getUid()).child("Interests").child("Films").exists()) interestList.add("Films\n");
                    if (dataSnapshot.child("young").child(user.getUid()).child("Interests").child("CurrentAffairs").exists()) interestList.add("Current Affairs\n");
                    if (dataSnapshot.child("young").child(user.getUid()).child("Interests").child("Photography").exists()) interestList.add("Photography\n");
                    if (dataSnapshot.child("young").child(user.getUid()).child("Interests").child("Books").exists()) interestList.add("Books\n");
                    if (dataSnapshot.child("young").child(user.getUid()).child("Interests").child("Sport").exists()) interestList.add("Sport\n");

                    int numberOfInterests = interestList.size();
                    for(int i = 0; i < numberOfInterests; i ++) {
                        TVinterestsListProfilePage.append((CharSequence) interestList.get(i));
                    }

                } else if (dataSnapshot.child("wise").child(user.getUid()).exists()) {
                    String name = dataSnapshot.child("wise").child(user.getUid()).child("name").getValue(String.class);
                    String aboutMe = dataSnapshot.child("wise").child(user.getUid()).child("aboutme").getValue(String.class);
                    TVprofilePageName.setText(name);
                    TVprofileWiseOrYoung.setText("Wise");
                    TVAboutMeProfile.setText(aboutMe);

                    if (dataSnapshot.child("wise").child(user.getUid()).child("Interests").child("CardGames").exists()) interestList.add("Card Games\n");
                    if (dataSnapshot.child("wise").child(user.getUid()).child("Interests").child("BoardGames").exists()) interestList.add("Board Games\n");
                    if (dataSnapshot.child("wise").child(user.getUid()).child("Interests").child("Puzzles").exists()) interestList.add("Puzzles\n");
                    if (dataSnapshot.child("wise").child(user.getUid()).child("Interests").child("Knitting").exists()) interestList.add("Knitting\n");
                    if (dataSnapshot.child("wise").child(user.getUid()).child("Interests").child("Music").exists()) interestList.add("Music\n");
                    if (dataSnapshot.child("wise").child(user.getUid()).child("Interests").child("Films").exists()) interestList.add("Films\n");
                    if (dataSnapshot.child("wise").child(user.getUid()).child("Interests").child("CurrentAffairs").exists()) interestList.add("Current Affairs\n");
                    if (dataSnapshot.child("wise").child(user.getUid()).child("Interests").child("Photography").exists()) interestList.add("Photography\n");
                    if (dataSnapshot.child("wise").child(user.getUid()).child("Interests").child("Books").exists()) interestList.add("Books\n");
                    if (dataSnapshot.child("wise").child(user.getUid()).child("Interests").child("Sport").exists()) interestList.add("Sport\n");

                    int numberOfInterests = interestList.size();
                    for(int i = 0; i < numberOfInterests; i ++) {
                        TVinterestsListProfilePage.append((CharSequence) interestList.get(i));
                    }
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        IVprofilePagePicture = findViewById(R.id.IVprofilePagePicture);
        TVprofileWiseOrYoung = findViewById(R.id.TVprofileWiseOrYoung);
        TVprofilePageName = findViewById(R.id.TVprofilePageName);
        TVaboutMeTitle = findViewById(R.id.TVaboutMeTitle);
        TVAboutMeProfile = findViewById(R.id.TVAboutMeProfile);
        TVinterestsListProfilePage = findViewById(R.id.TVinterestsListProfilePage);


//        FloatingActionButton fab = findViewById(R.id.fab);
////        fab.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
////            }
////        });
    }

   }

