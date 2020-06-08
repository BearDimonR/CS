package lection2.demo4;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws Exception {
        new Data().solve();
    }

}
class Data {
    public void solve() throws Exception
    {
        List<Future<String>> list =
                new ArrayList<Future<String>>();

        //отримуємо для себе пул потоків фіксованого розміру
        //потоки запущені їх непотрібно запускати
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 7; i++)
            //потоки запущені тому ми їм просто передаємо роботу
            //метод submit повертає клас Future
            list.add(es.submit(new MyCallable<String>()));

        //дає всім допрацювати
        es.shutdown();
        //припиняє одразу...
        //Thread.sleep(1000);
        //es.shutdownNow();

        for (Future<String> f : list)
            //в цей моммент відбувається блокування і я чекаю результату
            System.out.println(f.get());
    }
}
//теж, що й run але вміє повертати значення
class MyCallable<T> implements Callable {

    @Override
    public T call() throws Exception {

        String res = Thread.currentThread().getName();
        Thread.sleep(1000);
        System.out.println("done");
        return (T)res;
    }
}