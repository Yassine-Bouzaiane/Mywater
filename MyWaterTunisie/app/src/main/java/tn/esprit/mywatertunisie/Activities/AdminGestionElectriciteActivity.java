package tn.esprit.mywatertunisie.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.SharedPrefManager;

public class AdminGestionElectriciteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_gestion_electricite);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admin, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.a_Logout:
                Toast.makeText(this, "User Logged Out", Toast.LENGTH_SHORT).show();
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
                break;

        }
        return true;
    }

}
