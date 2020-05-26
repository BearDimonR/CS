package com.miedviediev.cs.Homework2.Receivers;

import com.miedviediev.cs.Homework2.Processor;
import com.miedviediev.cs.Homework2.Sender;
import com.miedviediev.cs.Practice1.MessageDecoder;
import com.miedviediev.cs.Practice1.PackageInfo;

public class Task implements Runnable {

    private byte[] msg;

    public Task(byte[] msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        PackageInfo p = MessageDecoder.getInstance().decodePackage(msg);
        Sender.getInstance().sendPackage(Processor.getInstance().process(p));
    }

    public byte[] getMsg() {
        return msg;
    }
}
