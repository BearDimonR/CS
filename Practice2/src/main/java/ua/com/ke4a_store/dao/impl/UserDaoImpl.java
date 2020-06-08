package ua.com.ke4a_store.dao.impl;

import ua.com.ke4a_store.dao.UserDao;
import ua.com.ke4a_store.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private Connector connector;

    public UserDaoImpl(Connector connector) {
        this.connector = connector;
    }

    public User mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String login = resultSet.getString("login");
        String password = resultSet.getString("pssword");

        return new User(id, login, password);
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        User user = null;

        try (Connection connection = connector.getConnection()) {
            //Try-with-resources
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM users WHERE login=? AND pssword=?");

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = (mapResultSetToEntity(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(user);
    }
}
