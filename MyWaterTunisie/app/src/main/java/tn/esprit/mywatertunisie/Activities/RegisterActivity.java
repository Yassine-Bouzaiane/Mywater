package tn.esprit.mywatertunisie.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import tn.esprit.mywatertunisie.R;
import tn.esprit.mywatertunisie.SharedPrefManager;
import tn.esprit.mywatertunisie.URLs;


public class RegisterActivity extends AppCompatActivity {


    EditText editTextFullName, editTextEmail, editTextPassword, editTextConfirmPassword, editTextAddress;
    // EditText editTextFullName = findViewById(R.id.register_full_name);
//    final String str = editTextFullName.getText().toString();

    StringRequest stringRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //if the user is already logged in we will directly start the profile activity
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Log.e("Shared Pref Register", "before ifs");
            if (SharedPrefManager.getInstance(this).getUser().getTypeUser().equals("admin")) {
                finish();
                Log.e("Shared Pref Register", "if admin");

                startActivity(new Intent(getApplicationContext(), AdminMainActivity.class));

                Log.e("Shared Pref Register", "after ifs");
            } else if (SharedPrefManager.getInstance(this).getUser().getTypeUser().equals("user")) {
                finish();
                Log.e("Shared Pref Register", "if admin");

                startActivity(new Intent(getApplicationContext(), MainActivity.class));

                Log.e("Shared Pref Register", "after ifs");
            }

        }
        editTextFullName = findViewById(R.id.register_full_name);
        editTextEmail = findViewById(R.id.register_email);
        editTextPassword = findViewById(R.id.register_password);
        editTextAddress = findViewById(R.id.register_address);
        editTextConfirmPassword = findViewById(R.id.register_confirm_password);


        findViewById(R.id.register_connect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on button register
                //here we will register the user to server

                registerUser();
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Log.e("Register", "COMPLETE");
            }
        });

        findViewById(R.id.textViewLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if user pressed on login
                //we will open the login screen
                finish();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

    }

    private void registerUser() {
        //     final String name = editTextFullName.getText().toString();
        final String name = editTextFullName.getText().toString();

        final String email = editTextEmail.getText().toString();
        final String address = editTextAddress.getText().toString();
        final String password = editTextPassword.getText().toString();
        final String confirmPassword = editTextConfirmPassword.getText().toString();
        final String imageUser = "a.png";
        final String typeUser = "user";

        //first we will do the validations

        if (TextUtils.isEmpty(name)) {
            editTextFullName.setError("Please enter username");
            editTextFullName.requestFocus();
            return;
        }

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

        if (TextUtils.isEmpty(address)) {
            editTextAddress.setError("Enter an address");
            editTextAddress.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Enter a password");
            editTextPassword.requestFocus();
            return;
        }

        if (!(password.equals(confirmPassword))) {
            editTextConfirmPassword.setError("Password Mismatch");
            editTextConfirmPassword.requestFocus();
            return;
        }


//for POST requests, only the following line should be changed to


        StringRequest sr = new StringRequest(Request.Method.POST, URLs.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("HttpClient", "success! response: " + response);


                        startActivity(new Intent(getApplicationContext(), MainActivity.class));


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERREUR JSON>>>>>>", "error: " + error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("create_name", name);
                params.put("create_email", email);
                params.put("create_Password", password);
                params.put("create_address", address);
                params.put("create_imageUser", imageUser);
                params.put("create_typeUser", typeUser);

                return params;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(this);
        Log.e("Register Queue", "declaring rq");

        queue.add(sr);


//         stringRequest = new StringRequest(Request.Method.POST, URLs.URL_REGISTER,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//                            //converting response to json object
//                            JSONObject obj = new JSONObject(response);
//
//                            //if no error in response
//                            if (!obj.getBoolean("error")) {
//                //                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//
//                                //getting the user from the response
//                                JSONObject userJson = obj.getJSONObject("user");
//                                //creating a new user object
//                               User user = new User(
//                               //         userJson.getInt("id"),
//                                        userJson.getString("name"),
//                                        userJson.getString("email"),
//                                        userJson.getString("Password"),
//                                        userJson.getString("address"),
//                                        userJson.getString("imageUser"),
//                                        userJson.getString("typeUser")
//
//                                        );
//                                Log.e("AAAAA >>>>>>>>>>>>", userJson.toString());
//
//                                //storing the user in shared preferences
//                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
//
//                                //starting the profile activity
//                              //  finish();
//                      startActivity(new Intent(getApplicationContext(), ProfilActivity.class));
//                            } else {
//                           //     Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                    //    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                        Log.e("onError>>>>","erroor: "+error.getMessage());
//                    }
//                })
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("name", name);
//                params.put("email", email);
//                params.put("Password", password);
//                params.put("address", address);
//                params.put("imageUser", imageUser);
//                params.put("typeUser", typeUser);
//               // Toast.makeText(getApplicationContext(),"test", Toast.LENGTH_LONG).show();
//                Log.e("MAP>>>", params.toString());
//                return params;
//            }
//        };
//        try {
//            Log.e("String req>>>>",stringRequest.getHeaders().toString());
//        } catch (AuthFailureError authFailureError) {
//            authFailureError.printStackTrace();
//        }
//        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

}



