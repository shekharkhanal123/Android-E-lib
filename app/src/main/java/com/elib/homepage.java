package com.elib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class homepage extends AppCompatActivity {

    Button dip,man,sci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        dip = findViewById(R.id.dipbtn);
        man = findViewById(R.id.manbtn);
        sci = findViewById(R.id.scibtn);


        //        Navigation
        Intent navdip = new Intent(homepage.this, diploma.class);
        Intent navsci = new Intent(homepage.this, management.class);
        Intent navman = new Intent(homepage.this, science.class);

        dip.setOnClickListener(v -> {
            startActivity(navdip);
        });

        sci.setOnClickListener(v -> {
            startActivity(navsci);
        });

        man.setOnClickListener(v -> {
            startActivity(navman);
        });

    }
}