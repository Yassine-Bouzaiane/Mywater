package tn.esprit.mywatertunisie.Fragments;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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

import tn.esprit.mywatertunisie.Activities.MainActivity;
import tn.esprit.mywatertunisie.Adapters.RecyclerViewProduitAdapter;
import tn.esprit.mywatertunisie.Entities.Produit;
import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.URLs;


/**
 * A simple {@link Fragment} subclass.
 */
public class Eau_Fragment extends Fragment {
    View view;
    private RecyclerView eauRecyclerView;
    private List<Produit> listProdEaux;
    RecyclerViewProduitAdapter recyclerViewProduitAdapter;
    SwipeRefreshLayout mySwipeRefreshLayout;
    ProgressBar pbeau;

    public Eau_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e("Eau frag", "Eau frag");

        view = inflater.inflate(R.layout.fragment_eau, container, false);
        eauRecyclerView = view.findViewById(R.id.eau_recyclerview);
        recyclerViewProduitAdapter = new RecyclerViewProduitAdapter(getContext(), listProdEaux);
        eauRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        eauRecyclerView.setAdapter(recyclerViewProduitAdapter);

        pbeau= view.findViewById(R.id.progress_bar_Eau);
        pbeau.setVisibility(View.VISIBLE);
        eauRecyclerView.setVisibility(View.INVISIBLE);
        loadRecyclerViewData();


        mySwipeRefreshLayout = view.findViewById(R.id.swiperefreshEau);

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

        return view;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listProdEaux = new ArrayList<>();

        //        listProdEaux.add(new Produit("A001","Osmoseur",
        //                "Système de filtration de l'eau potable", 370,"eau","a.png",13));
        //        listProdEaux.add(new Produit("A002","Kit de Filtres",
        //                "Filtres pour osmoseur", 28,"eau","b.png",67));
        //        listProdEaux.add(new Produit("A003","Bouteille Filtrante",
        //                "Système de filtration portable de l'eau potable", 60,"eau","c.png",40));
        //        listProdEaux.add(new Produit("A004","Adoucisseur",
//                "Système d'adoussicement domestique", 1460,"eau","d.png",7));
        Log.e("Eau Load>>>", "AAAAAAAA");

        Log.e("Eau Load>>>", "BBBBBBBBBBBB");

    }

    private void loadRecyclerViewData() {

        //     final ProgressDialog progressDialog = new ProgressDialog(getContext());
        //  ProgressDialog.show(getContext(), "Loading..", "JSON is Loading...");
        Log.e("Eau Load>>>", "CCCCCCCCCCCC");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_PROD_EAU, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("Eau Load>>>", "DDDDDDDDDD");

                //             progressDialog.cancel();
                try {
                    Log.e("Eau Load>>>", "EEEEEEEEEEEE");

                    //         JSONObject jsonObject = new JSONObject(response);
                    Log.e("Eau Load>>>", "FFFFFFFFFFF");
                    JSONArray array = new JSONArray(response);
                    Log.e("Eau Load>>>", "GGGGGGGGGGGG");
                    listProdEaux.clear();
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject o = array.getJSONObject(i);
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
                        listProdEaux.add(produit);


                    }

recyclerViewProduitAdapter.notifyDataSetChanged();
                } catch (JSONException e) {

                    Log.e("Eau ERREUR:>>>>>", e.getMessage());
                }

                pbeau.setVisibility(View.INVISIBLE);
                eauRecyclerView.setVisibility(View.VISIBLE);

                mySwipeRefreshLayout.setRefreshing(false);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //                  progressDialog.dismiss();
                        Toast.makeText(getContext(), "Something's wrong", Toast.LENGTH_LONG).show();
                        pbeau.setVisibility(View.INVISIBLE);
                        eauRecyclerView.setVisibility(View.VISIBLE);

                        mySwipeRefreshLayout.setRefreshing(false);

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);


    }

    /*
     * Listen for option item selections so that we receive a notification
     * when the user requests a refresh by selecting the refresh action bar item.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            // Check if user triggered a refresh:
            case R.id.menu_refresh:
                Log.e("menuuu refresh", "Refresh menu item selected");

                // Signal SwipeRefreshLayout to start the progress indicator
                mySwipeRefreshLayout.setRefreshing(true);

                // Start the refresh background task.
                // This method calls setRefreshing(false) when it's finished.
                loadRecyclerViewData();
                Log.e("menuu refresh 2", "Refresh recyclerview Data");
                return true;
        }

        // User didn't trigger a refresh, let the superclass handle this action
        return super.onOptionsItemSelected(item);

    }
}
