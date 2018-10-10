package com.catreloaded.ama.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferencesConstants {

    public static final String REMEMBER_USER = "remember_user";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    private static String mUsername;
    private static String mPassword;

    private PreferencesConstants(){

    }

    private static void setValues(Context context){
        if(mPassword == null){
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            mPassword = sharedPreferences.getString(PASSWORD,"NULL");
        }
        if(mUsername == null){
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            mUsername = sharedPreferences.getString(USERNAME,"NULL");
        }
    }

    public static String getUsername(Context context){
        setValues(context);
        return mUsername;
    }

    public static String getPassword(Context context){
        setValues(context);
        return mPassword;
    }
}
