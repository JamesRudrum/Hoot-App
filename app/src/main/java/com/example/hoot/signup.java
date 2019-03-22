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
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {

    private Button BTNsignup;
    private EditText ETemail;
    private EditText ETfirstname;
    private EditText ETpassword;
    private FirebaseAuth mAuth;
    private Switch SWaccounttype;
    private EditText ETaboutme;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

        BTNsignup = findViewById(R.id.BTNsignup);
        ETfirstname = findViewById( R.id.ETfirstname);
        ETemail = findViewById(R.id.ETemail);
        ETpassword = findViewById(R.id.ETpassword);
        ETaboutme = findViewById(R.id.ETaboutme);
        SWaccounttype = findViewById(R.id.SWaccounttype);


        BTNsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();

                mAuth.createUserWithEmailAndPassword(ETemail.getText().toString(), ETpassword.getText().toString())
                        .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(signup.this, "Successful", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    String userid = user.getUid();
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference myRef = database.getReference().child("users").child(SWaccounttype.isChecked() ? "wise" : "young").child(userid);
                                    myRef.child("name").setValue(ETfirstname.getText().toString());
                                    myRef.child("aboutme").setValue(ETaboutme.getText().toString());
                                    startActivity(new Intent(signup.this, ProfileActivity.class));
                            } else {
                                    Toast.makeText(signup.this, "Unsuccessful", Toast.LENGTH_SHORT).show();
                                }

            };
        });
            };


        });
    }

}
