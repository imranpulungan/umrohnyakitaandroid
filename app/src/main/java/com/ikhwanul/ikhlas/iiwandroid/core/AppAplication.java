package com.ikhwanul.ikhlas.iiwandroid.core;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by ingo-pc on 3/19/18.
 */

public class AppAplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
