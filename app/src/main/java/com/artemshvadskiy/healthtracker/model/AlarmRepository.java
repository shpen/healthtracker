package com.artemshvadskiy.healthtracker.model;

import com.artemshvadskiy.healthtracker.db.Database;

import java.util.List;

/**
 * Created by Artem on 10/2/2016.
 */

public class AlarmRepository extends Repository<Alarm> {

    public AlarmRepository(Database database) {
        super(database);
    }

    @Override
    protected Class<Alarm> getModelClass() {
        return Alarm.class;
    }
}
