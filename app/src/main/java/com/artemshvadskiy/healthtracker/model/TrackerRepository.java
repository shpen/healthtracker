package com.artemshvadskiy.healthtracker.model;

import com.artemshvadskiy.healthtracker.db.Database;

public class TrackerRepository extends Repository<Tracker> {

    public TrackerRepository(Database database) {
        super(database);
    }

    @Override
    protected Class<Tracker> getModelClass() {
        return Tracker.class;
    }
}
