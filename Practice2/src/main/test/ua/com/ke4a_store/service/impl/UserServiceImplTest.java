package ua.com.ke4a_store.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.com.ke4a_store.dao.UserDao;
import ua.com.ke4a_store.entity.User;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void shouldFindByLoginAndPassword() {
        Optional<User> optionalAdmin = Optional.ofNullable(new User(1, "igor", "12345"));

        when(userDao.findByLoginAndPassword("igor", "12345")).thenReturn(optionalAdmin);

        User actual = userDao.findByLoginAndPassword("igor", "12345").get();
        User expected = new User(1, "igor", "12345");

        Assert.assertEquals(expected, actual);
    }
}