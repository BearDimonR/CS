package ua.com.ke4a_store.service;

import ua.com.ke4a_store.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByLoginAndPassword(String login, String password);
}
