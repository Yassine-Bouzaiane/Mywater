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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import tn.esprit.mywatertunisie.Entities.User;
import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.SharedPrefManager;
import tn.esprit.mywatertunisie.URLs;


public class ProfilActivity extends AppCompatActivity {
    ImageView imageUser_profil;
    EditText textViewName, textViewEmail, textViewAddress, textViewPassword;
    String url_image_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);


        textViewPassword = findViewById(R.id.textViewPassword);
        textViewName =  findViewById(R.id.textViewName);
        textViewEmail =  findViewById(R.id.textViewEmail);
        textViewAddress =  findViewById(R.id.textViewAdresse);
        imageUser_profil = findViewById(R.id.image_user_profil);

        //getting the current user
        User user = SharedPrefManager.getInstance(this).getUser();

        //setting the values to the textviews
        textViewPassword.setText(user.getPassword());
        textViewName.setText(user.getNom());
        textViewEmail.setText(user.getEmail());
        textViewAddress.setText(user.getAdresse());

        url_image_user = URLs.URL_IMG + user.getImageUser();

        Log.e("URL for img", url_image_user);
        Picasso.get().load(url_image_user).resize(200, 200).into(imageUser_profil);

        //when the user presses logout button
        //calling the logout method
        findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                SharedPrefManager.getInstance(getApplicationContext()).logout();
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


}