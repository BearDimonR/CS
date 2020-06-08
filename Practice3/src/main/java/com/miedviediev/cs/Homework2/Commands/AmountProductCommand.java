package com.miedviediev.cs.Homework2.Commands;

import com.miedviediev.cs.Homework2.Models.StorageHandler;

import java.security.InvalidParameterException;
import java.sql.SQLException;

public class AmountProductCommand implements ICommand {
    @Override
    public String execute(String[] message) throws SQLException, InvalidParameterException {
        return StorageHandler.getInstance().getAmount(Integer.valueOf(message[0]));
    }
}
