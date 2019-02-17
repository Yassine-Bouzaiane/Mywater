package tn.esprit.mywatertunisie.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.SharedPrefManager;


public class DevisPiscineActivity extends AppCompatActivity {
    Spinner styleSpinner;
    Spinner typeSpinner;
    Double prix = 5.0d;
    EditText edit_hauteur;
    EditText edit_largeur;
    Button popUpDevisButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devis_piscine);

        edit_largeur = findViewById(R.id.edit_largeur);
        edit_hauteur = findViewById(R.id.edit_hauteur);
        styleSpinner = findViewById(R.id.styleSpinner);
        popUpDevisButton = findViewById(R.id.popUpDevisButton);

///////////////////////////////////// Style piscine //////////////////////////////////

        List<String> stylePiscine = new ArrayList<String>();
        stylePiscine.add("Romain");
        stylePiscine.add("Réctangle");
        stylePiscine.add("Forme de Rein");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stylePiscine);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        styleSpinner.setAdapter(dataAdapter);

///////////////////////////////////// Type piscine //////////////////////////////////
        typeSpinner = findViewById(R.id.typeSpinner);

        List<String> typePiscine = new ArrayList<String>();
        typePiscine.add("Skimmer");
        typePiscine.add("Débordement");

        ArrayAdapter<String> dataTypeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typePiscine);

        // Drop down layout style - list view with radio button
        dataTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        typeSpinner.setAdapter(dataTypeAdapter);

        // initialisation edit_Button
        popUpDevisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double l = Double.valueOf(edit_hauteur.getText().toString()).doubleValue();
                Double h = Double.valueOf(edit_largeur.getText().toString()).doubleValue();
                Log.e("Longeur", l + "");
                Log.e("Longeur", h + "");
                Double res = l * h;
                Log.e("res", res + "");

/////////////////////////IF////////
                if (typeSpinner.getSelectedItem().equals("Skimmer")) {
                    Double res2 = res * 1300;
                    Log.e("res2", res2 + "");
                    if (styleSpinner.getSelectedItem().equals("Romain")) {
                        prix = res2 * 1.15;
                    }
                    if (styleSpinner.getSelectedItem().equals("Réctangle")) {
                        prix = res2 * 1.1;
                    }
                    if (styleSpinner.getSelectedItem().equals("Forme de Rein")) {
                        prix = res2 * 1.2;
                    }
                }
                if (typeSpinner.getSelectedItem().equals("Débordement")) {
                    Double res3 = res * 1600;
                    if (styleSpinner.getSelectedItem().equals("Romain")) {
                        prix = res3 * 1.15;
                    }
                    if (styleSpinner.getSelectedItem().equals("Réctangle")) {
                        prix = res3 * 1.1;
                    }
                    if (styleSpinner.getSelectedItem().equals("Forme de Rein")) {
                        prix = res3 * 1.2;
                    }
                }


/////////////////////////IF////////

                Intent intent = new Intent(DevisPiscineActivity.this, Pop.class);

                //     String p = String.valueOf(prix);
                intent.putExtra("prix", prix);
                startActivity(intent);
            }
        });

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
                break;
            case R.id.action_Profile:
                Toast.makeText(this, "Profil", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), ProfilActivity.class));
                break;

            case R.id.action_devis_piscine:
                Toast.makeText(this, "Créez votre Devis pour Piscine", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), DevisPiscineActivity.class));
                break;
            case R.id.callResp:
                Toast.makeText(this, "Appeler un Responsable", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+21622797193"));
                startActivity(intent);
                break;
            case R.id.menuLogout:
                Toast.makeText(this, "Déconnexion", Toast.LENGTH_SHORT).show();
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
                break;

        }
        return true;
    }

}