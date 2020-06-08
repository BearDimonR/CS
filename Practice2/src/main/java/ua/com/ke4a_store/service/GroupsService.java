package ua.com.ke4a_store.service;

import ua.com.ke4a_store.entity.GoodsGroup;

import java.util.List;

public interface GroupsService {
    List<GoodsGroup> findAll();

    void deleteById(long id);

    void insert(GoodsGroup group);

    void updateById(GoodsGroup group);
}
