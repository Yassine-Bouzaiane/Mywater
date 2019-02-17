package tn.esprit.mywatertunisie;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import tn.esprit.mywatertunisie.Activities.LoginActivity;
import tn.esprit.mywatertunisie.Activities.RegisterActivity;
import tn.esprit.mywatertunisie.Entities.User;

public class SharedPrefManager {

    //the constants
    //simplifiedcodingsharedpref
    private static final String SHARED_PREF_NAME = "mywatersharedpref";
    private static final String KEY_NAME = "keyname";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_PASSWORD = "keypassword";
    private static final String KEY_ADDRESS = "keyaddress";
    private static final String KEY_IMAGE_USER = "keyimageuser";
    private static final String KEY_TYPE_USER = "keytypeuser";
    private static final String KEY_ID = "keyid";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_NAME, user.getNom());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_PASSWORD, user.getPassword());
        editor.putString(KEY_ADDRESS, user.getAdresse());
        editor.putString(KEY_IMAGE_USER, user.getImageUser());
        editor.putString(KEY_TYPE_USER, user.getTypeUser());

        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NAME, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(


                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_NAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_PASSWORD, null),
                sharedPreferences.getString(KEY_ADDRESS, null),
                sharedPreferences.getString(KEY_IMAGE_USER, null),
                sharedPreferences.getString(KEY_TYPE_USER, null)

        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(mCtx.getApplicationContext(), LoginActivity.class);
        mCtx.startActivity(intent);
    }
}
