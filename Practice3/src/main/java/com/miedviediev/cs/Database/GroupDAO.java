package com.miedviediev.cs.Database;

import com.miedviediev.cs.Homework2.Models.GroupProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GroupDAO implements DAO<GroupProduct> {

    public static GroupDAO instance;

    public synchronized static GroupDAO getInstance() {
        if (instance == null)
            instance = new GroupDAO();
        return instance;
    }

    private Connection connection;

    private GroupDAO() {
    }

    @Override
    public Optional<GroupProduct> get(long id) throws SQLException {
        try {
            connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM group_products WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next())
                return Optional.of(new GroupProduct(
                        res.getLong(1),
                        res.getString(2),
                        res.getString(3)));
            return Optional.empty();
        } finally {
            Connector.getInstance().releaseConnection(connection);
            connection = null;
        }
    }

    @Override
    public Optional<Object> get(long id, String field) throws SQLException {
        try {
            connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ? FROM group_products WHERE id=?");
            preparedStatement.setString(1, field);
            preparedStatement.setLong(2, id);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next())
                return Optional.of(res.getObject(1));
            return Optional.empty();
        } finally {
            Connector.getInstance().releaseConnection(connection);
            connection = null;
        }
    }

    @Override
    public List<Object> getAll(String field) throws SQLException {
        try {
            connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT "+field+" FROM group_products");
            ResultSet res = preparedStatement.executeQuery();
            List<Object> groups = new ArrayList<>();
            while (res.next()) {
                groups.add(res.getObject(1));
            }
            return groups;
        } finally {
            Connector.getInstance().releaseConnection(connection);
            connection = null;
        }    }

    @Override
    public List<GroupProduct> getAll() throws SQLException {
        try {
            connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM group_products");
            ResultSet res = preparedStatement.executeQuery();
            List<GroupProduct> groups = new ArrayList<>();
            while (res.next()) {
                groups.add(new GroupProduct(
                        res.getLong(1),
                        res.getString(2),
                        res.getString(3)));
            }
            return groups;
        } finally {
            Connector.getInstance().releaseConnection(connection);
            connection = null;
        }
    }

    @Override
    public boolean save(GroupProduct group) throws SQLException {
        try {
            connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO group_products (name, description) VALUES (?,?)");
            preparedStatement.setString(1, group.getName());
            preparedStatement.setString(2, group.getDescription());
            int res = preparedStatement.executeUpdate();
            return res != 0;
        } finally {
            Connector.getInstance().releaseConnection(connection);
            connection = null;
        }
    }

    @Override
    public boolean update(long id, String query) throws SQLException {
        try {
            connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE group_products SET " + query + " WHERE id = ?");
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() != 0;
        } finally {
            Connector.getInstance().releaseConnection(connection);
            connection = null;
        }
    }

    @Override
    public boolean delete(long id) throws SQLException {
        try {
            connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM group_products WHERE id = ?");
            preparedStatement.setLong(1, id);
            int res = preparedStatement.executeUpdate();
            return res != 0;
        } finally {
            Connector.getInstance().releaseConnection(connection);
            connection = null;
        }
    }
}
