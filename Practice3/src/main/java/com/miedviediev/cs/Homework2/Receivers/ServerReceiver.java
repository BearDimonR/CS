package com.miedviediev.cs.Homework2.Receivers;

import com.miedviediev.cs.Practice1.PackageInfo;

import java.net.InetAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerReceiver {

    public final static int MAX_LEN = 16600;

    private static ServerReceiver receiver;

    public static ServerReceiver getInstance() {
        if(receiver == null)
            receiver = new ServerReceiver();
        return receiver;
    }

    private ThreadPoolExecutor pool;
    private PackageInfo msg;

    private ServerReceiver() {
        pool = new ThreadPoolExecutor(
                4, Runtime.getRuntime().availableProcessors() + 1, 5, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(100), Executors.defaultThreadFactory(),
                new TaskReject()
        );
    }

    public void receivePackage(SocketChannel sc, byte[] bytes) {
        pool.execute(new Task(sc, bytes));
    }


    public PackageInfo getMsg() {
        return msg;
    }

    public void shutDownPool() {
        pool.shutdown();
        try {
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
        }
    }

    public void receiveUDPPackage(byte[] res, SocketAddress address) {
        pool.execute(new UDPTask(res, address));
    }
}

