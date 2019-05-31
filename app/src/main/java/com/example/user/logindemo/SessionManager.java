package com.example.user.logindemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "Maheshwari_Yuva";
    private static final String IS_LOGIN = "loggedIn";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_NAME = "name";
    public static final String KEY_ROLEID = "roleId";
    public static final String KEY_STATE_ID = "state_id";
    public static final String KEY_DIST = "district_id";
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void createLoginSession(String email){

        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }
    public void checkLogin(){

        if(!this.isLoggedIn()){
            Intent i = new Intent(_context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }

    }
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_ROLEID, pref.getString(KEY_ROLEID, null));
        user.put(KEY_STATE_ID, pref.getString(KEY_STATE_ID, null));
        user.put(KEY_DIST, pref.getString(KEY_DIST, null));
        return user;
    }

    public void logoutUser(){
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        editor.remove("email");
        editor.clear();
        editor.apply();
        editor.commit();
      /*Intent i = new Intent(_context, Login.class);
      _context.startActivity(i);*/

    }
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
