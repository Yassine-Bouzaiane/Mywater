package tn.esprit.mywatertunisie.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.SharedPrefManager;
import tn.esprit.mywatertunisie.URLs;

public class AdminDetailsUser extends AppCompatActivity {

    Intent intent;
    int idUser;
    String nomUser, emailUser, adresseUser, typeUser, imageUser;
    TextView userName, userEmail, userAdresse;
    ImageView userImage;
    String url_user_id, url_image_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_details_user);

        intent = getIntent();
        idUser = intent.getIntExtra("idUser", 0);
        nomUser = intent.getStringExtra("nomUser");
        emailUser = intent.getStringExtra("emailUser");
        adresseUser = intent.getStringExtra("adresseUser");
        imageUser = intent.getStringExtra("imageUser");
        typeUser = intent.getStringExtra("typeUser");
//        url_update_user = URLs.URL_UPDATE_USERS_BY_ID+idUser;
        userName = findViewById(R.id.admin_prof_nom);
        userEmail = findViewById(R.id.admin_prof_email);
        userAdresse = findViewById(R.id.admin_prof_adresse);
        userImage = findViewById(R.id.admin_prof_image);

        userName.setText(nomUser);
        userEmail.setText(emailUser);
        userAdresse.setText(adresseUser);
        url_image_user = URLs.URL_IMG + imageUser;

        Log.e("URL for img", url_image_user);
        Picasso.get().load(url_image_user).resize(180, 180).into(userImage);


    }

    public void addAsAdmin(View view) {
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
