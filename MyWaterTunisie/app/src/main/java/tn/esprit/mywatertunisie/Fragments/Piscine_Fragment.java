package tn.esprit.mywatertunisie.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
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

import tn.esprit.mywatertunisie.Adapters.RecyclerViewProduitAdapter;
import tn.esprit.mywatertunisie.Entities.Produit;
import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.URLs;


/**
 * A simple {@link Fragment} subclass.
 */
public class Piscine_Fragment extends Fragment {
    View view;
    private RecyclerView piscineRecyclerView;
    private List<Produit> listProdEaux;
    private SwipeRefreshLayout mySwipeRefreshLayout;
    ProgressBar pbPiscine;
    RecyclerViewProduitAdapter recyclerViewProduitAdapter;

    public Piscine_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_piscine, container, false);
        piscineRecyclerView = view.findViewById(R.id.piscine_recyclerview);
        recyclerViewProduitAdapter = new RecyclerViewProduitAdapter(getContext(), listProdEaux);
        piscineRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        piscineRecyclerView.setAdapter(recyclerViewProduitAdapter);


        pbPiscine = view.findViewById(R.id.progress_bar_Piscine);
        pbPiscine.setVisibility(View.VISIBLE);
        piscineRecyclerView.setVisibility(View.INVISIBLE);

        loadRecyclerViewData();


        mySwipeRefreshLayout = view.findViewById(R.id.swiperefreshPiscine);

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
        Log.e("Piscine Load>>>", "AAAAAAAA");


        Log.e("Piscine Load>>>", "BBBBBBBBBBBB");

    }

    private void loadRecyclerViewData() {

//        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        //  ProgressDialog.show(getContext(), "Loading..", "JSON is Loading...");
        Log.e("Piscine Load>>>", "CCCCCCCCCCCC");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_PROD_PISCINE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("Piscine Load>>>", "DDDDDDDDDD");

                try {
                    Log.e("Piscine Load>>>", "EEEEEEEEEEEE");

                    //         JSONObject jsonObject = new JSONObject(response);
                    Log.e("Piscine Load>>>", "FFFFFFFFFFF");
                    JSONArray array = new JSONArray(response);
                    Log.e("Piscine Load>>>", "GGGGGGGGGGGG");
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

                    Log.e("Piscine ERREUR:>>>>>", e.getMessage());
                }

                pbPiscine.setVisibility(View.INVISIBLE);
                piscineRecyclerView.setVisibility(View.VISIBLE);

                mySwipeRefreshLayout.setRefreshing(false);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

}
