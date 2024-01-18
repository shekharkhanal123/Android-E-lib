package com.elib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class diploma extends AppCompatActivity {
ImageView backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diploma);
        backbtn= findViewById(R.id.backbtn);

        backbtn.setOnClickListener(v -> {
            finish();
        });
    }
}