package com.artemshvadskiy.healthtracker.di;

import com.artemshvadskiy.healthtracker.model.ModelManager;
import com.artemshvadskiy.healthtracker.model.Alarm;
import com.artemshvadskiy.healthtracker.model.Event;
import com.artemshvadskiy.healthtracker.model.Repository;
import com.artemshvadskiy.healthtracker.model.Tracker;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class RealmModule {
    @Provides
    @Singleton
    static ModelManager provideRealmDbManager() {
        return new ModelManager();
    }

    @Provides
    static Repository<Tracker> provideTrackerRepository(ModelManager modelManager) {
        return modelManager;
    }

    @Provides
    static Repository<Alarm> provideAlarmRepository(ModelManager modelManager) {
        return modelManager;
    }

    @Provides
    static Repository<Event> provideEventRepository(ModelManager modelManager) {
        return modelManager;
    }
}
