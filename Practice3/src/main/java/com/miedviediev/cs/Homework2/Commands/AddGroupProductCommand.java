package com.miedviediev.cs.Homework2.Commands;

import com.miedviediev.cs.Homework2.Models.StorageHandler;

import java.security.InvalidParameterException;
import java.sql.SQLException;

public class AddGroupProductCommand implements ICommand {
    @Override
    public synchronized String execute(String[] message) throws SQLException, InvalidParameterException {
        return StorageHandler.getInstance().setGroup(Integer.valueOf(message[0]), Integer.valueOf(message[1]));
    }
}
