package example2;

class Data{
    int count =0;
    static int countSt =0;
}

class MyThread implements Runnable {
  
    Data obj;
    
    MyThread(Data d){
        obj = d;
        new Thread(this).start();
    }
    void add(){
        try {
            Thread.sleep(10);
            int n=obj.count;
            n++;
            Thread.sleep(10);
            obj.count=n;
        } catch (InterruptedException ex) {       }
    }
    static void addStatic() {
                try {
            Thread.sleep(10);
            int n=Data.countSt;
            n++;
            Thread.sleep(10);
            Data.countSt=n;
        } catch (InterruptedException ex) {       }
    }
    public void run() {
        for(int i=0; i<10; i++) add();
        for(int i=0; i<10; i++) addStatic();
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        Data d=new Data();
        MyThread t1=new MyThread(d);
        MyThread t2=new MyThread(d);

        Thread.sleep(1000);
        System.out.println(d.count);
        System.out.println(Data.countSt);    }

}
