package com.example.yao.sharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by 89551 on 2016-09-05.
 */
public class share {

    SharedPreferences preferences;
    public share(Context context,String name,int mode){
        preferences= context.getSharedPreferences(name, mode);
     }

    public void setPreferences(boolean d,String name, String pass, boolean b){
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean("d",d);
        edit.putString("name",name);
        edit.putString("pass",pass);
        edit.putBoolean("b",b);
        edit.commit();
    }
    public void setTime(boolean b){
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean("time",b);
        edit.commit();
    }

    public String getName(){
        String name = preferences.getString("name", "");
        return name;
    }
    public String getPass(){
        String pass = preferences.getString("pass", "");
        return pass;
    }

    public boolean getd(){
        boolean d = preferences.getBoolean("d", false);
        return d;
    }

    public boolean getB(){
        boolean b = preferences.getBoolean("b", false);
        return b;
    }
    public boolean getTime(){
        boolean time = preferences.getBoolean("time", true);
        return time;
    }

}
