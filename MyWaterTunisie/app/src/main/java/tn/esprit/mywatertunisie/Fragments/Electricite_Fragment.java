
package tn.esprit.mywatertunisie.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
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


/*
 * A simple {@link Fragment} subclass.
 */
public class Electricite_Fragment extends Fragment {
    View view;
    private RecyclerView electriciteRecyclerView;
    private List<Produit> listProdEaux;
    private SwipeRefreshLayout mySwipeRefreshLayout;
ProgressBar pbelectricite;
    RecyclerViewProduitAdapter recyclerViewProduitAdapter;
    public Electricite_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e("Piscine frag", "Piscine frag");

        view = inflater.inflate(R.layout.fragment_electricite, container, false);
        electriciteRecyclerView = view.findViewById(R.id.electricite_recyclerview);
        recyclerViewProduitAdapter = new RecyclerViewProduitAdapter(getContext(), listProdEaux);
        electriciteRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        electriciteRecyclerView.setAdapter(recyclerViewProduitAdapter);

        pbelectricite = view.findViewById(R.id.progress_bar_Electricite);
        pbelectricite.setVisibility(View.VISIBLE);
        electriciteRecyclerView.setVisibility(View.INVISIBLE);
        loadRecyclerViewData();


        mySwipeRefreshLayout = view.findViewById(R.id.swiperefreshElect);

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
        Log.e("AAAAAA", "AAAAAAAA");

        loadRecyclerViewData();

        Log.e("BBBBBB", "BBBBBBBBBBBB");

    }

    private void loadRecyclerViewData() {

        //     final ProgressDialog progressDialog = new ProgressDialog(getContext());
        //  ProgressDialog.show(getContext(), "Loading..", "JSON is Loading...");
        Log.e("CCCCCCC", "CCCCCCCCCCCC");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_PROD_ELECT, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e("DDDDDDD", "DDDDDDDDDD");

                //             progressDialog.cancel();
                try {
                    Log.e("EEEEEE", "EEEEEEEEEEEE");

                    //         JSONObject jsonObject = new JSONObject(response);
                    Log.e("FFFFFFF", "FFFFFFFFFFF");
                    JSONArray array = new JSONArray(response);
                    Log.e("GGGGGGG", "GGGGGGGGGGGG");
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

                    Log.e("ERREEEUUUR:>>>>>", e.getMessage());
                }
                pbelectricite.setVisibility(View.INVISIBLE);
                electriciteRecyclerView.setVisibility(View.VISIBLE);

                mySwipeRefreshLayout.setRefreshing(false);


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //                  progressDialog.dismiss();
                        Toast.makeText(getContext(), "Something's wrong", Toast.LENGTH_LONG).show();
                        pbelectricite.setVisibility(View.INVISIBLE);
                        electriciteRecyclerView.setVisibility(View.VISIBLE);

                        mySwipeRefreshLayout.setRefreshing(false);
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

}


/*


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import tn.esprit.mywatertunisie.Adapters.RecyclerViewElectAdapter;
import tn.esprit.mywatertunisie.Entities.Electricite;
import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.URLs;

*/
/**
 * A simple {@link Fragment} subclass.
 *//*



public class Electricite_Fragment extends Fragment {


        View view;
        private RecyclerView electRecyclerView;
        private List<Electricite> listElectricite;


        public Electricite_Fragment() {
            // Required empty public constructor
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment

            view = inflater.inflate(R.layout.fragment_electricite, container, false);
            electRecyclerView = view.findViewById(R.id.electricite_recyclerview);
            RecyclerViewElectAdapter recyclerViewAdapter = new RecyclerViewElectAdapter(getContext(), listElectricite);
            electRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            electRecyclerView.setAdapter(recyclerViewAdapter);


            return view;
        }


        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            listElectricite = new ArrayList<>();

//        listProdEaux.add(new Produit("A001","Osmoseur",
//                "Système de filtration de l'eau potable", 370,"eau","a.png",13));
//        listProdEaux.add(new Produit("A002","Kit de Filtres",
//                "Filtres pour osmoseur", 28,"eau","b.png",67));
//        listProdEaux.add(new Produit("A003","Bouteille Filtrante",
//                "Système de filtration portable de l'eau potable", 60,"eau","c.png",40));
//        listProdEaux.add(new Produit("A004","Adoucisseur",
//                "Système d'adoussicement domestique", 1460,"eau","d.png",7));
            Log.e("Electricité >>>>>","AAAAAAAA");

            loadRecyclerViewData();
            Log.e("Electricité >>>>>","BBBBBBBBBBBB");

        }

        private void loadRecyclerViewData() {

//        final ProgressDialog progressDialog = new ProgressDialog(getContext());
            //  ProgressDialog.show(getContext(), "Loading..", "JSON is Loading...");
            Log.e("Electricité >>>>>","CCCCCCCCCCCC");

            StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_ELECT_LIST, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.e("Electricité >>>>>","DDDDDDDDDD");

                    try {
                        Log.e("Electricité >>>>>","EEEEEEEEEEEE");

                        //         JSONObject jsonObject = new JSONObject(response);
                        Log.e("Electricité >>>>>","FFFFFFFFFFF");
                        JSONArray array = new JSONArray(response);
                        Log.e("Electricité >>>>>","GGGGGGGGGGGG");

                        for (int i = 0; i < array.length(); i++) {

                            JSONObject o = array.getJSONObject(i);
                            Electricite electricite = new Electricite(
                                    o.getString("titre"),
                                    o.getString("description"),
                                    o.getString("imageElect")
                                );
                            Log.e("ELECTRICITE>>>>", electricite.toString());
                            listElectricite.add(electricite);

                        }


                    } catch (JSONException e) {

                        Log.e("ERREEEUUUR ELECT:>>>>>",e.getMessage());
                    }

                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                   Log.e("eeeeeeeeee>> ", error.getMessage());
                  //          Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(getContext());
            requestQueue.add(stringRequest);

        }

    }
*/


