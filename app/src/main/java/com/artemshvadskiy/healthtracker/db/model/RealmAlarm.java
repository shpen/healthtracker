package com.artemshvadskiy.healthtracker.db.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmAlarm extends RealmObject {
    @PrimaryKey
    public long mId;

    public int mHour;
    public int mMinute;
}
