package com.artemshvadskiy.healthtracker.model;

import java.util.List;

public interface Repository<T extends BaseModel> {
    T get(Class<T> clazz, long id);
    List<T> getAll(Class<T> clazz);
    void save(T item);
    void delete(T item);
}
