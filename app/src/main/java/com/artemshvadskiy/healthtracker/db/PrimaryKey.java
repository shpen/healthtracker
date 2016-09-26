package com.artemshvadskiy.healthtracker.db;

import io.realm.Realm;
import io.realm.RealmModel;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public class PrimaryKey {
    public static final String ID = "mId";

    private static final HashMap<Class, AtomicLong> mKeys = new HashMap<>();

    public static void init() {
        Realm realm = Realm.getDefaultInstance();
        Set<Class<? extends RealmModel>> models = realm.getConfiguration().getRealmObjectClasses();
        for (Class clazz : models) {
            Number max = realm.where(clazz).max(ID);
            long maxLong;
            if (max == null) {
                maxLong = 0;
            } else {
                maxLong = max.longValue();
            }
            mKeys.put(clazz, new AtomicLong(maxLong));
        }
    }

    public static synchronized long generateKey(Class clazz) {
        if (mKeys.isEmpty()) {
            throw new IllegalStateException("Need to call init() before using factory");
        }

        return mKeys.get(clazz).incrementAndGet();
    }
}
