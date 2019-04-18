package tn.esprit.mywatertunisie.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import lal.adhish.gifprogressbar.GifView;
import tn.esprit.mywatertunisie.Entities.User;
import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.SharedPrefManager;
import tn.esprit.mywatertunisie.URLs;
import tn.esprit.mywatertunisie.VolleySingleton;


public class LoginActivity extends AppCompatActivity {
    EditText editTextEmail, editTextPassword;
    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //  Log.e("Shared Pref login", ""+SharedPrefManager.getInstance(this).getUser());

        Log.e("Shared Pref Register", "before ifs");


        editTextEmail = findViewById(R.id.login_email);
        editTextPassword = findViewById(R.id.login_password);


        Log.e("Shared Pref login1", "after ifs");
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Log.e("Shared Pref login2", "before ifs");
            Log.e("Shared Pref login2.5", "" + SharedPrefManager.getInstance(this).getUser());

            if (SharedPrefManager.getInstance(this).getUser().getTypeUser().equals("admin")) {
                finish();
                Log.e("Shared Pref login3", "if admin");

                startActivity(new Intent(getApplicationContext(), AdminMainActivity.class));

                Log.e("Shared Pref login4", "after ifs");
            } else if (SharedPrefManager.getInstance(this).getUser().getTypeUser().equals("user")) {
                finish();
                Log.e("Shared Pref login5", "if admin");

                startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                Toast.makeText(getApplicationContext(), "CHANGE THE EMAIL PATTERN", Toast.LENGTH_SHORT).show();

                Log.e("Shared Pref login6", "after ifs");
            }

        }
        //if user presses on login
        //calling the method login
        findViewById(R.id.login_connect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

        //if user presses on not registered
        findViewById(R.id.textViewRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open register screen
                finish();
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        // View v = findViewById(android.R.id.content);

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            Toast.makeText(getApplicationContext(), "Connected to Internet", Toast.LENGTH_LONG).show();
            //      Snackbar.make(v, "Connected To Internet", Snackbar.LENGTH_LONG);
            Log.e("SnackBar1 >>> ", "WIFI Network");
        } else if (mobileNetwork != null && mobileNetwork.isConnected()) {
            Log.e("SnackBar2 >>> ", "Mobile Network");
            //     Snackbar.make(v, "Connected To Internet", Snackbar.LENGTH_LONG);
            Toast.makeText(getApplicationContext(), "Connected to Internet", Toast.LENGTH_LONG).show();
        } else if (activeNetwork != null && activeNetwork.isConnected()) {
            //        Snackbar.make(v, "Connected To Internet", Snackbar.LENGTH_LONG);
            Toast.makeText(getApplicationContext(), "Connected to Internet", Toast.LENGTH_LONG).show();
            Log.e("SnackBar3 >>> ", "Active Network");
        } else {
            //        Snackbar.make(v, "Not Connected To Internet", Snackbar.LENGTH_LONG);
            Toast.makeText(getApplicationContext(), "Not Connected to Internet", Toast.LENGTH_LONG).show();
            Log.e("SnackBar4 >>> ", "No Network");
        }
    }


    private void userLogin() {
        //first getting the values
        final String email = editTextEmail.getText().toString();
        final String password = editTextPassword.getText().toString();
        String URL_Login = URLs.URL_LOGIN + "/" + email;

        //validating inputs
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please enter your email");
            editTextEmail.requestFocus();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;
        }


        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            Log.e("######", "#####");
            return;
        }
        Log.e("StringRequest", "just before StringRequest");
        Log.e("URL >>", URL_Login);
        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_Login,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.e("Login onResponse >>>>", "inside onResponse");

                        try {
                            //converting response to json object
                            //    JSONObject obj = new JSONObject(response);
                            JSONArray obj = new JSONArray(response);

                            //if no error in response
                            //           if (!obj.getBoolean(0)) {

                            //getting the user from the response
                            JSONObject userJson = obj.getJSONObject(0);

                            //creating a new user object
                            User user = new User(
                                    userJson.getInt("id"),
                                    userJson.getString("name"),
                                    userJson.getString("email"),
                                    userJson.getString("Password"),
                                    userJson.getString("address"),
                                    userJson.getString("imageUser"),
                                    userJson.getString("typeUser")
                            );
                            Log.e("7777777", "77777777");

                            //storing the user in shared preferences
                            SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                            Log.e("User >>>>", user.toString());


                            //starting the profile activity
                            finish();
                            switch (user.getTypeUser()) {
                                case "user":
                                    startActivity(new Intent(getApplicationContext(), DashboardActivity.class));

                                    break;
                                case "admin":

                                    startActivity(new Intent(getApplicationContext(), AdminMainActivity.class));

                                    break;
                                default:
                                    Toast.makeText(getApplicationContext(), "Unknown user type !! " + user.getTypeUser(), Toast.LENGTH_SHORT).show();

                                    break;
                            }
                         /*   } else {
                                Toast.makeText(getApplicationContext(), "bbbbbb" + obj.getString(0), Toast.LENGTH_SHORT).show();
                            }*/
                        } catch (JSONException e) {
                            Log.e("Erreru JSON Login >>>>>", e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "ErreurResponse Connexion: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("Password", password);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Log.e("Back Pressed", "Not Going Back though lol!");
    }
}