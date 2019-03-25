package com.example.hoot;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        ActionBar actionBar = getSupportActionBar();

//        actionBar.setTitle("Feed");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users/wise");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<RecyclerModelActivity, ViewHolderActivity> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<RecyclerModelActivity, ViewHolderActivity>(
                        RecyclerModelActivity.class,
                        R.layout.feed_card,
                        ViewHolderActivity.class,
                        myRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolderActivity ViewHolderActivity, RecyclerModelActivity RecyclerModelActivity, int position) {
                         ViewHolderActivity.setDetails(getApplicationContext(), RecyclerModelActivity.getName(), RecyclerModelActivity.getAboutme(), RecyclerModelActivity.getImage());
                    }
                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }


}