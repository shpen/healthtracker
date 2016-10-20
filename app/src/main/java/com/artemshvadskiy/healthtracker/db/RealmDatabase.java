package com.artemshvadskiy.healthtracker.db;

import com.artemshvadskiy.healthtracker.db.model.RealmAlarm;
import com.artemshvadskiy.healthtracker.db.model.RealmEvent;
import com.artemshvadskiy.healthtracker.db.model.RealmTracker;
import com.artemshvadskiy.healthtracker.model.Alarm;
import com.artemshvadskiy.healthtracker.model.BaseModel;
import com.artemshvadskiy.healthtracker.model.Event;
import com.artemshvadskiy.healthtracker.model.Tracker;
import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 10/2/2016.
 */

public class RealmDatabase implements Database {
    private final Realm mRealm;

    public RealmDatabase() {
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public <T extends BaseModel> T get(Class<T> clazz, long id) {
        Class<? extends RealmModel> realmClass = getRealmClass(clazz);

        RealmModel realmModel = mRealm
                .where(realmClass)
                .equalTo(PrimaryKey.ID, id)
                .findFirst();

        return copyFromRealm(realmModel);
    }

    @Override
    public <T extends BaseModel> List<T> getAll(Class<T> clazz) {
        Class<? extends RealmModel> realmClass = getRealmClass(clazz);

        RealmResults<? extends RealmModel> realmResults = mRealm
                .where(realmClass)
                .findAll();

        List<T> results = new ArrayList<>();
        for (RealmModel realmModel : realmResults) {
            T t = copyFromRealm(realmModel);
            results.add(t);
        }

        return results;
    }

    @Override
    public void save(BaseModel item) {

    }

    @Override
    public void delete(BaseModel item) {

    }

    private Class<? extends RealmModel> getRealmClass(Class<? extends BaseModel> clazz) {
        if (clazz == Tracker.class) {
            return RealmTracker.class;
        } else if (clazz == Event.class) {
            return RealmEvent.class;
        } else if (clazz == Alarm.class) {
            return RealmAlarm.class;
        }

        return null;
    }

    private <T extends BaseModel> T copyFromRealm(RealmModel realmModel) {
        if (realmModel == null) return null;

        if (realmModel instanceof RealmTracker) {
            RealmTracker realmTracker = (RealmTracker) realmModel;

            Tracker tracker = new Tracker();
            tracker.setName(realmTracker.mName);

            ArrayList<Alarm> alarms = new ArrayList<>();
            for (RealmAlarm realmAlarm : realmTracker.mAlarms) {
                alarms.add(new Alarm(tracker, realmAlarm.mHour, realmAlarm.mMinute));
            }
            tracker.setAlarms(alarms);

            ArrayList<Event> events = new ArrayList<>();
            for (RealmEvent realmEvent : realmTracker.mEvents) {
                events.add(new Event(tracker, realmEvent.mDate));
            }
            tracker.setEvents(events);

            return (T) tracker;
        }

        return null;
    }

    private <T extends RealmModel> void copyToRealm(final BaseModel model) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Class<? extends RealmModel> realmClass = getRealmClass(model.getClass());
                RealmModel realmModel = realm
                        .where(realmClass)
                        .equalTo(PrimaryKey.ID, model.getId())
                        .findFirst();
                if (realmModel == null) {
                    if (model instanceof Tracker) {
                        Tracker tracker = (Tracker) model;
                        RealmTracker realmTracker = new RealmTracker();
                        realmTracker.mName = tracker.getName();

                        for (Alarm alarm : tracker.getAlarms()) {
                            
                        }
                    }
                }

                realm.copyToRealm(realmModel);
            }
        });
    }
}
