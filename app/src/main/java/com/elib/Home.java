package com.elib;

import static com.elib.R.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    Toolbar tool;
    NavigationView navigationView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_home);

        tool = findViewById(id.toolbar);
        drawerLayout = findViewById(id.drawer_layout);
        navigationView = findViewById(id.nav_view);



        navigationView.setNavigationItemSelectedListener(this);

        navigationView.bringToFront();
        setSupportActionBar(tool);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,tool, string.navigation_drawer_open, string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(id.fragment_layout,new HomeFragment()).commit();
            navigationView.setCheckedItem(id.nav_home);
        }
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
        switch (menuitem.getItemId()){

            case id.nav_home:

                getSupportFragmentManager().beginTransaction().replace(id.fragment_layout,new HomeFragment()).commit();
                break;

            case id.nav_logout:

                Toast.makeText(this, "Logout successfull!", Toast.LENGTH_SHORT).show();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + menuitem.getItemId());
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
}