package ua.com.ke4a_store.dao;

import ua.com.ke4a_store.entity.User;

import java.util.Optional;

public interface UserDao {
    Optional<User> findByLoginAndPassword(String login, String password);
}
