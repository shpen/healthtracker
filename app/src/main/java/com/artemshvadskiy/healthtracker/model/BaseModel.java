package com.artemshvadskiy.healthtracker.model;

import javax.inject.Inject;

public abstract class BaseModel<T extends BaseModel> {
    @Inject Repository<T> mRepository;

    private long mId;

    void setId(long id) {
        mId = id;
    }

    public long getId() {
        return mId;
    }

    protected void save() {
        mRepository.save((T) this);
    }

    protected void delete() {
        mRepository.delete((T) this);
    }
}
