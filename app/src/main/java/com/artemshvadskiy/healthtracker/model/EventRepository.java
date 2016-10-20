package com.artemshvadskiy.healthtracker.model;

import com.artemshvadskiy.healthtracker.db.Database;

import java.util.List;

/**
 * Created by Artem on 10/2/2016.
 */

public class EventRepository extends Repository<Event> {

    public EventRepository(Database database) {
        super(database);
    }

    @Override
    protected Class<Event> getModelClass() {
        return Event.class;
    }
}
