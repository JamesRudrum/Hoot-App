package com.example.hoot;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button BTNsignuplink;
    private Button BTNsignin;
    private EditText ETLoginEmail;
    private EditText ETLoginPassword;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }

        BTNsignuplink = findViewById(R.id.BTNsignuplink);
        BTNsignuplink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, signup.class));
            }
        });


        BTNsignin = findViewById(R.id.BTNsignin);
        ETLoginEmail = findViewById(R.id.ETLoginEmail);
        ETLoginPassword = findViewById(R.id.ETLoginPassword);

        BTNsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth =  FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(ETLoginEmail.getText().toString(), ETLoginPassword.getText().toString())
                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                            } else    {
                                Toast.makeText(MainActivity.this, "Login UnSuccessful", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
            }
        });

    };
        }