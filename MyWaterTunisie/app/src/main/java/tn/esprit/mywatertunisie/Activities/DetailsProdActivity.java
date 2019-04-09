package tn.esprit.mywatertunisie.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import tn.esprit.mywatertunisie.Entities.Produit;
import tn.esprit.mywatertunisie.Entities.User;
import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.SharedPrefManager;
import tn.esprit.mywatertunisie.URLs;
import yalantis.com.sidemenu.interfaces.Resourceble;

public class DetailsProdActivity extends AppCompatActivity {

    Intent intent;
    int idProd;
    String url_prod_id;
    TextView tv_title, tv_description;
    ImageView img_imgProd;
    String url_image_prod;
    User user;
    int quantite = 666;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_prod);
        intent = getIntent();
        idProd = intent.getIntExtra("idProd", 0);
        url_prod_id = URLs.URL_PROD_BY_ID + idProd;
        tv_title = findViewById(R.id.tv_title);
        tv_description = findViewById(R.id.tv_description);
        img_imgProd = findViewById(R.id.image_tv_image);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_prod_id, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {

                    //         JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = new JSONArray(response);


                    JSONObject o = array.getJSONObject(0);
                    Produit produit = new Produit(
                            o.getInt("idProd"),
                            o.getString("reference"),
                            o.getString("nom"),
                            o.getString("description"),
                            o.getInt("prix"),
                            o.getString("categorie"),
                            o.getString("imageProd"),
                            o.getInt("quantite"));
                    Log.e("PRODUIT>>>>", produit.toString());


                    tv_title.setText(produit.getNom());
                    tv_description.setText(produit.getDescription());
                    url_image_prod = URLs.URL_IMG + produit.getImg();
                    Log.e("URL for img", url_image_prod);
                    Picasso.get().load(url_image_prod).resize(200, 200).into(img_imgProd);

                    Log.e("URL IMAGE >>>", produit.getImg());
                    Log.e("URL IMAGE >>>", url_image_prod);

                    Log.e("after picasso", ">>>>> Details Produit<<<<<");
                } catch (JSONException e) {

                    Log.e("ERREEEUUUR:>>>>>", e.getMessage());
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }


    public void addToCart(View view) {

        user = SharedPrefManager.getInstance(getApplicationContext()).getUser();

        StringRequest sr = new StringRequest(Request.Method.POST, URLs.URL_ADD_TO_CART,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("HttpClient", "success! response: " + response);
                        Log.e("Params Cart >>>", "UserID: "+String.valueOf(user.getId())+ "\n ProdID: "+String.valueOf(idProd));

                        Toast.makeText(getApplicationContext(), "Le produit « " + tv_title.getText() + " » a été ajouté au panier", Toast.LENGTH_SHORT).show();

                        //              startActivity(new Intent(getApplicationContext(), MainActivity.class));


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Params Cart >>>", "UserID: "+String.valueOf(user.getId())+ "\n ProdID: "+String.valueOf(idProd));
                        Log.e("ERREUR JSON>>>>>>", "error Detail Prod: " + error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("create_id_user", String.valueOf(user.getId()));
                params.put("create_id_prod", String.valueOf(idProd));
                params.put("create_quantite", String.valueOf(quantite));

                Log.e("Map", "Map Finished");

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(this);
        Log.e("Queue", "declaring rq");

        queue.add(sr);


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
//------------------------------------------------

/*        BoomMenuButton bmb = findViewById(R.id.bmb);

        switch (bmb.getPiecePlaceEnum().pieceNumber()) {
            case 1:
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.add_to_cart)
                        .normalTextRes(R.string.normalTextRes)
                        .subNormalTextRes(R.string.subTextRes);
                bmb.addBuilder(builder);
                bmb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), ProfilActivity.class);
                        startActivity(intent);
                    }
                });
                break;

            case 2:
                HamButton.Builder builder1 = new HamButton.Builder()
                        .normalImageRes(R.drawable.add_to_cart)
                        .normalTextRes(R.string.normalTextRes)
                        .subNormalTextRes(R.string.subTextRes);
                bmb.addBuilder(builder1);
                bmb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), ChantierActivity.class);
                        startActivity(intent);
                    }
                });
                break;

            case 3:
                HamButton.Builder builder2 = new HamButton.Builder()
                        .normalImageRes(R.drawable.add_to_cart)
                        .normalTextRes(R.string.normalTextRes)
                        .subNormalTextRes(R.string.subTextRes);
                bmb.addBuilder(builder2);
                bmb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        finish();
                        SharedPrefManager.getInstance(getApplicationContext()).logout();
                    }
                });
                break;

        }


for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder()
                    .normalImageRes(R.drawable.add_to_cart)
                    .normalTextRes(R.string.normalTextRes)
                    .subNormalTextRes(R.string.subTextRes);
            bmb.addBuilder(builder);
Log.e("BOOOOOMM",""+ bmb.getPiecePlaceEnum().pieceNumber());
        }
*/
////////////////////////////////////////////

/*
        LayoutInflater inflater = getLayoutInflater();

View drawerLayout = inflater.inflate(R.layout.drawer_layout, .......... );
        ViewAnimator viewAnimator = new ViewAnimator(DetailsProdActivity.this,
                new ArrayList<Resourceble>(),
                (LinearLayout) findViewById(R.id.left_drawer),
                contentFragment, drawerLayout);
        //to open menu you have to override ActionBarDrawerToggle method

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            super.onDrawerSlide(drawerView, slideOffset);
            if (slideOffset > 0.6 && viewAnimator.getLinearLayout().getChildCount() == 0)
                viewAnimator.showMenuContent();
        }
        public void onDrawerClosed(View view) {
            super.onDrawerClosed(view);
            viewAnimator.getLinearLayout().removeAllViews();
            viewAnimator.getLinearLayout().invalidate();
        }
*/
////////////////////////////////////////////
