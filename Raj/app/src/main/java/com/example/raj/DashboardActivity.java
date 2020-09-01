package com.example.raj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardActivity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView emailTextView,PasswordTextView;
    private ImageView UserImageView;
    private String email,password;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private static final String USERS = "users";
    String emaiString;

    Button DeleteAccount;

    String EmailHolder;
    TextView Email;
    Button LogOUT ;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;
    FirebaseUser mUser;
    //@SuppressLint("SetTextI18n")
    public static final String TAG="LOGIN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        DeleteAccount = findViewById(R.id.DeleteAccount);
        DeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(DashboardActivity.this);
                dialog.setTitle("Are you sure?....");
                dialog.setMessage("Delete sucesfully.....");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        Firebase.delete()

                    }
                });

            }
        });

/////////////////////////

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
///////////////////////
        //Email = (TextView)findViewById(R.id.textView1);
        //LogOUT = (Button)findViewById(R.id.button1);

        //Intent intent = getIntent();

        // Receiving User Email Send By MainActivity.
        EmailHolder = intent.getStringExtra(MainActivity.userEmail);

        // Setting up received email to TextView.
        Email.setText(Email.getText().toString()+ EmailHolder);

        // Adding click listener to Log Out button.

        LogOUT.setOnClickListener(new View.OnClickListener() {
            // @Override
            public void onClick(View v) {


                //Finishing current DashBoard activity on button click.
                finish();

                Toast.makeText(DashboardActivity.this,"Log Out Successfull", Toast.LENGTH_LONG).show();
                //Intent intent=new Intent(DashboardActivity.this,login.class);
                //startActivity(intent);
               /*if (v.getId() == R.id.button1) {
                    AuthUI.getInstance()
                            .signOut(this)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                public void onComplete(@NonNull Task<Void> task) {
                                    // user is now signed out
                                    startActivity(new Intent(DashboardActivity.this, login.class));
                                    finish();
                                }
                            });
                }*/

            }
        });

    }

    public void ClickFeedback(View view) {
        Intent i1= new Intent(this,FeedbackActivity.class);
        startActivity(i1);
    }
    public void ClickEdit(View view) {
        Intent j1= new Intent(this,profile.class);
        startActivity(j1);
    }

}