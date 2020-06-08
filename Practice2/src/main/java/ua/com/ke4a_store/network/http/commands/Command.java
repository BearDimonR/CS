package ua.com.ke4a_store.network.http.commands;

import java.sql.SQLException;

public interface Command {

    void callCommand() throws SQLException;

    String getCommand();
}
