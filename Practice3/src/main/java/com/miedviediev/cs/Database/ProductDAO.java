package com.miedviediev.cs.Database;

import com.miedviediev.cs.Homework2.Models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAO implements DAO<Product> {
    public static ProductDAO instance;

    public synchronized static ProductDAO getInstance() {
        if (instance == null)
            instance = new ProductDAO();
        return instance;
    }

    private Connection connection;

    private ProductDAO() {
    }

    @Override
    public Optional<Product> get(long id) throws SQLException {
        try {
            connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next())
                return Optional.of(new Product(
                        res.getLong(1),
                        res.getString(2),
                        res.getFloat(3),
                        res.getFloat(4),
                        res.getLong(5),
                        res.getString(6)));
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT ? FROM product WHERE id=?");
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT "+field+" FROM product");
            ResultSet res = preparedStatement.executeQuery();
            List<Object> groups = new ArrayList<>();
            while (res.next())
                groups.add(res.getObject(1));
            return groups;
        } finally {
            Connector.getInstance().releaseConnection(connection);
            connection = null;
        }
    }

    @Override
    public List<Product> getAll() throws SQLException {
        try {
            connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product");
            ResultSet res = preparedStatement.executeQuery();
            List<Product> groups = new ArrayList<>();
            while (res.next()) {
                groups.add(new Product(
                        res.getLong(1),
                        res.getString(2),
                        res.getFloat(3),
                        res.getFloat(4),
                        res.getLong(5),
                        res.getString(6)));
            }
            return groups;
        } finally {
            Connector.getInstance().releaseConnection(connection);
            connection = null;
        }
    }

    @Override
    public boolean save(Product group) throws SQLException {
        try {
            connection = Connector.getInstance().getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement
                            ("INSERT INTO product (name, price, amount, group_products_id, description) " +
                                    "VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, group.getName());
            preparedStatement.setFloat(2, group.getPrice());
            preparedStatement.setFloat(3, group.getAmount());
            preparedStatement.setLong(4, group.getGroupProduct());
            preparedStatement.setString(5, group.getDescription());
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
                    connection.prepareStatement
                            ("UPDATE product SET " +
                                    query +
                                    " WHERE id = ?");
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM product WHERE id = ?");
            preparedStatement.setLong(1, id);
            int res = preparedStatement.executeUpdate();
            return res != 0;
        } finally {
            Connector.getInstance().releaseConnection(connection);
            connection = null;
        }
    }
}