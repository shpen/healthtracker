package com.artemshvadskiy.healthtracker.db.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class RealmTracker extends RealmObject {
    @PrimaryKey public long mId;

    @Required public String mName;

    public boolean mManuallyLoggable;
    public RealmList<RealmAlarm> mAlarms;
    public RealmList<RealmEvent> mEvents;
}
