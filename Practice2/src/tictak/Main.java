package tictak;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {

//        Data d = new Data();
//
//        Worker w1 = new Worker(1, d);
//        Worker w2 = new Worker(2, d);
//        Worker w3 = new Worker(3, d);
//
//        w3.join();
//        System.out.println("end of main...");
        ArrayList<Test> arrayList = new ArrayList<>();
        arrayList.add(new Test(1, "super"));
        arrayList.add(new Test(2, "test"));

        Thread w1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Test test:arrayList) {
                    test.id +=1;
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread w2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (Test test:arrayList) {
                    test.name +=" hi";
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        w1.start();
        w2.start();
        w2.join();
        w1.join();
        for (Test test:arrayList) {
            System.out.println(test);
        }
        System.out.println(Test.counter);
    }
}

class Test {
    public static int counter = 0;
    public int uniq;
    public int id;
    public String name;

    public Test(int id, String name) {
        this.id = id;
        this.name = name;
        uniq = ++counter;
    }

    @Override
    public String toString() {
        return "Test{" +
                "uniq=" + uniq +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
