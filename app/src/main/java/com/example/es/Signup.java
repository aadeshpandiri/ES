package com.example.es;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signup extends AppCompatActivity {
    EditText e3,e4,e5,e6;
    FirebaseDatabase database;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        database=FirebaseDatabase.getInstance();
        e3 = (EditText)findViewById(R.id.n1);
        e4 = (EditText)findViewById(R.id.p1);
        e5 = (EditText)findViewById(R.id.p2);
        e6 =(EditText)findViewById(R.id.s);

    }
    public void sample(View v) {

        final String username = e3.getText().toString();
        final String password = e4.getText().toString();
        final String confirmpassword = e5.getText().toString();
        final String securityanswer = e6.getText().toString();
        reff = database.getReference().child("Accounts");
        if (!username.isEmpty() && !password.isEmpty() && !confirmpassword.isEmpty() && !securityanswer.isEmpty() ){
            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(username).exists()) {
                        Toast.makeText(Signup.this, "User already exits", Toast.LENGTH_LONG).show();

                        e3.setText("");
                        e4.setText("");
                        e5.setText("");
                        e6.setText("");


                    } else {
                        if (password.equals(confirmpassword)) {

                            reff.child(username).child("Username").setValue(username);
                            reff.child(username).child("Password").setValue(password);
                            reff.child(username).child("confirm Password").setValue(confirmpassword);
                            reff.child(username).child("security answer").setValue(securityanswer);

                            Toast.makeText(Signup.this, "Registration successfull", Toast.LENGTH_LONG).show();

                            e3.setText("");
                            e4.setText("");
                            e5.setText("");
                            e6.setText("");

                        }
                        else {
                            Toast.makeText(Signup.this, "Passwords not matched", Toast.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });

        }
        else
        {
            Toast.makeText(Signup.this, "Fill the complete details ", Toast.LENGTH_LONG).show();

        }




    }

    public void signin(View view) {
        Intent i9 = new Intent(Signup.this,MainActivity.class);
        startActivity(i9);
    }
}