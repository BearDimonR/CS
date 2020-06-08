package com.miedviediev.cs.Practice3.Servers;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class StoreServerTCP implements Runnable
{

    public static void main( String args[] ) {
        new StoreServerTCP(228);
    }

    private final int port;
    private final ByteBuffer buffer = ByteBuffer.allocate( 16384 );
    private Selector selector;
    private ServerSocket serverSocket;

    public StoreServerTCP( int port ) {
        this.port = port;
        new Thread( this ).start();
    }

    public void run() {
        try {
            initServer();

            while (true) {
                if (selector.select() == 0)
                    continue;
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    if ((key.isAcceptable()))
                        handleAccept();
                    else if (key.isReadable())
                        handleRead(key);
                }
                keys.clear();
            }
        } catch(IOException e) {
            System.err.println(e);
        }
    }

    private void handleRead(SelectionKey key) throws IOException {

    }

    private void handleAccept() throws IOException {
        try {

            Socket s = serverSocket.accept();
            System.out.println("Got connection from " + s);

            SocketChannel sc = s.getChannel();
            sc.configureBlocking(false);

            sc.register(selector, SelectionKey.OP_READ);
        } catch (IOException e){
            System.err.println("Problem with accepting connection!");
            throw e;
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
        } catch (IOException e){
            System.err.println("Problem with starting-up server!");
            throw e;
        }
    }

    private boolean processInput(SocketChannel sc) throws IOException {
        buffer.clear();
        sc.read(buffer);
        buffer.flip();

        if (buffer.limit()==0)
            return false;

        sc.write( buffer );


        return true;
    }
}

