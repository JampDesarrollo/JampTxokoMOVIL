package com.example.a2dam.jamp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import messageuserbean.UserBean;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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

        UserBean user = (UserBean) getIntent().getExtras().getSerializable("Usuario");
        String date = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(user.getLastAccess());
        tfFullName.setText(user.getFullname());
        tfLogin.setText(user.getLogin());
        tfLastAcces.setText("Ultimo acceso: " + date);
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
        int id = item.getItemId();
        if (id == R.id.action_logOut) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Method that create nav_header and options
     *
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Toast toast = Toast.makeText(getApplicationContext(),
                "Boton desactivado en este momento", Toast.LENGTH_SHORT);
        ;

        if (id == R.id.nav_camera) {
            toast.show();
        } else if (id == R.id.nav_gallery) {
            toast.show();
        } else if (id == R.id.nav_manage) {
            toast.show();
        } else if (id == R.id.nav_share) {
            toast.show();
        } else if (id == R.id.nav_send) {
            toast.show();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
