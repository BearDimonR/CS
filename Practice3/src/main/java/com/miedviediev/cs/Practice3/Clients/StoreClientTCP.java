package com.miedviediev.cs.Practice3.Clients;

import com.miedviediev.cs.Homework2.Commands.ECommands;
import com.miedviediev.cs.Practice1.Cryption.ClientTCPKeys;
import com.miedviediev.cs.Practice1.Cryption.MessageEncoder;
import com.miedviediev.cs.Practice1.PackageInfo;
import com.miedviediev.cs.Practice3.Servers.StoreServerTCP;

import java.io.*;
import java.net.SocketException;

import java.net.Socket;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;

public class StoreClientTCP implements Runnable {

    public static void main(String[] args) {
        for (int i = 0; i < 3; ++i) {
            new Thread(new StoreClientTCP("localhost", StoreServerTCP.PORT)).start();
        }
    }

    private static int counter = 0;

    private int port;
    private int id;
    private String host;

    private Socket s;
    private InputStream in;
    private OutputStream out;

    public StoreClientTCP(String host, int port) {
        this.host = host;
        this.id = ++counter;
        this.port = port;
    }

    public void run() {
        try {
            s = new Socket(host, port);
            out = s.getOutputStream();
            in = s.getInputStream();
            initKey();
            doAction();
        } catch (SocketException e) {
            System.err.println("Connection lost...Trying to connect again");
            Thread.yield();
            run();
        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initKey() throws IOException {
        try {
            while (in.available() == 0) Thread.yield();
            byte[] res = new byte[in.available()];
            in.read(res);
            byte[] key = ClientTCPKeys.getInstance().getClientKey(
                    KeyFactory.getInstance(MessageEncoder.ALGORITHMPAIR).generatePublic(new X509EncodedKeySpec(res)));
            out.write(ClientTCPKeys.getInstance().getPublicKey());
            while (in.available() == 0) Thread.yield();
            res = new byte[in.available()];
            in.read(res);
            out.write(key);
            ClientTCPKeys.getInstance().addServerKey(res);
            System.out.println("Keys successful");
        } catch (IOException e) {
            System.err.println("Problem with network while creating key");
            throw e;
        } catch (Exception e) {
            System.err.println("Problem with alhorithm");
            System.err.println(e.getMessage());
        }

    }

    private void doAction() throws IOException, ClassNotFoundException, InterruptedException {
        while (true) {
            out.write(ClientTCPKeys.getInstance().encodePackage(new PackageInfo((byte) 0,
                    ECommands.AMOUNT_OF_PRODUCT.getId(), 1, "2")));
            System.out.println("Client " + id + " send message");
            while (in.available() == 0) Thread.yield();
            byte[] res = new byte[in.available()];
            in.read(res);
            PackageInfo p = ClientTCPKeys.getInstance().decodePackage(res);
            System.out.println("Client " + id + " received message: " + p);
            Thread.sleep(2000);
        }
    }


}
