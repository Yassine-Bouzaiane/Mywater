package tn.esprit.mywatertunisie.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.SharedPrefManager;

public class DashboardActivity extends AppCompatActivity {
    Button panier, produits, profile, devis, contact, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        panier = findViewById(R.id.panierBtn);
        produits = findViewById(R.id.produitsBtn);
        profile = findViewById(R.id.profileBtn);
        devis = findViewById(R.id.devisBtn);
        contact = findViewById(R.id.contactBtn);
        logout = findViewById(R.id.logoutBtn);

        panier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Panier", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ShowCartActivity.class));

            }
        });

        produits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Produits", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Profile", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ProfilActivity.class));

            }
        });

        devis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Créez votre Devis pour Piscine", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), DevisPiscineActivity.class));

            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Appeler un Responsable", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+21622797193"));
                startActivity(intent);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Déconnexion", Toast.LENGTH_SHORT).show();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
            }
        });



    }
}
