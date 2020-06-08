package com.miedviediev.cs.Homework2;

import com.miedviediev.cs.Practice1.Cryption.ServerTCPKeys;
import com.miedviediev.cs.Practice1.Cryption.UDPEncoding;
import com.miedviediev.cs.Practice1.PackageInfo;
import com.miedviediev.cs.Practice3.Servers.StoreServerUDP;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Sender {

    private static Sender sender;

    public static Sender getInstance() {
        if(sender == null)
            sender = new Sender();
        return sender;
    }

    private Sender() {};

    public void sendPackage(SocketChannel sc, PackageInfo msg) {
        try {
            sc.write(ByteBuffer.wrap(ServerTCPKeys.getInstance().encodePackage(sc.socket() ,msg)));
        } catch (IOException e){
            System.err.println("Problem with writing answer to client!");
        }
        System.out.println("Send answer to " + sc);
    }

    public void sendBack(SocketChannel sc, byte[] msg) {
        try {
            sc.write(ByteBuffer.wrap(msg));
        } catch (IOException e){
            System.err.println("Problem with writing answer to client!");
        }
    }

    public void sendUDPPackage(PackageInfo process, SocketAddress address) {
        try {
            StoreServerUDP.getInstance().channel.send(
                    ByteBuffer.wrap(UDPEncoding.getInstance().encodePackage(process)), address);
        } catch (IOException e) {
            System.out.println("Problem with writing answer to client!");
        }
    }
}
