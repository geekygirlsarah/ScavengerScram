package com.terrorbytes.scavengerscram;

import com.terrorbytes.scavengerscram.model.UserLogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager 
{ 
    private static final String PREFERENCE_FILE_KEY = "com.terrorbytes.scavengerscram.PREFERENCE_FILE_KEY"; 
    private static final String KEY_LOGGED_IN = "loggedIn";   
    public static final String KEY_USERID = "userId";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    
    private SharedPreferences mPreferences;     
    private Editor mEditor; 
    private Context mContext; 
      
    public SessionManager(Context context)
    { 
        mContext = context; 
        mPreferences = mContext.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE); 
        mEditor = mPreferences.edit(); 
    } 
    
    public void createLoginSession(String name, String email, Integer userId)
    { 
        mEditor.putBoolean(KEY_LOGGED_IN, true);
        mEditor.putInt(KEY_USERID, userId);
        mEditor.putString(KEY_NAME, name); 
        mEditor.putString(KEY_EMAIL, email); 
        mEditor.commit(); 
    }    
      
    public void checkLogin()
    { 
        if(!isLoggedIn())
        { 
            Intent i = new Intent(mContext, LoginActivity.class); 
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
            mContext.startActivity(i); 
        }         
    } 
    
    public UserLogin getUserLogin()
    {
    	UserLogin user = new UserLogin();
    	user.setId(mPreferences.getInt(KEY_USERID, -1)); 
    	user.setName(mPreferences.getString(KEY_NAME, null));
    	user.setName(mPreferences.getString(KEY_EMAIL, null)); 
        return user; 
    }
    
    public void logoutUser()
    { 
        mEditor.clear(); 
        mEditor.commit(); 
        Intent intent = new Intent(mContext, LoginActivity.class); 
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
        mContext.startActivity(intent);
    }
    
    public SharedPreferences getSharedPreferences()
    {
    	return mPreferences;
    }
    
    public boolean isLoggedIn()
    { 
        return mPreferences.getBoolean(KEY_LOGGED_IN, false); 
    } 
}