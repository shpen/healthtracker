package com.artemshvadskiy.healthtracker.model;

import java.util.Date;

public class Event extends BaseModel<Event> {
    private final Tracker mTracker;
    private final Date mDate;

    public Event(Tracker tracker) {
        this(tracker, new Date(System.currentTimeMillis()));
    }

    public Event(Tracker tracker, Date date) {
        mTracker = tracker;
        mDate = date;
    }

    public Date getDate() {
        return mDate;
    }

    public String getTrackerName() {
        return mTracker.getName();
    }
}
