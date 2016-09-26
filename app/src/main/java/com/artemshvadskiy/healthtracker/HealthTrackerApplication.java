package com.artemshvadskiy.healthtracker;

import android.app.Application;

import com.artemshvadskiy.healthtracker.db.PrimaryKey;

import com.artemshvadskiy.healthtracker.di.DaggerRealmComponent;
import com.artemshvadskiy.healthtracker.di.RealmComponent;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class HealthTrackerApplication extends Application {
    private RealmComponent mRealmComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mRealmComponent = DaggerRealmComponent.builder().build();

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfig);

        PrimaryKey.init();
    }

    public RealmComponent getRealmComponent() {
        return mRealmComponent;
    }
}
