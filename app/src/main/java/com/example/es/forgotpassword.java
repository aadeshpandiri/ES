package com.example.es;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class forgotpassword extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    FirebaseDatabase database;
    DatabaseReference myref3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        database=FirebaseDatabase.getInstance();
        e1=(EditText)findViewById(R.id.n22);
        e2=(EditText)findViewById(R.id.ans);
        e3=(EditText)findViewById(R.id.cp11);
        e4=(EditText)findViewById(R.id.cp22);

        setContentView(R.layout.activity_forgotpassword);
    }

    public void submitbtn(View view) {
        final String str=e1.getText().toString();
        final String secans=e2.getText().toString();
        final String pass=e3.getText().toString();
        final String pass1=e4.getText().toString();
        myref3=database.getReference().child("Accounts");
        myref3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(str).exists()){
                    String sec=dataSnapshot.child(str).child("security answer").getValue().toString();
                    if(secans.equals(sec)){
                        if(pass.equals(pass1)){
                            myref3.child(str).child("Password").setValue(pass);
                            myref3.child(str).child("confirm Password").setValue(pass1);
                        }
                        else
                        {
                            Toast.makeText(forgotpassword.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(forgotpassword.this, "Security answer not matched", Toast.LENGTH_LONG).show();
                    }


                }
                else
                {
                    Toast.makeText(forgotpassword.this, "User not exists", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
