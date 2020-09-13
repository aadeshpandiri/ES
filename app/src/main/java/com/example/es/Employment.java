package com.example.es;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Employment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employment);
    }

    public void emtake(View view) {
        Intent i3 = new Intent(Employment.this,employtake.class);
        startActivity(i3);
    }

    public void emgive(View view) {
        Intent i3 = new Intent(Employment.this,employgive.class);
        startActivity(i3);
    }
}
