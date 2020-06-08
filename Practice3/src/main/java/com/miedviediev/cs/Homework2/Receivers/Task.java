package com.miedviediev.cs.Homework2.Receivers;

import com.miedviediev.cs.Homework2.Processor;
import com.miedviediev.cs.Homework2.Sender;
import com.miedviediev.cs.Practice1.Cryption.ServerTCPKeys;
import com.miedviediev.cs.Practice1.PackageInfo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.channels.SocketChannel;

public class Task implements Runnable {

    SocketChannel sc;
    byte[] msg;

    public Task(SocketChannel sc, byte[] msg) {
        this.sc = sc;
        this.msg = msg;
    }

    @Override
    public void run() {
            PackageInfo p = ServerTCPKeys.getInstance().decodePackage(sc.socket(), msg);
            Sender.getInstance().sendPackage(sc, Processor.getInstance().process(p));
    }

}
