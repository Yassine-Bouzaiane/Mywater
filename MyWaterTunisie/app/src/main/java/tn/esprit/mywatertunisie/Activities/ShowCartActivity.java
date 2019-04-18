package tn.esprit.mywatertunisie.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tn.esprit.mywatertunisie.Adapters.ListViewCartAdapter;
import tn.esprit.mywatertunisie.Entities.Cart;
import tn.esprit.mywatertunisie.Entities.User;
import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.SharedPrefManager;
import tn.esprit.mywatertunisie.URLs;


public class ShowCartActivity extends AppCompatActivity {
    //    public RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
    private List<Cart> listProdCart;
    private User user;
    View view;
    ListView listView;
    SwipeRefreshLayout mySwipeRefreshLayout;

    ListViewCartAdapter listViewCartAdapter;
    ProgressBar pbElect;
    TapBarMenu tapBarMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cart);

        tapBarMenu = findViewById(R.id.tapBarMenuCart);
        tapBarMenu.close();
        tapBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                tapBarMenu.toggle();
            }
        });


//        mRecyclerView = findViewById(R.id.show_cart_recyclerview);

        listView = findViewById(R.id.listView);
        listProdCart = new ArrayList<>();

        Log.e("loadrecycler", "loadrecycler");
        listViewCartAdapter = new ListViewCartAdapter(getApplicationContext(), listProdCart);
        listView.setAdapter(listViewCartAdapter);

        pbElect = findViewById(R.id.progress_bar_cart);
        pbElect.setVisibility(View.VISIBLE);
        listView.setVisibility(View.INVISIBLE);
        loadRecyclerViewData();
        mySwipeRefreshLayout = findViewById(R.id.swiperefreshCart);

        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.e("Refresh EAU", "onRefresh called from SwipeRefreshLayout");

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        loadRecyclerViewData();

//                        Fragment currentFragment = getActivity().getFragmentManager().findFragmentById(R.id.fragment_container);
//                        if (currentFragment instanceof Eau_Fragment) {
//                            FragmentTransaction fragTransaction =   (getActivity()).getFragmentManager().beginTransaction();
//                            fragTransaction.detach(currentFragment);
//                            fragTransaction.attach(currentFragment);
//                            fragTransaction.commit();}}


                        Log.e("Refresh EAU 2", "onRefresh called from SwipeRefreshLayout");

                    }
                }
        );

        //        ArrayAdapter<Cart> arrayAdapter
//                = new ArrayAdapter(this, R.layout.item_eaux , listProdCart);
//        listView.setAdapter(arrayAdapter);


        //      mRecyclerView.setLayoutManager(mLa youtManager);
        //    mAdapter = new ListViewCartAdapter(getApplicationContext(), listProdCart);
        //  mRecyclerView.setAdapter(mAdapter);


    }


    private void loadRecyclerViewData() {
        user = SharedPrefManager.getInstance(getApplicationContext()).getUser();
        String URL_SHOW_CART_BY_USER_sp = URLs.URL_SHOW_CART_BY_USER + user.getId();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_SHOW_CART_BY_USER_sp, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    Log.e("on response test", "Testing CART StringRequest Response");

                    //         JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = new JSONArray(response);
                    listProdCart.clear();
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject o = array.getJSONObject(i);
                        Cart cart = new Cart(

                                o.getInt("id_user"),
                                o.getInt("id_prod"),
                                o.getInt("quantiteCart"),
                                o.getString("reference"),
                                o.getString("nom"),
                                o.getString("description"),
                                o.getInt("prix"),
                                o.getString("categorie"),
                                o.getString("imageProd"),
                                o.getInt("quantite"));


                        //    Log.e("CART>>>>", cart.toString());
                        listProdCart.add(cart);
                    }
                    Log.e("AddTo ListProd", listProdCart.toString());

                    listViewCartAdapter.notifyDataSetChanged();
                } catch (JSONException e) {

                    Log.e("Cart ERREUR JSONexp >>>", e.getMessage());
                }
                pbElect.setVisibility(View.INVISIBLE);
                listView.setVisibility(View.VISIBLE);

                mySwipeRefreshLayout.setRefreshing(false);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Cart onErrorResp >>>>", error.getMessage() + "aaa");

                        pbElect.setVisibility(View.INVISIBLE);
                        listView.setVisibility(View.VISIBLE);

                        mySwipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getApplicationContext(), "Errreeuur: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


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


    public void toDashboard(View view) {
        Toast.makeText(this, "Dashboard", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ShowCartActivity.this, DashboardActivity.class);
        startActivity(intent);
    }


    public void callMyWater(View view) {
        Toast.makeText(this, "Appeler MyWater", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+21622797193"));
        startActivity(intent);
    }

    public void toDevis(View view) {
        Toast.makeText(this, "Demande de Devis", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ShowCartActivity.this, DevisPiscineActivity.class);
        startActivity(intent);
    }

    public void toProducts(View view) {
        Toast.makeText(this, "Produits", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ShowCartActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
