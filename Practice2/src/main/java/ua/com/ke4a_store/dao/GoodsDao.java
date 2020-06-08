package ua.com.ke4a_store.dao;

import ua.com.ke4a_store.entity.Good;

import java.util.List;

public interface GoodsDao extends GenericDao<Good> {
    void insert(Good item, long groupid);

    Good findById(long id);

    List<Good> findByName(String partName);

    List<Good> findByGroupId(long id);
}
