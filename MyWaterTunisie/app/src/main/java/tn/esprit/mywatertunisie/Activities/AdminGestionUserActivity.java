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

import tn.esprit.mywatertunisie.Adapters.AdminRecyclerViewUsersAdapter;
import tn.esprit.mywatertunisie.Entities.User;
import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.SharedPrefManager;
import tn.esprit.mywatertunisie.URLs;

public class AdminGestionUserActivity extends AppCompatActivity {

    private RecyclerView userRecyclerView;
    private List<User> listUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_gestion_user);

        listUsers = new ArrayList<>();
        userRecyclerView = findViewById(R.id.user_recyclerview);

//        AdminRecyclerViewUsersAdapter adminRecyclerViewUsersAdapter
//                = new AdminRecyclerViewUsersAdapter(getApplicationContext(), listUsers);
        loadRecyclerViewData();

    }

    private void loadRecyclerViewData() {

        Log.e("Loading List Users: ", "Loading Users...");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_GET_ALL_USERS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {

//                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = new JSONArray(response);


                    for (int i = 0; i < array.length(); i++) {

                        JSONObject userJson = array.getJSONObject(i);

                        User user = new User(
                                userJson.getInt("id"),
                                userJson.getString("name"),
                                userJson.getString("email"),
                                userJson.getString("Password"),
                                userJson.getString("address"),
                                userJson.getString("imageUser"),
                                userJson.getString("typeUser")
                        );
                        listUsers.add(user);
                        Log.e("USERS >>>>", user.toString());

                    }

                    userRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    userRecyclerView.setAdapter(new AdminRecyclerViewUsersAdapter(getApplicationContext(), listUsers));

                } catch (JSONException e) {

                    Log.e("ERREUR List Users:>>>>>", e.getMessage());
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

        Log.e("ListU after request>>>>", listUsers.toString());


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
