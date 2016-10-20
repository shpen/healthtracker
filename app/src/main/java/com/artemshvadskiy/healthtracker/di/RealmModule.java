package com.artemshvadskiy.healthtracker.di;

import com.artemshvadskiy.healthtracker.db.Database;
import com.artemshvadskiy.healthtracker.db.RealmDatabase;
import com.artemshvadskiy.healthtracker.model.Alarm;
import com.artemshvadskiy.healthtracker.model.AlarmRepository;
import com.artemshvadskiy.healthtracker.model.Event;
import com.artemshvadskiy.healthtracker.model.EventRepository;
import com.artemshvadskiy.healthtracker.model.Repository;
import com.artemshvadskiy.healthtracker.model.Tracker;
import com.artemshvadskiy.healthtracker.model.TrackerRepository;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class RealmModule {
    @Provides
    @Singleton
    static Database provideRealmDatabase() {
        return new RealmDatabase();
    }

    @Provides
    @Singleton
    static Repository<Event> provideEventRepository(Database database) {
        return new EventRepository(database);
    }

    @Provides
    @Singleton
    static Repository<Alarm> provideAlarmRepository(Database database) {
        return new AlarmRepository(database);
    }

    @Provides
    @Singleton
    static Repository<Tracker> provideTrackerRepository(Database database) {
        return new TrackerRepository(database);
    }
}
