package tn.esprit.mywatertunisie.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import tn.esprit.mywatertunisie.Entities.User;
import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.SharedPrefManager;
import tn.esprit.mywatertunisie.URLs;

public class DetailProfile extends AppCompatActivity {
    TextView nom, email, adresse;
    ImageView imageUser;
    String url_image_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_profile);

        User user = SharedPrefManager.getInstance(this).getUser();

        nom = findViewById(R.id.profile_nom);
        email = findViewById(R.id.profile_email);
        adresse = findViewById(R.id.profile_adresse);
        imageUser = findViewById(R.id.profile_image);
        url_image_user = URLs.URL_IMG + user.getImageUser();

        nom.setText(user.getNom());
        email.setText(user.getEmail());
        adresse.setText(user.getAdresse());
        Picasso.get().load(url_image_user).resize(200, 200).into(imageUser);


    }

    public void toModifierProfile(View view) {
        Intent intent = new Intent(DetailProfile.this, ProfilActivity.class);
        startActivity(intent);
    }
}
