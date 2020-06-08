package ua.com.ke4a_store.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.ke4a_store.dao.GoodsDao;
import ua.com.ke4a_store.entity.Good;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GoodsServiceImplTest {

    @Mock
    private GoodsDao goodsDao;
    @InjectMocks
    private GoodsServiceImpl goodsService;


    private Good good() {
        return new Good(1L, "cola",5,5,1L);
    }

    private Good[] goods() {
        return new Good[]{
                good(),
                good()
        };
    }

    @Test
    public void shouldFindAll() {
        List<Good> goods = Arrays.asList(goods());

        when(goodsDao.findAll()).thenReturn(goods);

        List<Good> actual = goodsService.findAll();
        List<Good> expected = Arrays.asList(goods());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindByGroupId() {
        List<Good> goods = Arrays.asList(goods());

        when(goodsDao.findByGroupId(1L)).thenReturn(goods);

        List<Good> actual = goodsService.findByGroupId(1L);
        List<Good> expected = Arrays.asList(goods());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindByName() {
        List<Good> goods = Arrays.asList(goods());

        when(goodsDao.findByName("cola")).thenReturn(goods);

        List<Good> actual = goodsService.findByName("cola");
        List<Good> expected = Arrays.asList(goods());

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindById() {
        Good good = good();

        when(goodsDao.findById(1L)).thenReturn(good);

        Good actual = goodsDao.findById(1L);
        Good expected = good();

        assertEquals(expected, actual);
    }
}