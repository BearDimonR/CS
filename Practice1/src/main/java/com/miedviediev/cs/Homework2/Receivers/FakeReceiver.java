package com.miedviediev.cs.Homework2.Receivers;

import com.miedviediev.cs.Practice1.MessageEncoder;
import com.miedviediev.cs.Practice1.PackageInfo;

import java.util.Random;
import java.util.concurrent.*;

public class FakeReceiver implements IReceiver {

    private static FakeReceiver receiver;

    public static FakeReceiver getInstance() {
        if(receiver == null)
            receiver = new FakeReceiver();
        return receiver;
    }

    private static Random random = new Random();

    private ThreadPoolExecutor pool;
    private PackageInfo msg;

    private FakeReceiver() {
        pool = new ThreadPoolExecutor(
                4, Runtime.getRuntime().availableProcessors() + 1, 5, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(100), Executors.defaultThreadFactory(),
                new TaskReject()
        );
    }

    public void receivePackage() {
        pool.execute(new Task(MessageEncoder.getInstance().encodePackage(msg)));
    }

    public PackageInfo generateMsg(int command, String msg) {
        this.msg = new PackageInfo((byte) random.nextInt(100), command, random.nextInt(100), msg);
        return this.msg;
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
}
