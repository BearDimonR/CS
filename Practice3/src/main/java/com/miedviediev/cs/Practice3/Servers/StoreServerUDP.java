package com.miedviediev.cs.Practice3.Servers;

import com.miedviediev.cs.Homework2.Receivers.ServerReceiver;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class StoreServerUDP implements Runnable {

    public static void main(String[] args) {
        StoreServerUDP.getInstance();
    }

    public final int port;
    public DatagramChannel channel;
    private ByteBuffer buffer = ByteBuffer.allocate(ServerReceiver.MAX_LEN);

    private static StoreServerUDP instance;

    public synchronized static StoreServerUDP getInstance(){
        if(instance == null)
            instance = new StoreServerUDP();
        return instance;
    }

    private StoreServerUDP() {
        port = StoreServerTCP.PORT;
        new Thread(this).start();
    }

    public void run() {
        try {
            initServer();
            System.out.println("Server is running on port: " + port);
            while (true) {
                try {
                    read();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read() throws IOException {
        buffer.clear();
        DatagramPacket packet = new DatagramPacket(buffer.array(), buffer.capacity());
        channel.socket().receive(packet);
        buffer.flip();
        byte[] res = new byte[buffer.limit()];
        ServerReceiver.getInstance().receiveUDPPackage(res, packet.getSocketAddress());
    }

    private void initServer() throws IOException {
        channel = DatagramChannel.open();
        InetSocketAddress isa = new InetSocketAddress(port);
        channel.socket().bind(isa);
    }
}
