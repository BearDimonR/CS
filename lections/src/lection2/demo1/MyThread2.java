package lection2.demo1;

public class MyThread2 implements Runnable {
	public Thread thread;

	public MyThread2()
	{
		thread = new Thread(this);
	}

	@Override
	public void run() {
		for (int i = 0; i < 30; i++)
			System.out.println("Name = " + Thread.currentThread().getName() +
					", i = " + i);
	}
}
