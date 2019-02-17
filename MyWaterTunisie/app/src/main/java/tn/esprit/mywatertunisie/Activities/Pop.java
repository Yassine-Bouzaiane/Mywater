package tn.esprit.mywatertunisie.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import tn.esprit.mywatertunisie.R;

public class Pop extends Activity {
    Intent intent;
    Double prix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popdevis);

        intent = getIntent();
        prix = intent.getDoubleExtra("prix",1.0d);

        TextView prixEditText =  findViewById(R.id.prixTextView) ;

        DecimalFormat df = new DecimalFormat("0.0");

        Log.e("PopUp prix ",prix+"");
        prixEditText.setText(df.format(prix)+" DT");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8),(int) (height*.6));
    }

    public void CallResp(View view) {
        Toast.makeText(this, "Appeler un Responsable", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+21622797193"));
        startActivity(intent);
    }
}