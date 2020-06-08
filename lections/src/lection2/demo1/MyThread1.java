package lection2.demo1;

public class MyThread1 extends Thread {

@Override
	public void run(){
		for (int i = 0; i < 3000; i++){
			int k = 10/i;
			System.out.println("Name = " + Thread.currentThread().getName() +
					", i = " + i);
			}
	}

}
