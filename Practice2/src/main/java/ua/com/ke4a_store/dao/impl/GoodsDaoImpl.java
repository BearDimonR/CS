package ua.com.ke4a_store.dao.impl;

import ua.com.ke4a_store.dao.GoodsDao;
import ua.com.ke4a_store.entity.Good;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDaoImpl extends GenericDaoImpl<Good> implements GoodsDao {

    public GoodsDaoImpl(Connector connector) {
        super("goods", connector);
    }

    @Override
    public Good mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        int price = resultSet.getInt("price");
        int count = resultSet.getInt("count");
        long gid = resultSet.getLong("groupid");

        return new Good(id, name, price, count,gid);
    }

    @Override
    public void insert(Good     item, long groupid) {
        try (Connection connection = connector.getConnection()) {
            //Try-with-resource

            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT goods(id,name,price,count,groupid) VALUES (?,?,?,?,?)");


            preparedStatement.setLong(1,item.getId());
            preparedStatement.setString(2,item.getName());
            preparedStatement.setInt(3,item.getPrice());
            preparedStatement.setInt(4,item.getCount());
            preparedStatement.setLong(5,groupid);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateById(Good item) {
        try (Connection connection = connector.getConnection()) {
            //Try-with-resource

            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE goods SET name=?,price=?,count=?,groupid=? WHERE id=?");

            preparedStatement.setString(1,item.getName());
            preparedStatement.setInt(2,item.getPrice());
            preparedStatement.setInt(3,item.getCount());
            preparedStatement.setLong(4,item.getGroupId());
            preparedStatement.setLong(5,item.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Good findById(long id) {
        throw new UnsupportedOperationException("HAVENT OVERRIDED");
    }

    @Override
    public List<Good> findByName(String partName) {

        partName = "%"+partName+"%";

        try (Connection connection = connector.getConnection()) {
            //Try-with-resources
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM " + table + " WHERE name LIKE ?");

            preparedStatement.setString(1, partName);

            ResultSet resultSet = preparedStatement.executeQuery();

            return mapResultSetToList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();

    }

    @Override
    public List<Good> findByGroupId(long id) {
        try (Connection connection = connector.getConnection()) {
            //Try-with-resources
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM " + table + " WHERE groupid=?");

            preparedStatement.setLong(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            return mapResultSetToList(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

}
