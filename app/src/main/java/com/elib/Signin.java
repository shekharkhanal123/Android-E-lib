package com.elib;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signin extends AppCompatActivity {


    //      Variable Declaration
    SharedPreferences sharedPreferences;
    EditText musername,maddress,mphone;
    Button nextbtn;
    TextView navlogbtn;
    Intent next,navlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        //        get data from signup form
        musername = findViewById(R.id.inusername);
        maddress = findViewById(R.id.inaddress);
        mphone = findViewById(R.id.inphone);
        nextbtn = findViewById(R.id.Nextbtn);
        navlogbtn = findViewById(R.id.navlog);

        //        Navigation
        next = new Intent(Signin.this , Register.class);
        navlog = new Intent(Signin.this, Login.class);


        //        On click EvenHandler
        navlogbtn.setOnClickListener(v -> {
            startActivity(navlog);
            finish();
        });
        nextbtn.setOnClickListener(v -> {
            String username = musername.getText().toString().trim();
            String address = maddress.getText().toString().trim();
            String phone = mphone.getText().toString().trim();

            if(!username.equals("")&&!address.equals("")&&!phone.equals("")){

//                Input validation code here!!!

                next.putExtra("keyname",username);
                next.putExtra("keyphone",phone);
                next.putExtra("keyadd",address);

                startActivity(next);

            }else{
                Toast.makeText(getApplicationContext(),"All fields are required!",Toast.LENGTH_SHORT).show();

                if(TextUtils.isEmpty(username)){
                    musername.setError("Username is required!");
                }

                if(TextUtils.isEmpty(phone)){
                    mphone.setError("Phone number is required!");
                }

                if(TextUtils.isEmpty(address)){
                    maddress.setError("Address is required!");
                }
            }
        });
    }
}