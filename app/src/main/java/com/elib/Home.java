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
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    SharedPreferences sharedPreferences;
    private DrawerLayout drawerLayout;
    TextView name,email;
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
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        sharedPreferences=getSharedPreferences("user_info",MODE_PRIVATE);

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.bringToFront();
        setSupportActionBar(tool);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,tool, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout,new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }


    @SuppressLint("NonConstantResourceId")
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
        else if (menuitem.getItemId()==R.id.nav_logout){
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.clear();
            editor.apply();
            logout(Home.this);
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
                Toast.makeText(getApplicationContext(), "Logout Sucessfull!", Toast.LENGTH_SHORT).show();

                Intent logout = new Intent(Home.this, MainActivity.class);
                startActivity(logout);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();

    }
}