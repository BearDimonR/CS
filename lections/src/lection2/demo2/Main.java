package lection2.demo2;

import java.util.Random;

public class Main {

	private static int mNum=12;
	private static ThreadLocal<Integer> threadLocal =
			ThreadLocal.withInitial(() -> new Random().nextInt(100));

	public static void main(String[] args) {
		threadLocal.set(new Random().nextInt(100));
		mNum=13;
		Runnable runner = () -> {
            threadLocal.set(new Random().nextInt(100));
            mNum++;
            System.out.println("Thread " +
                    Thread.currentThread().getName() + ", value is " + threadLocal.get()+", mNum ="+mNum);
        };

		//стартуємо три потоки
		for (int i=0; i<3; i++)
			(new Thread (runner)).start();

		System.out.println("Main Thread, value is " +	threadLocal.get()+", mNum ="+mNum);
	}
}

