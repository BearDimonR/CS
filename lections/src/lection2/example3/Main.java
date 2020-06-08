package example3;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Data d = new Data();

        ExecutorService es = Executors.newFixedThreadPool(5);
        for(int i=0; i<5; i++)
            es.submit(new WorkWData(d));


        TimeUnit.SECONDS.sleep(3);
        es.shutdown();
        //es.shutdownNow();
        es.awaitTermination(10000, TimeUnit.MILLISECONDS);
    }
}

class WorkWData implements Runnable {
    Data obj;
    WorkWData(Data d) {
        obj=d;
     }
    public void run() {
        int n;
        n=obj.read();
        System.out.println("First read"+" "+Thread.currentThread().getName()+" "+new Integer(n).toString());
        obj.write();
        System.out.println("Write"+" "+Thread.currentThread().getName()+" "+new Integer(n).toString());
        n=obj.read();
        System.out.println("Second read"+" "+Thread.currentThread().getName()+" "+new Integer(n).toString());
    }
}

class Data {
    int count=1;

    int read(){
        try {
            int n = count;
            TimeUnit.MILLISECONDS.sleep(300);
            count=n;
        } catch (InterruptedException ex) { }
        return count;
    }
    void write(){
        try {
            int n = count;
            TimeUnit.MILLISECONDS.sleep(300);
            n++;
            count=n;
        } catch (InterruptedException ex) { }
    }
}

