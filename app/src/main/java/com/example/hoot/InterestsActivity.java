package com.example.hoot;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.select_interests);

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
