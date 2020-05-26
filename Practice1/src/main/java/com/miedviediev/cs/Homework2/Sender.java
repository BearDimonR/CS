package com.miedviediev.cs.Homework2;

import com.miedviediev.cs.Practice1.MessageDecoder;
import com.miedviediev.cs.Practice1.PackageInfo;

public class Sender {

    private static Sender sender;

    public static Sender getInstance() {
        if(sender == null)
            sender = new Sender();
        return sender;
    }

    private Sender() {};

    public void sendPackage(byte[] msg) {
        System.out.println("Send answer: " + MessageDecoder.getInstance().decodePackage(msg));
    }
}
