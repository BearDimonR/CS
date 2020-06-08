package ua.com.ke4a_store.dao.impl;

import ua.com.ke4a_store.dao.GroupsDao;
import ua.com.ke4a_store.entity.GoodsGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupsDaoImpl extends GenericDaoImpl<GoodsGroup> implements GroupsDao {

    public GroupsDaoImpl(Connector connector) {
        super("ggroups", connector);
    }

    @Override
    public GoodsGroup mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");

        return new GoodsGroup(id, name);
    }

    @Override
    public void insert(GoodsGroup item) {
        try (Connection connection = connector.getConnection()) {
            //Try-with-resource

            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT ggroups(name) VALUES (?)");


            preparedStatement.setString(1,item.getName());


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateById(GoodsGroup item) {
        try (Connection connection = connector.getConnection()) {
            //Try-with-resource

            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE ggroups SET name=? WHERE id=?");


            preparedStatement.setLong(2,item.getId());
            preparedStatement.setString(1,item.getName());


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<GoodsGroup> mapResultSetToList(ResultSet resultSet) throws SQLException
    {
        List<GoodsGroup> groups = new ArrayList<>();

        while (resultSet.next()) {
            groups.add(mapResultSetToEntity(resultSet));
            groups.get(groups.size()-1)
                    .addAll(new GoodsDaoImpl(connector).findByGroupId(groups.get(groups.size()-1).getId()));
        }

        return groups;
    }
}
