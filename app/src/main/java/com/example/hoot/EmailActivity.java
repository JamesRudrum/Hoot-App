package com.example.hoot;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EmailActivity extends AppCompatActivity {

    private EditText mEditTextSubject;
    private EditText mEditTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println(getIntent().getStringExtra("userid"));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEditTextSubject = findViewById(R.id.ETemail_subject);
        mEditTextMessage = findViewById(R.id.ETemail_message);

        Button buttonSend = findViewById(R.id.BTsend);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        });
    }

    private void sendMail(){
        if (getIntent().hasExtra("userid")) {
            String userid = getIntent().getStringExtra("userid");
            String wiseoryoung = getIntent().getStringExtra("wiseoryoung");

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("users").child(wiseoryoung).child(userid).child("email");

            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String recipient = dataSnapshot.getValue(String.class);
                    String subject = mEditTextSubject.getText().toString();
                    String message = mEditTextMessage.getText().toString();
                    System.out.println(recipient);
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Message from Hoot!: " + subject);
                    intent.putExtra(Intent.EXTRA_TEXT, message);

                    intent.setType("message/rfc822");
                    startActivity(Intent.createChooser(intent, "Choose an email client"));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
        startActivity(new Intent(EmailActivity.this, FeedActivity.class));

    }
}
