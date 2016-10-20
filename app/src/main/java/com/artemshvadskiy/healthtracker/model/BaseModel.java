package com.artemshvadskiy.healthtracker.model;

public abstract class BaseModel {
    private long mId;

    void setId(long id) {
        mId = id;
    }

    public long getId() {
        return mId;
    }
}
