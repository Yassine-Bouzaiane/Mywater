package tn.esprit.mywatertunisie.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.SharedPrefManager;

public class AdminMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        findViewById(R.id.gestion_Users).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminGestionUserActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.gestion_Prod_Eau).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AdminGestionProdActivity.class));

            }
        });

        findViewById(R.id.gestion_Electricite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminGestionElectriciteActivity.class);
                startActivity(intent);

            }
        });


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

