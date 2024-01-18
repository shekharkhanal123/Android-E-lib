package com.elib;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.function.Function;
import java.util.jar.JarException;


public class Register extends AppCompatActivity {
    SharedPreferences sharedPreferences;
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

            Login.putExtra("keyname",username);

//               send info to otp section
//            OTP.putExtra("keyname",username);
//            OTP.putExtra("keyphone",phone);
//            OTP.putExtra("keyadd",address);
//            OTP.putExtra("keyemail",email);
//            OTP.putExtra("keyadd",cpass);

            sharedPreferences= getSharedPreferences("user_info", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("phone",phone);
            editor.putString("address",address);
            editor.putString("cpass",cpass);
            editor.putString("username",username);
            editor.putString("email",email);
            editor.commit();

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
                                Toast.makeText(getApplicationContext(),"User Approved!",Toast.LENGTH_SHORT).show();
                                progressDialog.setMessage("Registering User");
                                progressDialog.show();
                                Log.i("otp",  "after verify");
                                mail(us,em);
                            }
                            else {
                                JSONObject jsonObject = new JSONObject(response);
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(),"hello Internet Connection Error! ",Toast.LENGTH_SHORT).show();
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
    private void mail(String username,String email){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,constants.URL_MAILER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try{
//                        Toast.makeText(getApplicationContext(),"OTP is sent to your email!",Toast.LENGTH_LONG).show();
//                        Intent OTP = new Intent(Register.this , OTP.class);
//                        startActivity(OTP);
//                        finish();
                    String add,phone,pass;
                   phone= sharedPreferences.getString("phone",null);
                   add= sharedPreferences.getString("address",null);
                   pass= sharedPreferences.getString("cpass",null);
                        Log.i("otp",  "after sending otp");
                        con(username,phone,add,email,pass);

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
                params.put("email", email);
                params.put("name", username);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }
    private void con(String b,String c,String d,String e,String f){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, constants.URL_REGISTER, response -> {
            progressDialog.dismiss();
            try {
                if(response.equals("Registered")){
                    Toast.makeText(getApplicationContext(),"Registered Successfully!", Toast.LENGTH_LONG).show();
                    //        Navigation
                    Intent Login = new Intent(Register.this, Login.class);
                    startActivity(Login);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Server not Responding", Toast.LENGTH_LONG).show();
                }

            }
            catch (Exception e1){
                Toast.makeText(getApplicationContext(),"Code Error!", Toast.LENGTH_LONG).show();
                e1.printStackTrace();
            }
        }, error -> {
            progressDialog.hide();
            Toast.makeText(getApplicationContext(),"Internet Connection Error!",Toast.LENGTH_SHORT).show();

        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("name",b);
                params.put("phone",c);
                params.put("address",d);
                params.put("email",e);
                params.put("password2",f);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}