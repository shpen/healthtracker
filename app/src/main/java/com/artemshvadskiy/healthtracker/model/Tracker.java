package com.artemshvadskiy.healthtracker.model;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class Tracker extends BaseModel<Tracker> {
    private String mName;
    private ArrayList<Alarm> mAlarms;
    private ArrayList<Event> mEvents;

    @Inject
    public Tracker() {
        mAlarms = new ArrayList<>();
        mEvents = new ArrayList<>();
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public List<Alarm> getAlarms() {
        return mAlarms;
    }

    public void logEvent() {
        new Event(this).save();
    }
}
