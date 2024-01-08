package com.elib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button VAG = findViewById(R.id.VAG);
        Button Login = findViewById(R.id.login);
        Button Signin = findViewById(R.id.signup);


        Intent iNext = new Intent(MainActivity.this, homepage.class);
        Intent ilog = new Intent(MainActivity.this, Login.class);
        Intent isign = new Intent(MainActivity.this, Signin.class);



        VAG.setOnClickListener(V -> startActivity(iNext));
        Login.setOnClickListener(V -> startActivity(ilog));
        Signin.setOnClickListener(V -> startActivity(isign));
        

    }
}