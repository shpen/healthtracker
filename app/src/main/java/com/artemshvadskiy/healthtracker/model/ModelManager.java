package com.artemshvadskiy.healthtracker.model;

import com.artemshvadskiy.healthtracker.db.model.RealmAlarm;
import com.artemshvadskiy.healthtracker.db.model.RealmEvent;
import com.artemshvadskiy.healthtracker.db.model.RealmTracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ModelManager implements Repository {
    private final HashMap<Long, Tracker> mTrackers;
    private final HashMap<Long, Event> mEvents;
    private final HashMap<Long, Alarm> mAlarms;

    public ModelManager() {
        mTrackers = new HashMap<>();
        mEvents = new HashMap<>();
        mAlarms = new HashMap<>();

        fillDummyData();
    }

    private void fillDummyData() {
        int numTrackers = 4;
        for (int i = 0; i < numTrackers; i++) {
            Tracker tracker = new Tracker();
            tracker.setId(i);
            tracker.setName("Tracker " + i);
            mTrackers.put((long) i, tracker);

            for (int j = 0; j < 3; j++) {
                Event event = new Event(tracker);
                event.setId((long) i * numTrackers + j);
                mEvents.put(event.getId(), event);
            }

            for (int j = 0; j < 3; j++) {
                Alarm alarm = new Alarm(tracker, j * 2 + 1, j * 10);
                alarm.setId((long) i * numTrackers + j);
                mAlarms.put(alarm.getId(), alarm);
            }
        }
    }

    @Override
    public BaseModel get(Class clazz, long id) {
        if (clazz == Alarm.class) {
            return mAlarms.get(id);
        } else if (clazz == Event.class) {
            return mEvents.get(id);
        } else if (clazz == Tracker.class) {
            return mTrackers.get(id);
        }

        return null;
    }

    @Override
    public List getAll(Class clazz) {
        Class realmClass;
        if (clazz == Alarm.class) {
            realmClass = RealmAlarm.class;

            return new ArrayList<>(mAlarms.values());
        } else if (clazz == Event.class) {
            realmClass = RealmEvent.class;

            return new ArrayList<>(mEvents.values());
        } else if (clazz == Tracker.class) {
            realmClass = RealmTracker.class;

            return new ArrayList<>(mTrackers.values());
        }

        return null;
    }

    @Override
    public void save(BaseModel item) {

    }

    @Override
    public void delete(BaseModel item) {

    }
}
