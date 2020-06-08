package ua.com.ke4a_store.network.http.commands.impl;

import ua.com.ke4a_store.dao.impl.Connector;
import ua.com.ke4a_store.dao.impl.GoodsDaoImpl;
import ua.com.ke4a_store.entity.Good;
import ua.com.ke4a_store.network.http.commands.Command;

import java.sql.*;

public class GoodsHandler implements Command {
    private Connection connection;
    private int id;

    public GoodsHandler(int id) {
        this.id = id;
    }


    public boolean createConnection(Connection connection) {
        this.connection = connection;
        return true;
    }


    public boolean createConnection(String url, String username, String password) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void callCommand() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM goods WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();

        rs.next();
    }

    public void callCommand(String commandTitle) {
        GoodsDaoImpl goodsDao = new GoodsDaoImpl(new Connector());
        switch (commandTitle) {
            case "update":
                goodsDao.updateById(new Good(0,commandTitle, Integer.valueOf(commandTitle),Integer.reverse(id),0));
                break;
            case "delete":
                goodsDao.updateById(new Good(0,commandTitle, Integer.valueOf(commandTitle),Integer.reverse(id),0));
                break;
            case "create":
                goodsDao.insert(new Good(0,commandTitle, Integer.valueOf(commandTitle),Integer.reverse(id),0),3);
                break;
        }
    }

    public String getCommand() {
        return null;
    }
}
