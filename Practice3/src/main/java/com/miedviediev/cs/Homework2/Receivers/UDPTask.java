package com.miedviediev.cs.Homework2.Receivers;

import com.miedviediev.cs.Homework2.Processor;
import com.miedviediev.cs.Homework2.Sender;
import com.miedviediev.cs.Practice1.Cryption.ServerTCPKeys;
import com.miedviediev.cs.Practice1.Cryption.UDPEncoding;
import com.miedviediev.cs.Practice1.PackageInfo;

import java.net.InetAddress;
import java.net.SocketAddress;

public class UDPTask implements Runnable {

    byte[] msg;
    SocketAddress address;

    public UDPTask(byte[] msg, SocketAddress address) {
        this.msg = msg;
        this.address = address;
    }

    @Override
    public void run() {
        PackageInfo p = UDPEncoding.getInstance().decodePackage(msg);
        Sender.getInstance().sendUDPPackage(Processor.getInstance().process(p), address);
    }
}
