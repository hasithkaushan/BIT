package com.example.raj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {
    private TextView nameTextView;
    private TextView emailTextView,PasswordTextView;
    private ImageView UserImageView;
    private String email,password;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private static final String USERS = "users";
    String emaiString;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Intent intent = getIntent();
         email =intent.getStringExtra("email");

        nameTextView = findViewById(R.id.name_textview);
        emailTextView = findViewById(R.id.email_textview);
        PasswordTextView = findViewById(R.id.password_textview);
        UserImageView = findViewById(R.id.user_imageview);


        database =FirebaseDatabase.getInstance();
        userRef = database.getReference(USERS);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    if (ds.child("email").getValue().equals(email)){
                        nameTextView.setText(ds.child("fullName").getValue(String.class));
                        emailTextView.setText(email);
                        nameTextView.setText(ds.child("professions").getValue(String.class));
                        PasswordTextView.setText(password);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        }
        );


    }

    public void ClickFeedback(View view) {
    }
}