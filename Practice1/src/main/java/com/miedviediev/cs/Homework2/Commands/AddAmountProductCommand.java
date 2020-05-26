package com.miedviediev.cs.Homework2.Commands;

import com.miedviediev.cs.Homework2.Models.Storage;

public class AddAmountProductCommand implements ICommand {
    @Override
    public synchronized String execute(String[] message) {
        return Storage.getInstance().addAmount(Integer.valueOf(message[0]), Integer.valueOf(message[1]));
    }
}
