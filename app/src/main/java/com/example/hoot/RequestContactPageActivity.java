package com.example.hoot;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class RequestContactPageActivity extends AppCompatActivity {

    private ImageView IVpictureRCPage;
    private TextView TVprofileNameRCPage;
    private TextView TVwiseOrYoungRCPage;
    private TextView TVaboutMeTitleRCPage;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestcontactpage);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TVprofileNameRCPage = findViewById(R.id.TVprofileNameRCPage);
        TVaboutMeTitleRCPage = findViewById(R.id.TVaboutMeTitleRCPage);
        TVwiseOrYoungRCPage = findViewById(R.id.TVwiseOrYoungRCPage);
        IVpictureRCPage = findViewById(R.id.IVpictureRCPage);

        if (getIntent().hasExtra("userid")) {
            String userid = getIntent().getStringExtra("userid");
            String wiseoryoung = getIntent().getStringExtra("wiseoryoung");
            System.out.println(userid);

            databaseReference = FirebaseDatabase.getInstance().getReference("users/" + wiseoryoung + "/" + userid);

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String aboutMe = dataSnapshot.child("aboutme").getValue(String.class);
                    String image = dataSnapshot.child("image").getValue(String.class);
                    String wiseoryoung = getIntent().getStringExtra("wiseoryoung");
                    TVprofileNameRCPage.setText(name);
                    TVaboutMeTitleRCPage.setText(aboutMe);
                    TVwiseOrYoungRCPage.setText(wiseoryoung);
                    GlideApp.with(RequestContactPageActivity.this).load(image).into(IVpictureRCPage);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

        FloatingActionButton fab = findViewById(R.id.FABchatButtonRCPage);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RequestContactPageActivity.this, Chat2Activity.class));
//                Intent intent = new Intent(, );;
            }
        });


    }

}
