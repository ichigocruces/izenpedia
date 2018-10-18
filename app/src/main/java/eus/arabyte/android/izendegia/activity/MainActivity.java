package eus.arabyte.android.izendegia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import eus.arabyte.android.izendegia.R;
import eus.arabyte.android.izendegia.activity.fragments.AboutFragment;
import eus.arabyte.android.izendegia.activity.fragments.ArabyteFragment;
import eus.arabyte.android.izendegia.activity.fragments.BoysFragment;
import eus.arabyte.android.izendegia.activity.fragments.FavsFragment;
import eus.arabyte.android.izendegia.activity.fragments.GirlsFragment;
import eus.arabyte.android.izendegia.activity.fragments.PopularFragment;
import eus.arabyte.android.izendegia.activity.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // TODO: recoger la lista por defecto de las preferencias
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, new PopularFragment());
        ft.commit();
    }

    /**
     * Controlamos si se pulsa el boton de atras:
     * <ul>
     * <li>Si esta el menu de navegacion abierto, lo cerramos</li>
     * <li>Si no esta abierto, se pide confirmacion para salir (double click)</li>
     * </ul>
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            // salir de la app
            if (exit) {
                finish();
            } else {
                Toast.makeText(this, R.string.alert_close_message,
                        Toast.LENGTH_SHORT).show();
                exit = true;
                new CountDownTimer(3000, 1000) {

                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        exit = false;
                    }
                }.start();
            }
        }
    }

    /**
     * Cargamos el fragment que corresponda en funcion de item
     *
     * @param item MenuItem
     * @return boolean
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //creating fragment object
        Fragment fragment = null;

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_popular) {
            fragment = new PopularFragment();
        } else if (id == R.id.nav_boys) {
            fragment = new BoysFragment();
        } else if (id == R.id.nav_girls) {
            fragment = new GirlsFragment();
        } else if (id == R.id.nav_favs) {
            fragment = new FavsFragment();
        } else if (id == R.id.nav_settings) {
            fragment = new SettingsFragment();
        } else if (id == R.id.nav_about) {
            fragment = new AboutFragment();
//        } else if (id == R.id.nav_arabyte) {
//            fragment = new ArabyteFragment();
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * Restart the activity to reload the language
     */
    public void restartActivity() {
        finish();
        startActivity(getIntent());
    }

    /**
     * Llama al super para forzar que invoque al fragment
     *
     * @param requestCode int
     * @param resultCode  int
     * @param data        Intent
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

}
