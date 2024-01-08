package com.elib;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity {

    EditText memail, mpassword;
    Button loginbtn ;
    ProgressDialog progressDialog;

    CheckBox showpass;

    private  boolean passwordshow = false;
    TextView navsignbtn;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //        getting data from login form
        memail = findViewById(R.id.inemail);
        mpassword = findViewById(R.id.inpassword);
        loginbtn = findViewById(R.id.loginbtn);
        navsignbtn = findViewById(R.id.navsign);
        showpass = findViewById(R.id.showpass);
        progressDialog = new ProgressDialog(this);

        //        Navigation
        Intent navsign = new Intent(Login.this, Signin.class);
        Intent homepage = new Intent(Login.this, homepage.class);


        //        On click EvenHandler
        showpass.setOnClickListener(v -> {
            if(passwordshow){
                passwordshow=false;
                mpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

            }else {
                passwordshow = true;
                mpassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
            mpassword.setSelection(mpassword.length());
        });
        navsignbtn.setOnClickListener(v -> {
            startActivity(navsign);
            finish();
        });
        loginbtn.setOnClickListener(v -> {
            String email = memail.getText().toString().trim();
            String password = mpassword.getText().toString().trim();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, constants.URL_LOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                if (response.equals("Success")){
                                    Toast.makeText(getApplicationContext(),"Login Successfully!",Toast.LENGTH_LONG).show();
                                    startActivity(homepage);
                                } else if(response.equals("wrgpass")) {
                                    mpassword.setError("Wrong password");
                                    Toast.makeText(getApplicationContext(),"Wrong Password!",Toast.LENGTH_LONG).show();
                                }
                                else{
                                    JSONObject jsonObject = new JSONObject(response);
                                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.hide();
                    Toast.makeText(getApplicationContext(),"Internet Connection Error!",Toast.LENGTH_SHORT).show();

                    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("email",email);
                    params.put("password",password);
                    return params;
                }
            };

            if(!email.equals("")&&!password.equals("")){
                try{

                    progressDialog.setMessage("Loading.....");
                    progressDialog.show();

                    RequestHandler.getInstance( this).addToRequestQueue(stringRequest);

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Network Error!", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(getApplicationContext(),"Invalid Inputs!",Toast.LENGTH_SHORT).show();

                if (TextUtils.isEmpty(email)) {
                    memail.setError("Invalid Email!");
                }

                if (TextUtils.isEmpty(password)) {
                    mpassword.setError("Invalid Password!");
                }
            }
        });
    }
}