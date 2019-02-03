package com.example.a2dam.jamp.models;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
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
import android.widget.Toast;

import com.example.a2dam.jamp.R;
import com.example.a2dam.jamp.dataClasses.UserBean;
import com.example.a2dam.jamp.dialogs.Dialog_LogOut;
import com.example.a2dam.jamp.fragments.ChangePasswordFragmentController;
import com.example.a2dam.jamp.fragments.EventFragmentController;
import com.example.a2dam.jamp.fragments.ExpenseFragmentController;
import com.example.a2dam.jamp.fragments.ProductFragmentController;
import com.example.a2dam.jamp.fragments.TelephoneFragmentController;

import java.text.SimpleDateFormat;

/**
 * Class that controller PrincipalActivityController
 * @author Julen
 */
public class PrincipalActivityController extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    /**
     * Full name TextView
     */
    private TextView tfFullName;

    /**
     * Login TextView
     */
    private TextView tfLogin;

    /**
     * LastAcces TextView
     */
    private TextView tfLastAcces;

    private DrawerLayout drawerLayout;

    private NavigationView navigationView;

    public static UserBean publicUser;
    public static UserBean getPublicUser() {
        return publicUser;
    }
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

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView = navigationView.getHeaderView(0);

        tfFullName = hView.findViewById(R.id.tfFullName);
        tfLogin = hView.findViewById(R.id.tfLogin);
        tfLastAcces = hView.findViewById(R.id.tfLastConnection);

        //estas dos lineas se borran en la version con servidor
        MainActivityController main=new MainActivityController();
        tfLogin.setText(main.getUsuario());
        /*
        try {
            publicUser = (UserBean) getIntent().getExtras().getSerializable("Usuario");
            tfFullName.setText(publicUser.getFullname());
            tfLogin.setText(publicUser.getLogin());
            String date = new SimpleDateFormat("HH:mm dd/MM/yyyy").format(publicUser.getLastAccess());
            tfLastAcces.setText("Ultimo acceso: " + date);
        }catch (NullPointerException e){
            Toast.makeText(this,this.getResources().getString(R.string.null_pointer_exception_error), Toast.LENGTH_LONG).show();
        }*/

        /*si el savedInstanceState es igual a null significa que es la primera vez que se carga la actividad entonces llama al metodo setFragment() y le manda el fragmento que queremos que se muestre, en este caso
        es la actividad de productos (la 0), si es distinto de null significa que la actividad ya estaba cargada y no queremos que nos vuelva a cargar el fragment de productos sino que queremos quedarnos en el
        fragmento que ya estabamos*/
        if(savedInstanceState == null) {
            setFragment(0);
            navigationView.getMenu().getItem(0).setChecked(true);
        }

    }


    /**
     * Method to close the nav header if is opened and
     * close the PrincipalActivityController if nav_header is closed with back button
     */
    @Override
    public void onBackPressed() {
        int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if (backStackEntryCount == 1) {
            DialogFragment dialogo =new Dialog_LogOut();
            dialogo.show(getSupportFragmentManager(),"Dialog_LogOut");
        }else{
            FragmentManager manager = getSupportFragmentManager();
            if(manager.getBackStackEntryCount() > 1) {
                super.onBackPressed();
                Fragment currentFragment =manager.findFragmentById(R.id.fragment);
                if(currentFragment instanceof ProductFragmentController){
                    navigationView.getMenu().getItem(0).setChecked(true);
                }else if(currentFragment instanceof EventFragmentController){
                    navigationView.getMenu().getItem(1).setChecked(true);
                }else if(currentFragment instanceof TelephoneFragmentController){
                    navigationView.getMenu().getItem(2).setChecked(true);
                }else if(currentFragment instanceof ExpenseFragmentController){
                    navigationView.getMenu().getItem(3).setChecked(true);
                }else if(currentFragment instanceof ChangePasswordFragmentController){
                    navigationView.getMenu().getItem(4).setChecked(true);
                }
            }
        }
    }

    /**
     * Method that create menuOptions in PrincipalActivityController
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
     * Method that logOut and close PrincipalActivityController
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
                Intent iniciarSesion = new Intent(PrincipalActivityController.this, MainActivityController.class);
                startActivity(iniciarSesion);
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
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction;
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (position) {
            case 0:
                ProductFragmentController productFragmentController = new ProductFragmentController();
                fragmentTransaction.replace(R.id.fragment, productFragmentController);
                break;
            case 1:
                EventFragmentController eventFragmentController = new EventFragmentController();
                fragmentTransaction.replace(R.id.fragment, eventFragmentController);
                break;
            case 2:
                TelephoneFragmentController telephonFragment = new TelephoneFragmentController();
                fragmentTransaction.replace(R.id.fragment, telephonFragment);
                break;
            case 3:
                ExpenseFragmentController expenseFragmentController = new ExpenseFragmentController();
                fragmentTransaction.replace(R.id.fragment, expenseFragmentController);
                break;
            case 4:
                ChangePasswordFragmentController changePasswordFragmentController = new ChangePasswordFragmentController();
                fragmentTransaction.replace(R.id.fragment, changePasswordFragmentController);
                break;
        }
        //Hace que los fragment se añadan a la cola de ejecucion (es para cuando cliquemos el boton de atras no te cierre el activity y te muestre el de login sino que te vaya al anterior fragment)
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
