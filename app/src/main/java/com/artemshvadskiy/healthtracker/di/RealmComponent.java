package com.artemshvadskiy.healthtracker.di;

import com.artemshvadskiy.healthtracker.MainActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules={RealmModule.class})
public interface RealmComponent {
    void inject(MainActivity activity);
}
