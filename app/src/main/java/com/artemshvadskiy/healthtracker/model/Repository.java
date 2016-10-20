package com.artemshvadskiy.healthtracker.model;

import com.artemshvadskiy.healthtracker.db.Database;

import java.util.List;

public abstract class Repository<T extends BaseModel> {
    protected final Database mDatabase;

    public Repository(Database database) {
        mDatabase = database;
    }

    public T get(long id) {
        return mDatabase.get(getModelClass(), id);
    }

    public List<T> getAll() {
        return mDatabase.getAll(getModelClass());
    }

    public void save(T item) {
        mDatabase.save(item);
    }

    public void delete(T item) {
        mDatabase.delete(item);
    }

    protected abstract Class<T> getModelClass();
}
