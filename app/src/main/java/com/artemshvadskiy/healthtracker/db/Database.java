package com.artemshvadskiy.healthtracker.db;

import com.artemshvadskiy.healthtracker.model.BaseModel;

import java.util.List;

/**
 * Created by Artem on 10/2/2016.
 */

public interface Database {
    <T extends BaseModel> T get(Class<T> clazz, long id);
    <T extends BaseModel> List<T> getAll(Class<T> clazz);
    void save(BaseModel item);
    void delete(BaseModel item);
}
