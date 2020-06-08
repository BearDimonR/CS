package lection2.demo1;

public class Main2 {

	public static void main(String[] args) {
		final MyThread1 mt = new MyThread1();
		MyThread2 omt = new MyThread2();
		//Встановили один потік як демон
		mt.setDaemon(true);
		System.out.println("Демон "+mt.isDaemon());

		mt.start();

		//mt.join();
		//omt.thread.join();

		omt.thread.start();
		System.out.println("end of main");

	}

}
