package com.miedviediev.cs.Homework2.Commands;

import com.miedviediev.cs.Homework2.Models.StorageHandler;

import java.security.InvalidParameterException;
import java.sql.SQLException;

public class AddGroupCommand implements ICommand {
    @Override
    public synchronized String execute(String[] message) throws SQLException, InvalidParameterException {
        return StorageHandler.getInstance().addGroup(message[0], message[1]);
    }
}
