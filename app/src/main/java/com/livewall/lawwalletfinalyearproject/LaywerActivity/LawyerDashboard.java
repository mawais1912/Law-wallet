package com.livewall.lawwalletfinalyearproject.LaywerActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.livewall.lawwalletfinalyearproject.Activity.SigninActivity;
import com.livewall.lawwalletfinalyearproject.AdminActivity.Fragment.listofUserFragmenttoAdmin;
import com.livewall.lawwalletfinalyearproject.LaywerActivity.Fragment.UpdateLawyerprofileFragment;
import com.livewall.lawwalletfinalyearproject.LaywerActivity.Fragment.ViewBookAppointmentsFragment;
import com.livewall.lawwalletfinalyearproject.LaywerActivity.Fragment.ViewusertochatFragment;
import com.livewall.lawwalletfinalyearproject.R;
import com.livewall.lawwalletfinalyearproject.UserActivity.LawDiraryDetailsFragment;

public class LawyerDashboard extends AppCompatActivity {
    ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lawyer_dashboard);
        LoadHomeFragmentData();
        Navdrawer();
    }
    private void LoadHomeFragmentData(){
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.framelayoutid,new UpdateLawyerprofileFragment())
                .commit();
    }
    private void Navdrawer(){
        NavigationView navigationView;
        DrawerLayout drawerLayout;
        Toolbar toolbar=findViewById(R.id.toolbarid);
        setSupportActionBar(toolbar);
        navigationView=findViewById(R.id.nav_viewid);
        drawerLayout=findViewById(R.id.drawerlayoutid);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.lawdiarayID:
                        getSupportFragmentManager().
                                beginTransaction().
                                replace(R.id.framelayoutid,new LawDiraryDetailsFragment())
                                .commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.updatelawyerpro:
                        getSupportFragmentManager().
                                beginTransaction().
                                replace(R.id.framelayoutid,new UpdateLawyerprofileFragment())
                                .commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.viewchatID:
                        getSupportFragmentManager().
                                beginTransaction().
                                replace(R.id.framelayoutid,new ViewusertochatFragment())
                                .commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.viewBookingID:
                        getSupportFragmentManager().
                                beginTransaction().
                                replace(R.id.framelayoutid,new ViewBookAppointmentsFragment())
                                .commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.lisTofuser:
                        getSupportFragmentManager().
                                beginTransaction().
                                replace(R.id.framelayoutid,new listofUserFragmenttoAdmin())
                                .commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.signout:
                        FirebaseAuth.getInstance().signOut();

                        Toast.makeText(LawyerDashboard.this, "Signout", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), SigninActivity.class));
                        break;
                }
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}