package com.example.yao.application;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 89551 on 2016-08-30.
 */
public class myApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
