package ua.com.ke4a_store.service;

import ua.com.ke4a_store.entity.Good;

import java.util.List;

public interface GoodsService {
    List<Good> findAll();

    List<Good> findByGroupId(long id);

    List<Good> findByName(String partName);

    Good findById(long id);

    void deleteById(long id);

    void insert(Good good, long id);

    void updateById(Good good);
}
