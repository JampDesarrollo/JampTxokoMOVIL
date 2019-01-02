package com.example.a2dam.jamp.model;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.fragments.ChangePasswordFragment;
import com.example.a2dam.jamp.fragments.EventFragment;
import com.example.a2dam.jamp.fragments.ExpenseFragment;
import com.example.a2dam.jamp.fragments.ProductFragment;
import com.example.a2dam.jamp.fragments.TelephonFragment;

/**
 * Class that controller PrincipalActivity
 * @author Julen
 */
public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    /**
     * Full name TextView
     */
    TextView tfFullName;
    /**
     * Login TextView
     */
    TextView tfLogin;
    /**
     * LastAcces TextView
     */
    TextView tfLastAcces;

    DrawerLayout drawerLayout;


    /**
     * Method that create Principal Activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView = navigationView.getHeaderView(0);

        tfFullName = hView.findViewById(R.id.tfFullName);
        tfLogin = hView.findViewById(R.id.tfLogin);
        tfLastAcces = hView.findViewById(R.id.tfLastConnection);
/*
        UserBean user = (UserBean) getIntent().getExtras().getSerializable("Usuario");
        String date = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(user.getLastAccess());
        tfFullName.setText(user.getFullname());
        tfLogin.setText(user.getLogin());
        tfLastAcces.setText("Ultimo acceso: " + date);*/

//        setupNavigationDrawerContent(navigationView);

        setFragment(0);
    }


    /**
     * Method to close the nav header if is opened and
     * close the PrincipalActivity if nav_header is closed with back button
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Method that create menuOptions in PrincipalActivity
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    /**
     * Method that logOut and close PrincipalActivity
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_logOut:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Method that create nav_header and options
     *
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_product) {
            setFragment(0);
        } else if (id == R.id.nav_event) {
            setFragment(1);
        } else if (id == R.id.nav_telephon) {
            setFragment(2);
        } else if (id == R.id.nav_outgoings) {
            setFragment(3);
        } else if (id== R.id.nav_changepassword){
            setFragment(4);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFragment(int position) {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        switch (position) {
            case 0:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                ProductFragment productFragment = new ProductFragment();
                fragmentTransaction.replace(R.id.fragment, productFragment);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                EventFragment eventFragment = new EventFragment();
                fragmentTransaction.replace(R.id.fragment, eventFragment);
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                TelephonFragment telephonFragment = new TelephonFragment();
                fragmentTransaction.replace(R.id.fragment, telephonFragment);
                fragmentTransaction.commit();
                break;
            case 3:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                ExpenseFragment expenseFragment = new ExpenseFragment();
                fragmentTransaction.replace(R.id.fragment, expenseFragment);
                fragmentTransaction.commit();
                break;
            case 4:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                ChangePasswordFragment changePasswordFragment = new ChangePasswordFragment();
                fragmentTransaction.replace(R.id.fragment, changePasswordFragment);
                fragmentTransaction.commit();
                break;
        }
    }

}
