package com.elib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    ImageView backbtn;
    TextView name,email,hisbtn,brbtn,rtbtn;

    @Override
    protected void onResume() {
        super.onResume();
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        name.setText(SharedPrefManager.getInstance(this).getUsername());
        email.setText(SharedPrefManager.getInstance(this).getEmail());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(v -> {
            finish();
        });

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        hisbtn = findViewById(R.id.hisbtn);
        brbtn = findViewById(R.id.brbtn);
        rtbtn = findViewById(R.id.rtbtn);

        name.setText(SharedPrefManager.getInstance(this).getUsername());
        email.setText(SharedPrefManager.getInstance(this).getEmail());

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new BorrowedFragment()).commit();
        }

        hisbtn.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new historyFragment()).commit();
        });

        brbtn.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new BorrowedFragment()).commit();
        });

        rtbtn.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new ReturnedFragment()).commit();
        });

    }
}