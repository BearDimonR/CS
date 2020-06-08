package ua.com.ke4a_store.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.ke4a_store.dao.GroupsDao;
import ua.com.ke4a_store.entity.GoodsGroup;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GroupsServiceImplTest {

    @Mock
    private GroupsDao groupsDao;
    @InjectMocks
    private GroupsServiceImpl groupsService;


    private GoodsGroup group() {
        return new GoodsGroup(1L, "beverages");
    }

    private GoodsGroup[] groups() {
        return new GoodsGroup[]{
                group(),
                group()
        };
    }

    @Test
    public void shouldFindAll() {
        List<GoodsGroup> group = Arrays.asList(groups());

        when(groupsDao.findAll()).thenReturn(group);

        List<GoodsGroup> actual = groupsService.findAll();
        List<GoodsGroup> expected = Arrays.asList(groups());

        assertEquals(expected, actual);
    }
}