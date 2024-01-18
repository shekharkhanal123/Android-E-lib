package com.elib;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Header extends AppCompatActivity {
    TextView name,email;

    @Override
    protected void onResume() {
        super.onResume();
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        Log.i("access","name");
        name.setText(SharedPrefManager.getInstance(this).getUsername());
        email.setText(SharedPrefManager.getInstance(this).getEmail());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);

        name.setText(SharedPrefManager.getInstance(this).getUsername());
        email.setText(SharedPrefManager.getInstance(this).getEmail());
    }
}