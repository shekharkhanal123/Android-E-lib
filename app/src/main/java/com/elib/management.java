package com.elib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class management extends AppCompatActivity {
    ImageView backbtn;
    TableLayout tableLayout;
    TextView t1,t2,t3,t4,empty;
    CheckBox chk;
    TableRow tr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);
        backbtn= findViewById(R.id.backbtn);
        tableLayout= (TableLayout) findViewById(R.id.man_table);

        backbtn.setOnClickListener(v -> {
            finish();
        });
        StringRequest stringRequest = new StringRequest(Request.Method.POST, constants.URL_FETCH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            if(response.equals("Empty")){
                                Log.i("empty", "here");
                                empty = new TextView(management.this);
                                empty.setText("No Record Found");
                                empty.setTextColor(Color.GRAY);
                                empty.setGravity(Gravity.CENTER);
                                empty.setPadding(0,10,0,0);

                                tableLayout.addView(empty);
                            }else{
                                Log.i("empty", "not");
                                JSONArray array = new JSONArray(response);
                                for(int i=0; i< array.length(); i++){
                                    JSONObject obj = array.getJSONObject(i);
                                    String sem = obj.getString("Class");
                                    String name = obj.getString("name");
                                    String pub = obj.getString("publication");
                                    String status = obj.getString("status");
                                    Integer id = obj.getInt("id");
                                    addrow(sem, name, pub, status,id);
                                }

                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "Internet Connection Error!" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("tname", "Management");
                params.put("class", "Class");
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
    private void addrow(String s,String n,String p,String a,Integer i) {


        tr= new TableRow(this);
        t1= new TextView(this);
        t2= new TextView(this);
        t3= new TextView(this);
        t4= new TextView(this);
        chk= new CheckBox(this);

        t1.setText(s);
        t1.setGravity(Gravity.CENTER);
        tr.addView(t1);

        t2.setText(n);
        t2.setGravity(Gravity.CENTER);
        tr.addView(t2);

        t3.setText(p);
        t3.setGravity(Gravity.CENTER);
        tr.addView(t3);

        t4.setText(a);
        t4.setGravity(Gravity.CENTER);
        tr.addView(t4);

        chk.setGravity(Gravity.CENTER);
        chk.setId(i);
        tr.addView(chk);

        tableLayout.addView(tr);

    }
}