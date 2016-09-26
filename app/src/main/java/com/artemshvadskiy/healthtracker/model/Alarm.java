package com.artemshvadskiy.healthtracker.model;

public class Alarm extends BaseModel<Alarm> {
    private Tracker mTracker;
    private int mHour;
    private int mMinute;

    public Alarm(Tracker tracker, int hour, int minute) {
        mTracker = tracker;
        mHour = hour;
        mMinute = minute;
    }

    public int getHour() {
        return mHour;
    }

    public int getMinute() {
        return mMinute;
    }

    @Override
    public String toString() {
        return mHour + ":" + mMinute;
    }
}
