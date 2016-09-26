package com.artemshvadskiy.healthtracker.db.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import java.util.Date;

public class RealmEvent extends RealmObject {
    @PrimaryKey
    public long mId;

    public RealmTracker mTracker;
    public Date mDate;
}
