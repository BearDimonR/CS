package com.miedviediev.cs.Homework2.Commands;

import com.miedviediev.cs.Homework2.Models.Storage;

public class AddGroupCommand implements ICommand {
    @Override
    public synchronized String execute(String[] message) {
        return Storage.getInstance().addGroup(message[0], message[1]);
    }
}
