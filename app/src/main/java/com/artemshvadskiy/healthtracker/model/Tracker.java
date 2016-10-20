package com.artemshvadskiy.healthtracker.model;

import java.util.ArrayList;
import java.util.List;

public class Tracker extends BaseModel {
    private String mName;
    private List<Alarm> mAlarms;
    private List<Event> mEvents;

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

    public void setAlarms(List<Alarm> alarms) {
        mAlarms = alarms;
    }

    public List<Alarm> getAlarms() {
        return mAlarms;
    }

    public void setEvents(List<Event> events) {
        mEvents = events;
    }

    public List<Event> getEvents() {
        return mEvents;
    }

    public void logEvent() {
        mEvents.add(new Event(this));
    }
}
