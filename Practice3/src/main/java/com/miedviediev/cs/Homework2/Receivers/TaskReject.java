package com.miedviediev.cs.Homework2.Receivers;

import com.miedviediev.cs.Homework2.Processor;
import com.miedviediev.cs.Homework2.Sender;
import com.miedviediev.cs.Practice1.Cryption.ServerTCPKeys;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class TaskReject implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Task was rejected, because pool is full");
        Task rejected = (Task) r;
        Sender.getInstance().sendBack(rejected.sc, rejected.msg);

    }
}
