package com.miedviediev.cs.Homework2.Commands;

import com.miedviediev.cs.Homework2.Models.Storage;
import com.miedviediev.cs.Homework2.Models.StorageHandler;

import java.security.InvalidParameterException;
import java.sql.SQLException;

public class AddAmountProductCommand implements ICommand {
    @Override
    public synchronized String execute(String[] message) throws SQLException, InvalidParameterException {
        return StorageHandler.getInstance().addAmount(Integer.valueOf(message[0]), Float.valueOf(message[1]));
    }
}
