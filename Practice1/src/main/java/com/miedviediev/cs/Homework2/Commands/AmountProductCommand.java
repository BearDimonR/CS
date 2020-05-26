package com.miedviediev.cs.Homework2.Commands;


import com.miedviediev.cs.Homework2.Models.Storage;

public class AmountProductCommand implements ICommand {
    @Override
    public String execute(String[] message) {
        return Storage.getInstance().getAmount(Integer.valueOf(message[0]));
    }
}
