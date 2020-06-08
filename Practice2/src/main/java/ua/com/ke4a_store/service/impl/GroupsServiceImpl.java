package ua.com.ke4a_store.service.impl;

import ua.com.ke4a_store.dao.GroupsDao;
import ua.com.ke4a_store.entity.GoodsGroup;
import ua.com.ke4a_store.service.GroupsService;

import java.util.List;

public class GroupsServiceImpl implements GroupsService {

    private GroupsDao groupsDao;

    public GroupsServiceImpl(GroupsDao groupsDao) {
        this.groupsDao = groupsDao;
    }

    @Override
    public List<GoodsGroup> findAll() {
        return groupsDao.findAll();
    }

    @Override
    public void deleteById(long id) {
        groupsDao.deleteById(id);
    }

    @Override
    public void insert(GoodsGroup group) {
        groupsDao.insert(group);
    }

    @Override
    public void updateById(GoodsGroup group) {
        groupsDao.updateById(group);
    }
}
