package com.example.hoot;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FeedActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("Feed");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        database.getReference("users").addListenerForSingleValueEvent(new ValueEventListener() {
            FirebaseUser user = mAuth.getCurrentUser();


            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("young").child(user.getUid()).exists()) {
                    myRef = database.getReference("users/wise");
                    FirebaseRecyclerAdapter<RecyclerModelActivity, ViewHolderActivity> firebaseRecyclerAdapter =
                            new FirebaseRecyclerAdapter<RecyclerModelActivity, ViewHolderActivity>(
                                    RecyclerModelActivity.class,
                                    R.layout.feed_card,
                                    ViewHolderActivity.class,
                                    myRef
                            ) {
                                @Override

                                protected void populateViewHolder(ViewHolderActivity ViewHolderActivity, RecyclerModelActivity RecyclerModelActivity, int position) {
                                    ViewHolderActivity.setDetails(FeedActivity.this, RecyclerModelActivity.getName(), RecyclerModelActivity.getAboutme(), RecyclerModelActivity.getImage());
                                }
                            };
                    recyclerView.setAdapter(firebaseRecyclerAdapter);
                } else if (dataSnapshot.child("wise").child(user.getUid()).exists()) {
                    myRef = database.getReference("users/young");
                    FirebaseRecyclerAdapter<RecyclerModelActivity, ViewHolderActivity> firebaseRecyclerAdapter =
                            new FirebaseRecyclerAdapter<RecyclerModelActivity, ViewHolderActivity>(
                                    RecyclerModelActivity.class,
                                    R.layout.feed_card,
                                    ViewHolderActivity.class,
                                    myRef
                            ) {
                                @Override
                                protected void populateViewHolder(ViewHolderActivity ViewHolderActivity, RecyclerModelActivity RecyclerModelActivity, int position) {
                                    ViewHolderActivity.setDetails(FeedActivity.this, RecyclerModelActivity.getName(), RecyclerModelActivity.getAboutme(), RecyclerModelActivity.getImage());
                                }
                            };
                    recyclerView.setAdapter(firebaseRecyclerAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}