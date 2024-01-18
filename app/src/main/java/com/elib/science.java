package com.elib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class science extends AppCompatActivity {
    ImageView backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science);
        backbtn= findViewById(R.id.backbtn);

        backbtn.setOnClickListener(v -> {
            finish();
        });

    }
}