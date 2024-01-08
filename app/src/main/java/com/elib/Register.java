package com.elib;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarException;


public class Register extends AppCompatActivity {

    private boolean passwordshow = false;
    EditText memail, mcrpass, mcpass;
    Button registerbtn;
    ProgressDialog progressDialog;
    CheckBox showpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //        get value from signin activity
        String username = getIntent().getStringExtra("keyname");
        String phone = getIntent().getStringExtra("keyphone");
        String address = getIntent().getStringExtra("keyadd");


        //        get data from signup form
        memail = findViewById(R.id.sgEmail);
        mcrpass = findViewById(R.id.crpass);
        mcpass = findViewById(R.id.cpass);
        registerbtn = findViewById(R.id.signubtn);
        showpass = findViewById(R.id.showpass);
        progressDialog = new ProgressDialog(this);

        //        Navigation
        Intent Login =new Intent(Register.this , Login.class);
        Intent OTP = new Intent(Register.this , OTP.class);




        //        On click EvenHandler
        showpass.setOnClickListener(v -> {
             //          password visibility

            if(passwordshow){
                passwordshow=false;
                mcrpass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                mcpass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

            }else {
                passwordshow = true;
                mcrpass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                mcpass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

            }
            mcrpass.setSelection(mcrpass.length());
            mcpass.setSelection(mcpass.length());
        });
        registerbtn.setOnClickListener(v -> {
            String email = memail.getText().toString().trim();
            String crpass = mcrpass.getText().toString().trim();
            String cpass = mcpass.getText().toString().trim();
            OTP.putExtra("keyname",username);
            OTP.putExtra("keyphone",phone);
            OTP.putExtra("keyadd",address);
            OTP.putExtra("keyemail",email);
            OTP.putExtra("keyadd",cpass);

            if(!email.equals("")&&!crpass.equals("")&&!cpass.equals(""))
            {
                //            password validation
                if(crpass.length() < 6)
                {

                    mcrpass.setError("Must be at least 6 letter");
                    Toast.makeText(getApplicationContext(), "Password must be atleast 6 letter!", Toast.LENGTH_SHORT).show();
                    //    other password validation

                }
                else if (!(crpass.matches(cpass)))
                {
                    mcpass.setError("Password doesn't match!");
                    Toast.makeText(getApplicationContext(), "Password must Match!", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    try
                    {
                        progressDialog.setMessage("Verifying User...");
                        progressDialog.show();
                        //1111111
                        Log.i("otp",  "before calling verify");
                        verify(email,username);

                    }
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(), "Network Error!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(), "All fields are required!", Toast.LENGTH_SHORT).show();

                if(TextUtils.isEmpty(email))
                {
                    memail.setError("Email is required!");
                }

                if(TextUtils.isEmpty(crpass))
                {
                    mcrpass.setError("Password is required!");
                }

                if(TextUtils.isEmpty(cpass))
                {
                    mcpass.setError("Password doesn't match!");
                }
            }

        });
    }
    private void verify(String em,String us){
        //11111
        StringRequest stringRequest = new StringRequest(Request.Method.POST,constants.URL_VERIFY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("otp",  "during varifying");
                        progressDialog.dismiss();
                        try {
//                                JSONObject jsonObject = new JSONObject(response);
//                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            if(response.equals("Verified")){
                                Toast.makeText(getApplicationContext(),"User Verified",Toast.LENGTH_SHORT).show();
                                progressDialog.setMessage("Sending OTP to your Email...");
                                progressDialog.show();
                                Log.i("otp",  "after verify");
                                mail(em,us);
                            }
                            else {
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            }
//

                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext()," Internet Connection Error! ",Toast.LENGTH_SHORT).show();
            }
        }){
            @NonNull
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name",us);
                params.put("email",em);
                return params;
            }
        };
        RequestHandler.getInstance( this).addToRequestQueue(stringRequest);
    }
    private void mail(String X,String Y){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,constants.URL_MAILER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try{
                        Toast.makeText(getApplicationContext(),"OTP is sent to your email!",Toast.LENGTH_LONG).show();
                        Intent OTP = new Intent(Register.this , OTP.class);
                        Log.i("otp",  "after sending otp");
                        startActivity(OTP);
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(),"Mailer isn't responding !",Toast.LENGTH_SHORT).show();

            }
        })
        {
            @NonNull
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email", X);
                params.put("name", Y);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }
}