package ua.com.ke4a_store.service.impl;

import ua.com.ke4a_store.dao.UserDao;
import ua.com.ke4a_store.entity.User;
import ua.com.ke4a_store.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return userDao.findByLoginAndPassword(login, password);
    }
}
