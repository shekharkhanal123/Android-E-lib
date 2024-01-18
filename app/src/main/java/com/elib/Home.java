package com.elib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    SharedPreferences sharedPreferences;
    private DrawerLayout drawerLayout;
    private TextView name,email;
    private  LinearLayout profile;
    MenuItem login;
    Toolbar tool;
    NavigationView navigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tool = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        login = navigationView.getMenu().findItem(R.id.nav_log);
        profile = navigationView.getHeaderView(0).findViewById(R.id.pro_link);
        navigationView.setNavigationItemSelectedListener(this);

        profile.setOnClickListener(v -> {
            Intent pnav = new Intent(Home.this , ProfileActivity.class);
            startActivity(pnav);
            drawerLayout.closeDrawer(GravityCompat.START);
        });

        navigationView.bringToFront();
        setSupportActionBar(tool);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,tool, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(!SharedPrefManager.getInstance(this).isLoogedin()){
            login.setTitle("Login");
        }

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        name = navigationView.getHeaderView(0).findViewById(R.id.name);
        email = navigationView.getHeaderView(0).findViewById(R.id.email);

        name.setText(SharedPrefManager.getInstance(this).getUsername());
        email.setText(SharedPrefManager.getInstance(this).getEmail());



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {

        if(menuitem.getItemId()==R.id.nav_home){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new HomeFragment()).commit();
        }
        else if(menuitem.getItemId()==R.id.nav_settings){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new SettingFragment()).commit();
        }
        else if(menuitem.getItemId()==R.id.nav_about){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new AboutFragment()).commit();
        }
        else if (menuitem.getItemId()==R.id.nav_log){
            if(!SharedPrefManager.getInstance(this).isLoogedin()){
                Intent nav= new Intent(this,Login.class);
                startActivity(nav);
                finish();
            }
            else {
                logout(this);
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    private  void logout(Home home){
        AlertDialog.Builder builder = new AlertDialog.Builder(home);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout ? ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    SharedPrefManager.getInstance(Home.this).logout();
                    Toast.makeText(getApplicationContext(), "Logout Sucessfull!", Toast.LENGTH_SHORT).show();
                    Intent nav= new Intent(Home.this, MainActivity.class);
                    startActivity(nav);
                    finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                navigationView.setCheckedItem(R.id.nav_home);
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}