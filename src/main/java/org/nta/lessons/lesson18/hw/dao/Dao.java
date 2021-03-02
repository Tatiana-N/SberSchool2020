package org.nta.lessons.lesson18.hw.dao;

import java.util.List;

public interface Dao<T> {
    List<T> getList();

    T getById(int id);

    T create(T obj);

    void showAll();

    void deleteById(int id);

    void update(T obj);
}
