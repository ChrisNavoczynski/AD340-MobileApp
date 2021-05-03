package com.example.helloworld;

import android.os.Bundle;
import android.view.MenuItem;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

public class SignInActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Variable from User Input
    private String name;
    private String age;
    private String asId;
    private String seekId;
    private String employ;
    private String describe;
    //Variables after appending
    private String sName;
    private String sAge;
    private String sAsId;
    private String sSeekId;
    private String sEmploy;
    private String sDescribe;
    //Other Variable calls
    private FragmentManager fragManage;
    private DrawerLayout drawer;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        fragManage = getSupportFragmentManager();

        StringBuilder pName = new StringBuilder(getString(R.string.thanks_signed_in));
        StringBuilder pAge = new StringBuilder(getString(R.string.show_age));
        StringBuilder pAsId = new StringBuilder(getString(R.string.identify_as));
        StringBuilder pSeekId = new StringBuilder(getString(R.string.seeking_id));
        StringBuilder pEmploy = new StringBuilder(getString(R.string.occupation_stat));
        StringBuilder pDescribe = new StringBuilder();
        intent = getIntent();
        Bundle b = intent.getExtras();

        if (b != null) {
            if (b.containsKey(Constants.PROFILE_NAME)) {
                name = b.getString(Constants.PROFILE_NAME);
            }
            if (b.containsKey(Constants.AGE)) {
                age = b.getString(Constants.AGE);
            }
            if (b.containsKey(Constants.ID_AS)) {
                asId = b.getString(Constants.ID_AS);
            }
            if (b.containsKey(Constants.SEEKING)) {
                seekId=b.getString(Constants.SEEKING);
            }
            if (b.containsKey(Constants.OCCUPATION)) {
                employ = b.getString(Constants.OCCUPATION);
            }
            if (b.containsKey(Constants.ABOUT_ME)) {
                describe = b.getString(Constants.ABOUT_ME);
            }
        }
        pName.append(name).append("!\n");
        pAge.append(age).append("\n");
        pAsId.append(asId).append(" ");
        pSeekId.append(seekId).append("\n");
        pEmploy.append(employ).append("\n");
        pDescribe.append(describe).append("\n");

        sName = pName.toString();
        sAge = pAge.toString();
        sAsId = pAsId.toString();
        sSeekId = pSeekId.toString();
        sEmploy = pEmploy.toString();
        sDescribe = pDescribe.toString();

        ProfileFragment fragment = new ProfileFragment();
        fragment.setAttachSignIn(new AttachSignIn(sName, sAge, sAsId, sSeekId,
                sEmploy, sDescribe));
        FragmentTransaction transact = fragManage.beginTransaction();
        transact.add(R.id.frag_profile, fragment, "frag1");

        //goBackBtn.setOnClickListener(view -> finish());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_profile:
                ProfileFragment fragment = new ProfileFragment();
                fragment.setAttachSignIn(new AttachSignIn(sName, sAge, sAsId, sSeekId, sEmploy, sDescribe));
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_profile,
                        fragment).commit();
                break;

            case R.id.nav_matches:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_profile,
                        new MatchesFragment()).commit();
                break;

            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.frag_profile,
                        new SettingsFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
       if (drawer.isDrawerOpen(GravityCompat.START)) {
           drawer.closeDrawer(GravityCompat.START);
       } else {
           super.onBackPressed();
       }
    }

    public static class AttachSignIn {
        String name;
        String age;
        String asId;
        String seekId;
        String employ;
        String describe;

        AttachSignIn(String name, String age, String asId, String seekId,
                     String employ, String describe) {
            this.name = name;
            this.age = age;
            this.asId = asId;
            this.seekId = seekId;
            this.employ = employ;
            this.describe = describe;
        }
    }
}
