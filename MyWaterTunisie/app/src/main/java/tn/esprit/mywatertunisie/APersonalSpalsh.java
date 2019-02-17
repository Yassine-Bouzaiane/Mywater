package tn.esprit.mywatertunisie;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lal.adhish.gifprogressbar.GifView;
import tn.esprit.mywatertunisie.Activities.LoginActivity;

public class APersonalSpalsh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apersonal_spalsh);
        GifView pGif =  findViewById(R.id.progressBar);
        pGif.setImageResource(R.drawable.melting);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(APersonalSpalsh.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);

    }
}
