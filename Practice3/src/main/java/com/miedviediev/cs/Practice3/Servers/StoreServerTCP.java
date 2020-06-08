package com.miedviediev.cs.Practice3.Servers;

import com.miedviediev.cs.Database.Connector;
import com.miedviediev.cs.Database.GroupDAO;
import com.miedviediev.cs.Homework2.Receivers.ServerReceiver;
import com.miedviediev.cs.Practice1.Cryption.ServerTCPKeys;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.sql.SQLException;
import java.util.*;

public class StoreServerTCP implements Runnable {

    public final static int PORT = 10200;
    ByteBuffer buffer = ByteBuffer.allocate(ServerReceiver.MAX_LEN);

    public static void main(String args[]) {
        new StoreServerTCP(PORT);
    }

    private final int port;
    private Selector selector;
    private ServerSocket serverSocket;

    public StoreServerTCP(int port) {
        this.port = port;
        new Thread(this).start();
    }

    public void run() {
        try {
            initServer();
            ServerTCPKeys.getInstance();
            Connector.initConnector();

            while (true) {
                if (selector.select() == 0)
                    continue;
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    if(!key.isValid()) keys.remove(key);
                    if ((key.isAcceptable()))
                        handleAccept();
                    else if (key.isReadable())
                        handleRead(key);
                }
                keys.clear();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private void handleRead(SelectionKey key) {
        SocketChannel sc = null;
        try {
            sc = (SocketChannel) key.channel();
            buffer.clear();
            sc.read(buffer);
            buffer.flip();
            if (buffer.limit() == 0) {
                key.cancel();
                Socket s = null;
                try {
                    s = sc.socket();
                    s.close();

                } catch (IOException e) {
                    System.err.println("Error closing socket in reading:" + s);
                }
                ServerTCPKeys.getInstance().checkKeys();
                System.out.println("Closed: " + sc);
                return;
            }
            byte[] res = new byte[buffer.limit()];
            buffer.get(res, 0, buffer.limit());
            ServerReceiver.getInstance().receivePackage(sc, res);
        } catch (IOException e) {
            key.cancel();
            try {
                sc.close();
            } catch (IOException e1) {
                System.err.println("Problem with closing SocketChannel in reading!");
            }
            ServerTCPKeys.getInstance().checkKeys();
            System.out.println("Closed: " + sc);
        }
    }

    private void handleAccept() throws IOException {
        try {

            Socket s = serverSocket.accept();
            System.out.println("Got connection from " + s);
            initKey(s);
            SocketChannel sc = s.getChannel();
            sc.configureBlocking(false);
            sc.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            System.err.println("Problem with accepting connection!");
            throw e;
        }
    }

    private void initKey(Socket s) throws IOException {
        try {
            SocketChannel sc = s.getChannel();
            OutputStream out = s.getOutputStream();
            out.write(ServerTCPKeys.getInstance().getPublicKey());
            buffer.clear();
            sc.read(buffer);
            buffer.flip();
            while (buffer.limit() == 0) {
                Thread.yield();
                buffer.clear();
                sc.read(buffer);
                buffer.flip();
            }
            byte[] res = new byte[buffer.limit()];
            buffer.get(res);
            out.write(ServerTCPKeys.getInstance().getServerKey(
                    KeyFactory.getInstance(ServerTCPKeys.ALGORITHMPAIR).generatePublic(new X509EncodedKeySpec(res))));
            buffer.clear();
            sc.read(buffer);
            buffer.flip();
            while (buffer.limit() == 0) {
                Thread.yield();
                buffer.clear();
                sc.read(buffer);
                buffer.flip();
            }
            res = new byte[buffer.limit()];
            buffer.get(res);
            ServerTCPKeys.getInstance().addKey(s, res);
            System.out.println("Keys successful");
        } catch (IOException e) {
            System.err.println("Problem with network while creating key");
            throw e;
        } catch (Exception e) {
            System.err.println("Problem with algorithm");
            System.err.println(e.getMessage());
        }
    }

    private void initServer() throws IOException {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            serverSocket = ssc.socket();
            serverSocket.bind(new InetSocketAddress(port));

            selector = Selector.open();

            ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server was started on port: " + port);
        } catch (IOException e) {
            System.err.println("Problem with starting-up server!");
            throw e;
        }
    }
}