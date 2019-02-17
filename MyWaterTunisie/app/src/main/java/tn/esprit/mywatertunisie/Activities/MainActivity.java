package tn.esprit.mywatertunisie.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import tn.esprit.mywatertunisie.Adapters.FragmentAdapter;
import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.SharedPrefManager;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private static ViewPager viewPager;
    private static FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mDrawerLayout = findViewById(R.id.drawer_layout);

/*
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }


                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );
*/

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ActionBar actionbar = getSupportActionBar();
//        actionbar.setDisplayHomeAsUpEnabled(true);
//        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        viewPager = findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        adapter = new FragmentAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart:
                Toast.makeText(this, "Panier", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ShowCartActivity.class));
                return true;
            case R.id.action_Profile:
                Toast.makeText(this, "Profil", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ProfilActivity.class));
                return true;

            case R.id.action_devis_piscine:
                Toast.makeText(this, "Créez votre Devis pour Piscine", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), DevisPiscineActivity.class));
                return true;

            case R.id.callResp:
                Toast.makeText(this, "Appeler un Responsable", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+21622797193"));
                startActivity(intent);
                return true;


            case R.id.menuLogout:
                Toast.makeText(this, "Déconnexion", Toast.LENGTH_SHORT).show();
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
