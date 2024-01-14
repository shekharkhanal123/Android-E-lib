package com.elib;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Header extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        sharedPreferences=getSharedPreferences("user_info",MODE_PRIVATE);
        name.setText("hello");
        name.setText("helllo");
        name.setText(sharedPreferences.getString("username",null));
        email.setText(sharedPreferences.getString("email",null));
    }
}