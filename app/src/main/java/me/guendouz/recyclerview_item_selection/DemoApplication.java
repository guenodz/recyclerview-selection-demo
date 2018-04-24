package me.guendouz.recyclerview_item_selection;

import android.app.Application;

import timber.log.Timber;

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
