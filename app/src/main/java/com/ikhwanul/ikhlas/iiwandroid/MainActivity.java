package com.ikhwanul.ikhlas.iiwandroid;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ikhwanul.ikhlas.iiwandroid.api.response.AuthResponse;
import com.ikhwanul.ikhlas.iiwandroid.entities.User;
import com.ikhwanul.ikhlas.iiwandroid.fragment.BinaanKDMFragment;
import com.ikhwanul.ikhlas.iiwandroid.fragment.BinaanPerwakilanFragment;
import com.ikhwanul.ikhlas.iiwandroid.fragment.HistoryJamaahFragment;
import com.ikhwanul.ikhlas.iiwandroid.fragment.HomeFragment;
import com.ikhwanul.ikhlas.iiwandroid.fragment.JamaahFragment;
import com.ikhwanul.ikhlas.iiwandroid.fragment.KomisiPSCFragment;
import com.ikhwanul.ikhlas.iiwandroid.fragment.OnFragmentInteractionListener;
import com.ikhwanul.ikhlas.iiwandroid.fragment.ProfileFragment;
import com.ikhwanul.ikhlas.iiwandroid.utils.Session;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , OnFragmentInteractionListener {
    private Fragment fragmentContent;
    private NavigationView navigationView;
    private int selectedMenu;

    User dataUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataUser = (User) getIntent().getSerializableExtra("dataUser");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        FragmentTransaction frgManager = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        fragmentContent = new HomeFragment();
        if (dataUser != null){
            bundle.putSerializable("send", dataUser);
        }
        fragmentContent.setArguments(bundle);
        frgManager.replace(R.id.content_fragment, fragmentContent).commit();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        Menu m = navigationView.getMenu();
        if (selectedMenu != id){
            selectedMenu = id;
            if (id == R.id.nav_home) {
                setTitle("Home");
                fragmentContent = new HomeFragment();
            }else if (id == R.id.nav_commision) {
                boolean b= !m.findItem(R.id.nav_commission_vice).isVisible();
                m.findItem(R.id.nav_commission_vice).setVisible(b);
                m.findItem(R.id.nav_commission_paid_off).setVisible(b);
                m.findItem(R.id.nav_commision_PSC).setVisible(b);
                return false;
            }else if (id == R.id.nav_commision_PSC){
                setTitle("Komisi PSC");
                fragmentContent = KomisiPSCFragment.newInstance();
            }
            else if (id == R.id.nav_profile){
                setTitle("Profile");
                fragmentContent = ProfileFragment.newInstance();
            }else if (id == R.id.nav_jamaah) {
                boolean b = !m.findItem(R.id.nav_jamaah_data).isVisible();
                //setting submenus visible state
                m.findItem(R.id.nav_jamaah_data).setVisible(b);
                m.findItem(R.id.nav_jamaah_history).setVisible(b);
                return false;
            }else if(id == R.id.nav_jamaah_data){
                setTitle("Data Jamaah");
                fragmentContent = JamaahFragment.newInstance();
            }else if(id == R.id.nav_jamaah_history){
                setTitle("History Jamaah");
                fragmentContent = HistoryJamaahFragment.newInstance();
            }else if(id == R.id.nav_logout){
                if (dataUser != null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setCancelable(true);
                    builder.setTitle("Logout");
                    builder.setMessage("Yakin ingin keluar dari akun \n"+ dataUser.username);
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Session.with(MainActivity.this).clearLoginSession();
                                }
                            });
                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }else{
                    Session.with(MainActivity.this).clearLoginSession();
                }
            }
            FragmentManager frgManager = getSupportFragmentManager();
            Bundle bundle = new Bundle();
            if (dataUser != null){
                bundle.putSerializable("send", dataUser);
            }
            fragmentContent.setArguments(bundle);
            frgManager.beginTransaction().replace(R.id.content_fragment, fragmentContent).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
