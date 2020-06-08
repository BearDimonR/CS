package com.miedviediev.cs.Homework2.Commands;

import java.security.InvalidParameterException;
import java.sql.SQLException;

public interface ICommand {
    String execute(String[] message) throws SQLException, InvalidParameterException;
}
