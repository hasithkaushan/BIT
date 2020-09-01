package com.example.raj;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URI;
import java.net.URL;

public class Send_Feedback01 extends AppCompatActivity {
private Firebase Ref;
private EditText YourEmail,name,Rate,Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send__feedback01);




        YourEmail=(EditText)findViewById(R.id.YourEmail);
        name = (EditText)findViewById(R.id.name);
        Rate =(EditText)findViewById(R.id.Rate);
        Message =(EditText)findViewById(R.id.Message);
        Firebase.setAndroidContext(this);
       // Ref=new Firebase(URL:"" );
        //mdatabase = FirebaseDatabase.getInstance().getReference().child("Users");
    }

    public void feedbacksent(View view) {
        String YourEmailinput=YourEmail.getText().toString();
        String nameinput=name.getText().toString();
        String Rateinput=Rate.getText().toString();
        String Messageinput=Message.getText().toString();

        Firebase ReYourEmail=Ref.child("YourEmail");
        ReYourEmail.setValue(YourEmailinput);

        Firebase Rename=Ref.child("name");
        Rename.setValue(nameinput);

        Firebase ReRate=Ref.child("Rate");
        ReRate.setValue(Rateinput);

        Firebase ReMessage=Ref.child("Message");
        ReMessage.setValue(Messageinput);


    }
}