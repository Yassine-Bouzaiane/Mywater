package tn.esprit.mywatertunisie.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.mywatertunisie.Adapters.RecyclerViewProduitAdapter;
import tn.esprit.mywatertunisie.Entities.Produit;
import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.SharedPrefManager;
import tn.esprit.mywatertunisie.URLs;

public class AdminGestionProdActivity extends AppCompatActivity {

    private RecyclerView produitRecyclerView;
    private List<Produit> listProduits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_gestion_produits);

        listProduits = new ArrayList<>();
        produitRecyclerView = findViewById(R.id.admin_produit_recyclerview);

//        RecyclerViewProduitsAdapter adminRecyclerViewProduitsAdapter
//                = new RecyclerViewProduitsAdapter(getApplicationContext(), listProduits);
        loadRecyclerViewData();

    }

    private void loadRecyclerViewData() {

        Log.e("Loading List Produits: ", "Loading Produits...");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_PROD, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {

//                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = new JSONArray(response);


                    for (int i = 0; i < array.length(); i++) {

                        JSONObject produitJson = array.getJSONObject(i);

                        Produit produit = new Produit(
                                produitJson.getInt("idProd"),
                                produitJson.getString("reference"),
                                produitJson.getString("nom"),
                                produitJson.getString("description"),
                                produitJson.getInt("prix"),
                                produitJson.getString("categorie"),
                                produitJson.getString("imageProd"),
                                produitJson.getInt("quantite"));
                        Log.e("PRODUIT>>>>", produit.toString());


                        listProduits.add(produit);
                        Log.e("PRODUITS >>>>", produit.toString());

                    }

                    produitRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    produitRecyclerView.setAdapter(new RecyclerViewProduitAdapter(getApplicationContext(), listProduits));

                } catch (JSONException e) {

                    Log.e("ERREUR List Produits >>", e.getMessage());
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

        Log.e("ListP after request>>>>", listProduits.toString());


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
