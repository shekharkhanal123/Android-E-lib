package com.elib;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OTP extends AppCompatActivity {


    EditText otp;
    Button con;
    TextView msg,resend;
    ProgressDialog progressDialog;

    private boolean resendenable= false;
    private int resetTime= 60;


    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        //        get value from register activity

        String username = getIntent().getStringExtra("keyname");
        String phone = getIntent().getStringExtra("keyphone");
        String address = getIntent().getStringExtra("keyadd");
        String email = getIntent().getStringExtra("keyemail");
        String cpass = getIntent().getStringExtra("keycpass");

        otp = findViewById(R.id.inotp);
        con= findViewById(R.id.conbtn);
        msg = findViewById(R.id.mess);
        resend = findViewById(R.id.resendbtn);
        progressDialog = new ProgressDialog(this);

        msg.setText(email);

        String OTP =otp.getText().toString().trim();

        startcountdown();

        resend.setOnClickListener(v -> {
            if(resendenable){
                startcountdown();
                resend(email,username);
            }
        });

        con.setOnClickListener(v -> {
            progressDialog.setMessage("Loading...");
            progressDialog.show();
            con(OTP,username,phone,address,email,cpass);

        });
    }
    private void startcountdown(){

        resendenable= false;
        resend.setTextColor(getResources().getColor(R.color.def));
        new CountDownTimer(resetTime * 1000L,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                resend.setText("Send Again("+(millisUntilFinished/1000)+")");
            }

            @Override
            public void onFinish() {
                resendenable= true;
                resend.setText("Send Again");
                resend.setTextColor(getResources().getColor(R.color.btncolor));

            }
        }.start();
    }
    private void resend(String x ,String y){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,constants.URL_MAILER, response -> {
            progressDialog.dismiss();
            try {
                    Toast.makeText(getApplicationContext(),"OTP is sent to your email!",Toast.LENGTH_LONG).show();
            }
            catch (Exception e){
                Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }, error -> {
            progressDialog.hide();
            Toast.makeText(getApplicationContext(),"Internet  Error!",Toast.LENGTH_SHORT).show();

        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email", x);
                params.put("name", y);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }
    private void con(String a,String b,String c,String d,String e,String f){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, constants.URL_REGISTER, response -> {
            progressDialog.dismiss();
            try {
                if(response.equals("Registered")){
                    Toast.makeText(getApplicationContext(),"Registered Successfully!", Toast.LENGTH_LONG).show();
                    //        Navigation
                    Intent Login = new Intent(OTP.this, Login.class);
                    startActivity(Login);
                }
                else {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                }

            }
            catch (JSONException e1){
                Toast.makeText(getApplicationContext(),"Server isn't Responding", Toast.LENGTH_LONG).show();
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
                params.put("cotp",a);
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