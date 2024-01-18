package com.elib;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SharedPrefManager {
    private static SharedPrefManager instance;
    private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static final String KEY_USERNAME="username";
    private static final String KEY_USEREMAIL="email";
    private static Context ctx;
    private SharedPreferences sharedPreferences;

    private SharedPrefManager(Context context) {
        ctx = context;
        sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        return instance;
    }

    public  boolean userLogin(String username, String email){
//        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        editor.putString(KEY_USEREMAIL,email);
        editor.putString(KEY_USERNAME,username);

        editor.apply();
        return true;
    }
    public boolean isLoogedin(){
//        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USEREMAIL,null)!=null){
            return true;
        }
        return false;
    }

    public boolean logout(){
//        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        editor.clear();
        editor.apply();
        return true;
    }

    public  String getUsername(){
//        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME,"Guest Account");
    }
    public  String getEmail(){
//        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USEREMAIL,null);
    }


}