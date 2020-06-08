package ua.com.ke4a_store.dao;

import ua.com.ke4a_store.entity.GoodsGroup;

public interface GroupsDao extends GenericDao<GoodsGroup>{

    void insert(GoodsGroup item);
}
