package ua.com.ke4a_store.dao;

import java.util.List;

public interface GenericDao<T> {

    void deleteById(long id);

    void updateById(T item);

    List<T> findAll();
}
