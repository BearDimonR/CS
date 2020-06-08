package com.miedviediev.cs.Practice3.Clients;

import com.miedviediev.cs.Homework2.Commands.ECommands;
import com.miedviediev.cs.Practice1.Cryption.UDPEncoding;
import com.miedviediev.cs.Practice1.PackageInfo;
import com.miedviediev.cs.Practice3.Servers.StoreServerTCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class StoreClientUDP implements Runnable {
    public static void main(String[] args) {
        for (int i = 0; i < 5; ++i) {
            new Thread(new StoreClientUDP("localhost", StoreServerTCP.PORT)).start();
        }
    }

    private static int counter = 0;

    private int port;
    private int id;
    private String host;

    private DatagramSocket s;
    private InputStream in;
    private OutputStream out;

    public StoreClientUDP(String host, int port) {
        this.host = host;
        this.id = ++counter;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            s = new DatagramSocket();
            while (true) {

                byte[] buf = UDPEncoding.getInstance().encodePackage(new PackageInfo((byte) 0,
                        ECommands.AMOUNT_OF_PRODUCT.getId(), 1, "2"));
                InetAddress address = InetAddress.getByName(host);
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
                s.send(packet);
                System.out.println("Sended: " + packet.getAddress().toString() + "   " + packet.getPort());
                s.receive(packet);
                System.out.println("Received: " + UDPEncoding.getInstance().decodePackage(packet.getData()));
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
