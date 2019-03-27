package com.example.hoot;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InterestsActivity extends AppCompatActivity {

    private RadioButton RBBoardGames;
    private RadioButton RBPuzzles;
    private RadioButton RBKnitting;
    private RadioButton RBMusic;
    private RadioButton RBFilms;
    private RadioButton RBCurrentAffairs;
    private RadioButton RBCardGames;
    private RadioButton RBPhotography;
    private RadioButton RBBooks;
    private RadioButton RBSport;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private Button BTNinterestsSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Select interests");

        RBBoardGames = findViewById(R.id.RBBoardGames);
        RBPuzzles = findViewById(R.id.RBPuzzles);
        RBKnitting = findViewById(R.id.RBKnitting);
        RBMusic = findViewById(R.id.RBMusic);
        RBFilms = findViewById(R.id.RBFilms);
        RBCurrentAffairs = findViewById(R.id.RBCurrentAffairs);
        RBCardGames = findViewById(R.id.RBCardGames);
        RBPhotography = findViewById(R.id.RBPhotography);
        RBBooks = findViewById(R.id.RBBooks);
        RBSport = findViewById(R.id.RBSport);
        BTNinterestsSubmit = findViewById(R.id.BTNinterestsSubmit);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        BTNinterestsSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        mAuth = FirebaseAuth.getInstance();
                        FirebaseUser user = mAuth.getCurrentUser();
                        String userid = user.getUid();
                        if (dataSnapshot.child("Young").child(user.getUid()).exists()) {
                            DatabaseReference myRef = databaseReference.child("Young").child(userid).child("Interests");
                            if (RBBoardGames.isChecked()) myRef.child("BoardGames").setValue("true");
                            if (RBPuzzles.isChecked()) myRef.child("Puzzles").setValue("true");
                            if (RBKnitting.isChecked()) myRef.child("Knitting").setValue("true");
                            if (RBMusic.isChecked()) myRef.child("Music").setValue("true");
                            if (RBFilms.isChecked()) myRef.child("Films").setValue("true");
                            if (RBCurrentAffairs.isChecked()) myRef.child("CurrentAffairs").setValue("true");
                            if (RBCardGames.isChecked()) myRef.child("CardGames").setValue("true");
                            if (RBPhotography.isChecked()) myRef.child("Photography").setValue("true");
                            if (RBBooks.isChecked()) myRef.child("Books").setValue("true");
                            if (RBSport.isChecked()) myRef.child("Sport").setValue("true");

                            startActivity(new Intent(InterestsActivity.this, ProfileActivity.class));

                        } else if (dataSnapshot.child("Wise").child(user.getUid()).exists()) {
                            DatabaseReference myRef = databaseReference.child("Wise").child(userid).child("Interests");
                            if (RBBoardGames.isChecked()) myRef.child("BoardGames").setValue("true");
                            if (RBPuzzles.isChecked()) myRef.child("Puzzles").setValue("true");
                            if (RBKnitting.isChecked()) myRef.child("Knitting").setValue("true");
                            if (RBMusic.isChecked()) myRef.child("Music").setValue("true");
                            if (RBFilms.isChecked()) myRef.child("Films").setValue("true");
                            if (RBCurrentAffairs.isChecked()) myRef.child("CurrentAffairs").setValue("true");
                            if (RBCardGames.isChecked()) myRef.child("CardGames").setValue("true");
                            if (RBPhotography.isChecked()) myRef.child("Photography").setValue("true");
                            if (RBBooks.isChecked()) myRef.child("Books").setValue("true");
                            if (RBSport.isChecked()) myRef.child("Sport").setValue("true");
                            startActivity(new Intent(InterestsActivity.this, ProfileActivity.class));


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });





        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
