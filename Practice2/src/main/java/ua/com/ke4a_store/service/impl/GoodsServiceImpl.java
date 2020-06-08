package ua.com.ke4a_store.service.impl;

import ua.com.ke4a_store.dao.GoodsDao;
import ua.com.ke4a_store.entity.Good;
import ua.com.ke4a_store.service.GoodsService;

import java.util.List;

public class GoodsServiceImpl implements GoodsService {

    private GoodsDao goodsDao;

    public GoodsServiceImpl(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Override
    public List<Good> findAll() {
        return goodsDao.findAll();
    }

    @Override
    public List<Good> findByGroupId(long id) {
        return goodsDao.findByGroupId(id);
    }

    @Override
    public List<Good> findByName(String partName) {
        return goodsDao.findByName(partName);
    }

    @Override
    public Good findById(long id) {
        return goodsDao.findById(id);
    }

    @Override
    public void deleteById(long id) {
        goodsDao.deleteById(id);
    }

    @Override
    public void insert(Good good, long id) {
        goodsDao.insert(good, id);
    }

    @Override
    public void updateById(Good good) {
        goodsDao.updateById(good);
    }
}
